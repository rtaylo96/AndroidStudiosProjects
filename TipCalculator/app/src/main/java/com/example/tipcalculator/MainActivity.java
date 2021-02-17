package com.example.tipcalculator;
/*
Homework 1 - Tip Calculator
MainActivity.java
Nicole Hite

Note: We both thought HWs were solo since the instructions said "student" name rather than
"studentS", so we both did it seperately but still both ended up completing it. Messaged
Dr. Shehab about it and he said it was okay & just to mention it here.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    EditText billTotal;
    RadioGroup percentGroup;
    TextView percentLabel;
    SeekBar seekBar;
    TextView tipTotal;
    TextView finalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotal = findViewById(R.id.billTotal);
        percentGroup = findViewById(R.id.percentGroup);
        percentLabel = findViewById(R.id.percentLabel);
        seekBar = findViewById(R.id.percent);
        tipTotal = findViewById(R.id.tipTotal);
        finalTotal = findViewById(R.id.finalTotal);

        billTotal.addTextChangedListener(this);
        percentGroup.setOnCheckedChangeListener((group, checkedId) -> updateTotals());

        // Update totals when the progress is changed (will only update if "Custom" selected
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentLabel.setText(progress + "%");
                updateTotals();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Exit when button is clicked
        findViewById(R.id.exitButton).setOnClickListener(v -> {
            finish();
            System.exit(0);
        });
    }

    /**
     * This method will calculate the tip & new total and update the labels accordingly
     */
    public void updateTotals() {
        Toast invalid = Toast.makeText(this, "Please enter a valid number" +
                " to calculate your total.", Toast.LENGTH_LONG);
        // If the amount is NOT empty, calculate
        if (!String.valueOf(billTotal.getText()).isEmpty()) {
            invalid.cancel();
            float billAmount = Float.parseFloat(String.valueOf(billTotal.getText()));
            int checkedId = percentGroup.getCheckedRadioButtonId();
            Log.d("TAG", String.valueOf(billAmount));
            float tip = 0.0f;

            // Calculate correct tip based on percent selected
            if (checkedId == R.id.tenPercent) {
                tip = billAmount * .10f;
            } else if (checkedId == R.id.fifteenPercent) {
                tip = billAmount * .15f;
            } else if (checkedId == R.id.eighteenPercent) {
                tip = billAmount * .18f;
            } else if (checkedId == R.id.customPercent) {
                tip = billAmount * (.01f * seekBar.getProgress());
            }

            // Update totals & set the text of the corresponding labels
            float newTotal = billAmount + tip;
            tipTotal.setText(String.format("%.02f", tip));
            finalTotal.setText(String.format("%.02f", newTotal));
        } else { // Set to 0.0 if empty
            invalid.show();
            tipTotal.setText(String.valueOf(0.00f));
            finalTotal.setText(String.valueOf(0.00f));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        updateTotals();
    }

    @Override
    public void afterTextChanged(Editable s) {
    }


}