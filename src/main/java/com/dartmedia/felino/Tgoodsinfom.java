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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ni
 */
@Entity
@Table(name = "TGOODSINFOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tgoodsinfom.findAll", query = "SELECT t FROM Tgoodsinfom t"),
    @NamedQuery(name = "Tgoodsinfom.findByCspfFlag", query = "SELECT t FROM Tgoodsinfom t WHERE t.cspfFlag = :cspfFlag"),
    @NamedQuery(name = "Tgoodsinfom.findByCspfGroup", query = "SELECT t FROM Tgoodsinfom t WHERE t.cspfGroup = :cspfGroup"),
    @NamedQuery(name = "Tgoodsinfom.findByCspfDesc", query = "SELECT t FROM Tgoodsinfom t WHERE t.cspfDesc = :cspfDesc"),
    @NamedQuery(name = "Tgoodsinfom.findByUseYn", query = "SELECT t FROM Tgoodsinfom t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tgoodsinfom.findByInsertId", query = "SELECT t FROM Tgoodsinfom t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tgoodsinfom.findByInsertDate", query = "SELECT t FROM Tgoodsinfom t WHERE t.insertDate = :insertDate")})
public class Tgoodsinfom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CSPF_FLAG")
    private String cspfFlag;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CSPF_GROUP")
    private String cspfGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "CSPF_DESC")
    private String cspfDesc;
    @Size(max = 1)
    @Column(name = "USE_YN")
    private String useYn;
    @Size(max = 10)
    @Column(name = "INSERT_ID")
    private String insertId;
    @Column(name = "INSERT_DATE")
    @Temporal(TemporalType.DATE)
    private Date insertDate;

    public Tgoodsinfom() {
    }

    public Tgoodsinfom(String cspfGroup) {
        this.cspfGroup = cspfGroup;
    }

    public Tgoodsinfom(String cspfGroup, String cspfFlag, String cspfDesc) {
        this.cspfGroup = cspfGroup;
        this.cspfFlag = cspfFlag;
        this.cspfDesc = cspfDesc;
    }

    public String getCspfFlag() {
        return cspfFlag;
    }

    public void setCspfFlag(String cspfFlag) {
        this.cspfFlag = cspfFlag;
    }

    public String getCspfGroup() {
        return cspfGroup;
    }

    public void setCspfGroup(String cspfGroup) {
        this.cspfGroup = cspfGroup;
    }

    public String getCspfDesc() {
        return cspfDesc;
    }

    public void setCspfDesc(String cspfDesc) {
        this.cspfDesc = cspfDesc;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getInsertId() {
        return insertId;
    }

    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cspfGroup != null ? cspfGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tgoodsinfom)) {
            return false;
        }
        Tgoodsinfom other = (Tgoodsinfom) object;
        if ((this.cspfGroup == null && other.cspfGroup != null) || (this.cspfGroup != null && !this.cspfGroup.equals(other.cspfGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tgoodsinfom[ cspfGroup=" + cspfGroup + " ]";
    }
    
}
