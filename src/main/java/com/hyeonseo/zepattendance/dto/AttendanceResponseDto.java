package com.hyeonseo.zepattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttendanceResponseDto {

    private Long id;
    private String zepUserId;
    private String message;

}