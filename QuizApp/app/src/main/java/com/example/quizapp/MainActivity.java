package com.example.quizapp;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextview;
    TextView totalQuestionTextview;
    Button ansA, ansB, ansC, ansD;
    Button btn_submit;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextview = findViewById(R.id.total_question);
        questionTextview = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        btn_submit = findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        totalQuestionTextview.setText("Total question: " +totalQuestion);

        loadNewQuestion();
    }

    private void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionTextview.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer = "";
    }

    private void finishQuiz(){
        String passStatus;
        if(score >= totalQuestion*0.6){
            passStatus = "Passed";
        }else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score is "+ score + " out of " + totalQuestion)
                .setPositiveButton("Restart", ((dialog, i) -> restartQuiz() ))
                .setCancelable(false)
                .show();
    }
    private void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){
    ansA.setBackgroundColor(Color.WHITE);
    ansB.setBackgroundColor(Color.WHITE);
    ansC.setBackgroundColor(Color.WHITE);
    ansD.setBackgroundColor(Color.WHITE);

    Button clickButton = (Button) view;

    if (clickButton.getId() == R.id.btn_submit){
        if (!selectedAnswer.isEmpty()){
            if (selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                score++;
            }else {
                clickButton.setBackgroundColor(Color.MAGENTA);
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }else {

        }
    }else {
        selectedAnswer = clickButton.getText().toString();
        clickButton.setBackgroundColor(Color.YELLOW);
    }
    }
}