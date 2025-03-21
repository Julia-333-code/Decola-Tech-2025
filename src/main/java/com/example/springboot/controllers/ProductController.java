package com.example.springboot.controllers;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

@PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRecordDto, productModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
}

@GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
}

@GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productD = productRepository.findById(id);
        if (productD.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productD.get());
    }

@DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productD = productRepository.findById(id);
        if (productD.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");
        }

        productRepository.delete(productD.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");
    }
}








