package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    private long id;
    private String name;
    private double price;
    private String gender;
    private String sale;
    private List<Photo> photos;
    private List<Category> categoryList;
    private Brand brand;
    private long count;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "price", nullable = false, length = 45)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Column(name = "gender", nullable = false, length = 45)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name = "sale", nullable = false, length = 45)
    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    @Column(name = "count", nullable = false, length = 45)
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (count != product.count) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (gender != null ? !gender.equals(product.gender) : product.gender != null) return false;
        if (sale != null ? !sale.equals(product.sale) : product.sale != null) return false;
        if (photos != null ? !photos.equals(product.photos) : product.photos != null) return false;
        if (categoryList != null ? !categoryList.equals(product.categoryList) : product.categoryList != null)
            return false;
        return brand != null ? brand.equals(product.brand) : product.brand == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        result = 31 * result + (categoryList != null ? categoryList.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }


    @OneToMany(mappedBy = "product")
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }


    @ManyToMany(mappedBy="products")
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
