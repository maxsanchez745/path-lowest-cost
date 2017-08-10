package com.example.myapplication;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by user on 8/10/17.
 */

public class GridParser {
    private static final String TAG = "GridParserTAG_";

    public Object[][] parse(String input) {
        int width;
        int height;

        String processedInput = preProcessInput(input);
        String[] rows = processedInput.split("\\},");

        height = rows.length;

        if (rows.length < 1) {
            return null;
        }

        String processedRowWidth = preProcessInput(rows[0]).replace("}", "").replace("{", "");
        String[] numbersWidth = processedRowWidth.split(",");
        width = numbersWidth.length;

        Object[][] grid = new Object[height][width];

        for (int i = 0; i < rows.length; i++) {
            String processedRow = preProcessInput(rows[i]).replace("}", "").replace("{", "");
            String[] numbers = processedRow.split(",");
            for (int j = 0; j < numbers.length; j++) {
                grid[i][j] = numbers[j];
            }
        }

        return grid;
    }

    private String preProcessInput(String input) {
        return input.trim().replace(" ", "");
    }
}
