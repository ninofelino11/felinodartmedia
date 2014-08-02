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
@Table(name = "TWAREHOUSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Twarehouse.findAll", query = "SELECT t FROM Twarehouse t"),
    @NamedQuery(name = "Twarehouse.findByWhCode", query = "SELECT t FROM Twarehouse t WHERE t.whCode = :whCode"),
    @NamedQuery(name = "Twarehouse.findByWhName", query = "SELECT t FROM Twarehouse t WHERE t.whName = :whName"),
    @NamedQuery(name = "Twarehouse.findByWhRealYn", query = "SELECT t FROM Twarehouse t WHERE t.whRealYn = :whRealYn"),
    @NamedQuery(name = "Twarehouse.findByStockCheckYn", query = "SELECT t FROM Twarehouse t WHERE t.stockCheckYn = :stockCheckYn"),
    @NamedQuery(name = "Twarehouse.findByWhKindFlag", query = "SELECT t FROM Twarehouse t WHERE t.whKindFlag = :whKindFlag"),
    @NamedQuery(name = "Twarehouse.findByWhPost", query = "SELECT t FROM Twarehouse t WHERE t.whPost = :whPost"),
    @NamedQuery(name = "Twarehouse.findByWhPostSeq", query = "SELECT t FROM Twarehouse t WHERE t.whPostSeq = :whPostSeq"),
    @NamedQuery(name = "Twarehouse.findByWhAddr", query = "SELECT t FROM Twarehouse t WHERE t.whAddr = :whAddr"),
    @NamedQuery(name = "Twarehouse.findByWhDdd", query = "SELECT t FROM Twarehouse t WHERE t.whDdd = :whDdd"),
    @NamedQuery(name = "Twarehouse.findByWhTel1", query = "SELECT t FROM Twarehouse t WHERE t.whTel1 = :whTel1"),
    @NamedQuery(name = "Twarehouse.findByWhTel2", query = "SELECT t FROM Twarehouse t WHERE t.whTel2 = :whTel2"),
    @NamedQuery(name = "Twarehouse.findByWhTel3", query = "SELECT t FROM Twarehouse t WHERE t.whTel3 = :whTel3"),
    @NamedQuery(name = "Twarehouse.findByWhFaxDdd", query = "SELECT t FROM Twarehouse t WHERE t.whFaxDdd = :whFaxDdd"),
    @NamedQuery(name = "Twarehouse.findByWhFax1", query = "SELECT t FROM Twarehouse t WHERE t.whFax1 = :whFax1"),
    @NamedQuery(name = "Twarehouse.findByWhFax2", query = "SELECT t FROM Twarehouse t WHERE t.whFax2 = :whFax2"),
    @NamedQuery(name = "Twarehouse.findByWhScale", query = "SELECT t FROM Twarehouse t WHERE t.whScale = :whScale"),
    @NamedQuery(name = "Twarehouse.findByWhPerson", query = "SELECT t FROM Twarehouse t WHERE t.whPerson = :whPerson"),
    @NamedQuery(name = "Twarehouse.findByWhNote", query = "SELECT t FROM Twarehouse t WHERE t.whNote = :whNote"),
    @NamedQuery(name = "Twarehouse.findByInsertId", query = "SELECT t FROM Twarehouse t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Twarehouse.findByInsertDate", query = "SELECT t FROM Twarehouse t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Twarehouse.findByModifyId", query = "SELECT t FROM Twarehouse t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Twarehouse.findByModifyDate", query = "SELECT t FROM Twarehouse t WHERE t.modifyDate = :modifyDate"),
    @NamedQuery(name = "Twarehouse.findByDistributeRate", query = "SELECT t FROM Twarehouse t WHERE t.distributeRate = :distributeRate"),
    @NamedQuery(name = "Twarehouse.findByMoveDay", query = "SELECT t FROM Twarehouse t WHERE t.moveDay = :moveDay"),
    @NamedQuery(name = "Twarehouse.findByPantosWcode", query = "SELECT t FROM Twarehouse t WHERE t.pantosWcode = :pantosWcode"),
    @NamedQuery(name = "Twarehouse.findByWhType", query = "SELECT t FROM Twarehouse t WHERE t.whType = :whType"),
    @NamedQuery(name = "Twarehouse.findBySentToMnc", query = "SELECT t FROM Twarehouse t WHERE t.sentToMnc = :sentToMnc")})
public class Twarehouse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "WH_CODE")
    private String whCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "WH_NAME")
    private String whName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "WH_REAL_YN")
    private String whRealYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STOCK_CHECK_YN")
    private String stockCheckYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "WH_KIND_FLAG")
    private String whKindFlag;
    @Size(max = 8)
    @Column(name = "WH_POST")
    private String whPost;
    @Size(max = 3)
    @Column(name = "WH_POST_SEQ")
    private String whPostSeq;
    @Size(max = 900)
    @Column(name = "WH_ADDR")
    private String whAddr;
    @Size(max = 12)
    @Column(name = "WH_DDD")
    private String whDdd;
    @Size(max = 4)
    @Column(name = "WH_TEL1")
    private String whTel1;
    @Size(max = 4)
    @Column(name = "WH_TEL2")
    private String whTel2;
    @Size(max = 6)
    @Column(name = "WH_TEL3")
    private String whTel3;
    @Size(max = 4)
    @Column(name = "WH_FAX_DDD")
    private String whFaxDdd;
    @Size(max = 4)
    @Column(name = "WH_FAX1")
    private String whFax1;
    @Size(max = 4)
    @Column(name = "WH_FAX2")
    private String whFax2;
    @Size(max = 2)
    @Column(name = "WH_SCALE")
    private String whScale;
    @Column(name = "WH_PERSON")
    private Short whPerson;
    @Size(max = 600)
    @Column(name = "WH_NOTE")
    private String whNote;
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
    @Column(name = "DISTRIBUTE_RATE")
    private Short distributeRate;
    @Column(name = "MOVE_DAY")
    private Short moveDay;
    @Size(max = 5)
    @Column(name = "PANTOS_WCODE")
    private String pantosWcode;
    @Size(max = 1)
    @Column(name = "WH_TYPE")
    private String whType;
    @Size(max = 1)
    @Column(name = "SENT_TO_MNC")
    private String sentToMnc;

    public Twarehouse() {
    }

    public Twarehouse(String whCode) {
        this.whCode = whCode;
    }

    public Twarehouse(String whCode, String whName, String whRealYn, String stockCheckYn, String whKindFlag, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.whCode = whCode;
        this.whName = whName;
        this.whRealYn = whRealYn;
        this.stockCheckYn = stockCheckYn;
        this.whKindFlag = whKindFlag;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getWhRealYn() {
        return whRealYn;
    }

    public void setWhRealYn(String whRealYn) {
        this.whRealYn = whRealYn;
    }

    public String getStockCheckYn() {
        return stockCheckYn;
    }

    public void setStockCheckYn(String stockCheckYn) {
        this.stockCheckYn = stockCheckYn;
    }

    public String getWhKindFlag() {
        return whKindFlag;
    }

    public void setWhKindFlag(String whKindFlag) {
        this.whKindFlag = whKindFlag;
    }

    public String getWhPost() {
        return whPost;
    }

    public void setWhPost(String whPost) {
        this.whPost = whPost;
    }

    public String getWhPostSeq() {
        return whPostSeq;
    }

    public void setWhPostSeq(String whPostSeq) {
        this.whPostSeq = whPostSeq;
    }

    public String getWhAddr() {
        return whAddr;
    }

    public void setWhAddr(String whAddr) {
        this.whAddr = whAddr;
    }

    public String getWhDdd() {
        return whDdd;
    }

    public void setWhDdd(String whDdd) {
        this.whDdd = whDdd;
    }

    public String getWhTel1() {
        return whTel1;
    }

    public void setWhTel1(String whTel1) {
        this.whTel1 = whTel1;
    }

    public String getWhTel2() {
        return whTel2;
    }

    public void setWhTel2(String whTel2) {
        this.whTel2 = whTel2;
    }

    public String getWhTel3() {
        return whTel3;
    }

    public void setWhTel3(String whTel3) {
        this.whTel3 = whTel3;
    }

    public String getWhFaxDdd() {
        return whFaxDdd;
    }

    public void setWhFaxDdd(String whFaxDdd) {
        this.whFaxDdd = whFaxDdd;
    }

    public String getWhFax1() {
        return whFax1;
    }

    public void setWhFax1(String whFax1) {
        this.whFax1 = whFax1;
    }

    public String getWhFax2() {
        return whFax2;
    }

    public void setWhFax2(String whFax2) {
        this.whFax2 = whFax2;
    }

    public String getWhScale() {
        return whScale;
    }

    public void setWhScale(String whScale) {
        this.whScale = whScale;
    }

    public Short getWhPerson() {
        return whPerson;
    }

    public void setWhPerson(Short whPerson) {
        this.whPerson = whPerson;
    }

    public String getWhNote() {
        return whNote;
    }

    public void setWhNote(String whNote) {
        this.whNote = whNote;
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

    public Short getDistributeRate() {
        return distributeRate;
    }

    public void setDistributeRate(Short distributeRate) {
        this.distributeRate = distributeRate;
    }

    public Short getMoveDay() {
        return moveDay;
    }

    public void setMoveDay(Short moveDay) {
        this.moveDay = moveDay;
    }

    public String getPantosWcode() {
        return pantosWcode;
    }

    public void setPantosWcode(String pantosWcode) {
        this.pantosWcode = pantosWcode;
    }

    public String getWhType() {
        return whType;
    }

    public void setWhType(String whType) {
        this.whType = whType;
    }

    public String getSentToMnc() {
        return sentToMnc;
    }

    public void setSentToMnc(String sentToMnc) {
        this.sentToMnc = sentToMnc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (whCode != null ? whCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Twarehouse)) {
            return false;
        }
        Twarehouse other = (Twarehouse) object;
        if ((this.whCode == null && other.whCode != null) || (this.whCode != null && !this.whCode.equals(other.whCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Twarehouse[ whCode=" + whCode + " ]";
    }
    
}
