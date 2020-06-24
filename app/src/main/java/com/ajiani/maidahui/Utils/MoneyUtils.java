package com.ajiani.maidahui.Utils;

import android.util.Log;

import java.text.DecimalFormat;

public class MoneyUtils {
    public static float getBen(float f1, int i) {
        f1 = f1 * 100;

        float f2 = f1 / i;

        return f2 / 100;
    }

    public static float getli(float f1, int i) {
      f1 = f1 * 10;
        if(i==3){
            float f2 = (float) (f1 * 2.3 / i);
            return f2 / 1000;
        }else if(i==6){
            float f2 = (float) (f1 * 4.5 / i);
            return f2 / 1000;
        }else if(i==12){
            float f2 = (float) (f1 * 7.5 / i);
            return f2 / 1000;
        }
       return 0;
    }

    public static String get2(float f)  {
        DecimalFormat mFormat = new DecimalFormat(".00");
        String formatNum3 = mFormat.format(f);
        return formatNum3;
    }
}
