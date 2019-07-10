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
package de.vermietet.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "electricitycounter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Electricitycounter.findAll", query = "SELECT e FROM Electricitycounter e"),
    @NamedQuery(name = "Electricitycounter.findByElectricitycounterid", query = "SELECT e FROM Electricitycounter e WHERE e.electricitycounterid = :electricitycounterid")})
public class Electricitycounter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "electricitycounterid")
    private Integer electricitycounterid;
    @JoinColumn(name = "apartment", referencedColumnName = "apartmentid")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Apartment apartment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electricitycounter", fetch = FetchType.LAZY)
    private List<Electricityconsumption> electricityconsumptionList;

    public Electricitycounter() {
    }

    public Electricitycounter(Integer electricitycounterid) {
        this.electricitycounterid = electricitycounterid;
    }

    public Integer getElectricitycounterid() {
        return electricitycounterid;
    }

    public void setElectricitycounterid(Integer electricitycounterid) {
        this.electricitycounterid = electricitycounterid;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @XmlTransient
    public List<Electricityconsumption> getElectricityconsumptionList() {
        return electricityconsumptionList;
    }

    public void setElectricityconsumptionList(List<Electricityconsumption> electricityconsumptionList) {
        this.electricityconsumptionList = electricityconsumptionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (electricitycounterid != null ? electricitycounterid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Electricitycounter)) {
            return false;
        }
        Electricitycounter other = (Electricitycounter) object;
        if ((this.electricitycounterid == null && other.electricitycounterid != null) || (this.electricitycounterid != null && !this.electricitycounterid.equals(other.electricitycounterid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Electricitycounter[ electricitycounterid=" + electricitycounterid + " ]";
    }
    
}
