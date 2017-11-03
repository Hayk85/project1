package com.example.demo.repository;

import com.example.demo.model.Photo;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Hayk on 27.08.2017.
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {

    public List<Photo> findByProduct(Product product);
}
