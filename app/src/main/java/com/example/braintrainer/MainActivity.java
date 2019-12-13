package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView q_tv,ctd_tv,score_tv,ans_tv;
    GridLayout gl;
    int correct_ans_pos;
    int score = 0;
    int num_of_ques = 0;
    ConstraintLayout cl;
    CountDownTimer cdt;
    Button start_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_bt = findViewById(R.id.button);
        start_bt.setVisibility(View.VISIBLE);
        q_tv = findViewById(R.id.textView);
        ctd_tv = findViewById(R.id.textView3);
        score_tv = findViewById(R.id.textView2);
        ans_tv = findViewById(R.id.textView4);
        gl =  findViewById(R.id.gridLayout);
        cl = findViewById(R.id.Constraint_layout);
        cl.setVisibility(View.INVISIBLE);



    }

    public void start(View view)
    {
        start_bt.setVisibility(View.INVISIBLE);
        cl.setVisibility(View.VISIBLE);
        ans_tv.setVisibility(View.INVISIBLE);
        get_Question();
        ctd_tv.setText("30s");
        cdt = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ctd_tv.setText(Integer.toString((int) millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                ans_tv.setText("DONE");
                cl.setVisibility(View.INVISIBLE);
                start_bt.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void get_Question()
    {

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        q_tv.setText(Integer.toString(a)+" + "+Integer.toString(b));



        correct_ans_pos = rand.nextInt(4);

        ArrayList<String> al = new ArrayList<String>();
        for( int i = 0; i <= 3; i++ )
        {
            if(i == correct_ans_pos)
            {
                al.add(Integer.toString(a + b));
            }
            else
            {
                int c = rand.nextInt(41);
                if(c == (a+b))
                {
                    while(c == (a+b))
                    {
                        c = rand.nextInt(41);
                    }
                }
                al.add(Integer.toString(c));
            }
        }

        for(int i=0; i<gl.getChildCount(); i++) {
            Button child = (Button) gl.getChildAt(i);
            String str = al.get(i);
            child.setText(str);
            // do stuff with child view
        }
        al.clear();


    }

    public void pressed(View view)
    {
        ans_tv.setVisibility(View.VISIBLE);
        Button bt = (Button) view;
        int get_tag = Integer.parseInt(bt.getTag().toString());
        if ( get_tag == correct_ans_pos)
        {
            ans_tv.setText("Correct");
            score++;
        }
        else
        {
            ans_tv.setText("Wrong :(");
        }
        num_of_ques++;
        updateScore();
        get_Question();

    }

    public void updateScore()
    {
        score_tv.setText(Integer.toString(score)+"/"+Integer.toString(num_of_ques));
    }


}
