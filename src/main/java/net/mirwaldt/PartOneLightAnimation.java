package net.mirwaldt;

import static net.mirwaldt.LightState.OFF;
import static net.mirwaldt.LightState.ON;

public class PartOneLightAnimation extends AbstractLightAnimation {

    public PartOneLightAnimation(LightBoardFactory lightBoardFactory, int site, String initialBoardAsString) {
        super(lightBoardFactory, site, initialBoardAsString);
    }

    @Override
    public LightBoard animate(int steps) {
        for (int step = 0; step < steps; step++) {
            final LightBoard nextBoard = getNextBoard();
            final LightBoard currentBoard = getCurrentBoard();
            for (int row = 0; row < currentBoard.getSite(); row++) {
                for (int col = 0; col < currentBoard.getSite(); col++) {
                    final LightState oldLightState = currentBoard.getLight(row, col);
                    if(oldLightState.equals(ON)) {
                        int neighboursOn = countNeighboursOn(row, col);
                        if (2 <= neighboursOn && neighboursOn <= 3) {
                            nextBoard.setLight(row, col, ON);
                        } else {
                            nextBoard.setLight(row, col, OFF);
                        }
                    } else {
                        int neighboursOn = countNeighboursOn(row, col);
                        if (neighboursOn == 3) {
                            nextBoard.setLight(row, col, ON);
                        } else {
                            nextBoard.setLight(row, col, OFF);
                        }
                    }
                }
            }
            switchBoard();
        }
        return getCurrentBoard();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
