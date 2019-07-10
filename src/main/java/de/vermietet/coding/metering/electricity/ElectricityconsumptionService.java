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

import de.vermietet.coding.metering.ConsumptionReport;
import de.vermietet.coding.http.EntityNotFoundExceptionForHttp;
import de.vermietet.coding.metering.functions.CreateConsumptionReportFromDatabaseRecords;
import de.vermietet.coding.metering.electricity.functions.CreateElectricitycounterInfoFromEntity;
import de.vermietet.coding.metering.functions.ParseDuration;
import de.vermietet.coding.metering.electricity.repositories.ElectricityconsumptionRepository;
import de.vermietet.coding.metering.electricity.repositories.ElectricitycounterRepository;
import de.vermietet.domain.Electricityconsumption;
import de.vermietet.domain.Electricitycounter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author USER
 */
@Service
public class ElectricityconsumptionService {
    
    private final ElectricitycounterRepository electricitycounterRepository;

    private final ElectricityconsumptionRepository electricityconsumptionRepository;
    
    private final CreateElectricitycounterInfoFromEntity createElectricitycounterInfoFromEntity;
    
    private final CreateConsumptionReportFromDatabaseRecords createConsumptionReportFromDatabaseRecords;
    
    private final ParseDuration parseDuration;
    
    private final ElectricitycounterCallbackProperties counterCallbackProperties;
    
    public ElectricityconsumptionService(
            @Autowired ElectricitycounterRepository electricitycounterRepository, 
            @Autowired ElectricityconsumptionRepository electricityconsumptionRepository, 
            @Autowired CreateElectricitycounterInfoFromEntity createElectricitycounterInfoFromEntity, 
            @Autowired CreateConsumptionReportFromDatabaseRecords createConsumptionReportFromDatabaseRecords,
            @Autowired ParseDuration parseDuration,
            @Autowired ElectricitycounterCallbackProperties counterCallbackProperties) {
        
        this.electricitycounterRepository = Objects.requireNonNull(electricitycounterRepository);
        this.electricityconsumptionRepository = Objects.requireNonNull(electricityconsumptionRepository);
        this.createElectricitycounterInfoFromEntity = Objects.requireNonNull(createElectricitycounterInfoFromEntity);
        this.createConsumptionReportFromDatabaseRecords = Objects.requireNonNull(createConsumptionReportFromDatabaseRecords);
        this.parseDuration = Objects.requireNonNull(parseDuration);
        this.counterCallbackProperties = Objects.requireNonNull(counterCallbackProperties);
    }
    
    public Electricityconsumption recordConsumption(ElectricityconsumptionData ec) {
        
        final Electricitycounter counter = findCounterById(ec.getCounter_id());

        if(ec.getDuration_in_seconds() == null) {
            ec.setDuration_in_seconds(this.counterCallbackProperties.getDefaultDurationForAmountConsumedInSeconds());
        }
        if(ec.getStart_time() == null) {
            ec.setStart_time(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(ec.getDuration_in_seconds()));
        }

        final Electricityconsumption electricityConsumption = new Electricityconsumption();
        electricityConsumption.setAmountInWatts(ec.getAmount());
        electricityConsumption.setDurationInSeconds(ec.getDuration_in_seconds().intValue());
        electricityConsumption.setElectricitycounter(counter);
        electricityConsumption.setStarttime(new Date(ec.getStart_time()));

        electricityconsumptionRepository.save(electricityConsumption);
        
        return electricityConsumption;
    }

    public ElectricitycounterInfo getCounterInfo(Integer counter_id) {
        
        final Electricitycounter counter = findCounterById(counter_id);

        return getCounterInfo(counter);
    }

    public ElectricitycounterInfo getCounterInfo(Electricitycounter counter) {
        
        final ElectricitycounterInfo counterInfo = createElectricitycounterInfoFromEntity.apply(counter);
        
        return counterInfo;
    }

    public ConsumptionReport getConsumptionReport(String duration) {
        
        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        final long durationMillis;
        try{
            durationMillis = parseDuration.apply(duration, timeUnit);
        }catch(IllegalArgumentException e) {
            throw new javax.validation.ValidationException("Invalid value for duration: " + duration, e);
        }
        
        return getConsumptionReport(durationMillis, timeUnit);
    }
    
    public ConsumptionReport getConsumptionReport(long duration, TimeUnit timeUnit) {

        final List<Object[]> consumptionRecords = electricityconsumptionRepository
                .getConsumptionTill(duration, timeUnit, new Date());
        
        return createConsumptionReportFromDatabaseRecords.apply(consumptionRecords);
    }

    public Electricitycounter findCounterById(Integer counter_id) {
        
        final Electricitycounter counter = electricitycounterRepository
                .findById(counter_id)
                .orElseThrow(() -> new EntityNotFoundExceptionForHttp(Electricitycounter.class, counter_id));
        
        return counter;
    }

    public Electricityconsumption findConsumptionById(Integer consumption_id) {
        
        final Electricityconsumption consumption = electricityconsumptionRepository
                .findById(consumption_id)
                .orElseThrow(() -> new EntityNotFoundExceptionForHttp(Electricityconsumption.class, consumption_id));
        
        return consumption;
    }
}
