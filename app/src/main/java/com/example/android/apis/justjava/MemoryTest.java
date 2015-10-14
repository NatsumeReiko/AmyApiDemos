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
import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.apis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class MemoryTest extends Activity {
    List<HashVsListTestDB> messageList = new ArrayList<>();
    HashMap<String, HashVsListTestDB> hashMap = new HashMap<String, HashVsListTestDB>();
    MyLinkedMap<String, HashVsListTestDB> linkHashMap = new MyLinkedMap<String, HashVsListTestDB>();

    private static final int FROM_LIST = 0;
    private static final int FROM_HASHMAP = 1;
    private static final int FROM_LINK_HASHMAP = 2;

    private static final String AVAILABLE_MEMORY = "available_memory:";
    private static final String TIMER = "TIME:";
    private static final String LIST_TAG = "LIST:";
    private static final String HASHMAP_TAG = "HASHMAP:";
    private static final String LINK_HASHMAP_TAG = "LINK_HASHMAP:";

    private long startMemory, endMemory = 0;
    private ActivityManager am;
    Runtime runtime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.many_button_layout);
        ((Button) findViewById(R.id.btn_01)).setText(LIST_TAG);
        ((Button) findViewById(R.id.btn_02)).setText(HASHMAP_TAG);
        ((Button) findViewById(R.id.btn_03)).setText(LINK_HASHMAP_TAG);

        runtime = Runtime.getRuntime();
//        am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        startMemory = am.getMemoryClass();
        Log.d("MemoryTest", "onCreate" + AVAILABLE_MEMORY + runtime.maxMemory());


    }

    private void makeTestDt(int mode) {
        startMemory = runtime.maxMemory() - runtime.freeMemory();
//        startMemory = am.getMemoryClass();

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


            if (mode == FROM_LIST)
                messageList.add(data);

            if (mode == FROM_HASHMAP)
                hashMap.put(data.messageId, data);

            if (mode == FROM_LINK_HASHMAP)
                linkHashMap.put(data.messageId, data);

        }
        endMemory = runtime.maxMemory() - runtime.freeMemory();
//        endMemory = am.getMemoryClass();

        if (mode == FROM_LIST)
            Log.d("MemoryTest", LIST_TAG + AVAILABLE_MEMORY + (startMemory - endMemory));

        if (mode == FROM_HASHMAP)
            Log.d("MemoryTest", HASHMAP_TAG + AVAILABLE_MEMORY + (startMemory - endMemory));

        if (mode == FROM_LINK_HASHMAP)
            Log.d("MemoryTest", LINK_HASHMAP_TAG + AVAILABLE_MEMORY + (startMemory - endMemory));


    }

    public void onBtnClicked(View v) {
        switch (v.getId()) {
            //find one from list
            case R.id.btn_01: {
                makeTestDt(FROM_LIST);
            }
            break;
            //find one from hashmap
            case R.id.btn_02: {
                makeTestDt(FROM_HASHMAP);
            }
            break;
            case R.id.btn_03: {
                makeTestDt(FROM_LINK_HASHMAP);
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

//    private void findDtById(String msgId, int from) {
//        if (from == FROM_LIST) {
//            HashVsListTestDB data;
//            startTimer = System.currentTimeMillis();
//            for (int index = 0; index < messageList.size(); index++) {
//                data = messageList.get(index);
//                if (msgId.equals(data.messageId)) {
//                    Log.d("HashMapVsListTest", LIST_TAG + available_memory + data.messageId);
//                    break;
//                }
//            }
//            endTimer = System.currentTimeMillis();
//            Log.d("HashMapVsListTest", LIST_TAG + TIMER + (endTimer - startTimer));
//
//        } else if (from == FROM_HASHMAP) {
//            startTimer = System.currentTimeMillis();
//            HashVsListTestDB data = hashMap.get(msgId);
//            endTimer = System.currentTimeMillis();
//            Log.d("HashMapVsListTest", HASHMAP_TAG + available_memory + data.messageId);
//            Log.d("HashMapVsListTest", HASHMAP_TAG + TIMER + (endTimer - startTimer));
//        } else if (from == FROM_LINK_HASHMAP) {
//
//            startTimer = System.currentTimeMillis();
//            HashVsListTestDB data = linkHashMap.get(msgId);
//            endTimer = System.currentTimeMillis();
//            Log.d("HashMapVsListTest", LINK_HASHMAP_TAG + available_memory + data.messageId);
//            Log.d("HashMapVsListTest", HASHMAP_TAG +"linkHashMap.get(msgId)"+ TIMER + (endTimer - startTimer));
//
//
//            startTimer = System.currentTimeMillis();
//            int postion = new ArrayList<String>(linkHashMap.keySet()).indexOf(msgId);
//            endTimer = System.currentTimeMillis();
//            Log.d("HashMapVsListTest", LINK_HASHMAP_TAG + "POSITION:" + postion);
//            Log.d("HashMapVsListTest", HASHMAP_TAG +"getPOSITION by msgId:"+ TIMER + (endTimer - startTimer));
//
//            int size = linkHashMap.size();
//            Log.d("HashMapVsListTest", LINK_HASHMAP_TAG + "SIZE:" + size);
////            for (int index = 0; index < size; index++) {
////                data = linkHashMap.getValue(index);
////                Log.d("HashMapVsListTest", LINK_HASHMAP_TAG + available_memory + data.messageId);
////            }
//
//        }
//
//    }
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

            Entry<K, V> entry = this.getEntry(i);
            if (entry == null) return null;

            return entry.getValue();
        }

        public Entry<K, V> getEntry(int i) {
            // check if negetive index provided
            Set<Entry<K, V>> entries = entrySet();
            int j = 0;

            for (Entry<K, V> entry : entries)
                if (j++ == i) return entry;

            return null;

        }

    }
}
