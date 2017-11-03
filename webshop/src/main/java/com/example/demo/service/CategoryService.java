package com.example.demo.service;

import com.example.demo.model.Category;

import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayk on 03.09.2017.
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Product> getProductByCategory(Category category){
        return (List<Product>) (( categoryRepository.getOne(category.getId())).getProducts());
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public void addOrUpdateCategory(Category category){
        categoryRepository.saveAndFlush(category);
    }
    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }
    public Category getById(long id){return categoryRepository.findOne(id);}
}
