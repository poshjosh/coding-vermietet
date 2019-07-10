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
package de.vermietet.coding.metering.electricity;

import de.vermietet.coding.metering.functions.CreateConsumptionFromDatabaseRecord;
import de.vermietet.coding.metering.functions.CreateConsumptionReportFromDatabaseRecords;
import de.vermietet.coding.metering.electricity.functions.CreateElectricitycounterInfoFromEntity;
import de.vermietet.coding.metering.functions.ParseDuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author USER
 */
@Configuration
public class ElectricityconsumptionConfiguration{
    
    @Bean
    public CreateConsumptionFromDatabaseRecord createConsumptionFromDatabaseRecord() {
    
        return new CreateConsumptionFromDatabaseRecord();
    }
    
    @Bean
    public CreateElectricitycounterInfoFromEntity createElectricitycounterInfoFromEntity() {
    
        return new CreateElectricitycounterInfoFromEntity();
    }
    
    @Bean
    public CreateConsumptionReportFromDatabaseRecords createConsumptionReportFromConsumption(
            CreateConsumptionFromDatabaseRecord createConsumptionFromDatabaseRecord) {
    
        return new CreateConsumptionReportFromDatabaseRecords(createConsumptionFromDatabaseRecord);
    }
    
    @Bean
    public ParseDuration parseDuration() {
        
        return new ParseDuration();
    }
}
