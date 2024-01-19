package com.thisit.softwaregroup.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewBadScriptRegular extends AppCompatTextView {

    public TextViewBadScriptRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewBadScriptRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewBadScriptRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/BadScript_Regular.otf");
            setTypeface(tf);
        }
    }

}
