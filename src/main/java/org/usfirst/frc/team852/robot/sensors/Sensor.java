package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.usfirst.frc.team852.robot.data.DataType;
import org.usfirst.frc.team852.robot.data.GenericData;
import org.usfirst.frc.team852.robot.data.LidarData;
import sun.net.www.content.text.Generic;

import java.util.concurrent.atomic.AtomicReference;

public abstract class Sensor extends MqttSub {

    protected GenericData data;
    protected final AtomicReference<GenericData> dataRef = new AtomicReference<>();  // TODO might want to pull datarefs from robotmap
    protected String sensorName;

    public Sensor(GenericData data, String mqttTopic, String sensorName) {
        super(mqttTopic);
        this.dataRef.set(data);
        this.sensorName = sensorName;
    }

    public abstract void subscribe(MqttClient client);

}
