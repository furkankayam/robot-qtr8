package com.furkankayam.service;

import com.furkankayam.dto.response.MessageResponseDto;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface MessageService {

    MessageResponseDto getMessage() throws MqttException, InterruptedException;

}
