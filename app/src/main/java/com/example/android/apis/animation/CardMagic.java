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

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.android.apis.R;

import java.util.ArrayList;

public class CardMagic extends Activity {
    LinearLayout card, card01, card02, card03, card04, card05;
    private Animation animation, animation01, animation02, animation03, animation04, animation05;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_magic);

        initialView();

        Button starter = (Button) findViewById(R.id.startButton);
        starter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                card.startAnimation(animation);
                card01.startAnimation(animation01);
                card02.startAnimation(animation02);
                card03.startAnimation(animation03);
                card04.startAnimation(animation04);
                card05.startAnimation(animation05);

            }
        });
    }

    private void initialView(){
        card = (LinearLayout) findViewById(R.id.card);
        card01 = (LinearLayout) findViewById(R.id.card_01);
        card02 = (LinearLayout) findViewById(R.id.card_02);
        card03 = (LinearLayout) findViewById(R.id.card_03);
        card04 = (LinearLayout) findViewById(R.id.card_04);
        card05 = (LinearLayout) findViewById(R.id.card_05);

        animation = AnimationUtils.loadAnimation(this, R.anim.card_horizontal_in_from_left_anim);
        animation01 = AnimationUtils.loadAnimation(this, R.anim.card_push_left_in);
        animation02 = AnimationUtils.loadAnimation(this, R.anim.card_push_left_out);
//        animation03 = AnimationUtils.loadAnimation(this, R.anim.card_push_right_in);
        animation03 = AnimationUtils.loadAnimation(this, R.anim.card_vote_alpha);
        animation04 = AnimationUtils.loadAnimation(this, R.anim.card_push_right_out);
        animation05 = AnimationUtils.loadAnimation(this, R.anim.card_rotation);

        animation05.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card03.clearAnimation();
                card05.startAnimation(animation03);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public class XYHolder {
        private float mX;
        private float mY;

        public XYHolder(float x, float y) {
            mX = x;
            mY = y;
        }

        public float getX() {
            return mX;
        }

        public void setX(float x) {
            mX = x;
        }

        public float getY() {
            return mY;
        }

        public void setY(float y) {
            mY = y;
        }
    }

    public class XYEvaluator implements TypeEvaluator {
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            XYHolder startXY = (XYHolder) startValue;
            XYHolder endXY = (XYHolder) endValue;
            return new XYHolder(startXY.getX() + fraction * (endXY.getX() - startXY.getX()),
                    startXY.getY() + fraction * (endXY.getY() - startXY.getY()));
        }
    }


    public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

        public final ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();
        ValueAnimator bounceAnim = null;
        ViewGroup ball = null;

        public MyAnimationView(Context context, ViewGroup target) {
            super(context);
            ball = target;
        }

        private void createAnimation() {
            if (bounceAnim == null) {
                XYHolder startXY = new XYHolder(0f, 0f);
                XYHolder endXY = new XYHolder(300f, 500f);
                bounceAnim = ObjectAnimator.ofObject(ball, "xY",
                        new XYEvaluator(), endXY);
                bounceAnim.setDuration(1500);
                bounceAnim.addUpdateListener(this);
            }
        }

        public void startAnimation() {
            createAnimation();
            bounceAnim.start();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.save();
            canvas.translate(ball.getX(), ball.getY());
            ball.draw(canvas);
            canvas.restore();
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            invalidate();
        }

    }
}