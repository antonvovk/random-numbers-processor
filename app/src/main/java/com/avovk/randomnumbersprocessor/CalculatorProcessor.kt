package com.avovk.randomnumbersprocessor

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class CalculatorProcessor(
    rangeMin: BigDecimal,
    rangeMax: BigDecimal,
    private val amountOfNumbers: Int,
    desiredSum: String
) {

    private val rangeMin: BigDecimal
    private var rangeMax: BigDecimal
    private val initialRangeMax: BigDecimal
    private val desiredSum: DecimalNumber

    init {
        this.rangeMin = rangeMin.setScale(2, RoundingMode.HALF_UP)
        this.rangeMax = rangeMax.setScale(2, RoundingMode.HALF_UP)
        this.initialRangeMax = rangeMax.setScale(2, RoundingMode.HALF_UP)
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
        if (leftPart < rangeMin.toInt()) {
            leftPart = rangeMin.toInt()
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
}
