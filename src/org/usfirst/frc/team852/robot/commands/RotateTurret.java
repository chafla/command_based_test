package org.usfirst.frc.team852.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team852.robot.Robot;
import org.usfirst.frc.team852.robot.subsystems.Turret;

/**
 * Created by Matthew on 6/22/2017.
 */
public class RotateTurret extends Command {

    private boolean atTarget;

    public RotateTurret() {
        this.requires(Robot.turret);
    }

    public void execute() {


    }

    public boolean isFinished() {

        return this.atTarget;

    }
}
