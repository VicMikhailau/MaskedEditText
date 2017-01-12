package com.vicmikhailau.maskededittext;


public interface IFormattedString extends CharSequence  {
    String getUnMaskedString();
}


abstract class AbstractFormattedString implements IFormattedString{
    private String mFormattedString;
    private final String mUnmaskedString;
    final Mask mMask;


    AbstractFormattedString(Mask mask, String rawString){
        mMask = mask;
        mUnmaskedString = buildRawString(rawString);
    }


    abstract String formatString();
    abstract String buildRawString(String str);

    public String getUnMaskedString() {
        return mUnmaskedString;
    }


    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public String toString() {
        if (mFormattedString == null){
            mFormattedString = formatString();
        }
        return mFormattedString;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }
}

class FormattedString extends AbstractFormattedString {

    FormattedString(Mask mask, String rawString) {
        super(mask, rawString);
    }

    String buildRawString(String str) {
        StringBuilder builder = new StringBuilder();
        int inputLen = Math.min(mMask.size(), str.length());
        for (int i = 0; i < inputLen; i++){
            char ch = str.charAt(i);
            if (!mMask.isValidPrepopulateCharacter(ch))
                builder.append(ch);
        }
        return builder.toString();
    }

    String formatString() {
        StringBuilder builder = new StringBuilder();

        int strIndex = 0;
        int maskCharIndex = 0;
        char stringCharacter;

        while (strIndex < getUnMaskedString().length() && maskCharIndex < mMask.size()) {
            MaskCharacter maskChar = mMask.get(maskCharIndex);

            stringCharacter = getUnMaskedString().charAt(strIndex);

            if (maskChar.isValidCharacter(stringCharacter)) {
                builder.append(maskChar.processCharacter(stringCharacter));
                strIndex += 1;
                maskCharIndex += 1;
            } else if (maskChar.isPrepopulate()) {
                builder.append(maskChar.processCharacter(stringCharacter));
                maskCharIndex += 1;
            } else {
                strIndex += 1;
            }
        }

        return builder.toString();
    }



}