package com.vicmikhailau.maskededittextsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vicmikhailau.maskededittext.MaskedFormatter
import com.vicmikhailau.maskededittext.MaskedWatcher
import com.vicmikhailau.maskededittextsample.databinding.ActivityMainBinding

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

    private lateinit var binding: ActivityMainBinding

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        formatter?.let{
            binding.edtMasked.addTextChangedListener(MaskedWatcher(it, binding.edtMasked))
        }
        val s = formatter?.formatString(binding.edtMasked.text.toString())?.unMaskedString
    }

    private fun getUnMaskedTextForEdtCustom() {
        binding.edtMaskedCustom.unMaskedText
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
