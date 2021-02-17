package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG = "demo";
    final static public String NAME_KEY = "NAME";
    final static public String USER_KEY = "USER";
    final static public String USERS_KEY = "USERS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        setTitle("Main Activity");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(USER_KEY, new User("John Doe", 25));

                ArrayList<User> users = new ArrayList<>();
                users.add(new User("Bob Smith", 25));
                users.add(new User("Jane Doe", 22));

                intent.putExtra(USERS_KEY, users);

                startActivity(intent);
            }
        });

    }
}

//Implicit Intents
        /*
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapplication.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
            }
        });
        */

//Implicit Intents Manifest
        /*
        <activity android:name=".ThirdActivity">
            <intent-filter>
                <action android:name="com.example.myapplication.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity android:name=".SecondActivity">
            <intent-filter>
                <action android:name="com.example.myapplication.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
         */

        //Explicit Intents
        /*
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        */

 /*
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
    */


/*
        setTitle("Main Activity");

        findViewById(R.id.buttonShowAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Error")
                        .setMessage("Unable to perform task!!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d(TAG, "onClick: OK");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d(TAG, "onClick: Cancel");
                            }
                        })
                        .setNeutralButton("I Don't Know", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d(TAG, "onClick: I Don't Know !!");
                            }
                        });
                builder.create().show();
            }
        });
        */

        /*
        imageView = findViewById(R.id.imageView);

        findViewById(R.id.buttonSmile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.smiling);
            }
        });

        findViewById(R.id.buttonSad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.sad);
            }
        });
        */

//setTitle("Main Activity");

//TextView textView = findViewById(R.id.textViewTop);
//textView.setText("Hello World");

//EditText editText = findViewById(R.id.editTextEntry);
//editText.setText("An Example Text");


//Log.d(TAG, "onCreate: ");
//Log.d(TAG, "onCreate: ");

//Toast.makeText(this, "Testing Toasts!!!", Toast.LENGTH_SHORT).show();