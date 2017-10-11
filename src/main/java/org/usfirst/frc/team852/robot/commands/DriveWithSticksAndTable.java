package org.usfirst.frc.team852.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team852.robot.Robot;

public class DriveWithSticksAndTable extends Command {

    public DriveWithSticksAndTable() {
        // List all the subsystems required for this command
        requires(Robot.drivetrain);
        requires(Robot.mqtt);
    }


    @Override
    protected void initialize() {

    }


    @Override
    protected void execute() {
        Robot.drivetrain.takeJoystickInputsFieldOriented(Robot.oi.xbox, Robot.oi.stick2);
    }


    @Override
    protected boolean isFinished() {
        // Don't stop the drivetrain
        return false;
    }


    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
