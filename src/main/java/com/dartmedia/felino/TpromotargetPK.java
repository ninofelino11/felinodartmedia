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
public class TpromotargetPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "PROMO_NO")
    private String promoNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "PROMO_SEQ")
    private String promoSeq;

    public TpromotargetPK() {
    }

    public TpromotargetPK(String promoNo, String promoSeq) {
        this.promoNo = promoNo;
        this.promoSeq = promoSeq;
    }

    public String getPromoNo() {
        return promoNo;
    }

    public void setPromoNo(String promoNo) {
        this.promoNo = promoNo;
    }

    public String getPromoSeq() {
        return promoSeq;
    }

    public void setPromoSeq(String promoSeq) {
        this.promoSeq = promoSeq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promoNo != null ? promoNo.hashCode() : 0);
        hash += (promoSeq != null ? promoSeq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TpromotargetPK)) {
            return false;
        }
        TpromotargetPK other = (TpromotargetPK) object;
        if ((this.promoNo == null && other.promoNo != null) || (this.promoNo != null && !this.promoNo.equals(other.promoNo))) {
            return false;
        }
        if ((this.promoSeq == null && other.promoSeq != null) || (this.promoSeq != null && !this.promoSeq.equals(other.promoSeq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TpromotargetPK[ promoNo=" + promoNo + ", promoSeq=" + promoSeq + " ]";
    }
    
}
