package com.vicmikhailau.maskededittext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

import java.lang.ref.WeakReference

open class MaskedWatcher(maskedFormatter: MaskedFormatter, editText: EditText) : TextWatcher {

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private val mMaskFormatter: MaskedFormatter = maskedFormatter
    private val mEditText: WeakReference<EditText> = WeakReference(editText)
    private var oldFormattedValue = ""
    private var oldCursorPosition: Int = 0

    // Listeners.
    private var beforeTextChanged: ((text: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null
    private var onTextChanged: ((text: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null
    private var afterTextChanged: ((text: Editable?) -> Unit)? = null

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    fun addTextChangedListener(
            beforeTextChanged: ((
                    text: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
            ) -> Unit)? = null,
            onTextChanged: ((
                    text: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
            ) -> Unit)? = null,
            afterTextChanged: ((text: Editable?) -> Unit)? = null) {
        this.beforeTextChanged = beforeTextChanged
        this.onTextChanged = onTextChanged
        this.afterTextChanged = afterTextChanged
    }

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
            var mask: Mask? = null
            mMaskFormatter.mMask?.let { mask = it }

            var maskLength = 0
            mMaskFormatter.maskLength?.let { maskLength = it}
            newCursorPosition = 1.coerceAtLeast(newCursorPosition.coerceAtMost(maskLength))


            var isPopulate = false
            mask?.get(newCursorPosition - 1)?.isPrepopulate?.let { isPopulate = it }
            if (isPopulate)
                newCursorPosition -= 1
        }
        newCursorPosition = 0.coerceAtLeast(newCursorPosition.coerceAtMost(formattedString.length))
        editText.setSelection(newCursorPosition)
    }

    override fun afterTextChanged(s: Editable?) {
        if (s == null)
            return

        var value = s.toString()

        var maskLength = 0
        mMaskFormatter.maskLength?.let { maskLength = it}

        if (value.length > oldFormattedValue.length && maskLength < value.length) {
            value = oldFormattedValue
        }

        val formattedString = mMaskFormatter.formatString(value)

        formattedString?.let { setFormattedText(it) }
        oldFormattedValue = formattedString.toString()
        afterTextChanged?.invoke(mEditText.get()?.text ?: s)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        mEditText.get()?.selectionStart?.let { this.oldCursorPosition = it }
        beforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(s, start, before, count)
    }
}
