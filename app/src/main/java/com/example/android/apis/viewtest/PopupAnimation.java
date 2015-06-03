package com.example.android.apis.viewtest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.apis.R;


public class PopupAnimation {


    public static void showNotification(
            final Context context,
            final String message,
            final boolean fullScreen) {

        // レイアウトを取得する。
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.view_notification_popup, null);

        // コンポーネントを取得する。
        final View notificationLinear = view.findViewById(R.id.notification_linear);
        final TextView messageText = (TextView) view.findViewById(R.id.message_text);

        messageText.setText(message);

        // WindowManagerを取得する。
        final WindowManager windowManager
                = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        // フラグを決定する。
        int flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT || fullScreen) {
            flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }

        // レイアウトパラメータを設定する。
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                flags,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP;

        messageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Toast.makeText(context, "ClickableSpan ", Toast.LENGTH_SHORT).show();

            }
        });

        try {
            // アニメーションを開始する。
            startAnimation(view, notificationLinear, windowManager);
        } catch (RuntimeException e) {
            return;
        }
        // ビューを表示する。
        windowManager.addView(view, params);

    }

    private static void startAnimation(
            final View layout,
            final View view,
            final WindowManager windowManager) {

        final Context context = view.getContext();

        // アニメーションを開始する。
        final AnimatorSet anim = (AnimatorSet) AnimatorInflater.loadAnimator(
                context, R.animator.vertical_slide_in);
        anim.setTarget(view);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(final Animator animator) {
            }

            @Override
            public void onAnimationEnd(final Animator animator) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        endAnimation(layout, view, windowManager);
                    }
                }, 2500);
            }

            @Override
            public void onAnimationCancel(final Animator animator) {
            }

            @Override
            public void onAnimationRepeat(final Animator animator) {
            }
        });
        anim.start();
    }


    private static void endAnimation(
            final View layout,
            final View view,
            final WindowManager windowManager) {

        final Context context = view.getContext();

        // アニメーションを開始する。
        final AnimatorSet anim = (AnimatorSet) AnimatorInflater.loadAnimator(
                context, R.animator.vertical_slide_out);
        anim.setTarget(view);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(final Animator animator) {
            }

            @Override
            public void onAnimationEnd(final Animator animator) {
                try {
                    windowManager.removeView(layout);
                } catch (Throwable t) {
                    // do nothing
                }
            }

            @Override
            public void onAnimationCancel(final Animator animator) {
            }

            @Override
            public void onAnimationRepeat(final Animator animator) {
            }
        });
        anim.start();
    }
}
