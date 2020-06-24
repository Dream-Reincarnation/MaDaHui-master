package com.ajiani.maidahui.Utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.login.LoginActivity;
import com.ajiani.maidahui.activity.login.PhoneActivity;

public class JumpUtils {

    private static Intent intent;

    public static void gotoActivity(Context context, Class<? extends Activity> activity) {
        intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public static void gotoLoginActivity(Activity context) {
        context.startActivityForResult(new Intent(context, LoginActivity.class),14);

    }
    //带参数跳转

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void gotoActivity(Context context, Class<? extends Activity> activity, Bundle bundle) {
        intent = new Intent(context, activity);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        // activity.overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void gotoActivity(Activity context, Class<? extends Activity> activity, Bundle bundle,int requestCode) {
        intent = new Intent(context, activity);
        intent.putExtra("bundle", bundle);
        context.startActivityForResult(intent, requestCode, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        // activity.overridePendingTransition(R.anim.zoom_enter,
        // R.anim.zoom_exit);
    }

}
