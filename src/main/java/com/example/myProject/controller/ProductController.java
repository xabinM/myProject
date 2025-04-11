package com.example.myProject.controller;

import com.example.myProject.domain.member.Member;
import com.example.myProject.domain.product.Product;
import com.example.myProject.dto.product.ProductEditRequest;
import com.example.myProject.dto.product.ProductRegisterRequest;
import com.example.myProject.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "product_registration";
    }

    @PostMapping("/register")
    public String registerProduct(@Valid @ModelAttribute ProductRegisterRequest request, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        productService.registerProduct(request, loginMember);

        return "redirect:/products";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute ProductEditRequest request) {
        productService.updateProduct(request);
        return "redirect:/products";
    }
}
