package net.mirwaldt;

import org.junit.jupiter.api.Test;

import static net.mirwaldt.LightState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountNeighboursTest {

    private final LightBoardFactory lightBoardFactory = new TwoDimensionalArrayLightBoardFactory();

    @Test
    void test_corners() {
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

            for (int selectedBitIndex = 0; selectedBitIndex < 4; selectedBitIndex++) {
                final int row = (2 & selectedBitIndex) >> 1;
                final int col = 1 & selectedBitIndex;

                final int selectedBit = 1 << selectedBitIndex;
                final int bitMask = ~selectedBit;
                final int restBits = allBits & bitMask;
                final int bitCount = Integer.bitCount(restBits);

                assertEquals(bitCount, partOneLightAnimation.countNeighboursOn(row, col));
            }
        }
    }

    @Test
    void test_middleAndBorders() {
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
