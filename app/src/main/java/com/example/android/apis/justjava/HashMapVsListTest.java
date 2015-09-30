/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.justjava;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.apis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMapVsListTest extends Activity {
    List<HashVsListTestDB> messageList = new ArrayList<>();
    HashMap<String, HashVsListTestDB> hashMap = new HashMap<String, HashVsListTestDB>();
    MyLinkedMap<String, HashVsListTestDB> linkHashMap = new MyLinkedMap<String, HashVsListTestDB>();

    private static final int FROM_LIST = 0;
    private static final int FROM_HASHMAP = 1;
    private static final int FROM_LINK_HASHMAP = 2;

    private static final String MSG_ID = "MSG_ID:";
    private static final String TIMER = "TIME:";
    private static final String FROM_LIST_TAG = "FROM_LIST:";
    private static final String FROM_HASHMAP_TAG = "FROM_HASHMAP:";
    private static final String FROM_LINK_HASHMAP_TAG = "FROM_LINK_HASHMAP:";

    private long startTimer, endTimer = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.many_button_layout);
        ((Button) findViewById(R.id.btn_01)).setText(FROM_LIST_TAG);
        ((Button) findViewById(R.id.btn_02)).setText(FROM_HASHMAP_TAG);
        ((Button) findViewById(R.id.btn_03)).setText(FROM_LINK_HASHMAP_TAG);

        makeTestDt();
    }

    private void makeTestDt() {
        HashVsListTestDB data;
        for (int allIndex = 0; allIndex < 100000; allIndex++) {

            data = new HashVsListTestDB();
            data.messageId = String.valueOf(allIndex);
            data.groupId = 1;
            data.postTimestamp = "timestamp";
            data.status = 1;
            data.type = 1;
            data.message = "message" + allIndex;
            data.senderId = 1013914;
            data.contentMainInfo = "no";
            data.contentSubInfo = "no";
            data.readCount = allIndex;
            data.reportLabelDisplay = true;
            data.reportExpiration = "2015-10-20T17:49:36+0900";
            data.isReported = true;

            data.readCount = allIndex;
            data.status = MessageStatus.SEND_FAILED_NO_RETRY.getValue();
            data.postTimestamp = "2015-09-09T17:49:36+0900";

            messageList.add(data);

            hashMap.put(data.messageId, data);

            if (allIndex > 300) {
                linkHashMap.put(data.messageId, data);
            }

        }

    }

    public void onBtnClicked(View v) {
        switch (v.getId()) {
            //find one from list
            case R.id.btn_01: {
                findDtById("5999", FROM_LIST);
            }
            break;
            //find one from hashmap
            case R.id.btn_02: {
                findDtById("5999", FROM_HASHMAP);
            }
            break;
            case R.id.btn_03: {
                findDtById("5999", FROM_LINK_HASHMAP);
            }
            break;
            case R.id.btn_04: {

            }
            break;
            case R.id.btn_05: {

            }
            break;
        }
    }

    private void findDtById(String msgId, int from) {
        if (from == FROM_LIST) {
            HashVsListTestDB data;
            startTimer = System.currentTimeMillis();
            for (int index = 0; index < messageList.size(); index++) {
                data = messageList.get(index);
                if (msgId.equals(data.messageId)) {
                    Log.d("HashMapVsListTest", FROM_LIST_TAG + MSG_ID + data.messageId);
                    break;
                }
            }
            endTimer = System.currentTimeMillis();
            Log.d("HashMapVsListTest", FROM_LIST_TAG + TIMER + (endTimer - startTimer));

        } else if (from == FROM_HASHMAP) {
            startTimer = System.currentTimeMillis();
            HashVsListTestDB data = hashMap.get(msgId);
            endTimer = System.currentTimeMillis();
            Log.d("HashMapVsListTest", FROM_HASHMAP_TAG + MSG_ID + data.messageId);
            Log.d("HashMapVsListTest", FROM_HASHMAP_TAG + TIMER + (endTimer - startTimer));
        } else if (from == FROM_LINK_HASHMAP) {

            startTimer = System.currentTimeMillis();
            HashVsListTestDB data = linkHashMap.get(msgId);
            endTimer = System.currentTimeMillis();
            Log.d("HashMapVsListTest", FROM_LINK_HASHMAP_TAG + MSG_ID + data.messageId);
            Log.d("HashMapVsListTest", FROM_HASHMAP_TAG +"linkHashMap.get(msgId)"+ TIMER + (endTimer - startTimer));


            startTimer = System.currentTimeMillis();
            int postion = new ArrayList<String>(linkHashMap.keySet()).indexOf(msgId);
            endTimer = System.currentTimeMillis();
            Log.d("HashMapVsListTest", FROM_LINK_HASHMAP_TAG + "POSITION:" + postion);
            Log.d("HashMapVsListTest", FROM_HASHMAP_TAG +"getPOSITION by msgId:"+ TIMER + (endTimer - startTimer));

            int size = linkHashMap.size();
            Log.d("HashMapVsListTest", FROM_LINK_HASHMAP_TAG + "SIZE:" + size);
//            for (int index = 0; index < size; index++) {
//                data = linkHashMap.getValue(index);
//                Log.d("HashMapVsListTest", FROM_LINK_HASHMAP_TAG + MSG_ID + data.messageId);
//            }

        }

    }
//    public void onBtnClicked(View v) {
//        switch (v.getId()) {
//            case R.id.btn_01: {
//
//            }
//            break;
//            case R.id.btn_02: {
//
//            }
//            break;
//            case R.id.btn_03: {
//
//            }
//            break;
//            case R.id.btn_04: {
//
//            }
//            break;
//            case R.id.btn_05: {
//
//            }
//            break;
//        }
//    }


    class MyLinkedMap<K, V> extends LinkedHashMap<K, V> {

        public V getValue(int i) {

            Map.Entry<K, V> entry = this.getEntry(i);
            if (entry == null) return null;

            return entry.getValue();
        }

        public Map.Entry<K, V> getEntry(int i) {
            // check if negetive index provided
            Set<Entry<K, V>> entries = entrySet();
            int j = 0;

            for (Map.Entry<K, V> entry : entries)
                if (j++ == i) return entry;

            return null;

        }

    }
}
