package net.mirwaldt.aoc.year2015.day18;

import java.util.BitSet;

public class BitSetLightBoard extends AbstractLightBoard {
    private final BitSet bits;
    public BitSetLightBoard(int site) {
        super(site);
        this.bits = new BitSet(site * site);
    }

    public BitSetLightBoard(int site, String initialBoardAsString) {
        super(site);
        this.bits = new BitSet(site * site);
        init(initialBoardAsString);
    }

    @Override
    public LightState getLight(int row, int col) {
        return (bits.get(calculateBitIndex(row, col))) ? LightState.ON : LightState.OFF;
    }

    @Override
    public void setLight(int row, int col, LightState lightState) {
        int bitIndex = calculateBitIndex(row, col);
        if(lightState.equals(LightState.ON)) {
            bits.set(bitIndex);
        } else {
            bits.clear(bitIndex);
        }
    }

    private int calculateBitIndex(int row, int col) {
        return row * site + col;
    }
}
