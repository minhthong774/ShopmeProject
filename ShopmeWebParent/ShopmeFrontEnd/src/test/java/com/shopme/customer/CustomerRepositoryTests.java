package com.shopme.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import com.shopme.Customer.CustomerRepository;
import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {
    @Autowired CustomerRepository repo;
    @Autowired TestEntityManager entityManager;

    @Test
    public void testCreateCustomer(){
        String email="abc@gmail.com";
        String password = "123";
        String firstName = "henry";
        String lastName = "cyrus";
        String phoneNumber = "0826879409";
        String addressLine1 = "abc";
        String city = "Ho Chi Minh";
        String state = "Ho Chi Minh";
        Country country = entityManager.find(Country.class, 16);
        String postalCode = "520";
        Date createdTime = new Date();
        Boolean enabled = true;
        Customer customer = new Customer(email, password, firstName, lastName, phoneNumber, addressLine1, city, state, country, postalCode, createdTime, enabled);

        Customer savedCustomer = repo.save(customer);
        assertThat(savedCustomer).isNotNull();
    }

    @Test
    public void testListCustomer(){
        Iterable<Customer> listCustomers = repo.findAll();
        assertThat(listCustomers).size().isEqualTo(1);
    }

    @Test
    public void testUpdateCustomer(){
        Integer id = 1;
        String email="abc@gmail.com";
        String password = "1234";
        String firstName = "tunbi";
        String lastName = "cyrus";
        String phoneNumber = "0826879409";
        String addressLine1 = "xom 1";
        String city = "Ho Chi Minh";
        String state = "Ho Chi Minh";
        Country country = new Country(14);
        String postalCode = "520";
        Date createdTime = new Date();
        Boolean enabled = false;
        Customer customer = new Customer(id, email, password, firstName, lastName, phoneNumber, addressLine1, city, state, country, postalCode, createdTime, enabled);

        Customer savedCustomer = repo.save(customer);
        assertThat(savedCustomer).isNotNull();
    }

    @Test
    public void testGetCustomer(){
        Integer id = 1;
        Customer customer = repo.findById(id).get();
        System.out.println(customer);
    }

    @Test
    public void testDeleteCustomer(){
        Integer id = 2;
        repo.deleteById(id);

        Optional<Customer> customer = repo.findById(id);

        assertThat(customer).isNotPresent();
    }

    @Test
    public void testFindByEmail(){
        String email="abc@gmail.com";
        Customer customer = repo.findByEmail(email);

        assertThat(customer.getId()).isEqualTo(2);
    }

    @Test
    public void testFindByVerificationCode(){
        String verificationCode="520";
        Customer customer = repo.findByVerificationCode(verificationCode);

        assertThat(customer.getId()).isEqualTo(2);
    }

    @Test
    public void testEnableCustomer(){
        Integer id = 3;
        repo.enable(id);
        Optional<Customer> customer = repo.findById(id);

        assertThat(customer.get().getEnabled()).isEqualTo(true);
    }
}
