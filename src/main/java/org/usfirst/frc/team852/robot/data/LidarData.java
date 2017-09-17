package org.usfirst.frc.team852.robot.data;

public class LidarData extends GenericData {
    private final double val;

    public LidarData(final int val) {
        super(DataType.Lidar);
        this.val = val;
    }

    public double getOnce() {
        this.setInvalid();
        return this.val;
    }

    public double get() {
        return this.val;
    }
}