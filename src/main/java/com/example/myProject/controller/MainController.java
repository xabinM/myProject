package com.example.myProject.controller;

import com.example.myProject.domain.member.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");

        if (member != null) {
            model.addAttribute("username", member.getUsername());
        }

        return "index";
    }
}
