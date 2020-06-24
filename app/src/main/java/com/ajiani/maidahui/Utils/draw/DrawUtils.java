package com.ajiani.maidahui.Utils.draw;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.MyApp;

public class DrawUtils {
    public static Drawable setStroke(int color,Drawable drawable){

        GradientDrawable myGrad = (GradientDrawable)drawable;
        myGrad.setStroke(1, MyApp.getApp().getResources().getColor(color));
        return myGrad;
    }
    public static Drawable setSolid(int color,Drawable drawable){

        GradientDrawable myGrad = (GradientDrawable)drawable;
        myGrad.setColor( MyApp.getApp().getResources().getColor(color));
        return myGrad;
    }
}
