package com.furkankayam.service.impl;
import com.furkankayam.config.MqttConfig;
import com.furkankayam.dto.converter.MqttConverter;
import com.furkankayam.dto.response.MessageResponseDto;
import com.furkankayam.model.Message;
import com.furkankayam.model.MqttSubscribeModel;
import com.furkankayam.repository.MessageRepository;
import com.furkankayam.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MqttConfig mqttConfig;
    private final MqttConverter mqttConverter;
    private final MessageRepository messageRepository;

    @Override
    public MessageResponseDto getMessage() throws MqttException, InterruptedException {

        List<String> subscribeModelList = new ArrayList<>();
        MqttSubscribeModel mqttSubscribeModel = new MqttSubscribeModel();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        mqttConfig.getInstance().subscribeWithResponse("message", (s, message) -> {

            mqttSubscribeModel.setId(message.getId());
            mqttSubscribeModel.setMessage(new String(message.getPayload()));
            mqttSubscribeModel.setQos(message.getQos());

            subscribeModelList.add(mqttSubscribeModel.getMessage());
            countDownLatch.countDown();

        });

        countDownLatch.await(50, TimeUnit.MILLISECONDS);

        MessageResponseDto messageResponseDto = mqttConverter.toResponseDto(subscribeModelList);

        Message message = mqttConverter.toMessage(messageResponseDto);
        messageRepository.save(message);

        return messageResponseDto;

    }

}
