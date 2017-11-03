package com.example.demo.model;

import java.util.List;

/**
 * Created by Hayk on 16.10.2017.
 */
public class Carusel {
   private Product product;
   private Product next;
   private Product prew;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getNext() {
        return next;
    }

    public void setNext(Product next) {
        this.next = next;
    }

    public Product getPrew() {
        return prew;
    }

    public void setPrew(Product prew) {
        this.prew = prew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carusel carusel = (Carusel) o;

        if (product != null ? !product.equals(carusel.product) : carusel.product != null) return false;
        if (next != null ? !next.equals(carusel.next) : carusel.next != null) return false;
        return prew != null ? prew.equals(carusel.prew) : carusel.prew == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        result = 31 * result + (prew != null ? prew.hashCode() : 0);
        return result;
    }
}
