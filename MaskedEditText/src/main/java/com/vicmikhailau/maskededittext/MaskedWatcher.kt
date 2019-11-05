package com.vicmikhailau.maskededittext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

import java.lang.ref.WeakReference

class MaskedWatcher(maskedFormatter: MaskedFormatter, editText: EditText) : TextWatcher {

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private val mMaskFormatter: WeakReference<MaskedFormatter> = WeakReference(maskedFormatter)
    private val mEditText: WeakReference<EditText> = WeakReference(editText)
    private var oldFormattedValue = ""
    private var oldCursorPosition: Int = 0

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    private fun setFormattedText(formattedString: IFormattedString) {
        val editText = mEditText.get() ?: return

        val deltaLength = formattedString.length - oldFormattedValue.length


        editText.removeTextChangedListener(this)
        editText.setText(formattedString)
        editText.addTextChangedListener(this)

        var newCursorPosition = oldCursorPosition

        if (deltaLength > 0) {
            newCursorPosition += deltaLength
        } else if (deltaLength < 0) {
            newCursorPosition -= 1
        } else {
            val mask = mMaskFormatter.get()!!.mMask
            newCursorPosition = 1.coerceAtLeast(newCursorPosition.coerceAtMost(mMaskFormatter.get()!!.maskLength))
            if (mask!![newCursorPosition - 1].isPrepopulate)
                newCursorPosition -= 1
        }
        newCursorPosition = 0.coerceAtLeast(newCursorPosition.coerceAtMost(formattedString.length))
        editText.setSelection(newCursorPosition)
    }

    override fun afterTextChanged(s: Editable?) {
        if (s == null)
            return

        var value = s.toString()

        if (value.length > oldFormattedValue.length && mMaskFormatter.get()!!.maskLength < value.length) {
            value = oldFormattedValue
        }

        val formattedString = mMaskFormatter.get()!!.formatString(value)

        setFormattedText(formattedString)
        oldFormattedValue = formattedString.toString()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        this.oldCursorPosition = mEditText.get()!!.selectionStart
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
}
