package com.example.ms_pedidos360_catalog.controller;

import com.example.ms_pedidos360_catalog.dto.ProductUpdateDto;
import com.example.ms_pedidos360_catalog.dto.StockDecreaseDto;
import com.example.ms_pedidos360_catalog.dto.StockUpdateDto;
import com.example.ms_pedidos360_catalog.model.Product;
import com.example.ms_pedidos360_catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateDto dto) {
        Product updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Product> adjustStock(@PathVariable Long id, @Valid @RequestBody StockUpdateDto dto) {
        Product updated = productService.adjustStock(id, dto.getStock());
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/stock/decrease")
    public ResponseEntity<Product> decreaseStock(
            @PathVariable Long id,
            @RequestBody(required = false) StockDecreaseDto dto,
            @RequestParam(required = false) Integer quantity) {

        int decreaseQty = 1;
        if (dto != null && dto.getQuantity() != null) {
            decreaseQty = dto.getQuantity();
        } else if (quantity != null) {
            decreaseQty = quantity;
        }

        Product updated = productService.decreaseStock(id, decreaseQty);
        return ResponseEntity.ok(updated);
    }
}
