package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Find the logout button
        btnLogout = findViewById(R.id.btnLogout);

        // Set up onClick listener for the logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
                Toast.makeText(MainActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
            }
        });

        // Setting up Window Insets for padding
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
