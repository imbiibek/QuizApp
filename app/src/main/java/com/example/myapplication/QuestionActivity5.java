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

public class QuestionActivity5 extends AppCompatActivity {



    String question[] = {
            "What is required in each C program?",
            "Which of the following comment is correct when a macro definition includes arguments?",
            "What is a lint?",
            "What is the output of this statement \"printf(\"%d\", (a++))\"?",
            "If abcdefg is the input, the output will be"

    };
    String answer[] = {

            "The program must have at least one function.",
            " The opening parenthesis should immediately follow the macro name.",
            "Analyzing tool",
            "The current value of \"a\".",
            "efg"


    };
    String opt[] = {

            "The program must have at least one function.", "The program does not require any function.", "Input data", "Output data",
            " The opening parenthesis should immediately follow the macro name.", "There should be at least one blank between the macro name and the opening parenthesis", "There should be only one blank between the macro name and the opening parenthesis.", "All the above comments are correct.",
            "Analyzing tool", "C compiler", "Interactive debugger", "C interpreter",
            "The current value of \"a\".", "The value of (a + 1)", "Error message", "Garbage",
            "efg", "abcd", "abc", "Garbage"



    };

    int flag = 0;

    public static int marks=0, correct=0, wrong=0;

    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    private TextView questionnumber;

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

        final TextView score = (TextView) findViewById(R.id.textview4);
        TextView textView = (TextView) findViewById(R.id.DispName);
        Intent intent = getIntent();

        questionnumber = findViewById(R.id.DispName);
        submitbutton = (Button) findViewById(R.id.button3);
        quitbutton = (Button) findViewById(R.id.buttonquit);
        tv = (TextView) findViewById(R.id.tvque);

        radio_g = (RadioGroup) findViewById(R.id.answergrp);
        rb1 = (RadioButton) findViewById(R.id.radiobutton);
        rb2 = (RadioButton) findViewById(R.id.radiobutton);
        rb3 = (RadioButton) findViewById(R.id.radiobutton);
        rb4 = (RadioButton) findViewById(R.id.radiobutton);

        tv.setText(question[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radio_g.getCheckedRadioButtonId()== -1)

                {
                    Toast.makeText(QuestionActivity5.this, "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uans = (RadioButton)  findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();

                if (ansText.equals(answer[flag]))
                {
                    correct++;
                    Toast.makeText(QuestionActivity5.this, "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(QuestionActivity5.this, "wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;
                if (score!=null)
                {
                    score.setText(""+correct);

                    if(flag<question.length) {
                        tv.setText(question[flag]);
                        rb1.setText(opt[flag * 4]);
                        rb2.setText(opt[flag * 4 + 1]);
                        rb3.setText(opt[flag * 4 + 2]);
                        rb4.setText(opt[flag * 4 + 3]);
                        questionnumber.setText(flag + "/" + question.length + "Question");

                    }
                    else {
                        marks=correct;
                        Intent in = new Intent(QuestionActivity5.this,ResultActivity5.class);
                        startActivity(in);

                    }

                    radio_g.clearCheck();

                }


            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),ResultActivity5.class);
                startActivity(intent1);
            }
        });



    }
}