package net.mirwaldt;

import static net.mirwaldt.LightState.ON;

public class PartTwoLightAnimation implements LightAnimation {
    private final PartOneLightAnimation partOneLightAnimation;

    public PartTwoLightAnimation(LightBoardFactory lightBoardFactory, int site, String initialBoardAsString) {
        this.partOneLightAnimation = new PartOneLightAnimation(lightBoardFactory, site, initialBoardAsString);
    }

    private void switchOnCorners() {
        final LightBoard lightBoard = partOneLightAnimation.getCurrentBoard();
        lightBoard.setLight(0, 0, ON);
        lightBoard.setLight(lightBoard.getSite() - 1, 0, ON);
        lightBoard.setLight(0, lightBoard.getSite() - 1, ON);
        lightBoard.setLight(lightBoard.getSite() - 1, lightBoard.getSite() - 1, ON);
    }

    @Override
    public LightBoard animate(int steps) {
        switchOnCorners();
        for (int i = 0; i < steps; i++) {
            partOneLightAnimation.animate(1);
            switchOnCorners();
        }
        return partOneLightAnimation.getCurrentBoard();
    }
}
