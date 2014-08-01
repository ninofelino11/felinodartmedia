
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문 프로모션 내역
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Torderpromo extends BaseEntity {

    public Torderpromo(){ super();}

    private String seq;
    private String promo_no;
    private String do_type;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String cancel_yn;
    private String cancel_date;
    private String cancel_id;
    private String remark;
    private String insert_date;
    private String insert_id;

    private String coupon_promo_bdate;
    private String coupon_promo_edate;
    private String coupon_use_fix_yn;
    private int coupon_use_day; //= PKG11 쿠폰 유효기간 체크 변경 - 20110328 by kst
    private int valid_days; //= PKG11 적립금 프로모션의 유효기간 세팅 변경 - 20110328 by kst    
    
    /** Set Method **/
    public void setSeq( String seq ){ this.seq = seq; }
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setDo_type( String do_type ){ this.do_type = do_type; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setOrder_d_seq( String order_d_seq ){ this.order_d_seq = order_d_seq; }
    public void setOrder_w_seq( String order_w_seq ){ this.order_w_seq = order_w_seq; }
	public void setCancel_yn(String cancel_yn) { this.cancel_yn = cancel_yn; }
	public void setCancel_date( String cancel_date ) { this.cancel_date = cancel_date; }
	public void setCancel_id( String cancel_id ) { this.cancel_id = cancel_id; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getSeq(){ return seq; }
    public String getPromo_no(){ return promo_no; }
    public String getDo_type(){ return do_type; }
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getOrder_d_seq(){ return order_d_seq; }
    public String getOrder_w_seq(){ return order_w_seq; }
	public String getCancel_yn() { return cancel_yn; }
	public String getCancel_date() { return cancel_date; }
	public String getCancel_id() { return cancel_id; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

    /* extra property */
    private String cust_no;
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public String getCust_no(){ return cust_no; }

    private String lotterypromo_no;
    public void setLotterypromo_no( String lotterypromo_no ){ this.lotterypromo_no = lotterypromo_no; }
    public String getLotterypromo_no(){ return lotterypromo_no; }

    private String coupon_promo_no;
    public void setCoupon_promo_no( String coupon_promo_no ){ this.coupon_promo_no = coupon_promo_no; }
    public String getCoupon_promo_no(){ return coupon_promo_no; }

    private String app_type;
    public void setApp_type( String app_type ){ this.app_type = app_type; }
    public String getApp_type(){ return app_type; }

    private double amount;
    public void setAmount( double amount ){ this.amount = amount; }
    public double getAmount(){ return amount; }

    private long cancel_qty;
    public void setCancel_qty( long cancel_qty ){ this.cancel_qty = cancel_qty; }
    public long getCancel_qty(){ return cancel_qty; }

    private String coupon_yn;
    public void setCoupon_yn( String coupon_yn ){ this.coupon_yn = coupon_yn; }
    public String getCoupon_yn(){ return coupon_yn; }

    private String use_yn;
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public String getUse_yn(){ return use_yn; }

    private String coupon_seq;
    public void setCoupon_seq( String coupon_seq ){ this.coupon_seq = coupon_seq; }
    public String getCoupon_seq(){ return coupon_seq; }
    
    private String promo_name;
    public String getPromo_name() {		return promo_name;	}
	public void setPromo_name(String promo_name) {		this.promo_name = promo_name;	}
	
	private String coupon_lottery_promo_name;
	public String getCoupon_lottery_promo_name() {		return coupon_lottery_promo_name;	}
	public void setCoupon_lottery_promo_name(String coupon_lottery_promo_name) {		this.coupon_lottery_promo_name = coupon_lottery_promo_name;	}
	
	private String master_selecter;
	public String getMaster_selecter() {		return master_selecter;	}
	public void setMaster_selecter(String master_selecter) {		this.master_selecter = master_selecter;	}
	public String getCoupon_promo_bdate() {
		return coupon_promo_bdate;
	}
	public void setCoupon_promo_bdate(String coupon_promo_bdate) {
		this.coupon_promo_bdate = coupon_promo_bdate;
	}
	public String getCoupon_promo_edate() {
		return coupon_promo_edate;
	}
	public void setCoupon_promo_edate(String coupon_promo_edate) {
		this.coupon_promo_edate = coupon_promo_edate;
	}
	public String getCoupon_use_fix_yn() {
		return coupon_use_fix_yn;
	}
	public void setCoupon_use_fix_yn(String coupon_use_fix_yn) {
		this.coupon_use_fix_yn = coupon_use_fix_yn;
	}
	public int getCoupon_use_day() {
		return coupon_use_day;
	}
	public void setCoupon_use_day(int coupon_use_day) {
		this.coupon_use_day = coupon_use_day;
	}
	public int getValid_days() {
		return valid_days;
	}
	public void setValid_days(int valid_days) {
		this.valid_days = valid_days;
	}
	
	
} 