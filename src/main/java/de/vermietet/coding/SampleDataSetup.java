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
package de.vermietet.coding;

import de.vermietet.coding.metering.electricity.repositories.ApartmentRepository;
import de.vermietet.coding.metering.electricity.repositories.BuildingRepository;
import de.vermietet.coding.metering.electricity.repositories.ElectricitycounterRepository;
import de.vermietet.coding.metering.electricity.repositories.VillageRepository;
import de.vermietet.domain.Apartment;
import de.vermietet.domain.Building;
import de.vermietet.domain.Electricitycounter;
import de.vermietet.domain.Village;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.CrudRepository;

/**
 * @author USER
 */
@Configuration
class SampleDataSetup {

    private static final Logger LOG = LoggerFactory.getLogger(SampleDataSetup.class);
    
    @Autowired
    private Environment environment;

    @Autowired
    private ElectricitycounterRepository counterRepo;
    
    @Autowired
    private ApartmentRepository apartmentRepo;

    @Autowired
    private BuildingRepository buildingRepo;

    @Autowired
    private VillageRepository villageRepo;
    
    @Bean
    CommandLineRunner init() {
        
        return args -> {
            
            if(isEnabled()) {
                try{
                    initSampleData();
                }catch(Throwable t) {
                    LOG.error("Dev data may not have been inserted to database.", t);
                }
            }
        };
    }

    public void initSampleData() {
        
        final long count = villageRepo.count();
        
        if(count !=  0) {
            LOG.info("Data already exists");
            return;
        }
        
        LOG.info("Initializing test data");
        
        this.createVillageList(0, 3);
        
        assertHasRecords(counterRepo, Electricitycounter.class);
        assertHasRecords(apartmentRepo, Apartment.class);
        assertHasRecords(buildingRepo, Building.class);
        assertHasRecords(villageRepo, Village.class);
    }

    public boolean isEnabled() {
        
        final String prop = environment.getProperty("database.insertTestData");
        
        LOG.info("database.insertTestData = {}", prop);

        return prop == null || prop.isEmpty() ? false : Boolean.parseBoolean(prop);
    }

    public List<Village> createVillageList(int offset, int limit) {
        final List<Village> output = new ArrayList<>(limit < 0 ? 10 : limit);
        for(int i=offset; i<offset + limit; i++) {
            final Village village = new Village();
            village.setVillagename("Village-" + i);
            villageRepo.save(village);
            LOG.info("Saved to row: " + i + ", entity: " + village);
            
            final List<Building> buildingList = this.createBuildingList(village, 0, limit);
            village.setBuildingList(buildingList);
            
            output.add(village);
        }
        return output;
    }
    
    public List<Building> createBuildingList(Village village, int offset, int limit) {
        final List<Building> output = new ArrayList<>(limit < 0 ? 10 : limit);
        for(int i=offset; i<offset + limit; i++) {
            final Building building = new Building();
            building.setBuildingname(village.getVillagename() + "_Building-" + i);
            building.setVillage(village);
            buildingRepo.save(building);
            LOG.info("Saved to row: " + i + ", entity: " + building);
            
            final List<Apartment> apartmentList = this.createApartmentList(building, 0, limit);
            building.setApartmentList(apartmentList);

            output.add(building);
        }
        return output;
    }

    public List<Apartment> createApartmentList(Building building, int offset, int limit) {
        final List<Apartment> output = new ArrayList<>(limit < 0 ? 10 : limit);
        for(int i=offset; i<offset + limit; i++) {
            final Apartment apartment = new Apartment();
            apartment.setApartmentname(building.getBuildingname()+"_Appartment-" + i);
            apartment.setBuilding(building);
            apartmentRepo.save(apartment);
            LOG.info("Saved to row: " + i + ", entity: " + apartment);

            final Electricitycounter counter = new Electricitycounter();
            counter.setApartment(apartment);
            apartment.setElectricitycounter(counter);
            counterRepo.save(counter);
            LOG.info("Saved to row: " + i + ", entity: " + counter);

            output.add(apartment);
        }
        return output;
    }
    
    public void assertHasRecords(CrudRepository repo, Class entityType) {
        
        long count;
        
        if((count = repo.count()) <= 0) {
            
            LOG.info("Count: " + count + ", entity type: " + entityType);
        
            throw new AssertionError();
        }
    }
}
/**
 * 
    public boolean isProfileActive(String profile) {
        
        final String [] activeProfiles = environment.getActiveProfiles();

        return activeProfiles != null && Arrays.asList(activeProfiles).contains(profile);
    }
 * 
 */