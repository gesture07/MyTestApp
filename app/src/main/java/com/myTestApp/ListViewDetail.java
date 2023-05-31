package com.myTestApp;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ListViewDetail extends AppCompatActivity {
   private ImageView imageView;
   private TextView titleTextView;
   private TextView desTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewdetail);

        imageView = findViewById(R.id.picture);
        titleTextView = findViewById(R.id.word);
        desTextView = findViewById(R.id.des);

        //이전 화면에서 전달받은 데이터를 가져옵니다.
        ListItem listItem = getIntent().getParcelableExtra("listItem");
        if (listItem != null){
            //데이터를 사용하여 다음 화면을 업데이트 합니다.
            Glide.with(this).load(listItem.getImgUrl()).into(imageView);
            titleTextView.setText(listItem.getWord());
            desTextView.setText(listItem.getDes());
        }
    }
}

