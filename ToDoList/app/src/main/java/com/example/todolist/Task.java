package com.example.todolist;

import java.io.Serializable;

/**
 * Homework #2
 * To Do List
 * Rachel Taylor and Nicole Hite
 */

public class Task implements Serializable, Comparable<Task>  {
    String name, date, priority;

    public Task(String name, String date, String priority) {
        this.name = name;
        this.date = date;
        this.priority = priority;
    }

    public Task() {
    }

    @Override
    public int compareTo(Task o) {
        return this.date.compareTo(o.date);
    }
}
