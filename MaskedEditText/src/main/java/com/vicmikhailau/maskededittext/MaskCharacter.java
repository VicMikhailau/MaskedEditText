package com.vicmikhailau.maskededittext;


abstract class MaskCharacter {
    abstract public boolean isValidCharacter(char ch);

    public char processCharacter(char ch) {
        return ch;
    }

    public boolean isPrepopulate() {
        return false;
    }

}

class DigitCharacter extends MaskCharacter {
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isDigit(ch);
    }

}

class UpperCaseCharacter extends MaskCharacter {
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isUpperCase(ch);
    }

    @Override
    public char processCharacter(char ch) {
        return Character.toUpperCase(ch);
    }
}


class LowerCaseCharacter extends MaskCharacter {
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLowerCase(ch);
    }

    @Override
    public char processCharacter(char ch) {
        return Character.toLowerCase(ch);
    }
}

class AlphaNumericCharacter extends MaskCharacter {
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLetterOrDigit(ch);
    }
}

class LetterCharacter extends MaskCharacter {
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLetter(ch);
    }
}

class HexCharacter extends MaskCharacter {
    private static final String HEX_CHARS = "0123456789ABCDEF";

    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLetterOrDigit(ch) && HEX_CHARS.indexOf(Character.toUpperCase(ch)) != -1;
    }

    @Override
    public char processCharacter(char ch) {
        return Character.toUpperCase(ch);
    }
}

class LiteralCharacter extends MaskCharacter {
    private Character character;

    LiteralCharacter() {
        character = null;
    }

    LiteralCharacter(char ch) {
        character = ch;
    }

    @Override
    public boolean isValidCharacter(char ch) {
        return character == null || character == ch;
    }


    @Override
    public char processCharacter(char ch) {
        if (character != null)
            return character;
        return ch;
    }

    public boolean isPrepopulate() {
        return character != null;
    }
}

