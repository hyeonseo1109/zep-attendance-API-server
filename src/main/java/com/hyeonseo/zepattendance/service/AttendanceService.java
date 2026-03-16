package com.hyeonseo.zepattendance.service;

import com.hyeonseo.zepattendance.dto.AttendanceRequestDto;
import com.hyeonseo.zepattendance.entity.Attendance;
import com.hyeonseo.zepattendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    private static final Random random = new Random();

    private static final String[] MESSAGES = {
            "어머 이게 웬일이야~ 반가워라!",
            "학생~ 또 왔구려~ 홀홀홀..",
            "어이! 지각이다 지각! 운동장 5바퀴 돌고 와!",
            "후후.. 기다리고 있었습니다, 이쪽으로 모시지요.",
            "너,.너 누구야! 여기 어떻게 들어왔어!",
            "흥.. 절대 기다린 건 아니라구."
    };

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
    public String getRandomMessage() {
        return MESSAGES[random.nextInt(MESSAGES.length)];
    }
}