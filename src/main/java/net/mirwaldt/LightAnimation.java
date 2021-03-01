package net.mirwaldt;

public interface LightAnimation<BoardType> {
    void init(BoardType initialBoard, int site);
    BoardType animate(int steps);
    long count(BoardType board);
    default long animateAndCount(int steps) {
        return count(animate(steps));
    }
}
