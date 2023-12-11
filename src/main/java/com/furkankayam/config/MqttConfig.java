package com.furkankayam.config;

import com.furkankayam.exception.MqttClientException;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

@Configuration
@Scope("prototype")
public class MqttConfig {

    // RANDOM CLIENT ID
    private static final String MQTT_CLIENT_ID = UUID.randomUUID().toString();

    // DEFAULT CLIENT ID
    /*private static final String MQTT_CLIENT_ID = "client";*/

    private static final String MQTT_SERVER_URL= "tcp://192.168.1.85:1883";

    @Bean
    public IMqttClient getInstance() {
        IMqttClient instance;
        try {
            instance = new MqttClient(MQTT_SERVER_URL, MQTT_CLIENT_ID);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
            }
        } catch (MqttException e) {
            return (IMqttClient) new MqttClientException("MqttException!");
        }

        return instance;
    }

}
