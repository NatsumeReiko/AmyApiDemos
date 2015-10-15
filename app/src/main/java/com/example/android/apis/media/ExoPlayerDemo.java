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

package com.example.android.apis.media;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;

import com.example.android.apis.R;
import com.example.android.apis.media.exoplayer.EventLogger;
import com.google.android.exoplayer.AspectRatioFrameLayout;
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
import com.google.android.exoplayer.util.DebugTextViewHelper;
import com.google.android.exoplayer.util.Util;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * An activity that plays media using {@link ExoPlayer}.
 */
public class ExoPlayerDemo extends Activity implements SurfaceHolder.Callback, OnClickListener,MediaCodecVideoTrackRenderer.EventListener,
        AudioCapabilitiesReceiver.Listener {
    private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private static final int VIDEO_BUFFER_SEGMENTS = 200;
    private static final int AUDIO_BUFFER_SEGMENTS = 54;
    private static final int TEXT_BUFFER_SEGMENTS = 2;
    private static final int LIVE_EDGE_LATENCY_MS = 30000;

    public static final int TYPE_DASH = 0;
    public static final int TYPE_SS = 1;
    public static final int TYPE_HLS = 2;
    public static final int TYPE_OTHER = 3;

    public static final String CONTENT_TYPE_EXTRA = "content_type";
    public static final String CONTENT_ID_EXTRA = "content_id";

    private static final String TAG = "PlayerActivity";
    private static final int MENU_GROUP_TRACKS = 1;
    private static final int ID_OFFSET = 2;

    private static final CookieManager defaultCookieManager;
    private Handler mainHandler;

    static {
        defaultCookieManager = new CookieManager();
        defaultCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    private EventLogger eventLogger;
    private View debugRootView;
    private View shutterView;
    private AspectRatioFrameLayout videoFrame;
    private SurfaceView surfaceView;

    private ExoPlayer player;
    private DebugTextViewHelper debugViewHelper;
    private boolean playerNeedsPrepare;

    private long playerPosition;
    private boolean enableBackgroundAudio;

    private Uri contentUri;
    private int contentType;
    private String contentId;

    private AudioCapabilitiesReceiver audioCapabilitiesReceiver;

    // Activity lifecycle

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        contentUri = intent.getData();
        contentType = intent.getIntExtra(CONTENT_TYPE_EXTRA, -1);
        contentId = intent.getStringExtra(CONTENT_ID_EXTRA);

        setContentView(R.layout.exoplayer_demo);
        mainHandler = new Handler();

        View root = findViewById(R.id.root);
        root.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.performClick();
                }
                return true;
            }
        });
        root.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
                    return false;
                }
                return true;
            }
        });

//        shutterView = findViewById(R.id.shutter);
//        debugRootView = findViewById(R.id.controls_root);

        videoFrame = (AspectRatioFrameLayout) findViewById(R.id.video_frame);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(this);
//        subtitleLayout = (SubtitleLayout) findViewById(R.id.subtitles);


        CookieHandler currentHandler = CookieHandler.getDefault();
        if (currentHandler != defaultCookieManager) {
            CookieHandler.setDefault(defaultCookieManager);
        }

        audioCapabilitiesReceiver = new AudioCapabilitiesReceiver(this, this);
        audioCapabilitiesReceiver.register();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player == null) {
            preparePlayer03(true);
        } else {
//            player.setBackgrounded(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!enableBackgroundAudio) {
            releasePlayer();
        } else {
//            player.setBackgrounded(true);
        }
//        shutterView.setVisibility(View.VISIBLE);
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
        preparePlayer03(true);
    }

    // AudioCapabilitiesReceiver.Listener methods

    @Override
    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
        if (player == null) {
            return;
        }
//        boolean backgrounded = player.getBackgrounded();
        boolean playWhenReady = player.getPlayWhenReady();
        releasePlayer();
        preparePlayer03(playWhenReady);
//        player.setBackgrounded(backgrounded);
    }

    // Internal methods

//    private DemoPlayer.RendererBuilder getRendererBuilder() {
//        String userAgent = Util.getUserAgent(this, "ExoPlayerDemo");
//        switch (contentType) {
//            case TYPE_SS:
//                return new SmoothStreamingRendererBuilder(this, userAgent, contentUri.toString(),
//                        new SmoothStreamingTestMediaDrmCallback());
//            case TYPE_DASH:
//                return new DashRendererBuilder(this, userAgent, contentUri.toString(),
//                        new WidevineTestMediaDrmCallback(contentId));
//            case TYPE_HLS:
//                return new HlsRendererBuilder(this, userAgent, contentUri.toString());
//            case TYPE_OTHER:
//                return new ExtractorRendererBuilder(this, userAgent, contentUri);
//            default:
//                throw new IllegalStateException("Unsupported type: " + contentType);
//        }
//    }

    MediaCodecVideoTrackRenderer videoRenderer;
    MediaCodecAudioTrackRenderer audioRenderer;

    private void preparePlayer03(boolean playWhenReady) {

        Uri uri = Uri.parse("http://html5demos.com/assets/dizzy.mp4");
        final int numRenderers = 2;

        Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter(mainHandler,
                null);
        DataSource dataSource = new DefaultUriDataSource(this, bandwidthMeter, Util.getUserAgent(this, "ExoPlayerDemo"));
        // Build the sample source
        ExtractorSampleSource sampleSource = new ExtractorSampleSource(uri, dataSource, allocator, 10 * BUFFER_SEGMENT_SIZE);

        // Build the track renderers
        videoRenderer = new MediaCodecVideoTrackRenderer(sampleSource, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
        audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource);

        // Build the ExoPlayer and start playback
        ExoPlayer exoPlayer = ExoPlayer.Factory.newInstance(numRenderers);
        exoPlayer.prepare(videoRenderer, audioRenderer);

        // Pass the surface to the video renderer.
        exoPlayer.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surfaceView.getHolder().getSurface());

        exoPlayer.setPlayWhenReady(true);
    }

    private void releasePlayer() {
        if (player != null) {
//            debugViewHelper.stop();
//            debugViewHelper = null;
//            playerPosition = player.getCurrentPosition();
            player.release();
            player = null;
//            eventLogger.endSession();
//            eventLogger = null;
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (player != null) {
//            player.sendMessage(
//                    videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surfaceView.getHolder().getSurface());
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
        videoFrame.setAspectRatio(
                height == 0 ? 1 : (width * pixelWidthHeightRatio) / height);
    }

    @Override
    public void onDrawnToSurface(Surface surface) {

    }
}
