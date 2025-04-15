package com.example.myProject.domain.product;

import com.example.myProject.domain.BaseTimeEntity;
import com.example.myProject.domain.ProductStatus;
import com.example.myProject.domain.member.Member;
import com.example.myProject.dto.product.ProductEditRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String productName;

    @Column(length = 1000)
    private String imageUrl;

    private Integer price;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id")
    private Member seller;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private boolean isDeleted;

    public void updatePartial(ProductEditRequest request) {
        if (request.getProductName() != null) {
            this.productName = request.getProductName();
        }
        if (request.getImageUrl() != null) {
            this.imageUrl = request.getImageUrl();
        }
        if (request.getPrice() != null) {
            this.price = request.getPrice();
        }
        if (request.getDescription() != null) {
            this.description = request.getDescription();
        }
    }
}
