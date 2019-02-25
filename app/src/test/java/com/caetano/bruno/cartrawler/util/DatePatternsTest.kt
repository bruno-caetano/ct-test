package com.caetano.bruno.cartrawler.util

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import java.util.Calendar
import java.util.Locale

@RunWith(BlockJUnit4ClassRunner::class)
class DatePatternsTest {

    @Test
    fun `Test date format`() {
        val calendar = Calendar.getInstance(Locale.US)
        calendar.set(2015, 2, 15, 10, 0, 0)
        val date = calendar.time
        val formatted = date.format("dd MMM, h:mm")
        Assert.assertEquals("15 Mar, 10:00", formatted)
    }

}