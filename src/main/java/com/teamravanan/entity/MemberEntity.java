package com.teamravanan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "MEMBER_TAB")
@Builder
@AllArgsConstructor
@Data
public class MemberEntity {

    @Transient
    public static final String MEMBER_SEQUENCE = "employee_sequence";

    @Id
    long id;
    String firstName;
    String lastName;
    String email;
    String phone;

    public MemberEntity() {
    }
}

