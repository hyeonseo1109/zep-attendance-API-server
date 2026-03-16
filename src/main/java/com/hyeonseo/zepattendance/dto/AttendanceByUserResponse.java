package com.hyeonseo.zepattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AttendanceByUserResponse {
    private List<String> date;
    private int total;
}