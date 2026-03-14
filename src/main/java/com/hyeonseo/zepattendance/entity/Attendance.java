package com.hyeonseo.zepattendance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(
        name = "attendance",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_user_checkdate",
                        columnNames = {"zep_user_id", "check_date"}
                )
        }
)

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zep_user_id", nullable = false)
    private String zepUserId;

    @Column(name = "check_date", nullable = false)
    private LocalDate checkDate;

    public Attendance (String zepUserId, LocalDate checkDate) {
        this.zepUserId = zepUserId;
        this.checkDate = checkDate;
    }
}
