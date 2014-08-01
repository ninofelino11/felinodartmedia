
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 추첨 프로모션 기본정보
*
* @version 1.0, 2006/11/10
* @author Commerceware Ins.
*/
public class Tlotterypromom extends BaseEntity {

    public Tlotterypromom(){ super();}

    private String lottery_promo_no;
    private String lottery_promo_name;
    private String lottery_promo_bdate;
    private String lottery_promo_edate;
    private String do_type;
    private String limit_yn;
    private String limit_qty;
    private String confirm_cnt;
    private String provide_cnt;
    private String order_media_all_yn;
    private String order_media;
    private String media_code_all_yn;
    private String media_code;
    private String use_code;
    private String end_yn;
    private String end_date;
    private String promo_note;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String parent_lottery_promo_no;

    private String lottery_promo_bdate_org;
    
    private String coupon_promo_no;
    private String goods_code;
    private String goodsdt_code;
    private String own_cost;
    private String valid_days;
    private String Entp_cost;
    private String entp_code;
    private String tax_rcv_flag;
    private String do_amt;
    
    
	public String getLottery_promo_no() {
		return lottery_promo_no;
	}
	public String getLottery_promo_name() {
		return lottery_promo_name;
	}
	public String getLottery_promo_bdate() {
		return lottery_promo_bdate;
	}
	public String getLottery_promo_edate() {
		return lottery_promo_edate;
	}
	public String getDo_type() {
		return do_type;
	}
	public String getLimit_yn() {
		return limit_yn;
	}
	public String getLimit_qty() {
		return limit_qty;
	}
	public String getConfirm_cnt() {
		return confirm_cnt;
	}
	public String getProvide_cnt() {
		return provide_cnt;
	}
	public String getOrder_media_all_yn() {
		return order_media_all_yn;
	}
	public String getOrder_media() {
		return order_media;
	}
	public String getMedia_code_all_yn() {
		return media_code_all_yn;
	}
	public String getMedia_code() {
		return media_code;
	}
	public String getUse_code() {
		return use_code;
	}
	public String getEnd_yn() {
		return end_yn;
	}
	public String getEnd_date() {
		return end_date;
	}
	public String getPromo_note() {
		return promo_note;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getModify_date() {
		return modify_date;
	}
	public String getModify_id() {
		return modify_id;
	}
	public String getParent_lottery_promo_no() {
		return parent_lottery_promo_no;
	}
	public String getLottery_promo_bdate_org() {
		return lottery_promo_bdate_org;
	}
	public String getCoupon_promo_no() {
		return coupon_promo_no;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public String getOwn_cost() {
		return own_cost;
	}
	public String getValid_days() {
		return valid_days;
	}
	public String getEntp_cost() {
		return Entp_cost;
	}
	public String getEntp_code() {
		return entp_code;
	}
	public String getTax_rcv_flag() {
		return tax_rcv_flag;
	}
	public String getDo_amt() {
		return do_amt;
	}
	public void setLottery_promo_no(String lotteryPromoNo) {
		lottery_promo_no = lotteryPromoNo;
	}
	public void setLottery_promo_name(String lotteryPromoName) {
		lottery_promo_name = lotteryPromoName;
	}
	public void setLottery_promo_bdate(String lotteryPromoBdate) {
		lottery_promo_bdate = lotteryPromoBdate;
	}
	public void setLottery_promo_edate(String lotteryPromoEdate) {
		lottery_promo_edate = lotteryPromoEdate;
	}
	public void setDo_type(String doType) {
		do_type = doType;
	}
	public void setLimit_yn(String limitYn) {
		limit_yn = limitYn;
	}
	public void setLimit_qty(String limitQty) {
		limit_qty = limitQty;
	}
	public void setConfirm_cnt(String confirmCnt) {
		confirm_cnt = confirmCnt;
	}
	public void setProvide_cnt(String provideCnt) {
		provide_cnt = provideCnt;
	}
	public void setOrder_media_all_yn(String orderMediaAllYn) {
		order_media_all_yn = orderMediaAllYn;
	}
	public void setOrder_media(String orderMedia) {
		order_media = orderMedia;
	}
	public void setMedia_code_all_yn(String mediaCodeAllYn) {
		media_code_all_yn = mediaCodeAllYn;
	}
	public void setMedia_code(String mediaCode) {
		media_code = mediaCode;
	}
	public void setUse_code(String useCode) {
		use_code = useCode;
	}
	public void setEnd_yn(String endYn) {
		end_yn = endYn;
	}
	public void setEnd_date(String endDate) {
		end_date = endDate;
	}
	public void setPromo_note(String promoNote) {
		promo_note = promoNote;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}
	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}
	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}
	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}
	public void setParent_lottery_promo_no(String parentLotteryPromoNo) {
		parent_lottery_promo_no = parentLotteryPromoNo;
	}
	public void setLottery_promo_bdate_org(String lotteryPromoBdateOrg) {
		lottery_promo_bdate_org = lotteryPromoBdateOrg;
	}
	public void setCoupon_promo_no(String couponPromoNo) {
		coupon_promo_no = couponPromoNo;
	}
	public void setGoods_code(String goodsCode) {
		goods_code = goodsCode;
	}
	public void setGoodsdt_code(String goodsdtCode) {
		goodsdt_code = goodsdtCode;
	}
	public void setOwn_cost(String ownCost) {
		own_cost = ownCost;
	}
	public void setValid_days(String validDays) {
		valid_days = validDays;
	}
	public void setEntp_cost(String entpCost) {
		Entp_cost = entpCost;
	}
	public void setEntp_code(String entpCode) {
		entp_code = entpCode;
	}
	public void setTax_rcv_flag(String taxRcvFlag) {
		tax_rcv_flag = taxRcvFlag;
	}
	public void setDo_amt(String doAmt) {
		do_amt = doAmt;
	}

}