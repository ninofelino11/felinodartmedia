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
@Table(name = "TBANK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbank.findAll", query = "SELECT t FROM Tbank t"),
    @NamedQuery(name = "Tbank.findByBankCode", query = "SELECT t FROM Tbank t WHERE t.bankCode = :bankCode"),
    @NamedQuery(name = "Tbank.findByBankName", query = "SELECT t FROM Tbank t WHERE t.bankName = :bankName"),
    @NamedQuery(name = "Tbank.findByRemark", query = "SELECT t FROM Tbank t WHERE t.remark = :remark"),
    @NamedQuery(name = "Tbank.findByUseYn", query = "SELECT t FROM Tbank t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tbank.findByInsertId", query = "SELECT t FROM Tbank t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tbank.findByInsertDate", query = "SELECT t FROM Tbank t WHERE t.insertDate = :insertDate")})
public class Tbank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "BANK_CODE")
    private String bankCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "BANK_NAME")
    private String bankName;
    @Size(max = 1500)
    @Column(name = "REMARK")
    private String remark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
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

    public Tbank() {
    }

    public Tbank(String bankCode) {
        this.bankCode = bankCode;
    }

    public Tbank(String bankCode, String bankName, String useYn, String insertId, Date insertDate) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.useYn = useYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        hash += (bankCode != null ? bankCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbank)) {
            return false;
        }
        Tbank other = (Tbank) object;
        if ((this.bankCode == null && other.bankCode != null) || (this.bankCode != null && !this.bankCode.equals(other.bankCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tbank[ bankCode=" + bankCode + " ]";
    }
    
}
