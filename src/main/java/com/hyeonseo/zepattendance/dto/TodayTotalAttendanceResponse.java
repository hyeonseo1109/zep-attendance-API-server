package com.hyeonseo.zepattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TodayTotalAttendanceResponse {
    private List<String> userId;
    private int total;
}