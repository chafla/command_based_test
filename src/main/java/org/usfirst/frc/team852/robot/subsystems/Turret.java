package org.usfirst.frc.team852.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team852.robot.RobotMap;
import org.usfirst.frc.team852.robot.utilities.PIDControl;

/**
 * Base turret controls. Should handle the rotation of the turret, the launch motor, and the little flap motor.
 * Should also handle related sensor inputs.
 */
public class Turret extends Subsystem {

    private CANTalon launcher = RobotMap.turretLauncher;
    private CANTalon rotator = RobotMap.turretRotation;
    private CANTalon controlFlap = RobotMap.turretFlap;
    private Encoder launcherRPM = RobotMap.turretRPM;
    private boolean hasShot = false;  // Flag that changes when the turret has shot.

    @Override
    public void initDefaultCommand() {

    }

    // Spin up to a target RPM
    // TODO: Determine what the RPM and flap should be at based on the distance and arc needed to land in target
    public void spinUp(double targetRPM) {

        PIDControl RPMPid = new PIDControl(0,0,0);

        while (!this.hasShot) {

            // Set launcher to a speed based on the PID difference between launcherRPM and targetRPM.

        }

    }

    public void rotate(double degrees) {

        // Rotate the turret from its current heading by a matter of degrees

    }

    public void rotateFromNeutral(double targetBearing) {

        // Rotate the turret to a fixed bearing based on its initial state

    }

    public void setFlap(double distance) {

        // Set the flap to a set point along its track.
        // We will need to account for limits

    }

    public void resetFlap() {

        // Set the flap to a known point

    }

    // Reset the turret after a shot
    // Should reset any flags/logic as well as resetting hardware components
    public void reset() {
        this.hasShot = false;
        // TODO: Cancel shooting
    }

}
