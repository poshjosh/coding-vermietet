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

import de.vermietet.domain.Apartment;
import java.util.Arrays;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author USER
 */
@SpringBootApplication
@EntityScan(basePackageClasses=Apartment.class)
public class Application {
    
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    @PostConstruct
    public void init(){
        LOG.info("Default TimeZone: {}", TimeZone.getDefault());
    }

    public static void main(String[] args) {
        
        final ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        ctx.registerShutdownHook();

        LOG.info("Registered application context shutdownhook");
        
        final String [] activeProfiles = ctx.getEnvironment().getActiveProfiles();

        final String webAppType = ctx.getEnvironment().getProperty("spring.main.web-application-type");

        LOG.info("Spring Context initialized. Web application type: {}, active profiles: {}", 
                webAppType,
                (activeProfiles==null?null:Arrays.toString(activeProfiles)));
    }
}