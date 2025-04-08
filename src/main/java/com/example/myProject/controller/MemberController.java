package com.example.myProject.controller;

import com.example.myProject.dto.SignupRequest;
import com.example.myProject.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // → templates/signup.html 을 보여줌
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request) {
        // DB에 저장
        memberService.save(request);

        return "login";
    }
}
