package com.shopme.admin.customer;

import java.util.List;

import com.shopme.common.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    @Autowired CustomerService customerService;

    @GetMapping("/customer")
    public String listFirstPage(Model model){
        return listByPage(1, null, model);
    }

    @GetMapping("/customer/page/{pageNum}")
    public String listByPage(@PathVariable("pageNum") Integer pageNum,
        @Param("keyword") String keyword,
        Model model
    ){
        Page<Customer> page = customerService.listByPage(keyword, pageNum);
        List<Customer> listCustomers = page.getContent();

        long startCount = (pageNum - 1) * customerService.CUSTOMER_PER_PAGE + 1;
		long endCount = startCount + customerService.CUSTOMER_PER_PAGE -1;
		if(endCount>page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listUsers", listCustomers);
		model.addAttribute("keyword", keyword);

        return "customers/customers";
    }

    @GetMapping("/customers/{id}/enabled/{status}")
    public String updateCustomerEnabledStatus(
        @PathVariable("id") Integer id,
        @PathVariable("status") boolean enabled,
        RedirectAttributes ra
    ){
        customerService.updateCustomerEnabledStatus(id, enabled);
		String status = enabled?"enabled":"disabled";
		String message = "The customer ID " + id + " has been " + status;
		ra.addFlashAttribute("message", message);
        return "redirect:customers";
    }

    @GetMapping("/customers/detail/{id}")
    public String viewCustomer(@PathVariable("id") Integer id, Model model, 
    RedirectAttributes ra){
        try{
            Customer customer = customerService.get(id);

            model.addAttribute("customer", customer);

            return "customers/customer_detail_modal";
        } catch( CustomerNotFoundException ex){
            ra.addFlashAttribute("message", ex.getMessage());

            return "redirect:/customers";
        }

    }

    
}
