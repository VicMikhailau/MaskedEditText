package com.vicmikhailau.maskededittext;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

public class MaskedWatcher implements TextWatcher {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private MaskedFormatter mMaskFormatter;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MaskedWatcher(String mask) {
        mMaskFormatter = new MaskedFormatter(mask);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public String getMask() {
        return mMaskFormatter.getMask();
    }

    public void setMask(String mask) {
        mMaskFormatter.setMask(mask);
    }

    public String getUnMaskedString() {
        return mMaskFormatter.getUnMaskedString();
    }

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    @Override
    public void afterTextChanged(Editable s) {
        String filtered = mMaskFormatter.valueToString(s);

        if (!TextUtils.equals(s, filtered)) {
            s.replace(0, s.length(), filtered);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
