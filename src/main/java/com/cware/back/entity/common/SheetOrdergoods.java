
package com.cware.back.entity.common;

import com.cware.back.common.BaseEntity;

/**
* 주문 및 CS 처리시에 주문상품 Sheet 정보
*
* @version 1.0, 2006/07/22
* @author Commerceware Ins.
*/
public class SheetOrdergoods extends BaseEntity {

    public SheetOrdergoods(){ super();}

    private String goods_name;
    private String goods_code;
    private String comp_goods_code;
    private double sale_price;
    private String set_yn;
    private String entp_code;
    private String order_no;
    private String order_g_seq;
    private String cust_no;
    private String order_date;
    private String order_gb;
    private String promo_no;
    private long   order_qty;
    private long   cancel_qty;
    private long   return_qty;
    private long   exch_cnt;
    private long   as_cnt;
    private double dc_rate;
    private double dc_amt_goods;
    private double dc_amt_memb;
    private double dc_amt_div;
    private double dc_amt;
    private String media_gb;
    private String media_code;
    private String dely_hope_yn;
    private String anony_yn;
    private String cod_yn;
    private long   limit_gu;
    private double buy_cost;
    private String receiver_seq;
    private String norest_allot_months;

    /** Set Method **/
    public void setGoods_name( String goods_name ){ this.goods_name = goods_name; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setComp_goods_code( String comp_goods_code ){ this.comp_goods_code = comp_goods_code; }
    public void setSale_price( double sale_price ){ this.sale_price = sale_price; }
    public void setSet_yn( String set_yn ){ this.set_yn = set_yn; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setOrder_date( String order_date ){ this.order_date = order_date; }
    public void setOrder_gb( String order_gb ){ this.order_gb = order_gb; }
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setOrder_qty( long order_qty ){ this.order_qty = order_qty; }
    public void setCancel_qty( long cancel_qty ){ this.cancel_qty = cancel_qty; }
    public void setReturn_qty( long return_qty ){ this.return_qty = return_qty; }
    public void setExch_cnt( long exch_cnt ){ this.exch_cnt = exch_cnt; }
    public void setAs_cnt( long as_cnt ){ this.as_cnt = as_cnt; }
    public void setDc_rate( double dc_rate ){ this.dc_rate = dc_rate; }
    public void setDc_amt_goods( double dc_amt_goods ){ this.dc_amt_goods = dc_amt_goods; }
    public void setDc_amt_memb( double dc_amt_memb ){ this.dc_amt_memb = dc_amt_memb; }
    public void setDc_amt_div( double dc_amt_div ){ this.dc_amt_div = dc_amt_div; }
    public void setDc_amt( double dc_amt ){ this.dc_amt = dc_amt; }
    public void setMedia_gb( String media_gb ){ this.media_gb = media_gb; }
    public void setMedia_code( String media_code ){ this.media_code = media_code; }
    public void setDely_hope_yn( String dely_hope_yn ){ this.dely_hope_yn = dely_hope_yn; }
    public void setAnony_yn( String anony_yn ){ this.anony_yn = anony_yn; }
    public void setCod_yn( String cod_yn ){ this.cod_yn = cod_yn; }
    public void setLimit_gu( long limit_gu ){ this.limit_gu = limit_gu; }
    public void setBuy_cost( double buy_cost ){ this.buy_cost = buy_cost; }
    public void setReceiver_seq( String receiver_seq ){ this.receiver_seq = receiver_seq; }
    public void setNorest_allot_months( String norest_allot_months ){ this.norest_allot_months = norest_allot_months; }

    public void initSheetOrdergoods(){
        setCwareAction          ( "" );
        setCwareInfo            ( "" );
        this.goods_name          =  "";
        this.goods_code          =  "";
        this.comp_goods_code     =  "";
        this.sale_price          =  0;
        this.set_yn              =  "0";
        this.entp_code           =  "";
        this.order_no            =  "";
        this.order_g_seq         =  "";
        this.cust_no             =  "";
        this.order_date          =  "";
        this.order_gb            =  "10";
        this.promo_no            =  "";
        this.order_qty           =  0;
        this.cancel_qty          =  0;
        this.return_qty          =  0;
        this.exch_cnt            =  0;
        this.as_cnt              =  0;
        this.dc_rate             =  0;
        this.dc_amt_goods        =  0;
        this.dc_amt_memb         =  0;
        this.dc_amt_div          =  0;
        this.dc_amt              =  0;
        this.media_gb            =  "01";
        this.media_code          =  "000";
        this.dely_hope_yn        =  "0";
        this.anony_yn            =  "0";
        this.cod_yn              =  "0";
        this.limit_gu            =  0;
        this.buy_cost            =  0;
        this.receiver_seq        =  "";
        this.norest_allot_months =  "";
    }


    /** Get Method **/
    public String getGoods_name(){ return goods_name; }
    public String getGoods_code(){ return goods_code; }
    public String getComp_goods_code(){ return comp_goods_code; }
    public double getSale_price(){ return sale_price; }
    public String getSet_yn(){ return set_yn; }
    public String getEntp_code(){ return entp_code; }
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getCust_no(){ return cust_no; }
    public String getOrder_date(){ return order_date; }
    public String getOrder_gb(){ return order_gb; }
    public String getPromo_no(){ return promo_no; }
    public long   getOrder_qty(){ return order_qty; }
    public long   getCancel_qty(){ return cancel_qty; }
    public long   getReturn_qty(){ return return_qty; }
    public long   getExch_cnt(){ return exch_cnt; }
    public long   getAs_cnt(){ return as_cnt; }
    public double getDc_rate(){ return dc_rate; }
    public double getDc_amt_goods(){ return dc_amt_goods; }
    public double getDc_amt_memb(){ return dc_amt_memb; }
    public double getDc_amt_div(){ return dc_amt_div; }
    public double getDc_amt(){ return dc_amt; }
    public String getMedia_gb(){ return media_gb; }
    public String getMedia_code(){ return media_code; }
    public String getDely_hope_yn(){ return dely_hope_yn; }
    public String getAnony_yn(){ return anony_yn; }
    public String getCod_yn(){ return cod_yn; }
    public long   getLimit_gu(){ return limit_gu; }
    public double getBuy_cost(){ return buy_cost; }
    public String getReceiver_seq(){ return receiver_seq; }
    public String getNorest_allot_months(){ return norest_allot_months; }


} 