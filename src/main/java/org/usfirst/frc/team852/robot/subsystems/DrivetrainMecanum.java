package org.usfirst.frc.team852.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team852.robot.RobotMap;
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

    private static final double stickDeadZone = 0.05;


    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new DriveWithSticksTank());

    }

    public void takeJoystickInputsArcade(Joystick left, Joystick right) {

        this.robotDrive.mecanumDrive_Cartesian(this.adjustDeadZone(left.getX()),
                -this.adjustDeadZone(left.getY()),
                -this.adjustDeadZone(right.getX()),
                0);
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
        this.robotDrive.drive(0,0);  // something neat
    }
}
