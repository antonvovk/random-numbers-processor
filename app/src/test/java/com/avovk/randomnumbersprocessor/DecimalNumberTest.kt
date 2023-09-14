package com.avovk.randomnumbersprocessor

import org.junit.Assert.assertEquals
import org.junit.Test

class DecimalNumberTest {

    @Test
    fun toStringTest() {
        assertEquals("0.00", DecimalNumber(0, 0).toString())
        assertEquals("0.01", DecimalNumber(0, 1).toString())
        assertEquals("0.02", DecimalNumber(0, 2).toString())
        assertEquals("0.03", DecimalNumber(0, 3).toString())
        assertEquals("0.04", DecimalNumber(0, 4).toString())
        assertEquals("0.05", DecimalNumber(0, 5).toString())
        assertEquals("0.06", DecimalNumber(0, 6).toString())
        assertEquals("0.07", DecimalNumber(0, 7).toString())
        assertEquals("0.08", DecimalNumber(0, 8).toString())
        assertEquals("0.09", DecimalNumber(0, 9).toString())
        assertEquals("0.10", DecimalNumber(0, 10).toString())
        assertEquals("0.11", DecimalNumber(0, 11).toString())
        assertEquals("0.12", DecimalNumber(0, 12).toString())
        assertEquals("0.99", DecimalNumber(0, 99).toString())
    }

    @Test
    fun testAdd() {
        assertEquals("8.00", DecimalNumber(3, 0).add(DecimalNumber(5, 0)).toString())
        assertEquals("2.02", DecimalNumber(1, 1).add(DecimalNumber(1, 1)).toString())
        assertEquals("3.00", DecimalNumber(1, 1).add(DecimalNumber(1, 99)).toString())
        assertEquals("3.98", DecimalNumber(1, 99).add(DecimalNumber(1, 99)).toString())
        assertEquals("3.99", DecimalNumber(2, 0).add(DecimalNumber(1, 99)).toString())
    }
}
