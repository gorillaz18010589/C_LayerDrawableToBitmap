package com.dyaco.c_layerdrawabletobitmap;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;

import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawableUtils {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static byte[] getImageBytes(Context context, String num, int height, @ColorRes int color) {
        int defaultLayoutHeight = 80;
        int defaultLayoutWidth = 65;

        int[] arrayNum = Arrays.stream(num.split("#", -1))
                .mapToInt(Integer::parseInt)
                .toArray();

        final List<Drawable> images = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            PaintDrawable drawable = new PaintDrawable();
            drawable.getPaint().setColor(context.getColor(color));
            images.add(drawable);
        }
        final LayerDrawable layerDrawable = new LayerDrawable(images.toArray(new Drawable[0]));

        int n = 0;
        for (int i = 0; i < 20; i++) {
            layerDrawable.setLayerGravity(i, Gravity.START | Gravity.BOTTOM);
            layerDrawable.setLayerSize(i, defaultLayoutWidth, defaultLayoutHeight * arrayNum[i]);
            layerDrawable.setLayerInsetLeft(i, n);
            n += 80;
        }
        Bitmap bitmap = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), height, Bitmap.Config.ARGB_8888);
        layerDrawable.setBounds(0, 0, layerDrawable.getIntrinsicWidth(), height);
        layerDrawable.draw(new Canvas(bitmap));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);

        return stream.toByteArray();
    }


    /**
     * ??????level????????????,??????getImageBytesLevelOverIncline????????????,???????????????inclineImageView????????????imgView?????????
     *
     * @param context
     * @param levelNum
     * @param inclineNum
     * @param height
     * @param color      //level????????????incline???????????????
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static byte[] getImageBytesInclineOverLevel(Context context, String levelNum, String inclineNum, int height, @ColorRes int color) {
        int defaultLayoutHeight = 80;
        int defaultLayoutWidth = 65;
        int levelColor = R.color.green;  //???level??????incline, level????????????incline??????,?????????????????????

        int[] arrayLevel = Arrays.stream(levelNum.split("#", -1))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] arrayIncline = Arrays.stream(inclineNum.split("#", -1))
                .mapToInt(Integer::parseInt)
                .toArray();

        final List<Drawable> images = new ArrayList<>();


        for (int i = 0; i < arrayLevel.length; i++) {
            PaintDrawable drawable = new PaintDrawable();


            if (arrayLevel[i] > arrayIncline[i]) {
                drawable.getPaint().setColor(context.getColor(color));
                Log.v("hank", "getImageBytesInclineOverLevel ?????? -> i:" + i);

            } else if (arrayLevel[i] == arrayIncline[i]) {
                drawable.getPaint().setColor(context.getColor(color));
                Log.v("hank", "getImageBytesInclineOverLevel ?????? -> i:" + i);


                //???level??????incline??????????????????level,???level,incline???????????????
            } else if (arrayLevel[i] < arrayIncline[i]) {
                drawable.getPaint().setColor(context.getResources().getColor(levelColor));
                int level = arrayLevel[i];
                int incline = arrayIncline[i];

                arrayIncline[i] = level;
                arrayLevel[i] = incline;
                Log.v("hank", "getImageBytesInclineOverLevel ?????? -> i:" + i + "arrayLevel[i] :" + arrayLevel[i] + ", arrayIncline[i]:" + arrayIncline[i]);

            }


            images.add(drawable);
        }


        final LayerDrawable layerDrawable = new LayerDrawable(images.toArray(new Drawable[0]));

        int n = 0;
        for (int i = 0; i < 20; i++) {
            layerDrawable.setLayerGravity(i, Gravity.START | Gravity.BOTTOM);
            layerDrawable.setLayerSize(i, defaultLayoutWidth, defaultLayoutHeight * arrayIncline[i]);
            layerDrawable.setLayerInsetLeft(i, n);
            n += 80;
        }
        Bitmap bitmap = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), height, Bitmap.Config.ARGB_8888);
        layerDrawable.setBounds(0, 0, layerDrawable.getIntrinsicWidth(), height);
        layerDrawable.draw(new Canvas(bitmap));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);

        return stream.toByteArray();
    }


    /**
     * ??????Incline????????????,??????getImageBytesInclineOverLevel????????????,?????????????????????levelImageView???????????????
     *
     * @param context
     * @param levelNum
     * @param inclineNum
     * @param height
     * @param color   //level????????????incline???????????????
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static byte[] getImageBytesLevelOverIncline(Context context, String levelNum, String inclineNum, int height, @ColorRes int color) {
        int defaultLayoutHeight = 80;
        int defaultLayoutWidth = 65;
        int inclineColor = R.color.green_light;  //???level??????incline, level????????????incline??????,????????????????????????

        int[] arrayLevel = Arrays.stream(levelNum.split("#", -1))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] arrayIncline = Arrays.stream(inclineNum.split("#", -1))
                .mapToInt(Integer::parseInt)
                .toArray();

        final List<Drawable> images = new ArrayList<>();


        for (int i = 0; i < arrayLevel.length; i++) {
            PaintDrawable drawable = new PaintDrawable();


            if (arrayLevel[i] > arrayIncline[i]) {
                drawable.getPaint().setColor(context.getColor(color));
                Log.v("hank", "getImageBytesLevelOverIncline ?????? -> i:" + i);

            } else if (arrayLevel[i] == arrayIncline[i]) {
                drawable.getPaint().setColor(context.getColor(color));
                Log.v("hank", "getImageBytesLevelOverIncline ?????? -> i:" + i);

                //???level??????incline?????????????????????incline,???????????????level,level,incline???????????????
            } else if (arrayLevel[i] < arrayIncline[i]) {
                drawable.getPaint().setColor(context.getResources().getColor(inclineColor));
                int level = arrayLevel[i];
                int incline = arrayIncline[i];

                arrayIncline[i] = level;
                arrayLevel[i] = incline;
                Log.v("hank", "getImageBytesLevelOverIncline ?????? -> i:" + i + "arrayLevel[i] :" + arrayLevel[i] + ", arrayIncline[i]:" + arrayIncline[i]);

            }

            images.add(drawable);
        }


        final LayerDrawable layerDrawable = new LayerDrawable(images.toArray(new Drawable[0]));

        int n = 0;
        for (int i = 0; i < 20; i++) {
            layerDrawable.setLayerGravity(i, Gravity.START | Gravity.BOTTOM);
            layerDrawable.setLayerSize(i, defaultLayoutWidth, defaultLayoutHeight * arrayLevel[i]);
            layerDrawable.setLayerInsetLeft(i, n);
            n += 80;
        }
        Bitmap bitmap = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), height, Bitmap.Config.ARGB_8888);
        layerDrawable.setBounds(0, 0, layerDrawable.getIntrinsicWidth(), height);
        layerDrawable.draw(new Canvas(bitmap));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);

        return stream.toByteArray();
    }
}
