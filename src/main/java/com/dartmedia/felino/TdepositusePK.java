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
public class TdepositusePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CUST_NO")
    private String custNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "USE_SEQ")
    private String useSeq;

    public TdepositusePK() {
    }

    public TdepositusePK(String custNo, String useSeq) {
        this.custNo = custNo;
        this.useSeq = useSeq;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getUseSeq() {
        return useSeq;
    }

    public void setUseSeq(String useSeq) {
        this.useSeq = useSeq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custNo != null ? custNo.hashCode() : 0);
        hash += (useSeq != null ? useSeq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdepositusePK)) {
            return false;
        }
        TdepositusePK other = (TdepositusePK) object;
        if ((this.custNo == null && other.custNo != null) || (this.custNo != null && !this.custNo.equals(other.custNo))) {
            return false;
        }
        if ((this.useSeq == null && other.useSeq != null) || (this.useSeq != null && !this.useSeq.equals(other.useSeq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TdepositusePK[ custNo=" + custNo + ", useSeq=" + useSeq + " ]";
    }
    
}
