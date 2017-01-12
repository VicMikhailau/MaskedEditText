package com.vicmikhailau.maskededittext;


public class MaskedFormatter {
    Mask mMask;

    MaskedFormatter() {
        mMask = null;
    }

    public MaskedFormatter(String fmtString) {
        this();
        this.setMask(fmtString);
    }


    public String getMaskString() {
        if (mMask != null) {
            return mMask.getFormatString();
        }
        return null;
    }

    public int getMaskLength() {
        return mMask.size();
    }

    public void setMask(String fmtString) {
        mMask = new Mask(fmtString);
    }


    public IFormattedString formatString(String value) {
        return mMask.getFormattedString(value);
    }

}
