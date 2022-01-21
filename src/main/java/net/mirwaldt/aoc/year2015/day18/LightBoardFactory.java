package net.mirwaldt.aoc.year2015.day18;

public interface LightBoardFactory {
    LightBoard create(int site);
    LightBoard create(int site, String initialBoardAsString);
}
