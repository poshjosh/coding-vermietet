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
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "rentedapartment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rentedapartment.findAll", query = "SELECT r FROM Rentedapartment r"),
    @NamedQuery(name = "Rentedapartment.findByRentedapartmentid", query = "SELECT r FROM Rentedapartment r WHERE r.rentedapartmentid = :rentedapartmentid")})
public class Rentedapartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rentedapartmentid")
    private Integer rentedapartmentid;
    @JoinColumn(name = "apartment", referencedColumnName = "apartmentid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Apartment apartment;
    @JoinColumn(name = "landlord", referencedColumnName = "landlordid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Landlord landlord;
    @JoinColumn(name = "rentalcontract", referencedColumnName = "rentalcontractid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rentalcontract rentalcontract;
    @JoinColumn(name = "tenant", referencedColumnName = "tenantid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tenant tenant;

    public Rentedapartment() {
    }

    public Rentedapartment(Integer rentedapartmentid) {
        this.rentedapartmentid = rentedapartmentid;
    }

    public Integer getRentedapartmentid() {
        return rentedapartmentid;
    }

    public void setRentedapartmentid(Integer rentedapartmentid) {
        this.rentedapartmentid = rentedapartmentid;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public Rentalcontract getRentalcontract() {
        return rentalcontract;
    }

    public void setRentalcontract(Rentalcontract rentalcontract) {
        this.rentalcontract = rentalcontract;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentedapartmentid != null ? rentedapartmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rentedapartment)) {
            return false;
        }
        Rentedapartment other = (Rentedapartment) object;
        if ((this.rentedapartmentid == null && other.rentedapartmentid != null) || (this.rentedapartmentid != null && !this.rentedapartmentid.equals(other.rentedapartmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Rentedapartment[ rentedapartmentid=" + rentedapartmentid + " ]";
    }
    
}
