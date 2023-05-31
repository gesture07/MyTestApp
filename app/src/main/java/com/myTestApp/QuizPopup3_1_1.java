package com.myTestApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuizPopup3_1_1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizpopup3_1_1);

        Button Pic_wo = (Button) findViewById(R.id.Pic_Wo);
        Pic_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Picture_Word.class);
                startActivity(intent);
            }
        });

        /*Button Sou_Pic = (Button) findViewById(R.id.Sou_Pic);
        Sou_Pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Sound_Picture.class);
                startActivity(intent);
            }
        });*/

        Button Let_Pic = (Button) findViewById(R.id.Let_Pic);
        Let_Pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Letter_Picture.class);
                startActivity(intent);
            }
        });
    }
}
