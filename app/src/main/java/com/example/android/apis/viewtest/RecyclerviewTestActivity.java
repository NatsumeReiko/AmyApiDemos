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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.apis.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewTestActivity extends Activity {
    RecyclerView myListView;
    MyAdapter adapter;
    List<MyData> myDataList = new ArrayList<>();
    int maxIndex = 500;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_test_layout);

        makeDate();

        myListView = (RecyclerView) findViewById(R.id.mylistview);

//        myListView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        myListView.setLayoutManager(mLayoutManager);

        adapter = new MyAdapter();
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

    private final View.OnClickListener starButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(final View view) {
            changeDate((int) view.getTag());
        }
    };

    public static class CycleViewHolder extends RecyclerView.ViewHolder {

        TextView nicknameButton;
        //        final TextView timeText;
        ImageButton starButton;
        FrameLayout starFrame;

        public CycleViewHolder(View v) {
            super(v);
            nicknameButton = (TextView) v.findViewById(R.id.nickname_button);
            starButton = (ImageButton) v.findViewById(R.id.star_button);
            starFrame = (FrameLayout) v.findViewById(R.id.star_frame);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<CycleViewHolder> {

        public MyAdapter() {
        }

        @Override
        public CycleViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
            View v;
            if(viewType == 0) {
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listview_test_layout_item, parent, false);
            }else{
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listview_test_layout_item_01, parent, false);

            }
            CycleViewHolder vh = new CycleViewHolder(v);
            return vh;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 ;
        }

        @Override
        public void onBindViewHolder(CycleViewHolder holder, int position) {

            final MyData item = myDataList.get(position);

            holder.nicknameButton.setText(item.Message);

            holder.starButton.setOnClickListener(starButtonListener);
            holder.starButton.setTag(item.Id);

            holder.starFrame.setVisibility(item.showPic ? View.VISIBLE : View.INVISIBLE);

        }



        @Override
        public int getItemCount() {
            return myDataList.size();
        }
    }
}
