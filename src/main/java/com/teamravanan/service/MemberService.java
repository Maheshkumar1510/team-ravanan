package com.teamravanan.service;

import com.teamravanan.dto.MemberDto;
import com.teamravanan.entity.MemberEntity;
import com.teamravanan.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    public MemberDto addMember(MemberDto memberDto) {

        MemberEntity memberEntity = MemberEntity.builder()
                .id(sequenceGeneratorService.generateSequence(MemberEntity.MEMBER_SEQUENCE))
                .firstName(memberDto.getFirstName())
                .lastName(memberDto.getLastName())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .build();
        MemberEntity memberEntity1 = memberRepo.save(memberEntity);

        return MemberDto.builder().memId(memberEntity1.getId()).build();
    }

    public MemberDto getMember(long id) {
        MemberEntity entity = memberRepo.findById(id);
        return MemberDto.builder()
                .memId(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }
}
