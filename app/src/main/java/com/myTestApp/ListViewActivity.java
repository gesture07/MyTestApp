package com.myTestApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;





import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    //카메라
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button button;

    //파이어베이스
    private DatabaseReference databaseRef;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    private List<ListItem> ListItem;


    protected void onCreate(Bundle savedInstancesState) {

        super.onCreate(savedInstancesState);
        setContentView(R.layout.listview);

        //카메라 기능
        button = findViewById(R.id.btnPhoto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        //firebaseApp초기화 및 연결 설정 (apllication믈래스 또는 액티비티의 oncreate()메서드에서 호출)
        FirebaseApp.initializeApp(this);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        databaseRef = FirebaseDatabase.getInstance().getReference("photos");

        ListItem = new ArrayList<>();



        // Firebase Realtime Database에서 데이터를 가져올 위치를 지정합니다.
        databaseRef = FirebaseDatabase.getInstance().getReference("kids");
        //realtime database에서 데이터 변경을 감지하기 위해 사용하는 인터페이스
        //ValueEventListener valueEventListener = new ValueEventListener() {
        //데이터를 한 번만 가져오고 데이터 변경사항을 실시간으로 감지하지 않습니다.
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
        // ValueEventListener를 등록하여 데이터 변경을 감지하고 처리합니다.
        //databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                ListItem.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //String value = snapshot.getValue(String.class);
                    //Log.d("FirebaseData", "Value: " + value);
                    ListItem listItem = snapshot.getValue(ListItem.class);
                    // 각각의 데이터 스냅샷에 대해 반복합니다.
                    // 스냅샷에서 데이터를 가져와 처리합니다.
                    // 예를 들어, "name"과 "age"라는 필드가 있는 경우:
                    String tangerine = snapshot.child("과일").child("귤").child("tangerine").getValue(String.class);
                    //String tangerine = snapshot.child("과일").child("귤").getValue(String.class);
                    //String strawberry = snapshot.child("과일").child("딸기").getValue(String.class);

                    //String imageUrl = snapshot.child("").getValue(String.class);
                    //String title = snapshot.child("title").getValue(String.class);
                    //String description = snapshot.child("description").getValue(String.class);

                    // 가져온 데이터를 처리합니다.

                    ListItem.add(listItem);
                    list.add(tangerine);
                }
                adapter.notifyDataSetChanged();
                updateListView();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 에러 처리
                Log.e("ListViewActivity", "Database Error: " + databaseError.getMessage());

            }


        });

        //databaseRef.addValueEventListener(valueEventListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem selectedListItem = ListItem.get(position);
                String selectedItem = (String) parent.getItemAtPosition(position);
                // 다음 화면으로 이동하는 인텐트 생성 및 시작
                Intent intent = new Intent(ListViewActivity.this, ListViewDetail.class);
                intent.putExtra("selectedItem", selectedItem);
                //intent.putExtra("listItem", selectedListItem);
                startActivity(intent);
            }
        });






        //final TextView tvSelect = findViewById(R.id.tv_select);


        //list.add(tangerine);
        list.add("딸기");
        /*list.add("바나나");
        list.add("사과");
        list.add("수박");
        list.add("미국");
        list.add("브라질");
        list.add("영국");
        list.add("일본");
        list.add("중국");
        list.add("개");
        list.add("고양이");
        list.add("코끼리");
        list.add("안경");
        list.add("양말");
        list.add("책상");
        list.add("티비");
        list.add("휴지");
        list.add("장미");
        list.add("해바라기");
*/



        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String data = (String) adapterView.getItemAtPosition(i);
                //tvSelect.setText(data);
            }


        });*/


        /*databaseReference.child("Firebase_Group_List").child(Gname).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Firebase_Group_List group = dataSnapshot.getValue(Firebase_Group_List.class);

                //각각의 값 받아오기 get어쩌구 함수들은 Together_group_list.class에서 지정한것
                gintro = group.getGintro();
                goaltime = group.getGoaltime();
                gdate = group.getGoalday();

                //텍스트뷰에 받아온 문자열 대입하기
                goaltime_tv.setText(goaltime);
                gintro_tv.setText(gintro);
                gdate_tv.setText(gdate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });*/

    }

    //카메라
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Do something with the imageBitmap, such as display it in an ImageView
        }
    }

    //탐색뷰
    private void searchData(){
        SearchView searchView = findViewById(R.id.sv_list);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                ArrayList<ListItem> filterData = new ArrayList<>();
                for (int i = 0; i < ListItem.size(); i++) {
                    ListItem data = ListItem.get(i);
                    if (data.getWord().toLowerCase().contains(newText.toLowerCase())) {
                        filterData.add(data);
                    }
                }
                //DataAdapter adapter = new DataAdapter(getApplicationContext(), 0, filterData);
                //listView.setAdapter(adapter);
                return false;
            }

        });
    }

    private void updateListView() {
        ArrayAdapter<ListItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListItem);
        listView.setAdapter(adapter);
    }


}
