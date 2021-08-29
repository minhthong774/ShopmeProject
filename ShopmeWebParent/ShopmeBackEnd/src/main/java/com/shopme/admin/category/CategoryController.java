package com.shopme.admin.category;

import java.util.List;

import com.shopme.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    
    @Autowired
public CategoryService service;

    @GetMapping("/categories")
    public String listCategory(Model model){
        List<Category> listCategories = service.listAll();

        model.addAttribute("listCategories", listCategories);

        return "categories/categories";
    }
}
