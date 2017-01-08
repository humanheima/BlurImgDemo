package com.humanheima.blurimgdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.humanheima.blurimgdemo.transform.BlurTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_big_below)
    ImageView imgBigBelow;
    @BindView(R.id.img_big_above)
    ImageView imgBigAbove;
    @BindView(R.id.img)
    CircleImageView circleImage;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.img_bottom)
    ImageView imgBottom;
    @BindView(R.id.img_next)
    ImageView imgNext;
    /**
     * 透明度
     */
    private int mAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.sun_main).into(circleImage);
        Bitmap bitmap = ((BitmapDrawable) imgBottom.getDrawable()).getBitmap();
        imgBottom.setImageBitmap(BlurBitmap.blur(MainActivity.this, bitmap));
        //模拟加载网络图片,
        blurStatic();
        blurAutomatical();
        blurByGlide();
    }

    /**
     * 通过glide来实现模糊图片
     */
    private void blurByGlide() {
        Glide.with(this)
                .load(Images.imageUrls[1])
                .transform(new BlurTransformation(this))
                .into(imgNext);
    }

    /**
     * 动态模糊一张图片，实现思想是在底部加载一张最大程度模糊的图片 imgBigBelow
     * 在模糊图片上面加载一张清晰的图片 imgBigAbove ，然后动态改变上面清晰图片的透明度来实现。
     */
    private void blurAutomatical() {
        Picasso.with(this).load(Images.imageUrls[1]).into(imgBigAbove);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                //动态改变上面的大图的透明度
                Log.e("alph", "progress" + progress + "," + (1f - ((float) progress / 100f)));
                imgBigAbove.setAlpha((1f - ((float) progress / 100f)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 静态模糊一张图片
     */
    private void blurStatic() {
        //加载图片，添加回调，当加载成功的时候模糊图片
        Picasso.with(this).load(Images.imageUrls[1]).into(imgBigBelow, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) imgBigBelow.getDrawable()).getBitmap();
                imgBigBelow.setImageBitmap(BlurBitmap.blur(MainActivity.this, bitmap));
            }

            @Override
            public void onError() {
                Log.e("tag", "加载图片出错");
            }
        });
    }
}
