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
@Table(name = "TPROGRAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tprogram.findAll", query = "SELECT t FROM Tprogram t"),
    @NamedQuery(name = "Tprogram.findByProgCode", query = "SELECT t FROM Tprogram t WHERE t.progCode = :progCode"),
    @NamedQuery(name = "Tprogram.findByProgName", query = "SELECT t FROM Tprogram t WHERE t.progName = :progName"),
    @NamedQuery(name = "Tprogram.findByBdate", query = "SELECT t FROM Tprogram t WHERE t.bdate = :bdate"),
    @NamedQuery(name = "Tprogram.findByEdate", query = "SELECT t FROM Tprogram t WHERE t.edate = :edate"),
    @NamedQuery(name = "Tprogram.findByInsertId", query = "SELECT t FROM Tprogram t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tprogram.findByInsertDate", query = "SELECT t FROM Tprogram t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tprogram.findByModifyId", query = "SELECT t FROM Tprogram t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tprogram.findByModifyDate", query = "SELECT t FROM Tprogram t WHERE t.modifyDate = :modifyDate")})
public class Tprogram implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "PROG_CODE")
    private String progCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 900)
    @Column(name = "PROG_NAME")
    private String progName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BDATE")
    @Temporal(TemporalType.DATE)
    private Date bdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EDATE")
    @Temporal(TemporalType.DATE)
    private Date edate;
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

    public Tprogram() {
    }

    public Tprogram(String progCode) {
        this.progCode = progCode;
    }

    public Tprogram(String progCode, String progName, Date bdate, Date edate, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.progCode = progCode;
        this.progName = progName;
        this.bdate = bdate;
        this.edate = edate;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getProgCode() {
        return progCode;
    }

    public void setProgCode(String progCode) {
        this.progCode = progCode;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
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
        hash += (progCode != null ? progCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tprogram)) {
            return false;
        }
        Tprogram other = (Tprogram) object;
        if ((this.progCode == null && other.progCode != null) || (this.progCode != null && !this.progCode.equals(other.progCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tprogram[ progCode=" + progCode + " ]";
    }
    
}
