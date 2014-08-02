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
@Table(name = "TPROMOCOUNSEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpromocounsel.findAll", query = "SELECT t FROM Tpromocounsel t"),
    @NamedQuery(name = "Tpromocounsel.findByPromoNo", query = "SELECT t FROM Tpromocounsel t WHERE t.tpromocounselPK.promoNo = :promoNo"),
    @NamedQuery(name = "Tpromocounsel.findByOrderNo", query = "SELECT t FROM Tpromocounsel t WHERE t.tpromocounselPK.orderNo = :orderNo"),
    @NamedQuery(name = "Tpromocounsel.findByOrderGSeq", query = "SELECT t FROM Tpromocounsel t WHERE t.tpromocounselPK.orderGSeq = :orderGSeq"),
    @NamedQuery(name = "Tpromocounsel.findByGoodsSelectNo", query = "SELECT t FROM Tpromocounsel t WHERE t.tpromocounselPK.goodsSelectNo = :goodsSelectNo"),
    @NamedQuery(name = "Tpromocounsel.findByCounselQty", query = "SELECT t FROM Tpromocounsel t WHERE t.counselQty = :counselQty"),
    @NamedQuery(name = "Tpromocounsel.findByInsertId", query = "SELECT t FROM Tpromocounsel t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tpromocounsel.findByInsertDate", query = "SELECT t FROM Tpromocounsel t WHERE t.insertDate = :insertDate")})
public class Tpromocounsel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TpromocounselPK tpromocounselPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COUNSEL_QTY")
    private int counselQty;
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

    public Tpromocounsel() {
    }

    public Tpromocounsel(TpromocounselPK tpromocounselPK) {
        this.tpromocounselPK = tpromocounselPK;
    }

    public Tpromocounsel(TpromocounselPK tpromocounselPK, int counselQty, String insertId, Date insertDate) {
        this.tpromocounselPK = tpromocounselPK;
        this.counselQty = counselQty;
        this.insertId = insertId;
        this.insertDate = insertDate;
    }

    public Tpromocounsel(String promoNo, String orderNo, String orderGSeq, String goodsSelectNo) {
        this.tpromocounselPK = new TpromocounselPK(promoNo, orderNo, orderGSeq, goodsSelectNo);
    }

    public TpromocounselPK getTpromocounselPK() {
        return tpromocounselPK;
    }

    public void setTpromocounselPK(TpromocounselPK tpromocounselPK) {
        this.tpromocounselPK = tpromocounselPK;
    }

    public int getCounselQty() {
        return counselQty;
    }

    public void setCounselQty(int counselQty) {
        this.counselQty = counselQty;
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
        hash += (tpromocounselPK != null ? tpromocounselPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpromocounsel)) {
            return false;
        }
        Tpromocounsel other = (Tpromocounsel) object;
        if ((this.tpromocounselPK == null && other.tpromocounselPK != null) || (this.tpromocounselPK != null && !this.tpromocounselPK.equals(other.tpromocounselPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tpromocounsel[ tpromocounselPK=" + tpromocounselPK + " ]";
    }
    
}
