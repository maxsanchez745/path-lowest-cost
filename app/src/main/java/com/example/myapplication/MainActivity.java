package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.a_main_et)
    EditText editText;

    @BindView(R.id.a_main_has_path)
    TextView textViewHasPath;

    @BindView(R.id.a_main_total_weight)
    TextView textViewWeight;

    @BindView(R.id.a_main_path_taken)
    TextView textViewPathTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    // On click - Get the string from the EditText, parse the input and then calculating the lowest path
    @OnClick(R.id.a_main_btn)
    public void calculateLowestPath(View view) {
        final String input = editText.getText().toString();
        Object[][] grid = parseInput(input);
        generateLowestPath(grid);
    }

    // Parse a string (EditText input) to be a grid of Objects
    private Object[][] parseInput(String input) {
        GridParser gridParser = new GridParser();
        return gridParser.parse(input);
    }

    // Calculates the lowest path with help of the GridLowestCost helper
    private void generateLowestPath(Object[][] grid) {
        try {
            GridLowestCost gridLowestCost = new GridLowestCost(grid);
            gridLowestCost.calculatePath();

            boolean pathExists = gridLowestCost.calculatePath();

            textViewHasPath.setText(pathExists ? "Yes" : "No");
            textViewWeight.setText(String.format(Locale.getDefault(), "%d", gridLowestCost.getCalculatedWeight()));
            final int[] pathTaken = gridLowestCost.getPathTaken();
            textViewPathTaken.setText(Arrays.toString(pathTaken));
        } catch (Exception e) {
            textViewHasPath.setText(R.string.invalid_matrix);
        }
    }
}
