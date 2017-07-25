package org.usfirst.frc.team852.robot.data;

// TODO: What was val?

public class CameraData extends GenericData {
    private final double val;
    private final double width;

    public CameraData(final DataType dataType, final int val, final int width) {
        super(dataType);
        this.val = val;
        this.width = width;
    }

    public double getOnce() {
        this.setInvalid();
        return this.val;
    }

    public double get() {
        return this.val;
    }

    public double getWidth() {
        return this.width;
    }
}
