package com.example.myProject.service;

import com.example.myProject.domain.ProductStatus;
import com.example.myProject.domain.member.Member;
import com.example.myProject.domain.product.Product;
import com.example.myProject.dto.product.ProductEditRequest;
import com.example.myProject.dto.product.ProductRegisterRequest;
import com.example.myProject.repository.ProductRepository;
import jakarta.transaction.Transactional;
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
        product.setStatus(ProductStatus.SELLING);
        product.setDeleted(false);

        productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));
    }

    public List<Product> getMyProducts(Long id) {
        return productRepository.findAllBySellerId(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllByDeletedFalse();
    }

    @Transactional  // dirty checking 을 위한
    public void updateProduct(Long productId, ProductEditRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        product.updatePartial(request);
    }

    @Transactional  // dirty checking 을 위한
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));
        product.setDeleted(true);
    }
}
