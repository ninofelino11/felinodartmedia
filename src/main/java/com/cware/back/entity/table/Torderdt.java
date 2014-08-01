
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문 Detail
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Torderdt extends BaseEntity implements Comparable {

    public Torderdt(){ super();}

    //= compare
    private Torderdt od = null;
    public int compareTo(Object o){
        int  rtn = 0;
    	od = (Torderdt)o;
    	rtn = (this.getGoods_code()+this.getGoodsdt_code()).compareTo(od.getGoods_code()+od.getGoodsdt_code());
    	return rtn;
    }

    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String cust_no;
    private String receiver_seq;
    private String order_date;
    private String order_gb;
    private String do_flag;
    private String goods_gb;
    private String media_gb;
    private String media_code;
    private String goods_code;
    private String goodsdt_code;
    private String goodsdt_info;
    private double sale_price;
    private long   order_qty;
    private long   syscancel;
    private long   syslast;
    private double order_amt;
    private double dc_rate;
    private double dc_amt;
    private double dc_amt_goods; //OldField : dc_goods
    private double dc_amt_memb;  //NewField
    private double dc_amt_div;   //OldField : dc_sum
    private double rsale_amt;
    private double syslast_dc;
    private double syslast_dc_goods; //OldField : syslast_goods
    private double syslast_dc_memb;  //NewField
    private double syslast_dc_div;   //OldField : syslast_sum
    private double dc_rate_goods;    //NewField
    private double syslast_amt;
    private double vat_rate;
    private double syslast_net;
    private double syslast_vat;
    private String md_code;
    private String wh_code;
    private String sale_yn;
    private String dely_type;
    private String dely_gb;
    private String cust_dely_flag;
    private String first_plan_date;
    private String out_plan_date;
    private String stock_flag;
    private String stock_key;
    private String dely_hope_date;
    private String dely_hope_yn;
    private String dely_hope_time;
    private String preout_gb;
    private String goods_select_no;
    private String single_due_gb;
    private String pack_yn;
    private String anony_yn;
    private String msg;
    private String msg_note;
    private String happy_card_yn;
    private double saveamt;
    private String saveamt_gb;
    private String last_proc_date;
    private String promo_no1;
    private String promo_no2;
    private String set_goods_code;
    private String set_md_code;
    private String receipt_yn;
    private String slip_yn;
    private String entpslip_no;
    private String remark1_v;
    private String remark2_v;
    private long   remark3_n;
    private long   remark4_n;
    private String remark5_v;
    private String remark6_v;
    private String last_proc_id;
    /** add propertis **/
	private String do_flag_org;
    private String receiver_seq_org;
    private double dc_amt_card;
    private double syslast_dc_card;
	public Torderdt getOd() {
		return od;
	}
	public void setOd(Torderdt od) {
		this.od = od;
	}
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
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_gb() {
		return order_gb;
	}
	public void setOrder_gb(String order_gb) {
		this.order_gb = order_gb;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
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
	public long getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(long order_qty) {
		this.order_qty = order_qty;
	}
	public long getSyscancel() {
		return syscancel;
	}
	public void setSyscancel(long syscancel) {
		this.syscancel = syscancel;
	}
	public long getSyslast() {
		return syslast;
	}
	public void setSyslast(long syslast) {
		this.syslast = syslast;
	}
	public double getOrder_amt() {
		return order_amt;
	}
	public void setOrder_amt(double order_amt) {
		this.order_amt = order_amt;
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
	public double getRsale_amt() {
		return rsale_amt;
	}
	public void setRsale_amt(double rsale_amt) {
		this.rsale_amt = rsale_amt;
	}
	public double getSyslast_dc() {
		return syslast_dc;
	}
	public void setSyslast_dc(double syslast_dc) {
		this.syslast_dc = syslast_dc;
	}
	public double getSyslast_dc_goods() {
		return syslast_dc_goods;
	}
	public void setSyslast_dc_goods(double syslast_dc_goods) {
		this.syslast_dc_goods = syslast_dc_goods;
	}
	public double getSyslast_dc_memb() {
		return syslast_dc_memb;
	}
	public void setSyslast_dc_memb(double syslast_dc_memb) {
		this.syslast_dc_memb = syslast_dc_memb;
	}
	public double getSyslast_dc_div() {
		return syslast_dc_div;
	}
	public void setSyslast_dc_div(double syslast_dc_div) {
		this.syslast_dc_div = syslast_dc_div;
	}
	public double getDc_rate_goods() {
		return dc_rate_goods;
	}
	public void setDc_rate_goods(double dc_rate_goods) {
		this.dc_rate_goods = dc_rate_goods;
	}
	public double getSyslast_amt() {
		return syslast_amt;
	}
	public void setSyslast_amt(double syslast_amt) {
		this.syslast_amt = syslast_amt;
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
	public String getWh_code() {
		return wh_code;
	}
	public void setWh_code(String wh_code) {
		this.wh_code = wh_code;
	}
	public String getSale_yn() {
		return sale_yn;
	}
	public void setSale_yn(String sale_yn) {
		this.sale_yn = sale_yn;
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
	public String getCust_dely_flag() {
		return cust_dely_flag;
	}
	public void setCust_dely_flag(String cust_dely_flag) {
		this.cust_dely_flag = cust_dely_flag;
	}
	public String getFirst_plan_date() {
		return first_plan_date;
	}
	public void setFirst_plan_date(String first_plan_date) {
		this.first_plan_date = first_plan_date;
	}
	public String getOut_plan_date() {
		return out_plan_date;
	}
	public void setOut_plan_date(String out_plan_date) {
		this.out_plan_date = out_plan_date;
	}
	public String getStock_flag() {
		return stock_flag;
	}
	public void setStock_flag(String stock_flag) {
		this.stock_flag = stock_flag;
	}
	public String getStock_key() {
		return stock_key;
	}
	public void setStock_key(String stock_key) {
		this.stock_key = stock_key;
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
	public String getGoods_select_no() {
		return goods_select_no;
	}
	public void setGoods_select_no(String goods_select_no) {
		this.goods_select_no = goods_select_no;
	}
	public String getSingle_due_gb() {
		return single_due_gb;
	}
	public void setSingle_due_gb(String single_due_gb) {
		this.single_due_gb = single_due_gb;
	}
	public String getPack_yn() {
		return pack_yn;
	}
	public void setPack_yn(String pack_yn) {
		this.pack_yn = pack_yn;
	}
	public String getAnony_yn() {
		return anony_yn;
	}
	public void setAnony_yn(String anony_yn) {
		this.anony_yn = anony_yn;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg_note() {
		return msg_note;
	}
	public void setMsg_note(String msg_note) {
		this.msg_note = msg_note;
	}
	public String getHappy_card_yn() {
		return happy_card_yn;
	}
	public void setHappy_card_yn(String happy_card_yn) {
		this.happy_card_yn = happy_card_yn;
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
	public String getReceipt_yn() {
		return receipt_yn;
	}
	public void setReceipt_yn(String receipt_yn) {
		this.receipt_yn = receipt_yn;
	}
	public String getSlip_yn() {
		return slip_yn;
	}
	public void setSlip_yn(String slip_yn) {
		this.slip_yn = slip_yn;
	}
	public String getEntpslip_no() {
		return entpslip_no;
	}
	public void setEntpslip_no(String entpslip_no) {
		this.entpslip_no = entpslip_no;
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
	public String getDo_flag_org() {
		return do_flag_org;
	}
	public void setDo_flag_org(String do_flag_org) {
		this.do_flag_org = do_flag_org;
	}
	public String getReceiver_seq_org() {
		return receiver_seq_org;
	}
	public void setReceiver_seq_org(String receiver_seq_org) {
		this.receiver_seq_org = receiver_seq_org;
	}
	public double getSyslast_net() {
		return syslast_net;
	}
	public void setSyslast_net(double syslast_net) {
		this.syslast_net = syslast_net;
	}
	public double getSyslast_vat() {
		return syslast_vat;
	}
	public void setSyslast_vat(double syslast_vat) {
		this.syslast_vat = syslast_vat;
	}
	public double getDc_amt_card() {
		return dc_amt_card;
	}
	public void setDc_amt_card(double dc_amt_card) {
		this.dc_amt_card = dc_amt_card;
	}
	public double getSyslast_dc_card() {
		return syslast_dc_card;
	}
	public void setSyslast_dc_card(double syslast_dc_card) {
		this.syslast_dc_card = syslast_dc_card;
	}
}