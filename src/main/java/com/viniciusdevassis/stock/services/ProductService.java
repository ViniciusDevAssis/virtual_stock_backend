package com.viniciusdevassis.stock.services;

import com.viniciusdevassis.stock.controllers.advice.exceptions.ProductIdNotFoundException;
import com.viniciusdevassis.stock.controllers.advice.exceptions.ProductNameNotFoundException;
import com.viniciusdevassis.stock.dto.CreateProductDTO;
import com.viniciusdevassis.stock.dto.ResponseProductDTO;
import com.viniciusdevassis.stock.dto.UpdateProductDTO;
import com.viniciusdevassis.stock.entities.Product;
import com.viniciusdevassis.stock.entities.User;
import com.viniciusdevassis.stock.enums.Errors;
import com.viniciusdevassis.stock.enums.Status;
import com.viniciusdevassis.stock.mapper.ProductMapper;
import com.viniciusdevassis.stock.repositories.ProductRepository;
import com.viniciusdevassis.stock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductMapper mapper;

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProductById(Long id){
        return repository.findById(id).orElseThrow(() -> new ProductIdNotFoundException(Errors.PEE201));
    }

    @Transactional
    public ResponseProductDTO createProduct(CreateProductDTO dto){
        Long userId = getUserIdFromToken();
        User user = userRepository.findById(userId).orElse(null);
        Product product = mapper.createProductDTOToProduct(dto);
        product.setUser(user);
        Product savedProduct = repository.save(product);
        return mapper.productToResponseProductDTO(savedProduct);
    }

    @Transactional
    public ResponseProductDTO updateProduct(Long id, UpdateProductDTO dto){
        Product product = getProductById(id);

        product.setName(
                dto.getName() != null && !dto.getName().isBlank() ? dto.getName() : product.getName()
        );
        product.setDescription(
                dto.getDescription() != null ? dto.getDescription() : product.getDescription()
        );
        product.setPrice(
                dto.getPrice() != null ? dto.getPrice() : product.getPrice()
        );
        product.setInventory(
                dto.getInventory() != null ? dto.getInventory() : product.getInventory()
        );

        Product updatedProduct = repository.save(product);
        return mapper.productToResponseProductDTO(updatedProduct);

    }

    @Transactional
    public void deactivateProductByName(String name){
        Product product = getProductByName(name);
        if (product.getStatus() != Status.INACTIVE) {
            product.setStatus(Status.INACTIVE);
            repository.save(product);
        }
    }

    @Transactional
    public void activateProductByName(String name) {
        Product product = getProductByName(name);
        if (product.getStatus() != Status.ACTIVE) {
            product.setStatus(Status.ACTIVE);
            repository.save(product);
        }
    }

    private List<Product> getProductsByUser(){
        Long userId = getUserIdFromToken();
        User user = userService.getUserById(userId);
        return user.getProducts();
    }

    public List<Product> getActiveProductsByUser(){
        return getProductsByUser().stream().filter(product -> product.getStatus().equals(Status.ACTIVE)).toList();
    }

    private Product getProductByName(String name){
        return repository.findByNameIgnoreCase(name).orElseThrow(() -> new ProductNameNotFoundException(Errors.PEE202));
    }

    private Long getUserIdFromToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((User) principal).getId();
        } else {
            return Long.parseLong(principal.toString());
        }
    }
}
