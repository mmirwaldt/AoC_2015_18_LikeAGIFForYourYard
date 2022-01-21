package net.mirwaldt.aoc.year2015.day18;

public class TwoDimensionalArrayLightBoardFactory implements LightBoardFactory {
    @Override
    public LightBoard create(int site) {
        return new TwoDimensionalArrayLightBoard(site);
    }

    @Override
    public LightBoard create(int site, String initialBoardAsString) {
        return new TwoDimensionalArrayLightBoard(site, initialBoardAsString);
    }
}
