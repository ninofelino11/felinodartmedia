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
@Table(name = "TDEPOSIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdeposit.findAll", query = "SELECT t FROM Tdeposit t"),
    @NamedQuery(name = "Tdeposit.findByCustNo", query = "SELECT t FROM Tdeposit t WHERE t.tdepositPK.custNo = :custNo"),
    @NamedQuery(name = "Tdeposit.findByDepositamtSeq", query = "SELECT t FROM Tdeposit t WHERE t.tdepositPK.depositamtSeq = :depositamtSeq"),
    @NamedQuery(name = "Tdeposit.findByReceiptNo", query = "SELECT t FROM Tdeposit t WHERE t.receiptNo = :receiptNo"),
    @NamedQuery(name = "Tdeposit.findByOrderNo", query = "SELECT t FROM Tdeposit t WHERE t.orderNo = :orderNo"),
    @NamedQuery(name = "Tdeposit.findByProcGb", query = "SELECT t FROM Tdeposit t WHERE t.procGb = :procGb"),
    @NamedQuery(name = "Tdeposit.findByProcYn", query = "SELECT t FROM Tdeposit t WHERE t.procYn = :procYn"),
    @NamedQuery(name = "Tdeposit.findByTransCustNo", query = "SELECT t FROM Tdeposit t WHERE t.transCustNo = :transCustNo"),
    @NamedQuery(name = "Tdeposit.findByUseSeq", query = "SELECT t FROM Tdeposit t WHERE t.useSeq = :useSeq"),
    @NamedQuery(name = "Tdeposit.findByDepositamtGb", query = "SELECT t FROM Tdeposit t WHERE t.depositamtGb = :depositamtGb"),
    @NamedQuery(name = "Tdeposit.findByDepositamt", query = "SELECT t FROM Tdeposit t WHERE t.depositamt = :depositamt"),
    @NamedQuery(name = "Tdeposit.findByDepositNote", query = "SELECT t FROM Tdeposit t WHERE t.depositNote = :depositNote"),
    @NamedQuery(name = "Tdeposit.findByRemark1V", query = "SELECT t FROM Tdeposit t WHERE t.remark1V = :remark1V"),
    @NamedQuery(name = "Tdeposit.findByRemark2V", query = "SELECT t FROM Tdeposit t WHERE t.remark2V = :remark2V"),
    @NamedQuery(name = "Tdeposit.findByRemark3N", query = "SELECT t FROM Tdeposit t WHERE t.remark3N = :remark3N"),
    @NamedQuery(name = "Tdeposit.findByRemark4N", query = "SELECT t FROM Tdeposit t WHERE t.remark4N = :remark4N"),
    @NamedQuery(name = "Tdeposit.findByRemark5D", query = "SELECT t FROM Tdeposit t WHERE t.remark5D = :remark5D"),
    @NamedQuery(name = "Tdeposit.findByRemark6D", query = "SELECT t FROM Tdeposit t WHERE t.remark6D = :remark6D"),
    @NamedQuery(name = "Tdeposit.findByProcId", query = "SELECT t FROM Tdeposit t WHERE t.procId = :procId"),
    @NamedQuery(name = "Tdeposit.findByProcDate", query = "SELECT t FROM Tdeposit t WHERE t.procDate = :procDate")})
public class Tdeposit implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TdepositPK tdepositPK;
    @Size(max = 16)
    @Column(name = "RECEIPT_NO")
    private String receiptNo;
    @Size(max = 14)
    @Column(name = "ORDER_NO")
    private String orderNo;
    @Size(max = 2)
    @Column(name = "PROC_GB")
    private String procGb;
    @Size(max = 1)
    @Column(name = "PROC_YN")
    private String procYn;
    @Size(max = 12)
    @Column(name = "TRANS_CUST_NO")
    private String transCustNo;
    @Size(max = 10)
    @Column(name = "USE_SEQ")
    private String useSeq;
    @Size(max = 2)
    @Column(name = "DEPOSITAMT_GB")
    private String depositamtGb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEPOSITAMT")
    private BigDecimal depositamt;
    @Size(max = 900)
    @Column(name = "DEPOSIT_NOTE")
    private String depositNote;
    @Size(max = 10)
    @Column(name = "REMARK1_V")
    private String remark1V;
    @Size(max = 900)
    @Column(name = "REMARK2_V")
    private String remark2V;
    @Column(name = "REMARK3_N")
    private Long remark3N;
    @Column(name = "REMARK4_N")
    private Long remark4N;
    @Column(name = "REMARK5_D")
    @Temporal(TemporalType.DATE)
    private Date remark5D;
    @Column(name = "REMARK6_D")
    @Temporal(TemporalType.DATE)
    private Date remark6D;
    @Size(max = 10)
    @Column(name = "PROC_ID")
    private String procId;
    @Column(name = "PROC_DATE")
    @Temporal(TemporalType.DATE)
    private Date procDate;

    public Tdeposit() {
    }

    public Tdeposit(TdepositPK tdepositPK) {
        this.tdepositPK = tdepositPK;
    }

    public Tdeposit(String custNo, String depositamtSeq) {
        this.tdepositPK = new TdepositPK(custNo, depositamtSeq);
    }

    public TdepositPK getTdepositPK() {
        return tdepositPK;
    }

    public void setTdepositPK(TdepositPK tdepositPK) {
        this.tdepositPK = tdepositPK;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProcGb() {
        return procGb;
    }

    public void setProcGb(String procGb) {
        this.procGb = procGb;
    }

    public String getProcYn() {
        return procYn;
    }

    public void setProcYn(String procYn) {
        this.procYn = procYn;
    }

    public String getTransCustNo() {
        return transCustNo;
    }

    public void setTransCustNo(String transCustNo) {
        this.transCustNo = transCustNo;
    }

    public String getUseSeq() {
        return useSeq;
    }

    public void setUseSeq(String useSeq) {
        this.useSeq = useSeq;
    }

    public String getDepositamtGb() {
        return depositamtGb;
    }

    public void setDepositamtGb(String depositamtGb) {
        this.depositamtGb = depositamtGb;
    }

    public BigDecimal getDepositamt() {
        return depositamt;
    }

    public void setDepositamt(BigDecimal depositamt) {
        this.depositamt = depositamt;
    }

    public String getDepositNote() {
        return depositNote;
    }

    public void setDepositNote(String depositNote) {
        this.depositNote = depositNote;
    }

    public String getRemark1V() {
        return remark1V;
    }

    public void setRemark1V(String remark1V) {
        this.remark1V = remark1V;
    }

    public String getRemark2V() {
        return remark2V;
    }

    public void setRemark2V(String remark2V) {
        this.remark2V = remark2V;
    }

    public Long getRemark3N() {
        return remark3N;
    }

    public void setRemark3N(Long remark3N) {
        this.remark3N = remark3N;
    }

    public Long getRemark4N() {
        return remark4N;
    }

    public void setRemark4N(Long remark4N) {
        this.remark4N = remark4N;
    }

    public Date getRemark5D() {
        return remark5D;
    }

    public void setRemark5D(Date remark5D) {
        this.remark5D = remark5D;
    }

    public Date getRemark6D() {
        return remark6D;
    }

    public void setRemark6D(Date remark6D) {
        this.remark6D = remark6D;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tdepositPK != null ? tdepositPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdeposit)) {
            return false;
        }
        Tdeposit other = (Tdeposit) object;
        if ((this.tdepositPK == null && other.tdepositPK != null) || (this.tdepositPK != null && !this.tdepositPK.equals(other.tdepositPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tdeposit[ tdepositPK=" + tdepositPK + " ]";
    }
    
}
