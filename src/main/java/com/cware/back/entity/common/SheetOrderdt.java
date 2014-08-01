
package com.cware.back.entity.common;

import com.cware.back.common.BaseEntity;

/**
* 주문 및 CS 처리시에 주문상품상세 Sheet 정보
*
* @version 1.0, 2006/07/22
* @author Commerceware Ins.
*/
public class SheetOrderdt extends BaseEntity {

    public SheetOrderdt(){ super();}

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
    private double   sale_price;
    private long   order_qty;
    private long   syscancel;
    private long   syslast;
    private double order_amt;
    private double dc_rate_goods;
    private double dc_rate;
    private double dc_amt_goods;
    private double dc_amt_memb;
    private double dc_amt_div;
    private double dc_amt;
    private double rsale_amt;
    private double vat_rate;
    private String md_code;
    private String wh_code;
    private String sale_yn;
    private String dely_type;
    private String dely_gb;
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
    private String goods_name;
    private String first_plan_date;
    private String stock_chk_place;
    private String post_no;
    private String post_seq;
    private String cust_dely_flag;
    private String post_yn;
    private String promo_no1;
    private String promo_no2;
    private String org_dely_hope_date;
    private String area_dely_hope_date;
    private String ormake_yn;
    private String set_goods_code;
    private String set_md_code;
    private double saveamt_rate;
    private double buy_cost;
    private String order_code;
    private double coupon_saveamt;

    /** Set Method **/
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setOrder_d_seq( String order_d_seq ){ this.order_d_seq = order_d_seq; }
    public void setOrder_w_seq( String order_w_seq ){ this.order_w_seq = order_w_seq; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setReceiver_seq( String receiver_seq ){ this.receiver_seq = receiver_seq; }
    public void setOrder_date( String order_date ){ this.order_date = order_date; }
    public void setOrder_gb( String order_gb ){ this.order_gb = order_gb; }
    public void setDo_flag( String do_flag ){ this.do_flag = do_flag; }
    public void setGoods_gb( String goods_gb ){ this.goods_gb = goods_gb; }
    public void setMedia_gb( String media_gb ){ this.media_gb = media_gb; }
    public void setMedia_code( String media_code ){ this.media_code = media_code; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setGoodsdt_info( String goodsdt_info ){ this.goodsdt_info = goodsdt_info; }
    public void setSale_price( double sale_price ){ this.sale_price = sale_price; }
    public void setOrder_qty( long   order_qty ){ this.order_qty = order_qty; }
    public void setSyscancel( long   syscancel ){ this.syscancel = syscancel; }
    public void setSyslast( long   syslast ){ this.syslast = syslast; }
    public void setOrder_amt( double order_amt ){ this.order_amt = order_amt; }
    public void setDc_rate_goods( double dc_rate_goods ){ this.dc_rate_goods = dc_rate_goods; }
    public void setDc_rate( double dc_rate ){ this.dc_rate = dc_rate; }
    public void setDc_amt_goods( double dc_amt_goods ){ this.dc_amt_goods = dc_amt_goods; }
    public void setDc_amt_memb( double dc_amt_memb ){ this.dc_amt_memb = dc_amt_memb; }
    public void setDc_amt_div( double dc_amt_div ){ this.dc_amt_div = dc_amt_div; }
    public void setDc_amt( double dc_amt ){ this.dc_amt = dc_amt; }
    public void setRsale_amt( double rsale_amt ){ this.rsale_amt = rsale_amt; }
    public void setVat_rate( double vat_rate ){ this.vat_rate = vat_rate; }
    public void setMd_code( String md_code ){ this.md_code = md_code; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setSale_yn( String sale_yn ){ this.sale_yn = sale_yn; }
    public void setDely_type( String dely_type ){ this.dely_type = dely_type; }
    public void setDely_gb( String dely_gb ){ this.dely_gb = dely_gb; }
    public void setOut_plan_date( String out_plan_date ){ this.out_plan_date = out_plan_date; }
    public void setStock_flag( String stock_flag ){ this.stock_flag = stock_flag; }
    public void setStock_key( String stock_key ){ this.stock_key = stock_key; }
    public void setDely_hope_date( String dely_hope_date ){ this.dely_hope_date = dely_hope_date; }
    public void setDely_hope_yn( String dely_hope_yn ){ this.dely_hope_yn = dely_hope_yn; }
    public void setDely_hope_time( String dely_hope_time ){ this.dely_hope_time = dely_hope_time; }
    public void setPreout_gb( String preout_gb ){ this.preout_gb = preout_gb; }
    public void setGoods_select_no( String goods_select_no ){ this.goods_select_no = goods_select_no; }
    public void setSingle_due_gb( String single_due_gb ){ this.single_due_gb = single_due_gb; }
    public void setPack_yn( String pack_yn ){ this.pack_yn = pack_yn; }
    public void setAnony_yn( String anony_yn ){ this.anony_yn = anony_yn; }
    public void setMsg( String msg ){ this.msg = msg; }
    public void setMsg_note( String msg_note ){ this.msg_note = msg_note; }
    public void setHappy_card_yn( String happy_card_yn ){ this.happy_card_yn = happy_card_yn; }
    public void setSaveamt( double saveamt ){ this.saveamt = saveamt; }
    public void setSaveamt_gb( String saveamt_gb ){ this.saveamt_gb = saveamt_gb; }
    public void setLast_proc_date( String last_proc_date ){ this.last_proc_date = last_proc_date; }
    public void setGoods_name( String goods_name ){ this.goods_name = goods_name; }
    public void setFirst_plan_date( String first_plan_date ){ this.first_plan_date = first_plan_date; }
    public void setStock_chk_place( String stock_chk_place ){ this.stock_chk_place = stock_chk_place; }
    public void setPost_no( String post_no ){ this.post_no = post_no; }
    public void setPost_seq( String post_seq ){ this.post_seq = post_seq; }
    public void setCust_dely_flag( String cust_dely_flag ){ this.cust_dely_flag = cust_dely_flag; }
    public void setPost_yn( String post_yn ){ this.post_yn = post_yn; }
    public void setPromo_no1( String promo_no1 ){ this.promo_no1 = promo_no1; }
    public void setPromo_no2( String promo_no2 ){ this.promo_no2 = promo_no2; }
    public void setOrg_dely_hope_date( String org_dely_hope_date ){ this.org_dely_hope_date = org_dely_hope_date; }
    public void setArea_dely_hope_date( String area_dely_hope_date ){ this.area_dely_hope_date = area_dely_hope_date; }
    public void setOrmake_yn( String ormake_yn ){ this.ormake_yn = ormake_yn; }
    public void setSet_goods_code( String set_goods_code ){ this.set_goods_code = set_goods_code; }
    public void setSet_md_code( String set_md_code ){ this.set_md_code = set_md_code; }
    public void setSaveamt_rate( double saveamt_rate ){ this.saveamt_rate = saveamt_rate; }
    public void setBuy_cost( double buy_cost ){ this.buy_cost = buy_cost; }
    public void setOrder_code( String order_code ){ this.order_code = order_code; }
    public void setCoupon_Saveamt( double coupon_saveamt ){ this.coupon_saveamt = coupon_saveamt; }


    public void initSheetOrderdt(){
        setCwareAction          ( "" );
        setCwareInfo            ( "" );
        this.order_no            =  "";
        this.order_g_seq         =  "";
        this.order_d_seq         =  "";
        this.order_w_seq         =  "";
        this.cust_no             =  "";
        this.receiver_seq        =  "";
        this.order_date          =  "";
        this.order_gb            =  "";
        this.do_flag             =  "";
        this.goods_gb            =  "";
        this.media_gb            =  "";
        this.media_code          =  "";
        this.goods_code          =  "";
        this.goodsdt_code        =  "";
        this.goodsdt_info        =  "";
        this.sale_price          =  0;
        this.order_qty           =  0;
        this.syscancel           =  0;
        this.syslast             =  0;
        this.order_amt           =  0;
        this.dc_rate_goods       =  0;
        this.dc_rate             =  0;
        this.dc_amt_goods        =  0;
        this.dc_amt_memb         =  0;
        this.dc_amt_div          =  0;
        this.dc_amt              =  0;
        this.rsale_amt           =  0;
        this.vat_rate            =  0;
        this.md_code             =  "";
        this.wh_code             =  "";
        this.sale_yn             =  "";
        this.dely_type           =  "";
        this.dely_gb             =  "";
        this.out_plan_date       =  "";
        this.stock_flag          =  "";
        this.stock_key           =  "";
        this.dely_hope_date      =  "";
        this.dely_hope_yn        =  "";
        this.dely_hope_time      =  "";
        this.preout_gb           =  "";
        this.goods_select_no     =  "";
        this.single_due_gb       =  "";
        this.pack_yn             =  "";
        this.anony_yn            =  "";
        this.msg                 =  "";
        this.msg_note            =  "";
        this.happy_card_yn       =  "0";
        this.saveamt             =  0;
        this.saveamt_gb          =  "90";
        this.last_proc_date      =  "";
        this.goods_name          =  "";
        this.first_plan_date     =  "";
        this.stock_chk_place     =  "";
        this.post_no             =  "";
        this.post_seq            =  "";
        this.cust_dely_flag      =  "0";
        this.post_yn             =  "";
        this.promo_no1           =  "";
        this.promo_no2           =  "";
        this.org_dely_hope_date  =  "";
        this.area_dely_hope_date =  "";
        this.ormake_yn           =  "";
        this.set_goods_code      =  "";
        this.set_md_code         =  "";
        this.saveamt_rate        =  0;
        this.buy_cost            =  0;
        this.order_code          =  "00";
        this.coupon_saveamt 	 =  0;
    }

    /** Get Method **/
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getOrder_d_seq(){ return order_d_seq; }
    public String getOrder_w_seq(){ return order_w_seq; }
    public String getCust_no(){ return cust_no; }
    public String getReceiver_seq(){ return receiver_seq; }
    public String getOrder_date(){ return order_date; }
    public String getOrder_gb(){ return order_gb; }
    public String getDo_flag(){ return do_flag; }
    public String getGoods_gb(){ return goods_gb; }
    public String getMedia_gb(){ return media_gb; }
    public String getMedia_code(){ return media_code; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getGoodsdt_info(){ return goodsdt_info; }
    public double getSale_price(){ return sale_price; }
    public long   getOrder_qty(){ return order_qty; }
    public long   getSyscancel(){ return syscancel; }
    public long   getSyslast(){ return syslast; }
    public double getOrder_amt(){ return order_amt; }
    public double getDc_rate_goods(){ return dc_rate_goods; }
    public double getDc_rate(){ return dc_rate; }
    public double getDc_amt_goods(){ return dc_amt_goods; }
    public double getDc_amt_memb(){ return dc_amt_memb; }
    public double getDc_amt_div(){ return dc_amt_div; }
    public double getDc_amt(){ return dc_amt; }
    public double getRsale_amt(){ return rsale_amt; }
    public double getVat_rate(){ return vat_rate; }
    public String getMd_code(){ return md_code; }
    public String getWh_code(){ return wh_code; }
    public String getSale_yn(){ return sale_yn; }
    public String getDely_type(){ return dely_type; }
    public String getDely_gb(){ return dely_gb; }
    public String getOut_plan_date(){ return out_plan_date; }
    public String getStock_flag(){ return stock_flag; }
    public String getStock_key(){ return stock_key; }
    public String getDely_hope_date(){ return dely_hope_date; }
    public String getDely_hope_yn(){ return dely_hope_yn; }
    public String getDely_hope_time(){ return dely_hope_time; }
    public String getPreout_gb(){ return preout_gb; }
    public String getGoods_select_no(){ return goods_select_no; }
    public String getSingle_due_gb(){ return single_due_gb; }
    public String getPack_yn(){ return pack_yn; }
    public String getAnony_yn(){ return anony_yn; }
    public String getMsg(){ return msg; }
    public String getMsg_note(){ return msg_note; }
    public String getHappy_card_yn(){ return happy_card_yn; }
    public double getSaveamt(){ return saveamt; }
    public String getSaveamt_gb(){ return saveamt_gb; }
    public String getLast_proc_date(){ return last_proc_date; }
    public String getGoods_name(){ return goods_name; }
    public String getFirst_plan_date(){ return first_plan_date; }
    public String getStock_chk_place(){ return stock_chk_place; }
    public String getPost_no(){ return post_no; }
    public String getPost_seq(){ return post_seq; }
    public String getCust_dely_flag(){ return cust_dely_flag; }
    public String getPost_yn(){ return post_yn; }
    public String getPromo_no1(){ return promo_no1; }
    public String getPromo_no2(){ return promo_no2; }
    public String getOrg_dely_hope_date(){ return org_dely_hope_date; }
    public String getArea_dely_hope_date(){ return area_dely_hope_date; }
    public String getOrmake_yn(){ return ormake_yn; }
    public String getSet_goods_code(){ return set_goods_code; }
    public String getSet_md_code(){ return set_md_code; }
    public double getSaveamt_rate(){ return saveamt_rate; }
    public double getBuy_cost(){ return buy_cost; }
    public String getOrder_code(){ return order_code; }
    public double getCoupon_Saveamt(){ return coupon_saveamt; }

 

}