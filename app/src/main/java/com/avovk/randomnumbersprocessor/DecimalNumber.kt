package com.avovk.randomnumbersprocessor

class DecimalNumber(private var left: Int = 0, private var right: Int = 0) {

    init {
        if (right < 0 || right > 99) {
            throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return if (right <= 9) {
            "$left.0$right"
        } else {
            "$left.$right"
        }
    }

    fun add(value: DecimalNumber): DecimalNumber {
        left += value.left
        right += value.right
        if (right >= 100) {
            left += 1
            right -= 100
        }
        return this
    }

    fun getLeft() = left

    fun getRight() = right
}
