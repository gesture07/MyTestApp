package com.myTestApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class Letter_Picture extends AppCompatActivity {

    ImageButton picture1, picture2;
    TextView word;
    private Database mDatabase;
    private int mCorrectAnswerIndex;
    //퀴즈 전체 문제 수
    private int mQuizCount;
    //퀴즈 정답 수 체크
    int correctAnswers = 0;
    private ArrayList<Integer> mRemainingQuestionIndexes;
    private void setupQuiz() {
        //무작위 질문 생성
        Random random = new Random();
        int questionIndex = random.nextInt(mDatabase.flags_answers.length);

        // 질문 텍스트 설정
        word.setText(mDatabase.flags_answers[questionIndex]);

        // 정답 인덱스 설정
        mCorrectAnswerIndex = random.nextInt(2);

        // 이미지 설정
        if (mCorrectAnswerIndex == 0) {
            picture1.setImageResource(mDatabase.flags[questionIndex]);

            // 무작위 오답 지수 생성
            int incorrectIndex = random.nextInt(mDatabase.flags_answers.length);
            while (incorrectIndex == questionIndex) {
                incorrectIndex = random.nextInt(mDatabase.flags_answers.length);
            }
            picture2.setImageResource(mDatabase.flags[incorrectIndex]);
        } else {
            picture2.setImageResource(mDatabase.flags[questionIndex]);

            // 무작위 오답 지수 생성
            int incorrectIndex = random.nextInt(mDatabase.flags_answers.length);
            while (incorrectIndex == questionIndex) {
                incorrectIndex = random.nextInt(mDatabase.flags_answers.length);
            }
            picture1.setImageResource(mDatabase.flags[incorrectIndex]);
        }
    }

    public void checkAnswer(View view) {
        int selectedAnswerIndex = 0;
        switch (view.getId()) {
            case R.id.picture1:
                selectedAnswerIndex = 0;
                break;
            case R.id.picture2:
                selectedAnswerIndex = 1;
                break;
            default:
                break;
        }
        mQuizCount++;

        if (selectedAnswerIndex == mCorrectAnswerIndex) {
            Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show();
            correctAnswers++;
            if (mQuizCount == 5) {
                showResult(correctAnswers, mQuizCount);
            } else {
                setupQuiz();
            }
        } else {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show();
            if (mQuizCount == 5) {
                showResult(correctAnswers, mQuizCount);
            } else {
                setupQuiz();
            }
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.letter_picture);
        mDatabase = new Database();
        picture1 = findViewById(R.id.picture1);
        picture2 = findViewById(R.id.picture2);

        word = findViewById(R.id.word);
        mQuizCount = 0;

        setupQuiz();

    }
    public void showResult(int correctAnswers, int totalQuestions){
        Intent intent = new Intent(this, Letter_Picture_Result.class);
        intent.putExtra("correctAnswers", correctAnswers);
        intent.putExtra("totalQuestions", totalQuestions);
        startActivity(intent);
        finish();
    }
}
