package com.vicmikhailau.maskededittext;

import java.util.ArrayList;
import java.util.List;


public class Mask {
    private String mRawMaskString;
    private List<MaskCharacter> mMask;
    private MaskCharacterFabric mFabric;
    private List<MaskCharacter> mPrepopulateCharacter;

    public Mask() {
        mFabric = new MaskCharacterFabric();
    }

    public Mask(String fmtString) {
        this();
        mRawMaskString = fmtString;
        mMask = buildMask(mRawMaskString);
    }

    public String getFormatString() {
        return mRawMaskString;
    }

    public int size() {
        return mMask.size();
    }

    public MaskCharacter get(int index) {
        return mMask.get(index);
    }

    public boolean isValidPrepopulateCharacter(char ch){
        for (MaskCharacter maskCharacter : mPrepopulateCharacter){
            if (maskCharacter.isValidCharacter(ch)) {
                return true;
            }
        }
        return false;
    }


    private List<MaskCharacter> buildMask(String fmtString) {
        List<MaskCharacter> result = new ArrayList<>();
        mPrepopulateCharacter = new ArrayList<>();
        for (char ch : fmtString.toCharArray()) {
            MaskCharacter maskCharacter =  mFabric.buildCharacter(ch);
            if (maskCharacter.isPrepopulate()) {
                mPrepopulateCharacter.add(maskCharacter);
            }
            result.add(maskCharacter);
        }
        return result;
    }


    public IFormattedString getFormattedString(String value) {
        return new FormattedString(this, value);
    }



}
