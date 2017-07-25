package org.usfirst.frc.team852.robot.data;

public class HeadingData extends GenericData {
    private final double degrees;

    public HeadingData(double degrees) {
        super(DataType.Heading);
        this.degrees = degrees;
    }

    public double getOnce() {
        this.setInvalid();
        return this.degrees;
    }

    public double get() {
        return this.degrees;
    }
}
