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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "electricityconsumption")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Electricityconsumption.findAll", query = "SELECT e FROM Electricityconsumption e"),
    @NamedQuery(name = "Electricityconsumption.findByElectricityconsumptionid", query = "SELECT e FROM Electricityconsumption e WHERE e.electricityconsumptionid = :electricityconsumptionid"),
    @NamedQuery(name = "Electricityconsumption.findByStarttime", query = "SELECT e FROM Electricityconsumption e WHERE e.starttime = :starttime"),
    @NamedQuery(name = "Electricityconsumption.findByDurationInSeconds", query = "SELECT e FROM Electricityconsumption e WHERE e.durationInSeconds = :durationInSeconds"),
    @NamedQuery(name = "Electricityconsumption.findByAmountInWatts", query = "SELECT e FROM Electricityconsumption e WHERE e.amountInWatts = :amountInWatts")})
public class Electricityconsumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "electricityconsumptionid")
    private Integer electricityconsumptionid;
    @Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @Column(name = "starttime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;
    @Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @Column(name = "duration_in_seconds")
    private int durationInSeconds;
    @Basic(optional = false)
    //@javax.validation.constraints.NotNull
    @Column(name = "amount_in_watts")
    private float amountInWatts;
    @JoinColumn(name = "electricitycounter", referencedColumnName = "electricitycounterid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Electricitycounter electricitycounter;

    public Electricityconsumption() {
    }

    public Electricityconsumption(Integer electricityconsumptionid) {
        this.electricityconsumptionid = electricityconsumptionid;
    }

    public Electricityconsumption(Integer electricityconsumptionid, Date starttime, int durationInSeconds, float amountInWatts) {
        this.electricityconsumptionid = electricityconsumptionid;
        this.starttime = starttime;
        this.durationInSeconds = durationInSeconds;
        this.amountInWatts = amountInWatts;
    }

    public Integer getElectricityconsumptionid() {
        return electricityconsumptionid;
    }

    public void setElectricityconsumptionid(Integer electricityconsumptionid) {
        this.electricityconsumptionid = electricityconsumptionid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public float getAmountInWatts() {
        return amountInWatts;
    }

    public void setAmountInWatts(float amountInWatts) {
        this.amountInWatts = amountInWatts;
    }

    public Electricitycounter getElectricitycounter() {
        return electricitycounter;
    }

    public void setElectricitycounter(Electricitycounter electricitycounter) {
        this.electricitycounter = electricitycounter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (electricityconsumptionid != null ? electricityconsumptionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Electricityconsumption)) {
            return false;
        }
        Electricityconsumption other = (Electricityconsumption) object;
        if ((this.electricityconsumptionid == null && other.electricityconsumptionid != null) || (this.electricityconsumptionid != null && !this.electricityconsumptionid.equals(other.electricityconsumptionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.vermietet.domain.Electricityconsumption[ electricityconsumptionid=" + electricityconsumptionid + " ]";
    }
    
}
