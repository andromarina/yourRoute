package com.yourRoute.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.yourRoute.R;

public class CustomRadioButton extends RadioButton {
    public CustomRadioButton(Context context) {
        super(context);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArrayDrawable = context.obtainStyledAttributes(attrs, R.styleable.button_drawable);
        Drawable drawableLeft = typedArrayDrawable.getDrawable(R.styleable.button_drawable_drawableSrcLeft);
        if (drawableLeft != null) {
            int drawableLeftWidth = typedArrayDrawable.getDimensionPixelSize(R.styleable.button_drawable_drawableSrcWidth, 0);
            int drawableLeftHeight = typedArrayDrawable.getDimensionPixelSize(R.styleable.button_drawable_drawableSrcHeight, 0);
            drawableLeft.setBounds(0, 0, drawableLeftWidth, drawableLeftHeight);
            setCompoundDrawables(drawableLeft, null, null, null);
        }
    }

}