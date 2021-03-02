package net.mirwaldt;

public interface LightBoardFactory {
    LightBoard create(int site);
    LightBoard create(int site, String initialBoardAsString);
}
