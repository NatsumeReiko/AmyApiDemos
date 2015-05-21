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


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.android.apis.R;

/**
 * This application demonstrates loading Animator objects from XML resources.
 */
public class AnimationToMultiViewAtTheSameTime extends Activity {

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

        Animation animation05 = AnimationUtils.loadAnimation(this, R.anim.anim_start_effect_scale_01);

        Animation animation06 = AnimationUtils.loadAnimation(this, R.anim.card_rotation);

//        findViewById(R.id.star_button).startAnimation(animation06);

        findViewById(R.id.star_effect2_image).startAnimation(animation05);


        findViewById(R.id.nointerpolation).startAnimation(animation05);
        findViewById(R.id.interpolation).startAnimation(animation05);


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