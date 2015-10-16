/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.example.android.apis.media.exoplayer_in_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.android.apis.R;
import com.example.android.apis.media.VideoInfo;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.audio.AudioCapabilitiesReceiver;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.Util;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * An activity that plays media using {@link ExoPlayer}.
 */
public class ExoPlayerRecyclerViewDemo extends Activity
        implements SurfaceHolder.Callback, OnClickListener, MediaCodecVideoTrackRenderer.EventListener,
        AudioCapabilitiesReceiver.Listener {

    private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;

    private List<VideoInfo> videoInfoList = new ArrayList<>();
    ExoPlayerVideoRecyclerViewAdapter adapter;

    private RecyclerView recycleView;
    private LinearLayoutManager mLayoutManager;

    private int playPosition = -1;

    private Context appContext;

    MediaCodecVideoTrackRenderer videoRenderer;
    MediaCodecAudioTrackRenderer audioRenderer;

    private AudioCapabilitiesReceiver audioCapabilitiesReceiver;
    private ExoPlayer player;
    private static final CookieManager defaultCookieManager;

    private SurfaceView surfaceView;

    private Handler mainHandler;


    static {
        defaultCookieManager = new CookieManager();
        defaultCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exoplayer_recycler_view_demo);

        appContext = getApplicationContext();


        for (int index = 0; index < 10; index++) {
            videoInfoList.add(new VideoInfo("http://html5demos.com/assets/dizzy.mp4"));
            videoInfoList.add(new VideoInfo("https://platform-cdn.goalstart.jp/post-movie-native-2nd/12914/mp4_low_v2/12914.mp4"));
        }

        mainHandler = new Handler();

        CookieHandler currentHandler = CookieHandler.getDefault();
        if (currentHandler != defaultCookieManager) {
            CookieHandler.setDefault(defaultCookieManager);
        }
        audioCapabilitiesReceiver = new AudioCapabilitiesReceiver(this, this);
        audioCapabilitiesReceiver.register();

        player = ExoPlayer.Factory.newInstance(2);
        player.addListener(new ExoPlayer.Listener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {
                    case ExoPlayer.STATE_BUFFERING:
                        break;
                    case ExoPlayer.STATE_ENDED:
                        player.seekTo(0);
                        break;
                    case ExoPlayer.STATE_IDLE:
                        break;
                    case ExoPlayer.STATE_PREPARING:
                        break;
                    case ExoPlayer.STATE_READY:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPlayWhenReadyCommitted() {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }
        });
        surfaceView = new SurfaceView(this);
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));

        recycleView = (RecyclerView) findViewById(R.id.video_demo_recycler_list);

        mLayoutManager = new LinearLayoutManager(appContext, VERTICAL, false);
        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setStackFromEnd(true);

        recycleView.setLayoutManager(mLayoutManager);
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {

                    play(getPlayTargetPosition());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        adapter = new ExoPlayerVideoRecyclerViewAdapter(this, videoInfoList);
        recycleView.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        audioCapabilitiesReceiver.unregister();
        releasePlayer();
    }

    // OnClickListener methods

    @Override
    public void onClick(View view) {
    }

    // AudioCapabilitiesReceiver.Listener methods

    @Override
    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
        if (player == null) {
            return;
        }
        releasePlayer();
//        preparePlayer(playWhenReady);
//        player.setBackgrounded(backgrounded);
    }


    private void preparePlayer(int position) {

        Uri uri = Uri.parse(videoInfoList.get(position).videoUrl);

        Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter(mainHandler,
                null);

        DataSource dataSource =
                new DefaultUriDataSource(this, bandwidthMeter, Util.getUserAgent(this, "ExoPlayerDemo"));

        // Build the sample source
        ExtractorSampleSource sampleSource =
                new ExtractorSampleSource(uri, dataSource, allocator, 10 * BUFFER_SEGMENT_SIZE);

        // Build the track renderers
        videoRenderer = new MediaCodecVideoTrackRenderer(sampleSource,
                MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
        audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource);

        // Build the ExoPlayer and start playback
        player.prepare(videoRenderer, audioRenderer);


        player.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surfaceView.getHolder().getSurface());
        player.setPlayWhenReady(true);
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (player != null) {
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Do nothing.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (player != null) {
//            player.blockingClearSurface();
            player.release();
        }
    }


    @Override
    public void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException e) {

    }

    @Override
    public void onCryptoError(MediaCodec.CryptoException e) {

    }

    @Override
    public void onDecoderInitialized(String decoderName, long elapsedRealtimeMs, long initializationDurationMs) {

    }

    @Override
    public void onDroppedFrames(int count, long elapsed) {

    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
//        videoFrame.setAspectRatio(
//                height == 0 ? 1 : (width * pixelWidthHeightRatio) / height);
    }

    @Override
    public void onDrawnToSurface(Surface surface) {

    }

    public int getPlayTargetPosition() {
        return mLayoutManager.findLastCompletelyVisibleItemPosition();
    }

    private void removeVideoView(SurfaceView videoView) {
        ViewGroup parent = (ViewGroup) videoView.getParent();

        if (parent == null) {
            return;
        }

        int index = parent.indexOfChild(videoView);
        if (index >= 0) {
            parent.removeViewAt(index);
        }

    }

    private void play(int position) {
        if (position == playPosition) {
            return;
        }

        playPosition = position;
//
//        final CustomVideoView videoView = this.videoView;
//
        removeVideoView(surfaceView);
//        videoView.reset();
//
//        VideoInfo data = videoInfoList.get(position);
//
//        if (data == null) {
//            playPosition = null;
//            return this;
//        }

        // get target View position in RecyclerView
        int at = position - mLayoutManager.findFirstVisibleItemPosition();
        View child = recycleView.getChildAt(at);
        if (child == null) {
            return;
        }
        ExoPlayerVideoRecyclerViewAdapter.VideoViewHolder holder
                = (ExoPlayerVideoRecyclerViewAdapter.VideoViewHolder) child.getTag();
        if (holder == null) {
            playPosition = -1;
            return;
        }
        holder.videoContainer.addView(surfaceView);
        preparePlayer(playPosition);
    }
}
