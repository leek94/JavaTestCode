package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    // test 주체
    MemberService memberService;

    // test 협력자
    @MockBean
    MemberRepository memberRepository;

    // Test를 실행하기 전마다 MemberService에 가짜 객체를 주입시켜준다
    @BeforeEach
    void setUp(){
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    @DisplayName("맴버 생성 성공")
    void createMemberSuccess(){
        //given
        Member member3 = Member.builder().name("hi3").age(10).build();
        ReflectionTestUtils.setField(member3, "id", 3L); // id 값을 지정해줌
        // 테스트시 private 값 변경시 setter 메서드 사용 못할 시 사용함

        Mockito.when(memberRepository.save(member3)).thenReturn(member3);
        //가짜 객체 응답 정의 : 실제 데이터 베이스 이용하지 않고
        // memberRepository.save(member3)을 실행시  member3의 값을 리턴 하도록

        //when
        Long hi3 = memberService.createMember("hi3", 10);
        // 서비스에서 member3의 id 값을리턴

        //then
        assertThat(hi3).isEqualTo(3L);
        // 리턴한 값이랑 3이 맞는지 확인함
    }

    @Test
    @DisplayName("멤버 생성시 member1과 이름이 같아서 예외 발생")
    void createMemberFail() {
        //given
        Member member1 = Member.builder().name("hi1").age(10).build();
        // 객체 생성
        Mockito.when(memberRepository.findByName("hi1")).thenReturn(Optional.of(member1));
        // 가짜 객체 응답 정의 : 레포지토리에서 hi1로 값을 찾아보고 옵셔널로 감싼 member1을 반환

        //when

        //then
        assertThatThrownBy(() -> memberService.createMember("hi1", 10))
                                 .isInstanceOf(IllegalStateException.class);
        // assertThatThrownBy(() -> ) 에외 발생하는지 확인하는 구문
        // isInstanceOf를 뒤에는 발생하는 예외를 적어줌
    }

}