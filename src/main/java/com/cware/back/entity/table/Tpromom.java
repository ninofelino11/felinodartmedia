
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로모션 기본정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpromom extends BaseEntity {

    public Tpromom(){ super();}

    private String promo_no;
    private String promo_name;
    private String coupon_yn;
    private String app_type;
    private String do_type;
    private String promo_bdate;
    private String promo_edate;
    private String order_media_all_yn;
    private String order_media;
    private String media_code_all_yn;
    private String media_code;
    private String limit_yn;
    private long   limit_qty;
    private String goods_all_yn;
    private String gross_net_flag;
    private double app_amt;
    private String amt_rate_flag;
    private double do_rate;
    private double do_amt;
    private String select_yn;
    private long   select_qty;
    private String coupon_promo_no;
    private String lottery_promo_no;
    private String use_code;
    private String remark;
    private String first_order_yn;
    private String memb_gb_all_yn;
    private String memb_gb;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    private String promo_bdate_org;
    private String coupon_use_fix_yn;
    private int    valid_days;
    private int    coupon_use_day;
    public int getCoupon_use_day() {
		return coupon_use_day;
	}
	public void setCoupon_use_day(int couponUseDay) {
		coupon_use_day = couponUseDay;
	}

	private String card_all_yn;
    private String card_code;
    private String card_dc_falg;
    private String norest_allot_months;


	public String getPromo_no() {
		return promo_no;
	}
	public String getPromo_name() {
		return promo_name;
	}
	public String getCoupon_yn() {
		return coupon_yn;
	}
	public String getApp_type() {
		return app_type;
	}
	public String getDo_type() {
		return do_type;
	}
	public String getPromo_bdate() {
		return promo_bdate;
	}
	public String getPromo_edate() {
		return promo_edate;
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
	public String getLimit_yn() {
		return limit_yn;
	}
	public long getLimit_qty() {
		return limit_qty;
	}
	public String getGoods_all_yn() {
		return goods_all_yn;
	}
	public String getGross_net_flag() {
		return gross_net_flag;
	}
	public double getApp_amt() {
		return app_amt;
	}
	public String getAmt_rate_flag() {
		return amt_rate_flag;
	}
	public double getDo_rate() {
		return do_rate;
	}
	public double getDo_amt() {
		return do_amt;
	}
	public String getSelect_yn() {
		return select_yn;
	}
	public long getSelect_qty() {
		return select_qty;
	}
	public String getCoupon_promo_no() {
		return coupon_promo_no;
	}
	public String getLottery_promo_no() {
		return lottery_promo_no;
	}
	public String getUse_code() {
		return use_code;
	}
	public String getRemark() {
		return remark;
	}
	public String getFirst_order_yn() {
		return first_order_yn;
	}
	public String getMemb_gb_all_yn() {
		return memb_gb_all_yn;
	}
	public String getMemb_gb() {
		return memb_gb;
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
	public String getPromo_bdate_org() {
		return promo_bdate_org;
	}
	public String getCoupon_use_fix_yn() {
		return coupon_use_fix_yn;
	}
	public int getValid_days() {
		return valid_days;
	}
	public String getCard_all_yn() {
		return card_all_yn;
	}
	public String getCard_code() {
		return card_code;
	}
	public String getCard_dc_falg() {
		return card_dc_falg;
	}
	public String getNorest_allot_months() {
		return norest_allot_months;
	}
	public void setPromo_no(String promoNo) {
		promo_no = promoNo;
	}
	public void setPromo_name(String promoName) {
		promo_name = promoName;
	}
	public void setCoupon_yn(String couponYn) {
		coupon_yn = couponYn;
	}
	public void setApp_type(String appType) {
		app_type = appType;
	}
	public void setDo_type(String doType) {
		do_type = doType;
	}
	public void setPromo_bdate(String promoBdate) {
		promo_bdate = promoBdate;
	}
	public void setPromo_edate(String promoEdate) {
		promo_edate = promoEdate;
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
	public void setLimit_yn(String limitYn) {
		limit_yn = limitYn;
	}
	public void setLimit_qty(long limitQty) {
		limit_qty = limitQty;
	}
	public void setGoods_all_yn(String goodsAllYn) {
		goods_all_yn = goodsAllYn;
	}
	public void setGross_net_flag(String grossNetFlag) {
		gross_net_flag = grossNetFlag;
	}
	public void setApp_amt(double appAmt) {
		app_amt = appAmt;
	}
	public void setAmt_rate_flag(String amtRateFlag) {
		amt_rate_flag = amtRateFlag;
	}
	public void setDo_rate(double doRate) {
		do_rate = doRate;
	}
	public void setDo_amt(double doAmt) {
		do_amt = doAmt;
	}
	public void setSelect_yn(String selectYn) {
		select_yn = selectYn;
	}
	public void setSelect_qty(long selectQty) {
		select_qty = selectQty;
	}
	public void setCoupon_promo_no(String couponPromoNo) {
		coupon_promo_no = couponPromoNo;
	}
	public void setLottery_promo_no(String lotteryPromoNo) {
		lottery_promo_no = lotteryPromoNo;
	}
	public void setUse_code(String useCode) {
		use_code = useCode;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setFirst_order_yn(String firstOrderYn) {
		first_order_yn = firstOrderYn;
	}
	public void setMemb_gb_all_yn(String membGbAllYn) {
		memb_gb_all_yn = membGbAllYn;
	}
	public void setMemb_gb(String membGb) {
		memb_gb = membGb;
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
	public void setPromo_bdate_org(String promoBdateOrg) {
		promo_bdate_org = promoBdateOrg;
	}
	public void setCoupon_use_fix_yn(String couponUseFixYn) {
		coupon_use_fix_yn = couponUseFixYn;
	}
	public void setValid_days(int validDays) {
		valid_days = validDays;
	}
	public void setCard_all_yn(String cardAllYn) {
		card_all_yn = cardAllYn;
	}
	public void setCard_code(String cardCode) {
		card_code = cardCode;
	}
	public void setCard_dc_falg(String cardDcFalg) {
		card_dc_falg = cardDcFalg;
	}
	public void setNorest_allot_months(String norestAllotMonths) {
		norest_allot_months = norestAllotMonths;
	}



}