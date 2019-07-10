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

import de.vermietet.coding.metering.electricity.controllers.ElectricityconsumptionController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

/**
 * @author USER
 */
@Component
public class ElectricitycounterResourceAssembler implements 
        ResourceAssembler<ElectricitycounterInfo, Resource<ElectricitycounterInfo>> {

    @Override
    public Resource<ElectricitycounterInfo> toResource(ElectricitycounterInfo entity) {

        final Integer id = entity.getId();
        
        return new Resource<>(entity,
            linkTo(methodOn(ElectricityconsumptionController.class).getCounterInfo(id)).withSelfRel());
    }
}