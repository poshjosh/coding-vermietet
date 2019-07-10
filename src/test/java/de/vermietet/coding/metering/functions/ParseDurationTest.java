/*
 * Copyright (C) 2019 USER
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.vermietet.coding.metering.functions;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.LoggerFactory;

/**
 * @author USER
 */
public class ParseDurationTest {

    private static final Logger LOG = LoggerFactory.getLogger(ParseDurationTest.class);
    
    public ParseDurationTest() { }

    /**
     * Test of apply method, of class ParseDuration.
     */
    @Test
    public void testApply() {
        LOG.info("apply");
        testApply("1d", TimeUnit.DAYS, 1L);
        testApply("1d", TimeUnit.HOURS, 24L);
        testApply("2h", TimeUnit.HOURS, 2L);
        testApply("24h", TimeUnit.DAYS, 1L);
        testApply("3m", TimeUnit.MINUTES, 3L);
        testApply("4s", TimeUnit.SECONDS, 4L);
    }

    public void testApply(String duration, TimeUnit timeUnit, Long expResult) {
        LOG.info("apply(" + duration + ")");
        final ParseDuration instance = new ParseDuration();
        final Long result = instance.apply(duration, timeUnit);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeUnit method, of class ParseDuration.
     */
    @Test
    public void testGetTimeUnit() {
        LOG.info("getTimeUnit");
        testGetTimeUnit("d", TimeUnit.DAYS);
        testGetTimeUnit("h", TimeUnit.HOURS);
        testGetTimeUnit("m", TimeUnit.MINUTES);
        testGetTimeUnit("s", TimeUnit.SECONDS);
        testGetTimeUnit("", new ParseDuration().getDefaultTimeUnit());
        testGetTimeUnit("0", new ParseDuration().getDefaultTimeUnit());
    }
    
    public void testGetTimeUnit(String unit, TimeUnit expResult) {
        LOG.info("getTimeUnit(" + unit + ")");
        final ParseDuration instance = new ParseDuration();
        final TimeUnit result = instance.getTimeUnit(unit);
        assertEquals(expResult, result);
    }
}
