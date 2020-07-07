package com.gfa.greenbay.services;

import com.gfa.greenbay.exceptions.NotPositiveNumberException;
import com.gfa.greenbay.models.Bid;
import com.gfa.greenbay.models.DTO.ItemListDTO;
import com.gfa.greenbay.models.Items;
import com.gfa.greenbay.repositories.BidRepository;
import com.gfa.greenbay.repositories.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemListService {

  private final ItemRepository itemRepository;

  public ItemListService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;

  }

  public List<ItemListDTO> first20Item() {
    List<ItemListDTO> first20List = new ArrayList<>();
    for (Items i : this.itemRepository.findAllBy()) {
      if (i.isSellable()) {
        long lastBid = i.getBids().stream().map(Bid::getBid).max(Long::compareTo).orElse(i.getStartingPrice());
        ItemListDTO itemDTO = new ItemListDTO(i.getName(), i.getPhotoUrl(), lastBid);
        first20List.add(itemDTO);
        if (first20List.size() > 20) {
          return first20List;
        }
      }
    }
    return first20List;
  }

  public List<ItemListDTO> listItemsByPage(long pageNumber) throws Exception {
    int limit = 20;
    int offset = (int) pageNumber == 1 ? 0 : (int) pageNumber * 20 - 20;
    if (pageNumber < 0) {
      throw new NotPositiveNumberException("Page number must be higher then zero.");
    } else {
      List<ItemListDTO> pagePerItemList = new ArrayList<>();
      for (Items i : this.itemRepository.listItemsByPageNumber(limit, offset)) {
        if (i.isSellable()) {
          long lastBid = i.getBids().stream().map(Bid::getBid).max(Long::compareTo).orElse(i.getStartingPrice());
          ItemListDTO itemDTO = new ItemListDTO(i.getName(), i.getPhotoUrl(), lastBid);
          pagePerItemList.add(itemDTO);
        }
      }
      return pagePerItemList;
    }
  }
}
