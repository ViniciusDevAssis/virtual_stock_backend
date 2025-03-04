package com.viniciusdevassis.stock.entities;

import com.viniciusdevassis.stock.enums.Status;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description = "";
    private Double price;
    private Integer inventory = 0;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, Integer inventory, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
