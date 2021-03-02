package net.mirwaldt;

import static net.mirwaldt.LightState.OFF;
import static net.mirwaldt.LightState.ON;

public abstract class AbstractLightBoard implements LightBoard {
    protected final int site;

    public AbstractLightBoard(int site) {
        this.site = site;
    }

    protected void init(String initialBoardAsString) {
        final String[] rows = initialBoardAsString.split("\n");
        for (int rowIndex = 0; rowIndex < site; rowIndex++) {
            final String row = rows[rowIndex];
            initRow(rowIndex, row);
        }
    }

    private void initRow(int rowIndex, String row) {
        for (int colIndex = 0; colIndex < site; colIndex++) {
            final String col = row.substring(colIndex, colIndex + 1);
            initCell(rowIndex, colIndex, col);
        }
    }

    private void initCell(int rowIndex, int colIndex, String col) {
        if (col.equals(OFF.getSign())) {
            setLight(rowIndex, colIndex, OFF);
        } else if (col.equals(ON.getSign())) {
            setLight(rowIndex, colIndex, ON);
        } else {
            throw new IllegalArgumentException("Cannot handle '" + col + "' at row "
                    + rowIndex + " and col " + colIndex + ".");
        }
    }

    protected void init() {
        for (int row = 0; row < getSite(); row++) {
            for (int col = 0; col < getSite(); col++) {
                setLight(row, col, OFF);
            }
        }
    }

    @Override
    public int getSite() {
        return site;
    }

    @Override
    public long countLightsOn() {
        int count = 0;
        for (int row = 0; row < getSite(); row++) {
            for (int col = 0; col < getSite(); col++) {
                if(getLight(row, col).equals(ON)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int rowIndex = 0; rowIndex < site; rowIndex++) {
            for (int colIndex = 0; colIndex < site; colIndex++) {
                stringBuilder.append(getLight(rowIndex, colIndex).getSign());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
