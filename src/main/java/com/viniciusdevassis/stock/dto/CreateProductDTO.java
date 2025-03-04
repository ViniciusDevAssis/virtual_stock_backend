package com.viniciusdevassis.stock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProductDTO {

    @NotBlank(message = "O campo nome é obrigatório")
    private String name;
    private String description;
    @NotNull(message = "O campo preço é obrigatório")
    private Double price;

    public CreateProductDTO() {
    }

    public CreateProductDTO(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
}
