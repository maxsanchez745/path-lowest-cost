package com.example.myapplication;

/**
 * Created by user on 8/10/17.
 */

public class GridParser {
    private static final String TAG = "GridParserTAG_";

    // Getting the input String and parsing it
    public Object[][] parse(String input) {
        int width;
        int height;

        // Split the input with curly braces (separate each row)
        String processedInput = preProcessInput(input);
        String[] rows = processedInput.split("\\},");

        height = rows.length;

        // If there are no rows, the parsing is not valid
        if (rows.length < 1) {
            return null;
        }

        // Get the width and height of the actual grid with the values of each row
        String processedRowWidth = preProcessInput(rows[0]).replace("}", "").replace("{", "");
        String[] numbersWidth = processedRowWidth.split(",");
        width = numbersWidth.length;

        Object[][] grid = new Object[height][width];

        // Loop through the parsed input into the grid
        for (int i = 0; i < rows.length; i++) {
            String processedRow = preProcessInput(rows[i]).replace("}", "").replace("{", "");
            String[] numbers = processedRow.split(",");
            System.arraycopy(numbers, 0, grid[i], 0, numbers.length);
        }

        return grid;
    }

    // Pre-process the values to avoid unneeded spaces
    private String preProcessInput(String input) {
        return input.trim().replace(" ", "");
    }
}
