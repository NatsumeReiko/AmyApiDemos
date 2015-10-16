package com.example.android.apis.media.exoplayer_in_recyclerview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.example.android.apis.media.VideoInfo;


public class ExoPlayerVideoRecyclerView extends RecyclerView {

    private static final int NO_PLAY_POSITION = -1;

    //surface view for playing video
    private SurfaceView videoSurfaceView;
//
//    private CustomOnScrollListener customOnScrollListener;

    /**
     * the position of playing video
     */
    private Integer playPosition;

    private float center;

    public ExoPlayerVideoRecyclerView(Context context) {
        super(context);
        initialize();
    }

    public ExoPlayerVideoRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ExoPlayerVideoRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable parent = super.onSaveInstanceState();
        SavedState ss = new SavedState(parent);
        ss.playPosition = playPosition;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(final Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        playPosition = ss.playPosition;
    }

//    @Override
//    public void setOnScrollListener(OnScrollListener l) {
//        customOnScrollListener.setOnScrollListener(l);
//    }

    public int getPlayTargetPosition() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) getLayoutManager());

        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
        int visibleViewCount = lastVisiblePosition - firstVisiblePosition + 1;

        float center = this.center;

        Log.d("scroll", "count: " + visibleViewCount);

        for (int i = 0; i < visibleViewCount; ++i) {
            Rect visibleRect = new Rect();
            getChildAt(i).getGlobalVisibleRect(visibleRect);
            Log.d("scroll", "top: " + visibleRect.top);
            Log.d("scroll", "bottom: " + visibleRect.bottom);
            Log.d("scroll", "center: " + center);
            if (visibleRect.top <= center && center <= visibleRect.bottom) {
                Log.d("scroll", "position; " + (firstVisiblePosition + i));
                return firstVisiblePosition + i;
            }
        }
        return NO_PLAY_POSITION;
    }

//    public VideoListView play() {
//        int position = getPlayTargetPosition();
//        if (position != NO_PLAY_POSITION) {
//            play(position);
//        }
//        return this;
//    }
    private void play(int position) {
//
//        final CustomVideoView videoView = this.videoView;
//
//        // 別の位置にある動画再生用のViewを非表示にする。
//        removeVideoView(videoView);
//        videoView.reset();
//
        VideoInfo data = ((ExoPlayerVideoRecyclerViewAdapter) getAdapter()).getItem(position);
//
//        // データがないときは再生しない。
//        if (data == null) {
//            playPosition = null;
//            return this;
//        }
        LinearLayoutManager layoutManager = ((LinearLayoutManager) getLayoutManager());

        // get target View position in RecyclerView
        int at = position - layoutManager.findFirstVisibleItemPosition();
//
//        // 動画再生用のViewを表示させる。
        View child = getChildAt(at);
//
//        // まだViewがないときは再生しない。
        if (child == null) {
            return;
        }
//
//        VideoListAdapter.ViewHolder holder = (VideoListAdapter.ViewHolder) child.getTag();
//        if (holder == null) {
//            playPosition = null;
//            return this;
//        }
//        holder.layout.addView(videoView);
//
//        // 動画のURLを渡して再生準備を行う。
//        if (NetworkState.getInstance(getContext()).isWifiConnected() && data.getMovieUrlMp4High() != null) {
//            videoView.setTimelineData(data);
//        } else if (!NetworkState.getInstance(getContext()).isWifiConnected() && data.getMovieUrlMp4Low() != null) {
//            videoView.setTimelineData(data);
//        }
//
//        playPosition = position;
//        return this;
    }

//    public VideoListView stop() {
//        CustomVideoView videoView = this.videoView;
//        removeVideoView(videoView);
//        videoView.reset();
//        return this;
//    }

//    public VideoListView reset() {
//        stop();
//        playPosition = null;
//        return this;
//    }

//    public VideoListView destroy() {
//        videoView.destroy();
//        customOnScrollListener = null;
//        playPosition = null;
//        return this;
//    }

    public Integer getPlayPosition() {
        return playPosition;
    }

    private void initialize() {

//        videoView = new CustomVideoView(getContext().getApplicationContext());
//
//        // カスタムスクロールリスナをセットする。
//        CustomOnScrollListener customOnScrollListener = createCustomOnScrollListener();
//        super.setOnScrollListener(customOnScrollListener);
//        this.customOnScrollListener = customOnScrollListener;

        // 画面の中央位置を取得する。
        Display display = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        center = size.y * 0.5F;
    }

    //    /**
//     * カスタムスクロールリスナを生成する。
//     *
//     * @return カスタムスクロールリスナ。
//     */
//    private CustomOnScrollListener createCustomOnScrollListener() {
//        return new CustomOnScrollListener(getContext().getApplicationContext(), new CustomOnScrollListener.OnChangeVisibleListener() {
//
//            @Override
//            public void onExit(AbsListView view, int position) {
//
//                // 動画を停止する。
//                reset();
//            }
//
//            @Override
//            public void onEnter(final AbsListView view, int position, final int at) {
//
//                // 動画を再生する。
//                play(position);
//            }
//        });
//    }
//
//    /**
//     * 動画再生用のViewを削除する。
//     *
//     * @param videoView 動画再生用のView。
//     */
//    private void removeVideoView(CustomVideoView videoView) {
//        ViewGroup parent = (ViewGroup) videoView.getParent();
//
//        // 親のViewがないときは何もしない。
//        if (parent == null) {
//            return;
//        }
//
//        // Viewが見つかった時は削除する。
//        int index = parent.indexOfChild(videoView);
//        if (index >= 0) {
//            parent.removeViewAt(index);
//        }
//    }

    private static class SavedState extends BaseSavedState {
        Integer playPosition;

        public SavedState(final Parcelable superState) {
            super(superState);
        }

        public SavedState(final Parcel in) {
            super(in);
            playPosition = in.readByte() == 0x00 ? null : in.readInt();
        }

        @Override
        public void writeToParcel(final Parcel out, final int flags) {
            super.writeToParcel(out, flags);
            if (playPosition == null) {
                out.writeByte((byte) (0x00));
            } else {
                out.writeByte((byte) (0x01));
                out.writeInt(playPosition);
            }
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
