package net.mirwaldt;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.mirwaldt.LightState.*;

public class DefaultLightAnimation implements LightAnimation<LightState[][]> {
    private int site;
    private LightState[][] board;

    @Override
    public void init(LightState[][] initialBoard, int site) {
        this.site = site;
        this.board = initialBoard;
    }

    @Override
    public LightState[][] animate(int steps) {
        for (int step = 0; step < steps; step++) {
            final LightState[][] nextBoard = initBoard(site);
            for (int row = 0; row < site; row++) {
                for (int col = 0; col < site; col++) {
                    LightState oldLightState = board[row][col];
                    if(oldLightState.equals(ON)) {
                        int neighboursOn = countNeighboursOn(row, col);
                        if (2 <= neighboursOn && neighboursOn <= 3) {
                            nextBoard[row][col] = ON;
                        } else {
                            nextBoard[row][col] = OFF;
                        }
                    } else {
                        int neighboursOn = countNeighboursOn(row, col);
                        if (neighboursOn == 3) {
                            nextBoard[row][col] = ON;
                        } else {
                            nextBoard[row][col] = OFF;
                        }
                    }
                }
            }
            board = nextBoard;
        }
        return board;
    }

    int countNeighboursOff(int row, int col) {
        return countNeighbours(row, col, OFF);
    }

    int countNeighboursOn(int row, int col) {
        return countNeighbours(row, col, ON);
    }

    int countNeighbours(int rowIndex, int colIndex, LightState lightState) {
        int count = 0;
        for (int row = max(0, rowIndex - 1); row < min(site, rowIndex + 2); row++) {
            for (int col = max(0, colIndex - 1); col < min(site, colIndex + 2); col++) {
                if(!isCenter(rowIndex, colIndex, row, col)
                        && board[row][col].equals(lightState)) {
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
    public long count(LightState[][] board) {
        return Arrays.stream(board).flatMap(Arrays::stream).filter(state -> state.equals(LightState.ON)).count();
    }
}
