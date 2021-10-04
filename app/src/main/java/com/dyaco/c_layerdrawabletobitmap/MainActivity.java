package com.dyaco.c_layerdrawabletobitmap;
//點陣圖,兩個點陣圖重疊

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
//    private String levelNum = "1#4#10#3#10#10#10#2#10#6#6#6#1#1#1#6#6#6#6#6";
//    private String inclineNum = "5#5#5#5#5#5#5#10#10#10#10#10#10#10#10#10#10#10#10#10";

    private String levelNum   = "7#7#7#8#8#8#10#9#9#9#10#9#9#9#9#8#8#7#7#7";
    private String inclineNum = "7#7#1#1#10#2#3#3#3#3#3#2#2#2#1#1#1#1#1#1";
    //    private String data2 = "1#2#10#10#1#5#5#5#5#3#1#3#4#4#3#6#3#3#6#4";
    private ImageView image;
    private ImageView image2;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setImageView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setImageView() {
        //byte[] imageBytes = DrawableUtils.getImageBytes(this, data, 800, R.color.red, true);
        //byte[] imageBytes2 = DrawableUtils.getImageBytes(this, data2, 800, R.color.green);

        byte[] imageBytes = DrawableUtils.getImageBytesLevelOverIncline(this, levelNum, inclineNum, 800, R.color.red);
        byte[] imageBytes2 = DrawableUtils.getImageBytesInclineOverLevel(this, levelNum, inclineNum, 800, R.color.green);

        Glide.with(this)
                .load(imageBytes)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                //  .placeholder(R.drawable.insert_custom_diagram)
                .into(image
                );

        Glide.with(this)
                .load(imageBytes2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                //  .placeholder(R.drawable.insert_custom_diagram)
                .into(image2
                );
    }

    private void initView() {
        image = findViewById(R.id.image);
        image2 = findViewById(R.id.image2);
    }
}