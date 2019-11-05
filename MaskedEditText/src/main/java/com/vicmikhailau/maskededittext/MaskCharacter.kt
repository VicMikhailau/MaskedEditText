package com.vicmikhailau.maskededittext


abstract class MaskCharacter {

    open val isPrepopulate: Boolean
        get() = false

    abstract fun isValidCharacter(ch: Char): Boolean

    open fun processCharacter(ch: Char): Char {
        return ch
    }

}

internal class DigitCharacter : MaskCharacter() {
    override fun isValidCharacter(ch: Char): Boolean {
        return Character.isDigit(ch)
    }

}

internal class UpperCaseCharacter : MaskCharacter() {
    override fun isValidCharacter(ch: Char): Boolean {
        return Character.isUpperCase(ch)
    }

    override fun processCharacter(ch: Char): Char {
        return Character.toUpperCase(ch)
    }
}


internal class LowerCaseCharacter : MaskCharacter() {
    override fun isValidCharacter(ch: Char): Boolean {
        return Character.isLowerCase(ch)
    }

    override fun processCharacter(ch: Char): Char {
        return Character.toLowerCase(ch)
    }
}

internal class AlphaNumericCharacter : MaskCharacter() {
    override fun isValidCharacter(ch: Char): Boolean {
        return Character.isLetterOrDigit(ch)
    }
}

internal class LetterCharacter : MaskCharacter() {
    override fun isValidCharacter(ch: Char): Boolean {
        return Character.isLetter(ch)
    }
}

internal class HexCharacter : MaskCharacter() {

    override fun isValidCharacter(ch: Char): Boolean {
        return Character.isLetterOrDigit(ch) && HEX_CHARS.indexOf(Character.toUpperCase(ch)) != -1
    }

    override fun processCharacter(ch: Char): Char {
        return Character.toUpperCase(ch)
    }

    companion object {
        private val HEX_CHARS = "0123456789ABCDEF"
    }
}

internal class LiteralCharacter : MaskCharacter {
    private var character: Char? = null

    override val isPrepopulate: Boolean
        get() = character != null

    constructor() {
        character = null
    }

    constructor(ch: Char) {
        character = ch
    }

    override fun isValidCharacter(ch: Char): Boolean {
        return character == null || character == ch
    }


    override fun processCharacter(ch: Char): Char {
        return if (character != null) character!! else ch
    }
}

