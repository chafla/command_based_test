package org.usfirst.frc.team852.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team852.robot.Robot;
import org.usfirst.frc.team852.robot.utilities.PIDControl;

/**
 * Created by Matt on 7/8/2017.
 * Template: end goal should be setting up in front of the goal based on the trajectory we'd need to achieve in order to
 * land the ball in the goal. May vary depending on whether or not we need to account for shooting pos
 */
public class DriveToShootingLocation extends Command {

    public boolean atTarget;

    public DriveToShootingLocation() {

        requires(Robot.drivetrain);
        // TODO Requires lidar
        this.atTarget = false;
    }

    @Override
    protected boolean isFinished() {
        return this.atTarget;
    }


}
