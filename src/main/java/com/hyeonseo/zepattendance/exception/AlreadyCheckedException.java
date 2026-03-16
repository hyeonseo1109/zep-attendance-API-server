package com.hyeonseo.zepattendance.exception;

public class AlreadyCheckedException extends RuntimeException {
    public AlreadyCheckedException() {
        super("이미 출석했자나~~");
    }
}