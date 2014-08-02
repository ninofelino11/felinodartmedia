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
@Table(name = "TCUSTSYSTEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcustsystem.findAll", query = "SELECT t FROM Tcustsystem t"),
    @NamedQuery(name = "Tcustsystem.findByCustNo", query = "SELECT t FROM Tcustsystem t WHERE t.custNo = :custNo"),
    @NamedQuery(name = "Tcustsystem.findByCustGb", query = "SELECT t FROM Tcustsystem t WHERE t.custGb = :custGb"),
    @NamedQuery(name = "Tcustsystem.findByMembGb", query = "SELECT t FROM Tcustsystem t WHERE t.membGb = :membGb"),
    @NamedQuery(name = "Tcustsystem.findByCustWarning", query = "SELECT t FROM Tcustsystem t WHERE t.custWarning = :custWarning"),
    @NamedQuery(name = "Tcustsystem.findByCustChar", query = "SELECT t FROM Tcustsystem t WHERE t.custChar = :custChar"),
    @NamedQuery(name = "Tcustsystem.findByUseDeposit", query = "SELECT t FROM Tcustsystem t WHERE t.useDeposit = :useDeposit"),
    @NamedQuery(name = "Tcustsystem.findByUsePbDeposit", query = "SELECT t FROM Tcustsystem t WHERE t.usePbDeposit = :usePbDeposit"),
    @NamedQuery(name = "Tcustsystem.findByUseSaveamt", query = "SELECT t FROM Tcustsystem t WHERE t.useSaveamt = :useSaveamt"),
    @NamedQuery(name = "Tcustsystem.findByGrantSaveamt", query = "SELECT t FROM Tcustsystem t WHERE t.grantSaveamt = :grantSaveamt"),
    @NamedQuery(name = "Tcustsystem.findByReturnSaveamt", query = "SELECT t FROM Tcustsystem t WHERE t.returnSaveamt = :returnSaveamt"),
    @NamedQuery(name = "Tcustsystem.findByUsableSaveamt", query = "SELECT t FROM Tcustsystem t WHERE t.usableSaveamt = :usableSaveamt"),
    @NamedQuery(name = "Tcustsystem.findByTotSaveamt", query = "SELECT t FROM Tcustsystem t WHERE t.totSaveamt = :totSaveamt"),
    @NamedQuery(name = "Tcustsystem.findByDmYn", query = "SELECT t FROM Tcustsystem t WHERE t.dmYn = :dmYn"),
    @NamedQuery(name = "Tcustsystem.findByDmNoGb", query = "SELECT t FROM Tcustsystem t WHERE t.dmNoGb = :dmNoGb"),
    @NamedQuery(name = "Tcustsystem.findByDmNoId", query = "SELECT t FROM Tcustsystem t WHERE t.dmNoId = :dmNoId"),
    @NamedQuery(name = "Tcustsystem.findByDmNoDate", query = "SELECT t FROM Tcustsystem t WHERE t.dmNoDate = :dmNoDate"),
    @NamedQuery(name = "Tcustsystem.findByFirstOrderDate", query = "SELECT t FROM Tcustsystem t WHERE t.firstOrderDate = :firstOrderDate"),
    @NamedQuery(name = "Tcustsystem.findByLastOrderDate", query = "SELECT t FROM Tcustsystem t WHERE t.lastOrderDate = :lastOrderDate"),
    @NamedQuery(name = "Tcustsystem.findByFirstMsaleGb", query = "SELECT t FROM Tcustsystem t WHERE t.firstMsaleGb = :firstMsaleGb"),
    @NamedQuery(name = "Tcustsystem.findByTotOrderCnt", query = "SELECT t FROM Tcustsystem t WHERE t.totOrderCnt = :totOrderCnt"),
    @NamedQuery(name = "Tcustsystem.findByTotOrderAmt", query = "SELECT t FROM Tcustsystem t WHERE t.totOrderAmt = :totOrderAmt"),
    @NamedQuery(name = "Tcustsystem.findByTotCancelCnt", query = "SELECT t FROM Tcustsystem t WHERE t.totCancelCnt = :totCancelCnt"),
    @NamedQuery(name = "Tcustsystem.findByTotCancelAmt", query = "SELECT t FROM Tcustsystem t WHERE t.totCancelAmt = :totCancelAmt"),
    @NamedQuery(name = "Tcustsystem.findByTotReturnCnt", query = "SELECT t FROM Tcustsystem t WHERE t.totReturnCnt = :totReturnCnt"),
    @NamedQuery(name = "Tcustsystem.findByTotReturnAmt", query = "SELECT t FROM Tcustsystem t WHERE t.totReturnAmt = :totReturnAmt"),
    @NamedQuery(name = "Tcustsystem.findByMembTotAmt", query = "SELECT t FROM Tcustsystem t WHERE t.membTotAmt = :membTotAmt"),
    @NamedQuery(name = "Tcustsystem.findByBirthdayMmdd", query = "SELECT t FROM Tcustsystem t WHERE t.birthdayMmdd = :birthdayMmdd"),
    @NamedQuery(name = "Tcustsystem.findByTotShipCost", query = "SELECT t FROM Tcustsystem t WHERE t.totShipCost = :totShipCost"),
    @NamedQuery(name = "Tcustsystem.findByTotCanShipCost", query = "SELECT t FROM Tcustsystem t WHERE t.totCanShipCost = :totCanShipCost"),
    @NamedQuery(name = "Tcustsystem.findByModifyId", query = "SELECT t FROM Tcustsystem t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tcustsystem.findByModifyDate", query = "SELECT t FROM Tcustsystem t WHERE t.modifyDate = :modifyDate")})
public class Tcustsystem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CUST_NO")
    private String custNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CUST_GB")
    private String custGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MEMB_GB")
    private String membGb;
    @Size(max = 4)
    @Column(name = "CUST_WARNING")
    private String custWarning;
    @Size(max = 2)
    @Column(name = "CUST_CHAR")
    private String custChar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "USE_DEPOSIT")
    private BigDecimal useDeposit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USE_PB_DEPOSIT")
    private BigDecimal usePbDeposit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USE_SAVEAMT")
    private BigDecimal useSaveamt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRANT_SAVEAMT")
    private BigDecimal grantSaveamt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETURN_SAVEAMT")
    private BigDecimal returnSaveamt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USABLE_SAVEAMT")
    private BigDecimal usableSaveamt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOT_SAVEAMT")
    private BigDecimal totSaveamt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DM_YN")
    private String dmYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DM_NO_GB")
    private String dmNoGb;
    @Size(max = 10)
    @Column(name = "DM_NO_ID")
    private String dmNoId;
    @Column(name = "DM_NO_DATE")
    @Temporal(TemporalType.DATE)
    private Date dmNoDate;
    @Column(name = "FIRST_ORDER_DATE")
    @Temporal(TemporalType.DATE)
    private Date firstOrderDate;
    @Column(name = "LAST_ORDER_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastOrderDate;
    @Size(max = 2)
    @Column(name = "FIRST_MSALE_GB")
    private String firstMsaleGb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOT_ORDER_CNT")
    private int totOrderCnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOT_ORDER_AMT")
    private BigDecimal totOrderAmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOT_CANCEL_CNT")
    private int totCancelCnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOT_CANCEL_AMT")
    private BigDecimal totCancelAmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOT_RETURN_CNT")
    private int totReturnCnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOT_RETURN_AMT")
    private BigDecimal totReturnAmt;
    @Column(name = "MEMB_TOT_AMT")
    private BigDecimal membTotAmt;
    @Size(max = 4)
    @Column(name = "BIRTHDAY_MMDD")
    private String birthdayMmdd;
    @Column(name = "TOT_SHIP_COST")
    private BigDecimal totShipCost;
    @Column(name = "TOT_CAN_SHIP_COST")
    private BigDecimal totCanShipCost;
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

    public Tcustsystem() {
    }

    public Tcustsystem(String custNo) {
        this.custNo = custNo;
    }

    public Tcustsystem(String custNo, String custGb, String membGb, BigDecimal useDeposit, BigDecimal usePbDeposit, BigDecimal useSaveamt, BigDecimal grantSaveamt, BigDecimal returnSaveamt, BigDecimal usableSaveamt, BigDecimal totSaveamt, String dmYn, String dmNoGb, int totOrderCnt, BigDecimal totOrderAmt, int totCancelCnt, BigDecimal totCancelAmt, int totReturnCnt, BigDecimal totReturnAmt, String modifyId, Date modifyDate) {
        this.custNo = custNo;
        this.custGb = custGb;
        this.membGb = membGb;
        this.useDeposit = useDeposit;
        this.usePbDeposit = usePbDeposit;
        this.useSaveamt = useSaveamt;
        this.grantSaveamt = grantSaveamt;
        this.returnSaveamt = returnSaveamt;
        this.usableSaveamt = usableSaveamt;
        this.totSaveamt = totSaveamt;
        this.dmYn = dmYn;
        this.dmNoGb = dmNoGb;
        this.totOrderCnt = totOrderCnt;
        this.totOrderAmt = totOrderAmt;
        this.totCancelCnt = totCancelCnt;
        this.totCancelAmt = totCancelAmt;
        this.totReturnCnt = totReturnCnt;
        this.totReturnAmt = totReturnAmt;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustGb() {
        return custGb;
    }

    public void setCustGb(String custGb) {
        this.custGb = custGb;
    }

    public String getMembGb() {
        return membGb;
    }

    public void setMembGb(String membGb) {
        this.membGb = membGb;
    }

    public String getCustWarning() {
        return custWarning;
    }

    public void setCustWarning(String custWarning) {
        this.custWarning = custWarning;
    }

    public String getCustChar() {
        return custChar;
    }

    public void setCustChar(String custChar) {
        this.custChar = custChar;
    }

    public BigDecimal getUseDeposit() {
        return useDeposit;
    }

    public void setUseDeposit(BigDecimal useDeposit) {
        this.useDeposit = useDeposit;
    }

    public BigDecimal getUsePbDeposit() {
        return usePbDeposit;
    }

    public void setUsePbDeposit(BigDecimal usePbDeposit) {
        this.usePbDeposit = usePbDeposit;
    }

    public BigDecimal getUseSaveamt() {
        return useSaveamt;
    }

    public void setUseSaveamt(BigDecimal useSaveamt) {
        this.useSaveamt = useSaveamt;
    }

    public BigDecimal getGrantSaveamt() {
        return grantSaveamt;
    }

    public void setGrantSaveamt(BigDecimal grantSaveamt) {
        this.grantSaveamt = grantSaveamt;
    }

    public BigDecimal getReturnSaveamt() {
        return returnSaveamt;
    }

    public void setReturnSaveamt(BigDecimal returnSaveamt) {
        this.returnSaveamt = returnSaveamt;
    }

    public BigDecimal getUsableSaveamt() {
        return usableSaveamt;
    }

    public void setUsableSaveamt(BigDecimal usableSaveamt) {
        this.usableSaveamt = usableSaveamt;
    }

    public BigDecimal getTotSaveamt() {
        return totSaveamt;
    }

    public void setTotSaveamt(BigDecimal totSaveamt) {
        this.totSaveamt = totSaveamt;
    }

    public String getDmYn() {
        return dmYn;
    }

    public void setDmYn(String dmYn) {
        this.dmYn = dmYn;
    }

    public String getDmNoGb() {
        return dmNoGb;
    }

    public void setDmNoGb(String dmNoGb) {
        this.dmNoGb = dmNoGb;
    }

    public String getDmNoId() {
        return dmNoId;
    }

    public void setDmNoId(String dmNoId) {
        this.dmNoId = dmNoId;
    }

    public Date getDmNoDate() {
        return dmNoDate;
    }

    public void setDmNoDate(Date dmNoDate) {
        this.dmNoDate = dmNoDate;
    }

    public Date getFirstOrderDate() {
        return firstOrderDate;
    }

    public void setFirstOrderDate(Date firstOrderDate) {
        this.firstOrderDate = firstOrderDate;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public String getFirstMsaleGb() {
        return firstMsaleGb;
    }

    public void setFirstMsaleGb(String firstMsaleGb) {
        this.firstMsaleGb = firstMsaleGb;
    }

    public int getTotOrderCnt() {
        return totOrderCnt;
    }

    public void setTotOrderCnt(int totOrderCnt) {
        this.totOrderCnt = totOrderCnt;
    }

    public BigDecimal getTotOrderAmt() {
        return totOrderAmt;
    }

    public void setTotOrderAmt(BigDecimal totOrderAmt) {
        this.totOrderAmt = totOrderAmt;
    }

    public int getTotCancelCnt() {
        return totCancelCnt;
    }

    public void setTotCancelCnt(int totCancelCnt) {
        this.totCancelCnt = totCancelCnt;
    }

    public BigDecimal getTotCancelAmt() {
        return totCancelAmt;
    }

    public void setTotCancelAmt(BigDecimal totCancelAmt) {
        this.totCancelAmt = totCancelAmt;
    }

    public int getTotReturnCnt() {
        return totReturnCnt;
    }

    public void setTotReturnCnt(int totReturnCnt) {
        this.totReturnCnt = totReturnCnt;
    }

    public BigDecimal getTotReturnAmt() {
        return totReturnAmt;
    }

    public void setTotReturnAmt(BigDecimal totReturnAmt) {
        this.totReturnAmt = totReturnAmt;
    }

    public BigDecimal getMembTotAmt() {
        return membTotAmt;
    }

    public void setMembTotAmt(BigDecimal membTotAmt) {
        this.membTotAmt = membTotAmt;
    }

    public String getBirthdayMmdd() {
        return birthdayMmdd;
    }

    public void setBirthdayMmdd(String birthdayMmdd) {
        this.birthdayMmdd = birthdayMmdd;
    }

    public BigDecimal getTotShipCost() {
        return totShipCost;
    }

    public void setTotShipCost(BigDecimal totShipCost) {
        this.totShipCost = totShipCost;
    }

    public BigDecimal getTotCanShipCost() {
        return totCanShipCost;
    }

    public void setTotCanShipCost(BigDecimal totCanShipCost) {
        this.totCanShipCost = totCanShipCost;
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
        hash += (custNo != null ? custNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcustsystem)) {
            return false;
        }
        Tcustsystem other = (Tcustsystem) object;
        if ((this.custNo == null && other.custNo != null) || (this.custNo != null && !this.custNo.equals(other.custNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tcustsystem[ custNo=" + custNo + " ]";
    }
    
}
