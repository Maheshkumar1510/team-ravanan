package com.teamravanan.controller;

import com.teamravanan.dto.MemberDto;
import com.teamravanan.entity.MemberEntity;
import com.teamravanan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController

public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping
    public MemberDto addMember(@RequestBody MemberDto memberDto) {
        System.out.println("Employee added: " + memberDto);
        return memberService.addMember(memberDto);
    }

    @GetMapping("/get/{id}")
    public MemberDto getEmployee(@PathVariable Long id) {
        return memberService.getMember(id);
        
    }
}

