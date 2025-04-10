package com.example.myProject.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRegisterRequest {
    String productName;
    String imageUrl;
    int price;
    String description;
}
