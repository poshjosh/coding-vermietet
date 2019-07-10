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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "rentalcontract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rentalcontract.findAll", query = "SELECT r FROM Rentalcontract r"),
    @NamedQuery(name = "Rentalcontract.findByRentalcontractid", query = "SELECT r FROM Rentalcontract r WHERE r.rentalcontractid = :rentalcontractid"),
    @NamedQuery(name = "Rentalcontract.findByStartdate", query = "SELECT r FROM Rentalcontract r WHERE r.startdate = :startdate")})
public class Rentalcontract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @Column(name = "rentalcontractid")
    private Integer rentalcontractid;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rentalcontract", fetch = FetchType.LAZY)
    private List<Rentedapartment> rentedapartmentList;

    public Rentalcontract() {
    }

    public Rentalcontract(Integer rentalcontractid) {
        this.rentalcontractid = rentalcontractid;
    }

    public Integer getRentalcontractid() {
        return rentalcontractid;
    }

    public void setRentalcontractid(Integer rentalcontractid) {
        this.rentalcontractid = rentalcontractid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @XmlTransient
    public List<Rentedapartment> getRentedapartmentList() {
        return rentedapartmentList;
    }

    public void setRentedapartmentList(List<Rentedapartment> rentedapartmentList) {
        this.rentedapartmentList = rentedapartmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentalcontractid != null ? rentalcontractid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rentalcontract)) {
            return false;
        }
        Rentalcontract other = (Rentalcontract) object;
        if ((this.rentalcontractid == null && other.rentalcontractid != null) || (this.rentalcontractid != null && !this.rentalcontractid.equals(other.rentalcontractid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Rentalcontract[ rentalcontractid=" + rentalcontractid + " ]";
    }
    
}
