package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;

public class Schema implements TextColorSchema {

    //private static final char[] symbols = {'#', '$', '@', '%', '*', '+', '-', '\''};
    private static char[] symbols = new char[8];

    public Schema() {
        symbols = new char[]{'#', '$', '@', '%', '*', '+', '-', '\''};
    }

    public Schema(Character a, Character b, Character c, Character d, Character e, Character f, Character g, Character h) {
        symbols = new char[]{a, b, c, d, e, f, g, h};
    }

    @Override
    public char convert(int color) {
        return symbols[color/32];
    }
}
