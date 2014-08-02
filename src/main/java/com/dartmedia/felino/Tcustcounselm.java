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
@Table(name = "TCUSTCOUNSELM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcustcounselm.findAll", query = "SELECT t FROM Tcustcounselm t"),
    @NamedQuery(name = "Tcustcounselm.findByCounselSeq", query = "SELECT t FROM Tcustcounselm t WHERE t.counselSeq = :counselSeq"),
    @NamedQuery(name = "Tcustcounselm.findByCustNo", query = "SELECT t FROM Tcustcounselm t WHERE t.custNo = :custNo"),
    @NamedQuery(name = "Tcustcounselm.findByDoFlag", query = "SELECT t FROM Tcustcounselm t WHERE t.doFlag = :doFlag"),
    @NamedQuery(name = "Tcustcounselm.findByRefNo1", query = "SELECT t FROM Tcustcounselm t WHERE t.refNo1 = :refNo1"),
    @NamedQuery(name = "Tcustcounselm.findByRefNo2", query = "SELECT t FROM Tcustcounselm t WHERE t.refNo2 = :refNo2"),
    @NamedQuery(name = "Tcustcounselm.findByRefNo3", query = "SELECT t FROM Tcustcounselm t WHERE t.refNo3 = :refNo3"),
    @NamedQuery(name = "Tcustcounselm.findByRefNo4", query = "SELECT t FROM Tcustcounselm t WHERE t.refNo4 = :refNo4"),
    @NamedQuery(name = "Tcustcounselm.findByOutLgroupCode", query = "SELECT t FROM Tcustcounselm t WHERE t.outLgroupCode = :outLgroupCode"),
    @NamedQuery(name = "Tcustcounselm.findByOutMgroupCode", query = "SELECT t FROM Tcustcounselm t WHERE t.outMgroupCode = :outMgroupCode"),
    @NamedQuery(name = "Tcustcounselm.findByGoodsCode", query = "SELECT t FROM Tcustcounselm t WHERE t.goodsCode = :goodsCode"),
    @NamedQuery(name = "Tcustcounselm.findByGoodsdtCode", query = "SELECT t FROM Tcustcounselm t WHERE t.goodsdtCode = :goodsdtCode"),
    @NamedQuery(name = "Tcustcounselm.findByClaimNo", query = "SELECT t FROM Tcustcounselm t WHERE t.claimNo = :claimNo"),
    @NamedQuery(name = "Tcustcounselm.findByTel", query = "SELECT t FROM Tcustcounselm t WHERE t.tel = :tel"),
    @NamedQuery(name = "Tcustcounselm.findByDdd", query = "SELECT t FROM Tcustcounselm t WHERE t.ddd = :ddd"),
    @NamedQuery(name = "Tcustcounselm.findByTel1", query = "SELECT t FROM Tcustcounselm t WHERE t.tel1 = :tel1"),
    @NamedQuery(name = "Tcustcounselm.findByTel2", query = "SELECT t FROM Tcustcounselm t WHERE t.tel2 = :tel2"),
    @NamedQuery(name = "Tcustcounselm.findByTel3", query = "SELECT t FROM Tcustcounselm t WHERE t.tel3 = :tel3"),
    @NamedQuery(name = "Tcustcounselm.findByWildYn", query = "SELECT t FROM Tcustcounselm t WHERE t.wildYn = :wildYn"),
    @NamedQuery(name = "Tcustcounselm.findByQuickYn", query = "SELECT t FROM Tcustcounselm t WHERE t.quickYn = :quickYn"),
    @NamedQuery(name = "Tcustcounselm.findByHcReqDate", query = "SELECT t FROM Tcustcounselm t WHERE t.hcReqDate = :hcReqDate"),
    @NamedQuery(name = "Tcustcounselm.findByAutofaxYn", query = "SELECT t FROM Tcustcounselm t WHERE t.autofaxYn = :autofaxYn"),
    @NamedQuery(name = "Tcustcounselm.findByEmailYn", query = "SELECT t FROM Tcustcounselm t WHERE t.emailYn = :emailYn"),
    @NamedQuery(name = "Tcustcounselm.findByFromEmailAddr", query = "SELECT t FROM Tcustcounselm t WHERE t.fromEmailAddr = :fromEmailAddr"),
    @NamedQuery(name = "Tcustcounselm.findByIpAddr", query = "SELECT t FROM Tcustcounselm t WHERE t.ipAddr = :ipAddr"),
    @NamedQuery(name = "Tcustcounselm.findBySearchCnt", query = "SELECT t FROM Tcustcounselm t WHERE t.searchCnt = :searchCnt"),
    @NamedQuery(name = "Tcustcounselm.findByRecoCnt", query = "SELECT t FROM Tcustcounselm t WHERE t.recoCnt = :recoCnt"),
    @NamedQuery(name = "Tcustcounselm.findByDeleteYn", query = "SELECT t FROM Tcustcounselm t WHERE t.deleteYn = :deleteYn"),
    @NamedQuery(name = "Tcustcounselm.findByRemark", query = "SELECT t FROM Tcustcounselm t WHERE t.remark = :remark"),
    @NamedQuery(name = "Tcustcounselm.findByRefId1", query = "SELECT t FROM Tcustcounselm t WHERE t.refId1 = :refId1"),
    @NamedQuery(name = "Tcustcounselm.findByRefId2", query = "SELECT t FROM Tcustcounselm t WHERE t.refId2 = :refId2"),
    @NamedQuery(name = "Tcustcounselm.findByCsSendYn", query = "SELECT t FROM Tcustcounselm t WHERE t.csSendYn = :csSendYn"),
    @NamedQuery(name = "Tcustcounselm.findByQuickEndYn", query = "SELECT t FROM Tcustcounselm t WHERE t.quickEndYn = :quickEndYn"),
    @NamedQuery(name = "Tcustcounselm.findByOldCounselSeq", query = "SELECT t FROM Tcustcounselm t WHERE t.oldCounselSeq = :oldCounselSeq"),
    @NamedQuery(name = "Tcustcounselm.findByOldOutLgroupCode", query = "SELECT t FROM Tcustcounselm t WHERE t.oldOutLgroupCode = :oldOutLgroupCode"),
    @NamedQuery(name = "Tcustcounselm.findByOldOutMgroupCode", query = "SELECT t FROM Tcustcounselm t WHERE t.oldOutMgroupCode = :oldOutMgroupCode"),
    @NamedQuery(name = "Tcustcounselm.findByProcId", query = "SELECT t FROM Tcustcounselm t WHERE t.procId = :procId"),
    @NamedQuery(name = "Tcustcounselm.findByProcDate", query = "SELECT t FROM Tcustcounselm t WHERE t.procDate = :procDate"),
    @NamedQuery(name = "Tcustcounselm.findBySendEntpCode", query = "SELECT t FROM Tcustcounselm t WHERE t.sendEntpCode = :sendEntpCode"),
    @NamedQuery(name = "Tcustcounselm.findBySendEmpno", query = "SELECT t FROM Tcustcounselm t WHERE t.sendEmpno = :sendEmpno"),
    @NamedQuery(name = "Tcustcounselm.findByInsertId", query = "SELECT t FROM Tcustcounselm t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tcustcounselm.findByInsertDate", query = "SELECT t FROM Tcustcounselm t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tcustcounselm.findByRefGb", query = "SELECT t FROM Tcustcounselm t WHERE t.refGb = :refGb")})
public class Tcustcounselm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "COUNSEL_SEQ")
    private String counselSeq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CUST_NO")
    private String custNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DO_FLAG")
    private String doFlag;
    @Size(max = 14)
    @Column(name = "REF_NO1")
    private String refNo1;
    @Size(max = 10)
    @Column(name = "REF_NO2")
    private String refNo2;
    @Size(max = 10)
    @Column(name = "REF_NO3")
    private String refNo3;
    @Size(max = 10)
    @Column(name = "REF_NO4")
    private String refNo4;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "OUT_LGROUP_CODE")
    private String outLgroupCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "OUT_MGROUP_CODE")
    private String outMgroupCode;
    @Size(max = 10)
    @Column(name = "GOODS_CODE")
    private String goodsCode;
    @Size(max = 3)
    @Column(name = "GOODSDT_CODE")
    private String goodsdtCode;
    @Size(max = 14)
    @Column(name = "CLAIM_NO")
    private String claimNo;
    @Size(max = 15)
    @Column(name = "TEL")
    private String tel;
    @Size(max = 5)
    @Column(name = "DDD")
    private String ddd;
    @Size(max = 5)
    @Column(name = "TEL1")
    private String tel1;
    @Size(max = 5)
    @Column(name = "TEL2")
    private String tel2;
    @Size(max = 50)
    @Column(name = "TEL3")
    private String tel3;
    @Size(max = 1)
    @Column(name = "WILD_YN")
    private String wildYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "QUICK_YN")
    private String quickYn;
    @Column(name = "HC_REQ_DATE")
    @Temporal(TemporalType.DATE)
    private Date hcReqDate;
    @Size(max = 1)
    @Column(name = "AUTOFAX_YN")
    private String autofaxYn;
    @Size(max = 1)
    @Column(name = "EMAIL_YN")
    private String emailYn;
    @Size(max = 100)
    @Column(name = "FROM_EMAIL_ADDR")
    private String fromEmailAddr;
    @Size(max = 20)
    @Column(name = "IP_ADDR")
    private String ipAddr;
    @Column(name = "SEARCH_CNT")
    private Integer searchCnt;
    @Column(name = "RECO_CNT")
    private Integer recoCnt;
    @Size(max = 1)
    @Column(name = "DELETE_YN")
    private String deleteYn;
    @Size(max = 900)
    @Column(name = "REMARK")
    private String remark;
    @Size(max = 10)
    @Column(name = "REF_ID1")
    private String refId1;
    @Size(max = 10)
    @Column(name = "REF_ID2")
    private String refId2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CS_SEND_YN")
    private String csSendYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "QUICK_END_YN")
    private String quickEndYn;
    @Size(max = 14)
    @Column(name = "OLD_COUNSEL_SEQ")
    private String oldCounselSeq;
    @Size(max = 2)
    @Column(name = "OLD_OUT_LGROUP_CODE")
    private String oldOutLgroupCode;
    @Size(max = 3)
    @Column(name = "OLD_OUT_MGROUP_CODE")
    private String oldOutMgroupCode;
    @Size(max = 10)
    @Column(name = "PROC_ID")
    private String procId;
    @Column(name = "PROC_DATE")
    @Temporal(TemporalType.DATE)
    private Date procDate;
    @Size(max = 6)
    @Column(name = "SEND_ENTP_CODE")
    private String sendEntpCode;
    @Size(max = 20)
    @Column(name = "SEND_EMPNO")
    private String sendEmpno;
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
    @Size(max = 255)
    @Column(name = "REF_GB")
    private String refGb;

    public Tcustcounselm() {
    }

    public Tcustcounselm(String counselSeq) {
        this.counselSeq = counselSeq;
    }

    public Tcustcounselm(String counselSeq, String custNo, String doFlag, String outLgroupCode, String outMgroupCode, String quickYn, String csSendYn, String quickEndYn, String insertId, Date insertDate) {
        this.counselSeq = counselSeq;
        this.custNo = custNo;
        this.doFlag = doFlag;
        this.outLgroupCode = outLgroupCode;
        this.outMgroupCode = outMgroupCode;
        this.quickYn = quickYn;
        this.csSendYn = csSendYn;
        this.quickEndYn = quickEndYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
    }

    public String getCounselSeq() {
        return counselSeq;
    }

    public void setCounselSeq(String counselSeq) {
        this.counselSeq = counselSeq;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getDoFlag() {
        return doFlag;
    }

    public void setDoFlag(String doFlag) {
        this.doFlag = doFlag;
    }

    public String getRefNo1() {
        return refNo1;
    }

    public void setRefNo1(String refNo1) {
        this.refNo1 = refNo1;
    }

    public String getRefNo2() {
        return refNo2;
    }

    public void setRefNo2(String refNo2) {
        this.refNo2 = refNo2;
    }

    public String getRefNo3() {
        return refNo3;
    }

    public void setRefNo3(String refNo3) {
        this.refNo3 = refNo3;
    }

    public String getRefNo4() {
        return refNo4;
    }

    public void setRefNo4(String refNo4) {
        this.refNo4 = refNo4;
    }

    public String getOutLgroupCode() {
        return outLgroupCode;
    }

    public void setOutLgroupCode(String outLgroupCode) {
        this.outLgroupCode = outLgroupCode;
    }

    public String getOutMgroupCode() {
        return outMgroupCode;
    }

    public void setOutMgroupCode(String outMgroupCode) {
        this.outMgroupCode = outMgroupCode;
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

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getWildYn() {
        return wildYn;
    }

    public void setWildYn(String wildYn) {
        this.wildYn = wildYn;
    }

    public String getQuickYn() {
        return quickYn;
    }

    public void setQuickYn(String quickYn) {
        this.quickYn = quickYn;
    }

    public Date getHcReqDate() {
        return hcReqDate;
    }

    public void setHcReqDate(Date hcReqDate) {
        this.hcReqDate = hcReqDate;
    }

    public String getAutofaxYn() {
        return autofaxYn;
    }

    public void setAutofaxYn(String autofaxYn) {
        this.autofaxYn = autofaxYn;
    }

    public String getEmailYn() {
        return emailYn;
    }

    public void setEmailYn(String emailYn) {
        this.emailYn = emailYn;
    }

    public String getFromEmailAddr() {
        return fromEmailAddr;
    }

    public void setFromEmailAddr(String fromEmailAddr) {
        this.fromEmailAddr = fromEmailAddr;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getSearchCnt() {
        return searchCnt;
    }

    public void setSearchCnt(Integer searchCnt) {
        this.searchCnt = searchCnt;
    }

    public Integer getRecoCnt() {
        return recoCnt;
    }

    public void setRecoCnt(Integer recoCnt) {
        this.recoCnt = recoCnt;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefId1() {
        return refId1;
    }

    public void setRefId1(String refId1) {
        this.refId1 = refId1;
    }

    public String getRefId2() {
        return refId2;
    }

    public void setRefId2(String refId2) {
        this.refId2 = refId2;
    }

    public String getCsSendYn() {
        return csSendYn;
    }

    public void setCsSendYn(String csSendYn) {
        this.csSendYn = csSendYn;
    }

    public String getQuickEndYn() {
        return quickEndYn;
    }

    public void setQuickEndYn(String quickEndYn) {
        this.quickEndYn = quickEndYn;
    }

    public String getOldCounselSeq() {
        return oldCounselSeq;
    }

    public void setOldCounselSeq(String oldCounselSeq) {
        this.oldCounselSeq = oldCounselSeq;
    }

    public String getOldOutLgroupCode() {
        return oldOutLgroupCode;
    }

    public void setOldOutLgroupCode(String oldOutLgroupCode) {
        this.oldOutLgroupCode = oldOutLgroupCode;
    }

    public String getOldOutMgroupCode() {
        return oldOutMgroupCode;
    }

    public void setOldOutMgroupCode(String oldOutMgroupCode) {
        this.oldOutMgroupCode = oldOutMgroupCode;
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

    public String getSendEntpCode() {
        return sendEntpCode;
    }

    public void setSendEntpCode(String sendEntpCode) {
        this.sendEntpCode = sendEntpCode;
    }

    public String getSendEmpno() {
        return sendEmpno;
    }

    public void setSendEmpno(String sendEmpno) {
        this.sendEmpno = sendEmpno;
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

    public String getRefGb() {
        return refGb;
    }

    public void setRefGb(String refGb) {
        this.refGb = refGb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (counselSeq != null ? counselSeq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcustcounselm)) {
            return false;
        }
        Tcustcounselm other = (Tcustcounselm) object;
        if ((this.counselSeq == null && other.counselSeq != null) || (this.counselSeq != null && !this.counselSeq.equals(other.counselSeq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tcustcounselm[ counselSeq=" + counselSeq + " ]";
    }
    
}
