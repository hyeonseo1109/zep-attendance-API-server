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

    @PostMapping
    public AttendanceResponseDto checkAttendance(
            @RequestBody AttendanceRequestDto request
    ) {

        Attendance attendance = attendanceService.checkAttendance(request);

        String message = attendanceService.getRandomMessage();

        return new AttendanceResponseDto(
                attendance.getId(),
                attendance.getZepUserId(),
                message
        );
    }

}