package org.usfirst.frc.team852.robot.sensors;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by Matt on 7/11/2017.
 * A sensor or similar type of physical device that interacts with MQTT
 */
public abstract class MqttSub {

    protected String topic;

    public MqttSub(String topic) {
        this.topic = topic;
    }

    // Subacribe and add a listener
    public abstract void subscribe(final MqttClient client);

    public void subscribe(final MqttClient client, IMqttMessageListener listener) {
        try {
            client.subscribe(this.topic, listener);
        }

        catch (MqttException e) {
            System.out.println(String.format("Error in subscribe() [%s - %s]",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()));
            e.printStackTrace();
        }
    }



}
