package com.mnmason86.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton = MainActivity.this.findViewById(R.id.mainActivityAddTaskButton);

        addTaskButton.setOnClickListener(view -> {
            Intent goToAddTaskPage = new Intent(MainActivity.this, AddTask.class);
            startActivity(goToAddTaskPage);

        });

        Button allTaskButton = MainActivity.this.findViewById(R.id.mainActivityAllTasksButton);

        allTaskButton.setOnClickListener(view -> {
            Intent goToAllTaskPage = new Intent(MainActivity.this, AllTask.class);
            startActivity(goToAllTaskPage);
        });
    }
}