package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.BrandService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;

    @RequestMapping(value = "/admin/product/addc", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("category") Product product){
        productService.addOrUpdateProduct(product);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/admin/product/update/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable("id") Long id, Product product){
        productService.addOrUpdateProduct(product);

        return "redirect:/admin/product";

    }

    @RequestMapping(value = "/user/product", method = RequestMethod.POST)
    public String showAll(ModelMap modelMap){
        List<Product> products=productService.getAllProducts();
        modelMap.addAttribute("products",products);
        return "user/product";
    }
    @RequestMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(productService.getProductById(id));

        return "redirect:/admin/product";
    }
    @RequestMapping(value = "/user/product/{id}", method = RequestMethod.GET)
    public String showProduct(@PathVariable("id")long id,ModelMap modelMap){
        Product product=productService.getProductById(id);
        modelMap.addAttribute("product",product);
        return "/user/product/{id}";

    }
    @RequestMapping(value = "/product/bycategory/{id}")
    public String showProductsByCategory(@PathVariable("id")long id,ModelMap modelMap) {

        List<Product> products = productService.getProductsByCategory(categoryService.getById(id));
        List<Category> categories = categoryService.getAllCategories();
        List<Brand> brands = brandService.getAll();
        LinkedList<Product> sales=productService.getProductsBySale("SALE");

        modelMap.addAttribute("sales", sales);


        modelMap.addAttribute("brands", brands);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);


        return "index";
    }
    @RequestMapping(value = "/product/bygender/{product.gender}")
    public String showProductsByGender(@PathVariable("product.gender")String gender,ModelMap modelMap) {
        List<Product> products;
        if (gender.equals("FOR ALL")){
            products = productService.getAllProducts();
        } else {
                products = productService.getProductsByGender(gender);
          }
        List<Category> categories = categoryService.getAllCategories();
        List<Brand> brands = brandService.getAll();
        LinkedList<Product> sales=productService.getProductsBySale("SALE");

        modelMap.addAttribute("sales", sales);


        modelMap.addAttribute("brands", brands);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);

        return "index";
    }
    @RequestMapping(value = "/product/bybrand/{brand.name}")
    public String showProductsByBrand(@PathVariable("brand.name")String name,ModelMap modelMap) {

        List<Product> products = productService.getProductByBrand(brandService.getBrandByName(name));
        List<Category> categories = categoryService.getAllCategories();
        List<Brand> brands = brandService.getAll();
        LinkedList<Product> sales=productService.getProductsBySale("SALE");


        modelMap.addAttribute("brands", brands);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("sales", sales);


        return "index";
    }
}
