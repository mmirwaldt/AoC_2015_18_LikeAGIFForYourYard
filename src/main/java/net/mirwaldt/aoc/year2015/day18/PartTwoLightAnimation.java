package net.mirwaldt.aoc.year2015.day18;

public class PartTwoLightAnimation implements LightAnimation {
    private final PartOneLightAnimation partOneLightAnimation;

    public PartTwoLightAnimation(LightBoardFactory lightBoardFactory, int site, String initialBoardAsString) {
        this.partOneLightAnimation = new PartOneLightAnimation(lightBoardFactory, site, initialBoardAsString);
    }

    private void switchOnCorners() {
        final LightBoard lightBoard = partOneLightAnimation.getCurrentBoard();
        lightBoard.setLight(0, 0, LightState.ON);
        lightBoard.setLight(lightBoard.getSite() - 1, 0, LightState.ON);
        lightBoard.setLight(0, lightBoard.getSite() - 1, LightState.ON);
        lightBoard.setLight(lightBoard.getSite() - 1, lightBoard.getSite() - 1, LightState.ON);
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
