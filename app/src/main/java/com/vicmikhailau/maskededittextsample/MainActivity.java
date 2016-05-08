package com.vicmikhailau.maskededittextsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.vicmikhailau.maskededittext.MaskedEditText;
import com.vicmikhailau.maskededittext.MaskedWatcher;

public class MainActivity extends AppCompatActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private MaskedEditText mEdtMaskedCustom;

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
        mEdtMaskedCustom = (MaskedEditText) findViewById(R.id.edt_masked_custom);
        mEdtMasked = (EditText) findViewById(R.id.edt_masked);
    }

    /**
     * You cas use MaskedEditText declared in xml with attribute named mask
     * or
     * set mask in code for default EdiText
     * @param mask your mask
     */
    private void setMask(String mask) {
        mEdtMasked.addTextChangedListener(new MaskedWatcher(mask));
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
