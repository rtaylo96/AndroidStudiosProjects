package com.example.inclass3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView holdName;
    TextView holdEmail;
    TextView holdId;
    TextView holdDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("Profile");

        holdName = findViewById(R.id.holdName);
        holdEmail = findViewById(R.id.holdEmail);
        holdId = findViewById(R.id.holdId);
        holdDept = findViewById(R.id.holdDept);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("USER_KEY")){
            User user = (User) getIntent().getSerializableExtra("USER_KEY");
            //Fill out the form again
            holdName.setText(user.name);
            holdEmail.setText(user.email);
            holdId.setText(user.id);
            holdDept.setText(user.dept);
        }
    }
}