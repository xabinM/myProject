package com.example.myProject.controller;

import com.example.myProject.domain.member.Member;
import com.example.myProject.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final MemberService memberService;

    public ProfileController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", loginMember.getUsername());
        model.addAttribute("email", loginMember.getEmail());

        return "profile";
    }
}
