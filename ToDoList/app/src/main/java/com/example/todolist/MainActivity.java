package com.example.todolist;

/**
 * Homework #2
 * To Do List
 * Rachel Taylor and Nicole Hite
 */

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> taskList = new ArrayList<>();
    TextView taskName;
    TextView taskDate;
    TextView taskP;
    TextView taskNum;
    final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("To Do List");

        taskName = findViewById(R.id.taskName);
        taskDate = findViewById(R.id.taskDate);
        taskP = findViewById(R.id.taskP);
        taskNum = findViewById(R.id.taskNum);
        Log.d(TAG, "onCreate: " + taskList.size());
        updateLabels();

        findViewById(R.id.viewTasksButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Task");


                String[] tasks = new String[taskList.size()];
                for (int i = 0; i < taskList.size(); i++) {
                    tasks[i] = taskList.get(i).name;
                }
                builder.setItems(tasks, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("TASK_KEY", taskList.get(which));
                        intent.putExtra("TASKS_KEY", taskList);
                        startActivityForResult(intent, 100);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        findViewById(R.id.createTasksButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("TASK_KEY", taskList);
                startActivityForResult(intent, 100);
            }
        });
    }

    // Results from closing third activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            taskList = (ArrayList<Task>) data.getSerializableExtra("COMPLETE_TASK");
            updateLabels();
            Log.d(TAG, "onActivityResult: hi");
        }
        if (resultCode == RESULT_FIRST_USER) {
            taskList = (ArrayList<Task>) data.getSerializableExtra("REMOVE_TASK");
            updateLabels();
            Log.d(TAG, "onActivityResult: delete");
        }
    }

    private void updateLabels() {
        taskNum = findViewById(R.id.taskNum);
        if (taskList.size() == 0) {
            taskName.setText("None");
            taskDate.setText("");
            taskP.setText("");
        }

        if (taskList.size() == 1) taskNum.setText("You have " + 1 + " task");
        else taskNum.setText("You have " + taskList.size() + " tasks");
        if (taskList.size() > 0) {
            Task t = taskList.get(0);
            taskName.setText(t.name);
            taskP.setText(t.priority);

            // Parse the date into different parts
            String yr = t.date.substring(0, 4);
            String month = t.date.substring(4, 6);
            String day = t.date.substring(6);

            // Update based on if its single digit
            if (month.charAt(0) == '0') month = String.valueOf(month.charAt(1));
            if (day.charAt(0) == '0') day = String.valueOf(day.charAt(1));

            taskDate.setText(month + "/" + day + "/" + yr);
        }
    }

    protected Task makeTask() {
        String tName = String.valueOf(taskName.getText());
        String tDate = String.valueOf(taskDate.getText());
        String tPrior = String.valueOf(taskP.getText());
        Task info = new Task(tName, tDate, tPrior);
        return info;
    }
}