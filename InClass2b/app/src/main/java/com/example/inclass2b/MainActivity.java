package com.example.inclass2b;

/*
    InClass02
    MainActivity.java
    Rachel Taylor and Nicole Hite
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        RadioGroup grr = findViewById(R.id.radioGroup);
        int checkId = grr.getCheckedRadioButtonId();
        EditText inch = findViewById(R.id.distance);
        float i = Float.parseFloat(String.valueOf(inch.getText()));
        float result;
        if(checkId == R.id.meters){
            result = (float) (i * 0.0254);
            TextView grrr = findViewById(R.id.number);
            grrr.setText(String.valueOf(result));
        } else if (checkId == R.id.feet){
            result = i / 12;
            TextView grrr = findViewById(R.id.number);
            grrr.setText(String.valueOf(result));
        } else if (checkId == R.id.miles){
            result = i / 63360;
            TextView grrr = findViewById(R.id.number);
            grrr.setText(String.valueOf(result));
        } else if (checkId == R.id.clearAll){
            inch.setText("");
            TextView grrr = findViewById(R.id.number);
            grrr.setText("");
        } else {
            Toast.makeText(this, "Please Enter the Distance above", Toast.LENGTH_LONG).show();
        }
    }
}