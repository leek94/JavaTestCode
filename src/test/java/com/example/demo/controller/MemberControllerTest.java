package com.example.demo.controller;

import com.example.demo.dto.MemberResponseDto;
import com.example.demo.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MemberController.class) // Controller 테스트시 사용
class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberServiceImpl memberService;

    @Test
    @DisplayName("리스트 반환받기")
    void getList() throws Exception{
        // given
//        List<MemberResponseDto.ListDto> list = List.of(new MemberResponseDto.ListDto("asd", 10)
//                                , new MemberResponseDto.ListDto("fsd", 12));
        // 자바 9 이상부터 List.of를 사용 할 수 있다 -> 현재 자바 8 사용 중이므로 ArrayList 사용으로 대체
        List<MemberResponseDto.ListDto> list = new ArrayList<>();
        list.add(new MemberResponseDto.ListDto("asd", 10));
        list.add(new MemberResponseDto.ListDto("fsd", 12));

        Mockito.when(memberService.findAll()).thenReturn(list);

        // when

        // then
        mvc.perform(MockMvcRequestBuilders
                .get("/members") // get 요청을 보냄
                .contentType(MediaType.APPLICATION_JSON)) // contentType 타입이나 형식 지정
                .andDo(MockMvcResultHandlers.print())  // HTTP 요청에 대한 응답이나 로그, 특정 정보 츨력시 사용
                .andExpect(MockMvcResultMatchers.status().isOk()) // HTTP 상태 코드 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("fsd"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("asd"));
                // andExpext는 검증하는 로직
                // jsonPath("$.name").value("fsd) -> 단일 객체 값 검증시
                // jsonPath("$[1].name").value("fsd) -> 리스트 반환 검증시

    }

}