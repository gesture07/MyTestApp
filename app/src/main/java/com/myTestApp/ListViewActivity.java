package com.myTestApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.TextureView;
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

//camera
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import com.google.common.util.concurrent.ListenableFuture;
import org.tensorflow.lite.Interpreter;
//import androidx.camera.view.TextureViewImplementation;
import android.view.TextureView;





import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutionException;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    //카메라
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button button;
    private TextureView viewFinder;

    private static final String MODEL_PATH = "model.tflite";
    private static final String LABEL_PATH = "labels.txt";
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128.0f;
    private static final int INPUT_SIZE = 224;
    private static final int NUM_CLASSES = 1000;

    private Interpreter interpreter;
    private String[] labels;

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
        //viewFinder = findViewById(R.id.viewFinder);
/*
        try {
            interpreter = new Interpreter(loadModelFile());
            labels = loadLabelNames();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 카메라 프리뷰 설정
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent interlink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://teachablemachine.withgoogle.com/models/DwhxQmpps/"));
                //dispatchTakePictureIntent();
                startActivity((interlink));
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


/*
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


        });*/

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
        list.add("바나나");
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




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = (String) adapterView.getItemAtPosition(i);
                //tvSelect.setText(data);
            }


        });


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

    private MappedByteBuffer loadModelFile() throws Exception {
        AssetFileDescriptor fileDescriptor = getAssets().openFd("model_unquant.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long length = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);
    }

    private String[] loadLabelNames() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(LABEL_PATH)));
        List<String> labels = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            labels.add(line);
        }
        reader.close();
        return labels.toArray(new String[0]);
    }

    private void bindCameraPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .setTargetResolution(new Size(INPUT_SIZE, INPUT_SIZE))
                .build();

        /*button = findViewById(R.id.btnPhoto);
        preview.setSurfaceProvider(button.createSurfaceProvider());
*/
        /*button = findViewById(R.id.btnPhoto);
        if (preview != null && previewView.getSurfaceProvider() != null) {
            preview.setSurfaceProvider(previewView.getSurfaceProvider());
        }*/
/*
        preview.setSurfaceProvider (
            ((TextureView) findViewById(R.id.viewFinder)).getSurfaceProvider()
            //button = findViewById(R.id.btnPhoto);
        );*/

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetResolution(new Size(INPUT_SIZE, INPUT_SIZE))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();
/*
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), image -> {
            Bitmap bitmap = imageToBitmap(image);
            if (bitmap != null) {
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, true);
                Bitmap rgbBitmap = resizedBitmap.copy(Bitmap.Config.ARGB_8888, true);

                // 입력 이미지 전처리
                ByteBuffer inputBuffer = preprocessImage(rgbBitmap);

                // 모델 실행
                float[][] output = new float[1][NUM_CLASSES];
                interpreter.run(inputBuffer, output);

                // 결과 해석
                float[] probabilities = output[0];
                int maxIndex = argmax(probabilities);
                String predictedLabel = labels[maxIndex];

                // 결과 표시
                runOnUiThread(() -> {
                    TextView resultTextView = findViewById(R.id.resultTextView);
                    resultTextView.setText(predictedLabel);
                });
            }
            image.close();
        });

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        try {
            cameraProvider.unbindAll();
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap imageToBitmap(Image image) {
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    private ByteBuffer preprocessImage(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        ByteBuffer inputBuffer = ByteBuffer.allocateDirect(4 * width * height * 3);
        inputBuffer.order(ByteOrder.nativeOrder());

        for (int pixelValue : pixels) {
            int r = (pixelValue >> 16) & 0xFF;
            int g = (pixelValue >> 8) & 0xFF;
            int b = pixelValue & 0xFF;

            inputBuffer.putFloat((r - IMAGE_MEAN) / IMAGE_STD);
            inputBuffer.putFloat((g - IMAGE_MEAN) / IMAGE_STD);
            inputBuffer.putFloat((b - IMAGE_MEAN) / IMAGE_STD);
        }

        return inputBuffer;
    }

    private int argmax(float[] array) {
        int maxIndex = -1;
        float maxValue = Float.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxIndex = i;
                maxValue = array[i];
            }
        }

        return maxIndex;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Do something with the imageBitmap, such as display it in an ImageView
        }
    }*/
/*
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
    }*/
/*
    private void updateListView() {
        ArrayAdapter<ListItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListItem);
        listView.setAdapter(adapter);
    }*/


}}
