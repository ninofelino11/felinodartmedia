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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ni
 */
@Embeddable
public class TareashipcostPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "APPLY_DATE")
    @Temporal(TemporalType.DATE)
    private Date applyDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "AREA_GB")
    private String areaGb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SLIP_FLAG")
    private String slipFlag;

    public TareashipcostPK() {
    }

    public TareashipcostPK(Date applyDate, String areaGb, BigDecimal weight, String slipFlag) {
        this.applyDate = applyDate;
        this.areaGb = areaGb;
        this.weight = weight;
        this.slipFlag = slipFlag;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getAreaGb() {
        return areaGb;
    }

    public void setAreaGb(String areaGb) {
        this.areaGb = areaGb;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getSlipFlag() {
        return slipFlag;
    }

    public void setSlipFlag(String slipFlag) {
        this.slipFlag = slipFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applyDate != null ? applyDate.hashCode() : 0);
        hash += (areaGb != null ? areaGb.hashCode() : 0);
        hash += (weight != null ? weight.hashCode() : 0);
        hash += (slipFlag != null ? slipFlag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TareashipcostPK)) {
            return false;
        }
        TareashipcostPK other = (TareashipcostPK) object;
        if ((this.applyDate == null && other.applyDate != null) || (this.applyDate != null && !this.applyDate.equals(other.applyDate))) {
            return false;
        }
        if ((this.areaGb == null && other.areaGb != null) || (this.areaGb != null && !this.areaGb.equals(other.areaGb))) {
            return false;
        }
        if ((this.weight == null && other.weight != null) || (this.weight != null && !this.weight.equals(other.weight))) {
            return false;
        }
        if ((this.slipFlag == null && other.slipFlag != null) || (this.slipFlag != null && !this.slipFlag.equals(other.slipFlag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.TareashipcostPK[ applyDate=" + applyDate + ", areaGb=" + areaGb + ", weight=" + weight + ", slipFlag=" + slipFlag + " ]";
    }
    
}
