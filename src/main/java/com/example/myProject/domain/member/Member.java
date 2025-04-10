package com.example.myProject.domain.member;

import com.example.myProject.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
}
