package com.vicmikhailau.maskededittext;


class MaskCharacterFabric {
    private static final char ANYTHING_KEY = '*';
    private static final char DIGIT_KEY = '#';
    private static final char UPPERCASE_KEY = 'U';
    private static final char LOWERCASE_KEY = 'L';
    private static final char ALPHA_NUMERIC_KEY = 'A';
    private static final char CHARACTER_KEY = '?';
    private static final char HEX_KEY = 'H';

    MaskCharacter buildCharacter(char ch) {
        MaskCharacter result = null;
        switch (ch) {
            case ANYTHING_KEY:
                result = new LiteralCharacter();
                break;
            case DIGIT_KEY:
                result = new DigitCharacter();
                break;
            case UPPERCASE_KEY:
                result = new UpperCaseCharacter();
                break;
            case LOWERCASE_KEY:
                result = new LowerCaseCharacter();
                break;
            case ALPHA_NUMERIC_KEY:
                result = new AlphaNumericCharacter();
                break;
            case CHARACTER_KEY:
                result = new LetterCharacter();
                break;
            case HEX_KEY:
                result = new HexCharacter();
                break;
            default: {
                result = new LiteralCharacter(ch);
            }

        }
        return result;
    }
}

