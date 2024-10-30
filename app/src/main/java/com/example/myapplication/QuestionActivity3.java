package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class QuestionActivity3 extends AppCompatActivity {

    String[] question = {
            "What is the maximum possible length of an identifier?",
            "Who developed the Python language?",
            "In which year was the Python language developed?",
            "In which language is Python written?",
            "Which one of the following is the correct extension of the Python file?"
    };
    String[] answer = {
            "None of these above",
            "Guido van Rossum",
            "1989",
            "C",
            ".py"
    };
    String[] opt = {
            "16", "32", "64", "None of these above",
            "Guido van Rossum", "Zim Den", "Niene Stom", "Wick van Rossum",
            "1989", "1995", "1972", "1981",
            "C", "English", "PHP", "All of the above",
            ".py", ".python", ".p", "None of these"
    };

    int[] questionOrder;
    int flag = 0;

    public static int marks = 0, correct = 0, wrong = 0;

    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;
    private TextView questionnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question3);

        // Initialize and shuffle question order
        initializeShuffledQuestions();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextView score = findViewById(R.id.textview4);
        questionnumber = findViewById(R.id.DispName);
        submitbutton = findViewById(R.id.button3);
        quitbutton = findViewById(R.id.buttonquit);
        tv = findViewById(R.id.tvque);

        radio_g = findViewById(R.id.answergrp);
        rb1 = findViewById(R.id.radiobutton1);
        rb2 = findViewById(R.id.radiobutton2);
        rb3 = findViewById(R.id.radiobutton3);
        rb4 = findViewById(R.id.radiobutton4);

        setQuestion(flag);  // Set the first question

        submitbutton.setOnClickListener(v -> {
            if (radio_g.getCheckedRadioButtonId() == -1) {
                Toast.makeText(QuestionActivity3.this, "Please select one choice", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton uans = findViewById(radio_g.getCheckedRadioButtonId());
            String ansText = uans.getText().toString();

            if (ansText.equals(answer[questionOrder[flag]])) {
                correct++;
                Toast.makeText(QuestionActivity3.this, "Correct", Toast.LENGTH_SHORT).show();
            } else {
                wrong++;
                Toast.makeText(QuestionActivity3.this, "Wrong", Toast.LENGTH_SHORT).show();
            }

            flag++;
            if (score != null) {
                score.setText("" + correct);

                if (flag < question.length) {
                    setQuestion(flag);
                    questionnumber.setText((flag + 1) + "/" + question.length + " Question");
                } else {
                    marks = correct;
                    Intent in = new Intent(QuestionActivity3.this, ResultActivity3.class);
                    startActivity(in);
                }

                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), ResultActivity3.class);
            startActivity(intent1);
        });
    }

    // Initialize and shuffle the order of questions
    private void initializeShuffledQuestions() {
        questionOrder = new int[question.length];
        for (int i = 0; i < question.length; i++) {
            questionOrder[i] = i;
        }

        // Fisher-Yates shuffle for question indices
        Random random = new Random();
        for (int i = question.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = questionOrder[i];
            questionOrder[i] = questionOrder[j];
            questionOrder[j] = temp;
        }
    }

    // Set the current question and its options based on shuffled order
    private void setQuestion(int index) {
        int questionIndex = questionOrder[index];
        tv.setText(question[questionIndex]);
        rb1.setText(opt[questionIndex * 4]);
        rb2.setText(opt[questionIndex * 4 + 1]);
        rb3.setText(opt[questionIndex * 4 + 2]);
        rb4.setText(opt[questionIndex * 4 + 3]);
    }
}
