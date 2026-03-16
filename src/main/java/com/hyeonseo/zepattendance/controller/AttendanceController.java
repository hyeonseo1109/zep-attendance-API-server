package com.hyeonseo.zepattendance.controller;

import com.hyeonseo.zepattendance.dto.*;
import com.hyeonseo.zepattendance.entity.Attendance;
import com.hyeonseo.zepattendance.exception.AlreadyCheckedException;
import com.hyeonseo.zepattendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public BaseResponseDto<AttendancePostResponse> checkAttendance(
            @RequestBody AttendanceRequestDto request
    ) {

        Attendance attendance = attendanceService.checkAttendance(request);

        String message = attendanceService.getRandomMessage();

        AttendancePostResponse response = new AttendancePostResponse(
                attendance.getCheckDate().toString(),
                message
        );
        return new BaseResponseDto<>(true, response);
    }

    @GetMapping("/today")
    public BaseResponseDto<TodayTotalAttendanceResponse> getTodayAttendance() {
        List<Attendance> todayAttendance = attendanceService.getTodayAttendance();

        TodayTotalAttendanceResponse response = new TodayTotalAttendanceResponse(
                todayAttendance.stream()
                        .map(a -> a.getZepUserId())
                        .toList(),
                todayAttendance.size()
        );
        return new BaseResponseDto<>(true, response);
    }

    @GetMapping("/user")
    public BaseResponseDto<AttendanceByUserResponse> getUserAttendance(@RequestParam String userId) {
        List<Attendance> userAttendance = attendanceService.getUserAttendance(userId);

        List<String> dates = userAttendance.stream()
                .map(a -> a.getCheckDate().toString())
                .toList();

        List<String> safeDates = dates.isEmpty() ? List.of() : dates;

        AttendanceByUserResponse response = new AttendanceByUserResponse(safeDates, safeDates.size());
        return new BaseResponseDto<>(true, response);
    }

    @ExceptionHandler(AlreadyCheckedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public BaseResponseDto<String> handleAlreadyChecked(AlreadyCheckedException e) {
        return new BaseResponseDto<>(false, e.getMessage());
    }

}