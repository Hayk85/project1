package com.example.demo.service;

import com.example.demo.model.Brand;
import com.example.demo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayk on 03.09.2017.
 */
@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

   public List<Brand> getAll(){
       return brandRepository.findAll();
   }
   public void addOrUpdateBrand(Brand brand){
       brandRepository.saveAndFlush(brand);
   }
   public void deleteBrand(Brand brand){
       brandRepository.delete(brand);
   }
   public Brand getById(long id){return brandRepository.findOne(id);}
   public Brand getBrandByName(String name){return brandRepository.getByName(name);}
}
