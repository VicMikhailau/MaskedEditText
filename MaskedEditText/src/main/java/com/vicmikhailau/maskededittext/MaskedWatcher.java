package com.vicmikhailau.maskededittext;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.support.v7.widget.AppCompatEditText;

import java.lang.ref.WeakReference;

public class MaskedWatcher implements TextWatcher {
    // ===========================================================
    // Fields
    // ===========================================================

    private final WeakReference<MaskedFormatter> mMaskFormatter;
    private final WeakReference<EditText> mEditText;
    private String oldFormattedValue = "";
    private int oldCursorPosition;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MaskedWatcher(MaskedFormatter maskedFormatter, EditText editText) {
        mMaskFormatter = new WeakReference<>(maskedFormatter);
        mEditText = new WeakReference<>(editText);
    }

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    private void setFormattedText(IFormattedString formattedString) {
        EditText editText = mEditText.get();
        if (editText == null) {
            return;
        }

        int deltaLength = formattedString.length() - oldFormattedValue.length();


        editText.removeTextChangedListener(this);
        editText.setText(formattedString);
        editText.addTextChangedListener(this);

        int newCursorPosition = oldCursorPosition;

        if (deltaLength > 0) {
            newCursorPosition += deltaLength;
        } else if (deltaLength < 0) {
            newCursorPosition -= 1;
        } else {
            Mask mask = mMaskFormatter.get().mMask;
            newCursorPosition = Math.max(1, Math.min(newCursorPosition, mMaskFormatter.get().getMaskLength()));
            if (mask.get(newCursorPosition - 1).isPrepopulate())
                newCursorPosition -= 1;
        }
        newCursorPosition = Math.max(0, Math.min(newCursorPosition, formattedString.length()));
        editText.setSelection(newCursorPosition);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == null)
            return;

        String value = s.toString();

        if (value.length() > oldFormattedValue.length() &&  mMaskFormatter.get().getMaskLength() < value.length()) {
            value = oldFormattedValue;
        }

        IFormattedString formattedString = mMaskFormatter.get().formatString(value);

        setFormattedText(formattedString);
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
