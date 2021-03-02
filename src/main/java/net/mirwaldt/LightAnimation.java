package net.mirwaldt;

public interface LightAnimation {
    LightBoard animate(int steps);
    default long animateAndCount(int steps) {
        return animate(steps).countLightsOn();
    }
}
