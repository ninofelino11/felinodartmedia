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
@Table(name = "TRECEIPTSBANK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Treceiptsbank.findAll", query = "SELECT t FROM Treceiptsbank t"),
    @NamedQuery(name = "Treceiptsbank.findByBankCode", query = "SELECT t FROM Treceiptsbank t WHERE t.treceiptsbankPK.bankCode = :bankCode"),
    @NamedQuery(name = "Treceiptsbank.findByBankSeq", query = "SELECT t FROM Treceiptsbank t WHERE t.treceiptsbankPK.bankSeq = :bankSeq"),
    @NamedQuery(name = "Treceiptsbank.findByBankName", query = "SELECT t FROM Treceiptsbank t WHERE t.bankName = :bankName"),
    @NamedQuery(name = "Treceiptsbank.findByBankDepositNo", query = "SELECT t FROM Treceiptsbank t WHERE t.bankDepositNo = :bankDepositNo"),
    @NamedQuery(name = "Treceiptsbank.findByBankAddr", query = "SELECT t FROM Treceiptsbank t WHERE t.bankAddr = :bankAddr"),
    @NamedQuery(name = "Treceiptsbank.findByBankBranch", query = "SELECT t FROM Treceiptsbank t WHERE t.bankBranch = :bankBranch"),
    @NamedQuery(name = "Treceiptsbank.findByBankManName", query = "SELECT t FROM Treceiptsbank t WHERE t.bankManName = :bankManName"),
    @NamedQuery(name = "Treceiptsbank.findByBankManDdd", query = "SELECT t FROM Treceiptsbank t WHERE t.bankManDdd = :bankManDdd"),
    @NamedQuery(name = "Treceiptsbank.findByBankManTel1", query = "SELECT t FROM Treceiptsbank t WHERE t.bankManTel1 = :bankManTel1"),
    @NamedQuery(name = "Treceiptsbank.findByBankManTel2", query = "SELECT t FROM Treceiptsbank t WHERE t.bankManTel2 = :bankManTel2"),
    @NamedQuery(name = "Treceiptsbank.findByBankManTel3", query = "SELECT t FROM Treceiptsbank t WHERE t.bankManTel3 = :bankManTel3"),
    @NamedQuery(name = "Treceiptsbank.findByVaccountYn", query = "SELECT t FROM Treceiptsbank t WHERE t.vaccountYn = :vaccountYn"),
    @NamedQuery(name = "Treceiptsbank.findByBkId", query = "SELECT t FROM Treceiptsbank t WHERE t.bkId = :bkId"),
    @NamedQuery(name = "Treceiptsbank.findByBuserId", query = "SELECT t FROM Treceiptsbank t WHERE t.buserId = :buserId"),
    @NamedQuery(name = "Treceiptsbank.findByType", query = "SELECT t FROM Treceiptsbank t WHERE t.type = :type"),
    @NamedQuery(name = "Treceiptsbank.findByUseYn", query = "SELECT t FROM Treceiptsbank t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Treceiptsbank.findByRemark", query = "SELECT t FROM Treceiptsbank t WHERE t.remark = :remark"),
    @NamedQuery(name = "Treceiptsbank.findByInsertId", query = "SELECT t FROM Treceiptsbank t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Treceiptsbank.findByInsertDate", query = "SELECT t FROM Treceiptsbank t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Treceiptsbank.findByServCode", query = "SELECT t FROM Treceiptsbank t WHERE t.servCode = :servCode")})
public class Treceiptsbank implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TreceiptsbankPK treceiptsbankPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "BANK_NAME")
    private String bankName;
    @Size(max = 20)
    @Column(name = "BANK_DEPOSIT_NO")
    private String bankDepositNo;
    @Size(max = 900)
    @Column(name = "BANK_ADDR")
    private String bankAddr;
    @Size(max = 300)
    @Column(name = "BANK_BRANCH")
    private String bankBranch;
    @Size(max = 300)
    @Column(name = "BANK_MAN_NAME")
    private String bankManName;
    @Size(max = 4)
    @Column(name = "BANK_MAN_DDD")
    private String bankManDdd;
    @Size(max = 4)
    @Column(name = "BANK_MAN_TEL1")
    private String bankManTel1;
    @Size(max = 4)
    @Column(name = "BANK_MAN_TEL2")
    private String bankManTel2;
    @Size(max = 6)
    @Column(name = "BANK_MAN_TEL3")
    private String bankManTel3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VACCOUNT_YN")
    private String vaccountYn;
    @Size(max = 10)
    @Column(name = "BK_ID")
    private String bkId;
    @Size(max = 8)
    @Column(name = "BUSER_ID")
    private String buserId;
    @Size(max = 2)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
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
    @Size(max = 10)
    @Column(name = "SERV_CODE")
    private String servCode;

    public Treceiptsbank() {
    }

    public Treceiptsbank(TreceiptsbankPK treceiptsbankPK) {
        this.treceiptsbankPK = treceiptsbankPK;
    }

    public Treceiptsbank(TreceiptsbankPK treceiptsbankPK, String bankName, String vaccountYn, String useYn, String insertId, Date insertDate) {
        this.treceiptsbankPK = treceiptsbankPK;
        this.bankName = bankName;
        this.vaccountYn = vaccountYn;
        this.useYn = useYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
    }

    public Treceiptsbank(String bankCode, String bankSeq) {
        this.treceiptsbankPK = new TreceiptsbankPK(bankCode, bankSeq);
    }

    public TreceiptsbankPK getTreceiptsbankPK() {
        return treceiptsbankPK;
    }

    public void setTreceiptsbankPK(TreceiptsbankPK treceiptsbankPK) {
        this.treceiptsbankPK = treceiptsbankPK;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankDepositNo() {
        return bankDepositNo;
    }

    public void setBankDepositNo(String bankDepositNo) {
        this.bankDepositNo = bankDepositNo;
    }

    public String getBankAddr() {
        return bankAddr;
    }

    public void setBankAddr(String bankAddr) {
        this.bankAddr = bankAddr;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankManName() {
        return bankManName;
    }

    public void setBankManName(String bankManName) {
        this.bankManName = bankManName;
    }

    public String getBankManDdd() {
        return bankManDdd;
    }

    public void setBankManDdd(String bankManDdd) {
        this.bankManDdd = bankManDdd;
    }

    public String getBankManTel1() {
        return bankManTel1;
    }

    public void setBankManTel1(String bankManTel1) {
        this.bankManTel1 = bankManTel1;
    }

    public String getBankManTel2() {
        return bankManTel2;
    }

    public void setBankManTel2(String bankManTel2) {
        this.bankManTel2 = bankManTel2;
    }

    public String getBankManTel3() {
        return bankManTel3;
    }

    public void setBankManTel3(String bankManTel3) {
        this.bankManTel3 = bankManTel3;
    }

    public String getVaccountYn() {
        return vaccountYn;
    }

    public void setVaccountYn(String vaccountYn) {
        this.vaccountYn = vaccountYn;
    }

    public String getBkId() {
        return bkId;
    }

    public void setBkId(String bkId) {
        this.bkId = bkId;
    }

    public String getBuserId() {
        return buserId;
    }

    public void setBuserId(String buserId) {
        this.buserId = buserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getServCode() {
        return servCode;
    }

    public void setServCode(String servCode) {
        this.servCode = servCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treceiptsbankPK != null ? treceiptsbankPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treceiptsbank)) {
            return false;
        }
        Treceiptsbank other = (Treceiptsbank) object;
        if ((this.treceiptsbankPK == null && other.treceiptsbankPK != null) || (this.treceiptsbankPK != null && !this.treceiptsbankPK.equals(other.treceiptsbankPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Treceiptsbank[ treceiptsbankPK=" + treceiptsbankPK + " ]";
    }
    
}
