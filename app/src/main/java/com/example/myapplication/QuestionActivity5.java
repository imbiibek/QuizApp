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

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class QuestionActivity5 extends AppCompatActivity {

    String[] questions = {
            "What is Kotlin?",
            "Which platform does Kotlin primarily target?",
            "What paradigm(s) does the Kotlin programming language follow?",
            "How do you define a variable in Kotlin that cannot be reassigned?",
            "How do you declare a nullable variable in Kotlin?"
    };

    String[] answers = {
            "A statically-typed programming language for the JVM, Android, and browser",
            "JVM (Java Virtual Machine) Bytecode",
            "Both Object-Oriented and Functional",
            "val",
            "var name: String?"
    };

    String[][] options = {
            {
                    "A statically-typed programming language for the JVM, Android, and browser",
                    "JVM (Java Virtual Machine) Bytecode",
                    "Input data",
                    "Output data"
            },
            {
                    "The opening parenthesis should immediately follow the macro name.",
                    "There should be at least one blank between the macro name and the opening parenthesis",
                    "There should be only one blank between the macro name and the opening parenthesis.",
                    "All the above comments are correct."
            },
            {
                    "Both Object-Oriented and Functional",
                    "Only Object-Oriented",
                    "Procedural",
                    "Only Functional"
            },
            {
                    "var", "val", "const", "final"
            },
            {
                    "var name: String?",
                    "var name: String",
                    "var name: String",
                    "String name = null"
            }
    };

    int flag = 0;
    public static int marks = 0, correct = 0, wrong = 0;

    TextView tv;
    Button submitButton, quitButton;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    private TextView questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionNumber = findViewById(R.id.DispName);
        submitButton = findViewById(R.id.button3);
        quitButton = findViewById(R.id.buttonquit);
        tv = findViewById(R.id.tvque);

        radioGroup = findViewById(R.id.answergrp);
        rb1 = findViewById(R.id.radiobutton1);
        rb2 = findViewById(R.id.radiobutton2);
        rb3 = findViewById(R.id.radiobutton3);
        rb4 = findViewById(R.id.radiobutton4);

        // Shuffle questions and options
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        // Initialize the first question and options
        loadQuestion(indices.get(flag));

        submitButton.setOnClickListener(v -> {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(QuestionActivity5.this, "Please select one choice", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton userAnswer = findViewById(radioGroup.getCheckedRadioButtonId());
            String ansText = userAnswer.getText().toString();

            if (ansText.equals(answers[indices.get(flag)])) {
                correct++;
                Toast.makeText(QuestionActivity5.this, "Correct", Toast.LENGTH_SHORT).show();
            } else {
                wrong++;
                Toast.makeText(QuestionActivity5.this, "Wrong", Toast.LENGTH_SHORT).show();
            }

            flag++;
            if (flag < questions.length) {
                loadQuestion(indices.get(flag));
            } else {
                marks = correct;
                Intent intent = new Intent(QuestionActivity5.this, ResultActivity5.class);
                startActivity(intent);
            }

            radioGroup.clearCheck();
        });

        quitButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), ResultActivity5.class);
            startActivity(intent1);
        });
    }

    private void loadQuestion(int index) {
        tv.setText(questions[index]);
        rb1.setText(options[index][0]);
        rb2.setText(options[index][1]);
        rb3.setText(options[index][2]);
        rb4.setText(options[index][3]);
        questionNumber.setText((flag + 1) + "/" + questions.length + " Question");
    }
}
