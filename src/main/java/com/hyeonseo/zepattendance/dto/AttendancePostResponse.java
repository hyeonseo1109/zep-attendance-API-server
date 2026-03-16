package com.hyeonseo.zepattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttendancePostResponse {

    private String date;
    private String message;

}