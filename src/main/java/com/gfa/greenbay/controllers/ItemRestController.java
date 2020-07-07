package com.gfa.greenbay.controllers;

import com.gfa.greenbay.models.DTO.BidDTO;
import com.gfa.greenbay.models.DTO.ItemDTO;
import com.gfa.greenbay.services.ItemListService;
import com.gfa.greenbay.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemRestController {

  private final ItemService itemService;
  private final ItemListService itemListService;

  public ItemRestController(ItemService itemService,
                            ItemListService itemListService) {
    this.itemService = itemService;
    this.itemListService = itemListService;
  }

  @GetMapping("/list")
  public ResponseEntity first20Item() {
    return ResponseEntity.ok().body(this.itemListService.first20Item());
  }

  @GetMapping("/list/{pageNumber}")
  public ResponseEntity listItemByPageNumber(@PathVariable("pageNumber") long pageNumber) throws Exception {
    return ResponseEntity.ok().body(this.itemListService.listItemsByPage(pageNumber));
  }

  @PostMapping("/add")
  public ResponseEntity createItem(@RequestBody ItemDTO item) throws Exception {
    return ResponseEntity.ok().body(this.itemService.createNewItem(item));
  }

  @GetMapping("/info/{id}")
  public ResponseEntity infoAboutItem(@PathVariable("id") long id) throws Exception {
    return ResponseEntity.ok().body(this.itemService.infoAboutItem(id));
  }

  @GetMapping("/{id}")
  public ResponseEntity bidToItem(@PathVariable("id") long id, @RequestBody BidDTO bid) throws Exception {
    return ResponseEntity.ok().body(this.itemService.bidToItem(id, bid));
  }
}
