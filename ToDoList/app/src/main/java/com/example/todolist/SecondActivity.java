package com.example.todolist;

/**
 * Homework #2
 * To Do List
 * Rachel Taylor and Nicole Hite
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    TextView name;
    TextView date;
    TextView priority;
    Task task;
    final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Display Task");

        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        priority = findViewById(R.id.priority);
        Log.d(TAG, "Entering ");

        // Ensure there's data & update text accordingly
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("TASK_KEY")) {
            ArrayList<Task> taskList = (ArrayList<Task>) getIntent().getSerializableExtra("TASKS_KEY");
            task = (Task) getIntent().getSerializableExtra("TASK_KEY");

            // Set name & priority
            name.setText(task.name);
            priority.setText(task.priority);

            // Parse the date into different parts
            String yr = task.date.substring(0, 4);
            String month = task.date.substring(4, 6);
            String day = task.date.substring(6);

            // Update date based on if its single digit
            if (month.charAt(0) == '0') month = String.valueOf(month.charAt(1));
            if (day.charAt(0) == '0') day = String.valueOf(day.charAt(1));

            // Set the date
            date.setText(month + "/" + day + "/" + yr);
        }

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ArrayList<Task> taskList = (ArrayList<Task>) getIntent().getSerializableExtra("TASKS_KEY");
                Log.d(TAG, "OG Size: " + taskList.size());

                // Find task from list & remove it if it's "equal"
                Task task = (Task) getIntent().getSerializableExtra("TASK_KEY");
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).name.equals(task.name) &&
                            taskList.get(i).priority.equals(task.priority) &&
                            taskList.get(i).date.equals(task.date)) {
                        taskList.remove(i);
                    }
                }
                Log.d(TAG, "New Size :" + taskList.size());
                intent.putExtra("REMOVE_TASK", taskList);
                setResult(RESULT_FIRST_USER, intent);
                finish();
            }
        });
    }
}