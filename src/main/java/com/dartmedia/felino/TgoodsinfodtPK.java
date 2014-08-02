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
public class TgoodsinfodtPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CSPF_GROUP")
    private String cspfGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CSPF_CODE")
    private String cspfCode;

    public TgoodsinfodtPK() {
    }

    public TgoodsinfodtPK(String cspfGroup, String cspfCode) {
        this.cspfGroup = cspfGroup;
        this.cspfCode = cspfCode;
    }

    public String getCspfGroup() {
        return cspfGroup;
    }

    public void setCspfGroup(String cspfGroup) {
        this.cspfGroup = cspfGroup;
    }

    public String getCspfCode() {
        return cspfCode;
    }

    public void setCspfCode(String cspfCode) {
        this.cspfCode = cspfCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cspfGroup != null ? cspfGroup.hashCode() : 0);
        hash += (cspfCode != null ? cspfCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TgoodsinfodtPK)) {
            return false;
        }
        TgoodsinfodtPK other = (TgoodsinfodtPK) object;
        if ((this.cspfGroup == null && other.cspfGroup != null) || (this.cspfGroup != null && !this.cspfGroup.equals(other.cspfGroup))) {
            return false;
        }
        if ((this.cspfCode == null && other.cspfCode != null) || (this.cspfCode != null && !this.cspfCode.equals(other.cspfCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TgoodsinfodtPK[ cspfGroup=" + cspfGroup + ", cspfCode=" + cspfCode + " ]";
    }
    
}
