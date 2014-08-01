
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 취소상세
*
* @version 1.0, 2006/08/08
* @author Commerceware Ins.
*/
public class Tcanceldt extends BaseEntity {

public Tcanceldt(){ super();}

    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String cust_no;
    private String receiver_seq;
    private String cancel_date;
    private String cancel_gb;
    private String do_flag;
    private String cancel_code;
    private String goods_gb;
    private String media_gb;
    private String media_code;
    private String goods_code;
    private String goodsdt_code;
    private String goodsdt_info;
    private double sale_price;
    private long   cancel_qty;
    private double cancel_amt;
    private double dc_rate;
    private double dc_amt;
    private double dc_amt_goods;  //OldField : dc_goods
    private double dc_amt_memb;   //NewField
    private double dc_amt_div;    //OldField : dc_sum
    private double dc_rate_goods; //NewField
    private double rsale_amt;
    private double vat_rate;
    private String md_code;
    private String sale_yn;
    private String wh_code;
    private String dely_type;
    private String dely_gb;
    private String dely_hope_date;
    private String dely_hope_yn;
    private String dely_hope_time;
    private String preout_gb;
    private String single_due_gb;
    private double   saveamt;
    private String saveamt_gb;
    private String last_proc_date;
    private String promo_no1;
    private String promo_no2;
    private String set_goods_code;
    private String set_md_code;
    private String remark1_v;
    private String remark2_v;
    private long   remark3_n;
    private long   remark4_n;
    private String remark5_v;
    private String remark6_v;
    private String last_proc_id;
    private double dc_amt_card;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOrder_g_seq() {
		return order_g_seq;
	}
	public void setOrder_g_seq(String order_g_seq) {
		this.order_g_seq = order_g_seq;
	}
	public String getOrder_d_seq() {
		return order_d_seq;
	}
	public void setOrder_d_seq(String order_d_seq) {
		this.order_d_seq = order_d_seq;
	}
	public String getOrder_w_seq() {
		return order_w_seq;
	}
	public void setOrder_w_seq(String order_w_seq) {
		this.order_w_seq = order_w_seq;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getReceiver_seq() {
		return receiver_seq;
	}
	public void setReceiver_seq(String receiver_seq) {
		this.receiver_seq = receiver_seq;
	}
	public String getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(String cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getCancel_gb() {
		return cancel_gb;
	}
	public void setCancel_gb(String cancel_gb) {
		this.cancel_gb = cancel_gb;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
	}
	public String getCancel_code() {
		return cancel_code;
	}
	public void setCancel_code(String cancel_code) {
		this.cancel_code = cancel_code;
	}
	public String getGoods_gb() {
		return goods_gb;
	}
	public void setGoods_gb(String goods_gb) {
		this.goods_gb = goods_gb;
	}
	public String getMedia_gb() {
		return media_gb;
	}
	public void setMedia_gb(String media_gb) {
		this.media_gb = media_gb;
	}
	public String getMedia_code() {
		return media_code;
	}
	public void setMedia_code(String media_code) {
		this.media_code = media_code;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public void setGoodsdt_code(String goodsdt_code) {
		this.goodsdt_code = goodsdt_code;
	}
	public String getGoodsdt_info() {
		return goodsdt_info;
	}
	public void setGoodsdt_info(String goodsdt_info) {
		this.goodsdt_info = goodsdt_info;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public long getCancel_qty() {
		return cancel_qty;
	}
	public void setCancel_qty(long cancel_qty) {
		this.cancel_qty = cancel_qty;
	}
	public double getCancel_amt() {
		return cancel_amt;
	}
	public void setCancel_amt(double cancel_amt) {
		this.cancel_amt = cancel_amt;
	}
	public double getDc_rate() {
		return dc_rate;
	}
	public void setDc_rate(double dc_rate) {
		this.dc_rate = dc_rate;
	}
	public double getDc_amt() {
		return dc_amt;
	}
	public void setDc_amt(double dc_amt) {
		this.dc_amt = dc_amt;
	}
	public double getDc_amt_goods() {
		return dc_amt_goods;
	}
	public void setDc_amt_goods(double dc_amt_goods) {
		this.dc_amt_goods = dc_amt_goods;
	}
	public double getDc_amt_memb() {
		return dc_amt_memb;
	}
	public void setDc_amt_memb(double dc_amt_memb) {
		this.dc_amt_memb = dc_amt_memb;
	}
	public double getDc_amt_div() {
		return dc_amt_div;
	}
	public void setDc_amt_div(double dc_amt_div) {
		this.dc_amt_div = dc_amt_div;
	}
	public double getDc_rate_goods() {
		return dc_rate_goods;
	}
	public void setDc_rate_goods(double dc_rate_goods) {
		this.dc_rate_goods = dc_rate_goods;
	}
	public double getRsale_amt() {
		return rsale_amt;
	}
	public void setRsale_amt(double rsale_amt) {
		this.rsale_amt = rsale_amt;
	}
	public double getVat_rate() {
		return vat_rate;
	}
	public void setVat_rate(double vat_rate) {
		this.vat_rate = vat_rate;
	}
	public String getMd_code() {
		return md_code;
	}
	public void setMd_code(String md_code) {
		this.md_code = md_code;
	}
	public String getSale_yn() {
		return sale_yn;
	}
	public void setSale_yn(String sale_yn) {
		this.sale_yn = sale_yn;
	}
	public String getWh_code() {
		return wh_code;
	}
	public void setWh_code(String wh_code) {
		this.wh_code = wh_code;
	}
	public String getDely_type() {
		return dely_type;
	}
	public void setDely_type(String dely_type) {
		this.dely_type = dely_type;
	}
	public String getDely_gb() {
		return dely_gb;
	}
	public void setDely_gb(String dely_gb) {
		this.dely_gb = dely_gb;
	}
	public String getDely_hope_date() {
		return dely_hope_date;
	}
	public void setDely_hope_date(String dely_hope_date) {
		this.dely_hope_date = dely_hope_date;
	}
	public String getDely_hope_yn() {
		return dely_hope_yn;
	}
	public void setDely_hope_yn(String dely_hope_yn) {
		this.dely_hope_yn = dely_hope_yn;
	}
	public String getDely_hope_time() {
		return dely_hope_time;
	}
	public void setDely_hope_time(String dely_hope_time) {
		this.dely_hope_time = dely_hope_time;
	}
	public String getPreout_gb() {
		return preout_gb;
	}
	public void setPreout_gb(String preout_gb) {
		this.preout_gb = preout_gb;
	}
	public String getSingle_due_gb() {
		return single_due_gb;
	}
	public void setSingle_due_gb(String single_due_gb) {
		this.single_due_gb = single_due_gb;
	}
	public double getSaveamt() {
		return saveamt;
	}
	public void setSaveamt(double saveamt) {
		this.saveamt = saveamt;
	}
	public String getSaveamt_gb() {
		return saveamt_gb;
	}
	public void setSaveamt_gb(String saveamt_gb) {
		this.saveamt_gb = saveamt_gb;
	}
	public String getLast_proc_date() {
		return last_proc_date;
	}
	public void setLast_proc_date(String last_proc_date) {
		this.last_proc_date = last_proc_date;
	}
	public String getPromo_no1() {
		return promo_no1;
	}
	public void setPromo_no1(String promo_no1) {
		this.promo_no1 = promo_no1;
	}
	public String getPromo_no2() {
		return promo_no2;
	}
	public void setPromo_no2(String promo_no2) {
		this.promo_no2 = promo_no2;
	}
	public String getSet_goods_code() {
		return set_goods_code;
	}
	public void setSet_goods_code(String set_goods_code) {
		this.set_goods_code = set_goods_code;
	}
	public String getSet_md_code() {
		return set_md_code;
	}
	public void setSet_md_code(String set_md_code) {
		this.set_md_code = set_md_code;
	}
	public String getRemark1_v() {
		return remark1_v;
	}
	public void setRemark1_v(String remark1_v) {
		this.remark1_v = remark1_v;
	}
	public String getRemark2_v() {
		return remark2_v;
	}
	public void setRemark2_v(String remark2_v) {
		this.remark2_v = remark2_v;
	}
	public long getRemark3_n() {
		return remark3_n;
	}
	public void setRemark3_n(long remark3_n) {
		this.remark3_n = remark3_n;
	}
	public long getRemark4_n() {
		return remark4_n;
	}
	public void setRemark4_n(long remark4_n) {
		this.remark4_n = remark4_n;
	}
	public String getRemark5_v() {
		return remark5_v;
	}
	public void setRemark5_v(String remark5_v) {
		this.remark5_v = remark5_v;
	}
	public String getRemark6_v() {
		return remark6_v;
	}
	public void setRemark6_v(String remark6_v) {
		this.remark6_v = remark6_v;
	}
	public String getLast_proc_id() {
		return last_proc_id;
	}
	public void setLast_proc_id(String last_proc_id) {
		this.last_proc_id = last_proc_id;
	}
	public double getDc_amt_card() {
		return dc_amt_card;
	}
	public void setDc_amt_card(double dc_amt_card) {
		this.dc_amt_card = dc_amt_card;
	}
} 