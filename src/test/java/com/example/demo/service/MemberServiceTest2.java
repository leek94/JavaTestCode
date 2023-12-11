package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest // 스프링부트테스트는 통합테스테 사용됨 실제 스프링컨테이너를 만들기 때문에  Mock 객체 설정이 필요 없음
public class MemberServiceTest2 {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("맴버 만들기")
    void createMemberSuccess() {
        Long memberId = memberService.createMember("hi1", 10);
        assertThat(memberId).isEqualTo(1L);
    }

    @Test
    @DisplayName("이름 중복으로 만들기 실패")
    void createMemberFail() {
        Long memberId = memberService.createMember("hi1",10);
        assertThatThrownBy(() ->
                memberService.createMember("hi1",12))
                .isInstanceOf(IllegalStateException.class);
    }
}
