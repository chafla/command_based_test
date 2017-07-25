package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.usfirst.frc.team852.robot.data.GenericData;
import org.usfirst.frc.team852.robot.data.HeadingData;

public class Heading extends Sensor {

    public Heading(GenericData data, String mqttTopic, String sensorName) {
        super(data, mqttTopic, sensorName);
    }

    public void subscribe(MqttClient client) {
        try {
            client.subscribe(this.topic,
                    (topic, msg) -> {
                        final double msgData = Double.parseDouble(new String(msg.getPayload()));
                        dataRef.set(new HeadingData(msgData));
                        synchronized (dataRef) {
                            dataRef.notifyAll();
                        }
                    });
        } catch (MqttException e) {
            System.out.println(String.format("Error in subscribe() [%s - %s]",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()));
            e.printStackTrace();
        }

    }
}
