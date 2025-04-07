package com.teamravanan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class MemberDto {
    long memId;
    String firstName;
    String lastName;
    String email;
    String phone;
}
