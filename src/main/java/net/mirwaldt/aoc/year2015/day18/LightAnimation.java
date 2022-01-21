package net.mirwaldt.aoc.year2015.day18;

public interface LightAnimation {
    LightBoard animate(int steps);
    default long animateAndCount(int steps) {
        return animate(steps).countLightsOn();
    }
}
