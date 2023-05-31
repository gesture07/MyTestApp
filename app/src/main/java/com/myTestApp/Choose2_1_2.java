package com.myTestApp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Choose2_1_2 extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose2_1_2);

        //adapter생성
        //ListViewAdapter adapter = new ListViewAdapter();

        //리스트뷰 참조 및 adapter달기
        listView = (ListView) findViewById(R.id.listView);
        //listView.setAdapter(adapter);

        /*adapter.addItem("사과", "빨간색");
        adapter.addItem("딸기", "빨간색");
        adapter.addItem("바나나", "노란색");
        adapter.addItem("수박", "초록색");*/
    }
}
