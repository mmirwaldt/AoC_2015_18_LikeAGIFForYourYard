package net.mirwaldt;

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
