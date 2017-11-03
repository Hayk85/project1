package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/admin/addc", method = RequestMethod.POST)
public String addCategory(@ModelAttribute("category") Category category){
    categoryRepository.saveAndFlush(category);
    return "redirect:/admin";
}

    @RequestMapping(value = "/admin/category/update/{id}", method = RequestMethod.POST)
public String updateCategory(@PathVariable("id") Long id, Category category){
    categoryRepository.saveAndFlush(category);
    return "redirect:/admin/category";

}

    @RequestMapping(value = "/user/category", method = RequestMethod.POST)
public String showAll(ModelMap modelMap){
        List<Category> categories=categoryRepository.findAll();
        modelMap.addAttribute("categories",categories);
        return "user/category";
    }
    @RequestMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id){
    categoryRepository.delete(id);
    categoryRepository.flush();
    return "redirect:/admin/category";
    }
    @RequestMapping(value = "/user/category/{id}", method = RequestMethod.GET)
    public String showCategory(@PathVariable("id")long id,ModelMap modelMap){
        Category category=categoryRepository.findOne(id);
        modelMap.addAttribute("category",category);
        return "/user/category/{id}";

    }


}
