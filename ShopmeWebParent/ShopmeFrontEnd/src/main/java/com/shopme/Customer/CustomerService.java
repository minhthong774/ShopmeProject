package com.shopme.Customer;

import java.util.Date;
import java.util.List;


import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;
import com.shopme.setting.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class CustomerService {
    @Autowired private CountryRepository countryRepo;
    @Autowired private CustomerRepository repo;
    @Autowired PasswordEncoder passwordEncoder;

    public List<Country> listAllCountries(){
        return countryRepo.findALLByOrderByNameAsc();
    }

    public boolean isEmailUnique(String email){
        Customer customer = repo.findByEmail(email);
        return customer == null;
    }

    public void registerCustomer(Customer customer){
        encodePassword(customer);
        customer.setEnabled(false);
        customer.setCreatedTime(new Date());

        String randomCode = RandomString.make(64);
        customer.setVerificationCode(randomCode);

        repo.save(customer);
    }

    private void encodePassword(Customer customer){
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

    public boolean verify(String verificationCode){
        Customer customer = repo.findByVerificationCode(verificationCode);

        if(customer==null|| customer.isEnabled()){
            return false;
        } else{
            repo.enable(customer.getId());
            return true;
        }
    };
}
