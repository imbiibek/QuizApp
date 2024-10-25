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

public class ResultActivity3 extends AppCompatActivity {

    TextView tv, tv2, tv3;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.tvres);
        tv2 = findViewById(R.id.tvres2);
        tv3 = findViewById(R.id.tvres3);
        btnRestart = findViewById(R.id.btnRestart);

        // Retrieving data from Intent if values were passed, otherwise use static fields as fallback
        int correctAnswers = getIntent().getIntExtra("correct", QuestionActivity3.correct);
        int wrongAnswers = getIntent().getIntExtra("wrong", QuestionActivity3.wrong);
        int finalScore = correctAnswers;

        // Displaying the results
        tv.setText("Correct answer: " + correctAnswers);
        tv2.setText("Wrong answer: " + wrongAnswers);
        tv3.setText("Final Score: " + finalScore);

        // Restart button to reset the quiz
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetting static fields in QuestionActivity3
                QuestionActivity3.correct = 0;
                QuestionActivity3.wrong = 0;
                QuestionActivity3.marks = 0;

                // Starting MainActivity to restart the quiz
                Intent intent = new Intent(ResultActivity3.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish ResultActivity so it doesn’t stay in the back stack
            }
        });
    }
}
