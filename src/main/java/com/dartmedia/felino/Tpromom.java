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
@Table(name = "TPROMOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpromom.findAll", query = "SELECT t FROM Tpromom t"),
    @NamedQuery(name = "Tpromom.findByPromoNo", query = "SELECT t FROM Tpromom t WHERE t.promoNo = :promoNo"),
    @NamedQuery(name = "Tpromom.findByPromoName", query = "SELECT t FROM Tpromom t WHERE t.promoName = :promoName"),
    @NamedQuery(name = "Tpromom.findByFirstOrderYn", query = "SELECT t FROM Tpromom t WHERE t.firstOrderYn = :firstOrderYn"),
    @NamedQuery(name = "Tpromom.findByCouponYn", query = "SELECT t FROM Tpromom t WHERE t.couponYn = :couponYn"),
    @NamedQuery(name = "Tpromom.findByAppType", query = "SELECT t FROM Tpromom t WHERE t.appType = :appType"),
    @NamedQuery(name = "Tpromom.findByDoType", query = "SELECT t FROM Tpromom t WHERE t.doType = :doType"),
    @NamedQuery(name = "Tpromom.findByPromoBdate", query = "SELECT t FROM Tpromom t WHERE t.promoBdate = :promoBdate"),
    @NamedQuery(name = "Tpromom.findByPromoEdate", query = "SELECT t FROM Tpromom t WHERE t.promoEdate = :promoEdate"),
    @NamedQuery(name = "Tpromom.findByMembGbAllYn", query = "SELECT t FROM Tpromom t WHERE t.membGbAllYn = :membGbAllYn"),
    @NamedQuery(name = "Tpromom.findByMembGb", query = "SELECT t FROM Tpromom t WHERE t.membGb = :membGb"),
    @NamedQuery(name = "Tpromom.findByOrderMediaAllYn", query = "SELECT t FROM Tpromom t WHERE t.orderMediaAllYn = :orderMediaAllYn"),
    @NamedQuery(name = "Tpromom.findByOrderMedia", query = "SELECT t FROM Tpromom t WHERE t.orderMedia = :orderMedia"),
    @NamedQuery(name = "Tpromom.findByMediaCodeAllYn", query = "SELECT t FROM Tpromom t WHERE t.mediaCodeAllYn = :mediaCodeAllYn"),
    @NamedQuery(name = "Tpromom.findByMediaCode", query = "SELECT t FROM Tpromom t WHERE t.mediaCode = :mediaCode"),
    @NamedQuery(name = "Tpromom.findByLimitYn", query = "SELECT t FROM Tpromom t WHERE t.limitYn = :limitYn"),
    @NamedQuery(name = "Tpromom.findByLimitQty", query = "SELECT t FROM Tpromom t WHERE t.limitQty = :limitQty"),
    @NamedQuery(name = "Tpromom.findByGoodsAllYn", query = "SELECT t FROM Tpromom t WHERE t.goodsAllYn = :goodsAllYn"),
    @NamedQuery(name = "Tpromom.findByGrossNetFlag", query = "SELECT t FROM Tpromom t WHERE t.grossNetFlag = :grossNetFlag"),
    @NamedQuery(name = "Tpromom.findByAppAmt", query = "SELECT t FROM Tpromom t WHERE t.appAmt = :appAmt"),
    @NamedQuery(name = "Tpromom.findByAmtRateFlag", query = "SELECT t FROM Tpromom t WHERE t.amtRateFlag = :amtRateFlag"),
    @NamedQuery(name = "Tpromom.findByDoRate", query = "SELECT t FROM Tpromom t WHERE t.doRate = :doRate"),
    @NamedQuery(name = "Tpromom.findByDoAmt", query = "SELECT t FROM Tpromom t WHERE t.doAmt = :doAmt"),
    @NamedQuery(name = "Tpromom.findBySelectYn", query = "SELECT t FROM Tpromom t WHERE t.selectYn = :selectYn"),
    @NamedQuery(name = "Tpromom.findBySelectQty", query = "SELECT t FROM Tpromom t WHERE t.selectQty = :selectQty"),
    @NamedQuery(name = "Tpromom.findByCouponPromoNo", query = "SELECT t FROM Tpromom t WHERE t.couponPromoNo = :couponPromoNo"),
    @NamedQuery(name = "Tpromom.findByLotteryPromoNo", query = "SELECT t FROM Tpromom t WHERE t.lotteryPromoNo = :lotteryPromoNo"),
    @NamedQuery(name = "Tpromom.findByUseCode", query = "SELECT t FROM Tpromom t WHERE t.useCode = :useCode"),
    @NamedQuery(name = "Tpromom.findByCardAllYn", query = "SELECT t FROM Tpromom t WHERE t.cardAllYn = :cardAllYn"),
    @NamedQuery(name = "Tpromom.findByCardCode", query = "SELECT t FROM Tpromom t WHERE t.cardCode = :cardCode"),
    @NamedQuery(name = "Tpromom.findByCardDcFlag", query = "SELECT t FROM Tpromom t WHERE t.cardDcFlag = :cardDcFlag"),
    @NamedQuery(name = "Tpromom.findByNorestAllotMonths", query = "SELECT t FROM Tpromom t WHERE t.norestAllotMonths = :norestAllotMonths"),
    @NamedQuery(name = "Tpromom.findByCouponUseFixYn", query = "SELECT t FROM Tpromom t WHERE t.couponUseFixYn = :couponUseFixYn"),
    @NamedQuery(name = "Tpromom.findByCouponUseDay", query = "SELECT t FROM Tpromom t WHERE t.couponUseDay = :couponUseDay"),
    @NamedQuery(name = "Tpromom.findByValidDays", query = "SELECT t FROM Tpromom t WHERE t.validDays = :validDays"),
    @NamedQuery(name = "Tpromom.findByRemark", query = "SELECT t FROM Tpromom t WHERE t.remark = :remark"),
    @NamedQuery(name = "Tpromom.findByInsertId", query = "SELECT t FROM Tpromom t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tpromom.findByInsertDate", query = "SELECT t FROM Tpromom t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tpromom.findByModifyId", query = "SELECT t FROM Tpromom t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tpromom.findByModifyDate", query = "SELECT t FROM Tpromom t WHERE t.modifyDate = :modifyDate"),
    @NamedQuery(name = "Tpromom.findByCustGroup", query = "SELECT t FROM Tpromom t WHERE t.custGroup = :custGroup")})
public class Tpromom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "PROMO_NO")
    private String promoNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 900)
    @Column(name = "PROMO_NAME")
    private String promoName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "FIRST_ORDER_YN")
    private String firstOrderYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COUPON_YN")
    private String couponYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "APP_TYPE")
    private String appType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DO_TYPE")
    private String doType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROMO_BDATE")
    @Temporal(TemporalType.DATE)
    private Date promoBdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROMO_EDATE")
    @Temporal(TemporalType.DATE)
    private Date promoEdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MEMB_GB_ALL_YN")
    private String membGbAllYn;
    @Size(max = 30)
    @Column(name = "MEMB_GB")
    private String membGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ORDER_MEDIA_ALL_YN")
    private String orderMediaAllYn;
    @Size(max = 50)
    @Column(name = "ORDER_MEDIA")
    private String orderMedia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MEDIA_CODE_ALL_YN")
    private String mediaCodeAllYn;
    @Size(max = 50)
    @Column(name = "MEDIA_CODE")
    private String mediaCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "LIMIT_YN")
    private String limitYn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMIT_QTY")
    private int limitQty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GOODS_ALL_YN")
    private String goodsAllYn;
    @Size(max = 1)
    @Column(name = "GROSS_NET_FLAG")
    private String grossNetFlag;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "APP_AMT")
    private BigDecimal appAmt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "AMT_RATE_FLAG")
    private String amtRateFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DO_RATE")
    private BigDecimal doRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DO_AMT")
    private BigDecimal doAmt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SELECT_YN")
    private String selectYn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SELECT_QTY")
    private int selectQty;
    @Size(max = 12)
    @Column(name = "COUPON_PROMO_NO")
    private String couponPromoNo;
    @Size(max = 12)
    @Column(name = "LOTTERY_PROMO_NO")
    private String lotteryPromoNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "USE_CODE")
    private String useCode;
    @Size(max = 1)
    @Column(name = "CARD_ALL_YN")
    private String cardAllYn;
    @Size(max = 4)
    @Column(name = "CARD_CODE")
    private String cardCode;
    @Size(max = 1)
    @Column(name = "CARD_DC_FLAG")
    private String cardDcFlag;
    @Column(name = "NOREST_ALLOT_MONTHS")
    private Short norestAllotMonths;
    @Size(max = 1)
    @Column(name = "COUPON_USE_FIX_YN")
    private String couponUseFixYn;
    @Column(name = "COUPON_USE_DAY")
    private Integer couponUseDay;
    @Column(name = "VALID_DAYS")
    private Integer validDays;
    @Size(max = 600)
    @Column(name = "REMARK")
    private String remark;
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
    @Size(max = 20)
    @Column(name = "CUST_GROUP")
    private String custGroup;

    public Tpromom() {
    }

    public Tpromom(String promoNo) {
        this.promoNo = promoNo;
    }

    public Tpromom(String promoNo, String promoName, String firstOrderYn, String couponYn, String appType, String doType, Date promoBdate, Date promoEdate, String membGbAllYn, String orderMediaAllYn, String mediaCodeAllYn, String limitYn, int limitQty, String goodsAllYn, String amtRateFlag, BigDecimal doRate, BigDecimal doAmt, String selectYn, int selectQty, String useCode, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.promoNo = promoNo;
        this.promoName = promoName;
        this.firstOrderYn = firstOrderYn;
        this.couponYn = couponYn;
        this.appType = appType;
        this.doType = doType;
        this.promoBdate = promoBdate;
        this.promoEdate = promoEdate;
        this.membGbAllYn = membGbAllYn;
        this.orderMediaAllYn = orderMediaAllYn;
        this.mediaCodeAllYn = mediaCodeAllYn;
        this.limitYn = limitYn;
        this.limitQty = limitQty;
        this.goodsAllYn = goodsAllYn;
        this.amtRateFlag = amtRateFlag;
        this.doRate = doRate;
        this.doAmt = doAmt;
        this.selectYn = selectYn;
        this.selectQty = selectQty;
        this.useCode = useCode;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getPromoNo() {
        return promoNo;
    }

    public void setPromoNo(String promoNo) {
        this.promoNo = promoNo;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public String getFirstOrderYn() {
        return firstOrderYn;
    }

    public void setFirstOrderYn(String firstOrderYn) {
        this.firstOrderYn = firstOrderYn;
    }

    public String getCouponYn() {
        return couponYn;
    }

    public void setCouponYn(String couponYn) {
        this.couponYn = couponYn;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public Date getPromoBdate() {
        return promoBdate;
    }

    public void setPromoBdate(Date promoBdate) {
        this.promoBdate = promoBdate;
    }

    public Date getPromoEdate() {
        return promoEdate;
    }

    public void setPromoEdate(Date promoEdate) {
        this.promoEdate = promoEdate;
    }

    public String getMembGbAllYn() {
        return membGbAllYn;
    }

    public void setMembGbAllYn(String membGbAllYn) {
        this.membGbAllYn = membGbAllYn;
    }

    public String getMembGb() {
        return membGb;
    }

    public void setMembGb(String membGb) {
        this.membGb = membGb;
    }

    public String getOrderMediaAllYn() {
        return orderMediaAllYn;
    }

    public void setOrderMediaAllYn(String orderMediaAllYn) {
        this.orderMediaAllYn = orderMediaAllYn;
    }

    public String getOrderMedia() {
        return orderMedia;
    }

    public void setOrderMedia(String orderMedia) {
        this.orderMedia = orderMedia;
    }

    public String getMediaCodeAllYn() {
        return mediaCodeAllYn;
    }

    public void setMediaCodeAllYn(String mediaCodeAllYn) {
        this.mediaCodeAllYn = mediaCodeAllYn;
    }

    public String getMediaCode() {
        return mediaCode;
    }

    public void setMediaCode(String mediaCode) {
        this.mediaCode = mediaCode;
    }

    public String getLimitYn() {
        return limitYn;
    }

    public void setLimitYn(String limitYn) {
        this.limitYn = limitYn;
    }

    public int getLimitQty() {
        return limitQty;
    }

    public void setLimitQty(int limitQty) {
        this.limitQty = limitQty;
    }

    public String getGoodsAllYn() {
        return goodsAllYn;
    }

    public void setGoodsAllYn(String goodsAllYn) {
        this.goodsAllYn = goodsAllYn;
    }

    public String getGrossNetFlag() {
        return grossNetFlag;
    }

    public void setGrossNetFlag(String grossNetFlag) {
        this.grossNetFlag = grossNetFlag;
    }

    public BigDecimal getAppAmt() {
        return appAmt;
    }

    public void setAppAmt(BigDecimal appAmt) {
        this.appAmt = appAmt;
    }

    public String getAmtRateFlag() {
        return amtRateFlag;
    }

    public void setAmtRateFlag(String amtRateFlag) {
        this.amtRateFlag = amtRateFlag;
    }

    public BigDecimal getDoRate() {
        return doRate;
    }

    public void setDoRate(BigDecimal doRate) {
        this.doRate = doRate;
    }

    public BigDecimal getDoAmt() {
        return doAmt;
    }

    public void setDoAmt(BigDecimal doAmt) {
        this.doAmt = doAmt;
    }

    public String getSelectYn() {
        return selectYn;
    }

    public void setSelectYn(String selectYn) {
        this.selectYn = selectYn;
    }

    public int getSelectQty() {
        return selectQty;
    }

    public void setSelectQty(int selectQty) {
        this.selectQty = selectQty;
    }

    public String getCouponPromoNo() {
        return couponPromoNo;
    }

    public void setCouponPromoNo(String couponPromoNo) {
        this.couponPromoNo = couponPromoNo;
    }

    public String getLotteryPromoNo() {
        return lotteryPromoNo;
    }

    public void setLotteryPromoNo(String lotteryPromoNo) {
        this.lotteryPromoNo = lotteryPromoNo;
    }

    public String getUseCode() {
        return useCode;
    }

    public void setUseCode(String useCode) {
        this.useCode = useCode;
    }

    public String getCardAllYn() {
        return cardAllYn;
    }

    public void setCardAllYn(String cardAllYn) {
        this.cardAllYn = cardAllYn;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardDcFlag() {
        return cardDcFlag;
    }

    public void setCardDcFlag(String cardDcFlag) {
        this.cardDcFlag = cardDcFlag;
    }

    public Short getNorestAllotMonths() {
        return norestAllotMonths;
    }

    public void setNorestAllotMonths(Short norestAllotMonths) {
        this.norestAllotMonths = norestAllotMonths;
    }

    public String getCouponUseFixYn() {
        return couponUseFixYn;
    }

    public void setCouponUseFixYn(String couponUseFixYn) {
        this.couponUseFixYn = couponUseFixYn;
    }

    public Integer getCouponUseDay() {
        return couponUseDay;
    }

    public void setCouponUseDay(Integer couponUseDay) {
        this.couponUseDay = couponUseDay;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promoNo != null ? promoNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpromom)) {
            return false;
        }
        Tpromom other = (Tpromom) object;
        if ((this.promoNo == null && other.promoNo != null) || (this.promoNo != null && !this.promoNo.equals(other.promoNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tpromom[ promoNo=" + promoNo + " ]";
    }
    
}
