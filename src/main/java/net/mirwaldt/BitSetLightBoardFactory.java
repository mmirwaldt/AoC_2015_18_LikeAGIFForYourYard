package net.mirwaldt;

public class BitSetLightBoardFactory implements LightBoardFactory {
    @Override
    public LightBoard create(int site) {
        return new BitSetLightBoard(site);
    }

    @Override
    public LightBoard create(int site, String initialBoardAsString) {
        return new BitSetLightBoard(site, initialBoardAsString);
    }
}
