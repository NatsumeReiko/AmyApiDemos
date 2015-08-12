/**
 * Copyright (c) 2010, The Android Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.viewtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.apis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LaunchOtherActivity extends Activity {
    private static final int MAX_TASK = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewexampleactivity);

        final ListView listview = (ListView) findViewById(R.id.listview);
        String[] values = new String[]{
                "clear-top",
                "from_background",
                "reset-task-if-needed",
                "no-history",
                "new-document",
                "multiple-task",
                "multiple+new+task",
                "forward-result",
                "exclude-from-recent",
                "clear-task",
                "new-task",
                "LINE-FLAG-0"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.setBackgroundColor(getResources().getColor(R.color.solid_blue));
                launchOtherApp(item);
            }

        });
    }

    private void launchOtherApp(final String item) {
        switch (item) {
            case "clear-top": {
                startLINE(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            break;
            case "multiple-task": {
                startLINE(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            }
            break;
            case "multiple+new+task": {
                startLINE(Intent.FLAG_ACTIVITY_MULTIPLE_TASK, Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            break;
            case "forward-result": {
                startLINE(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            }
            break;
            case "clear-task": {
                startLINE(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            break;
            case "exclude-from-recent": {
                startLINE(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            }
            break;
            case "LINE-FLAG-0": {
                startLINE(0);
            }
            break;
            case "new-task": {
                startLINE(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            break;
            case "new-document": {
                startLINE(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            }
            break;
            case "no-history": {
                startLINE(Intent.FLAG_ACTIVITY_NO_HISTORY);
            }
            break;
            case "reset-task-if-needed": {
                startLINE(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            }
            break;
            case "from_background": {
                startLINE(Intent.FLAG_FROM_BACKGROUND);
            }
            break;

        }
    }

    private int index = 0;

    private void startLINE(int... flags) {
        String lineString = "line://msg/text/abcdefg: " + index;

        try {
//            final Intent intent = Intent.parseUri(lineString, Intent.URI_INTENT_SCHEME);
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setFlags(flag);

            final Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(lineString));
            for (int flag : flags) {
                intent.setFlags(flag);
            }
            startActivity(intent);
            this.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
