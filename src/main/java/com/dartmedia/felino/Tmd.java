/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ni
 */
@Entity
@Table(name = "TMD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmd.findAll", query = "SELECT t FROM Tmd t"),
    @NamedQuery(name = "Tmd.findByMdCode", query = "SELECT t FROM Tmd t WHERE t.mdCode = :mdCode"),
    @NamedQuery(name = "Tmd.findByMdName", query = "SELECT t FROM Tmd t WHERE t.mdName = :mdName"),
    @NamedQuery(name = "Tmd.findByUseYn", query = "SELECT t FROM Tmd t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tmd.findByRemark", query = "SELECT t FROM Tmd t WHERE t.remark = :remark"),
    @NamedQuery(name = "Tmd.findByInsertId", query = "SELECT t FROM Tmd t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tmd.findByInsertDate", query = "SELECT t FROM Tmd t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tmd.findByModifyId", query = "SELECT t FROM Tmd t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tmd.findByModifyDate", query = "SELECT t FROM Tmd t WHERE t.modifyDate = :modifyDate")})
public class Tmd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "MD_CODE")
    private String mdCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 600)
    @Column(name = "MD_NAME")
    private String mdName;
    @Size(max = 1)
    @Column(name = "USE_YN")
    private String useYn;
    @Size(max = 900)
    @Column(name = "REMARK")
    private String remark;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdCode")
    private Collection<Tbaljum> tbaljumCollection;

    public Tmd() {
    }

    public Tmd(String mdCode) {
        this.mdCode = mdCode;
    }

    public Tmd(String mdCode, String mdName, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.mdCode = mdCode;
        this.mdName = mdName;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getMdCode() {
        return mdCode;
    }

    public void setMdCode(String mdCode) {
        this.mdCode = mdCode;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @XmlTransient
    public Collection<Tbaljum> getTbaljumCollection() {
        return tbaljumCollection;
    }

    public void setTbaljumCollection(Collection<Tbaljum> tbaljumCollection) {
        this.tbaljumCollection = tbaljumCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdCode != null ? mdCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tmd)) {
            return false;
        }
        Tmd other = (Tmd) object;
        if ((this.mdCode == null && other.mdCode != null) || (this.mdCode != null && !this.mdCode.equals(other.mdCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tmd[ mdCode=" + mdCode + " ]";
    }
    
}
