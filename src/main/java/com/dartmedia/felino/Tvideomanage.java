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
@Table(name = "TVIDEOMANAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tvideomanage.findAll", query = "SELECT t FROM Tvideomanage t"),
    @NamedQuery(name = "Tvideomanage.findByVideoCode", query = "SELECT t FROM Tvideomanage t WHERE t.videoCode = :videoCode"),
    @NamedQuery(name = "Tvideomanage.findByVideoName", query = "SELECT t FROM Tvideomanage t WHERE t.videoName = :videoName"),
    @NamedQuery(name = "Tvideomanage.findByVideoTime", query = "SELECT t FROM Tvideomanage t WHERE t.videoTime = :videoTime"),
    @NamedQuery(name = "Tvideomanage.findByUseYn", query = "SELECT t FROM Tvideomanage t WHERE t.useYn = :useYn"),
    @NamedQuery(name = "Tvideomanage.findByVideoKinds", query = "SELECT t FROM Tvideomanage t WHERE t.videoKinds = :videoKinds"),
    @NamedQuery(name = "Tvideomanage.findByVideoNote", query = "SELECT t FROM Tvideomanage t WHERE t.videoNote = :videoNote"),
    @NamedQuery(name = "Tvideomanage.findByReviewYn", query = "SELECT t FROM Tvideomanage t WHERE t.reviewYn = :reviewYn"),
    @NamedQuery(name = "Tvideomanage.findByReviewDate", query = "SELECT t FROM Tvideomanage t WHERE t.reviewDate = :reviewDate"),
    @NamedQuery(name = "Tvideomanage.findByReviewId", query = "SELECT t FROM Tvideomanage t WHERE t.reviewId = :reviewId"),
    @NamedQuery(name = "Tvideomanage.findByReviewNote", query = "SELECT t FROM Tvideomanage t WHERE t.reviewNote = :reviewNote"),
    @NamedQuery(name = "Tvideomanage.findByProduction", query = "SELECT t FROM Tvideomanage t WHERE t.production = :production"),
    @NamedQuery(name = "Tvideomanage.findByInsertId", query = "SELECT t FROM Tvideomanage t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tvideomanage.findByInsertDate", query = "SELECT t FROM Tvideomanage t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tvideomanage.findByModifyId", query = "SELECT t FROM Tvideomanage t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tvideomanage.findByModifyDate", query = "SELECT t FROM Tvideomanage t WHERE t.modifyDate = :modifyDate")})
public class Tvideomanage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "VIDEO_CODE")
    private String videoCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 900)
    @Column(name = "VIDEO_NAME")
    private String videoName;
    @Size(max = 3)
    @Column(name = "VIDEO_TIME")
    private String videoTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USE_YN")
    private String useYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "VIDEO_KINDS")
    private String videoKinds;
    @Size(max = 900)
    @Column(name = "VIDEO_NOTE")
    private String videoNote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REVIEW_YN")
    private String reviewYn;
    @Column(name = "REVIEW_DATE")
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    @Size(max = 10)
    @Column(name = "REVIEW_ID")
    private String reviewId;
    @Size(max = 900)
    @Column(name = "REVIEW_NOTE")
    private String reviewNote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRODUCTION")
    private String production;
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

    public Tvideomanage() {
    }

    public Tvideomanage(String videoCode) {
        this.videoCode = videoCode;
    }

    public Tvideomanage(String videoCode, String videoName, String useYn, String videoKinds, String reviewYn, String production, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.videoCode = videoCode;
        this.videoName = videoName;
        this.useYn = useYn;
        this.videoKinds = videoKinds;
        this.reviewYn = reviewYn;
        this.production = production;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getVideoKinds() {
        return videoKinds;
    }

    public void setVideoKinds(String videoKinds) {
        this.videoKinds = videoKinds;
    }

    public String getVideoNote() {
        return videoNote;
    }

    public void setVideoNote(String videoNote) {
        this.videoNote = videoNote;
    }

    public String getReviewYn() {
        return reviewYn;
    }

    public void setReviewYn(String reviewYn) {
        this.reviewYn = reviewYn;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewNote() {
        return reviewNote;
    }

    public void setReviewNote(String reviewNote) {
        this.reviewNote = reviewNote;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
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
        hash += (videoCode != null ? videoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tvideomanage)) {
            return false;
        }
        Tvideomanage other = (Tvideomanage) object;
        if ((this.videoCode == null && other.videoCode != null) || (this.videoCode != null && !this.videoCode.equals(other.videoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tvideomanage[ videoCode=" + videoCode + " ]";
    }
    
}
