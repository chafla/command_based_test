package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.usfirst.frc.team852.robot.data.CameraData;
import org.usfirst.frc.team852.robot.data.DataType;
import org.usfirst.frc.team852.robot.data.GenericData;


public class Camera extends Sensor {

    public Camera(GenericData data, String mqttTopic, String sensorName) {
        super(data, mqttTopic, sensorName);
    }

    @Override
    public void subscribe(MqttClient client) {
        try {
            client.subscribe(this.topic,
                    (topic, msg) -> {
                        final String[] info = new String(msg.getPayload()).split(":");
                        final int currloc = Integer.parseInt(info[0]);
                        final int width = Integer.parseInt(info[1]);
                        this.dataRef.set(new CameraData(DataType.Camera, currloc, width));
                        synchronized (this.dataRef) {
                            this.dataRef.notifyAll();
                        }
                    });
        }
        catch (MqttException e) {
            System.out.println(String.format("Error in subscribe() [%s - %s]",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()));
            e.printStackTrace();
        }
    }
}
