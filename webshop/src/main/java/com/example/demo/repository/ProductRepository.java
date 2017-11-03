package com.example.demo.repository;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.model.Photo;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Repository
  public interface ProductRepository extends JpaRepository<Product,Long> {


  public List<Product> getByBrand(Brand brand);

  public List<Product> getByCategoryList(List<Category> categories);

  public LinkedList<Product> getAllBySale(String sale);

  public List<Product> getAllByGender(String gender);



}
