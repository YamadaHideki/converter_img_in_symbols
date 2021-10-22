package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;

public class Schema implements TextColorSchema {
    private static final char[] symbols = {'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {
        return symbols[color/32];
    }
}
