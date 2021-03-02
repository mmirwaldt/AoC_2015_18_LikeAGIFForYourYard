package net.mirwaldt;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.mirwaldt.LightState.ON;

public abstract class AbstractLightAnimation implements LightAnimation {
    protected final LightBoard leftBoard;
    protected final LightBoard rightBoard;
    protected LightBoard currentBoard;

    public AbstractLightAnimation(LightBoardFactory lightBoardFactory, int site, String initialBoardAsString) {
        this.leftBoard = lightBoardFactory.create(site, initialBoardAsString);
        this.rightBoard = lightBoardFactory.create(site);
        this.currentBoard = leftBoard;
    }

    public LightBoard getCurrentBoard() {
        return currentBoard;
    }

    protected LightBoard getNextBoard() {
        if(currentBoard == leftBoard) {
            return rightBoard;
        } else {
            return leftBoard;
        }
    }

    protected void switchBoard() {
        currentBoard = getNextBoard();
    }

    protected int countNeighboursOn(int rowIndex, int colIndex) {
        int count = 0;
        for (int row = max(0, rowIndex - 1); row < min(currentBoard.getSite(), rowIndex + 2); row++) {
            for (int col = max(0, colIndex - 1); col < min(currentBoard.getSite(), colIndex + 2); col++) {
                if(!isCenter(rowIndex, colIndex, row, col)
                        && currentBoard.getLight(row, col).equals(ON)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isCenter(int rowIndex, int colIndex, int row, int col) {
        return row == rowIndex && col == colIndex;
    }

    @Override
    public String toString() {
        return currentBoard.toString();
    }
}
