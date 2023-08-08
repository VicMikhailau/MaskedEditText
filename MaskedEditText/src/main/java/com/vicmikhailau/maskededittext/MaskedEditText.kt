package com.vicmikhailau.maskededittext

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.vicmikhailau.maskededittext.R.*

class MaskedEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private var maskedFormatter: MaskedFormatter? = null
    var maskedWatcher: MaskedWatcher? = null

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    val maskString: String?
        get() = maskedFormatter?.maskString

    val unMaskedText: String?
        get() {
            val currentText = text?.toString()
            val formattedString = currentText?.let { maskedFormatter?.formatString(it) }
            return formattedString?.unMaskedString
        }

    init {

        val typedArray = context.obtainStyledAttributes(attrs, styleable.MaskedEditText)

        if (typedArray.hasValue(styleable.MaskedEditText_mask)) {
            val maskStr = typedArray.getString(styleable.MaskedEditText_mask)

            if (!maskStr.isNullOrEmpty()) {
                setMask(maskStr)
            }
        }

        typedArray.recycle()
    }

    fun setMask(mMaskStr: String) {
        maskedFormatter = MaskedFormatter(mMaskStr)

        if (maskedWatcher != null) {
            removeTextChangedListener(maskedWatcher)
        }

        maskedFormatter?.let { maskedWatcher = MaskedWatcher(it, this) }
        addTextChangedListener(maskedWatcher)
    }
}

/**
 * Add an action which will be invoked before the text changed.
 * @return the [MaskedWatcher] added to the MaskedEditText
 */
fun MaskedEditText.doBeforeMaskedTextChanged(
        action: (
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
        ) -> Unit
) = maskedWatcher?.apply {
    addTextChangedListener(beforeTextChanged = action)
}

/**
 * Add an action which will be invoked when the text is changing.
 * @return the [MaskedWatcher] added to the MaskedEditText
 */
fun MaskedEditText.doOnMaskedTextChanged(
        action: (
                text: CharSequence?,
                start: Int,
                before: Int,
                count: Int
        ) -> Unit
) = maskedWatcher?.apply {
    addTextChangedListener(onTextChanged = action)
}

/**
 * Add an action which will be invoked after the text changed.
 * @return the [MaskedWatcher] added to the MaskedEditText
 */
fun MaskedEditText.doAfterMaskedTextChanged(
        action: (text: Editable?) -> Unit
) = maskedWatcher?.apply {
    addTextChangedListener(afterTextChanged = action)
}

/**
 * Add a text changed listener to this MaskedEditTest using the provided actions
 * @return the [MaskedWatcher] added to the MaskedEditText
 */
fun MaskedEditText.addMaskedTextChangedListener(
        beforeMaskedTextChanged: ((
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
        ) -> Unit)? = null,
        onMaskedTextChanged: ((
                text: CharSequence?,
                start: Int,
                before: Int,
                count: Int
        ) -> Unit)? = null,
        afterMaskedTextChanged: ((text: Editable?) -> Unit)? = null
) = maskedWatcher?.apply {
    addTextChangedListener(
            beforeMaskedTextChanged,
            onMaskedTextChanged,
            afterMaskedTextChanged
    )
}
