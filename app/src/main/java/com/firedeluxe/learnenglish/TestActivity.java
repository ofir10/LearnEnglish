package com.firedeluxe.learnenglish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class TestActivity extends Activity implements View.OnClickListener {
    TextView original_word, textView_show_answers, titleCountQ;
    EditText answer;
    Button send, option1, option2, option3, option4;
    LinearLayout options;
    ArrayList<Word> arr;
    int counter = 0;
    int correct = 0;
    Context context;
    String[] optionsarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        context = TestActivity.this;
        arr = (ArrayList<Word>) getIntent().getSerializableExtra("arr");
        titleCountQ = (TextView) findViewById(R.id.titleCountQ);
        titleCountQ.setText(String.valueOf(counter +"/" + arr.size()));
        original_word = (TextView) findViewById(R.id.textView_word);
        answer = (EditText) findViewById(R.id.editText_answer);
        send = (Button) findViewById(R.id.button_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                original_word.setText(arr.get(counter).getTranslate());
                original_word.setTextColor(Color.parseColor("#0D4C01"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        original_word.setTextColor(Color.parseColor("#000000"));
                        counter++;
                        titleCountQ.setText(String.valueOf(counter +"/" + arr.size()));
                        if(counter >= arr.size())
                        {
                            Toast.makeText(context, "המבחן הסתיים", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(TestActivity.this, MainActivity.class);
                            startActivity(i);
                        }else {
                            newWord();
                            if(options.getVisibility() == View.VISIBLE) {
                                updateOptions();
                            }
                        }
                    }
                }, 1200);
            }
        });
        options = (LinearLayout) findViewById(R.id.linearLayout_options);
        options.setVisibility(View.INVISIBLE);
        textView_show_answers = (TextView) findViewById(R.id.textView_show_answers);
        textView_show_answers.setOnClickListener(this);
        option1 = (Button) findViewById(R.id.option1);
        option1.setOnClickListener(this);
        option2 = (Button) findViewById(R.id.option2);
        option2.setOnClickListener(this);
        option3 = (Button) findViewById(R.id.option3);
        option3.setOnClickListener(this);
        option4 = (Button) findViewById(R.id.option4);
        option4.setOnClickListener(this);
        newWord();
        optionsarr = new String[4];
    }

    private void newWord() {
        original_word.setText(arr.get(counter).getOriginal());
    }

    private void setDefaultcolors(){
        option1.setBackgroundColor(Color.parseColor("#E0F7FA"));
        option2.setBackgroundColor(Color.parseColor("#FFF8E1"));
        option3.setBackgroundColor(Color.parseColor("#FBE9E7"));
        option4.setBackgroundColor(Color.parseColor("#E8F5E9"));
    }

    @Override
    public void onClick(View view) {
        Random r = new Random();
        if (textView_show_answers.equals(view)) {
            if(options.getVisibility() == View.VISIBLE) {
                options.setVisibility(View.INVISIBLE);
            }else {
                updateOptions();
                options.setVisibility(View.VISIBLE);
            }
        }
        else if (option1.equals(view)) {
            if(correct == 0) {
                option1.setBackgroundColor(Color.parseColor("#0D4C01"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        counter++;
                        titleCountQ.setText(String.valueOf(counter +"/" + arr.size()));
                        if(counter >= arr.size())
                        {
                            Toast.makeText(context, "המבחן הסתיים", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(TestActivity.this, MainActivity.class);
                            startActivity(i);
                        }else {
                            setDefaultcolors();
                            newWord();
                            updateOptions();
                        }
                    }
                }, 1200);
            } else {
                option1.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
        else if(option2.equals(view)) {
            if(correct == 1) {
                option2.setBackgroundColor(Color.parseColor("#0D4C01"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        counter++;
                        if(counter >= arr.size())
                        {
                            Toast.makeText(context, "המבחן הסתיים", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(TestActivity.this, MainActivity.class);
                            startActivity(i);
                        }else {
                            setDefaultcolors();
                            newWord();
                            updateOptions();
                        }
                    }
                }, 1200);

            } else {
                option2.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
        else if(option3.equals(view)) {
            if(correct == 2) {
                option3.setBackgroundColor(Color.parseColor("#0D4C01"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        counter++;
                        if(counter >= arr.size())
                        {
                            Toast.makeText(context, "המבחן הסתיים", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(TestActivity.this, MainActivity.class);
                            startActivity(i);
                        }else {
                            setDefaultcolors();
                            newWord();
                            updateOptions();
                        }
                    }
                }, 1200);

            } else {
                option3.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
        else if(option4.equals(view)) {
            if(correct == 3) {
                option4.setBackgroundColor(Color.parseColor("#0D4C01"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        counter++;
                        if(counter >= arr.size())
                        {
                            Toast.makeText(context, "המבחן הסתיים", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(TestActivity.this, MainActivity.class);
                            startActivity(i);
                        }else {
                            setDefaultcolors();
                            newWord();
                            updateOptions();
                        }
                    }
                }, 1200);

            } else {
                option4.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
    }

    private void setOptions() {
        Random random = new Random();
        ArrayList<Word> temparr = new ArrayList<>();
        for(Word w: arr) {
            if(w.get_id() == (arr.get(counter).get_id())) {
                continue;
            }
            temparr.add(w);
        }

        for(int i = 0; i < optionsarr.length; i++) {
            int currectWord = random.nextInt(temparr.size());
            optionsarr[i]  = temparr.get(currectWord).getTranslate();
            temparr.remove(currectWord);
        }
    }

    public void updateOptions() {
        Random r = new Random();
        correct = r.nextInt(4);
        setOptions();
        option1.setText(optionsarr[0]);
        option2.setText(optionsarr[1]);
        option3.setText(optionsarr[2]);
        option4.setText(optionsarr[3]);
        switch (correct) {
            case 0: option1.setText(arr.get(counter).getTranslate());
                break;
            case 1: option2.setText(arr.get(counter).getTranslate());
                break;
            case 2: option3.setText(arr.get(counter).getTranslate());
                break;
            case 3: option4.setText(arr.get(counter).getTranslate());
                break;
        }
    }
}
