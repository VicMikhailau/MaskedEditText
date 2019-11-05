package com.vicmikhailau.maskededittextsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vicmikhailau.maskededittext.MaskedFormatter
import com.vicmikhailau.maskededittext.MaskedWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * Use specific values for create your own mask (see example below or in xml):
     * ANYTHING_KEY = '*'
     * DIGIT_KEY = '#'
     * UPPERCASE_KEY = 'U';
     * LOWERCASE_KEY = 'L';
     * ALPHA_NUMERIC_KEY = 'A';
     * LITERAL_KEY = '\'';
     * CHARACTER_KEY = '?';
     * HEX_KEY = 'H';
     */

    /**
     * For getting unmasked text for MaskedEditText mEdtMaskedCustom just use mEdtMaskedCustom.getUnMaskedString().
     * For getting unmasked text for default EditText just use formatter.formatString(text).getUnMaskedString().
     */

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private var formatter: MaskedFormatter? = null

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMask("##/##/####")
    }

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * You cas use MaskedEditText declared in xml with attribute named mask
     * or
     * set mask in code for default EdiText
     *
     * @param mask your mask
     */
    private fun setMask(mask: String) {
        formatter = MaskedFormatter(mask)
        edtMasked.addTextChangedListener(MaskedWatcher(formatter!!, edtMasked!!))
        val s = formatter?.formatString(edtMasked.text.toString())?.unMaskedString
    }

    private fun getUnMaskedTextForEdtCustom() {
        edtMaskedCustom.unMaskedText
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
