package com.teamravanan.controller;

import com.teamravanan.dto.MemberDto;
import com.teamravanan.entity.MemberEntity;
import com.teamravanan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Document(collection = "database_sequences")
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

    @GetMapping("/getAllDetails")
    public List<MemberDto> getAllEmployee(){
        return memberService.getAllEmployee();
    }

    @GetMapping("/g")
    public String getEmployee(){
        return "HI";
    }
}

