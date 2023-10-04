package org.example.app.entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Product {

    private Integer id;
    private String name;
    private Integer quota;
    private Double price;

    public Product() {
    }

    public Product(String name, Integer quota, Double price) {
        this.name = name;
        this.quota = quota;
        this.price = price;
    }

    public Product(Integer id, String name, Integer quota, Double price) {
        this.id = id;
        this.name = name;
        this.quota = quota;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(quota, product.quota)) return false;
        return price.compareTo(product.price) == 0;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (quota != null ? quota.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "id - " + this.getId() +
                ", name: " + this.getName() +
                ", quota: " + this.getQuota() +
                ", price: " + this.getPrice() + "\n";
    }
}