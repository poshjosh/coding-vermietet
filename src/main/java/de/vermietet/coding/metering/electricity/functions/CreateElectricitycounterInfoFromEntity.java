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
package de.vermietet.coding.metering.electricity.functions;

import de.vermietet.coding.metering.electricity.ElectricitycounterInfo;
import de.vermietet.domain.Apartment;
import de.vermietet.domain.Building;
import de.vermietet.domain.Electricitycounter;
import de.vermietet.domain.Village;
import java.util.function.Function;

/**
 *
 * @author USER
 */
public class CreateElectricitycounterInfoFromEntity implements Function<Electricitycounter, ElectricitycounterInfo>{

    @Override
    public ElectricitycounterInfo apply(Electricitycounter counter) {

        final Apartment apartment = counter.getApartment();
        final Building building = apartment.getBuilding();
        final Village village = building.getVillage();

        final ElectricitycounterInfo counterInfo = new ElectricitycounterInfo();
        counterInfo.setApartment_name(apartment.getApartmentname());
        counterInfo.setBuilding_name(building.getBuildingname());
        counterInfo.setId(counter.getElectricitycounterid());
        counterInfo.setVillage_name(village.getVillagename());

        return counterInfo;
    }
}
