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
import javax.persistence.ManyToOne;
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
@Table(name = "apartment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apartment.findAll", query = "SELECT a FROM Apartment a"),
    @NamedQuery(name = "Apartment.findByApartmentid", query = "SELECT a FROM Apartment a WHERE a.apartmentid = :apartmentid"),
    @NamedQuery(name = "Apartment.findByApartmentname", query = "SELECT a FROM Apartment a WHERE a.apartmentname = :apartmentname")})
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "apartmentid")
    private Integer apartmentid;
    //@Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @javax.validation.constraints.Size(min = 1, max = 128)
    @Column(name = "apartmentname")
    private String apartmentname;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "apartment", fetch = FetchType.LAZY)
    private Electricitycounter electricitycounter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartment", fetch = FetchType.LAZY)
    private List<Rentedapartment> rentedapartmentList;
    @JoinColumn(name = "building", referencedColumnName = "buildingid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Building building;

    public Apartment() {
    }

    public Apartment(Integer apartmentid) {
        this.apartmentid = apartmentid;
    }

    public Apartment(Integer apartmentid, String apartmentname) {
        this.apartmentid = apartmentid;
        this.apartmentname = apartmentname;
    }

    public Integer getApartmentid() {
        return apartmentid;
    }

    public void setApartmentid(Integer apartmentid) {
        this.apartmentid = apartmentid;
    }

    public String getApartmentname() {
        return apartmentname;
    }

    public void setApartmentname(String apartmentname) {
        this.apartmentname = apartmentname;
    }

    public Electricitycounter getElectricitycounter() {
        return electricitycounter;
    }

    public void setElectricitycounter(Electricitycounter electricitycounter) {
        this.electricitycounter = electricitycounter;
    }

    @XmlTransient
    public List<Rentedapartment> getRentedapartmentList() {
        return rentedapartmentList;
    }

    public void setRentedapartmentList(List<Rentedapartment> rentedapartmentList) {
        this.rentedapartmentList = rentedapartmentList;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apartmentid != null ? apartmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apartment)) {
            return false;
        }
        Apartment other = (Apartment) object;
        if ((this.apartmentid == null && other.apartmentid != null) || (this.apartmentid != null && !this.apartmentid.equals(other.apartmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Apartment[ apartmentid=" + apartmentid + " ]";
    }
    
}
