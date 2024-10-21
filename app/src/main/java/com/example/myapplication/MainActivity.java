package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void C(View view) {

        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        startActivity(intent);

    }

    public void Cplus(View view) {

        Intent intent = new Intent(MainActivity.this, QuestionActivity2.class);
        startActivity(intent);
    }

    public void Python(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivity3.class);
        startActivity(intent);
    }

    public void Html(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivity4.class);
        startActivity(intent);
    }

    public void Kotlin(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivity5.class);
        startActivity(intent);
    }

    public void Csharp(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivity6.class);
        startActivity(intent);
    }
}