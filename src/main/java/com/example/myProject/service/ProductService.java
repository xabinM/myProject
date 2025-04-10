package com.example.myProject.service;

import com.example.myProject.domain.member.Member;
import com.example.myProject.domain.product.Product;
import com.example.myProject.dto.product.ProductRegisterRequest;
import com.example.myProject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerProduct(ProductRegisterRequest request, Member seller) {

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setImageUrl(request.getImageUrl());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setSeller(seller);

        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
