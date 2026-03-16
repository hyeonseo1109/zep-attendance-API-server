package com.hyeonseo.zepattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponseDto<T> {
    private boolean success;
    private T contents;
}