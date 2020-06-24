package com.ajiani.maidahui.interfaze;

import android.view.View;

public interface AutoPlayItem {
    void setActive();
    void deactivate();
    View getAutoplayView();
}
