package net.mirwaldt.aoc.year2015.day18;

public interface LightBoard {
    int getSite();
    LightState getLight(int row, int col);
    void setLight(int row, int col, LightState lightState);
    long countLightsOn();
}
