package net.mirwaldt;

import java.util.BitSet;

import static net.mirwaldt.LightState.OFF;
import static net.mirwaldt.LightState.ON;

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
        return (bits.get(calculateBitIndex(row, col))) ? ON : OFF;
    }

    @Override
    public void setLight(int row, int col, LightState lightState) {
        int bitIndex = calculateBitIndex(row, col);
        if(lightState.equals(ON)) {
            bits.set(bitIndex);
        } else {
            bits.clear(bitIndex);
        }
    }

    private int calculateBitIndex(int row, int col) {
        return row * site + col;
    }
}
