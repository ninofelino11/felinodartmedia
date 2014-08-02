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
@Table(name = "TPROMOTARGET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpromotarget.findAll", query = "SELECT t FROM Tpromotarget t"),
    @NamedQuery(name = "Tpromotarget.findByPromoNo", query = "SELECT t FROM Tpromotarget t WHERE t.tpromotargetPK.promoNo = :promoNo"),
    @NamedQuery(name = "Tpromotarget.findByPromoSeq", query = "SELECT t FROM Tpromotarget t WHERE t.tpromotargetPK.promoSeq = :promoSeq"),
    @NamedQuery(name = "Tpromotarget.findByGoodsCode", query = "SELECT t FROM Tpromotarget t WHERE t.goodsCode = :goodsCode"),
    @NamedQuery(name = "Tpromotarget.findByGiftAmt", query = "SELECT t FROM Tpromotarget t WHERE t.giftAmt = :giftAmt"),
    @NamedQuery(name = "Tpromotarget.findByOwnCost", query = "SELECT t FROM Tpromotarget t WHERE t.ownCost = :ownCost"),
    @NamedQuery(name = "Tpromotarget.findByEntpCost", query = "SELECT t FROM Tpromotarget t WHERE t.entpCost = :entpCost"),
    @NamedQuery(name = "Tpromotarget.findByEntpCode", query = "SELECT t FROM Tpromotarget t WHERE t.entpCode = :entpCode"),
    @NamedQuery(name = "Tpromotarget.findByInsertId", query = "SELECT t FROM Tpromotarget t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tpromotarget.findByInsertDate", query = "SELECT t FROM Tpromotarget t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tpromotarget.findByModifyId", query = "SELECT t FROM Tpromotarget t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tpromotarget.findByModifyDate", query = "SELECT t FROM Tpromotarget t WHERE t.modifyDate = :modifyDate")})
public class Tpromotarget implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TpromotargetPK tpromotargetPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "GOODS_CODE")
    private String goodsCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "GIFT_AMT")
    private BigDecimal giftAmt;
    @Column(name = "OWN_COST")
    private BigDecimal ownCost;
    @Column(name = "ENTP_COST")
    private BigDecimal entpCost;
    @Size(max = 6)
    @Column(name = "ENTP_CODE")
    private String entpCode;
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

    public Tpromotarget() {
    }

    public Tpromotarget(TpromotargetPK tpromotargetPK) {
        this.tpromotargetPK = tpromotargetPK;
    }

    public Tpromotarget(TpromotargetPK tpromotargetPK, String goodsCode, BigDecimal giftAmt, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.tpromotargetPK = tpromotargetPK;
        this.goodsCode = goodsCode;
        this.giftAmt = giftAmt;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public Tpromotarget(String promoNo, String promoSeq) {
        this.tpromotargetPK = new TpromotargetPK(promoNo, promoSeq);
    }

    public TpromotargetPK getTpromotargetPK() {
        return tpromotargetPK;
    }

    public void setTpromotargetPK(TpromotargetPK tpromotargetPK) {
        this.tpromotargetPK = tpromotargetPK;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public BigDecimal getGiftAmt() {
        return giftAmt;
    }

    public void setGiftAmt(BigDecimal giftAmt) {
        this.giftAmt = giftAmt;
    }

    public BigDecimal getOwnCost() {
        return ownCost;
    }

    public void setOwnCost(BigDecimal ownCost) {
        this.ownCost = ownCost;
    }

    public BigDecimal getEntpCost() {
        return entpCost;
    }

    public void setEntpCost(BigDecimal entpCost) {
        this.entpCost = entpCost;
    }

    public String getEntpCode() {
        return entpCode;
    }

    public void setEntpCode(String entpCode) {
        this.entpCode = entpCode;
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
        hash += (tpromotargetPK != null ? tpromotargetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpromotarget)) {
            return false;
        }
        Tpromotarget other = (Tpromotarget) object;
        if ((this.tpromotargetPK == null && other.tpromotargetPK != null) || (this.tpromotargetPK != null && !this.tpromotargetPK.equals(other.tpromotargetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tpromotarget[ tpromotargetPK=" + tpromotargetPK + " ]";
    }
    
}
