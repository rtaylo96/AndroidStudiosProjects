package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class SecondActivity extends AppCompatActivity {

    TextView textViewGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewGreeting = findViewById(R.id.textViewGreeting);

        if(getIntent() !=null && getIntent().getExtras() != null && getIntent().hasExtra(MainActivity.USERS_KEY)){
            ArrayList<User> users = (ArrayList<User>)getIntent().getSerializableExtra(MainActivity.USERS_KEY);
            Collections.shuffle(users);
            User user = users.get(0);
            textViewGreeting.setText("Hello " + user.name);
        }

        setTitle("Second Activity");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}