/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "TENTERPRISE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tenterprise.findAll", query = "SELECT t FROM Tenterprise t"),
    @NamedQuery(name = "Tenterprise.findByEntpCode", query = "SELECT t FROM Tenterprise t WHERE t.entpCode = :entpCode"),
    @NamedQuery(name = "Tenterprise.findByEntpName", query = "SELECT t FROM Tenterprise t WHERE t.entpName = :entpName"),
    @NamedQuery(name = "Tenterprise.findByEntpGubun", query = "SELECT t FROM Tenterprise t WHERE t.entpGubun = :entpGubun"),
    @NamedQuery(name = "Tenterprise.findBySIdno", query = "SELECT t FROM Tenterprise t WHERE t.sIdno = :sIdno"),
    @NamedQuery(name = "Tenterprise.findByWorkType", query = "SELECT t FROM Tenterprise t WHERE t.workType = :workType"),
    @NamedQuery(name = "Tenterprise.findByWorkKind", query = "SELECT t FROM Tenterprise t WHERE t.workKind = :workKind"),
    @NamedQuery(name = "Tenterprise.findByEntpPost", query = "SELECT t FROM Tenterprise t WHERE t.entpPost = :entpPost"),
    @NamedQuery(name = "Tenterprise.findByEntpPostSeq", query = "SELECT t FROM Tenterprise t WHERE t.entpPostSeq = :entpPostSeq"),
    @NamedQuery(name = "Tenterprise.findByEntpAddr", query = "SELECT t FROM Tenterprise t WHERE t.entpAddr = :entpAddr"),
    @NamedQuery(name = "Tenterprise.findByEntpDdd", query = "SELECT t FROM Tenterprise t WHERE t.entpDdd = :entpDdd"),
    @NamedQuery(name = "Tenterprise.findByEntpTel1", query = "SELECT t FROM Tenterprise t WHERE t.entpTel1 = :entpTel1"),
    @NamedQuery(name = "Tenterprise.findByEntpTel2", query = "SELECT t FROM Tenterprise t WHERE t.entpTel2 = :entpTel2"),
    @NamedQuery(name = "Tenterprise.findByEntpTel3", query = "SELECT t FROM Tenterprise t WHERE t.entpTel3 = :entpTel3"),
    @NamedQuery(name = "Tenterprise.findByEntpFax1", query = "SELECT t FROM Tenterprise t WHERE t.entpFax1 = :entpFax1"),
    @NamedQuery(name = "Tenterprise.findByEntpFax2", query = "SELECT t FROM Tenterprise t WHERE t.entpFax2 = :entpFax2"),
    @NamedQuery(name = "Tenterprise.findByEntpFax3", query = "SELECT t FROM Tenterprise t WHERE t.entpFax3 = :entpFax3"),
    @NamedQuery(name = "Tenterprise.findByEmailAddr", query = "SELECT t FROM Tenterprise t WHERE t.emailAddr = :emailAddr"),
    @NamedQuery(name = "Tenterprise.findByOwnerName", query = "SELECT t FROM Tenterprise t WHERE t.ownerName = :ownerName"),
    @NamedQuery(name = "Tenterprise.findByDishonorYn", query = "SELECT t FROM Tenterprise t WHERE t.dishonorYn = :dishonorYn"),
    @NamedQuery(name = "Tenterprise.findBySecretNo", query = "SELECT t FROM Tenterprise t WHERE t.secretNo = :secretNo"),
    @NamedQuery(name = "Tenterprise.findByFirstDate", query = "SELECT t FROM Tenterprise t WHERE t.firstDate = :firstDate"),
    @NamedQuery(name = "Tenterprise.findByCloseDate", query = "SELECT t FROM Tenterprise t WHERE t.closeDate = :closeDate"),
    @NamedQuery(name = "Tenterprise.findByCloseReason", query = "SELECT t FROM Tenterprise t WHERE t.closeReason = :closeReason"),
    @NamedQuery(name = "Tenterprise.findByDelyGb", query = "SELECT t FROM Tenterprise t WHERE t.delyGb = :delyGb"),
    @NamedQuery(name = "Tenterprise.findByTaxRate", query = "SELECT t FROM Tenterprise t WHERE t.taxRate = :taxRate"),
    @NamedQuery(name = "Tenterprise.findByEtc", query = "SELECT t FROM Tenterprise t WHERE t.etc = :etc"),
    @NamedQuery(name = "Tenterprise.findByInsertId", query = "SELECT t FROM Tenterprise t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tenterprise.findByInsertDate", query = "SELECT t FROM Tenterprise t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tenterprise.findByModifyId", query = "SELECT t FROM Tenterprise t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tenterprise.findByModifyDate", query = "SELECT t FROM Tenterprise t WHERE t.modifyDate = :modifyDate"),
    @NamedQuery(name = "Tenterprise.findByForeignYn", query = "SELECT t FROM Tenterprise t WHERE t.foreignYn = :foreignYn"),
    @NamedQuery(name = "Tenterprise.findByAccountGb", query = "SELECT t FROM Tenterprise t WHERE t.accountGb = :accountGb"),
    @NamedQuery(name = "Tenterprise.findBySapVendorCode", query = "SELECT t FROM Tenterprise t WHERE t.sapVendorCode = :sapVendorCode"),
    @NamedQuery(name = "Tenterprise.findByPaymentTerm", query = "SELECT t FROM Tenterprise t WHERE t.paymentTerm = :paymentTerm"),
    @NamedQuery(name = "Tenterprise.findBySentToMnc", query = "SELECT t FROM Tenterprise t WHERE t.sentToMnc = :sentToMnc")})
public class Tenterprise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ENTP_CODE")
    private String entpCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "ENTP_NAME")
    private String entpName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ENTP_GUBUN")
    private String entpGubun;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "S_IDNO")
    private String sIdno;
    @Size(max = 180)
    @Column(name = "WORK_TYPE")
    private String workType;
    @Size(max = 180)
    @Column(name = "WORK_KIND")
    private String workKind;
    @Size(max = 8)
    @Column(name = "ENTP_POST")
    private String entpPost;
    @Size(max = 3)
    @Column(name = "ENTP_POST_SEQ")
    private String entpPostSeq;
    @Size(max = 900)
    @Column(name = "ENTP_ADDR")
    private String entpAddr;
    @Size(max = 4)
    @Column(name = "ENTP_DDD")
    private String entpDdd;
    @Size(max = 4)
    @Column(name = "ENTP_TEL1")
    private String entpTel1;
    @Size(max = 4)
    @Column(name = "ENTP_TEL2")
    private String entpTel2;
    @Size(max = 50)
    @Column(name = "ENTP_TEL3")
    private String entpTel3;
    @Size(max = 4)
    @Column(name = "ENTP_FAX1")
    private String entpFax1;
    @Size(max = 4)
    @Column(name = "ENTP_FAX2")
    private String entpFax2;
    @Size(max = 4)
    @Column(name = "ENTP_FAX3")
    private String entpFax3;
    @Size(max = 900)
    @Column(name = "EMAIL_ADDR")
    private String emailAddr;
    @Size(max = 270)
    @Column(name = "OWNER_NAME")
    private String ownerName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DISHONOR_YN")
    private String dishonorYn;
    @Size(max = 10)
    @Column(name = "SECRET_NO")
    private String secretNo;
    @Column(name = "FIRST_DATE")
    @Temporal(TemporalType.DATE)
    private Date firstDate;
    @Column(name = "CLOSE_DATE")
    @Temporal(TemporalType.DATE)
    private Date closeDate;
    @Size(max = 900)
    @Column(name = "CLOSE_REASON")
    private String closeReason;
    @Size(max = 2)
    @Column(name = "DELY_GB")
    private String delyGb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TAX_RATE")
    private BigDecimal taxRate;
    @Size(max = 600)
    @Column(name = "ETC")
    private String etc;
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
    @Column(name = "FOREIGN_YN")
    private String foreignYn;
    @Size(max = 4)
    @Column(name = "ACCOUNT_GB")
    private String accountGb;
    @Size(max = 50)
    @Column(name = "SAP_VENDOR_CODE")
    private String sapVendorCode;
    @Size(max = 3)
    @Column(name = "PAYMENT_TERM")
    private String paymentTerm;
    @Size(max = 1)
    @Column(name = "SENT_TO_MNC")
    private String sentToMnc;

    public Tenterprise() {
    }

    public Tenterprise(String entpCode) {
        this.entpCode = entpCode;
    }

    public Tenterprise(String entpCode, String entpName, String entpGubun, String sIdno, String dishonorYn, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.entpCode = entpCode;
        this.entpName = entpName;
        this.entpGubun = entpGubun;
        this.sIdno = sIdno;
        this.dishonorYn = dishonorYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getEntpCode() {
        return entpCode;
    }

    public void setEntpCode(String entpCode) {
        this.entpCode = entpCode;
    }

    public String getEntpName() {
        return entpName;
    }

    public void setEntpName(String entpName) {
        this.entpName = entpName;
    }

    public String getEntpGubun() {
        return entpGubun;
    }

    public void setEntpGubun(String entpGubun) {
        this.entpGubun = entpGubun;
    }

    public String getSIdno() {
        return sIdno;
    }

    public void setSIdno(String sIdno) {
        this.sIdno = sIdno;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkKind() {
        return workKind;
    }

    public void setWorkKind(String workKind) {
        this.workKind = workKind;
    }

    public String getEntpPost() {
        return entpPost;
    }

    public void setEntpPost(String entpPost) {
        this.entpPost = entpPost;
    }

    public String getEntpPostSeq() {
        return entpPostSeq;
    }

    public void setEntpPostSeq(String entpPostSeq) {
        this.entpPostSeq = entpPostSeq;
    }

    public String getEntpAddr() {
        return entpAddr;
    }

    public void setEntpAddr(String entpAddr) {
        this.entpAddr = entpAddr;
    }

    public String getEntpDdd() {
        return entpDdd;
    }

    public void setEntpDdd(String entpDdd) {
        this.entpDdd = entpDdd;
    }

    public String getEntpTel1() {
        return entpTel1;
    }

    public void setEntpTel1(String entpTel1) {
        this.entpTel1 = entpTel1;
    }

    public String getEntpTel2() {
        return entpTel2;
    }

    public void setEntpTel2(String entpTel2) {
        this.entpTel2 = entpTel2;
    }

    public String getEntpTel3() {
        return entpTel3;
    }

    public void setEntpTel3(String entpTel3) {
        this.entpTel3 = entpTel3;
    }

    public String getEntpFax1() {
        return entpFax1;
    }

    public void setEntpFax1(String entpFax1) {
        this.entpFax1 = entpFax1;
    }

    public String getEntpFax2() {
        return entpFax2;
    }

    public void setEntpFax2(String entpFax2) {
        this.entpFax2 = entpFax2;
    }

    public String getEntpFax3() {
        return entpFax3;
    }

    public void setEntpFax3(String entpFax3) {
        this.entpFax3 = entpFax3;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDishonorYn() {
        return dishonorYn;
    }

    public void setDishonorYn(String dishonorYn) {
        this.dishonorYn = dishonorYn;
    }

    public String getSecretNo() {
        return secretNo;
    }

    public void setSecretNo(String secretNo) {
        this.secretNo = secretNo;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public String getDelyGb() {
        return delyGb;
    }

    public void setDelyGb(String delyGb) {
        this.delyGb = delyGb;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
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

    public String getForeignYn() {
        return foreignYn;
    }

    public void setForeignYn(String foreignYn) {
        this.foreignYn = foreignYn;
    }

    public String getAccountGb() {
        return accountGb;
    }

    public void setAccountGb(String accountGb) {
        this.accountGb = accountGb;
    }

    public String getSapVendorCode() {
        return sapVendorCode;
    }

    public void setSapVendorCode(String sapVendorCode) {
        this.sapVendorCode = sapVendorCode;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getSentToMnc() {
        return sentToMnc;
    }

    public void setSentToMnc(String sentToMnc) {
        this.sentToMnc = sentToMnc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entpCode != null ? entpCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tenterprise)) {
            return false;
        }
        Tenterprise other = (Tenterprise) object;
        if ((this.entpCode == null && other.entpCode != null) || (this.entpCode != null && !this.entpCode.equals(other.entpCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cware.back.action.common.Tenterprise[ entpCode=" + entpCode + " ]";
    }
    
}
