package com.hyeonseo.zepattendance.service;

import com.hyeonseo.zepattendance.dto.AttendanceRequestDto;
import com.hyeonseo.zepattendance.entity.Attendance;
import com.hyeonseo.zepattendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public Attendance checkAttendance(AttendanceRequestDto request) {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));

        attendanceRepository
                .findByZepUserIdAndCheckDate(request.getZepUserId(), today)
                .ifPresent(a -> {
                    throw new RuntimeException("이미 출석했습니다.");
                });

        Attendance attendance = new Attendance(
                request.getZepUserId(),
                today
        );

        return attendanceRepository.save(attendance);
    }
}