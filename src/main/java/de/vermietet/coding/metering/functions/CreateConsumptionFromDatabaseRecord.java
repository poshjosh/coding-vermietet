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

import de.vermietet.coding.metering.Consumption;
import de.vermietet.coding.metering.ConsumptionImpl;
import java.util.function.Function;

/**
 * @author USER
 */
public class CreateConsumptionFromDatabaseRecord implements Function<Object[], Consumption>{
     
    @Override
    public Consumption apply(Object[] record) {

        return new ConsumptionImpl((String)record[0], (Double)record[1]);
    }
}