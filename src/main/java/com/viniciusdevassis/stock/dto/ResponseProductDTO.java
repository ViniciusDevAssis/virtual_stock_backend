package com.viniciusdevassis.stock.dto;

public class ResponseProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer inventory;

    public ResponseProductDTO() {
    }

    public ResponseProductDTO(Long id, String name, String description, Double price, Integer inventory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
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
}
