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
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author USER
 */
public class ParseDuration implements BiFunction<String, TimeUnit, Long>{
    
    @Override
    public Long apply(String duration, TimeUnit timeUnit) {

        final Pattern pattern = Pattern.compile("(\\d{1,})(d|h|m|s)");
        
        final Matcher matcher = pattern.matcher(duration);
        
        if(matcher.find()) {
        
            final long digits = Long.parseLong(matcher.group(1));

            final TimeUnit unit = getTimeUnit(matcher.group(2));
            
            return timeUnit.convert(digits, unit);
            
        }else{
            
            throw new IllegalArgumentException();
        }
    }
    
    public TimeUnit getTimeUnit(String unit) {
        switch(unit) {
            case "d": return TimeUnit.DAYS;
            case "h": return TimeUnit.HOURS;
            case "m": return TimeUnit.MINUTES;
            case "s": return TimeUnit.SECONDS;
            default: return this.getDefaultTimeUnit();
        }
    }
    
    public TimeUnit getDefaultTimeUnit() {
        return TimeUnit.HOURS;
    }
}
