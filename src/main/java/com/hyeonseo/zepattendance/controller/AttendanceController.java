package com.hyeonseo.zepattendance.controller;

import com.hyeonseo.zepattendance.dto.AttendanceRequestDto;
import com.hyeonseo.zepattendance.dto.AttendanceResponseDto;
import com.hyeonseo.zepattendance.entity.Attendance;
import com.hyeonseo.zepattendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    private static final Random random = new Random();

    private static final String[] MESSAGES = {
            "어머 이게 웬일이야~ 반가워라!",
            "학생~ 또 왔구려~ 홀홀홀..",
            "어이! 지각이다 지각! 운동장 5바퀴 돌고 와!",
            "후후.. 기다리고 있었습니다, 이쪽으로 모시지요.",
            "너,.너 누구야! 여기 어떻게 들어왔어!",
            "흥.. 절대 기다린 건 아니라구."
    };

    @PostMapping
    public AttendanceResponseDto checkAttendance(
            @RequestBody AttendanceRequestDto request
    ) {

        Attendance attendance = attendanceService.checkAttendance(request);

        String message =  MESSAGES[random.nextInt(MESSAGES.length)];

        return new AttendanceResponseDto(
                attendance.getId(),
                attendance.getZepUserId(),
                message
        );
    }

}