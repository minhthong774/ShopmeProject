package com.shopme.admin.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.shopme.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
@Transactional
public class CategoryService {
    
    @Autowired
    public CategoryRepository categoryRepo;

    public List<Category> listAll(){
        return (List<Category>) categoryRepo.findAll(Sort.by("name").ascending());
    }

    public Category save(Category category){
        return categoryRepo.save(category);
    }

    public List<Category> listCategoriesUsedInform(){
        List<Category> categoriesUsedInForm = new ArrayList<>();

        Iterable<Category> categoriesInDB = categoryRepo.findAll();

        for (Category category : categoriesInDB){
            if(category.getParent()==null){
                categoriesUsedInForm.add(Category.copyIdAndName(category));

                Set<Category> children = category.getChildren();

                listChildren(categoriesUsedInForm, category, 0);
            }
        }

        return categoriesUsedInForm;
    }

    private void listChildren(List<Category> categoriesUsedInForm, Category parent, int subLevel){
        int newSubLevel = subLevel + 1;
        Set<Category> children = parent.getChildren();

        for(Category subCategory : children){
            String name = "";
            for(int i = 0; i < newSubLevel; i++){
                name+="--";
            }
            name  += subCategory.getName();

            categoriesUsedInForm.add(Category.copyIdAndName(subCategory.getId(), name));

            listChildren(categoriesUsedInForm, subCategory, newSubLevel);

        }
    }

}
