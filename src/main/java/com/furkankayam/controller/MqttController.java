package com.furkankayam.controller;

import com.furkankayam.dto.response.MessageResponseDto;
import com.furkankayam.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MqttController {

    private final MessageService messageService;

    @GetMapping("/subscribe")
    public ResponseEntity<MessageResponseDto> getMessage() throws MqttException, InterruptedException {
        return new ResponseEntity<>(messageService.getMessage(), HttpStatus.OK);
    }

}
