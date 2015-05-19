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
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.apis.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewTestActivity extends Activity {
    ListView myListView;
    NotificationPresentedStarAdapter adapter;
    List<MyData> myDataList = new ArrayList<>();
    int maxIndex = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_test_layout);

        makeDate();

        myListView = (ListView) findViewById(R.id.mylistview);
        adapter = new NotificationPresentedStarAdapter(this, R.layout.listview_test_layout_item, myDataList);
        myListView.setAdapter(adapter);


        findViewById(R.id.change_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                changeDate();
            }
        });

    }

    private void changeDate(int id) {
        if (id % 3 == 0) {
            for (int i = 0; i < myDataList.size(); i++) {
                MyData data = myDataList.get(i);

                if (data.Id == id) {
                    myDataList.remove(i);
                }
            }

            adapter.notifyDataSetChanged();
            return;
        }


        for (int i = 0; i < maxIndex; i++) {
            MyData data = myDataList.get(i);

            if (data.Id == id) {
                data.showPic = false;
                myDataList.set(i, data);
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void makeDate() {
        for (int i = 0; i < maxIndex; i++) {
            MyData item = new MyData();
            item.Id = i / 3;
            item.showPic = true;
            item.Message = "ID: " + item.Id + " index:" + i;

            myDataList.add(item);
        }
    }

    class MyData {
        public String Message;
        public int Id;
        public boolean showPic;
    }

    class NotificationPresentedStarAdapter extends ArrayAdapter<MyData> {

        private final LayoutInflater layoutInflater;

        public NotificationPresentedStarAdapter(Context context, int resource, List<MyData> objects) {
            super(context, resource, objects);
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.listview_test_layout_item, parent, false);

                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final MyData item = getItem(position);
            holder.nicknameButton.setText(item.Message);

            holder.starButton.setOnClickListener(starButtonListener);
            holder.starButton.setTag(item.Id);

            holder.starFrame.setVisibility(item.showPic ? View.VISIBLE : View.INVISIBLE);


            return convertView;

        }
    }

    private final View.OnClickListener starButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(final View view) {
            changeDate((int) view.getTag());
        }
    };

    private static class ViewHolder {
        final TextView nicknameButton;
        //        final TextView timeText;
        final ImageButton starButton;
        final FrameLayout starFrame;

        /**
         * コンストラクタ.
         *
         * @param view ビュー
         */
        public ViewHolder(final View view) {
//            backgroundLinear = (LinearLayout) view.findViewById(R.id.background_linear);
//            avatarButton = (AvatarButton) view.findViewById(R.id.avatar_button);
//            suspendedImage = (ImageView) view.findViewById(R.id.suspended_image);
//            nicknameButton = (TextButton) view.findViewById(R.id.nickname_button);
//            timeText = (TextView) view.findViewById(R.id.time_text);
//            starButton = (CommonButton) view.findViewById(R.id.star_button);

            nicknameButton = (TextView) view.findViewById(R.id.nickname_button);
            starButton = (ImageButton) view.findViewById(R.id.star_button);
            starFrame = (FrameLayout) view.findViewById(R.id.star_frame);

//            starEffectImage = new ImageView[3];
//            starEffectImage[0] = (ImageView) view.findViewById(R.id.star_effect1_image);
//            starEffectImage[1] = (ImageView) view.findViewById(R.id.star_effect2_image);
//            starEffectImage[2] = (ImageView) view.findViewById(R.id.star_effect3_image);
        }

    }
}
