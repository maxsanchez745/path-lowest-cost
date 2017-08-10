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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.a_main_et)
    EditText editText;

    @BindView(R.id.a_main_has_path)
    TextView textViewHasPath;

    @BindView(R.id.a_main_total_weight)
    TextView textViewWeight;

    @BindView(R.id.a_main_path_taken)
    TextView textViewPathTaken;

    private GridLowestCost gridLowestCost;
    private Object[][] grid;
    private GridParser gridParser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        gridParser = new GridParser();
    }

    public void calculateLowestPath(View view) {
        grid = gridParser.parse(editText.getText().toString());
        generateLowestPath();
    }

    private void generateLowestPath() {
        gridLowestCost = new GridLowestCost(grid);
        gridLowestCost.calculatePath();
        boolean pathExists = gridLowestCost.calculatePath();

        textViewHasPath.setText(pathExists ? "Yes" : "No");
        textViewWeight.setText(String.format(Locale.getDefault(), "%d", gridLowestCost.getCalculatedWeight()));
        final int[] pathTaken = gridLowestCost.getPathTaken();
        textViewPathTaken.setText(Arrays.toString(pathTaken));
    }
}
