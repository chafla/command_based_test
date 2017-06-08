package org.usfirst.frc.team852.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team852.robot.RobotMap;

import org.usfirst.frc.team852.robot.commands.DriveWithSticks;

/**
 * Created by Matthew on 6/8/2017.
 */
public class Drivetrain extends Subsystem {

    RobotDrive robotDrive = RobotMap.robotDrive;
    private CANTalon frontLeft = RobotMap.frontLeft;
    private CANTalon frontRight = RobotMap.frontRight;
    private CANTalon rearLeft = RobotMap.rearLeft;
    private CANTalon rearRight = RobotMap.rearRight;


    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new DriveWithSticks());

    }

    public void takeJoystickInputsTank(Joystick left, Joystick right) {

        this.robotDrive.tankDrive(left.getY(), right.getY());

    }

    public void stop() {
        this.robotDrive.drive(0,0);  // something neat
    }
}
