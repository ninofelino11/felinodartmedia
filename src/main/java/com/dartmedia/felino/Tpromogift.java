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
@Table(name = "TPROMOGIFT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpromogift.findAll", query = "SELECT t FROM Tpromogift t"),
    @NamedQuery(name = "Tpromogift.findByPromoNo", query = "SELECT t FROM Tpromogift t WHERE t.tpromogiftPK.promoNo = :promoNo"),
    @NamedQuery(name = "Tpromogift.findByPromoSeq", query = "SELECT t FROM Tpromogift t WHERE t.tpromogiftPK.promoSeq = :promoSeq"),
    @NamedQuery(name = "Tpromogift.findByGiftGoodsCode", query = "SELECT t FROM Tpromogift t WHERE t.giftGoodsCode = :giftGoodsCode"),
    @NamedQuery(name = "Tpromogift.findByGiftQty", query = "SELECT t FROM Tpromogift t WHERE t.giftQty = :giftQty"),
    @NamedQuery(name = "Tpromogift.findByInsertId", query = "SELECT t FROM Tpromogift t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tpromogift.findByInsertDate", query = "SELECT t FROM Tpromogift t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tpromogift.findByModifyId", query = "SELECT t FROM Tpromogift t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tpromogift.findByModifyDate", query = "SELECT t FROM Tpromogift t WHERE t.modifyDate = :modifyDate")})
public class Tpromogift implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TpromogiftPK tpromogiftPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "GIFT_GOODS_CODE")
    private String giftGoodsCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GIFT_QTY")
    private int giftQty;
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

    public Tpromogift() {
    }

    public Tpromogift(TpromogiftPK tpromogiftPK) {
        this.tpromogiftPK = tpromogiftPK;
    }

    public Tpromogift(TpromogiftPK tpromogiftPK, String giftGoodsCode, int giftQty, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.tpromogiftPK = tpromogiftPK;
        this.giftGoodsCode = giftGoodsCode;
        this.giftQty = giftQty;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public Tpromogift(String promoNo, String promoSeq) {
        this.tpromogiftPK = new TpromogiftPK(promoNo, promoSeq);
    }

    public TpromogiftPK getTpromogiftPK() {
        return tpromogiftPK;
    }

    public void setTpromogiftPK(TpromogiftPK tpromogiftPK) {
        this.tpromogiftPK = tpromogiftPK;
    }

    public String getGiftGoodsCode() {
        return giftGoodsCode;
    }

    public void setGiftGoodsCode(String giftGoodsCode) {
        this.giftGoodsCode = giftGoodsCode;
    }

    public int getGiftQty() {
        return giftQty;
    }

    public void setGiftQty(int giftQty) {
        this.giftQty = giftQty;
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
        hash += (tpromogiftPK != null ? tpromogiftPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpromogift)) {
            return false;
        }
        Tpromogift other = (Tpromogift) object;
        if ((this.tpromogiftPK == null && other.tpromogiftPK != null) || (this.tpromogiftPK != null && !this.tpromogiftPK.equals(other.tpromogiftPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tpromogift[ tpromogiftPK=" + tpromogiftPK + " ]";
    }
    
}
