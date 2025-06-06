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
import java.util.Objects;

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
        model.addAttribute("username", loginMember.getUsername());

        List<Product> myProducts = productService.getMyProducts(loginMember.getId());
        model.addAttribute("myProducts", myProducts);

        return "my_products";
    }

    @GetMapping("/register")
    public String registerPage(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        model.addAttribute("username", loginMember.getUsername());

        return "product_registration";
    }

    @PostMapping("/register")
    public String registerProduct(@Valid @ModelAttribute ProductRegisterRequest request, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        productService.registerProduct(request, loginMember);

        return "redirect:/";
    }

    @GetMapping("/products/{id}/edit")
    public String editPage(@PathVariable Long id, Model model, HttpSession session) {
        //당장 필요없는 코드 나중에 삭제 가능 멤버인지 체크할 때 사용해야함
        Member loginMember = (Member) session.getAttribute("loginMember");
        Product product = productService.getProduct(id);

        if (!Objects.equals(loginMember.getId(), product.getSeller().getId())) {
            throw new IllegalArgumentException("잘못된 접근");
        }

        model.addAttribute("product", product);

        return "edit";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute ProductEditRequest request, HttpSession session) {

        productService.updateProduct(request.getProductId(), request);

        return "redirect:/myProducts";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return "redirect:/myProducts";
    }
}
