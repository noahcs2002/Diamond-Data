package com.dd.api.restapi.calculators;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PitcherStatisticsCalculatorTests {

    @Test
    public void basicUpdateTest() {
        double ip = PitcherStatisticsCalculator.updateInningsPitched(8.2);
        assertEquals(8.2, ip, 0.0);
    }

    @Test
    public void complexIpUpdate() {
       double ip = PitcherStatisticsCalculator.updateInningsPitched(8.3);
       assertEquals(9.0, ip, 0.0);
    }

    @Test
    public void complexUpdate2() {
        double ip = PitcherStatisticsCalculator.updateInningsPitched(8.6);
        assertEquals(10.0, ip, 0.0);
    }

    @Test
    public void complexUpdate3() {
        double ip = PitcherStatisticsCalculator.updateInningsPitched(8.9);
        assertEquals(11, ip, 0.0);
    }
}