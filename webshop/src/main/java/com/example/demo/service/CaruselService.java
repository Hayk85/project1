package com.example.demo.service;

import com.example.demo.model.Carusel;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayk on 16.10.2017.
 */
public class CaruselService {
    @Autowired
    ProductService productService;
    private List<Carusel> carusels=new ArrayList<>();
    public List<Carusel> getCarusels(List<Product> productList){
        for (Product product : productList) {
            Carusel carusel=new Carusel();
            if (productList.indexOf(product)==0){
                carusel.setProduct(product);
                carusel.setNext(productList.get(productList.indexOf(product)+1));
                carusel.setPrew(productList.get(productList.size()-1));
            }
            else if(productList.indexOf(product)==productList.size()-1){
                carusel.setProduct(product);
                carusel.setNext(productList.get(0));
                carusel.setPrew(productList.get(productList.indexOf(product)-1));
            }else{
                carusel.setProduct(product);
                carusel.setNext(productList.get(productList.indexOf(product)+1));
                carusel.setPrew(productList.get(productList.indexOf(product)-1));
            }
            carusels.add(carusel);

        }
        return carusels;

    }
}
