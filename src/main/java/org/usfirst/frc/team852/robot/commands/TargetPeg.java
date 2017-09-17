package org.usfirst.frc.team852.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team852.robot.Robot;
import org.usfirst.frc.team852.robot.sensors.Camera;
import org.usfirst.frc.team852.robot.RobotMap;
import org.usfirst.frc.team852.robot.sensors.Lidar;
import org.usfirst.frc.team852.robot.utilities.PIDControl;

/**
 * Created by Matt on 9/17/2017.
 *
 * Target peg using stuff that happened when A was pressed
 */
public class TargetPeg extends Command {

    private double lidarAdjustmentRot;
    private double lidarAdjustmentDist;

    private double time;
    private boolean timedOut;
    private boolean initialized;

    private double lidarThreshold;

    private Camera cameraGear;
    private Lidar lidarLeft;
    private Lidar lidarRight;

    private PIDControl lidarGearAlignPID;
    private PIDControl lidarGearDistPID;
    private PIDControl cameraPID;

    public TargetPeg() {
        requires(Robot.drivetrain);
        requires(Robot.mqtt);

        this.timedOut = false;
        this.lidarThreshold = 0.05;
        this.time = System.currentTimeMillis();

        this.cameraGear = RobotMap.cameraGear;
        this.lidarLeft = RobotMap.leftLidar;
        this.lidarRight = RobotMap.rightLidar;

        this.lidarGearAlignPID = new PIDControl(0.00075,0.00001,0.00000);
        this.lidarGearAlignPID.setOutputConstraints(-1, 1);
        this.lidarGearAlignPID.setReversed();
        this.lidarGearDistPID = new PIDControl(0.0009,0.000006,0.00000);
        this.lidarGearDistPID.setReversed();  // Needs to be reversed due to the way that the wheels and controllers are set up
        this.lidarGearDistPID.setOutputConstraints(-1, 1);
        this.cameraPID = new PIDControl(0,0,0);
        this.initialized = false;
    }

    @Override
    protected void initialize() {
        this.lidarGearAlignPID.reset();
        this.lidarGearDistPID.reset();
        this.cameraPID.reset();
        this.time = System.currentTimeMillis();
        this.initialized = true;
    }

    @Override
    protected void execute() {

        double leftReading = lidarLeft.getReading();
        double rightReading = lidarRight.getReading();

        if (leftReading == -1 || rightReading == -1) {
            //robot.logMsg(LIDAR_GEAR, "Out of range");
            // TODO Add Logger to robot
            return;
        }

        lidarAdjustmentRot = lidarGearAlignPID.getPID(rightReading - leftReading);
        lidarAdjustmentDist = lidarGearDistPID.getPID(600, (leftReading + rightReading) / 2);

        String distMsg;
        String turnMsg;

        if (lidarAdjustmentRot > 0)
            turnMsg = "Clockwise";
        else if (lidarAdjustmentRot < 0)
            turnMsg = "Counterclockwise";
        else
            turnMsg = "Centered";

        if (lidarAdjustmentDist > 0)
            distMsg = "Forwards";
        else if (lidarAdjustmentDist < 0)
            distMsg = "Reverse";
        else
            distMsg = "Centered";

        System.out.println(String.format("%s and %s, dist error: %s, rot error: %s, cur dist: %s", turnMsg, distMsg, lidarAdjustmentDist, lidarAdjustmentRot, (leftReading + rightReading) / 2));

        Robot.drivetrain.drive(0, -lidarAdjustmentDist, -lidarAdjustmentRot);
    }

    @Override
    protected boolean isFinished() {
        return ((lidarAdjustmentRot <= this.lidarThreshold && lidarAdjustmentDist <= this.lidarThreshold) || this.timedOut) && initialized;
    }




}
