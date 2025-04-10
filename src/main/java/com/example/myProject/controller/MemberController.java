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
    public String signupPage() {
        return "signup";        // → templates/signup.html 을 보여줌
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request) {
        // DB에 저장
        memberService.save(request);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, HttpSession session, Model model) {
        Optional<Member> optionalMember = memberService.findByUsername(request.getUsername());
        if (optionalMember.isEmpty() || !optionalMember.get().getPassword().equals(request.getPassword())) {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login";
        }

        session.setAttribute("loginMember", optionalMember.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
