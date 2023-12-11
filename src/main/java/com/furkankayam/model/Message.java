package com.furkankayam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer position;

    private Byte[] sensor;

    @Column(name = "right_pwm")
    private Integer rightPwm;

    @Column(name = "left_pwm")
    private Integer leftPwm;

    @Column(name = "create_date")
    private LocalTime timeZone = LocalTime.now();

}
