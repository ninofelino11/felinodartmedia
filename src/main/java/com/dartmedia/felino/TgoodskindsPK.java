/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ni
 */
@Embeddable
public class TgoodskindsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "LGROUP")
    private String lgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MGROUP")
    private String mgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SGROUP")
    private String sgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DGROUP")
    private String dgroup;

    public TgoodskindsPK() {
    }

    public TgoodskindsPK(String lgroup, String mgroup, String sgroup, String dgroup) {
        this.lgroup = lgroup;
        this.mgroup = mgroup;
        this.sgroup = sgroup;
        this.dgroup = dgroup;
    }

    public String getLgroup() {
        return lgroup;
    }

    public void setLgroup(String lgroup) {
        this.lgroup = lgroup;
    }

    public String getMgroup() {
        return mgroup;
    }

    public void setMgroup(String mgroup) {
        this.mgroup = mgroup;
    }

    public String getSgroup() {
        return sgroup;
    }

    public void setSgroup(String sgroup) {
        this.sgroup = sgroup;
    }

    public String getDgroup() {
        return dgroup;
    }

    public void setDgroup(String dgroup) {
        this.dgroup = dgroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgroup != null ? lgroup.hashCode() : 0);
        hash += (mgroup != null ? mgroup.hashCode() : 0);
        hash += (sgroup != null ? sgroup.hashCode() : 0);
        hash += (dgroup != null ? dgroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TgoodskindsPK)) {
            return false;
        }
        TgoodskindsPK other = (TgoodskindsPK) object;
        if ((this.lgroup == null && other.lgroup != null) || (this.lgroup != null && !this.lgroup.equals(other.lgroup))) {
            return false;
        }
        if ((this.mgroup == null && other.mgroup != null) || (this.mgroup != null && !this.mgroup.equals(other.mgroup))) {
            return false;
        }
        if ((this.sgroup == null && other.sgroup != null) || (this.sgroup != null && !this.sgroup.equals(other.sgroup))) {
            return false;
        }
        if ((this.dgroup == null && other.dgroup != null) || (this.dgroup != null && !this.dgroup.equals(other.dgroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TgoodskindsPK[ lgroup=" + lgroup + ", mgroup=" + mgroup + ", sgroup=" + sgroup + ", dgroup=" + dgroup + " ]";
    }
    
}
