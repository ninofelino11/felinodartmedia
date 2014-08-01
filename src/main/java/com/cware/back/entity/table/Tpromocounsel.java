
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로모션 상담 기본정보
*
* @version 1.0, 2006/07/21
* @author Commerceware Ins.
*/
public class Tpromocounsel extends BaseEntity {

    public Tpromocounsel(){ super();}

    private String promo_no;
    private String order_no;
    private String order_g_seq;
    private String goods_select_no;
    private long   counsel_qty;
    private String insert_date;
    private String insert_id;

    private long   limit_qty;
    private String do_type;
    private String bigo;

    /** Set Method **/
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setGoods_select_no( String goods_select_no ){ this.goods_select_no = goods_select_no; }
    public void setCounsel_qty( long counsel_qty ){ this.counsel_qty = counsel_qty; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    public void setLimit_qty( long limit_qty ){ this.limit_qty = limit_qty; }
    public void setDo_type( String do_type ){ this.do_type = do_type; }
    public void setBigo( String bigo ){ this.bigo = bigo; }
    
    /** Get Method **/
    public String getPromo_no(){ return promo_no; }
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getGoods_select_no(){ return goods_select_no; }
    public long   getCounsel_qty(){ return counsel_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

    public long   getLimit_qty(){ return limit_qty; }
    public String getDo_type(){ return do_type; }
    public String getBigo(){ return bigo; }

} 