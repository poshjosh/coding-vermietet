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
@Table(name = "village")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Village.findAll", query = "SELECT v FROM Village v"),
    @NamedQuery(name = "Village.findByVillageid", query = "SELECT v FROM Village v WHERE v.villageid = :villageid"),
    @NamedQuery(name = "Village.findByVillagename", query = "SELECT v FROM Village v WHERE v.villagename = :villagename")})
public class Village implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "villageid")
    private Integer villageid;
    //@Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @javax.validation.constraints.Size(min = 1, max = 128)
    @Column(name = "villagename")
    private String villagename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "village", fetch = FetchType.LAZY)
    private List<Building> buildingList;

    public Village() {
    }

    public Village(Integer villageid) {
        this.villageid = villageid;
    }

    public Village(Integer villageid, String villagename) {
        this.villageid = villageid;
        this.villagename = villagename;
    }

    public Integer getVillageid() {
        return villageid;
    }

    public void setVillageid(Integer villageid) {
        this.villageid = villageid;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    @XmlTransient
    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (villageid != null ? villageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Village)) {
            return false;
        }
        Village other = (Village) object;
        if ((this.villageid == null && other.villageid != null) || (this.villageid != null && !this.villageid.equals(other.villageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Village[ villageid=" + villageid + " ]";
    }
    
}
