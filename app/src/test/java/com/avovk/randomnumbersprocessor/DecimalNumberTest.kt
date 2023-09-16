package com.avovk.randomnumbersprocessor

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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

    @Test
    fun testEquals() {
        assertTrue(DecimalNumber(5, 0) == DecimalNumber(5, 0))
        assertTrue(DecimalNumber(0, 5) == DecimalNumber(0, 5))
        assertTrue(DecimalNumber(5, 5) == DecimalNumber(5, 5))
        assertTrue(DecimalNumber(0, 0) == DecimalNumber(0, 0))
        assertFalse(DecimalNumber(0, 1) == DecimalNumber(1, 0))
        assertFalse(DecimalNumber(1, 0) == DecimalNumber(0, 1))
        assertFalse(DecimalNumber(1, 1) == DecimalNumber(2, 1))
    }

    @Test
    fun testFromString() {
        assertEquals(70, DecimalNumber.fromString("70").getLeft())
        assertEquals(0, DecimalNumber.fromString("70").getRight())

        assertEquals(70, DecimalNumber.fromString("70.").getLeft())
        assertEquals(0, DecimalNumber.fromString("70.").getRight())
        assertEquals(70, DecimalNumber.fromString("70.00").getLeft())
        assertEquals(0, DecimalNumber.fromString("70.00").getRight())

        assertEquals(70, DecimalNumber.fromString("70,").getLeft())
        assertEquals(0, DecimalNumber.fromString("70,").getRight())
        assertEquals(70, DecimalNumber.fromString("70,00").getLeft())
        assertEquals(0, DecimalNumber.fromString("70,00").getRight())

        assertEquals(70, DecimalNumber.fromString("70.23").getLeft())
        assertEquals(23, DecimalNumber.fromString("70.23").getRight())
        assertEquals(54, DecimalNumber.fromString("54.99").getLeft())
        assertEquals(99, DecimalNumber.fromString("54.99").getRight())
    }
}
