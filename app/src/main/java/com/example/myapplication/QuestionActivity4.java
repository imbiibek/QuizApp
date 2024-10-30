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

public class QuestionActivity4 extends AppCompatActivity {

    String[] question = {
            "HTML stands for -",
            "The correct sequence of HTML tags for starting a webpage is -",
            "Which of the following element is responsible for making the text bold in HTML?",
            "Which of the following tag is used for inserting the largest heading in HTML?",
            "Which of the following tag is used to insert a line-break in HTML?"
    };

    String[] answer = {
            "HyperText Markup Language",
            "HTML, Head, Title, Body",
            "<b>",
            "<h1>",
            "<br>"
    };

    String[] opt = {
            "HyperText Markup Language", "HyperText and links Markup Language", "HyperText Markup Language", "None of these",
            "HTML, Head, Title, Body", "Head, Title, HTML, Body", "HTML, Body, Title, Head", "All the above comments are correct.",
            "<pre>", "<a>", "<b>", "<br>",
            "<h1>", "<h3>", "<h5>", "<h6>",
            "<br>", "<a>", "<pre>", "<b>"
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
        setContentView(R.layout.activity_question4);

        // Initialize and shuffle question order
        initializeShuffledQuestions();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextView score = findViewById(R.id.textview4);
        Intent intent = getIntent();

        questionnumber = findViewById(R.id.DispName);
        submitbutton = findViewById(R.id.button3);
        quitbutton = findViewById(R.id.buttonquit);
        tv = findViewById(R.id.tvque);

        radio_g = findViewById(R.id.answergrp);
        rb1 = findViewById(R.id.radiobutton1);
        rb2 = findViewById(R.id.radiobutton2);
        rb3 = findViewById(R.id.radiobutton3);
        rb4 = findViewById(R.id.radiobutton4);

        setQuestion(flag);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuestionActivity4.this, "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uans = findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();

                if (ansText.equals(answer[questionOrder[flag]])) {
                    correct++;
                    Toast.makeText(QuestionActivity4.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    wrong++;
                    Toast.makeText(QuestionActivity4.this, "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;
                if (score != null) {
                    score.setText("" + correct);

                    if (flag < question.length) {
                        setQuestion(flag);
                        questionnumber.setText((flag + 1) + "/" + question.length + " Question");
                    } else {
                        marks = correct;
                        Intent in = new Intent(QuestionActivity4.this, ResultActivity4.class);
                        startActivity(in);
                    }

                    radio_g.clearCheck();
                }
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ResultActivity4.class);
                startActivity(intent1);
            }
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
