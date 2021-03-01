package net.mirwaldt;

public enum LightState {
    ON("#"), OFF(".");
    private final String sign;

    LightState(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }


    public static LightState[][] initBoard(int site) {
        final LightState[][] board = new LightState[site][site];
        for (int rowIndex = 0; rowIndex < site; rowIndex++) {
            for (int colIndex = 0; colIndex < site; colIndex++) {
                board[rowIndex][colIndex] = OFF;
            }
        }
        return board;
    }

    public static LightState[][] readBoardFromString(String boardAsString, int site) {
        final LightState[][] board = new LightState[site][site];
        final String[] rows = boardAsString.split("\n");
        for (int rowIndex = 0; rowIndex < site; rowIndex++) {
            final String row = rows[rowIndex];
            for (int colIndex = 0; colIndex < site; colIndex++) {
                final String col = row.substring(colIndex, colIndex + 1);
                if (col.equals(OFF.getSign())) {
                    board[rowIndex][colIndex] = OFF;
                } else if (col.equals(ON.getSign())) {
                    board[rowIndex][colIndex] = ON;
                } else {
                    throw new IllegalArgumentException("Cannot handle '" + col + "' at row "
                            + rowIndex + " and col " + colIndex + ".");
                }
            }
        }
        return board;
    }

    public static String boardToString(LightState[][] board, int site) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int rowIndex = 0; rowIndex < site; rowIndex++) {
            for (int colIndex = 0; colIndex < site; colIndex++) {
                stringBuilder.append(board[rowIndex][colIndex].getSign());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
