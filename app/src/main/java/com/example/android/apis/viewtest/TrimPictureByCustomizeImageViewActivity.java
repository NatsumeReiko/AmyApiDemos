package com.example.android.apis.viewtest;//ADD PACKAGE HERE

import android.app.Activity;
import android.os.Bundle;

import com.example.android.apis.R;
import com.example.android.apis.viewtest.otherclass.CropImageView;
import com.example.android.apis.viewtest.otherclass.CropImageViewClearMode;

public class TrimPictureByCustomizeImageViewActivity extends Activity {
    CropImageView target;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_picture_from_imageview);
        target = (CropImageView)findViewById(R.id.big_image);
        target.setOnTouchListener(target);

        CropImageViewClearMode targetClearMode = (CropImageViewClearMode)findViewById(R.id.big_image_clear_mode);
                targetClearMode.setOnTouchListener(targetClearMode);
    }



}
