package net.mirwaldt;

import java.util.Arrays;

import static net.mirwaldt.LightState.initBoard;

public class DefaultLightAnimation implements LightAnimation<LightState[][]> {
    private int site;

    @Override
    public void init(LightState[][] initialBoard, int site) {
        this.site = site;
    }

    @Override
    public LightState[][] animate(int steps) {
        return initBoard(6);
    }

    @Override
    public long count(LightState[][] board) {
        return Arrays.stream(board).flatMap(Arrays::stream).filter(state->state.equals(LightState.ON)).count();
    }
}
