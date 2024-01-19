package com.thisit.softwaregroup.customfonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextviewSourceSansProBold extends AppCompatTextView {

    public TextviewSourceSansProBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextviewSourceSansProBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextviewSourceSansProBold(Context context) {
        super(context);
        init();
    }

    @SuppressLint("WrongConstant")
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SourceSansPro-Bold.otf");
        setTypeface(tf ,1);

    }
}
