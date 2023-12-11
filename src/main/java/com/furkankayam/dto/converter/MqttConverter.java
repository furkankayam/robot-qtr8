package com.furkankayam.dto.converter;

import com.furkankayam.dto.response.MessageResponseDto;
import com.furkankayam.model.Message;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MqttConverter {

    public MessageResponseDto toResponseDto(List<String> message) {

        Gson gson = new Gson();

        return gson.fromJson(message.get(0), MessageResponseDto.class);

    }

    public Message toMessage(MessageResponseDto messageResponseDto) {

        Message message = new Message();
        message.setPosition(messageResponseDto.getPosition());
        message.setSensor(messageResponseDto.getSensor());
        message.setRightPwm(messageResponseDto.getRightPwm());
        message.setLeftPwm(messageResponseDto.getLeftPwm());

        return message;

    }

}
