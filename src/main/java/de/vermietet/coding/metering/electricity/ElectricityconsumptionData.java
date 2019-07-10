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

import java.io.Serializable;
import java.util.Objects;

/**
 * @author USER
 */
public class ElectricityconsumptionData implements Serializable{
    
    @javax.validation.constraints.Positive
    private Integer counter_id;
    
    @javax.validation.constraints.Null
    private Long start_time;
    
    @javax.validation.constraints.Null
    private Long duration_in_seconds;
    
    @javax.validation.constraints.PositiveOrZero
    private Float amount;

    public ElectricityconsumptionData() { }

    public Integer getCounter_id() {
        return counter_id;
    }

    public void setCounter_id(Integer counter_id) {
        this.counter_id = counter_id;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getDuration_in_seconds() {
        return duration_in_seconds;
    }

    public void setDuration_in_seconds(Long duration_in_seconds) {
        this.duration_in_seconds = duration_in_seconds;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.counter_id);
        hash = 31 * hash + Objects.hashCode(this.start_time);
        hash = 31 * hash + Objects.hashCode(this.duration_in_seconds);
        hash = 31 * hash + Objects.hashCode(this.amount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ElectricityconsumptionData other = (ElectricityconsumptionData) obj;
        if (!Objects.equals(this.counter_id, other.counter_id)) {
            return false;
        }
        if (!Objects.equals(this.start_time, other.start_time)) {
            return false;
        }
        if (!Objects.equals(this.duration_in_seconds, other.duration_in_seconds)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ElectricityconsumptionData.class.getSimpleName() + "{" + 
                "counter_id=" + counter_id + ", start_time=" + start_time + 
                ", duration_in_seconds=" + duration_in_seconds + 
                ", consumed_amount_in_watts=" + amount + '}';
    }
}
