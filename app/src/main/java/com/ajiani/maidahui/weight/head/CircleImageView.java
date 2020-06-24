package com.ajiani.maidahui.weight.head;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.ajiani.maidahui.Utils.DPUtils;

@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView {
    public CircleImageView(Context context) {
        super(context);

    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE); //这里的颜色决定了边缘的颜色

        Drawable drawable = getDrawable();
        if(drawable == null){
            return ;
        }
        if(getWidth() == 0 || getHeight() == 0){
            return ;
        }

        Bitmap b = ((BitmapDrawable)drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth() ;
        int h = getHeight() ;
        System.out.println("Windth="+w+",Height="+h);

        //圆形ImageView的半径为布局中的ImageView定义大小
        Bitmap roundBitmap = getCroppedBitmap(bitmap, w);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(w / 2, h / 2, w / 2 , paint);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }
    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {

        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.WHITE);
        //第三个参数减去的数值为白边的宽度.
        canvas.drawCircle(sbmp.getWidth() / 2 ,sbmp.getHeight() / 2 , sbmp.getWidth() / 2 - (DPUtils.dp2px(1)), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }






}

