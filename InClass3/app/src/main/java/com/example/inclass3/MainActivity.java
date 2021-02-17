/*
In Class #3
Group26_InClass03
Rachel Taylor and Nicole Hite
 */

package com.example.inclass3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText id;
    TextView dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Registration");
        name = findViewById(R.id.name);
        Toast invalidName = Toast.makeText(this, "Please enter letters only.", Toast.LENGTH_LONG);
        email = findViewById(R.id.email);
        Toast invalidEmail = Toast.makeText(this, "Please Enter a valid Email.", Toast.LENGTH_LONG);
        id = findViewById(R.id.id);
        Toast invalidID = Toast.makeText(this, "Please enter an ID.", Toast.LENGTH_LONG);
        dept = findViewById(R.id.dept);
        Toast invalidDept = Toast.makeText(this, "Please select a Department.", Toast.LENGTH_LONG);


        // Clicking Select Button
        findViewById(R.id.buttonSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // Make a user based on the current form information & pass it to the Second Activity
                User info = makeUser();
                intent.putExtra("USER_KEY", info);
                startActivityForResult(intent, 100);
            }
        });

        // Clicking Submit Button
        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!String.valueOf(name.getText()).matches("^[a-zA-Z]*$")){
                    invalidName.show();
                } else if(!String.valueOf(email.getText()).matches("^(.+)@(.+)$")) {
                    invalidEmail.show();
                } else if (String.valueOf(id.getText()).isEmpty()) {
                    invalidID.show();
                } else if (String.valueOf(dept.getText()).isEmpty()){
                    invalidDept.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                    intent.putExtra("USER_KEY", makeUser());
                    startActivity(intent);
                }
            }
        });
    }

    // Results from closing second activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            User user = (User) data.getSerializableExtra("COMPLETE_USER");
            //Fill out the form again
            name.setText(user.name);
            email.setText(user.email);
            id.setText(user.id);
            dept.setText(user.dept);
        }
    }

    /**
     * Method to get and hold information from each field creating user objects
     * @return a user object
     */
    protected User makeUser() {
        String userName = String.valueOf(name.getText());
        String userEmail = String.valueOf(email.getText());
        String userId = String.valueOf(id.getText());
        String userDept = String.valueOf(dept.getText());
        User info = new User(userName, userEmail, userId, userDept);
        return info;
    }
}