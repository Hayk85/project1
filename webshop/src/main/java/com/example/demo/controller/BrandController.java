package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BrandController {
    @Autowired
    BrandRepository brandRepository;
    @RequestMapping(value = "/admin/addb", method = RequestMethod.POST)
    @ResponseBody
    public String addBrand(@ModelAttribute("brand")Brand brand){
        brandRepository.saveAndFlush(brand);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/brand/update/{id}", method = RequestMethod.POST)
    public String updateBrand(@PathVariable("id") Long id, Brand brand){
        brandRepository.saveAndFlush(brand);
        return "redirect:/admin/brand";

    }

    @RequestMapping(value = "/user/brand", method = RequestMethod.POST)
    public String showAll(ModelMap modelMap){
        List<Brand> brand=brandRepository.findAll();
        modelMap.addAttribute("brand",brand);
        return "user/brand";
    }
    @RequestMapping("/admin/brand/delete/{id}")
    public String deleteBrand(@PathVariable("id") long id){
        brandRepository.delete(id);
        brandRepository.flush();
        return "redirect:/admin/brand";
    }
    @RequestMapping(value = "/user/brand/{id}", method = RequestMethod.GET)
    public String showBrand(@PathVariable("id")long id,ModelMap modelMap){
        Brand brand=brandRepository.findOne(id);
        modelMap.addAttribute("brand",brand);
        return "/brand/{id}";

    }

}
