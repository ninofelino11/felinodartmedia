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
@Table(name = "TCUSTSETTLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcustsettle.findAll", query = "SELECT t FROM Tcustsettle t"),
    @NamedQuery(name = "Tcustsettle.findByCustNo", query = "SELECT t FROM Tcustsettle t WHERE t.tcustsettlePK.custNo = :custNo"),
    @NamedQuery(name = "Tcustsettle.findBySettleSeq", query = "SELECT t FROM Tcustsettle t WHERE t.tcustsettlePK.settleSeq = :settleSeq"),
    @NamedQuery(name = "Tcustsettle.findBySettleGb", query = "SELECT t FROM Tcustsettle t WHERE t.settleGb = :settleGb"),
    @NamedQuery(name = "Tcustsettle.findByCardBankCode", query = "SELECT t FROM Tcustsettle t WHERE t.cardBankCode = :cardBankCode"),
    @NamedQuery(name = "Tcustsettle.findByBankSeq", query = "SELECT t FROM Tcustsettle t WHERE t.bankSeq = :bankSeq"),
    @NamedQuery(name = "Tcustsettle.findByCardNo", query = "SELECT t FROM Tcustsettle t WHERE t.cardNo = :cardNo"),
    @NamedQuery(name = "Tcustsettle.findByValidDate", query = "SELECT t FROM Tcustsettle t WHERE t.validDate = :validDate"),
    @NamedQuery(name = "Tcustsettle.findByDepositor", query = "SELECT t FROM Tcustsettle t WHERE t.depositor = :depositor"),
    @NamedQuery(name = "Tcustsettle.findByFamilyGb", query = "SELECT t FROM Tcustsettle t WHERE t.familyGb = :familyGb"),
    @NamedQuery(name = "Tcustsettle.findByDepoResiNo", query = "SELECT t FROM Tcustsettle t WHERE t.depoResiNo = :depoResiNo"),
    @NamedQuery(name = "Tcustsettle.findByCvv", query = "SELECT t FROM Tcustsettle t WHERE t.cvv = :cvv"),
    @NamedQuery(name = "Tcustsettle.findByUseYn", query = "SELECT t FROM Tcustsettle t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tcustsettle.findByIssueNumber", query = "SELECT t FROM Tcustsettle t WHERE t.issueNumber = :issueNumber"),
    @NamedQuery(name = "Tcustsettle.findByPayboxIdentifiant", query = "SELECT t FROM Tcustsettle t WHERE t.payboxIdentifiant = :payboxIdentifiant"),
    @NamedQuery(name = "Tcustsettle.findByPayboxCodetraitement", query = "SELECT t FROM Tcustsettle t WHERE t.payboxCodetraitement = :payboxCodetraitement"),
    @NamedQuery(name = "Tcustsettle.findByInsertId", query = "SELECT t FROM Tcustsettle t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tcustsettle.findByInsertDate", query = "SELECT t FROM Tcustsettle t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tcustsettle.findByModifyId", query = "SELECT t FROM Tcustsettle t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tcustsettle.findByModifyDate", query = "SELECT t FROM Tcustsettle t WHERE t.modifyDate = :modifyDate")})
public class Tcustsettle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcustsettlePK tcustsettlePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SETTLE_GB")
    private String settleGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CARD_BANK_CODE")
    private String cardBankCode;
    @Size(max = 3)
    @Column(name = "BANK_SEQ")
    private String bankSeq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 96)
    @Column(name = "CARD_NO")
    private String cardNo;
    @Size(max = 6)
    @Column(name = "VALID_DATE")
    private String validDate;
    @Size(max = 300)
    @Column(name = "DEPOSITOR")
    private String depositor;
    @Size(max = 1)
    @Column(name = "FAMILY_GB")
    private String familyGb;
    @Size(max = 192)
    @Column(name = "DEPO_RESI_NO")
    private String depoResiNo;
    @Size(max = 5)
    @Column(name = "CVV")
    private String cvv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USE_YN")
    private String useYn;
    @Size(max = 4)
    @Column(name = "ISSUE_NUMBER")
    private String issueNumber;
    @Size(max = 10)
    @Column(name = "PAYBOX_IDENTIFIANT")
    private String payboxIdentifiant;
    @Size(max = 3)
    @Column(name = "PAYBOX_CODETRAITEMENT")
    private String payboxCodetraitement;
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

    public Tcustsettle() {
    }

    public Tcustsettle(TcustsettlePK tcustsettlePK) {
        this.tcustsettlePK = tcustsettlePK;
    }

    public Tcustsettle(TcustsettlePK tcustsettlePK, String settleGb, String cardBankCode, String cardNo, String useYn, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.tcustsettlePK = tcustsettlePK;
        this.settleGb = settleGb;
        this.cardBankCode = cardBankCode;
        this.cardNo = cardNo;
        this.useYn = useYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public Tcustsettle(String custNo, String settleSeq) {
        this.tcustsettlePK = new TcustsettlePK(custNo, settleSeq);
    }

    public TcustsettlePK getTcustsettlePK() {
        return tcustsettlePK;
    }

    public void setTcustsettlePK(TcustsettlePK tcustsettlePK) {
        this.tcustsettlePK = tcustsettlePK;
    }

    public String getSettleGb() {
        return settleGb;
    }

    public void setSettleGb(String settleGb) {
        this.settleGb = settleGb;
    }

    public String getCardBankCode() {
        return cardBankCode;
    }

    public void setCardBankCode(String cardBankCode) {
        this.cardBankCode = cardBankCode;
    }

    public String getBankSeq() {
        return bankSeq;
    }

    public void setBankSeq(String bankSeq) {
        this.bankSeq = bankSeq;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getFamilyGb() {
        return familyGb;
    }

    public void setFamilyGb(String familyGb) {
        this.familyGb = familyGb;
    }

    public String getDepoResiNo() {
        return depoResiNo;
    }

    public void setDepoResiNo(String depoResiNo) {
        this.depoResiNo = depoResiNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getPayboxIdentifiant() {
        return payboxIdentifiant;
    }

    public void setPayboxIdentifiant(String payboxIdentifiant) {
        this.payboxIdentifiant = payboxIdentifiant;
    }

    public String getPayboxCodetraitement() {
        return payboxCodetraitement;
    }

    public void setPayboxCodetraitement(String payboxCodetraitement) {
        this.payboxCodetraitement = payboxCodetraitement;
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
        hash += (tcustsettlePK != null ? tcustsettlePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcustsettle)) {
            return false;
        }
        Tcustsettle other = (Tcustsettle) object;
        if ((this.tcustsettlePK == null && other.tcustsettlePK != null) || (this.tcustsettlePK != null && !this.tcustsettlePK.equals(other.tcustsettlePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tcustsettle[ tcustsettlePK=" + tcustsettlePK + " ]";
    }
    
}
