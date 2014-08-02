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
@Table(name = "TCARDCODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcardcode.findAll", query = "SELECT t FROM Tcardcode t"),
    @NamedQuery(name = "Tcardcode.findByCardCode", query = "SELECT t FROM Tcardcode t WHERE t.cardCode = :cardCode"),
    @NamedQuery(name = "Tcardcode.findByCardName", query = "SELECT t FROM Tcardcode t WHERE t.cardName = :cardName"),
    @NamedQuery(name = "Tcardcode.findByBusinessNo", query = "SELECT t FROM Tcardcode t WHERE t.businessNo = :businessNo"),
    @NamedQuery(name = "Tcardcode.findByCardManName", query = "SELECT t FROM Tcardcode t WHERE t.cardManName = :cardManName"),
    @NamedQuery(name = "Tcardcode.findByCardManDdd", query = "SELECT t FROM Tcardcode t WHERE t.cardManDdd = :cardManDdd"),
    @NamedQuery(name = "Tcardcode.findByCardManTel1", query = "SELECT t FROM Tcardcode t WHERE t.cardManTel1 = :cardManTel1"),
    @NamedQuery(name = "Tcardcode.findByCardManTel2", query = "SELECT t FROM Tcardcode t WHERE t.cardManTel2 = :cardManTel2"),
    @NamedQuery(name = "Tcardcode.findByCardManTel3", query = "SELECT t FROM Tcardcode t WHERE t.cardManTel3 = :cardManTel3"),
    @NamedQuery(name = "Tcardcode.findByRestNo", query = "SELECT t FROM Tcardcode t WHERE t.restNo = :restNo"),
    @NamedQuery(name = "Tcardcode.findByRestRate", query = "SELECT t FROM Tcardcode t WHERE t.restRate = :restRate"),
    @NamedQuery(name = "Tcardcode.findByNorestNo", query = "SELECT t FROM Tcardcode t WHERE t.norestNo = :norestNo"),
    @NamedQuery(name = "Tcardcode.findByNorestRate", query = "SELECT t FROM Tcardcode t WHERE t.norestRate = :norestRate"),
    @NamedQuery(name = "Tcardcode.findByMVanComp", query = "SELECT t FROM Tcardcode t WHERE t.mVanComp = :mVanComp"),
    @NamedQuery(name = "Tcardcode.findBySVanComp", query = "SELECT t FROM Tcardcode t WHERE t.sVanComp = :sVanComp"),
    @NamedQuery(name = "Tcardcode.findByInsVanComp", query = "SELECT t FROM Tcardcode t WHERE t.insVanComp = :insVanComp"),
    @NamedQuery(name = "Tcardcode.findByUseYn", query = "SELECT t FROM Tcardcode t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tcardcode.findByRemark", query = "SELECT t FROM Tcardcode t WHERE t.remark = :remark"),
    @NamedQuery(name = "Tcardcode.findByInsertId", query = "SELECT t FROM Tcardcode t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tcardcode.findByInsertDate", query = "SELECT t FROM Tcardcode t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tcardcode.findByCardCommissionRate", query = "SELECT t FROM Tcardcode t WHERE t.cardCommissionRate = :cardCommissionRate")})
public class Tcardcode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CARD_CODE")
    private String cardCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CARD_NAME")
    private String cardName;
    @Size(max = 10)
    @Column(name = "BUSINESS_NO")
    private String businessNo;
    @Size(max = 60)
    @Column(name = "CARD_MAN_NAME")
    private String cardManName;
    @Size(max = 4)
    @Column(name = "CARD_MAN_DDD")
    private String cardManDdd;
    @Size(max = 4)
    @Column(name = "CARD_MAN_TEL1")
    private String cardManTel1;
    @Size(max = 4)
    @Column(name = "CARD_MAN_TEL2")
    private String cardManTel2;
    @Size(max = 6)
    @Column(name = "CARD_MAN_TEL3")
    private String cardManTel3;
    @Size(max = 14)
    @Column(name = "REST_NO")
    private String restNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "REST_RATE")
    private BigDecimal restRate;
    @Size(max = 14)
    @Column(name = "NOREST_NO")
    private String norestNo;
    @Column(name = "NOREST_RATE")
    private BigDecimal norestRate;
    @Size(max = 2)
    @Column(name = "M_VAN_COMP")
    private String mVanComp;
    @Size(max = 2)
    @Column(name = "S_VAN_COMP")
    private String sVanComp;
    @Size(max = 2)
    @Column(name = "INS_VAN_COMP")
    private String insVanComp;
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
    @Column(name = "CARD_COMMISSION_RATE")
    private BigDecimal cardCommissionRate;

    public Tcardcode() {
    }

    public Tcardcode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Tcardcode(String cardCode, String cardName, String useYn, String insertId, Date insertDate) {
        this.cardCode = cardCode;
        this.cardName = cardName;
        this.useYn = useYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getCardManName() {
        return cardManName;
    }

    public void setCardManName(String cardManName) {
        this.cardManName = cardManName;
    }

    public String getCardManDdd() {
        return cardManDdd;
    }

    public void setCardManDdd(String cardManDdd) {
        this.cardManDdd = cardManDdd;
    }

    public String getCardManTel1() {
        return cardManTel1;
    }

    public void setCardManTel1(String cardManTel1) {
        this.cardManTel1 = cardManTel1;
    }

    public String getCardManTel2() {
        return cardManTel2;
    }

    public void setCardManTel2(String cardManTel2) {
        this.cardManTel2 = cardManTel2;
    }

    public String getCardManTel3() {
        return cardManTel3;
    }

    public void setCardManTel3(String cardManTel3) {
        this.cardManTel3 = cardManTel3;
    }

    public String getRestNo() {
        return restNo;
    }

    public void setRestNo(String restNo) {
        this.restNo = restNo;
    }

    public BigDecimal getRestRate() {
        return restRate;
    }

    public void setRestRate(BigDecimal restRate) {
        this.restRate = restRate;
    }

    public String getNorestNo() {
        return norestNo;
    }

    public void setNorestNo(String norestNo) {
        this.norestNo = norestNo;
    }

    public BigDecimal getNorestRate() {
        return norestRate;
    }

    public void setNorestRate(BigDecimal norestRate) {
        this.norestRate = norestRate;
    }

    public String getMVanComp() {
        return mVanComp;
    }

    public void setMVanComp(String mVanComp) {
        this.mVanComp = mVanComp;
    }

    public String getSVanComp() {
        return sVanComp;
    }

    public void setSVanComp(String sVanComp) {
        this.sVanComp = sVanComp;
    }

    public String getInsVanComp() {
        return insVanComp;
    }

    public void setInsVanComp(String insVanComp) {
        this.insVanComp = insVanComp;
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

    public BigDecimal getCardCommissionRate() {
        return cardCommissionRate;
    }

    public void setCardCommissionRate(BigDecimal cardCommissionRate) {
        this.cardCommissionRate = cardCommissionRate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardCode != null ? cardCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcardcode)) {
            return false;
        }
        Tcardcode other = (Tcardcode) object;
        if ((this.cardCode == null && other.cardCode != null) || (this.cardCode != null && !this.cardCode.equals(other.cardCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tcardcode[ cardCode=" + cardCode + " ]";
    }
    
}
