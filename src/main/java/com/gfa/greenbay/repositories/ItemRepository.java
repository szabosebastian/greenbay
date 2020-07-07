package com.gfa.greenbay.repositories;

import com.gfa.greenbay.models.Items;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Items, Long> {

  List<Items> findAllBy();

  @Query(value = "SELECT * FROM Items LIMIT ?1 OFFSET ?2", nativeQuery = true)
  List<Items> listItemsByPageNumber(int limit, int offset);

  @Query(value = "SELECT MAX(bid) FROM Items INNER JOIN Bid ON Items.id = Bid.id", nativeQuery = true)
  long maxBidOnItem();
}
