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

import de.vermietet.coding.http.Endpoints;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertTrue;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author USER
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class HttpRequestTest {

    private static final Logger LOG = LoggerFactory.getLogger(HttpRequestTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void counterCallbackShouldReturnHalLinks() throws Exception {
        final Map req = new HashMap();
        req.put("counter_id", 1);
        req.put("amount", 10000.123);
        final Map res = this.restTemplate.postForObject(
                "http://localhost:" + port + Endpoints.COUNTER_CALLBACK, req, Map.class);
        LOG.info(" Request: " + req + "\nResponse: " + res);
        assertTrue(res != null);
        assertTrue(res.containsKey("_links"));
    }
    
    @Test
    public void testCounterResponse() throws Exception {

        postElectricityConsumptionIfNone();

        final Map res = this.restTemplate.getForObject(
                "http://localhost:" + port + Endpoints.COUNTER + "?id=1", Map.class);
        LOG.info("Response: " + res);
        assertTrue(res != null);
        assertTrue(res.containsKey("id"));
        assertTrue(res.containsKey("village_name"));
    }

    @Test
    public void testConsumptionReportResponse() throws Exception {

        postElectricityConsumptionIfNone();

        final Map res = this.restTemplate.getForObject(
                "http://localhost:" + port + Endpoints.CONSUMPTION_REPORT + "?duration=24h", Map.class);
        LOG.info("Response: " + res);
        assertTrue(res != null);
        assertTrue(res.containsKey("villages"));
    }

    public boolean postElectricityConsumptionIfNone() throws Exception {
        if(hasConsumptionReport()) {
            return false;
        }
        final Map req = new HashMap();
        req.put("counter_id", 1);
        req.put("amount", 10000.123);
        final Map res = this.restTemplate.postForObject(
                "http://localhost:" + port + Endpoints.COUNTER_CALLBACK, req, Map.class);
        LOG.info(" Request: " + req + "\nResponse: " + res);
        assertTrue(res != null);
        assertTrue(res.containsKey("_links"));
        return true;
    }

    public boolean hasConsumptionReport() throws Exception {
        final Map res = this.restTemplate.getForObject(
                "http://localhost:" + port + Endpoints.CONSUMPTION_REPORT + "?duration=24h", Map.class);
        LOG.info("Response: " + res);
        assertTrue(res != null);
        final Collection c = (Collection)res.get("villages");
        return c != null && !c.isEmpty();
    }
}
