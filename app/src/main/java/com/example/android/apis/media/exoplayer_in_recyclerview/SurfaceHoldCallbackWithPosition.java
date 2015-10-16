package com.example.android.apis.media.exoplayer_in_recyclerview;

import android.view.SurfaceHolder;

/**
 * Created by ms2 on 2015/10/16.
 */
public interface SurfaceHoldCallbackWithPosition {
    public void surfaceWithPositionCreated(SurfaceHolder holder, int position);

    public void surfaceWithPositionChanged(SurfaceHolder holder, int format, int width,
                                           int height, int position);
    public void surfaceWithPositionDestroyed(SurfaceHolder holder, int position);
}