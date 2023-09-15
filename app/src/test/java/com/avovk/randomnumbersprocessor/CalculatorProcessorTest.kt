package com.avovk.randomnumbersprocessor

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal

class CalculatorProcessorTest {

    @Test
    fun test1() {
        for (i in 0..50) {
            val rangeMin = BigDecimal("23")
            val rangeMax = BigDecimal("24")
            val amountOfNumbers = 3
            val desiredSum = 70
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= rangeMin)
                assertTrue(number.toBigDecimal() <= rangeMax)
            }

            assertEquals(desiredSum.toString(), actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }

    @Test
    fun test2() {
        for (i in 0..50) {
            val rangeMin = BigDecimal("20")
            val rangeMax = BigDecimal("22")
            val amountOfNumbers = 5
            val desiredSum = 105
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= rangeMin)
                assertTrue(number.toBigDecimal() <= rangeMax)
            }

            assertEquals(desiredSum.toString(), actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }

    @Test
    fun test3() {
        for (i in 0..50) {
            val rangeMin = BigDecimal("22")
            val rangeMax = BigDecimal("24")
            val amountOfNumbers = 9
            val desiredSum = 210
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= rangeMin)
                assertTrue(number.toBigDecimal() <= rangeMax)
            }

            assertEquals(desiredSum.toString(), actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }
}
