package com.veron.bloodvisual.client.gui.animation;

/**
 * Класс для плавных анимаций
 */
public class Animation {
    private float value;
    private float target;
    private final float speed;

    public Animation(float speed) {
        this.speed = speed;
        this.value = 0;
        this.target = 0;
    }

    public void update(float delta) {
        if (value != target) {
            float diff = target - value;
            float change = diff * speed * delta;
            
            if (Math.abs(diff) < 0.001f) {
                value = target;
            } else {
                value += change;
            }
        }
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isFinished() {
        return value == target;
    }
}
