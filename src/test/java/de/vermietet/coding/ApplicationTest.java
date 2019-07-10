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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author USER
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ApplicationTest {

    private final Integer id = 1;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testElectricityconsumptionController() throws Exception {
        
        this.mockMvc.perform(post(Endpoints.COUNTER_CALLBACK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"counter_id\":\"1\",\"amount\":\"10000.123\"}")
                )
                .andDo(print())
                .andExpect(status().isCreated());

        this.mockMvc.perform(get(Endpoints.COUNTER+"?id=" + id))
                .andDo(print())
                .andExpect(status().isOk());
        
        this.mockMvc.perform(get(Endpoints.CONSUMPTION+"?id=" + id))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get(Endpoints.CONSUMPTION_REPORT+"?duration=24h"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}