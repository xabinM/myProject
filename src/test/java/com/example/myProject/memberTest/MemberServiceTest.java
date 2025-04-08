package com.example.myProject.memberTest;

import com.example.myProject.domain.member.Member;
import com.example.myProject.dto.SignupRequest;
import com.example.myProject.repository.MemberRepository;
import com.example.myProject.service.MemberService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(false)    // 이거면 트렌젝션 발동 x 실제로 DB에 커밋해버림
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입 저장 확인")
    void checkMemberSave() {
        //given
        SignupRequest request = new SignupRequest();
        request.setUsername("sabin");

        //when
        memberService.save(request);

        //then
        Member member = memberRepository.findByUsername("sabin").orElseThrow();

        Assertions.assertThat(member.getUsername()).isEqualTo("sabin");
    }
}
