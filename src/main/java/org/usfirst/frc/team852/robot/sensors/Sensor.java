package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.usfirst.frc.team852.robot.data.GenericData;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Generic sensor class.
 * All subclasses of this should be put into RobotMap
 */

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

    public GenericData getDataRef() {
        return this.dataRef.get();
    }

    /**
     * Get a data reading, marking the previous value as stale and invalid
     * on future reads.
     */
    public double getReadingOnce() {
        return this.getDataRef().getOnce();
    }

    /**
     * Get a single reading without marking the data as invalid.
     */
    public double getReading() {
        return this.getDataRef().get();
    }

}
