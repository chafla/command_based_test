package org.usfirst.frc.team852.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team852.robot.triggers.TurretLaunch;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
    public static CANTalon frontLeft = new CANTalon(0);
    public static CANTalon frontRight = new CANTalon(1);
    public static CANTalon rearLeft = new CANTalon(2);
    public static CANTalon rearRight = new CANTalon(3);
    public static RobotDrive robotDrive = new RobotDrive(frontLeft, frontRight, rearLeft, rearRight);

    public static CANTalon turretRotation = new CANTalon(4);
    public static CANTalon turretLauncher = new CANTalon(5);
    public static CANTalon turretFlap = new CANTalon(6);

    // TODO Insert lidar stuff and get all the raspi and related stuff integrated here
    // TODO Insert encoder stuff

    public static Encoder turretRPM = new Encoder(0, 1);

    public static TurretLaunch onTurretFire = new TurretLaunch();

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
