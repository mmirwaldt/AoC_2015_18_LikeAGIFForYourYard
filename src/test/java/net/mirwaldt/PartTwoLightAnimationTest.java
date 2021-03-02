package net.mirwaldt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoLightAnimationTest {
    public static final int SITE = 6;

    private static Stream<Arguments> lightBoardFactory() {
        return Stream.of(Arguments.of(new TwoDimensionalArrayLightBoardFactory()),
                Arguments.of(new BitSetLightBoardFactory()));
    }

    @ParameterizedTest
    @MethodSource("lightBoardFactory")
    void test_animate_oneStep(LightBoardFactory lightBoardFactory) throws IOException {
        test_animate_nSteps(1, lightBoardFactory);
    }

    @ParameterizedTest
    @MethodSource("lightBoardFactory")
    void test_animate_twoSteps(LightBoardFactory lightBoardFactory) throws IOException {
        test_animate_nSteps(2, lightBoardFactory);
    }

    @ParameterizedTest
    @MethodSource("lightBoardFactory")
    void test_animate_threeSteps(LightBoardFactory lightBoardFactory) throws IOException {
        test_animate_nSteps(3, lightBoardFactory);
    }

    @ParameterizedTest
    @MethodSource("lightBoardFactory")
    void test_animate_fourSteps(LightBoardFactory lightBoardFactory) throws IOException {
        test_animate_nSteps(4, lightBoardFactory);
    }

    @ParameterizedTest
    @MethodSource("lightBoardFactory")
    void test_animate_fiveSteps(LightBoardFactory lightBoardFactory) throws IOException {
        test_animate_nSteps(5, lightBoardFactory);
    }

    private String loadBoardAsStringFromFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName), StandardCharsets.US_ASCII);
    }

    void test_animate_nSteps(int steps, LightBoardFactory lightBoardFactory) throws IOException {
        final String initialBoardAsString = loadBoardAsStringFromFile("_0.txt");
        final LightAnimation lightAnimation = new PartTwoLightAnimation(
                lightBoardFactory, SITE, initialBoardAsString);
        final String boardAfterOneStepAsString = loadBoardAsStringFromFile("_" + steps + ".txt");
        final LightBoard expectedLightBoard = lightBoardFactory.create(SITE, boardAfterOneStepAsString);
        assertEquals(expectedLightBoard.toString(), lightAnimation.animate(steps).toString());
    }
}
