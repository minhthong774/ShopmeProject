package com.shopme.admin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {
    @Autowired CustomerService service;
    
    @RequestMapping("/customers/check_email")
    public String checkDuplicateEmail(@Param("id")Integer id, @Param("email")String email){
        return service.isEmailUnique(id, email)?"OK":"Duplicated";
    }
}
