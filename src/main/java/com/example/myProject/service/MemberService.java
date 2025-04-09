package com.example.myProject.service;

import com.example.myProject.domain.member.Member;
import com.example.myProject.dto.SignupRequest;
import com.example.myProject.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(SignupRequest request) {
        Member member = new Member();

        member.setUsername(request.getUsername());
        member.setPassword(request.getPassword());
        member.setEmail(request.getEmail());

        memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
