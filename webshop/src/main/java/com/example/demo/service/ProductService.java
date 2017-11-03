package com.example.demo.service;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.model.Photo;
import com.example.demo.model.Product;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hayk on 03.09.2017.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(Category category){
List<Category> categories=new ArrayList<>();
categories.add(category);
        return productRepository.getByCategoryList(categories);
    }
    public  List<Product> getProductByBrand(Brand brand){

        return brandRepository.findOne(brand.getId()).getProducts();
    }

    public void addOrUpdateProduct(Product product){
        productRepository.saveAndFlush(product);
    }
    public void deleteProduct(Product product){
       productRepository.delete(product);
    }
    public Product getProductById(long id){return productRepository.findOne(id);}
    public List<Product> getProductsByGender(String gender){
        return productRepository.getAllByGender(gender);
    }public LinkedList<Product> getProductsBySale(String sale){
        return productRepository.getAllBySale(sale);
    }

}
