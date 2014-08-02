/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ni
 */
@Embeddable
public class TdelydayPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DELY_GB")
    private String delyGb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "YYMMDD")
    @Temporal(TemporalType.DATE)
    private Date yymmdd;

    public TdelydayPK() {
    }

    public TdelydayPK(String delyGb, Date yymmdd) {
        this.delyGb = delyGb;
        this.yymmdd = yymmdd;
    }

    public String getDelyGb() {
        return delyGb;
    }

    public void setDelyGb(String delyGb) {
        this.delyGb = delyGb;
    }

    public Date getYymmdd() {
        return yymmdd;
    }

    public void setYymmdd(Date yymmdd) {
        this.yymmdd = yymmdd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (delyGb != null ? delyGb.hashCode() : 0);
        hash += (yymmdd != null ? yymmdd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdelydayPK)) {
            return false;
        }
        TdelydayPK other = (TdelydayPK) object;
        if ((this.delyGb == null && other.delyGb != null) || (this.delyGb != null && !this.delyGb.equals(other.delyGb))) {
            return false;
        }
        if ((this.yymmdd == null && other.yymmdd != null) || (this.yymmdd != null && !this.yymmdd.equals(other.yymmdd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TdelydayPK[ delyGb=" + delyGb + ", yymmdd=" + yymmdd + " ]";
    }
    
}
