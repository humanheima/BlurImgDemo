package com.humanheima.blurimgdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
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
    @BindView(R.id.blurLayout_sun_main)
    BlurLayout blurLayoutSunMain;
    @BindView(R.id.blurLayout_sun_main_bottom)
    BlurLayout blurLayoutSunMainBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.sun_main).into(imgSunMain, new Callback() {
            @Override
            public void onSuccess() {
                //blurLayoutSunMain.invalidate();
            }

            @Override
            public void onError() {

            }
        });
    }

}
