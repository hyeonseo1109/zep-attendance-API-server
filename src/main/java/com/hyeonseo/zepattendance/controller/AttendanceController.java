package com.hyeonseo.zepattendance.controller;

import com.hyeonseo.zepattendance.dto.AttendanceRequestDto;
import com.hyeonseo.zepattendance.dto.AttendanceResponseDto;
import com.hyeonseo.zepattendance.entity.Attendance;
import com.hyeonseo.zepattendance.exception.AlreadyCheckedException;
import com.hyeonseo.zepattendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/today")
    public List<Attendance> getTodayAttendance() {
        return attendanceService.getTodayAttendance();
    }

    @GetMapping("/user")
    public List<Attendance> getToTalAttendanceById(@RequestParam String userId) {
        return attendanceService.getUserAttendance(userId);
    }

    @ExceptionHandler(AlreadyCheckedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyChecked(AlreadyCheckedException e) {
        return e.getMessage();
    }

}