package com.iyer.shailesh.dbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.View.GONE;

public class RiddlesActivity extends AppCompatActivity {

    ImageView dialogImage;
    int set,ques;
    Dialog dialog;
    String question,answer;
    TextView quesView , questionHeading , riddleHeading , riddleHint;
    EditText answerField;
    Button next , captureMarker , checkAnswer , finishSet , showImage;
    ArrayList<Riddles> riddlesList;
    sqlitehelper sqlite;
    Toolbar toolbar;
    int images[] = {
            0,
            0,
            R.drawable.three,
            R.drawable.four,
            0,
            0,
            0,
            0,
            R.drawable.five
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddles);

        sqlite = new sqlitehelper(this);

        set = getIntent().getIntExtra("set",0);
        ques = getIntent().getIntExtra("ques",0);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar_one);
        toolbar.setTitle("Set "+set);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        riddlesList = sqlite.initialiseRiddles(set);
        riddlesList = sqlite.initialiseDetails(riddlesList);

        question = riddlesList.get(ques-1).getQUESTION();
        answer = riddlesList.get(ques-1).getANSWER();

        //Initializing the views
        riddleHeading = (TextView) findViewById(R.id.riddleHeading);
        quesView = (TextView) findViewById(R.id.questionText);
        questionHeading = (TextView) findViewById(R.id.questionHeading);
        answerField = (EditText) findViewById(R.id.answerField);
        next = (Button) findViewById(R.id.nextQues);
        captureMarker = (Button) findViewById(R.id.captureMarker);
        checkAnswer = (Button) findViewById(R.id.checkAnswer);
        finishSet = (Button) findViewById(R.id.finishSet);
        riddleHint = (TextView) findViewById(R.id.riddleHint);
        showImage = (Button) findViewById(R.id.showImage);

        riddleHeading.setText("Riddle Number "+ques);
        if(!riddlesList.get(ques-1).getIMAGE().equalsIgnoreCase("No")) {
            showImage.setVisibility(View.VISIBLE);
        }
        else {
            showImage.setVisibility(GONE);
        }
        quesView.setText(question);
        riddleHint.setVisibility(View.VISIBLE);

        if(riddlesList.get(ques-1).getStatus() == 2 && ques == 4) {
            next.setVisibility(GONE);
            checkAnswer.setVisibility(GONE);
            captureMarker.setVisibility(View.VISIBLE);
            captureMarker.setEnabled(false);
            answerField.setText(answer);
            answerField.setEnabled(false);
            finishSet.setVisibility(View.VISIBLE);
            riddleHint.setText("All Riddles Solved!");
        }

        else if(riddlesList.get(ques-1).getStatus() == 0) {
            checkAnswer.setVisibility(View.VISIBLE);
            captureMarker.setVisibility(GONE);
            next.setEnabled(false);
            riddleHint.setText("Solve the riddle!");
        }
        else if(riddlesList.get(ques-1).getStatus() == 1) {
            checkAnswer.setVisibility(GONE);
            answerField.setText(answer);
            answerField.setEnabled(false);
            captureMarker.setVisibility(View.VISIBLE);
            next.setEnabled(false);
            riddleHint.setText("Find the marker at " + answer + "!");
        }
        else if(riddlesList.get(ques-1).getStatus() == 2) {
            checkAnswer.setVisibility(GONE);
            captureMarker.setEnabled(false);
            captureMarker.setVisibility(View.VISIBLE);
            answerField.setText(answer);
            answerField.setEnabled(false);
            next.setEnabled(true);
            riddleHint.setText("Riddle Solved!");
        }

        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.equalsIgnoreCase(answerField.getText().toString())) {
                    riddlesList.get(ques-1).setStatus(1);
                    sqlite.update_Status(ques-1);
                    Toast.makeText(RiddlesActivity.this, "RightAnswer", Toast.LENGTH_SHORT).show();
                    checkAnswer.setVisibility(GONE);
                    answerField.setEnabled(false);
                    captureMarker.setVisibility(View.VISIBLE);
                    riddleHint.setText("Find the marker at " + answer + "!");
                }
                else {
                    Toast.makeText(RiddlesActivity.this, "WrongAnswer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        captureMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(RiddlesActivity.this,ArActivity.class);
                String location = answer;
                extras.putString("c",location);
                extras.putString("d",Integer.toString(ques-1));
                intent.putExtra("e",extras);
                startActivityForResult(intent,1);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ques != 4)
                {
                    ques++;
                    update_ui();
                }
            }
        });

        finishSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlite.finishGame();
                startActivity(new Intent(RiddlesActivity.this,MainActivity.class));
                finish();
            }
        });

        showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("IMAGEAPPEAR","IMAGE");
                showImageDialog();
            }
        });
    }

    private void showImageDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_image);

        dialogImage = (ImageView) dialog.findViewById(R.id.hint_image);
        dialogImage.setImageResource(images[set-1]);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    public void update_ui() {
        question = riddlesList.get(ques-1).getQUESTION();
        answer = riddlesList.get(ques-1).getANSWER();
        riddleHeading.setText("Riddle Number "+ques);
        answerField.setText("");
        answerField.setEnabled(true);
        captureMarker.setEnabled(true);
        captureMarker.setVisibility(GONE);
        checkAnswer.setVisibility(View.VISIBLE);
        next.setEnabled(false);
        quesView.setText(question);
        riddleHint.setText("Solve the riddle!");

        if(!riddlesList.get(ques-1).getIMAGE().equalsIgnoreCase("No")) {
            showImage.setVisibility(View.VISIBLE);
        }
        else {
            showImage.setVisibility(GONE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            int position = data.getIntExtra("position", 0);
            Log.e("Activity Result", "setting status");
            if (riddlesList != null && position != 3) {
                Log.e("Activity Result", "inside if loop setting status");
                riddlesList.get(position).setStatus(2);
                captureMarker.setEnabled(false);
                next.setEnabled(true);
                riddleHint.setText("Riddle Solved!");
            }
            else if(riddlesList != null) {
                riddlesList.get(position).setStatus(2);
                next.setVisibility(GONE);
                checkAnswer.setVisibility(GONE);
                captureMarker.setVisibility(View.VISIBLE);
                captureMarker.setEnabled(false);
                answerField.setText(answer);
                answerField.setEnabled(false);
                finishSet.setVisibility(View.VISIBLE);
                riddleHint.setText("All Riddles Solved!");
            }

        } else if (requestCode == 1 && resultCode == Activity.RESULT_CANCELED) {
            Log.e("Activity Result", "not setting status");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}