package com.veron.bloodvisual.client.settings;

/**
 * Числовая настройка со слайдером
 */
public class NumberSetting extends Setting<Double> {
    private final double min;
    private final double max;
    private final double increment;

    public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getIncrement() {
        return increment;
    }

    public void setValue(double value) {
        // Округляем до increment
        double v = Math.round(value / increment) * increment;
        super.setValue(Math.max(min, Math.min(max, v)));
    }

    public float getPercentage() {
        return (float) ((getValue() - min) / (max - min));
    }
}
