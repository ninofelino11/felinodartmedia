/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ni
 */
@Entity
@Table(name = "TGOODSINFODT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tgoodsinfodt.findAll", query = "SELECT t FROM Tgoodsinfodt t"),
    @NamedQuery(name = "Tgoodsinfodt.findByCspfGroup", query = "SELECT t FROM Tgoodsinfodt t WHERE t.tgoodsinfodtPK.cspfGroup = :cspfGroup"),
    @NamedQuery(name = "Tgoodsinfodt.findByCspfCode", query = "SELECT t FROM Tgoodsinfodt t WHERE t.tgoodsinfodtPK.cspfCode = :cspfCode"),
    @NamedQuery(name = "Tgoodsinfodt.findByCspfName", query = "SELECT t FROM Tgoodsinfodt t WHERE t.cspfName = :cspfName"),
    @NamedQuery(name = "Tgoodsinfodt.findByUseYn", query = "SELECT t FROM Tgoodsinfodt t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tgoodsinfodt.findByInsertId", query = "SELECT t FROM Tgoodsinfodt t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tgoodsinfodt.findByInsertDate", query = "SELECT t FROM Tgoodsinfodt t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tgoodsinfodt.findByCspfNameKr", query = "SELECT t FROM Tgoodsinfodt t WHERE t.cspfNameKr = :cspfNameKr")})
public class Tgoodsinfodt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TgoodsinfodtPK tgoodsinfodtPK;
    @Size(max = 180)
    @Column(name = "CSPF_NAME")
    private String cspfName;
    @Size(max = 1)
    @Column(name = "USE_YN")
    private String useYn;
    @Size(max = 10)
    @Column(name = "INSERT_ID")
    private String insertId;
    @Column(name = "INSERT_DATE")
    @Temporal(TemporalType.DATE)
    private Date insertDate;
    @Size(max = 180)
    @Column(name = "CSPF_NAME_KR")
    private String cspfNameKr;

    public Tgoodsinfodt() {
    }

    public Tgoodsinfodt(TgoodsinfodtPK tgoodsinfodtPK) {
        this.tgoodsinfodtPK = tgoodsinfodtPK;
    }

    public Tgoodsinfodt(String cspfGroup, String cspfCode) {
        this.tgoodsinfodtPK = new TgoodsinfodtPK(cspfGroup, cspfCode);
    }

    public TgoodsinfodtPK getTgoodsinfodtPK() {
        return tgoodsinfodtPK;
    }

    public void setTgoodsinfodtPK(TgoodsinfodtPK tgoodsinfodtPK) {
        this.tgoodsinfodtPK = tgoodsinfodtPK;
    }

    public String getCspfName() {
        return cspfName;
    }

    public void setCspfName(String cspfName) {
        this.cspfName = cspfName;
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

    public String getCspfNameKr() {
        return cspfNameKr;
    }

    public void setCspfNameKr(String cspfNameKr) {
        this.cspfNameKr = cspfNameKr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tgoodsinfodtPK != null ? tgoodsinfodtPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tgoodsinfodt)) {
            return false;
        }
        Tgoodsinfodt other = (Tgoodsinfodt) object;
        if ((this.tgoodsinfodtPK == null && other.tgoodsinfodtPK != null) || (this.tgoodsinfodtPK != null && !this.tgoodsinfodtPK.equals(other.tgoodsinfodtPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tgoodsinfodt[ tgoodsinfodtPK=" + tgoodsinfodtPK + " ]";
    }
    
}
