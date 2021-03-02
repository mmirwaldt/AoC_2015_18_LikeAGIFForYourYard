package net.mirwaldt;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static net.mirwaldt.LightState.boardToString;
import static net.mirwaldt.LightState.readBoardFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CornersAlwaysOnLightAnimationTest {
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

    @Test
    void test_animate_fiveSteps() throws IOException {
        test_animate_nSteps(5);
    }

    private String loadBoardAsStringFromFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName), StandardCharsets.US_ASCII);
    }

    void test_animate_nSteps(int steps) throws IOException {
        final CornersAlwaysOnLightAnimation defaultLightAnimation = new CornersAlwaysOnLightAnimation();
        final String initialBoardAsString = loadBoardAsStringFromFile("_0.txt");
        defaultLightAnimation.init(readBoardFromString(initialBoardAsString, SITE), SITE);
        final String boardAfterOneStepAsString = loadBoardAsStringFromFile("_" + steps + ".txt");
        final LightState[][] boardAfterOneStep = readBoardFromString(boardAfterOneStepAsString, SITE);
        assertEquals(boardToString(boardAfterOneStep, SITE), boardToString(defaultLightAnimation.animate(steps), SITE));
    }
}
