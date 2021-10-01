package com.graduate.mobilekiosk.web.customer;

import com.graduate.mobilekiosk.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
