package com.vicmikhailau.maskededittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class MaskedEditText extends AppCompatEditText {

    // ===========================================================
    // Fields
    // ===========================================================

    private MaskedFormatter mMaskedFormatter;
    private MaskedWatcher mMaskedWatcher;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MaskedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaskedEditText);

        if (typedArray.hasValue(R.styleable.MaskedEditText_mask)) {
            String maskStr = typedArray.getString(R.styleable.MaskedEditText_mask);

            if (maskStr != null && !maskStr.isEmpty()) {
                setMask(maskStr);
            }
        }

        typedArray.recycle();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public String getMaskString() {
        return mMaskedFormatter.getMaskString();
    }

    public String getUnMaskedText() {
        String currentText = getText().toString();
        IFormattedString formattedString = mMaskedFormatter.formatString(currentText);
        return formattedString.getUnMaskedString();
    }

    public void setMask(String mMaskStr) {
        mMaskedFormatter = new MaskedFormatter(mMaskStr);

        if (mMaskedWatcher != null) {
            removeTextChangedListener(mMaskedWatcher);
        }

        mMaskedWatcher = new MaskedWatcher(mMaskedFormatter, this);
        addTextChangedListener(mMaskedWatcher);
    }

}
