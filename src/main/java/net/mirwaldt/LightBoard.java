package net.mirwaldt;

public interface LightBoard {
    int getSite();
    LightState getLight(int row, int col);
    void setLight(int row, int col, LightState lightState);
    long countLightsOn();
}
