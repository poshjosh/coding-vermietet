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
package de.vermietet.coding.metering.electricity.repositories;

import de.vermietet.domain.Apartment;
import de.vermietet.domain.Apartment_;
import de.vermietet.domain.Building;
import de.vermietet.domain.Building_;
import de.vermietet.domain.Electricityconsumption;
import de.vermietet.domain.Electricityconsumption_;
import de.vermietet.domain.Electricitycounter;
import de.vermietet.domain.Electricitycounter_;
import de.vermietet.domain.Village;
import de.vermietet.domain.Village_;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * @author USER
 */
@Repository
@Transactional
public class ElectricityconsumptionRepositoryImpl 
        implements ElectricityconsumptionRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Object[]> getConsumptionTill(long duration, TimeUnit timeUnit, Date targetDate) {
        
        final Date startDate = new Date(targetDate.getTime() - timeUnit.toMillis(duration));
        
        return getConsumption(startDate, duration, timeUnit);
    }
    
    @Override
    public List<Object[]> getConsumption(Date startDate, long duration, TimeUnit timeUnit) {

        final Date endDate = new Date(startDate.getTime() + timeUnit.toMillis(duration));
        
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        
        final CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        
        final Root<Village> village = cq.from(Village.class);
        
        final ListJoin<Village, Building> village_bldg = village.join(Village_.buildingList);
        
        final ListJoin<Building, Apartment> bldg_aptmt = village_bldg.join(Building_.apartmentList);
        
        final Join<Apartment, Electricitycounter> aptmt_elecCounter = 
                bldg_aptmt.join(Apartment_.electricitycounter);
        
        final ListJoin<Electricitycounter, Electricityconsumption> aptmt_electConsump = 
                aptmt_elecCounter.join(Electricitycounter_.electricityconsumptionList);

        final Predicate withinTimeFrame = cb.between(
                aptmt_electConsump.<Date>get(Electricityconsumption_.starttime), startDate, endDate);
        
        cq.where(withinTimeFrame);
        
        final Expression<Double> sumOfElecConsumption = cb.sumAsDouble(
                aptmt_electConsump.<Float>get(Electricityconsumption_.amountInWatts));
        
        cq.multiselect(village.get(Village_.villagename), sumOfElecConsumption);
        cq.groupBy(village.get(Village_.villagename));
//        cq.select(sumOfElecConsumption);

        final TypedQuery<Object[]> tq = entityManager.createQuery(cq);

        final List<Object[]> output = tq.getResultList();
        
        return output;
    }
}
