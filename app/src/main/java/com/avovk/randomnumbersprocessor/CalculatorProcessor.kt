package com.avovk.randomnumbersprocessor

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class CalculatorProcessor(
    rangeMin: String,
    rangeMax: String,
    private val amountOfNumbers: Int,
    desiredSum: String
) {

    private var rangeMin: BigDecimal
    private var rangeMax: BigDecimal
    private val initialRangeMin: BigDecimal
    private val initialRangeMax: BigDecimal
    private val initialRangeMaxDecimalNumber: DecimalNumber
    private val desiredSum: DecimalNumber

    init {
        this.rangeMin = BigDecimal(rangeMin).setScale(2, RoundingMode.HALF_UP)
        this.rangeMax = BigDecimal(rangeMax).setScale(2, RoundingMode.HALF_UP)
        this.initialRangeMin = this.rangeMin.setScale(2, RoundingMode.HALF_UP)
        this.initialRangeMax = this.rangeMax.setScale(2, RoundingMode.HALF_UP)
        this.initialRangeMaxDecimalNumber = DecimalNumber.fromString(rangeMax)
        this.desiredSum = DecimalNumber.fromString(desiredSum)
    }

    fun calculate(): Array<DecimalNumber> {
        val result = Array(amountOfNumbers) { DecimalNumber(0, 0) }

        for (loop in 0..10_000) {
            val currentSum = DecimalNumber()

            for (i in 0 until amountOfNumbers - 1) {
                result[i] = randomNumber()
                currentSum.add(result[i])
            }

            val lastNumber = calculateLastNumber(currentSum)
            result[amountOfNumbers - 1] = lastNumber
            currentSum.add(lastNumber)

            if (currentSum == desiredSum) {
                return result
            } else if (lastNumber == initialRangeMaxDecimalNumber) {
                increaseMinRange()
            } else {
                decreaseMaxRange()
                if (rangeMax == rangeMin) {
                    rangeMax = initialRangeMax
                }
            }
        }

        return Array(amountOfNumbers) { DecimalNumber(0, 0) }
    }

    private fun randomNumber(): DecimalNumber {
        val randomBigDecimal =
            BigDecimal(Random.nextDouble(rangeMin.toDouble(), rangeMax.toDouble())).setScale(
                2, RoundingMode.HALF_UP
            )
        val stringParts = randomBigDecimal.toString().split(".")
        return DecimalNumber(stringParts[0].toInt(), stringParts[1].toInt())
    }

    private fun calculateLastNumber(currentSum: DecimalNumber): DecimalNumber {
        val diff = DecimalNumber.minus(desiredSum, currentSum)
        var leftPart = diff.getLeft()
        var rightPart = diff.getRight()

        if (leftPart > initialRangeMax.toInt()) {
            leftPart = initialRangeMax.toInt()
        }
        if (leftPart < initialRangeMin.toInt()) {
            leftPart = initialRangeMin.toInt()
        }

        if (leftPart == initialRangeMax.toInt() && rightPart > 0) {
            rightPart = 0
        }

        return DecimalNumber(leftPart, rightPart)
    }

    private fun decreaseMaxRange() {
        rangeMax -= if ((rangeMax - rangeMin) <= BigDecimal.TEN) {
            BigDecimal("0.01")
        } else {
            BigDecimal.ONE
        }
    }

    private fun increaseMinRange() {
        rangeMin += if ((rangeMax - rangeMin) <= BigDecimal.TEN) {
            BigDecimal("0.01")
        } else {
            BigDecimal.ONE
        }
    }
}
