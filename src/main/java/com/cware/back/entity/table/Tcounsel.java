
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문상담
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcounsel extends BaseEntity {

    public Tcounsel(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String goods_select_no;
    private long   counsel_qty;
    private String insert_date;
    private String insert_id;
    
    private String wh_code;  /* add 2007.01.25 */

    private String media_gb;  //= PKG11 방송 관련 상담 수량 계산 목적 컬럼 추가 - 20110328 by kst  
    private String media_code;    
    
    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setOrder_d_seq( String order_d_seq ){ this.order_d_seq = order_d_seq; }
    public void setOrder_w_seq( String order_w_seq ){ this.order_w_seq = order_w_seq; }
    public void setGoods_select_no( String goods_select_no ){ this.goods_select_no = goods_select_no; }
    public void setCounsel_qty( long   counsel_qty ){ this.counsel_qty = counsel_qty; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getOrder_d_seq(){ return order_d_seq; }
    public String getOrder_w_seq(){ return order_w_seq; }
    public String getGoods_select_no(){ return goods_select_no; }
    public long   getCounsel_qty(){ return counsel_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

    public String getWh_code(){ return wh_code; }
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

} 