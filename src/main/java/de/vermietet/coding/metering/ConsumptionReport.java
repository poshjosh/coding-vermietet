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
package de.vermietet.coding.metering;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author USER
 */
public class ConsumptionReport implements Serializable{

    private List<Consumption> villages;

    public ConsumptionReport() { }

    public ConsumptionReport(List<Consumption> villages) {
        this.villages = villages;
    }

    public List<Consumption> getVillages() {
        return villages;
    }

    public void setVillages(List<Consumption> villages) {
        this.villages = villages;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.villages);
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
        final ConsumptionReport other = (ConsumptionReport) obj;
        if (!Objects.equals(this.villages, other.villages)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ConsumptionReport.class.getSimpleName() + "{" + "consumptionList=" + villages + '}';
    }
}
