package com.gfa.greenbay.services;

import com.gfa.greenbay.exceptions.BidException;
import com.gfa.greenbay.exceptions.InvalidPhotoUrlException;
import com.gfa.greenbay.exceptions.ItemIsNotSellable;
import com.gfa.greenbay.exceptions.ItemNotFoundException;
import com.gfa.greenbay.exceptions.MissingParamsException;
import com.gfa.greenbay.exceptions.NotEnoughMoney;
import com.gfa.greenbay.exceptions.NotPositiveNumberException;
import com.gfa.greenbay.models.Bid;
import com.gfa.greenbay.models.DTO.BidDTO;
import com.gfa.greenbay.models.DTO.BidResponseDTO;
import com.gfa.greenbay.models.DTO.ItemDTO;
import com.gfa.greenbay.models.DTO.ItemResponseDTO;
import com.gfa.greenbay.models.GreenUser;
import com.gfa.greenbay.models.Items;
import com.gfa.greenbay.repositories.BidRepository;
import com.gfa.greenbay.repositories.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final ItemRepository itemRepository;
  private final ErrorService errorService;
  private final UserService userService;
  private final BidRepository bidRepository;

  public ItemService(ItemRepository itemRepository,
                     ErrorService errorService,
                     UserService userService,
                     BidRepository bidRepository) {
    this.itemRepository = itemRepository;
    this.errorService = errorService;
    this.userService = userService;
    this.bidRepository = bidRepository;
  }

  public Items createNewItem(ItemDTO item) throws Exception {
    if (errorService.checkClassNullFields(item).size() > 0) {
      throw new MissingParamsException(errorService.buildMissingFieldErrorMessage(errorService.checkClassNullFields(item)));
    } else if (item.getStartingPrice() < 0) {
      throw new NotPositiveNumberException("Price must be higher then zero.");
    } else if (checkUrlValidation(item.getPhotoUrl())) {
      throw new InvalidPhotoUrlException("Invalid photo type.");
    } else {
      return saveNewItem(item);
    }
  }

  private boolean checkUrlValidation(String photoUrl) {
    String firstFourDigits = photoUrl.substring(0, 4);
    String firstFiveDigits = photoUrl.substring(0, 5);
    String lastFourDigits = photoUrl.substring(photoUrl.length() - 4);
    if (firstFourDigits.equals("http") || firstFiveDigits.equals("https")) {
      switch (lastFourDigits) {
        case ".jpg":
        case ".png":
        case ".bmp":
          return false;
        default:
          return true;
      }
    } else {
      return true;
    }
  }

  private Items saveNewItem(ItemDTO itemDTO) {
    Items item = new ModelMapper().map(itemDTO, Items.class);
    GreenUser user = userService.findUserByToken();
    List<Items> userItems = user.getOwnedItems();
    item.setOwner(user);
    userItems.add(item);
    user.setOwnedItems(userItems);
    this.itemRepository.save(item);
    this.userService.saveGreenUser(user);
    return item;
  }

  public ItemResponseDTO infoAboutItem(long id) throws Exception {
    Items item = findById(id);
    if (item != null && item.isSellable()) {
      return convertToResponse(item);
    } else if (item != null && !item.isSellable()) {
      return convertToResponse(item);
    }
    throw new ItemNotFoundException("Item not found.");
  }

  public Items findById(long id) {
    return this.itemRepository.findById(id).orElse(null);
  }

  private ItemResponseDTO convertToResponse(Items item) {
    ItemResponseDTO responseItem = new ModelMapper().map(item, ItemResponseDTO.class);
    List<BidResponseDTO> bidResponseList = new ArrayList<>();

    for (Bid b : item.getBids()) {
      BidResponseDTO bidResponse = new BidResponseDTO();
      bidResponse.setName(b.getUser().getUsername());
      bidResponse.setBid(b.getBid());
      bidResponseList.add(bidResponse);
    }

    if (item.getBuyer() == null) {
      responseItem.setSeller(item.getOwner().getUsername());
      responseItem.setBuyer("No buyer yet.");
      responseItem.setBids(bidResponseList);
      return responseItem;
    } else {
      responseItem.setSeller(item.getOwner().getUsername());
      responseItem.setBuyer(item.getBuyer().getUsername());
      responseItem.setBids(bidResponseList);
      return responseItem;
    }
  }

  public Object bidToItem(long id, BidDTO bid) throws Exception {
    Items item = findById(id);
    if (item == null) {
      throw new ItemNotFoundException("Item not found.");
    } else if (!item.isSellable()) {
      throw new ItemIsNotSellable("The item can't be bought.");
    }
    return biddingLogic(item, bid);
  }

  private Object biddingLogic(Items item, BidDTO bidDTO) throws Exception {
    GreenUser user = userService.findUserByToken();
    Bid bid = new Bid(bidDTO.getBid());
    if (bid.getBid() > user.getBalance()) {
      throw new NotEnoughMoney("Not enough money on the user's account.");
    } else if (maxBid(item) >= bid.getBid()) {
      throw new BidException("Bid is too low.");
    } else if (bid.getBid() >= item.getPurchasePrice() &&
        user.getBalance() >= bid.getBid()) {
      return buyItem(bid, item, user);
    } else if (maxBid(item) < bid.getBid() &&
        bid.getBid() < item.getPurchasePrice() &&
        user.getBalance() >= bid.getBid()) {
      return takeABid(bid, item, user);
    }
    throw new BidException("Something went wrong in the bidding logic.");
  }

  private long maxBid(Items items) {
    if (itemRepository.maxBidOnItem() == null) {
      return items.getStartingPrice();
    }
    return itemRepository.maxBidOnItem();
  }

  private Object takeABid(Bid bid, Items item, GreenUser user) {
    bid.setUser(user);
    bid.setItems(item);

    List<Bid> user_bidList = user.getBids();
    user_bidList.add(bid);
    user.setBids(user_bidList);

    List<Bid> item_bidList = item.getBids();
    item_bidList.add(bid);
    item.setBids(item_bidList);

    this.bidRepository.save(bid);
    this.itemRepository.save(item);
    this.userService.saveGreenUser(user);

    return convertToResponse(item);
  }

  private Object buyItem(Bid bid, Items item, GreenUser user) {
    item.setSellable(false);
    item.setBuyer(user);

    List<Items> user_itemList = user.getBoughtItems();
    user_itemList.add(item);
    user.setBoughtItems(user_itemList);
    user.setBalance(user.getBalance() - bid.getBid());

    this.itemRepository.save(item);
    this.userService.saveGreenUser(user);

    return convertToResponse(item);
  }
}

/*  private Long highestBid(List<Bid> bids, Items item) {
    if (bids.stream().map(Bid::getBid).max(Long::compareTo).isPresent()) {
      return bids.stream().map(Bid::getBid).max(Long::compareTo).get();
    }
    return item.getStartingPrice();
  }*/
