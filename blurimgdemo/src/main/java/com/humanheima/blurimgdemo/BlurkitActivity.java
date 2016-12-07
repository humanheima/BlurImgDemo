package com.humanheima.blurimgdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.wonderkiln.blurkit.BlurKit;
import com.wonderkiln.blurkit.BlurLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlurkitActivity extends AppCompatActivity {

    @BindView(R.id.img_blur_lit)
    ImageView imgBlurLit;
    @BindView(R.id.blurLayout)
    BlurLayout blurLayout;
    @BindView(R.id.img_sun_main)
    ImageView imgSunMain;
    @BindView(R.id.img_sun_main_bottom)
    ImageView imgSunMainBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);
        // View
        //BlurKit.blur(View src, int radius);
        // Bitmap
        //BlurKit.blur(Bitmap src, int radius);
        //BlurKit.fastBlur(View src, int radius, float downscaleFactor);
        Bitmap bitmap = ((BitmapDrawable) imgSunMain.getDrawable()).getBitmap();
        BlurKit.getInstance().blur(bitmap, 4);

        // BlurKit.getInstance().fastBlur(imgSunMainBottom, 8, 7);
    }

}
