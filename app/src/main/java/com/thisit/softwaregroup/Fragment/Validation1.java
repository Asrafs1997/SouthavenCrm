package com.thisit.softwaregroup.Fragment;

import android.util.DisplayMetrics;

public class Validation1 {

    public static boolean isTabletScreen(CardFragment cardFragment) {
        DisplayMetrics metrics = new DisplayMetrics();
        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        if (diagonalInches >= 7) {
            // 7inch device or bigger
            return true;
        } else {
            // smaller device
            return false;
        }
    }
}
