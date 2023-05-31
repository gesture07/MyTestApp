package com.myTestApp;

//import static androidx.core.content.ContextCompat.Api16Impl.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.database.core.view.View;

public class Training2_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training2_1);


        //학습하기 팝업 선택지2_1_1 버튼 선택시 액티비티 전환
        Button popup2_1_1 = (Button) findViewById(R.id.popup2_1_1);
        popup2_1_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Popup2_1_1.class);
                startActivity(intent);
            }
        });
    }
}


