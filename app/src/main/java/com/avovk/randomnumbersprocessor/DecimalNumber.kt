package com.avovk.randomnumbersprocessor

import java.math.BigDecimal

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

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is DecimalNumber) {
            return false
        }
        return left == other.left && right == other.right
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

    fun toBigDecimal(): BigDecimal {
        return BigDecimal(toString())
    }

    fun getLeft() = left

    fun getRight() = right

    companion object {

        fun fromString(value: String): DecimalNumber {
            val parts = value.split(".", ",")
            return if (parts.size == 2 && parts[1].isNotBlank()) {
                if (parts[1].length == 1) {
                    DecimalNumber(parts[0].toInt(), parts[1].toInt() * 10)
                } else {
                    DecimalNumber(parts[0].toInt(), parts[1].toInt())
                }
            } else {
                DecimalNumber(parts[0].toInt(), 0)
            }
        }

        fun minus(a: DecimalNumber, b: DecimalNumber): DecimalNumber {
            var leftPart = a.getLeft() - b.getLeft()
            var rightPart = a.getRight() - b.getRight()
            if (rightPart < 0) {
                leftPart -= 1
                rightPart += 100
            }
            if (leftPart < 0 && rightPart != 0) {
                leftPart += 1
                rightPart = 100 - rightPart
            }
            return DecimalNumber(leftPart, rightPart)
        }
    }
}
