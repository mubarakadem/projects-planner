package com.iia.projectsplanner.common.ui

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PieChartKtTest{

    @Test
    fun pointsToStartAndSweepAngle(){
        val n = 2f.percentOf(8f)
        assertEquals(0.25f, n)
    }
}