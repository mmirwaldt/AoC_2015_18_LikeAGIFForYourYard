package net.mirwaldt;

import org.junit.jupiter.api.Test;

import static net.mirwaldt.LightState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountNeighboursTest {
    @Test
    void test_corners() {
        final DefaultLightAnimation defaultLightAnimation = new DefaultLightAnimation();
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
            defaultLightAnimation.init(readBoardFromString(boardAsString, 2), 2);

            for (int selectedBitIndex = 0; selectedBitIndex < 4; selectedBitIndex++) {
                final int row = (2 & selectedBitIndex) >> 1;
                final int col = 1 & selectedBitIndex;

                final int selectedBit = 1 << selectedBitIndex;
                final int bitMask = ~selectedBit;
                final int restBits = allBits & bitMask;
                final int bitCount = Integer.bitCount(restBits);

                assertEquals(bitCount, defaultLightAnimation.countNeighboursOn(row, col));
                assertEquals(3 - bitCount, defaultLightAnimation.countNeighboursOff(row, col));
            }
        }
    }

    @Test
    void test() {
        final DefaultLightAnimation defaultLightAnimation = new DefaultLightAnimation();
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
            defaultLightAnimation.init(readBoardFromString(boardAsString, 3), 3);

            final int centerBitIndex = 4;
            final int centerBit = 1 << centerBitIndex;
            final int centerBitMask = ~centerBit;
            final int bitCount = Integer.bitCount(allBits & centerBitMask);
            assertEquals(bitCount, defaultLightAnimation.countNeighboursOn(1, 1), "\n" + boardAsString);
            assertEquals(8 - bitCount, defaultLightAnimation.countNeighboursOff(1, 1),
                    "\n" + boardAsString);

            final int topCenterBitMask = getBitMaskForBitIndexes(0, 2, 3, 4, 5);
            final int topCenterBitCount = Integer.bitCount(allBits & topCenterBitMask);
            assertEquals(topCenterBitCount, defaultLightAnimation.countNeighboursOn(0, 1), "\n" + boardAsString);
            assertEquals(5 - topCenterBitCount, defaultLightAnimation.countNeighboursOff(0, 1),
                    "\n" + boardAsString);

            final int bottomCenterBitMask = getBitMaskForBitIndexes(3, 4, 5, 6, 8);
            final int bottomCenterBitCount = Integer.bitCount(allBits & bottomCenterBitMask);
            assertEquals(bottomCenterBitCount, defaultLightAnimation.countNeighboursOn(2, 1), "\n" + boardAsString);
            assertEquals(5 - bottomCenterBitCount, defaultLightAnimation.countNeighboursOff(2, 1),
                    "\n" + boardAsString);

            final int leftCenterBitMask = getBitMaskForBitIndexes(0, 1, 4, 6, 7);
            final int leftCenterBitCount = Integer.bitCount(allBits & leftCenterBitMask);
            assertEquals(leftCenterBitCount, defaultLightAnimation.countNeighboursOn(1, 0), "\n" + boardAsString);
            assertEquals(5 - leftCenterBitCount, defaultLightAnimation.countNeighboursOff(1, 0),
                    "\n" + boardAsString);

            final int rightCenterBitMask = getBitMaskForBitIndexes(1, 2, 4, 7, 8);
            final int rightCenterBitCount = Integer.bitCount(allBits & rightCenterBitMask);
            assertEquals(rightCenterBitCount, defaultLightAnimation.countNeighboursOn(1, 2), "\n" + boardAsString);
            assertEquals(5 - rightCenterBitCount, defaultLightAnimation.countNeighboursOff(1, 2),
                    "\n" + boardAsString);


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
