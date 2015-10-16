package com.example.android.apis.media.exoplayer_in_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.apis.R;
import com.example.android.apis.media.VideoInfo;
import com.google.android.exoplayer.AspectRatioFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ms2 on 2015/10/16.
 */
public class ExoPlayerVideoRecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private List<VideoInfo> videoInfoList = new ArrayList<>();


    public ExoPlayerVideoRecyclerViewAdapter(Context appContext, List<VideoInfo> videoInfoList) {
        this.videoInfoList = videoInfoList;
        inflater = LayoutInflater.from(appContext.getApplicationContext());

    }

    public VideoInfo getItem(int position) {

        if (position < videoInfoList.size()) {
            return videoInfoList.get(position);
        }

        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater
                .inflate(R.layout.exoplayer_recycler_view_row, parent, false);
        return new VideoViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        final VideoInfo item = videoInfoList.get(position);

        if (holder instanceof VideoViewHolder) {
            setVideoViewHolder((VideoViewHolder) holder, position);
        }
    }

    private void setVideoViewHolder(VideoViewHolder holder, int item) {
        holder.parent.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return videoInfoList.size();
    }


//    private class ExoPlayerSurfaceHoldCallBack implements SurfaceHolder.Callback {
//        final int position;
//
//        public ExoPlayerSurfaceHoldCallBack(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void surfaceCreated(SurfaceHolder holder) {
//
//        }
//
//        @Override
//        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//        }
//
//        @Override
//        public void surfaceDestroyed(SurfaceHolder holder) {
//
//        }
//    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        AspectRatioFrameLayout videoContainer;
        View parent;

        public VideoViewHolder(View v) {
            super(v);
            parent = v;
            videoContainer = (AspectRatioFrameLayout) v.findViewById(R.id.video_frame);
        }
    }
}
