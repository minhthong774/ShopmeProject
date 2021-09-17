package com.shopme.admin.customer;

import com.shopme.common.entity.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends  PagingAndSortingRepository<Customer, Integer>{

    @Query("SELECT c From Customer WHERE c.firstName = ?1")
    public Page<Customer> findAll(String keyword, Pageable pageable);

    public Customer findByEmail(String email);

    @Query("UPDATE Customer c set c.enabled = ?2 WHERE c.id = ?1")
    @Modifying
    public void updateEnabledStatus(Integer id, boolean enable);

    public Long countById(Integer id);
}
