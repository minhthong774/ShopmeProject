package com.shopme.admin.customer;

import java.util.List;
import java.util.Optional;

import com.shopme.admin.Country.CountryRepository;
import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {
    public static final int CUSTOMER_PER_PAGE = 1;
    @Autowired CustomerRepository customerRepo;
    @Autowired CountryRepository countryRepo;
    @Autowired PasswordEncoder passwordEncoder;

    public Page<Customer> listByPage(String keyword, int pageNum){
        Sort sort = Sort.by("firstName").ascending();
        Pageable pageable = PageRequest.of(pageNum-1, CUSTOMER_PER_PAGE);
        return customerRepo.findAll(keyword, pageable);
    }

    public void updateCustomerEnabledStatus(Integer id, boolean enabled){
        customerRepo.updateEnabledStatus(id, enabled);
    }

    public Customer get(Integer id) throws CustomerNotFoundException{
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isPresent()){
            throw new CustomerNotFoundException("Could not find any customer with id " + id );
        }

        return customer.get();
    }

    public List<Country> listAllCountry(){
        return countryRepo.findALLByOrderByNameAsc();
    }

    public boolean isEmailUnique(Integer id, String email){
        Customer customerByEmail = customerRepo.findByEmail(email);
		
		if(customerByEmail == null) return true;
		
		boolean isCreatingNew = (id ==null);
		
		if(isCreatingNew) {
			if(customerByEmail !=null) return false;
		} else {
			if(customerByEmail.getId() != id) {
				return false;
			}
		}
		
		return true;
    }

    public void save(Customer customer){
        customerRepo.save(customer);
    }

    public void delete(Integer id){
        customerRepo.deleteById(id);
    }
}
