package org.usfirst.frc.team852.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import org.usfirst.frc.team852.robot.data.CameraData;
import org.usfirst.frc.team852.robot.data.DataType;
import org.usfirst.frc.team852.robot.data.GenericData;
import org.usfirst.frc.team852.robot.sensors.Camera;
import org.usfirst.frc.team852.robot.sensors.Heading;
import org.usfirst.frc.team852.robot.sensors.Lidar;
import org.usfirst.frc.team852.robot.sensors.MqttSub;
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
    public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    public static CANTalon turretRotation = new CANTalon(4);
    public static CANTalon turretLauncher = new CANTalon(5);
    public static CANTalon turretFlap = new CANTalon(6);

    // TODO Insert lidar stuff and get all the raspi and related stuff integrated here
    // TODO Insert encoder stuff

    public static Encoder turretRPM = new Encoder(0, 1);

    public static TurretLaunch onTurretFire = new TurretLaunch();

    /////////////////////////////////////////////////////////////
    //                      Constants
    public static final String CAMERA_GEAR_LOGGING_POSITION_TOPIC = "logging/camera/gear/alignment";
    public static final String LIDAR_GEAR_LOGGING_POSITION_TOPIC = "logging/lidar/gear/distance";
    public static final String LONG_LIDAR_LOGGING_POSITION_TOPIC = "logging/lidar/long/distance";
    public static final String HEADING_LOGGING_POSITION_TOPIC = "logging/heading/degrees";
    public static final String MQTT_TOPIC = "roborio/keyboard/command";
    public static final String CAMERA_TOPIC = "camera/gear/x";
    public static final String CAMERA_COMMAND = "camera/gear/x=command";
    public static final String FRONT_LIDAR_TOPIC = "lidar/front/cm";
    public static final String FRONT_LIDAR_COMMAND = "lidar/front/command";
    public static final String REAR_LIDAR_TOPIC = "lidar/rear/cm";
    public static final String REAR_LIDAR_COMMAND = "lidar/rear/command";
    public static final String LEFT_LIDAR_TOPIC = "lidar/left/mm";
    public static final String LEFT_LIDAR_COMMAND = "lidar/left/command";
    public static final String RIGHT_LIDAR_TOPIC = "lidar/right/mm";
    public static final String RIGHT_LIDAR_COMMAND = "lidar/right/command";
    public static final String MQTT_HOSTNAME = "mqtt-turtle.local"; /*"10.8.52.14";*/
    public static final String HEADING_TOPIC = "heading/degrees";
    public static final String HEADING_COMMAND = "heading/command";
    public static final int MQTT_PORT = 1883;

    // TODO Add the sensor stuff that will be based in mqtt

    public static final Camera cameraGear = new Camera(null, CAMERA_TOPIC, "camera_gear");
    public static final Lidar leftLidar = new Lidar(null, LEFT_LIDAR_TOPIC, "left_lidar");
    public static final Lidar rightLidar = new Lidar(null, RIGHT_LIDAR_TOPIC, "right_lidar");
    public static final Heading heading = new Heading(null, HEADING_TOPIC, "heading");


    public static MqttSub[] mqttSubs = {
            // Insert MQTT subscribers here
            // This includes all sensors
            cameraGear,
            leftLidar,
            rightLidar,
            heading
    };

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
