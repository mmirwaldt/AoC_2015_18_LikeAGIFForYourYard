package net.mirwaldt;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static net.mirwaldt.LightState.boardToString;
import static net.mirwaldt.LightState.readBoardFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultLightAnimationTest {

    public static final int SITE = 6;

    @Test
    void test_animate_oneStep() throws IOException {
        test_animate_nSteps(1);
    }

    @Test
    void test_animate_twoSteps() throws IOException {
        test_animate_nSteps(2);
    }

    @Test
    void test_animate_threeSteps() throws IOException {
        test_animate_nSteps(3);
    }

    @Test
    void test_animate_fourSteps() throws IOException {
        test_animate_nSteps(4);
    }

    private String loadBoardAsStringFromFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName), StandardCharsets.US_ASCII);
    }

    void test_animate_nSteps(int steps) throws IOException {
        final DefaultLightAnimation defaultLightAnimation = new DefaultLightAnimation();
        final String initialBoardAsString = loadBoardAsStringFromFile("0.txt");
        defaultLightAnimation.init(readBoardFromString(initialBoardAsString, SITE), SITE);
        final String boardAfterOneStepAsString = loadBoardAsStringFromFile(steps + ".txt");
        final LightState[][] boardAfterOneStep = readBoardFromString(boardAfterOneStepAsString, SITE);
        assertEquals(boardToString(boardAfterOneStep, SITE), boardToString(defaultLightAnimation.animate(steps), SITE));
    }
}
