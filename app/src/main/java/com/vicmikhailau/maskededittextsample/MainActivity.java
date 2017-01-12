package com.vicmikhailau.maskededittextsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vicmikhailau.maskededittext.MaskedEditText;
import com.vicmikhailau.maskededittext.MaskedFormatter;
import com.vicmikhailau.maskededittext.MaskedWatcher;

public class MainActivity extends AppCompatActivity {

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
     * For getting unmasked text for default EditText with MaskedWatcher mMaskedWatcher just use mMaskedWatcher.getUnMaskedString().
     */

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private EditText mEdtMasked;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setMask("##/##/####");
    }

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    private void findViews() {
        mEdtMasked = (EditText) findViewById(R.id.edt_masked);
    }

    /**
     * You cas use MaskedEditText declared in xml with attribute named mask
     * or
     * set mask in code for default EdiText
     *
     * @param mask your mask
     */
    private void setMask(String mask) {
        MaskedFormatter formatter = new MaskedFormatter(mask);
        mEdtMasked.addTextChangedListener(new MaskedWatcher(formatter, mEdtMasked));
        // Example how to get unmasked string in this case.
        String s = formatter.formatString(mEdtMasked.getText().toString()).getUnMaskedString();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
