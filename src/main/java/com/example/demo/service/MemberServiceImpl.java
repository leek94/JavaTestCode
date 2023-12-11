package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.domain.Member;
import com.example.demo.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public List<MemberResponseDto.ListDto> findAll(){
        return memberRepository.findAll().stream().map(a -> new MemberResponseDto.ListDto(a.getName(),a.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public Long createMember(String name, int age){
        memberRepository.findByName(name).ifPresent(a -> {
            throw new IllegalStateException("이미 있는 아이디");
        });
        // ifPresent는 이미 값이 있으면 안에 있는 람다식을 발생시킴(예외)

        Member member = Member.builder()
                .age(age)
                .name(name).build();

        return memberRepository.save(member).getId();
    }

}