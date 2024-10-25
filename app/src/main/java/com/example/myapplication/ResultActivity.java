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

public class ResultActivity extends AppCompatActivity {

    TextView tv, tv2, tv3;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.tvres);
        tv2 = findViewById(R.id.tvres2);
        tv3 = findViewById(R.id.tvres3);

        btnRestart = findViewById(R.id.btnRestart);

        // Displaying correct answers, wrong answers, and final score
        StringBuffer sb = new StringBuffer();
        sb.append("Correct answer: " + QuestionActivity.correct + "\n");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("Wrong answer: " + QuestionActivity.wrong + "\n");

        StringBuffer sb3 = new StringBuffer();
        sb3.append("Final Score: " + QuestionActivity.correct + "\n");

        tv.setText(sb);
        tv2.setText(sb2);
        tv3.setText(sb3);

        // Restart button to reset the quiz
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetting static fields in QuestionActivity
                QuestionActivity.correct = 0;
                QuestionActivity.wrong = 0;
                QuestionActivity.marks = 0;

                // Starting MainActivity to restart the quiz
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish ResultActivity so it doesn’t stay in the back stack
            }
        });
    }
}
