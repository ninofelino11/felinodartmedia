/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ni
 */
@Entity
@Table(name = "TAREASHIPCOST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tareashipcost.findAll", query = "SELECT t FROM Tareashipcost t"),
    @NamedQuery(name = "Tareashipcost.findByApplyDate", query = "SELECT t FROM Tareashipcost t WHERE t.tareashipcostPK.applyDate = :applyDate"),
    @NamedQuery(name = "Tareashipcost.findByAreaGb", query = "SELECT t FROM Tareashipcost t WHERE t.tareashipcostPK.areaGb = :areaGb"),
    @NamedQuery(name = "Tareashipcost.findByWeight", query = "SELECT t FROM Tareashipcost t WHERE t.tareashipcostPK.weight = :weight"),
    @NamedQuery(name = "Tareashipcost.findByShipFee", query = "SELECT t FROM Tareashipcost t WHERE t.shipFee = :shipFee"),
    @NamedQuery(name = "Tareashipcost.findBySlipFlag", query = "SELECT t FROM Tareashipcost t WHERE t.tareashipcostPK.slipFlag = :slipFlag"),
    @NamedQuery(name = "Tareashipcost.findByCodYn", query = "SELECT t FROM Tareashipcost t WHERE t.codYn = :codYn"),
    @NamedQuery(name = "Tareashipcost.findByInsertDate", query = "SELECT t FROM Tareashipcost t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tareashipcost.findByInsertId", query = "SELECT t FROM Tareashipcost t WHERE t.insertId = :insertId")})
public class Tareashipcost implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TareashipcostPK tareashipcostPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SHIP_FEE")
    private BigDecimal shipFee;
    @Size(max = 1)
    @Column(name = "COD_YN")
    private String codYn;
    @Column(name = "INSERT_DATE")
    @Temporal(TemporalType.DATE)
    private Date insertDate;
    @Size(max = 10)
    @Column(name = "INSERT_ID")
    private String insertId;

    public Tareashipcost() {
    }

    public Tareashipcost(TareashipcostPK tareashipcostPK) {
        this.tareashipcostPK = tareashipcostPK;
    }

    public Tareashipcost(Date applyDate, String areaGb, BigDecimal weight, String slipFlag) {
        this.tareashipcostPK = new TareashipcostPK(applyDate, areaGb, weight, slipFlag);
    }

    public TareashipcostPK getTareashipcostPK() {
        return tareashipcostPK;
    }

    public void setTareashipcostPK(TareashipcostPK tareashipcostPK) {
        this.tareashipcostPK = tareashipcostPK;
    }

    public BigDecimal getShipFee() {
        return shipFee;
    }

    public void setShipFee(BigDecimal shipFee) {
        this.shipFee = shipFee;
    }

    public String getCodYn() {
        return codYn;
    }

    public void setCodYn(String codYn) {
        this.codYn = codYn;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getInsertId() {
        return insertId;
    }

    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tareashipcostPK != null ? tareashipcostPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareashipcost)) {
            return false;
        }
        Tareashipcost other = (Tareashipcost) object;
        if ((this.tareashipcostPK == null && other.tareashipcostPK != null) || (this.tareashipcostPK != null && !this.tareashipcostPK.equals(other.tareashipcostPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tareashipcost[ tareashipcostPK=" + tareashipcostPK + " ]";
    }
    
}
