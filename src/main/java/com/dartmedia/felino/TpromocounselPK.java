/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ni
 */
@Embeddable
public class TpromocounselPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "PROMO_NO")
    private String promoNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "ORDER_NO")
    private String orderNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ORDER_G_SEQ")
    private String orderGSeq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GOODS_SELECT_NO")
    private String goodsSelectNo;

    public TpromocounselPK() {
    }

    public TpromocounselPK(String promoNo, String orderNo, String orderGSeq, String goodsSelectNo) {
        this.promoNo = promoNo;
        this.orderNo = orderNo;
        this.orderGSeq = orderGSeq;
        this.goodsSelectNo = goodsSelectNo;
    }

    public String getPromoNo() {
        return promoNo;
    }

    public void setPromoNo(String promoNo) {
        this.promoNo = promoNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderGSeq() {
        return orderGSeq;
    }

    public void setOrderGSeq(String orderGSeq) {
        this.orderGSeq = orderGSeq;
    }

    public String getGoodsSelectNo() {
        return goodsSelectNo;
    }

    public void setGoodsSelectNo(String goodsSelectNo) {
        this.goodsSelectNo = goodsSelectNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promoNo != null ? promoNo.hashCode() : 0);
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        hash += (orderGSeq != null ? orderGSeq.hashCode() : 0);
        hash += (goodsSelectNo != null ? goodsSelectNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TpromocounselPK)) {
            return false;
        }
        TpromocounselPK other = (TpromocounselPK) object;
        if ((this.promoNo == null && other.promoNo != null) || (this.promoNo != null && !this.promoNo.equals(other.promoNo))) {
            return false;
        }
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        if ((this.orderGSeq == null && other.orderGSeq != null) || (this.orderGSeq != null && !this.orderGSeq.equals(other.orderGSeq))) {
            return false;
        }
        if ((this.goodsSelectNo == null && other.goodsSelectNo != null) || (this.goodsSelectNo != null && !this.goodsSelectNo.equals(other.goodsSelectNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TpromocounselPK[ promoNo=" + promoNo + ", orderNo=" + orderNo + ", orderGSeq=" + orderGSeq + ", goodsSelectNo=" + goodsSelectNo + " ]";
    }
    
}
