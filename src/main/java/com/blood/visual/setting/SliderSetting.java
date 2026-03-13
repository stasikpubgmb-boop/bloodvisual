package com.blood.visual.setting;

public class SliderSetting extends Setting<Double> {
    private double value;
    private double min;
    private double max;

    public SliderSetting(double min, double max, double value) {
        this.min = min;
        this.max = max;
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        if (value < this.min) {
            this.value = this.min;
        } else if (value > this.max) {
            this.value = this.max;
        } else {
            this.value = value;
        }
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }
}
