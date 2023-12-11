package com.example.demo.service;

import com.example.demo.dto.MemberResponseDto;

import java.util.List;

public interface MemberService {
    List<MemberResponseDto.ListDto> findAll();

    Long createMember(String name, int age);
}
