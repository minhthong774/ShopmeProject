package com.shopme.admin.category;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.shopme.admin.user.UserNotFoundException;
import com.shopme.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.compiler.ast.CallExpr;


@Service
@Transactional
public class CategoryService {
    
    @Autowired
    public CategoryRepository categoryRepo;

    public List<Category> listAll(){
        List<Category> rootCategories = categoryRepo.findRootCategories();
        return listHierarchicalCategories(rootCategories);
    }

    private List<Category> listHierarchicalCategories(List<Category> rootCategories){
        List<Category> hierarchicalCategories = new ArrayList<>();

        for (Category rootCategory : rootCategories){
            hierarchicalCategories.add(Category.copyFull(rootCategory));

            listSubHierarchicalCategories(hierarchicalCategories, rootCategory, 0);
        }

        return hierarchicalCategories;
    }

    private void listSubHierarchicalCategories(List<Category> hierarchicalCategories, Category parent, int subLevel){
        Set<Category> children = parent.getChildren();
        int newSubLevel = subLevel + 1;
        for(Category subCategory : children){
            String name = "";
            for(int i = 0; i < newSubLevel; i++){
                name+="--";
            }
            name  += subCategory.getName();

            hierarchicalCategories.add(Category.copyFull(subCategory, name));

            listSubHierarchicalCategories(hierarchicalCategories, subCategory, newSubLevel);
        }
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

                listSubCategoriesUsedInForm(categoriesUsedInForm, category, 0);
            }
        }

        return categoriesUsedInForm;
    }

    private void listSubCategoriesUsedInForm(List<Category> categoriesUsedInForm, Category parent, int subLevel){
        int newSubLevel = subLevel + 1;
        Set<Category> children = parent.getChildren();

        for(Category subCategory : children){
            String name = "";
            for(int i = 0; i < newSubLevel; i++){
                name+="--";
            }
            name  += subCategory.getName();

            categoriesUsedInForm.add(Category.copyIdAndName(subCategory.getId(), name));

            listSubCategoriesUsedInForm(categoriesUsedInForm, subCategory, newSubLevel);

        }
    }

    public Category get(Integer id) throws CategoryNotFoundException {
		try {
			return categoryRepo.findById(id).get();
		}catch(NoSuchElementException ex) {
			throw new CategoryNotFoundException("Could not find any category with ID " + id);
		}
	}

    public String checkUnique(Integer id, String name, String alias){
        boolean isCreatingNew = (id==null||id==0);
        
        Category categoryByName = categoryRepo.findByName(name);

        if(isCreatingNew){
            if(categoryByName!=null){
                return "DuplicateName";
            } else{
                Category categoryByAlias = categoryRepo.findByAlias(alias);
                if(categoryByAlias != null){
                    return "DuplicateAlias";
                }
            }
        } else{
            if(categoryByName != null && categoryByName.getId() != id){
                return "DuplicateName";
            }
            Category categoryByAlias = categoryRepo.findByAlias(alias);
            if(categoryByAlias!=null&&categoryByAlias.getId()!=id){
                return "DuplicateAlias";
            }
        }

        return "OK";
    }

}
