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
public class TcusttelPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "TEL")
    private String tel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CUST_NO")
    private String custNo;

    public TcusttelPK() {
    }

    public TcusttelPK(String tel, String custNo) {
        this.tel = tel;
        this.custNo = custNo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tel != null ? tel.hashCode() : 0);
        hash += (custNo != null ? custNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcusttelPK)) {
            return false;
        }
        TcusttelPK other = (TcusttelPK) object;
        if ((this.tel == null && other.tel != null) || (this.tel != null && !this.tel.equals(other.tel))) {
            return false;
        }
        if ((this.custNo == null && other.custNo != null) || (this.custNo != null && !this.custNo.equals(other.custNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TcusttelPK[ tel=" + tel + ", custNo=" + custNo + " ]";
    }
    
}
