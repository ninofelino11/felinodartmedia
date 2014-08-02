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
@Table(name = "TGOODSKINDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tgoodskinds.findAll", query = "SELECT t FROM Tgoodskinds t"),
    @NamedQuery(name = "Tgoodskinds.findByLgroup", query = "SELECT t FROM Tgoodskinds t WHERE t.tgoodskindsPK.lgroup = :lgroup"),
    @NamedQuery(name = "Tgoodskinds.findByMgroup", query = "SELECT t FROM Tgoodskinds t WHERE t.tgoodskindsPK.mgroup = :mgroup"),
    @NamedQuery(name = "Tgoodskinds.findBySgroup", query = "SELECT t FROM Tgoodskinds t WHERE t.tgoodskindsPK.sgroup = :sgroup"),
    @NamedQuery(name = "Tgoodskinds.findByDgroup", query = "SELECT t FROM Tgoodskinds t WHERE t.tgoodskindsPK.dgroup = :dgroup"),
    @NamedQuery(name = "Tgoodskinds.findByLgroupName", query = "SELECT t FROM Tgoodskinds t WHERE t.lgroupName = :lgroupName"),
    @NamedQuery(name = "Tgoodskinds.findByMgroupName", query = "SELECT t FROM Tgoodskinds t WHERE t.mgroupName = :mgroupName"),
    @NamedQuery(name = "Tgoodskinds.findBySgroupName", query = "SELECT t FROM Tgoodskinds t WHERE t.sgroupName = :sgroupName"),
    @NamedQuery(name = "Tgoodskinds.findByDgroupName", query = "SELECT t FROM Tgoodskinds t WHERE t.dgroupName = :dgroupName"),
    @NamedQuery(name = "Tgoodskinds.findByQcLgroup", query = "SELECT t FROM Tgoodskinds t WHERE t.qcLgroup = :qcLgroup"),
    @NamedQuery(name = "Tgoodskinds.findByQcMgroup", query = "SELECT t FROM Tgoodskinds t WHERE t.qcMgroup = :qcMgroup"),
    @NamedQuery(name = "Tgoodskinds.findByLmsdCode", query = "SELECT t FROM Tgoodskinds t WHERE t.lmsdCode = :lmsdCode"),
    @NamedQuery(name = "Tgoodskinds.findByDefVatRate", query = "SELECT t FROM Tgoodskinds t WHERE t.defVatRate = :defVatRate"),
    @NamedQuery(name = "Tgoodskinds.findByInsertId", query = "SELECT t FROM Tgoodskinds t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tgoodskinds.findByInsertDate", query = "SELECT t FROM Tgoodskinds t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tgoodskinds.findByModifyId", query = "SELECT t FROM Tgoodskinds t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tgoodskinds.findByModifyDate", query = "SELECT t FROM Tgoodskinds t WHERE t.modifyDate = :modifyDate")})
public class Tgoodskinds implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TgoodskindsPK tgoodskindsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 180)
    @Column(name = "LGROUP_NAME")
    private String lgroupName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 180)
    @Column(name = "MGROUP_NAME")
    private String mgroupName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 180)
    @Column(name = "SGROUP_NAME")
    private String sgroupName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 180)
    @Column(name = "DGROUP_NAME")
    private String dgroupName;
    @Size(max = 2)
    @Column(name = "QC_LGROUP")
    private String qcLgroup;
    @Size(max = 2)
    @Column(name = "QC_MGROUP")
    private String qcMgroup;
    @Size(max = 10)
    @Column(name = "LMSD_CODE")
    private String lmsdCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEF_VAT_RATE")
    private BigDecimal defVatRate;
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

    public Tgoodskinds() {
    }

    public Tgoodskinds(TgoodskindsPK tgoodskindsPK) {
        this.tgoodskindsPK = tgoodskindsPK;
    }

    public Tgoodskinds(TgoodskindsPK tgoodskindsPK, String lgroupName, String mgroupName, String sgroupName, String dgroupName, BigDecimal defVatRate, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.tgoodskindsPK = tgoodskindsPK;
        this.lgroupName = lgroupName;
        this.mgroupName = mgroupName;
        this.sgroupName = sgroupName;
        this.dgroupName = dgroupName;
        this.defVatRate = defVatRate;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public Tgoodskinds(String lgroup, String mgroup, String sgroup, String dgroup) {
        this.tgoodskindsPK = new TgoodskindsPK(lgroup, mgroup, sgroup, dgroup);
    }

    public TgoodskindsPK getTgoodskindsPK() {
        return tgoodskindsPK;
    }

    public void setTgoodskindsPK(TgoodskindsPK tgoodskindsPK) {
        this.tgoodskindsPK = tgoodskindsPK;
    }

    public String getLgroupName() {
        return lgroupName;
    }

    public void setLgroupName(String lgroupName) {
        this.lgroupName = lgroupName;
    }

    public String getMgroupName() {
        return mgroupName;
    }

    public void setMgroupName(String mgroupName) {
        this.mgroupName = mgroupName;
    }

    public String getSgroupName() {
        return sgroupName;
    }

    public void setSgroupName(String sgroupName) {
        this.sgroupName = sgroupName;
    }

    public String getDgroupName() {
        return dgroupName;
    }

    public void setDgroupName(String dgroupName) {
        this.dgroupName = dgroupName;
    }

    public String getQcLgroup() {
        return qcLgroup;
    }

    public void setQcLgroup(String qcLgroup) {
        this.qcLgroup = qcLgroup;
    }

    public String getQcMgroup() {
        return qcMgroup;
    }

    public void setQcMgroup(String qcMgroup) {
        this.qcMgroup = qcMgroup;
    }

    public String getLmsdCode() {
        return lmsdCode;
    }

    public void setLmsdCode(String lmsdCode) {
        this.lmsdCode = lmsdCode;
    }

    public BigDecimal getDefVatRate() {
        return defVatRate;
    }

    public void setDefVatRate(BigDecimal defVatRate) {
        this.defVatRate = defVatRate;
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
        hash += (tgoodskindsPK != null ? tgoodskindsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tgoodskinds)) {
            return false;
        }
        Tgoodskinds other = (Tgoodskinds) object;
        if ((this.tgoodskindsPK == null && other.tgoodskindsPK != null) || (this.tgoodskindsPK != null && !this.tgoodskindsPK.equals(other.tgoodskindsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tgoodskinds[ tgoodskindsPK=" + tgoodskindsPK + " ]";
    }
    
}
