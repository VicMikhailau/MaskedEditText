package com.vicmikhailau.maskededittext;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;

public class MaskedWatcher implements TextWatcher {
    // ===========================================================
    // Fields
    // ===========================================================

    private MaskedFormatter mMaskFormatter;
    private final WeakReference<EditText> mEditText;
    private String oldFormattedValue = "";
    private int oldCursorPosition;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MaskedWatcher(String mask, EditText editText) {
        mMaskFormatter = new MaskedFormatter(mask);
        mEditText = new WeakReference<>(editText);
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

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    @Override
    public void afterTextChanged(Editable s) {

        if (s == null)
            return;

        String value =  s.toString();

        if (value.length() > oldFormattedValue.length() &&  mMaskFormatter.getMaskLength() < value.length()) {
            value = oldFormattedValue;
        }

        IFormattedString formattedString = mMaskFormatter.formatString(value);

        if (!TextUtils.equals(s, formattedString)) {
            s.replace(0, s.length(), formattedString);
        }

        int currentPosition =  mEditText.get().getSelectionStart();
        if (oldCursorPosition - currentPosition > 0) {
            int newPosition;
            if (oldFormattedValue.length() - formattedString.length() > 1)
                newPosition = Math.max(0, Math.min(formattedString.length(), oldCursorPosition));
            else
                newPosition = Math.max(0, Math.min(formattedString.length(), currentPosition));
            mEditText.get().setSelection(newPosition);
        }
        oldFormattedValue = formattedString.toString();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        this.oldCursorPosition = mEditText.get().getSelectionStart();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
