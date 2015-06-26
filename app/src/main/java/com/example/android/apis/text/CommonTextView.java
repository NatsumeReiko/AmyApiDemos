package com.example.android.apis.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.android.apis.R;


public class CommonTextView extends TextView {

    private boolean isUserInputText;

    private final int initialTextStyle;

    public CommonTextView(Context context) {
        this(context, null);
    }

    public CommonTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CommonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) {
            initialTextStyle = Typeface.NORMAL;
            return;
        }

        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CommonTextView);

        isUserInputText = typedArray.getBoolean(R.styleable.CommonTextView_is_user_input_text, true);

        Typeface typeface = getTypeface();
        if (typeface != null) {
            initialTextStyle = getTypeface().getStyle();
        } else {
            initialTextStyle = Typeface.NORMAL;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        updateFont();
    }

    /**
     * フォントを更新する
     */
    private void updateFont() {
        Typeface typeface;
        if (isUserInputText) {
            typeface = Typeface.defaultFromStyle(initialTextStyle);
        } else {
            String FONT_BOLD   = "fonts/srgb_20141216";
            String FONT_NORMAL = "fonts/srgm_20141216";


            boolean isBold = (initialTextStyle == Typeface.BOLD);

            if (isBold) {

                typeface = Typeface.createFromAsset(getContext().getAssets(), FONT_BOLD);
            } else {
                typeface = Typeface.createFromAsset(getContext().getAssets(), FONT_NORMAL);
            }
        }
        setTypeface(typeface);
    }
}
