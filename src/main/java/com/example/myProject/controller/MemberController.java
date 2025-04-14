package com.example.myProject.controller;

import com.example.myProject.domain.member.Member;
import com.example.myProject.dto.member.LoginRequest;
import com.example.myProject.dto.member.SignupRequest;
import com.example.myProject.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");

        if (member != null) {
            model.addAttribute("username", member.getUsername());
        }

        return "index";
    }

    @GetMapping("/signup")
    public String signupPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        if (member != null) {
            model.addAttribute("username", member.getUsername());
            return "index";
        }
        return "signup";        // → templates/signup.html 을 보여줌
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request) {
        // DB에 저장
        memberService.save(request);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");

        if (member != null) {
            model.addAttribute("username", member.getUsername());
            return "index";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, HttpSession session, Model model) {
        try {
            Member member = memberService.login(request.getUsername(), request.getPassword());
            session.setAttribute("loginMember", member);

            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
