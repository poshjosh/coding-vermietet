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
@Table(name = "landlord")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Landlord.findAll", query = "SELECT l FROM Landlord l"),
    @NamedQuery(name = "Landlord.findByLandlordid", query = "SELECT l FROM Landlord l WHERE l.landlordid = :landlordid")})
public class Landlord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "landlordid")
    private Integer landlordid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "landlord", fetch = FetchType.LAZY)
    private List<Rentedapartment> rentedapartmentList;
    @JoinColumn(name = "userdetails", referencedColumnName = "userdetailsid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Userdetails userdetails;

    public Landlord() {
    }

    public Landlord(Integer landlordid) {
        this.landlordid = landlordid;
    }

    public Integer getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(Integer landlordid) {
        this.landlordid = landlordid;
    }

    @XmlTransient
    public List<Rentedapartment> getRentedapartmentList() {
        return rentedapartmentList;
    }

    public void setRentedapartmentList(List<Rentedapartment> rentedapartmentList) {
        this.rentedapartmentList = rentedapartmentList;
    }

    public Userdetails getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(Userdetails userdetails) {
        this.userdetails = userdetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (landlordid != null ? landlordid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Landlord)) {
            return false;
        }
        Landlord other = (Landlord) object;
        if ((this.landlordid == null && other.landlordid != null) || (this.landlordid != null && !this.landlordid.equals(other.landlordid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Landlord[ landlordid=" + landlordid + " ]";
    }
    
}
