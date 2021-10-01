package com.graduate.mobilekiosk.repository;

import com.graduate.mobilekiosk.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
