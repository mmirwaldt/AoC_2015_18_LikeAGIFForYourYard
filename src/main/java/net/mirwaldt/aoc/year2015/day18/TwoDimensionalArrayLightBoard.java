package net.mirwaldt.aoc.year2015.day18;

public class TwoDimensionalArrayLightBoard extends AbstractLightBoard {
    private final LightState[][] board;

    public TwoDimensionalArrayLightBoard(int site) {
        super(site);
        board = new LightState[site][site];
        init();
    }

    public TwoDimensionalArrayLightBoard(int site, String initialBoardAsString) {
        super(site);
        board = new LightState[site][site];
        init(initialBoardAsString);
    }

    @Override
    public int getSite() {
        return site;
    }

    @Override
    public LightState getLight(int row, int col) {
        return board[row][col];
    }

    @Override
    public void setLight(int row, int col, LightState lightState) {
        board[row][col] = lightState;
    }


}
