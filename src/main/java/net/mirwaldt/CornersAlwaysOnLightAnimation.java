package net.mirwaldt;

import static net.mirwaldt.LightState.ON;

public class CornersAlwaysOnLightAnimation implements LightAnimation<LightState[][]> {
    private final DefaultLightAnimation defaultLightAnimation = new DefaultLightAnimation();
    private int site;

    @Override
    public void init(LightState[][] initialBoard, int site) {
        switchOnCorners(initialBoard, site);
        defaultLightAnimation.init(initialBoard, site);
        this.site = site;
    }

    private void switchOnCorners(LightState[][] initialBoard, int site) {
        initialBoard[0][0] = ON;
        initialBoard[site -1][0] = ON;
        initialBoard[0][site -1] = ON;
        initialBoard[site -1][site -1] = ON;
    }

    @Override
    public LightState[][] animate(int steps) {
        LightState[][] board = null;
        for (int i = 0; i < steps; i++) {
            board = defaultLightAnimation.animate(1);
            init(board, site);
        }
        return board;
    }

    @Override
    public long count(LightState[][] board) {
        return defaultLightAnimation.count(board);
    }
}
