package net.mirwaldt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static net.mirwaldt.LightState.OFF;
import static net.mirwaldt.LightState.ON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountNeighboursTest {
    private static Stream<Arguments> lightBoardFactory() {
        return Stream.of(Arguments.of(new TwoDimensionalArrayLightBoardFactory()),
                Arguments.of(new BitSetLightBoardFactory()));
    }

    @ParameterizedTest
    @MethodSource("lightBoardFactory")
    void test_corners(LightBoardFactory lightBoardFactory) {
        /*
        Bits:
        01
        23
        */
        for (int allBits = 0; allBits < 16; allBits++) {
            final StringBuilder stringBuilder = new StringBuilder();
            for (int selectedBitIndex = 0; selectedBitIndex < 4; selectedBitIndex++) {
                final int selectedBit = 1 << selectedBitIndex;
                final int bitValue = allBits & selectedBit;
                if (0 < bitValue) {
                    stringBuilder.append(ON.getSign());
                } else {
                    stringBuilder.append(OFF.getSign());
                }
                if (selectedBitIndex == 1) {
                    stringBuilder.append("\n");
                }
            }

            final String boardAsString = stringBuilder.toString();

            final PartOneLightAnimation partOneLightAnimation = new PartOneLightAnimation(
                    lightBoardFactory, 2, boardAsString);

            final int leftTopBitMask = getBitMaskForBitIndexes(1, 2, 3);
            final int leftTopBitCount = Integer.bitCount(allBits & leftTopBitMask);
            assertEquals(leftTopBitCount, partOneLightAnimation.countNeighboursOn(0, 0));

            final int rightTopBitMask = getBitMaskForBitIndexes(0, 2, 3);
            final int rightTopBitCount = Integer.bitCount(allBits & rightTopBitMask);
            assertEquals(rightTopBitCount, partOneLightAnimation.countNeighboursOn(0, 1));

            final int leftBottomBitMask = getBitMaskForBitIndexes(0, 1, 3);
            final int leftBottomBitCount = Integer.bitCount(allBits & leftBottomBitMask);
            assertEquals(leftBottomBitCount, partOneLightAnimation.countNeighboursOn(1, 0));

            final int rightBottomBitMask = getBitMaskForBitIndexes(0, 1, 2);
            final int rightBottomBitCount = Integer.bitCount(allBits & rightBottomBitMask);
            assertEquals(rightBottomBitCount, partOneLightAnimation.countNeighboursOn(1, 1));
        }
    }

    @ParameterizedTest
    @MethodSource("lightBoardFactory")
    void test_middleAndBorders(LightBoardFactory lightBoardFactory) {
        /*
        Bits:
        012
        345
        678
         */
        for (int allBits = 0; allBits < 512; allBits++) {
            final StringBuilder stringBuilder = new StringBuilder();
            for (int selectedBitIndex = 0; selectedBitIndex < 9; selectedBitIndex++) {
                final int selectedBit = 1 << selectedBitIndex;
                final int bitValue = allBits & selectedBit;
                if (0 < bitValue) {
                    stringBuilder.append(ON.getSign());
                } else {
                    stringBuilder.append(OFF.getSign());
                }
                if (selectedBitIndex == 2 || selectedBitIndex == 5) {
                    stringBuilder.append("\n");
                }
            }

            final String boardAsString = stringBuilder.toString();
            final PartOneLightAnimation partOneLightAnimation = new PartOneLightAnimation(
                    lightBoardFactory, 3, boardAsString);

            final int centerBitIndex = 4;
            final int centerBit = 1 << centerBitIndex;
            final int centerBitMask = ~centerBit;
            final int bitCount = Integer.bitCount(allBits & centerBitMask);
            assertEquals(bitCount, partOneLightAnimation.countNeighboursOn(1, 1),
                    "\n" + boardAsString + "\n" + partOneLightAnimation.toString());

            final int topCenterBitMask = getBitMaskForBitIndexes(0, 2, 3, 4, 5);
            final int topCenterBitCount = Integer.bitCount(allBits & topCenterBitMask);
            assertEquals(topCenterBitCount, partOneLightAnimation.countNeighboursOn(0, 1), "\n" + boardAsString);

            final int bottomCenterBitMask = getBitMaskForBitIndexes(3, 4, 5, 6, 8);
            final int bottomCenterBitCount = Integer.bitCount(allBits & bottomCenterBitMask);
            assertEquals(bottomCenterBitCount, partOneLightAnimation.countNeighboursOn(2, 1), "\n" + boardAsString);

            final int leftCenterBitMask = getBitMaskForBitIndexes(0, 1, 4, 6, 7);
            final int leftCenterBitCount = Integer.bitCount(allBits & leftCenterBitMask);
            assertEquals(leftCenterBitCount, partOneLightAnimation.countNeighboursOn(1, 0), "\n" + boardAsString);

            final int rightCenterBitMask = getBitMaskForBitIndexes(1, 2, 4, 7, 8);
            final int rightCenterBitCount = Integer.bitCount(allBits & rightCenterBitMask);
            assertEquals(rightCenterBitCount, partOneLightAnimation.countNeighboursOn(1, 2), "\n" + boardAsString);
        }
    }

    private int getBitMaskForBitIndexes(int...bitIndexes) {
        int bitMask = 0;
        for (int bitIndex : bitIndexes) {
            bitMask = bitMask | (1 << bitIndex);
        }
        return bitMask;
    }
}
