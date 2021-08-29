package com.shopme.admin.category;

import java.util.List;

import com.shopme.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CategoryService {
    
    @Autowired
    public CategoryRepository categoryRepo;

    public List<Category> listAll(){
        return (List<Category>) categoryRepo.findAll(Sort.by("name").ascending());
    }
}
