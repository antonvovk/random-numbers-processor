package com.avovk.randomnumbersprocessor

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal

class CalculatorProcessorTest {

    @Test
    fun test1() {
        for (i in 0..1000) {
            val rangeMin = "23"
            val rangeMax = "24"
            val amountOfNumbers = 3
            val desiredSum = "70"
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= BigDecimal(rangeMin))
                assertTrue(number.toBigDecimal() <= BigDecimal(rangeMax))
            }

            assertEquals(desiredSum, actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }

    @Test
    fun test2() {
        for (i in 0..1000) {
            val rangeMin = "20"
            val rangeMax = "22"
            val amountOfNumbers = 5
            val desiredSum = "105"
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= BigDecimal(rangeMin))
                assertTrue(number.toBigDecimal() <= BigDecimal(rangeMax))
            }

            assertEquals(desiredSum, actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }

    @Test
    fun test3() {
        for (i in 0..1000) {
            val rangeMin = "22"
            val rangeMax = "24"
            val amountOfNumbers = 9
            val desiredSum = "210"
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= BigDecimal(rangeMin))
                assertTrue(number.toBigDecimal() <= BigDecimal(rangeMax))
            }

            assertEquals(desiredSum, actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }

    @Test
    fun test4() {
        for (i in 0..1000) {
            val rangeMin = "23"
            val rangeMax = "24"
            val amountOfNumbers = 3
            val desiredSum = "70.19"
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= BigDecimal(rangeMin))
                assertTrue(number.toBigDecimal() <= BigDecimal(rangeMax))
            }

            assertEquals(desiredSum, actualSum.toString())
        }
    }

    @Test
    fun test5() {
        for (i in 0..1000) {
            val rangeMin = "21"
            val rangeMax = "23"
            val amountOfNumbers = 6
            val desiredSum = "137"
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= BigDecimal(rangeMin))
                assertTrue(number.toBigDecimal() <= BigDecimal(rangeMax))
            }

            assertEquals(desiredSum, actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }

    @Test
    fun test6() {
        for (i in 0..1000) {
            val rangeMin = "21"
            val rangeMax = "23"
            val amountOfNumbers = 7
            val desiredSum = "160"
            val calculatorProcessor =
                CalculatorProcessor(rangeMin, rangeMax, amountOfNumbers, desiredSum)
            val result = calculatorProcessor.calculate()

            println("\n\nTHE RESULT: ${result.contentToString()}")

            val actualSum = DecimalNumber(0, 0)
            for (number in result) {
                actualSum.add(number)
                assertTrue(number.toBigDecimal() >= BigDecimal(rangeMin))
                assertTrue(number.toBigDecimal() <= BigDecimal(rangeMax))
            }

            assertEquals(desiredSum, actualSum.getLeft().toString())
            assertEquals(0, actualSum.getRight())
        }
    }
}
