package net.mirwaldt.aoc.year2015.day18;

public enum LightState {
    ON("#"), OFF(".");

    private final String sign;

    LightState(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
