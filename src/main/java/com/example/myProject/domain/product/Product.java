package com.example.myProject.domain.product;

import com.example.myProject.domain.BaseTimeEntity;
import com.example.myProject.domain.member.Member;
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
    private String imageUrl;
    private int price;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id")
    private Member seller;

}
