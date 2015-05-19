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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.apis.R;

public class AllPartsTestActivity extends Activity {
    private static final int MAX_TASK = 3;

    private EditText editTest;
    private Button sendMsgBtn;

    //invisible button cannot click even listener is set
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittext_test_layout);
        editTest = (EditText)findViewById(R.id.edit_test);
        sendMsgBtn = (Button)findViewById(R.id.send_message);
        sendMsgBtn.setEnabled(false);

        sendMsgBtn.setVisibility(View.INVISIBLE);
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(" EditTestActivity "," sendMsgBtn onClick ");
                Toast.makeText(AllPartsTestActivity.this," Invisible btn clicked" ,Toast.LENGTH_LONG).show();
                editTest.setText("");
            }
        });
        editTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                boolean couldClick = textLength(text) > 0 && text.length() <= 140;
                Log.d(" EditTestActivity "," onTextChanged btn click:" + String.valueOf(couldClick) + " text: "+ text);
//                sendMsgBtn.setEnabled(couldClick);
                sendMsgBtn.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            private int textLength(String text) {
                int length = 0;
                String[] texts = text.split("\n");
                for (String t : texts) {
                    length += t.length();
                }
                return length;
            }
        });

    }

}
