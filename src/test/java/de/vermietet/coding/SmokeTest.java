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

import de.vermietet.coding.metering.electricity.ElectricityconsumptionService;
import de.vermietet.coding.metering.electricity.controllers.ElectricityconsumptionController;
import de.vermietet.coding.metering.electricity.repositories.ElectricityconsumptionRepository;
import org.slf4j.Logger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author USER
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SmokeTest {

//    private static final Logger LOG = LoggerFactory.getLogger(SmokeTest.class);
    
    @Autowired
    private ElectricityconsumptionController controller;

    @Autowired
    private ElectricityconsumptionService service;

    @Autowired
    private ElectricityconsumptionRepository repo;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(service).isNotNull();
        assertThat(repo).isNotNull();
//        final List<Object[]> data = repo.getConsumptionTill(24, TimeUnit.HOURS, new Date());
    }
}
