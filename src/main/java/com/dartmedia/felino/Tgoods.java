/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "TGOODS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tgoods.findAll", query = "SELECT t FROM Tgoods t"),
    @NamedQuery(name = "Tgoods.findByGoodsCode", query = "SELECT t FROM Tgoods t WHERE t.goodsCode = :goodsCode"),
    @NamedQuery(name = "Tgoods.findByGoodsName", query = "SELECT t FROM Tgoods t WHERE t.goodsName = :goodsName"),
    @NamedQuery(name = "Tgoods.findByLgroup", query = "SELECT t FROM Tgoods t WHERE t.lgroup = :lgroup"),
    @NamedQuery(name = "Tgoods.findByMgroup", query = "SELECT t FROM Tgoods t WHERE t.mgroup = :mgroup"),
    @NamedQuery(name = "Tgoods.findBySgroup", query = "SELECT t FROM Tgoods t WHERE t.sgroup = :sgroup"),
    @NamedQuery(name = "Tgoods.findByDgroup", query = "SELECT t FROM Tgoods t WHERE t.dgroup = :dgroup"),
    @NamedQuery(name = "Tgoods.findByLmsdCode", query = "SELECT t FROM Tgoods t WHERE t.lmsdCode = :lmsdCode"),
    @NamedQuery(name = "Tgoods.findByQcLgroup", query = "SELECT t FROM Tgoods t WHERE t.qcLgroup = :qcLgroup"),
    @NamedQuery(name = "Tgoods.findByQcMgroup", query = "SELECT t FROM Tgoods t WHERE t.qcMgroup = :qcMgroup"),
    @NamedQuery(name = "Tgoods.findBySaleGb", query = "SELECT t FROM Tgoods t WHERE t.saleGb = :saleGb"),
    @NamedQuery(name = "Tgoods.findBySignGb", query = "SELECT t FROM Tgoods t WHERE t.signGb = :signGb"),
    @NamedQuery(name = "Tgoods.findByEntpCode", query = "SELECT t FROM Tgoods t WHERE t.entpCode = :entpCode"),
    @NamedQuery(name = "Tgoods.findByEntpManSeq", query = "SELECT t FROM Tgoods t WHERE t.entpManSeq = :entpManSeq"),
    @NamedQuery(name = "Tgoods.findByAccountGb", query = "SELECT t FROM Tgoods t WHERE t.accountGb = :accountGb"),
    @NamedQuery(name = "Tgoods.findByMdCode", query = "SELECT t FROM Tgoods t WHERE t.mdCode = :mdCode"),
    @NamedQuery(name = "Tgoods.findByKeyword", query = "SELECT t FROM Tgoods t WHERE t.keyword = :keyword"),
    @NamedQuery(name = "Tgoods.findByBuyMed", query = "SELECT t FROM Tgoods t WHERE t.buyMed = :buyMed"),
    @NamedQuery(name = "Tgoods.findByDelyType", query = "SELECT t FROM Tgoods t WHERE t.delyType = :delyType"),
    @NamedQuery(name = "Tgoods.findByWhCode", query = "SELECT t FROM Tgoods t WHERE t.whCode = :whCode"),
    @NamedQuery(name = "Tgoods.findByPostYn", query = "SELECT t FROM Tgoods t WHERE t.postYn = :postYn"),
    @NamedQuery(name = "Tgoods.findByTaxYn", query = "SELECT t FROM Tgoods t WHERE t.taxYn = :taxYn"),
    @NamedQuery(name = "Tgoods.findByVatRate", query = "SELECT t FROM Tgoods t WHERE t.vatRate = :vatRate"),
    @NamedQuery(name = "Tgoods.findByCostTaxYn", query = "SELECT t FROM Tgoods t WHERE t.costTaxYn = :costTaxYn"),
    @NamedQuery(name = "Tgoods.findByCostVatRate", query = "SELECT t FROM Tgoods t WHERE t.costVatRate = :costVatRate"),
    @NamedQuery(name = "Tgoods.findByMakecoCode", query = "SELECT t FROM Tgoods t WHERE t.makecoCode = :makecoCode"),
    @NamedQuery(name = "Tgoods.findByOriginCode", query = "SELECT t FROM Tgoods t WHERE t.originCode = :originCode"),
    @NamedQuery(name = "Tgoods.findByBrandCode", query = "SELECT t FROM Tgoods t WHERE t.brandCode = :brandCode"),
    @NamedQuery(name = "Tgoods.findByHandleCode", query = "SELECT t FROM Tgoods t WHERE t.handleCode = :handleCode"),
    @NamedQuery(name = "Tgoods.findByMixpackYn", query = "SELECT t FROM Tgoods t WHERE t.mixpackYn = :mixpackYn"),
    @NamedQuery(name = "Tgoods.findByPackBox", query = "SELECT t FROM Tgoods t WHERE t.packBox = :packBox"),
    @NamedQuery(name = "Tgoods.findBySqcYn", query = "SELECT t FROM Tgoods t WHERE t.sqcYn = :sqcYn"),
    @NamedQuery(name = "Tgoods.findBySqcGb", query = "SELECT t FROM Tgoods t WHERE t.sqcGb = :sqcGb"),
    @NamedQuery(name = "Tgoods.findBySetYn", query = "SELECT t FROM Tgoods t WHERE t.setYn = :setYn"),
    @NamedQuery(name = "Tgoods.findBySetGoodsYn", query = "SELECT t FROM Tgoods t WHERE t.setGoodsYn = :setGoodsYn"),
    @NamedQuery(name = "Tgoods.findByGiftYn", query = "SELECT t FROM Tgoods t WHERE t.giftYn = :giftYn"),
    @NamedQuery(name = "Tgoods.findByPayYn", query = "SELECT t FROM Tgoods t WHERE t.payYn = :payYn"),
    @NamedQuery(name = "Tgoods.findByDmYn", query = "SELECT t FROM Tgoods t WHERE t.dmYn = :dmYn"),
    @NamedQuery(name = "Tgoods.findByGiftReturnYn", query = "SELECT t FROM Tgoods t WHERE t.giftReturnYn = :giftReturnYn"),
    @NamedQuery(name = "Tgoods.findByExchYn", query = "SELECT t FROM Tgoods t WHERE t.exchYn = :exchYn"),
    @NamedQuery(name = "Tgoods.findByReturnYn", query = "SELECT t FROM Tgoods t WHERE t.returnYn = :returnYn"),
    @NamedQuery(name = "Tgoods.findByAsYn", query = "SELECT t FROM Tgoods t WHERE t.asYn = :asYn"),
    @NamedQuery(name = "Tgoods.findByOutStockYn", query = "SELECT t FROM Tgoods t WHERE t.outStockYn = :outStockYn"),
    @NamedQuery(name = "Tgoods.findByOrderMinQty", query = "SELECT t FROM Tgoods t WHERE t.orderMinQty = :orderMinQty"),
    @NamedQuery(name = "Tgoods.findByStockChkPlace", query = "SELECT t FROM Tgoods t WHERE t.stockChkPlace = :stockChkPlace"),
    @NamedQuery(name = "Tgoods.findByFirstBroadDate", query = "SELECT t FROM Tgoods t WHERE t.firstBroadDate = :firstBroadDate"),
    @NamedQuery(name = "Tgoods.findByOrderMediaAllYn", query = "SELECT t FROM Tgoods t WHERE t.orderMediaAllYn = :orderMediaAllYn"),
    @NamedQuery(name = "Tgoods.findByOrderMedia", query = "SELECT t FROM Tgoods t WHERE t.orderMedia = :orderMedia"),
    @NamedQuery(name = "Tgoods.findByArsName", query = "SELECT t FROM Tgoods t WHERE t.arsName = :arsName"),
    @NamedQuery(name = "Tgoods.findByParentGoodsCode", query = "SELECT t FROM Tgoods t WHERE t.parentGoodsCode = :parentGoodsCode"),
    @NamedQuery(name = "Tgoods.findByBaseGoodsCode", query = "SELECT t FROM Tgoods t WHERE t.baseGoodsCode = :baseGoodsCode"),
    @NamedQuery(name = "Tgoods.findByNorestAllotYn", query = "SELECT t FROM Tgoods t WHERE t.norestAllotYn = :norestAllotYn"),
    @NamedQuery(name = "Tgoods.findByNorestAllotMonths", query = "SELECT t FROM Tgoods t WHERE t.norestAllotMonths = :norestAllotMonths"),
    @NamedQuery(name = "Tgoods.findByCustDcYn", query = "SELECT t FROM Tgoods t WHERE t.custDcYn = :custDcYn"),
    @NamedQuery(name = "Tgoods.findBySampleYn", query = "SELECT t FROM Tgoods t WHERE t.sampleYn = :sampleYn"),
    @NamedQuery(name = "Tgoods.findBySaleStartDate", query = "SELECT t FROM Tgoods t WHERE t.saleStartDate = :saleStartDate"),
    @NamedQuery(name = "Tgoods.findByVVolume", query = "SELECT t FROM Tgoods t WHERE t.vVolume = :vVolume"),
    @NamedQuery(name = "Tgoods.findByMasterCode", query = "SELECT t FROM Tgoods t WHERE t.masterCode = :masterCode"),
    @NamedQuery(name = "Tgoods.findByInUnit", query = "SELECT t FROM Tgoods t WHERE t.inUnit = :inUnit"),
    @NamedQuery(name = "Tgoods.findByInUnitQty", query = "SELECT t FROM Tgoods t WHERE t.inUnitQty = :inUnitQty"),
    @NamedQuery(name = "Tgoods.findByRecoCnt", query = "SELECT t FROM Tgoods t WHERE t.recoCnt = :recoCnt"),
    @NamedQuery(name = "Tgoods.findByOrderRank", query = "SELECT t FROM Tgoods t WHERE t.orderRank = :orderRank"),
    @NamedQuery(name = "Tgoods.findByCommentCnt", query = "SELECT t FROM Tgoods t WHERE t.commentCnt = :commentCnt"),
    @NamedQuery(name = "Tgoods.findByWeight", query = "SELECT t FROM Tgoods t WHERE t.weight = :weight"),
    @NamedQuery(name = "Tgoods.findByFormCode", query = "SELECT t FROM Tgoods t WHERE t.formCode = :formCode"),
    @NamedQuery(name = "Tgoods.findBySizeCode", query = "SELECT t FROM Tgoods t WHERE t.sizeCode = :sizeCode"),
    @NamedQuery(name = "Tgoods.findByWhFixYn", query = "SELECT t FROM Tgoods t WHERE t.whFixYn = :whFixYn"),
    @NamedQuery(name = "Tgoods.findByDirectShipYn", query = "SELECT t FROM Tgoods t WHERE t.directShipYn = :directShipYn"),
    @NamedQuery(name = "Tgoods.findByPayFlag", query = "SELECT t FROM Tgoods t WHERE t.payFlag = :payFlag"),
    @NamedQuery(name = "Tgoods.findByAdvcRefundYn", query = "SELECT t FROM Tgoods t WHERE t.advcRefundYn = :advcRefundYn"),
    @NamedQuery(name = "Tgoods.findByDirectReturnYn", query = "SELECT t FROM Tgoods t WHERE t.directReturnYn = :directReturnYn"),
    @NamedQuery(name = "Tgoods.findByShipManSeq", query = "SELECT t FROM Tgoods t WHERE t.shipManSeq = :shipManSeq"),
    @NamedQuery(name = "Tgoods.findByReturnManSeq", query = "SELECT t FROM Tgoods t WHERE t.returnManSeq = :returnManSeq"),
    @NamedQuery(name = "Tgoods.findByOrderMakeYn", query = "SELECT t FROM Tgoods t WHERE t.orderMakeYn = :orderMakeYn"),
    @NamedQuery(name = "Tgoods.findByInstallYn", query = "SELECT t FROM Tgoods t WHERE t.installYn = :installYn"),
    @NamedQuery(name = "Tgoods.findByInsertId", query = "SELECT t FROM Tgoods t WHERE t.insertId = :insertId"),
    @NamedQuery(name = "Tgoods.findByInsertDate", query = "SELECT t FROM Tgoods t WHERE t.insertDate = :insertDate"),
    @NamedQuery(name = "Tgoods.findByModifyId", query = "SELECT t FROM Tgoods t WHERE t.modifyId = :modifyId"),
    @NamedQuery(name = "Tgoods.findByModifyDate", query = "SELECT t FROM Tgoods t WHERE t.modifyDate = :modifyDate"),
    @NamedQuery(name = "Tgoods.findByDelyBoxQty", query = "SELECT t FROM Tgoods t WHERE t.delyBoxQty = :delyBoxQty"),
    @NamedQuery(name = "Tgoods.findByDirectYn", query = "SELECT t FROM Tgoods t WHERE t.directYn = :directYn"),
    @NamedQuery(name = "Tgoods.findByEntpReturnYn", query = "SELECT t FROM Tgoods t WHERE t.entpReturnYn = :entpReturnYn"),
    @NamedQuery(name = "Tgoods.findByWmsWidth", query = "SELECT t FROM Tgoods t WHERE t.wmsWidth = :wmsWidth"),
    @NamedQuery(name = "Tgoods.findByWmsLength", query = "SELECT t FROM Tgoods t WHERE t.wmsLength = :wmsLength"),
    @NamedQuery(name = "Tgoods.findByWmsHeight", query = "SELECT t FROM Tgoods t WHERE t.wmsHeight = :wmsHeight"),
    @NamedQuery(name = "Tgoods.findByWmsVolume", query = "SELECT t FROM Tgoods t WHERE t.wmsVolume = :wmsVolume"),
    @NamedQuery(name = "Tgoods.findByFreeDeliveryYn", query = "SELECT t FROM Tgoods t WHERE t.freeDeliveryYn = :freeDeliveryYn"),
    @NamedQuery(name = "Tgoods.findByExtended", query = "SELECT t FROM Tgoods t WHERE t.extended = :extended"),
    @NamedQuery(name = "Tgoods.findByWarrantyPeriode", query = "SELECT t FROM Tgoods t WHERE t.warrantyPeriode = :warrantyPeriode"),
    @NamedQuery(name = "Tgoods.findByExPromoYn", query = "SELECT t FROM Tgoods t WHERE t.exPromoYn = :exPromoYn")})
public class Tgoods implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "GOODS_CODE")
    private String goodsCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 900)
    @Column(name = "GOODS_NAME")
    private String goodsName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "LGROUP")
    private String lgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MGROUP")
    private String mgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SGROUP")
    private String sgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DGROUP")
    private String dgroup;
    @Size(max = 8)
    @Column(name = "LMSD_CODE")
    private String lmsdCode;
    @Size(max = 2)
    @Column(name = "QC_LGROUP")
    private String qcLgroup;
    @Size(max = 2)
    @Column(name = "QC_MGROUP")
    private String qcMgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SALE_GB")
    private String saleGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SIGN_GB")
    private String signGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ENTP_CODE")
    private String entpCode;
    @Size(max = 3)
    @Column(name = "ENTP_MAN_SEQ")
    private String entpManSeq;
    @Size(max = 2)
    @Column(name = "ACCOUNT_GB")
    private String accountGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "MD_CODE")
    private String mdCode;
    @Size(max = 1800)
    @Column(name = "KEYWORD")
    private String keyword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "BUY_MED")
    private String buyMed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DELY_TYPE")
    private String delyType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "WH_CODE")
    private String whCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "POST_YN")
    private String postYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TAX_YN")
    private String taxYn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAT_RATE")
    private BigDecimal vatRate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COST_TAX_YN")
    private String costTaxYn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST_VAT_RATE")
    private BigDecimal costVatRate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "MAKECO_CODE")
    private String makecoCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ORIGIN_CODE")
    private String originCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "BRAND_CODE")
    private String brandCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "HANDLE_CODE")
    private String handleCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MIXPACK_YN")
    private String mixpackYn;
    @Column(name = "PACK_BOX")
    private Short packBox;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SQC_YN")
    private String sqcYn;
    @Size(max = 2)
    @Column(name = "SQC_GB")
    private String sqcGb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SET_YN")
    private String setYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SET_GOODS_YN")
    private String setGoodsYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GIFT_YN")
    private String giftYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PAY_YN")
    private String payYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DM_YN")
    private String dmYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GIFT_RETURN_YN")
    private String giftReturnYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "EXCH_YN")
    private String exchYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "RETURN_YN")
    private String returnYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "AS_YN")
    private String asYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "OUT_STOCK_YN")
    private String outStockYn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDER_MIN_QTY")
    private int orderMinQty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STOCK_CHK_PLACE")
    private String stockChkPlace;
    @Column(name = "FIRST_BROAD_DATE")
    @Temporal(TemporalType.DATE)
    private Date firstBroadDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ORDER_MEDIA_ALL_YN")
    private String orderMediaAllYn;
    @Size(max = 20)
    @Column(name = "ORDER_MEDIA")
    private String orderMedia;
    @Size(max = 900)
    @Column(name = "ARS_NAME")
    private String arsName;
    @Size(max = 10)
    @Column(name = "PARENT_GOODS_CODE")
    private String parentGoodsCode;
    @Size(max = 10)
    @Column(name = "BASE_GOODS_CODE")
    private String baseGoodsCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "NOREST_ALLOT_YN")
    private String norestAllotYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "NOREST_ALLOT_MONTHS")
    private String norestAllotMonths;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CUST_DC_YN")
    private String custDcYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SAMPLE_YN")
    private String sampleYn;
    @Column(name = "SALE_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date saleStartDate;
    @Size(max = 4)
    @Column(name = "V_VOLUME")
    private String vVolume;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MASTER_CODE")
    private String masterCode;
    @Size(max = 20)
    @Column(name = "IN_UNIT")
    private String inUnit;
    @Column(name = "IN_UNIT_QTY")
    private Integer inUnitQty;
    @Column(name = "RECO_CNT")
    private Integer recoCnt;
    @Column(name = "ORDER_RANK")
    private BigInteger orderRank;
    @Column(name = "COMMENT_CNT")
    private BigInteger commentCnt;
    @Size(max = 10)
    @Column(name = "WEIGHT")
    private String weight;
    @Size(max = 4)
    @Column(name = "FORM_CODE")
    private String formCode;
    @Size(max = 4)
    @Column(name = "SIZE_CODE")
    private String sizeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "WH_FIX_YN")
    private String whFixYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DIRECT_SHIP_YN")
    private String directShipYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PAY_FLAG")
    private String payFlag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ADVC_REFUND_YN")
    private String advcRefundYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DIRECT_RETURN_YN")
    private String directReturnYn;
    @Size(max = 3)
    @Column(name = "SHIP_MAN_SEQ")
    private String shipManSeq;
    @Size(max = 3)
    @Column(name = "RETURN_MAN_SEQ")
    private String returnManSeq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ORDER_MAKE_YN")
    private String orderMakeYn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "INSTALL_YN")
    private String installYn;
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
    @Column(name = "DELY_BOX_QTY")
    private Short delyBoxQty;
    @Size(max = 1)
    @Column(name = "DIRECT_YN")
    private String directYn;
    @Size(max = 1)
    @Column(name = "ENTP_RETURN_YN")
    private String entpReturnYn;
    @Column(name = "WMS_WIDTH")
    private Short wmsWidth;
    @Column(name = "WMS_LENGTH")
    private Short wmsLength;
    @Column(name = "WMS_HEIGHT")
    private Short wmsHeight;
    @Column(name = "WMS_VOLUME")
    private BigDecimal wmsVolume;
    @Size(max = 1)
    @Column(name = "FREE_DELIVERY_YN")
    private String freeDeliveryYn;
    @Size(max = 10)
    @Column(name = "EXTENDED")
    private String extended;
    @Column(name = "WARRANTY_PERIODE")
    private Long warrantyPeriode;
    @Size(max = 1)
    @Column(name = "EX_PROMO_YN")
    private String exPromoYn;

    public Tgoods() {
    }

    public Tgoods(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Tgoods(String goodsCode, String goodsName, String lgroup, String mgroup, String sgroup, String dgroup, String saleGb, String signGb, String entpCode, String mdCode, String buyMed, String delyType, String whCode, String postYn, String taxYn, BigDecimal vatRate, String costTaxYn, BigDecimal costVatRate, String makecoCode, String originCode, String brandCode, String handleCode, String mixpackYn, String sqcYn, String setYn, String setGoodsYn, String giftYn, String payYn, String dmYn, String giftReturnYn, String exchYn, String returnYn, String asYn, String outStockYn, int orderMinQty, String stockChkPlace, String orderMediaAllYn, String norestAllotYn, String norestAllotMonths, String custDcYn, String sampleYn, String masterCode, String whFixYn, String directShipYn, String payFlag, String advcRefundYn, String directReturnYn, String orderMakeYn, String installYn, String insertId, Date insertDate, String modifyId, Date modifyDate) {
        this.goodsCode = goodsCode;
        this.goodsName = goodsName;
        this.lgroup = lgroup;
        this.mgroup = mgroup;
        this.sgroup = sgroup;
        this.dgroup = dgroup;
        this.saleGb = saleGb;
        this.signGb = signGb;
        this.entpCode = entpCode;
        this.mdCode = mdCode;
        this.buyMed = buyMed;
        this.delyType = delyType;
        this.whCode = whCode;
        this.postYn = postYn;
        this.taxYn = taxYn;
        this.vatRate = vatRate;
        this.costTaxYn = costTaxYn;
        this.costVatRate = costVatRate;
        this.makecoCode = makecoCode;
        this.originCode = originCode;
        this.brandCode = brandCode;
        this.handleCode = handleCode;
        this.mixpackYn = mixpackYn;
        this.sqcYn = sqcYn;
        this.setYn = setYn;
        this.setGoodsYn = setGoodsYn;
        this.giftYn = giftYn;
        this.payYn = payYn;
        this.dmYn = dmYn;
        this.giftReturnYn = giftReturnYn;
        this.exchYn = exchYn;
        this.returnYn = returnYn;
        this.asYn = asYn;
        this.outStockYn = outStockYn;
        this.orderMinQty = orderMinQty;
        this.stockChkPlace = stockChkPlace;
        this.orderMediaAllYn = orderMediaAllYn;
        this.norestAllotYn = norestAllotYn;
        this.norestAllotMonths = norestAllotMonths;
        this.custDcYn = custDcYn;
        this.sampleYn = sampleYn;
        this.masterCode = masterCode;
        this.whFixYn = whFixYn;
        this.directShipYn = directShipYn;
        this.payFlag = payFlag;
        this.advcRefundYn = advcRefundYn;
        this.directReturnYn = directReturnYn;
        this.orderMakeYn = orderMakeYn;
        this.installYn = installYn;
        this.insertId = insertId;
        this.insertDate = insertDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLgroup() {
        return lgroup;
    }

    public void setLgroup(String lgroup) {
        this.lgroup = lgroup;
    }

    public String getMgroup() {
        return mgroup;
    }

    public void setMgroup(String mgroup) {
        this.mgroup = mgroup;
    }

    public String getSgroup() {
        return sgroup;
    }

    public void setSgroup(String sgroup) {
        this.sgroup = sgroup;
    }

    public String getDgroup() {
        return dgroup;
    }

    public void setDgroup(String dgroup) {
        this.dgroup = dgroup;
    }

    public String getLmsdCode() {
        return lmsdCode;
    }

    public void setLmsdCode(String lmsdCode) {
        this.lmsdCode = lmsdCode;
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

    public String getSaleGb() {
        return saleGb;
    }

    public void setSaleGb(String saleGb) {
        this.saleGb = saleGb;
    }

    public String getSignGb() {
        return signGb;
    }

    public void setSignGb(String signGb) {
        this.signGb = signGb;
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

    public String getAccountGb() {
        return accountGb;
    }

    public void setAccountGb(String accountGb) {
        this.accountGb = accountGb;
    }

    public String getMdCode() {
        return mdCode;
    }

    public void setMdCode(String mdCode) {
        this.mdCode = mdCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBuyMed() {
        return buyMed;
    }

    public void setBuyMed(String buyMed) {
        this.buyMed = buyMed;
    }

    public String getDelyType() {
        return delyType;
    }

    public void setDelyType(String delyType) {
        this.delyType = delyType;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getPostYn() {
        return postYn;
    }

    public void setPostYn(String postYn) {
        this.postYn = postYn;
    }

    public String getTaxYn() {
        return taxYn;
    }

    public void setTaxYn(String taxYn) {
        this.taxYn = taxYn;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    public String getCostTaxYn() {
        return costTaxYn;
    }

    public void setCostTaxYn(String costTaxYn) {
        this.costTaxYn = costTaxYn;
    }

    public BigDecimal getCostVatRate() {
        return costVatRate;
    }

    public void setCostVatRate(BigDecimal costVatRate) {
        this.costVatRate = costVatRate;
    }

    public String getMakecoCode() {
        return makecoCode;
    }

    public void setMakecoCode(String makecoCode) {
        this.makecoCode = makecoCode;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(String handleCode) {
        this.handleCode = handleCode;
    }

    public String getMixpackYn() {
        return mixpackYn;
    }

    public void setMixpackYn(String mixpackYn) {
        this.mixpackYn = mixpackYn;
    }

    public Short getPackBox() {
        return packBox;
    }

    public void setPackBox(Short packBox) {
        this.packBox = packBox;
    }

    public String getSqcYn() {
        return sqcYn;
    }

    public void setSqcYn(String sqcYn) {
        this.sqcYn = sqcYn;
    }

    public String getSqcGb() {
        return sqcGb;
    }

    public void setSqcGb(String sqcGb) {
        this.sqcGb = sqcGb;
    }

    public String getSetYn() {
        return setYn;
    }

    public void setSetYn(String setYn) {
        this.setYn = setYn;
    }

    public String getSetGoodsYn() {
        return setGoodsYn;
    }

    public void setSetGoodsYn(String setGoodsYn) {
        this.setGoodsYn = setGoodsYn;
    }

    public String getGiftYn() {
        return giftYn;
    }

    public void setGiftYn(String giftYn) {
        this.giftYn = giftYn;
    }

    public String getPayYn() {
        return payYn;
    }

    public void setPayYn(String payYn) {
        this.payYn = payYn;
    }

    public String getDmYn() {
        return dmYn;
    }

    public void setDmYn(String dmYn) {
        this.dmYn = dmYn;
    }

    public String getGiftReturnYn() {
        return giftReturnYn;
    }

    public void setGiftReturnYn(String giftReturnYn) {
        this.giftReturnYn = giftReturnYn;
    }

    public String getExchYn() {
        return exchYn;
    }

    public void setExchYn(String exchYn) {
        this.exchYn = exchYn;
    }

    public String getReturnYn() {
        return returnYn;
    }

    public void setReturnYn(String returnYn) {
        this.returnYn = returnYn;
    }

    public String getAsYn() {
        return asYn;
    }

    public void setAsYn(String asYn) {
        this.asYn = asYn;
    }

    public String getOutStockYn() {
        return outStockYn;
    }

    public void setOutStockYn(String outStockYn) {
        this.outStockYn = outStockYn;
    }

    public int getOrderMinQty() {
        return orderMinQty;
    }

    public void setOrderMinQty(int orderMinQty) {
        this.orderMinQty = orderMinQty;
    }

    public String getStockChkPlace() {
        return stockChkPlace;
    }

    public void setStockChkPlace(String stockChkPlace) {
        this.stockChkPlace = stockChkPlace;
    }

    public Date getFirstBroadDate() {
        return firstBroadDate;
    }

    public void setFirstBroadDate(Date firstBroadDate) {
        this.firstBroadDate = firstBroadDate;
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

    public String getArsName() {
        return arsName;
    }

    public void setArsName(String arsName) {
        this.arsName = arsName;
    }

    public String getParentGoodsCode() {
        return parentGoodsCode;
    }

    public void setParentGoodsCode(String parentGoodsCode) {
        this.parentGoodsCode = parentGoodsCode;
    }

    public String getBaseGoodsCode() {
        return baseGoodsCode;
    }

    public void setBaseGoodsCode(String baseGoodsCode) {
        this.baseGoodsCode = baseGoodsCode;
    }

    public String getNorestAllotYn() {
        return norestAllotYn;
    }

    public void setNorestAllotYn(String norestAllotYn) {
        this.norestAllotYn = norestAllotYn;
    }

    public String getNorestAllotMonths() {
        return norestAllotMonths;
    }

    public void setNorestAllotMonths(String norestAllotMonths) {
        this.norestAllotMonths = norestAllotMonths;
    }

    public String getCustDcYn() {
        return custDcYn;
    }

    public void setCustDcYn(String custDcYn) {
        this.custDcYn = custDcYn;
    }

    public String getSampleYn() {
        return sampleYn;
    }

    public void setSampleYn(String sampleYn) {
        this.sampleYn = sampleYn;
    }

    public Date getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public String getVVolume() {
        return vVolume;
    }

    public void setVVolume(String vVolume) {
        this.vVolume = vVolume;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getInUnit() {
        return inUnit;
    }

    public void setInUnit(String inUnit) {
        this.inUnit = inUnit;
    }

    public Integer getInUnitQty() {
        return inUnitQty;
    }

    public void setInUnitQty(Integer inUnitQty) {
        this.inUnitQty = inUnitQty;
    }

    public Integer getRecoCnt() {
        return recoCnt;
    }

    public void setRecoCnt(Integer recoCnt) {
        this.recoCnt = recoCnt;
    }

    public BigInteger getOrderRank() {
        return orderRank;
    }

    public void setOrderRank(BigInteger orderRank) {
        this.orderRank = orderRank;
    }

    public BigInteger getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(BigInteger commentCnt) {
        this.commentCnt = commentCnt;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public String getWhFixYn() {
        return whFixYn;
    }

    public void setWhFixYn(String whFixYn) {
        this.whFixYn = whFixYn;
    }

    public String getDirectShipYn() {
        return directShipYn;
    }

    public void setDirectShipYn(String directShipYn) {
        this.directShipYn = directShipYn;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    public String getAdvcRefundYn() {
        return advcRefundYn;
    }

    public void setAdvcRefundYn(String advcRefundYn) {
        this.advcRefundYn = advcRefundYn;
    }

    public String getDirectReturnYn() {
        return directReturnYn;
    }

    public void setDirectReturnYn(String directReturnYn) {
        this.directReturnYn = directReturnYn;
    }

    public String getShipManSeq() {
        return shipManSeq;
    }

    public void setShipManSeq(String shipManSeq) {
        this.shipManSeq = shipManSeq;
    }

    public String getReturnManSeq() {
        return returnManSeq;
    }

    public void setReturnManSeq(String returnManSeq) {
        this.returnManSeq = returnManSeq;
    }

    public String getOrderMakeYn() {
        return orderMakeYn;
    }

    public void setOrderMakeYn(String orderMakeYn) {
        this.orderMakeYn = orderMakeYn;
    }

    public String getInstallYn() {
        return installYn;
    }

    public void setInstallYn(String installYn) {
        this.installYn = installYn;
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

    public Short getDelyBoxQty() {
        return delyBoxQty;
    }

    public void setDelyBoxQty(Short delyBoxQty) {
        this.delyBoxQty = delyBoxQty;
    }

    public String getDirectYn() {
        return directYn;
    }

    public void setDirectYn(String directYn) {
        this.directYn = directYn;
    }

    public String getEntpReturnYn() {
        return entpReturnYn;
    }

    public void setEntpReturnYn(String entpReturnYn) {
        this.entpReturnYn = entpReturnYn;
    }

    public Short getWmsWidth() {
        return wmsWidth;
    }

    public void setWmsWidth(Short wmsWidth) {
        this.wmsWidth = wmsWidth;
    }

    public Short getWmsLength() {
        return wmsLength;
    }

    public void setWmsLength(Short wmsLength) {
        this.wmsLength = wmsLength;
    }

    public Short getWmsHeight() {
        return wmsHeight;
    }

    public void setWmsHeight(Short wmsHeight) {
        this.wmsHeight = wmsHeight;
    }

    public BigDecimal getWmsVolume() {
        return wmsVolume;
    }

    public void setWmsVolume(BigDecimal wmsVolume) {
        this.wmsVolume = wmsVolume;
    }

    public String getFreeDeliveryYn() {
        return freeDeliveryYn;
    }

    public void setFreeDeliveryYn(String freeDeliveryYn) {
        this.freeDeliveryYn = freeDeliveryYn;
    }

    public String getExtended() {
        return extended;
    }

    public void setExtended(String extended) {
        this.extended = extended;
    }

    public Long getWarrantyPeriode() {
        return warrantyPeriode;
    }

    public void setWarrantyPeriode(Long warrantyPeriode) {
        this.warrantyPeriode = warrantyPeriode;
    }

    public String getExPromoYn() {
        return exPromoYn;
    }

    public void setExPromoYn(String exPromoYn) {
        this.exPromoYn = exPromoYn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (goodsCode != null ? goodsCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tgoods)) {
            return false;
        }
        Tgoods other = (Tgoods) object;
        if ((this.goodsCode == null && other.goodsCode != null) || (this.goodsCode != null && !this.goodsCode.equals(other.goodsCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dartmedia.felino.Tgoods[ goodsCode=" + goodsCode + " ]";
    }
    
}
