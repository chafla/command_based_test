package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.usfirst.frc.team852.robot.data.DataType;
import org.usfirst.frc.team852.robot.data.GenericData;
import org.usfirst.frc.team852.robot.data.LidarData;

import static java.lang.String.format;

/**
 * Created by Matt on 7/19/2017.
 */
public class Lidar extends Sensor {

    public Lidar(GenericData data, String mqttTopic, String sensorName) {
        super(data, mqttTopic, sensorName);
    }

    @Override
    public void subscribe(MqttClient client) {
        // Feel free to override this if more stuff is needed
        try {
            client.subscribe(super.topic,
                    (topic, msg) -> {
                        final int msgData = Integer.parseInt(new String(msg.getPayload()));
                        super.dataRef.set(new LidarData(DataType.Lidar, msgData));
                        synchronized (dataRef) {
                            dataRef.notifyAll();
                        }
                    });
        }
        catch (MqttException e) {
            System.out.println(format("Error in subscribe() [%s - %s]",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()));
            e.printStackTrace();
        }
    }
}
