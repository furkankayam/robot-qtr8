package com.furkankayam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MqttClientException extends RuntimeException{
    public MqttClientException(String message) {
        super(message);
    }
}