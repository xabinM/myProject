package com.example.myProject.controller;

import com.example.myProject.domain.member.Member;
import com.example.myProject.domain.product.Product;
import com.example.myProject.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        if (member != null) {
            model.addAttribute("username", member.getUsername());
        }

        return "index";
    }
}
