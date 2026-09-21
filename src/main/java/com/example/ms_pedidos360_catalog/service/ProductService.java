package com.example.ms_pedidos360_catalog.service;

import com.example.ms_pedidos360_catalog.dto.ProductUpdateDto;
import com.example.ms_pedidos360_catalog.exception.InsufficientStockException;
import com.example.ms_pedidos360_catalog.exception.ProductNotFoundException;
import com.example.ms_pedidos360_catalog.model.Product;
import com.example.ms_pedidos360_catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Transactional
    public Product createProduct(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductUpdateDto dto) {
        Product product = getProductById(id);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Transactional
    public Product adjustStock(Long id, Integer stock) {
        Product product = getProductById(id);
        product.setStock(stock);
        return productRepository.save(product);
    }

    @Transactional
    public Product decreaseStock(Long id, Integer quantity) {
        Product product = getProductById(id);
        if (product.getStock() < quantity) {
            throw new InsufficientStockException(
                    "Insufficient stock for product " + product.getName() + " (ID: " + id + "). " +
                            "Available: " + product.getStock() + ", Requested: " + quantity);
        }
        product.setStock(product.getStock() - quantity);
        return productRepository.save(product);
    }
}
