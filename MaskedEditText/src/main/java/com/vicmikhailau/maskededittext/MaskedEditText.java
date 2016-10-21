package com.vicmikhailau.maskededittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MaskedEditText extends AppCompatEditText {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private String mMask;

    private MaskedWatcher mMaskedWatcher;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MaskedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaskedEditText);

        if (typedArray.hasValue(R.styleable.MaskedEditText_mask)) {
            mMask = typedArray.getString(R.styleable.MaskedEditText_mask);

            if (mMask != null && !mMask.isEmpty()) {
                mMaskedWatcher = new MaskedWatcher(mMask);
                addTextChangedListener(mMaskedWatcher);
            }
        }

        typedArray.recycle();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public String getMask() {
        return mMask;
    }

    public void setMask(String mMask) {
        this.mMask = mMask;

        if (mMaskedWatcher != null) {
            removeTextChangedListener(mMaskedWatcher);
        }

        mMaskedWatcher = new MaskedWatcher(this.mMask);
        addTextChangedListener(mMaskedWatcher);
    }

    public String getUnMaskedString() {
        return mMaskedWatcher != null
                ? (mMaskedWatcher.getUnMaskedString() == null ? getText().toString() : mMaskedWatcher.getUnMaskedString())
                : getText().toString();
    }

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
