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
public class TbaljudtPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "BALJU_NO", nullable = false, length = 12)
    private String baljuNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "GOODS_CODE", nullable = false, length = 10)
    private String goodsCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "GOODSDT_CODE", nullable = false, length = 3)
    private String goodsdtCode;

    public TbaljudtPK() {
    }

    public TbaljudtPK(String baljuNo, String goodsCode, String goodsdtCode) {
        this.baljuNo = baljuNo;
        this.goodsCode = goodsCode;
        this.goodsdtCode = goodsdtCode;
    }

    public String getBaljuNo() {
        return baljuNo;
    }

    public void setBaljuNo(String baljuNo) {
        this.baljuNo = baljuNo;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsdtCode() {
        return goodsdtCode;
    }

    public void setGoodsdtCode(String goodsdtCode) {
        this.goodsdtCode = goodsdtCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (baljuNo != null ? baljuNo.hashCode() : 0);
        hash += (goodsCode != null ? goodsCode.hashCode() : 0);
        hash += (goodsdtCode != null ? goodsdtCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbaljudtPK)) {
            return false;
        }
        TbaljudtPK other = (TbaljudtPK) object;
        if ((this.baljuNo == null && other.baljuNo != null) || (this.baljuNo != null && !this.baljuNo.equals(other.baljuNo))) {
            return false;
        }
        if ((this.goodsCode == null && other.goodsCode != null) || (this.goodsCode != null && !this.goodsCode.equals(other.goodsCode))) {
            return false;
        }
        if ((this.goodsdtCode == null && other.goodsdtCode != null) || (this.goodsdtCode != null && !this.goodsdtCode.equals(other.goodsdtCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TbaljudtPK[ baljuNo=" + baljuNo + ", goodsCode=" + goodsCode + ", goodsdtCode=" + goodsdtCode + " ]";
    }
    
}
