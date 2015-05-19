/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.example.android.apis.animation;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.android.apis.R;

/**
 * This application demonstrates loading Animator objects from XML resources.
 */
public class AnimationStar extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_loading);


        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAnimation();
            }
        });
        findViewById(R.id.star1tButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAnimation1();
            }
        });
    }

    private void createAnimation() {
        // Y軸移動アニメーション
        final ObjectAnimator animatorY
                = ObjectAnimator.ofFloat(findViewById(R.id.star_frame), "translationY", -30.0f, 0.0f);
        animatorY.setInterpolator(new CosInterpolator());
        animatorY.setDuration(500);

        AnimatorSet anim01 = (AnimatorSet) AnimatorInflater.
                loadAnimator(this, R.anim.anim_start_rotation_y);
        anim01.setTarget(findViewById(R.id.star_button));

        AnimatorSet anim02 = (AnimatorSet) AnimatorInflater.
                loadAnimator(this, R.anim.anim_start_effect0);
        anim02.setTarget(findViewById(R.id.star_effect1_image));

        AnimatorSet anim03 = (AnimatorSet) AnimatorInflater.
                loadAnimator(this, R.anim.anim_start_effect1);
        anim03.setTarget(findViewById(R.id.star_effect2_image));

        AnimatorSet anim04 = (AnimatorSet) AnimatorInflater.
                loadAnimator(this, R.anim.anim_start_effect3);
        anim04.setTarget(findViewById(R.id.star_effect3_image));


        AnimatorSet animation = new AnimatorSet();
        ((AnimatorSet) animation).playTogether(animatorY, anim01, anim02, anim03, anim04);

        animation.start();
    }

    private void createAnimation1() {
        // Y軸移動アニメーション
        final ObjectAnimator animatorY
                = ObjectAnimator.ofFloat(findViewById(R.id.nointerpolation), "translationY", -30.0f, 0.0f);
        animatorY.setDuration(500);

        // Y軸移動アニメーション
        final ObjectAnimator animatorY1
                = ObjectAnimator.ofFloat(findViewById(R.id.interpolation), "translationY", -30.0f, 0.0f);
        animatorY1.setInterpolator(new CosInterpolator());
        animatorY1.setDuration(500);

        AnimatorSet animation = new AnimatorSet();
        ((AnimatorSet) animation).playTogether(animatorY, animatorY1);

        animation.start();
    }

    private class CosInterpolator implements TimeInterpolator {
        @Override
        public float getInterpolation(float input) {
            input *= 2 * Math.PI;
            return FloatMath.cos(input);
        }
    }
}