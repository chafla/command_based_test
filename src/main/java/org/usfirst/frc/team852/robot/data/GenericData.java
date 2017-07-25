package org.usfirst.frc.team852.robot.data;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class GenericData {

    private AtomicBoolean invalid = new AtomicBoolean(false);
    private DataType dataType;

    protected GenericData(final DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isInvalid() {
        return this.invalid.get();
    }

    protected void setInvalid() {
        //System.out.println(this.dataType.getUpdateMsg());
        this.invalid.set(true);
    }

    public String getAlreadyReadMsg() {
        return this.dataType.getAlreadyReadMsg();
    }

    // Discard stale data
    public abstract double getOnce();

    // Return data, even if it is stale
    public abstract double get();
}
