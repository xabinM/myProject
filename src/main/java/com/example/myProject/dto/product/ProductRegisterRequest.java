package com.example.myProject.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRegisterRequest {
    @NotBlank(message = "상품 이름을 입력해주세요.")
    @Size(max = 255, message = "상품이름은 255자 이하로 입력해주세요.")
    String productName;

    @NotBlank(message = "사진을 주소를 넣어주세요.")
    @Size(max = 1000, message = "")
    String imageUrl;

    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    Integer price;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    @Size(max = 1000, message = "설명은 1000자 이하로 입력해주세요.")
    String description;
}
