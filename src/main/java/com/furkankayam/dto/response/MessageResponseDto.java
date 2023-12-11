package com.furkankayam.dto.response;

import lombok.Data;

@Data
public class MessageResponseDto {

    private Integer position;

    private Byte[] sensor;

    private Integer rightPwm;

    private Integer leftPwm;

}
