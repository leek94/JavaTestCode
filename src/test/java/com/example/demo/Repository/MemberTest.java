package com.example.demo.Repository;

import com.example.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @Test
    @DisplayName("맴버가 생성되는지 확인하는 테스트")
    void createMember() {
        //given
        Member member = Member.builder()
                .age(10)
                .name("hi")
                .build();
        //when

        //then
        Assertions.assertThat(member.getAge()).isEqualTo(10);
        Assertions.assertThat(member.getName()).isEqualTo("hi");
    }

    @Test
    @DisplayName("멤버의 나이 바뀌는지 확인하는 테스트")
    void changeAgeTest() {
        //given
        Member member = Member.builder()
                .age(18)
                .name("hi")
                .build();
        //when
        member.changeAge(13);
        //then

        Assertions.assertThat(member.getAge()).isEqualTo(12);
    }
}
