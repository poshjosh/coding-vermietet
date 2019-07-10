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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "building")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Building.findAll", query = "SELECT b FROM Building b"),
    @NamedQuery(name = "Building.findByBuildingid", query = "SELECT b FROM Building b WHERE b.buildingid = :buildingid"),
    @NamedQuery(name = "Building.findByBuildingname", query = "SELECT b FROM Building b WHERE b.buildingname = :buildingname")})
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "buildingid")
    private Integer buildingid;
    //@Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @javax.validation.constraints.Size(min = 1, max = 128)
    @Column(name = "buildingname")
    private String buildingname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building", fetch = FetchType.LAZY)
    private List<Apartment> apartmentList;
    @JoinColumn(name = "village", referencedColumnName = "villageid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Village village;

    public Building() {
    }

    public Building(Integer buildingid) {
        this.buildingid = buildingid;
    }

    public Building(Integer buildingid, String buildingname) {
        this.buildingid = buildingid;
        this.buildingname = buildingname;
    }

    public Integer getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(Integer buildingid) {
        this.buildingid = buildingid;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    @XmlTransient
    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buildingid != null ? buildingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Building)) {
            return false;
        }
        Building other = (Building) object;
        if ((this.buildingid == null && other.buildingid != null) || (this.buildingid != null && !this.buildingid.equals(other.buildingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Building[ buildingid=" + buildingid + " ]";
    }
    
}
