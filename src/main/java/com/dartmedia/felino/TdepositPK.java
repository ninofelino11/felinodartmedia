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
public class TdepositPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CUST_NO")
    private String custNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DEPOSITAMT_SEQ")
    private String depositamtSeq;

    public TdepositPK() {
    }

    public TdepositPK(String custNo, String depositamtSeq) {
        this.custNo = custNo;
        this.depositamtSeq = depositamtSeq;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getDepositamtSeq() {
        return depositamtSeq;
    }

    public void setDepositamtSeq(String depositamtSeq) {
        this.depositamtSeq = depositamtSeq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custNo != null ? custNo.hashCode() : 0);
        hash += (depositamtSeq != null ? depositamtSeq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdepositPK)) {
            return false;
        }
        TdepositPK other = (TdepositPK) object;
        if ((this.custNo == null && other.custNo != null) || (this.custNo != null && !this.custNo.equals(other.custNo))) {
            return false;
        }
        if ((this.depositamtSeq == null && other.depositamtSeq != null) || (this.depositamtSeq != null && !this.depositamtSeq.equals(other.depositamtSeq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TdepositPK[ custNo=" + custNo + ", depositamtSeq=" + depositamtSeq + " ]";
    }
    
}
