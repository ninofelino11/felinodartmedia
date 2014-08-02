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
@Table(name = "TDEPOSITUSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdeposituse.findAll", query = "SELECT t FROM Tdeposituse t"),
    @NamedQuery(name = "Tdeposituse.findByCustNo", query = "SELECT t FROM Tdeposituse t WHERE t.tdepositusePK.custNo = :custNo"),
    @NamedQuery(name = "Tdeposituse.findByUseSeq", query = "SELECT t FROM Tdeposituse t WHERE t.tdepositusePK.useSeq = :useSeq"),
    @NamedQuery(name = "Tdeposituse.findByReceiptNo", query = "SELECT t FROM Tdeposituse t WHERE t.receiptNo = :receiptNo"),
    @NamedQuery(name = "Tdeposituse.findByOrderNo", query = "SELECT t FROM Tdeposituse t WHERE t.orderNo = :orderNo"),
    @NamedQuery(name = "Tdeposituse.findByRepayNo", query = "SELECT t FROM Tdeposituse t WHERE t.repayNo = :repayNo"),
    @NamedQuery(name = "Tdeposituse.findByProcGb", query = "SELECT t FROM Tdeposituse t WHERE t.procGb = :procGb"),
    @NamedQuery(name = "Tdeposituse.findByProcYn", query = "SELECT t FROM Tdeposituse t WHERE t.procYn = :procYn"),
    @NamedQuery(name = "Tdeposituse.findByTransCustNo", query = "SELECT t FROM Tdeposituse t WHERE t.transCustNo = :transCustNo"),
    @NamedQuery(name = "Tdeposituse.findByDepositamtSeq", query = "SELECT t FROM Tdeposituse t WHERE t.depositamtSeq = :depositamtSeq"),
    @NamedQuery(name = "Tdeposituse.findByDepositamtGb", query = "SELECT t FROM Tdeposituse t WHERE t.depositamtGb = :depositamtGb"),
    @NamedQuery(name = "Tdeposituse.findByUseAmt", query = "SELECT t FROM Tdeposituse t WHERE t.useAmt = :useAmt"),
    @NamedQuery(name = "Tdeposituse.findByRemark1V", query = "SELECT t FROM Tdeposituse t WHERE t.remark1V = :remark1V"),
    @NamedQuery(name = "Tdeposituse.findByRemark2V", query = "SELECT t FROM Tdeposituse t WHERE t.remark2V = :remark2V"),
    @NamedQuery(name = "Tdeposituse.findByRemark3N", query = "SELECT t FROM Tdeposituse t WHERE t.remark3N = :remark3N"),
    @NamedQuery(name = "Tdeposituse.findByRemark4N", query = "SELECT t FROM Tdeposituse t WHERE t.remark4N = :remark4N"),
    @NamedQuery(name = "Tdeposituse.findByReamrk5D", query = "SELECT t FROM Tdeposituse t WHERE t.reamrk5D = :reamrk5D"),
    @NamedQuery(name = "Tdeposituse.findByRemark6D", query = "SELECT t FROM Tdeposituse t WHERE t.remark6D = :remark6D"),
    @NamedQuery(name = "Tdeposituse.findByProcId", query = "SELECT t FROM Tdeposituse t WHERE t.procId = :procId"),
    @NamedQuery(name = "Tdeposituse.findByProcDate", query = "SELECT t FROM Tdeposituse t WHERE t.procDate = :procDate")})
public class Tdeposituse implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TdepositusePK tdepositusePK;
    @Size(max = 16)
    @Column(name = "RECEIPT_NO")
    private String receiptNo;
    @Size(max = 14)
    @Column(name = "ORDER_NO")
    private String orderNo;
    @Size(max = 10)
    @Column(name = "REPAY_NO")
    private String repayNo;
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
    @Column(name = "DEPOSITAMT_SEQ")
    private String depositamtSeq;
    @Size(max = 2)
    @Column(name = "DEPOSITAMT_GB")
    private String depositamtGb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "USE_AMT")
    private BigDecimal useAmt;
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
    @Column(name = "REAMRK5_D")
    @Temporal(TemporalType.DATE)
    private Date reamrk5D;
    @Column(name = "REMARK6_D")
    @Temporal(TemporalType.DATE)
    private Date remark6D;
    @Size(max = 10)
    @Column(name = "PROC_ID")
    private String procId;
    @Column(name = "PROC_DATE")
    @Temporal(TemporalType.DATE)
    private Date procDate;

    public Tdeposituse() {
    }

    public Tdeposituse(TdepositusePK tdepositusePK) {
        this.tdepositusePK = tdepositusePK;
    }

    public Tdeposituse(String custNo, String useSeq) {
        this.tdepositusePK = new TdepositusePK(custNo, useSeq);
    }

    public TdepositusePK getTdepositusePK() {
        return tdepositusePK;
    }

    public void setTdepositusePK(TdepositusePK tdepositusePK) {
        this.tdepositusePK = tdepositusePK;
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

    public String getRepayNo() {
        return repayNo;
    }

    public void setRepayNo(String repayNo) {
        this.repayNo = repayNo;
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

    public String getDepositamtSeq() {
        return depositamtSeq;
    }

    public void setDepositamtSeq(String depositamtSeq) {
        this.depositamtSeq = depositamtSeq;
    }

    public String getDepositamtGb() {
        return depositamtGb;
    }

    public void setDepositamtGb(String depositamtGb) {
        this.depositamtGb = depositamtGb;
    }

    public BigDecimal getUseAmt() {
        return useAmt;
    }

    public void setUseAmt(BigDecimal useAmt) {
        this.useAmt = useAmt;
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

    public Date getReamrk5D() {
        return reamrk5D;
    }

    public void setReamrk5D(Date reamrk5D) {
        this.reamrk5D = reamrk5D;
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
        hash += (tdepositusePK != null ? tdepositusePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdeposituse)) {
            return false;
        }
        Tdeposituse other = (Tdeposituse) object;
        if ((this.tdepositusePK == null && other.tdepositusePK != null) || (this.tdepositusePK != null && !this.tdepositusePK.equals(other.tdepositusePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tdeposituse[ tdepositusePK=" + tdepositusePK + " ]";
    }
    
}
