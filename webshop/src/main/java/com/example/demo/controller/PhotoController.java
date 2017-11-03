package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PhotoController {
    @Autowired
    PhotoRepository photoRepository;
    @RequestMapping(value = "/admin/addph", method = RequestMethod.POST)
    public String addPhoto(@ModelAttribute("category")Photo photo){
        photoRepository.saveAndFlush(photo);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/photo/update/{id}", method = RequestMethod.POST)
    public String updatePhoto(@PathVariable("id") long id,long productId, Photo photo){
        photoRepository.saveAndFlush(photo);

        return "redirect:/admin/photo";

    }

    @RequestMapping(value = "/user/photo", method = RequestMethod.POST)
    public String showAll(ModelMap modelMap){
        List<Photo> photos=photoRepository.findAll();
        modelMap.addAttribute("photos",photos);
        return "user/photo";
    }
    @RequestMapping("/admin/photo/delete/{id}")
    public String deletePhoto(@PathVariable("id") long id){
        photoRepository.delete(id);
        photoRepository.flush();
        return "redirect:/admin/photo";
    }
    @RequestMapping(value = "/user/product/photo/{id}", method = RequestMethod.GET)
    public String showPhoto(@PathVariable("id")long id,ModelMap modelMap){
        Photo photo=photoRepository.findOne(id);
        modelMap.addAttribute("photo",photo);
        return "/user/photo/{id}";

    }
}
