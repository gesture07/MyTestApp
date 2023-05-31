package com.myTestApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //개발자 정보 버튼 클릭시 액티비티 전환
        Button developer_info_btn = (Button) findViewById(R.id.button);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
            }
        });


    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.1 사전 클릭 시 액티비티 전환
        Button dictionary1_1 = (Button) findViewById(R.id.dictionary1_1);
        dictionary1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
            }
        });

        //2.1 교육,오답 클릭 시 액티비티 전환
        Button training2_1 = (Button) findViewById(R.id.training2_1);
        training2_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Training2_1.class);
                startActivity(intent);
            }
        });

        //3.1 퀴즈 클릭 시 액티비티 전환
        Button quiz3_1 = (Button) findViewById(R.id.quizpopup3_1_1);
        quiz3_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizPopup3_1_1.class);
                startActivity(intent);
            }
        });

    }
}