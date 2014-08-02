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
@Table(name = "TDESCRIBECODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdescribecode.findAll", query = "SELECT t FROM Tdescribecode t"),
    @NamedQuery(name = "Tdescribecode.findByDescribeCode", query = "SELECT t FROM Tdescribecode t WHERE t.describeCode = :describeCode"),
    @NamedQuery(name = "Tdescribecode.findByDescribeTitle", query = "SELECT t FROM Tdescribecode t WHERE t.describeTitle = :describeTitle"),
    @NamedQuery(name = "Tdescribecode.findByWebFlag", query = "SELECT t FROM Tdescribecode t WHERE t.webFlag = :webFlag"),
    @NamedQuery(name = "Tdescribecode.findBySortSeq", query = "SELECT t FROM Tdescribecode t WHERE t.sortSeq = :sortSeq"),
    @NamedQuery(name = "Tdescribecode.findByUseYn", query = "SELECT t FROM Tdescribecode t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tdescribecode.findByInsertId", query = "SELECT t FROM Tdescribecode t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tdescribecode.findByInsertDate", query = "SELECT t FROM Tdescribecode t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tdescribecode.findByModifyId", query = "SELECT t FROM Tdescribecode t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tdescribecode.findByModifyDate", query = "SELECT t FROM Tdescribecode t WHERE t.modifyDate = :modifyDate"),
    @NamedQuery(name = "Tdescribecode.findByRequiredYn", query = "SELECT t FROM Tdescribecode t WHERE t.requiredYn = :requiredYn"),
    @NamedQuery(name = "Tdescribecode.findByDescribeTitleEn", query = "SELECT t FROM Tdescribecode t WHERE t.describeTitleEn = :describeTitleEn")})
public class Tdescribecode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "DESCRIBE_CODE")
    private String describeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "DESCRIBE_TITLE")
    private String describeTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "WEB_FLAG")
    private String webFlag;
    @Size(max = 3)
    @Column(name = "SORT_SEQ")
    private String sortSeq;
    @Size(max = 1)
    @Column(name = "USE_YN")
    private String useYn;
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
    @Size(max = 1)
    @Column(name = "REQUIRED_YN")
    private String requiredYn;
    @Size(max = 120)
    @Column(name = "DESCRIBE_TITLE_EN")
    private String describeTitleEn;

    public Tdescribecode() {
    }

    public Tdescribecode(String describeCode) {
        this.describeCode = describeCode;
    }

    public Tdescribecode(String describeCode, String describeTitle, String webFlag, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.describeCode = describeCode;
        this.describeTitle = describeTitle;
        this.webFlag = webFlag;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getDescribeCode() {
        return describeCode;
    }

    public void setDescribeCode(String describeCode) {
        this.describeCode = describeCode;
    }

    public String getDescribeTitle() {
        return describeTitle;
    }

    public void setDescribeTitle(String describeTitle) {
        this.describeTitle = describeTitle;
    }

    public String getWebFlag() {
        return webFlag;
    }

    public void setWebFlag(String webFlag) {
        this.webFlag = webFlag;
    }

    public String getSortSeq() {
        return sortSeq;
    }

    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
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

    public String getRequiredYn() {
        return requiredYn;
    }

    public void setRequiredYn(String requiredYn) {
        this.requiredYn = requiredYn;
    }

    public String getDescribeTitleEn() {
        return describeTitleEn;
    }

    public void setDescribeTitleEn(String describeTitleEn) {
        this.describeTitleEn = describeTitleEn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (describeCode != null ? describeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdescribecode)) {
            return false;
        }
        Tdescribecode other = (Tdescribecode) object;
        if ((this.describeCode == null && other.describeCode != null) || (this.describeCode != null && !this.describeCode.equals(other.describeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tdescribecode[ describeCode=" + describeCode + " ]";
    }
    
}
