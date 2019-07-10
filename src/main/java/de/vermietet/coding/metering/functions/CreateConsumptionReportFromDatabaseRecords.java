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
import de.vermietet.coding.metering.ConsumptionReport;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author USER
 */
public class CreateConsumptionReportFromDatabaseRecords 
        implements Function<List<Object[]>, ConsumptionReport>{

    private final CreateConsumptionFromDatabaseRecord createConsumptionFromDatabaseRecord;

    public CreateConsumptionReportFromDatabaseRecords(CreateConsumptionFromDatabaseRecord createConsumptionFromDatabaseRecord) {
        this.createConsumptionFromDatabaseRecord = Objects.requireNonNull(createConsumptionFromDatabaseRecord);
    }

    @Override
    public ConsumptionReport apply(List<Object[]> consumptionRecords) {

        final List<Consumption> output = consumptionRecords == null ? Collections.EMPTY_LIST :
                consumptionRecords.stream().map(createConsumptionFromDatabaseRecord).collect(Collectors.toList());
        
        return new ConsumptionReport(output);
    }
}
