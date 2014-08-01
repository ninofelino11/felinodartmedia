
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문 Goods
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tordergoods extends BaseEntity {

    public Tordergoods(){ super();}

    private String order_no;
    private String order_g_seq;
    private String cust_no;
    private String order_date;
    private String order_gb;
    private String promo_no;
    private String set_yn;
    private String goods_code;
    private long   order_qty;
    private long   cancel_qty;
    private long   claim_qty;
    private long   return_qty;
    private long   claim_can_qty;
    private long   exch_cnt;
    private long   as_cnt;
    private double sale_price;
    private double dc_rate;
    private double dc_amt;
    private double dc_amt_goods; //OldField : dc_goods
    private double dc_amt_memb;  //NewField
    private double dc_amt_div;   //OldField : dc_sum
    private String norest_allot_months;
    private double dc_amt_card;

    /** Set Method **/
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setOrder_date( String order_date ){ this.order_date = order_date; }
    public void setOrder_gb( String order_gb ){ this.order_gb = order_gb; }
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setSet_yn( String set_yn ){ this.set_yn = set_yn; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setOrder_qty( long   order_qty ){ this.order_qty = order_qty; }
    public void setCancel_qty( long   cancel_qty ){ this.cancel_qty = cancel_qty; }
    public void setClaim_qty( long   claim_qty ){ this.claim_qty = claim_qty; }
    public void setReturn_qty( long   return_qty ){ this.return_qty = return_qty; }
    public void setClaim_can_qty( long   claim_can_qty ){ this.claim_can_qty = claim_can_qty; }
    public void setExch_cnt( long   exch_cnt ){ this.exch_cnt = exch_cnt; }
    public void setAs_cnt( long   as_cnt ){ this.as_cnt = as_cnt; }
    public void setSale_price( double sale_price ){ this.sale_price = sale_price; }
    public void setDc_rate( double dc_rate ){ this.dc_rate = dc_rate; }
    public void setDc_amt( double dc_amt ){ this.dc_amt = dc_amt; }
    
	
    public void setNorest_allot_months( String norest_allot_months ){ this.norest_allot_months = norest_allot_months; }
    

    /** Get Method **/
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getCust_no(){ return cust_no; }
    public String getOrder_date(){ return order_date; }
    public String getOrder_gb(){ return order_gb; }
    public String getPromo_no(){ return promo_no; }
    public String getSet_yn(){ return set_yn; }
    public String getGoods_code(){ return goods_code; }
    public long   getOrder_qty(){ return order_qty; }
    public long   getCancel_qty(){ return cancel_qty; }
    public long   getClaim_qty(){ return claim_qty; }
    public long   getReturn_qty(){ return return_qty; }
    public long   getClaim_can_qty(){ return claim_can_qty; }
    public long   getExch_cnt(){ return exch_cnt; }
    public long   getAs_cnt(){ return as_cnt; }
    public double getSale_price(){ return sale_price; }
    public double getDc_rate(){ return dc_rate; }
    public double getDc_amt(){ return dc_amt; }
    
	

    public String getNorest_allot_months(){ return norest_allot_months; }
	
    /* extra property */
    
    private long   cancel_qty_org;
    public  void   setCancel_qty_org( long   cancel_qty_org ){ this.cancel_qty_org = cancel_qty_org; }
    public  long   getCancel_qty_org(){ return cancel_qty_org; }

    private long   claim_qty_org;
    public  void   setClaim_qty_org( long   claim_qty_org ){ this.claim_qty_org = claim_qty_org; }
    public  long   getClaim_qty_org(){ return claim_qty_org; }

    private long   claim_can_qty_org;
    public  void   setClaim_can_qty_org( long   claim_can_qty_org ){ this.claim_can_qty_org = claim_can_qty_org; }
    public  long   getClaim_can_qty_org(){ return claim_can_qty_org; }

    private long   exch_cnt_org;
    public  void   setExch_cnt_org( long   exch_cnt_org ){ this.exch_cnt_org = exch_cnt_org; }
    public  long   getExch_cnt_org(){ return exch_cnt_org; }

    private long   as_cnt_org;
    public  void   setAs_cnt_org( long   as_cnt_org ){ this.as_cnt_org = as_cnt_org; }
    public  long   getAs_cnt_org(){ return as_cnt_org; }
	public double getDc_amt_div() {
		return dc_amt_div;
	}
	public void setDc_amt_div(double dc_amt_div) {
		this.dc_amt_div = dc_amt_div;
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
	public double getDc_amt_card() {
		return dc_amt_card;
	}
	public void setDc_amt_card(double dc_amt_card) {
		this.dc_amt_card = dc_amt_card;
	}
    

} 