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
@Table(name = "TCUSTSPINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcustspinfo.findAll", query = "SELECT t FROM Tcustspinfo t"),
    @NamedQuery(name = "Tcustspinfo.findByCustNo", query = "SELECT t FROM Tcustspinfo t WHERE t.tcustspinfoPK.custNo = :custNo"),
    @NamedQuery(name = "Tcustspinfo.findBySeq", query = "SELECT t FROM Tcustspinfo t WHERE t.tcustspinfoPK.seq = :seq"),
    @NamedQuery(name = "Tcustspinfo.findByType", query = "SELECT t FROM Tcustspinfo t WHERE t.tcustspinfoPK.type = :type"),
    @NamedQuery(name = "Tcustspinfo.findByContent", query = "SELECT t FROM Tcustspinfo t WHERE t.content = :content"),
    @NamedQuery(name = "Tcustspinfo.findBySpBdate", query = "SELECT t FROM Tcustspinfo t WHERE t.spBdate = :spBdate"),
    @NamedQuery(name = "Tcustspinfo.findBySpEdate", query = "SELECT t FROM Tcustspinfo t WHERE t.spEdate = :spEdate"),
    @NamedQuery(name = "Tcustspinfo.findByMsg", query = "SELECT t FROM Tcustspinfo t WHERE t.msg = :msg"),
    @NamedQuery(name = "Tcustspinfo.findByInsertId", query = "SELECT t FROM Tcustspinfo t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tcustspinfo.findByInsertDate", query = "SELECT t FROM Tcustspinfo t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tcustspinfo.findByModifyId", query = "SELECT t FROM Tcustspinfo t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tcustspinfo.findByModifyDate", query = "SELECT t FROM Tcustspinfo t WHERE t.modifyDate = :modifyDate")})
public class Tcustspinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcustspinfoPK tcustspinfoPK;
    @Size(max = 36)
    @Column(name = "CONTENT")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SP_BDATE")
    @Temporal(TemporalType.DATE)
    private Date spBdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SP_EDATE")
    @Temporal(TemporalType.DATE)
    private Date spEdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3000)
    @Column(name = "MSG")
    private String msg;
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

    public Tcustspinfo() {
    }

    public Tcustspinfo(TcustspinfoPK tcustspinfoPK) {
        this.tcustspinfoPK = tcustspinfoPK;
    }

    public Tcustspinfo(TcustspinfoPK tcustspinfoPK, Date spBdate, Date spEdate, String msg, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.tcustspinfoPK = tcustspinfoPK;
        this.spBdate = spBdate;
        this.spEdate = spEdate;
        this.msg = msg;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public Tcustspinfo(String custNo, String seq, String type) {
        this.tcustspinfoPK = new TcustspinfoPK(custNo, seq, type);
    }

    public TcustspinfoPK getTcustspinfoPK() {
        return tcustspinfoPK;
    }

    public void setTcustspinfoPK(TcustspinfoPK tcustspinfoPK) {
        this.tcustspinfoPK = tcustspinfoPK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSpBdate() {
        return spBdate;
    }

    public void setSpBdate(Date spBdate) {
        this.spBdate = spBdate;
    }

    public Date getSpEdate() {
        return spEdate;
    }

    public void setSpEdate(Date spEdate) {
        this.spEdate = spEdate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
        hash += (tcustspinfoPK != null ? tcustspinfoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcustspinfo)) {
            return false;
        }
        Tcustspinfo other = (Tcustspinfo) object;
        if ((this.tcustspinfoPK == null && other.tcustspinfoPK != null) || (this.tcustspinfoPK != null && !this.tcustspinfoPK.equals(other.tcustspinfoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tcustspinfo[ tcustspinfoPK=" + tcustspinfoPK + " ]";
    }
    
}
