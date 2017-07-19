package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.usfirst.frc.team852.robot.data.DataType;
import org.usfirst.frc.team852.robot.data.GenericData;
import org.usfirst.frc.team852.robot.data.LidarData;
import sun.net.www.content.text.Generic;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

/**
 * Created by Matt on 7/11/2017.
 */
public abstract class Sensor extends MqttSub {

    protected GenericData data;
    protected final AtomicReference<GenericData> dataRef = new AtomicReference<>();  // TODO might want to pull datarefs from robotmap
    protected String sensorName;

    public Sensor(GenericData data, String mqttTopic, String sensorName) {

        super(mqttTopic);
        dataRef.set(data);  // Why can't I call this.dataref?
        this.sensorName = sensorName;
    }

    public abstract void subscribe(MqttClient client);

}
