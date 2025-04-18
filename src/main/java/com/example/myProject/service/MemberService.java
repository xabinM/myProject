package com.example.myProject.service;

import com.example.myProject.domain.member.Member;
import com.example.myProject.dto.member.SignupRequest;
import com.example.myProject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void save(SignupRequest request) {
        Optional<Member> existingMember = memberRepository.findByUsername(request.getUsername());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        Member member = new Member();

        member.setUsername(request.getUsername());
        member.setPassword(request.getPassword());
        member.setEmail(request.getEmail());

        memberRepository.save(member);
    }

    public Member login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }
}
