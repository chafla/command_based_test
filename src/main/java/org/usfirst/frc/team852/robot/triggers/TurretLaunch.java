package org.usfirst.frc.team852.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team852.robot.subsystems.Turret;

/**
 * Created by Matt on 7/8/2017.
 * Trigger to be called after a launch and reset the mechanism.
 */
public class TurretLaunch extends Trigger {

    private boolean isSet = false;


    @Override
    public boolean get() {
        return false;
    }

    public void set() {
        this.isSet = true;
    }

    public void reset() {
        this.isSet = false;
    }

}
