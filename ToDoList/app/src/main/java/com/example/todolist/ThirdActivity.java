package com.example.todolist;

/**
 * Homework #2
 * To Do List
 * Rachel Taylor and Nicole Hite
 */

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class ThirdActivity extends AppCompatActivity {

    EditText taskNameEdit;
    TextView dateSet;
    String dateFormat;
    RadioGroup pChoice;
    DatePickerDialog dpd;
    int year, month, dayOfMonth;
    Calendar c;
    final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("Create Task");
        pChoice = findViewById(R.id.pChoice);
        dateSet = findViewById(R.id.dateSet);

        findViewById(R.id.setDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                dpd = new DatePickerDialog(ThirdActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dpd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dateSet.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        });

                        // Store the values in Strings & adjust if single digit for sorting
                        String monthString = String.valueOf(month + 1);
                        if (month < 10) monthString = 0 + monthString;
                        String dayString = String.valueOf(dayOfMonth);
                        if (dayOfMonth < 10) dayString = 0 + dayString;
                        dateFormat = "" + year + monthString + dayString;
                        Log.d(TAG, "Date in YYYYMMDD Format: " + dateFormat);
                        dateSet.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, dayOfMonth);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();
            }
        });


        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskNameEdit = findViewById(R.id.taskNameEdit);
                // Validation
                if (String.valueOf(taskNameEdit.getText()).isEmpty()) { // Name missing
                    Toast.makeText(getApplicationContext(), "Please Enter a Name for the Task", Toast.LENGTH_LONG).show();
                } else if (String.valueOf(dateSet.getText()).isEmpty()) { // Date missing
                    Toast.makeText(getApplicationContext(), "Please Select a Date for this Task", Toast.LENGTH_LONG).show();
                } else if (pChoice.getCheckedRadioButtonId() == -1) { // Priority missing
                    Toast.makeText(getApplicationContext(), "Please Select A Priority for this Task", Toast.LENGTH_LONG).show();
                } else { // GOOD INPUT
                    Intent intent = new Intent();
                    ArrayList<Task> taskList = (ArrayList<Task>) getIntent().getSerializableExtra("TASK_KEY");
                    Log.d(TAG, "onClick: Tasks Gotten");
                    Task task = new Task();

                    // Name & Date set
                    task.name = String.valueOf(taskNameEdit.getText());
                    task.date = String.valueOf(dateFormat);

                    // Priority Set
                    int checkedId = pChoice.getCheckedRadioButtonId();
                    if (checkedId == R.id.High) task.priority = "High";
                    else if (checkedId == R.id.Medium) task.priority = "Medium";
                    else if (checkedId == R.id.Low) task.priority = "Low";

                    Log.d(TAG, task.name + " " + task.date + " " + task.priority);
                    taskList.add(task);

                    // Sort the TaskList based on the dates. First = Closest
                    Collections.sort(taskList);
                    intent.putExtra("COMPLETE_TASK", taskList);
                    setResult(RESULT_OK, intent);
                    finish();
                }
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