/*
In Class #3
Group26_InClass03
Rachel Taylor and Nicole Hite
 */
package com.example.inclass3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class SecondActivity extends AppCompatActivity {

    RadioGroup deptChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Department");
        deptChoice = findViewById(R.id.deptChoice);

        findViewById(R.id.buttonConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                User info = (User) getIntent().getSerializableExtra("USER_KEY");
                int checkedId =  deptChoice.getCheckedRadioButtonId();

                if(checkedId == R.id.compSci){
                    info.dept = "Computer Science";
                } else if (checkedId == R.id.sIS){
                    info.dept = "Software Info. Systems";
                } else if (checkedId == R.id.bInfo){
                    info.dept = "Bio Informatics";
                } else if (checkedId == R.id.dS){
                    info.dept = "Data Science";
                }
                intent.putExtra("COMPLETE_USER", info);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}