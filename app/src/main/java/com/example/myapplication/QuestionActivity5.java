package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionActivity5 extends AppCompatActivity {

    private List<Question> questionList = new ArrayList<>(); // To hold questions from Firebase
    private int[] questionOrder; // Array to store shuffled question indices
    private int flag = 0;
    public static int marks = 0, correct = 0, wrong = 0;

    private TextView tv, questionnumber, score;
    private Button submitbutton, quitbutton;
    private RadioGroup radio_g;
    private RadioButton rb1, rb2, rb3, rb4;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Quizzes").child("Activity5");

        // Initialize UI elements
        score = findViewById(R.id.textview4);
        questionnumber = findViewById(R.id.DispName);
        submitbutton = findViewById(R.id.button3);
        quitbutton = findViewById(R.id.buttonquit);
        tv = findViewById(R.id.tvque);
        radio_g = findViewById(R.id.answergrp);
        rb1 = findViewById(R.id.radiobutton1);
        rb2 = findViewById(R.id.radiobutton2);
        rb3 = findViewById(R.id.radiobutton3);
        rb4 = findViewById(R.id.radiobutton4);

        // Load questions from Firebase
        loadQuestionsFromFirebase();

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuestionActivity5.this, "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uans = findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();

                // Check if answer is correct
                if (ansText.equals(questionList.get(questionOrder[flag]).getAnswer())) {
                    correct++;
                    Toast.makeText(QuestionActivity5.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    wrong++;
                    Toast.makeText(QuestionActivity5.this, "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;
                if (flag < questionList.size()) {
                    setQuestion(flag);
                    questionnumber.setText((flag + 1) + "/" + questionList.size() + " Question");
                } else {
                    marks = correct;
                    Intent in = new Intent(QuestionActivity5.this, ResultActivity5.class);
                    startActivity(in);
                }
                score.setText(String.valueOf(correct));
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent1);
            }
        });
    }

    // Load questions from Firebase
    private void loadQuestionsFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionList.clear(); // Clear existing questions
                for (DataSnapshot quizSnapshot : dataSnapshot.getChildren()) {
                    String questionText = quizSnapshot.child("question").getValue(String.class);
                    List<String> options = new ArrayList<>();
                    for (DataSnapshot optionSnapshot : quizSnapshot.child("options").getChildren()) {
                        options.add(optionSnapshot.getValue(String.class));
                    }
                    String answer = quizSnapshot.child("answer").getValue(String.class);

                    // Create a new Question object and add it to the list
                    questionList.add(new Question(questionText, options, answer));
                }

                // Shuffle questions once data is loaded
                initializeShuffledQuestions();
                setQuestion(flag);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuestionActivity5.this, "Failed to load questions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Initialize and shuffle the order of questions
    private void initializeShuffledQuestions() {
        questionOrder = new int[questionList.size()];
        for (int i = 0; i < questionList.size(); i++) {
            questionOrder[i] = i;
        }

        // Fisher-Yates shuffle for question indices
        Random random = new Random();
        for (int i = questionList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = questionOrder[i];
            questionOrder[i] = questionOrder[j];
            questionOrder[j] = temp;
        }
    }

    // Set the current question and its options based on shuffled order
    private void setQuestion(int index) {
        int questionIndex = questionOrder[index];
        Question currentQuestion = questionList.get(questionIndex);

        tv.setText(currentQuestion.getQuestionText());
        rb1.setText(currentQuestion.getOptions().get(0));
        rb2.setText(currentQuestion.getOptions().get(1));
        rb3.setText(currentQuestion.getOptions().get(2));
        rb4.setText(currentQuestion.getOptions().get(3));
    }
}

// Define a Question class to hold question data
class Question5 {
    private String questionText;
    private List<String> options;
    private String answer;

    public Question5(String questionText, List<String> options, String answer) {
        this.questionText = questionText;
        this.options = options;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }
}
