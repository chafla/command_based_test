package org.usfirst.frc.team852.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team852.robot.Robot;
import org.usfirst.frc.team852.robot.sensors.MqttSub;
import org.usfirst.frc.team852.robot.subsystems.Mqtt;
import org.usfirst.frc.team852.robot.RobotMap;


/**
 * Created by Matt on 7/19/2017.
 */
public class ConnectToMqtt extends Command {

    private boolean finished;

    public ConnectToMqtt() {

        this.requires(Robot.mqtt);
        this.finished = false;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        // Subscribe to all mqtt clients listed in RobotMap
        Robot.mqtt.subscribeToTopics(RobotMap.mqttSubs);
        this.finished = true;

    }

    @Override
    protected boolean isFinished() {
        return this.finished;

    }

    @Override
    protected void interrupted() {
        // TODO Raise some sort of exception
    }
}
