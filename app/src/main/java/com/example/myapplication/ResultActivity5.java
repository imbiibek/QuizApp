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

public class ResultActivity5 extends AppCompatActivity {

    TextView tv, tv2, tv3;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.tvres);
        tv2 = findViewById(R.id.tvres2);
        tv3 = findViewById(R.id.tvres3);
        btnRestart = findViewById(R.id.btnRestart);

        // Displaying the correct and wrong answers and final score
        tv.setText("Correct answer: " + QuestionActivity5.correct);
        tv2.setText("Wrong answer: " + QuestionActivity5.wrong);
        tv3.setText("Final Score: " + QuestionActivity5.correct);

        // Restart button to reset the quiz
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetting static fields in QuestionActivity5
                QuestionActivity5.correct = 0;
                QuestionActivity5.wrong = 0;
                QuestionActivity5.marks = 0;

                // Starting MainActivity to restart the quiz
                Intent intent = new Intent(ResultActivity5.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish ResultActivity5 to clear it from the back stack
            }
        });
    }
}
