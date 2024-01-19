package com.thisit.softwaregroup.customfonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextviewSourceSansProRegular extends AppCompatTextView {

    public TextviewSourceSansProRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextviewSourceSansProRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextviewSourceSansProRegular(Context context) {
        super(context);
        init();
    }

    @SuppressLint("WrongConstant")
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SourceSansPro-Regular.otf");
        setTypeface(tf ,1);

    }
}
