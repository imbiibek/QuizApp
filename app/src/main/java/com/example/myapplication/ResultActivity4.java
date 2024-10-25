package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity4 extends AppCompatActivity {

    TextView tv, tv2, tv3;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.tvres);
        tv2 = findViewById(R.id.tvres2);
        tv3 = findViewById(R.id.tvres3);
        btnRestart = findViewById(R.id.btnRestart);

        // Fetch results from Intent if available, otherwise use static values
        int correctAnswers = getIntent().getIntExtra("correct", QuestionActivity4.correct);
        int wrongAnswers = getIntent().getIntExtra("wrong", QuestionActivity4.wrong);
        int finalScore = correctAnswers;

        // Display results
        tv.setText("Correct answer: " + correctAnswers);
        tv2.setText("Wrong answer: " + wrongAnswers);
        tv3.setText("Final Score: " + finalScore);

        // Restart button to reset the quiz
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset static fields in QuestionActivity4
                QuestionActivity4.correct = 0;
                QuestionActivity4.wrong = 0;
                QuestionActivity4.marks = 0;

                // Start MainActivity to restart the quiz
                Intent intent = new Intent(ResultActivity4.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish ResultActivity to clear it from the back stack
            }
        });
    }
}
