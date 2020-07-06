package com.gfa.greenbay.repositories;

import com.gfa.greenbay.models.GreenUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<GreenUser, Long> {

  GreenUser findByUsername(String username);

  boolean existsGreenUserByUsername(String username);
}
