package com.graduate.mobilekiosk.repository;

import com.graduate.mobilekiosk.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByUserName(String name);
}
