package com.viniciusdevassis.stock.controllers;

import com.viniciusdevassis.stock.dto.CreateProductDTO;
import com.viniciusdevassis.stock.dto.ResponseProductDTO;
import com.viniciusdevassis.stock.dto.UpdateProductDTO;
import com.viniciusdevassis.stock.entities.Product;
import com.viniciusdevassis.stock.entities.User;
import com.viniciusdevassis.stock.mapper.ProductMapper;
import com.viniciusdevassis.stock.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductMapper mapper;

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> getProductsByUser(){
        List<Product> products = service.getProductsByUser();
        List<ResponseProductDTO> response = mapper.productsToListDTO(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseProductDTO> getProductById(@PathVariable Long id){
        Product product = service.getProductById(id);
        ResponseProductDTO productDTO = mapper.productToResponseProductDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseProductDTO> createProduct(@Valid @RequestBody CreateProductDTO dto) {
        ResponseProductDTO createdProduct = service.createProduct(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(createdProduct);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductDTO dto) {
        ResponseProductDTO updatedProduct = service.updateProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/deactivate/{name}")
    public ResponseEntity<Void> deactivateProduct(@PathVariable String name) {
        service.deactivateProductByName(name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activate/{name}")
    public ResponseEntity<Void> activateProduct(@PathVariable String name) {
        service.activateProductByName(name);
        return ResponseEntity.noContent().build();
    }
}
