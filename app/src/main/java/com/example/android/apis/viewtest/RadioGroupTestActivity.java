/**
 * Copyright (c) 2010, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.viewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.apis.R;

public class RadioGroupTestActivity extends Activity implements RadioGroup.OnCheckedChangeListener,
        View.OnClickListener,
        View.OnFocusChangeListener,
        View.OnTouchListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_test_layout);
        ((RadioGroup) findViewById(R.id.radio_group)).setOnCheckedChangeListener(this);
//        (findViewById(R.id.right_radio_button)).setOnClickListener(this);
        (findViewById(R.id.right_radio_button)).setOnTouchListener(this);
//        (findViewById(R.id.radio_group)).setOnTouchListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        Toast.makeText(this, "onCheckedChanged", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.right_radio_button:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(true){
                        ((RadioButton)v).setChecked(true);
                    }
                    Toast.makeText(this, "right_radio_button", Toast.LENGTH_LONG).show();
                } else {
                    return true;
                }
                break;
            case R.id.left_radio_button:
                Toast.makeText(this, "left_radio_button", Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_group:
                Toast.makeText(this, "radio_group", Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_radio_button:
                Toast.makeText(this, "right_radio_button", Toast.LENGTH_LONG).show();
                break;
            case R.id.left_radio_button:
                Toast.makeText(this, "left_radio_button", Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_group:
                Toast.makeText(this, "radio_group", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.right_radio_button:
                Toast.makeText(this, "right_radio_button", Toast.LENGTH_LONG).show();
                break;
            case R.id.left_radio_button:
                Toast.makeText(this, "left_radio_button", Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_group:
                Toast.makeText(this, "radio_group", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
