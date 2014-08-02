/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ni
 */
@Entity
@Table(name = "TCUSTTEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcusttel.findAll", query = "SELECT t FROM Tcusttel t"),
    @NamedQuery(name = "Tcusttel.findByTel", query = "SELECT t FROM Tcusttel t WHERE t.tcusttelPK.tel = :tel"),
    @NamedQuery(name = "Tcusttel.findByCustNo", query = "SELECT t FROM Tcusttel t WHERE t.tcusttelPK.custNo = :custNo")})
public class Tcusttel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcusttelPK tcusttelPK;

    public Tcusttel() {
    }

    public Tcusttel(TcusttelPK tcusttelPK) {
        this.tcusttelPK = tcusttelPK;
    }

    public Tcusttel(String tel, String custNo) {
        this.tcusttelPK = new TcusttelPK(tel, custNo);
    }

    public TcusttelPK getTcusttelPK() {
        return tcusttelPK;
    }

    public void setTcusttelPK(TcusttelPK tcusttelPK) {
        this.tcusttelPK = tcusttelPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcusttelPK != null ? tcusttelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcusttel)) {
            return false;
        }
        Tcusttel other = (Tcusttel) object;
        if ((this.tcusttelPK == null && other.tcusttelPK != null) || (this.tcusttelPK != null && !this.tcusttelPK.equals(other.tcusttelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tcusttel[ tcusttelPK=" + tcusttelPK + " ]";
    }
    
}
