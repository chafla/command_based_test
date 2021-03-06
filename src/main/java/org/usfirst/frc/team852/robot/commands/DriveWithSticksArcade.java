package org.usfirst.frc.team852.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team852.robot.Robot;

/**
 * Created by Matthew on 6/8/2017.
 */
public class DriveWithSticksArcade extends Command {

    public DriveWithSticksArcade() {

        // List out all the subsystems required for this command

        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.drivetrain.takeJoystickInputsArcade(Robot.oi.stick1, Robot.oi.stick2);
    }

    @Override
    protected boolean isFinished() {
        // Never stop the drivetrain
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
