/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ni
 */
@MappedSuperclass
@Table(name = "TBALJUM")
@XmlRootElement
public class Tbaljum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "BALJU_NO", nullable = false, length = 12)
    private String baljuNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ENTP_CODE", nullable = false, length = 6)
    private String entpCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ENTP_MAN_SEQ", nullable = false, length = 3)
    private String entpManSeq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTP_BALJU_SEQ", nullable = false)
    private long entpBaljuSeq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALJU_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date baljuDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALJU_DELY_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date baljuDelyDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "BALJU_GB", nullable = false, length = 2)
    private String baljuGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REBALJU_YN", nullable = false, length = 1)
    private String rebaljuYn;
    @Size(max = 3)
    @Column(name = "REBALJU_CODE", length = 3)
    private String rebaljuCode;
    @Size(max = 900)
    @Column(name = "REBALJU_NOTE", length = 900)
    private String rebaljuNote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALJU_PRT_CNT", nullable = false)
    private short baljuPrtCnt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "LOOK_YN", nullable = false, length = 1)
    private String lookYn;
    @Column(name = "LOOK_DATE")
    @Temporal(TemporalType.DATE)
    private Date lookDate;
    @Size(max = 10)
    @Column(name = "LOOK_ID", length = 10)
    private String lookId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IPGO_PLAN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ipgoPlanDate;
    @Size(max = 3)
    @Column(name = "CH_CODE", length = 3)
    private String chCode;
    @Size(max = 900)
    @Column(name = "CH_NOTE", length = 900)
    private String chNote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CTRL_IN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ctrlInDate;
    @Column(name = "CTRL_DATE")
    @Temporal(TemporalType.DATE)
    private Date ctrlDate;
    @Size(max = 10)
    @Column(name = "CTRL_ID", length = 10)
    private String ctrlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "IN_END_YN", nullable = false, length = 1)
    private String inEndYn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IN_PLAN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inPlanDate;
    @Column(name = "IN_END_DATE")
    @Temporal(TemporalType.DATE)
    private Date inEndDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CANCEL_YN", nullable = false, length = 1)
    private String cancelYn;
    @Size(max = 3)
    @Column(name = "CANCEL_CODE", length = 3)
    private String cancelCode;
    @Size(max = 900)
    @Column(name = "CANCEL_NOTE", length = 900)
    private String cancelNote;
    @Size(max = 10)
    @Column(name = "CANCEL_ID", length = 10)
    private String cancelId;
    @Column(name = "CANCEL_DATE")
    @Temporal(TemporalType.DATE)
    private Date cancelDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "OLD_BALJU_NO", nullable = false, length = 12)
    private String oldBaljuNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALJU_SEQ", nullable = false)
    private short baljuSeq;
    @Size(max = 900)
    @Column(name = "NOTE", length = 900)
    private String note;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "INSERT_ID", nullable = false, length = 10)
    private String insertId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSERT_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date insertDate;
    @Size(max = 1)
    @Column(name = "AUTO_YN", length = 1)
    private String autoYn;
    @Size(max = 1)
    @Column(name = "SENT_TO_MNC", length = 1)
    private String sentToMnc;
    @Size(max = 1)
    @Column(name = "CANCELED_TO_MNC", length = 1)
    private String canceledToMnc;
    @JoinColumn(name = "MD_CODE", referencedColumnName = "MD_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Tmd mdCode;
    @JoinColumn(name = "WH_CODE", referencedColumnName = "WH_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Twarehouse whCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbaljum")
    private Collection<Tbaljudt> tbaljudtCollection;

    public Tbaljum() {
    }

    public Tbaljum(String baljuNo) {
        this.baljuNo = baljuNo;
    }

    public Tbaljum(String baljuNo, String entpCode, String entpManSeq, long entpBaljuSeq, Date baljuDate, Date baljuDelyDate, String baljuGb, String rebaljuYn, short baljuPrtCnt, String lookYn, Date ipgoPlanDate, Date ctrlInDate, String inEndYn, Date inPlanDate, String cancelYn, String oldBaljuNo, short baljuSeq, String insertId, Date insertDate) {
        this.baljuNo = baljuNo;
        this.entpCode = entpCode;
        this.entpManSeq = entpManSeq;
        this.entpBaljuSeq = entpBaljuSeq;
        this.baljuDate = baljuDate;
        this.baljuDelyDate = baljuDelyDate;
        this.baljuGb = baljuGb;
        this.rebaljuYn = rebaljuYn;
        this.baljuPrtCnt = baljuPrtCnt;
        this.lookYn = lookYn;
        this.ipgoPlanDate = ipgoPlanDate;
        this.ctrlInDate = ctrlInDate;
        this.inEndYn = inEndYn;
        this.inPlanDate = inPlanDate;
        this.cancelYn = cancelYn;
        this.oldBaljuNo = oldBaljuNo;
        this.baljuSeq = baljuSeq;
        this.insertId = insertId;
        this.insertDate = insertDate;
    }

    public String getBaljuNo() {
        return baljuNo;
    }

    public void setBaljuNo(String baljuNo) {
        this.baljuNo = baljuNo;
    }

    public String getEntpCode() {
        return entpCode;
    }

    public void setEntpCode(String entpCode) {
        this.entpCode = entpCode;
    }

    public String getEntpManSeq() {
        return entpManSeq;
    }

    public void setEntpManSeq(String entpManSeq) {
        this.entpManSeq = entpManSeq;
    }

    public long getEntpBaljuSeq() {
        return entpBaljuSeq;
    }

    public void setEntpBaljuSeq(long entpBaljuSeq) {
        this.entpBaljuSeq = entpBaljuSeq;
    }

    public Date getBaljuDate() {
        return baljuDate;
    }

    public void setBaljuDate(Date baljuDate) {
        this.baljuDate = baljuDate;
    }

    public Date getBaljuDelyDate() {
        return baljuDelyDate;
    }

    public void setBaljuDelyDate(Date baljuDelyDate) {
        this.baljuDelyDate = baljuDelyDate;
    }

    public String getBaljuGb() {
        return baljuGb;
    }

    public void setBaljuGb(String baljuGb) {
        this.baljuGb = baljuGb;
    }

    public String getRebaljuYn() {
        return rebaljuYn;
    }

    public void setRebaljuYn(String rebaljuYn) {
        this.rebaljuYn = rebaljuYn;
    }

    public String getRebaljuCode() {
        return rebaljuCode;
    }

    public void setRebaljuCode(String rebaljuCode) {
        this.rebaljuCode = rebaljuCode;
    }

    public String getRebaljuNote() {
        return rebaljuNote;
    }

    public void setRebaljuNote(String rebaljuNote) {
        this.rebaljuNote = rebaljuNote;
    }

    public short getBaljuPrtCnt() {
        return baljuPrtCnt;
    }

    public void setBaljuPrtCnt(short baljuPrtCnt) {
        this.baljuPrtCnt = baljuPrtCnt;
    }

    public String getLookYn() {
        return lookYn;
    }

    public void setLookYn(String lookYn) {
        this.lookYn = lookYn;
    }

    public Date getLookDate() {
        return lookDate;
    }

    public void setLookDate(Date lookDate) {
        this.lookDate = lookDate;
    }

    public String getLookId() {
        return lookId;
    }

    public void setLookId(String lookId) {
        this.lookId = lookId;
    }

    public Date getIpgoPlanDate() {
        return ipgoPlanDate;
    }

    public void setIpgoPlanDate(Date ipgoPlanDate) {
        this.ipgoPlanDate = ipgoPlanDate;
    }

    public String getChCode() {
        return chCode;
    }

    public void setChCode(String chCode) {
        this.chCode = chCode;
    }

    public String getChNote() {
        return chNote;
    }

    public void setChNote(String chNote) {
        this.chNote = chNote;
    }

    public Date getCtrlInDate() {
        return ctrlInDate;
    }

    public void setCtrlInDate(Date ctrlInDate) {
        this.ctrlInDate = ctrlInDate;
    }

    public Date getCtrlDate() {
        return ctrlDate;
    }

    public void setCtrlDate(Date ctrlDate) {
        this.ctrlDate = ctrlDate;
    }

    public String getCtrlId() {
        return ctrlId;
    }

    public void setCtrlId(String ctrlId) {
        this.ctrlId = ctrlId;
    }

    public String getInEndYn() {
        return inEndYn;
    }

    public void setInEndYn(String inEndYn) {
        this.inEndYn = inEndYn;
    }

    public Date getInPlanDate() {
        return inPlanDate;
    }

    public void setInPlanDate(Date inPlanDate) {
        this.inPlanDate = inPlanDate;
    }

    public Date getInEndDate() {
        return inEndDate;
    }

    public void setInEndDate(Date inEndDate) {
        this.inEndDate = inEndDate;
    }

    public String getCancelYn() {
        return cancelYn;
    }

    public void setCancelYn(String cancelYn) {
        this.cancelYn = cancelYn;
    }

    public String getCancelCode() {
        return cancelCode;
    }

    public void setCancelCode(String cancelCode) {
        this.cancelCode = cancelCode;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getOldBaljuNo() {
        return oldBaljuNo;
    }

    public void setOldBaljuNo(String oldBaljuNo) {
        this.oldBaljuNo = oldBaljuNo;
    }

    public short getBaljuSeq() {
        return baljuSeq;
    }

    public void setBaljuSeq(short baljuSeq) {
        this.baljuSeq = baljuSeq;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getAutoYn() {
        return autoYn;
    }

    public void setAutoYn(String autoYn) {
        this.autoYn = autoYn;
    }

    public String getSentToMnc() {
        return sentToMnc;
    }

    public void setSentToMnc(String sentToMnc) {
        this.sentToMnc = sentToMnc;
    }

    public String getCanceledToMnc() {
        return canceledToMnc;
    }

    public void setCanceledToMnc(String canceledToMnc) {
        this.canceledToMnc = canceledToMnc;
    }

    public Tmd getMdCode() {
        return mdCode;
    }

    public void setMdCode(Tmd mdCode) {
        this.mdCode = mdCode;
    }

    public Twarehouse getWhCode() {
        return whCode;
    }

    public void setWhCode(Twarehouse whCode) {
        this.whCode = whCode;
    }

    @XmlTransient
    public Collection<Tbaljudt> getTbaljudtCollection() {
        return tbaljudtCollection;
    }

    public void setTbaljudtCollection(Collection<Tbaljudt> tbaljudtCollection) {
        this.tbaljudtCollection = tbaljudtCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (baljuNo != null ? baljuNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbaljum)) {
            return false;
        }
        Tbaljum other = (Tbaljum) object;
        if ((this.baljuNo == null && other.baljuNo != null) || (this.baljuNo != null && !this.baljuNo.equals(other.baljuNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tbaljum[ baljuNo=" + baljuNo + " ]";
    }
    
}
