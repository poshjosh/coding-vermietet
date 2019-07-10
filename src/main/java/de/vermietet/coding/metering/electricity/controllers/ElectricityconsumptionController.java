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
package de.vermietet.coding.metering.electricity.controllers;

import de.vermietet.coding.http.Endpoints;
import de.vermietet.coding.metering.electricity.ElectricityconsumptionData;
import de.vermietet.coding.metering.ConsumptionReport;
import de.vermietet.coding.metering.electricity.ElectricityconsumptionResourceAssembler;
import de.vermietet.coding.metering.electricity.ElectricityconsumptionService;
import de.vermietet.coding.metering.electricity.ElectricitycounterInfo;
import de.vermietet.coding.metering.electricity.ElectricitycounterResourceAssembler;
import de.vermietet.domain.Electricityconsumption;
import de.vermietet.domain.Electricitycounter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author USER
 */
@RestController
public class ElectricityconsumptionController {

    private static final Logger LOG = LoggerFactory.getLogger(ElectricityconsumptionController.class);
    
    private final ElectricityconsumptionService controllerService;
    
    private final ElectricitycounterResourceAssembler counterResAssy;
    
    private final ElectricityconsumptionResourceAssembler consumptionResAssy;

    public ElectricityconsumptionController(
            @Autowired ElectricityconsumptionService controllerService,
            @Autowired ElectricitycounterResourceAssembler counterResAssy,
            @Autowired ElectricityconsumptionResourceAssembler consumptionResAssy) {
        this.controllerService = Objects.requireNonNull(controllerService);
        this.counterResAssy = Objects.requireNonNull(counterResAssy);
        this.consumptionResAssy = Objects.requireNonNull(consumptionResAssy);
    }
    
    @PostMapping(Endpoints.COUNTER_CALLBACK)
    public ResponseEntity<?> recordElectricityConsumption(
            @RequestBody @Valid ElectricityconsumptionData ec)
            throws URISyntaxException{
        
        LOG.debug("{}", ec);
        
        final Electricityconsumption consumption = this.controllerService.recordConsumption(ec);
        
        LOG.debug("{}, {}", ec, consumption);

        final Resource<Electricityconsumption> consumptionRes = this.consumptionResAssy.toResource(consumption);
        
        final Electricitycounter counter = consumption.getElectricitycounter();

        final ElectricitycounterInfo counterInfo = this.controllerService.getCounterInfo(counter);
        
        final Resource<ElectricitycounterInfo> counterInfoRes = this.counterResAssy.toResource(counterInfo);

        return ResponseEntity
            .created(new URI(consumptionRes.getId().expand().getHref()))
            .body(counterInfoRes);
    }

    @GetMapping(Endpoints.COUNTER)
    public ResponseEntity<?> getCounterInfo(@RequestParam(value="id") Integer id) {
        
        final ElectricitycounterInfo entity = this.controllerService.getCounterInfo(id);
        
        final Resource<ElectricitycounterInfo> resource = counterResAssy.toResource(entity);

        return ResponseEntity.ok(resource);
    }

    @GetMapping(Endpoints.CONSUMPTION)
    public ResponseEntity<?> getConsumption(@RequestParam(value="id") Integer id) {
        
        final Electricityconsumption entity = this.controllerService.findConsumptionById(id);
        
        final Resource<Electricityconsumption> resource = consumptionResAssy.toResource(entity);

        return ResponseEntity.ok(resource);
    }

    @GetMapping(Endpoints.CONSUMPTION_REPORT)
    public ConsumptionReport getConsumptionReportForHoursPast(
            @RequestParam(value="duration") String duration) {

        final ConsumptionReport consumptionReport = this.controllerService
                .getConsumptionReport(duration);
        
        return consumptionReport;
    }
}
