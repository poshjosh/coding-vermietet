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
@Table(name = "userdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdetails.findAll", query = "SELECT u FROM Userdetails u"),
    @NamedQuery(name = "Userdetails.findByUserdetailsid", query = "SELECT u FROM Userdetails u WHERE u.userdetailsid = :userdetailsid"),
    @NamedQuery(name = "Userdetails.findByUsername", query = "SELECT u FROM Userdetails u WHERE u.username = :username")})
public class Userdetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userdetailsid")
    private Integer userdetailsid;
    //@Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @javax.validation.constraints.Size(min = 1, max = 128)
    @Column(name = "username")
    private String username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userdetails", fetch = FetchType.LAZY)
    private List<Landlord> landlordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userdetails", fetch = FetchType.LAZY)
    private List<Tenant> tenantList;

    public Userdetails() {
    }

    public Userdetails(Integer userdetailsid) {
        this.userdetailsid = userdetailsid;
    }

    public Userdetails(Integer userdetailsid, String username) {
        this.userdetailsid = userdetailsid;
        this.username = username;
    }

    public Integer getUserdetailsid() {
        return userdetailsid;
    }

    public void setUserdetailsid(Integer userdetailsid) {
        this.userdetailsid = userdetailsid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public List<Landlord> getLandlordList() {
        return landlordList;
    }

    public void setLandlordList(List<Landlord> landlordList) {
        this.landlordList = landlordList;
    }

    @XmlTransient
    public List<Tenant> getTenantList() {
        return tenantList;
    }

    public void setTenantList(List<Tenant> tenantList) {
        this.tenantList = tenantList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userdetailsid != null ? userdetailsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdetails)) {
            return false;
        }
        Userdetails other = (Userdetails) object;
        if ((this.userdetailsid == null && other.userdetailsid != null) || (this.userdetailsid != null && !this.userdetailsid.equals(other.userdetailsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Userdetails[ userdetailsid=" + userdetailsid + " ]";
    }
    
}
