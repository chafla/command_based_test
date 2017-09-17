package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.usfirst.frc.team852.robot.data.DataType;
import org.usfirst.frc.team852.robot.data.GenericData;
import org.usfirst.frc.team852.robot.data.LidarData;

public class Lidar extends Sensor {

    public Lidar(GenericData data, String mqttTopic, String sensorName) {
        super(data, mqttTopic, sensorName);
    }

    @Override
    public void subscribe(MqttClient client) {
        // Feel free to override this if more stuff is needed
        try {
            client.subscribe(this.topic,
                    (topic, msg) -> {
                        final int msgData = Integer.parseInt(new String(msg.getPayload()));
                        dataRef.set(new LidarData(msgData));
                        synchronized (dataRef) {
                            dataRef.notifyAll();
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
