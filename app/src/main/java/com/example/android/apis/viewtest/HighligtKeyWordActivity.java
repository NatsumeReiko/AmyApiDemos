package com.example.android.apis.viewtest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.apis.R;

public class HighligtKeyWordActivity extends Activity {

    Button next;
    EditText et;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highligt_key_word);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);
        tv.setText("The name of our country is Bangladesh. Bangladesh is a land of rivers.");

        next = (Button) findViewById(R.id.button1);
        next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // TODO Auto-generated method stub
                tv.setText("The name of our country is Bangladesh. Bangladesh is a land of rivers.");
                String key = et.getText().toString();
                String target = tv.getText().toString();
                heightKeyWord(key, target, tv);

            }
        });
    }

    private void heightKeyWord(String key, String target, TextView resultView) {
        int end = target.indexOf(key, 0);
        Spannable wordToSpan = new SpannableString(target);

        for (int start = 0; start < target.length() && end != -1; start = end + 1) {

            end = target.indexOf(key, start);
            if (end == -1)
                break;
            else {
                wordToSpan.setSpan(new BackgroundColorSpan(0xFFFFFF00), end, end + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                resultView.setText(wordToSpan, TextView.BufferType.SPANNABLE);
            }
        }
    }


}
