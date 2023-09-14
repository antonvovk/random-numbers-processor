package com.avovk.randomnumbersprocessor

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class CalculatorProcessor(
    private val rangeMin: BigDecimal,
    private var rangeMax: BigDecimal,
    private val amountOfNumbers: Int,
    private val desiredSum: Int
) {

    fun calculate(): Array<DecimalNumber> {
        val result = Array(amountOfNumbers) { DecimalNumber(0, 0) }

        for (loop in 0..10_000) {
            val currentSum = DecimalNumber()

            for (i in 0 until amountOfNumbers - 1) {
                result[i] = randomNumber()
                currentSum.add(result[i])
            }

            val lastNumberLeftPart = calculateLastNumberLeftPart(currentSum)
            if (currentSum.getRight() == 0) {
                result[amountOfNumbers - 1] = DecimalNumber(lastNumberLeftPart)
            } else {
                result[amountOfNumbers - 1] =
                    DecimalNumber(lastNumberLeftPart, 100 - currentSum.getRight())
            }
            currentSum.add(result[amountOfNumbers - 1])

            if (currentSum.getLeft() == desiredSum && currentSum.getRight() == 0) {
                return result
            } else {
                decreaseMaxRange()
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

    private fun calculateLastNumberLeftPart(currentSum: DecimalNumber): Int {
        var leftPart = desiredSum - currentSum.getLeft()
        if (leftPart >= rangeMax.toInt()) {
            leftPart = rangeMax.toInt()
        } else if (leftPart <= rangeMin.toInt()) {
            leftPart = rangeMin.toInt()
        }
        return leftPart
    }

    private fun decreaseMaxRange() {
        rangeMax -= if ((rangeMax - rangeMin) <= BigDecimal.ONE) {
            BigDecimal("0.1")
        } else {
            BigDecimal.ONE
        }
        println("Max range changed to: $rangeMax")
    }
}
