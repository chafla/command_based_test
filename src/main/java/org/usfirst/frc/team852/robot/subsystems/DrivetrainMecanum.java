package org.usfirst.frc.team852.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team852.robot.Robot;
import org.usfirst.frc.team852.robot.RobotMap;
import org.usfirst.frc.team852.robot.commands.DriveWithSticksAndTable;
import org.usfirst.frc.team852.robot.commands.DriveWithSticksTank;

/**
 * Created by Matthew on 6/8/2017.
 */
public class DrivetrainMecanum extends Subsystem {

    RobotDrive robotDrive = RobotMap.robotDrive;
    private CANTalon frontLeft = RobotMap.frontLeft;
    private CANTalon frontRight = RobotMap.frontRight;
    private CANTalon rearLeft = RobotMap.rearLeft;
    private CANTalon rearRight = RobotMap.rearRight;
    private ADXRS450_Gyro gyro = RobotMap.gyro;

    private static final double stickDeadZone = 0.05;


    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithSticksAndTable());

    }

    public void takeJoystickInputsArcade(Joystick left, Joystick right) {

        this.robotDrive.mecanumDrive_Cartesian(this.adjustDeadZone(left.getX()),
                -this.adjustDeadZone(left.getY()),
                -this.adjustDeadZone(right.getX()),
                0);
    }

    public void takeJoystickInputsFieldOriented(XboxController xbox, Joystick stick) {
        double rot = xbox.getTriggerAxis(GenericHID.Hand.kRight) - xbox.getTriggerAxis(GenericHID.Hand.kLeft);
        double forward = this.adjustDeadZone(stick.getY()) * -1;
        double strafe = this.adjustDeadZone(stick.getX());
        SmartDashboard.putNumber("Gyro Value", gyro.getAngle());

        /* Adjust joystick X/Y by gyro input*/
        //double gyro_radians = RobotMap.heading.getReading() * Math.PI / 180;
        double gyro_radians = gyro.getAngle() * Math.PI / 180;
        double tmp = forward * Math.cos(gyro_radians) + strafe * Math.sin(gyro_radians);
        strafe = -forward * Math.sin(gyro_radians) + strafe * Math.cos(gyro_radians);


        this.robotDrive.mecanumDrive_Cartesian(strafe, forward, rot, 0);
    }

    public void takeJoystickInputsTank(Joystick left, Joystick right) {

        this.robotDrive.mecanumDrive_Cartesian((this.adjustDeadZone(left.getX()) + this.adjustDeadZone(right.getX())) / 2,
                (this.adjustDeadZone(left.getY()) + this.adjustDeadZone(right.getY())) / 2,
                (this.adjustDeadZone(left.getY()) - this.adjustDeadZone(right.getY())) / 2, 0);
    }

    public double adjustDeadZone(double val) {
        if (val >= -stickDeadZone && val <= stickDeadZone) val = 0.0;
        return val;
    }

    public void drive(double x, double y, double rot) {
        this.robotDrive.mecanumDrive_Cartesian(x, y, rot, 0);
    }

    public void stop() {
        this.robotDrive.drive(0, 0);  // something neat
    }
}
