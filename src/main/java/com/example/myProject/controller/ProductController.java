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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/myProducts")
    public String myProductsPage(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        List<Product> myProducts = productService.getMyProducts(loginMember.getId());
        model.addAttribute("myProducts", myProducts);

        return "myProducts";
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

    @GetMapping("/products/{id}/edit")
    public String editPage(@PathVariable Long id, Model model) {
        Product product = productService.getProduct(id);

        // 이 상품을 수정한다는  model 던지기
        model.addAttribute("product", product);

        return "edit";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute ProductEditRequest request, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        productService.updateProduct(loginMember.getId(), request);

        return "redirect:/products";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return "redirect:/products";
    }
}
