package org.usfirst.frc.team852.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team852.robot.subsystems.Turret;
import org.usfirst.frc.team852.robot.Robot;

/**
 * Spin up a turret to reach a certain speed/rpm. Should resemble what we did for Stronghold.
 * Should not only approach target speed, but should remain constant all the while, meaning that it should not end
 * until after a ball has been shot.
 * Should be run in parallel with other commands that rely on it, and should only spin down based on a subsystem flag
 * that says that the ball is clear.
 */
public class SpinUpTurret extends Command {

    public boolean atTargetRPM = false;
    private double targetRPM = 0;

    public SpinUpTurret() {
        this.requires(Robot.turret);
    }

    @Override
    protected void interrupted() {
        this.end();
    }

    @Override
    protected boolean isFinished() {
        // return when the last ball has been shot, or perhaps when a trigger button is released in manual operation.
        return false;
    }

    public void execute() {

        Robot.turret.spinUp(this.targetRPM);
    }
}
