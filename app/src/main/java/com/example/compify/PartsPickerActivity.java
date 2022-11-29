package com.example.compify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.compify.Interface.IFirebaseLoadDone;
import com.example.compify.Model.Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class PartsPickerActivity extends AppCompatActivity implements IFirebaseLoadDone, View.OnClickListener {
    AutoCompleteTextView firstModelACTV, secondModelACTV;
    Button compareButton;
    ImageView firstModelArrow, secondModelArrow;
    DatabaseReference compifyDataBase;
    IFirebaseLoadDone iFirebaseLoadDone;
    List<Data> dataList;
    List<String> model_list = new ArrayList<>();
    List<String> score1_list = new ArrayList<>();
    List<String> score2_list = new ArrayList<>();
    List<String> score3_list = new ArrayList<>();
    List<String> score4_list = new ArrayList<>();
    List<String> score5_list = new ArrayList<>();
    List<String> score6_list = new ArrayList<>();
    List<String> score7_list = new ArrayList<>();
    Bundle pathRetriever;
    String path;
    String firstModelName, firstModelScore1, firstModelScore2, firstModelScore3, firstModelScore4, firstModelScore5, firstModelScore6, firstModelScore7;
    String secondModelName, secondModelScore1, secondModelScore2, secondModelScore3, secondModelScore4, secondModelScore5, secondModelScore6, secondModelScore7;
    float translationX1 = 700f, translationX2 = -700f, translationY = 1000f;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts_picker);
        initActivity();
        doAnim();
        initDataBase();
        populateSpinner();
        getSpinnerItems();
    }

    private void initActivity() {
        firstModelACTV = findViewById(R.id.FirstModelACTV);
        secondModelACTV = findViewById(R.id.SecondModelACTV);

        firstModelArrow = findViewById(R.id.FirstModelArrow);
        secondModelArrow = findViewById(R.id.SecondModelArrow);

        compareButton = findViewById(R.id.COMPARE);

        firstModelACTV.setAlpha(0f);
        secondModelACTV.setAlpha(0f);

        firstModelArrow.setAlpha(0f);
        secondModelArrow.setAlpha(0f);

        compareButton.setAlpha(0f);

        firstModelACTV.setTranslationX(translationX1);
        secondModelACTV.setTranslationX(translationX2);

        compareButton.setTranslationY(translationY);

        firstModelArrow.setOnClickListener(this);
        secondModelArrow.setOnClickListener(this);
        compareButton.setOnClickListener(this);

        firstModelACTV.setClickable(false);
        secondModelACTV.setClickable(false);

        firstModelArrow.setClickable(false);
        secondModelArrow.setClickable(false);

        compareButton.setClickable(false);

        firstModelACTV.setThreshold(1);
        secondModelACTV.setThreshold(1);
    }

    private void doAnim() {
        firstModelACTV.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(1000).withStartAction(new Runnable(){
            public void run(){
                firstModelACTV.setClickable(false);
                firstModelArrow.setClickable(false);
            }
        }).withEndAction(new Runnable(){
            public void run(){
                firstModelArrow.setAlpha(1f);
                secondModelACTV.animate().translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(1000).withStartAction(new Runnable(){
                    public void run(){
                        secondModelACTV.setClickable(false);
                        secondModelArrow.setClickable(false);
                    }
                }).withEndAction(new Runnable(){
                    public void run(){
                        secondModelArrow.setAlpha(1f);
                        compareButton.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(1500).withStartAction(new Runnable(){
                            public void run(){
                                compareButton.setClickable(false);
                            }
                        }).withEndAction(new Runnable(){
                            public void run(){
                                firstModelACTV.setClickable(true);
                                firstModelArrow.setClickable(true);
                                secondModelACTV.setClickable(true);
                                secondModelArrow.setClickable(true);
                                compareButton.setClickable(true);
                            }
                        }).start();
                    }
                }).start();
            }
        }).start();
    }

    public void initDataBase() {
        pathRetriever = getIntent().getExtras();
        path = pathRetriever != null ? pathRetriever.getString("path") : null;
        compifyDataBase = FirebaseDatabase.getInstance().getReference(String.valueOf(path));
        iFirebaseLoadDone = this;
    }

    public void populateSpinner() {
        compifyDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Data> data = new ArrayList<>();
                for (DataSnapshot partsSnapsShot:dataSnapshot.getChildren()) {
                    data.add(partsSnapsShot.getValue(Data.class));
                }
                iFirebaseLoadDone.onFirebaseLoadSuccess(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Data> dataList) {
        this.dataList = dataList;
        for (Data data:dataList) {
            model_list.add(data.getModel());
            score1_list.add(data.getScore1());
            score2_list.add(data.getScore2());
            score3_list.add(data.getScore3());
            score4_list.add(data.getScore4());
            score5_list.add(data.getScore5());
            score6_list.add(data.getScore6());
            score7_list.add(data.getScore7());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, model_list);
            firstModelACTV.setAdapter(adapter);
            secondModelACTV.setAdapter(adapter);
        }
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast toast = Toast.makeText(PartsPickerActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void getSpinnerItems() {
        firstModelACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = model_list.indexOf(firstModelACTV.getText().toString());
                firstModelName = model_list.get(index);
                firstModelScore1 = score1_list.get(index);
                firstModelScore2 = score2_list.get(index);
                firstModelScore3 = score3_list.get(index);
                firstModelScore4 = score4_list.get(index);
                firstModelScore5 = score5_list.get(index);
                firstModelScore6 = score6_list.get(index);
                firstModelScore7 = score7_list.get(index);
            }
        });

        secondModelACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = model_list.indexOf(secondModelACTV.getText().toString());
                secondModelName = model_list.get(index);
                secondModelScore1 = score1_list.get(index);
                secondModelScore2 = score2_list.get(index);
                secondModelScore3 = score3_list.get(index);
                secondModelScore4 = score4_list.get(index);
                secondModelScore5 = score5_list.get(index);
                secondModelScore6 = score6_list.get(index);
                secondModelScore7 = score7_list.get(index);
            }
        });
    }

    private void firstModelArrowPressed() {
        try {
            firstModelACTV.showDropDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void secondModelArrowPressed() {
        try {
            secondModelACTV.showDropDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void compareButtonPressed() {
        String check1 = firstModelACTV.getText().toString(), check2 = secondModelACTV.getText().toString();
        int flag1, flag2;
        flag1 = model_list.contains(check1) ? 1 : 0;
        flag2 = model_list.contains(check2) ? 1 : 0;
        if (flag1 == 1 && flag2 == 1) {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(PartsPickerActivity.this,R.raw.menu_button_sound);
                mediaPlayer.start();
                Intent intent = new Intent(PartsPickerActivity.this, PieChartActivity.class);
                intent.putExtra("firstModelName", firstModelName);
                intent.putExtra("secondModelName", secondModelName);
                intent.putExtra("firstModelScore1", firstModelScore1);
                intent.putExtra("firstModelScore2", firstModelScore2);
                intent.putExtra("firstModelScore3", firstModelScore3);
                intent.putExtra("firstModelScore4", firstModelScore4);
                intent.putExtra("firstModelScore5", firstModelScore5);
                intent.putExtra("firstModelScore6", firstModelScore6);
                intent.putExtra("firstModelScore7", firstModelScore7);
                intent.putExtra("secondModelScore1", secondModelScore1);
                intent.putExtra("secondModelScore2", secondModelScore2);
                intent.putExtra("secondModelScore3", secondModelScore3);
                intent.putExtra("secondModelScore4", secondModelScore4);
                intent.putExtra("secondModelScore5", secondModelScore5);
                intent.putExtra("secondModelScore6", secondModelScore6);
                intent.putExtra("secondModelScore7", secondModelScore7);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast toast = Toast.makeText(PartsPickerActivity.this, "CHOOSE MODEL PROPERLY !!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.FirstModelArrow:
                firstModelArrowPressed();
                break;
            case R.id.SecondModelArrow:
                secondModelArrowPressed();
                break;
            case R.id.COMPARE:
                compareButtonPressed();
                break;
        }
    }
}