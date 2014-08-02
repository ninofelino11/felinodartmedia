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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "TDELYDAY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdelyday.findAll", query = "SELECT t FROM Tdelyday t"),
    @NamedQuery(name = "Tdelyday.findByDelyGb", query = "SELECT t FROM Tdelyday t WHERE t.tdelydayPK.delyGb = :delyGb"),
    @NamedQuery(name = "Tdelyday.findByYymmdd", query = "SELECT t FROM Tdelyday t WHERE t.tdelydayPK.yymmdd = :yymmdd"),
    @NamedQuery(name = "Tdelyday.findByDayGb", query = "SELECT t FROM Tdelyday t WHERE t.dayGb = :dayGb"),
    @NamedQuery(name = "Tdelyday.findByWorkYn", query = "SELECT t FROM Tdelyday t WHERE t.workYn = :workYn"),
    @NamedQuery(name = "Tdelyday.findByOffName", query = "SELECT t FROM Tdelyday t WHERE t.offName = :offName"),
    @NamedQuery(name = "Tdelyday.findByInsertId", query = "SELECT t FROM Tdelyday t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tdelyday.findByInsertDate", query = "SELECT t FROM Tdelyday t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tdelyday.findByModifyId", query = "SELECT t FROM Tdelyday t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tdelyday.findByModifyDate", query = "SELECT t FROM Tdelyday t WHERE t.modifyDate = :modifyDate")})
public class Tdelyday implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TdelydayPK tdelydayPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "DAY_GB")
    private String dayGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "WORK_YN")
    private String workYn;
    @Size(max = 240)
    @Column(name = "OFF_NAME")
    private String offName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "INSERT_ID")
    private String insertId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSERT_DATE")
    @Temporal(TemporalType.DATE)
    private Date insertDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MODIFY_ID")
    private String modifyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    public Tdelyday() {
    }

    public Tdelyday(TdelydayPK tdelydayPK) {
        this.tdelydayPK = tdelydayPK;
    }

    public Tdelyday(TdelydayPK tdelydayPK, String dayGb, String workYn, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.tdelydayPK = tdelydayPK;
        this.dayGb = dayGb;
        this.workYn = workYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public Tdelyday(String delyGb, Date yymmdd) {
        this.tdelydayPK = new TdelydayPK(delyGb, yymmdd);
    }

    public TdelydayPK getTdelydayPK() {
        return tdelydayPK;
    }

    public void setTdelydayPK(TdelydayPK tdelydayPK) {
        this.tdelydayPK = tdelydayPK;
    }

    public String getDayGb() {
        return dayGb;
    }

    public void setDayGb(String dayGb) {
        this.dayGb = dayGb;
    }

    public String getWorkYn() {
        return workYn;
    }

    public void setWorkYn(String workYn) {
        this.workYn = workYn;
    }

    public String getOffName() {
        return offName;
    }

    public void setOffName(String offName) {
        this.offName = offName;
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

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tdelydayPK != null ? tdelydayPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdelyday)) {
            return false;
        }
        Tdelyday other = (Tdelyday) object;
        if ((this.tdelydayPK == null && other.tdelydayPK != null) || (this.tdelydayPK != null && !this.tdelydayPK.equals(other.tdelydayPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tdelyday[ tdelydayPK=" + tdelydayPK + " ]";
    }
    
}
