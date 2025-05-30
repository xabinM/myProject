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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signupPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        if (member != null) {
            model.addAttribute("username", member.getUsername());
            return "redirect:/";
        }
        return "signup";        // → templates/signup.html 을 보여줌
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request, Model model) {
        try {
            memberService.save(request);

            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(defaultValue = "/") String redirectURL, HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");

        System.out.println("redirectURL : " + redirectURL);
        if (member != null) {
            model.addAttribute("username", member.getUsername());
            return "redirect:/";
        }

        model.addAttribute("redirectURL", redirectURL);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpSession session,
                        Model model) {
        try {
            Member member = memberService.login(request.getUsername(), request.getPassword());
            session.setAttribute("loginMember", member);

            return "redirect:" + redirectURL;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
