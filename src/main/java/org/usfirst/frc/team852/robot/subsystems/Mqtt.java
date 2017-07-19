package org.usfirst.frc.team852.robot.subsystems;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import org.athenian.BaseMqttCallback;
import org.athenian.Utils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team852.robot.RobotMap;
import org.usfirst.frc.team852.robot.data.CameraData;
import org.usfirst.frc.team852.robot.data.DataType;
import org.usfirst.frc.team852.robot.data.HeadingData;
import org.usfirst.frc.team852.robot.data.LidarData;
import org.usfirst.frc.team852.robot.sensors.MqttSub;

import static java.lang.String.format;

/**
 * Created by Matthew on 6/22/2017.
 * The base subsystem behind the whole mqtt system. Other classes should interface with this.
 */
public class Mqtt extends Subsystem {

    private boolean listenerEnabled = true;

    private final ExecutorService logExecutor = Executors.newFixedThreadPool(4);
    private final ExecutorService mqttExecutor = Executors.newFixedThreadPool(1);
    // Do we need executors while working with this stuff?
    private final AtomicReference<MqttClient> mqttClientRef = new AtomicReference<>(null);

    @Override
    protected void initDefaultCommand() {}  // We don't really need one

    // TODO: Might want to implement this
    @Override
    protected void setDefaultCommand(Command command) {
        super.setDefaultCommand(command);
    }

    public MqttClient getMqttClient() {
        return this.mqttClientRef.get();
    }

    // TODO Make this run in a command in Robot on Init
    // Note: does this ever break?
    private void initializeConnection() {
        this.mqttExecutor.submit((Runnable) () -> {
            final String url = format("tcp://%s:%d", RobotMap.MQTT_HOSTNAME, RobotMap.MQTT_PORT);

            final MqttConnectOptions opts = new MqttConnectOptions();
            opts.setCleanSession(true);
            opts.setAutomaticReconnect(true);
            opts.setConnectionTimeout(30);

            while (true) {
                try {
                    this.mqttClientRef.set(new MqttClient(url, MqttClient.generateClientId(), new MemoryPersistence()));
                } catch (MqttException e) {
                    System.out.println(format("Cannot create MQTT client [%s - %s]",
                            e.getClass().getSimpleName(),
                            e.getMessage()));
                    e.printStackTrace();
                    Utils.sleepSecs(1);
                    continue;
                }

                this.getMqttClient().setCallback(
                        new BaseMqttCallback() {
                            @Override
                            public void connectComplete(boolean reconnect, String url) {
                                super.connectComplete(reconnect, url);
                                subscribeToTopics(RobotMap.mqttSubs);  // TODO is there any way to reference a command from here
                            }
                        });

                try {
                    System.out.println(format("Connecting to MQTT broker at %s...", url));
                    this.getMqttClient().connect(opts);
                    System.out.println(format("Connected to MQTT broker at %s", url));
                    break;
                } catch (MqttException e) {
                    System.out.println(format("Cannot connect to MQTT broker at %s [%s - %s]",
                            url,
                            e.getClass().getSimpleName(),
                            e.getMessage()));
                    e.printStackTrace();
                    this.mqttClientRef.set(null);
                    Utils.sleepSecs(1);
                }
            }
        });

    }

    /*private void subscribeToTopics() {

    }*/

    // Below is old code copied directly from the old robot code
/*

    private void subscribeToTopics(List<MqttSub> subscribers) {
        System.out.println("Subscribing to topics");
        try {
            this.getMqttClient().subscribe(RobotMap.CAMERA_TOPIC,
                    (topic, msg) -> {
                        final String[] info = new String(msg.getPayload()).split(":");
                        final int currloc = Integer.parseInt(info[0]);
                        final int width = Integer.parseInt(info[1]);
                        this.strategy.getCameraGearRef().set(new CameraData(DataType.CameraGear, currloc, width));
                        synchronized (this.strategy.getCameraGearRef()) {
                            this.strategy.getCameraGearRef().notifyAll();
                        }
                    });

            this.getMqttClient().subscribe(RobotMap.FRONT_LIDAR_TOPIC,
                    (topic, msg) -> {
                        final int dist = Integer.parseInt(new String(msg.getPayload()));
                        this.strategy.getFrontLidarRef().set(new LidarData(DataType.FrontLidar, dist));
                        synchronized (this.strategy.getFrontLidarRef()) {
                            this.strategy.getFrontLidarRef().notifyAll();
                        }
                    });

            this.getMqttClient().subscribe(RobotMap.REAR_LIDAR_TOPIC,
                    (topic, msg) -> {
                        final int dist = Integer.parseInt(new String(msg.getPayload()));
                        this.strategy.getRearLidarRef().set(new LidarData(DataType.RearLidar, dist));
                        synchronized (this.strategy.getRearLidarRef()) {
                            this.strategy.getRearLidarRef().notifyAll();
                        }
                    });

            client.subscribe(RobotMap.LEFT_LIDAR_TOPIC,
                    (topic, msg) -> {
                        final int dist = Integer.parseInt(new String(msg.getPayload()));
                        this.strategy.getLeftLidarRef().set(new LidarData(DataType.LeftLidar, dist));
                        synchronized (this.strategy.getLeftLidarRef()) {
                            this.strategy.getLeftLidarRef().notifyAll();
                        }
                    });

            client.subscribe(RobotMap.RIGHT_LIDAR_TOPIC,
                    (topic, msg) -> {
                        final int dist = Integer.parseInt(new String(msg.getPayload()));
                        this.strategy.getRightLidarRef().set(new LidarData(DataType.RightLidar, dist));
                        synchronized (this.strategy.getRightLidarRef()) {
                            this.strategy.getRightLidarRef().notifyAll();
                        }
                    });

            client.subscribe(RobotMap.HEADING_TOPIC,
                    (topic, msg) -> {
                        final double degree = Double.parseDouble(new String(msg.getPayload()));
                        this.strategy.getHeadingRef().set(new HeadingData(degree));
                        synchronized (this.strategy.getHeadingRef()) {
                            this.strategy.getHeadingRef().notifyAll();
                        }
                    });
        }
        catch (MqttException e) {
            System.out.println(format("Error in subscribeToTopics() [%s - %s]",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()));
            e.printStackTrace();
        }
    }
*/

    public void subscribeToTopics(MqttSub[] subscribers) {
        for (MqttSub sub: subscribers) {
            sub.subscribe(this.getMqttClient());
        }
    }

    public void publishMessage(String topic, String message) {
        if (this.getMqttClient() != null) {
            try {
                this.getMqttClient().publish(topic, new MqttMessage((message.getBytes())));
            } catch (MqttException e) {
                System.out.println(format("An error occurred when publishing to MQTT [%s - %s]",
                        e.getClass().getSimpleName(),
                        e.getLocalizedMessage()));
                e.printStackTrace();
            }
        }
    }
}
