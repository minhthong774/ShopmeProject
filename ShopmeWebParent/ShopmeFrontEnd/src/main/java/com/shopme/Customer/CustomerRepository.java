package com.shopme.Customer;

import com.shopme.common.entity.Customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public Customer findByEmail(String email);

    public Customer findByVerificationCode(String code);

    @Query("UPDATE Customer c SET enabled = 1, c.verificationCode = null WHERE c.id = ?1")
    @Modifying
    public void enable(Integer id);
}
