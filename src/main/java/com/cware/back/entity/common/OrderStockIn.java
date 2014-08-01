
package com.cware.back.entity.common;

import com.cware.back.common.BaseEntity;

/**
* StockU
* 주문재고 검사 input beans
* 
* @version 1.0, 2006/07/21
* @author  commerceware.co.kr
*/
public class OrderStockIn extends BaseEntity {

    public OrderStockIn(){ super();}

    private long   row;
    private String selectFlag;
    private String goods_code;
    private String goodsdt_code;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String goods_select_no;
    private String insert_id;
    private long   counsel_qty;
    private String dely_type;
    private String stock_chk_place;
    private String dely_gb;
    private String post_no;
    private String post_seq;
    
    private String wh_code;  /* add 2007.01.25 */
    
    private String media_gb; //= PKG11 방송 관련 상담 수량 계산 목적 컬럼 추가 - 20110328 by kst
    private String media_code;

    /** Set Method **/
    public void setRow              ( long   row             ) { this.row             = row             ; } 
    public void setSelectFlag       ( String selectFlag      ) { this.selectFlag      = selectFlag      ; } 
    public void setGoods_code       ( String goods_code      ) { this.goods_code      = goods_code      ; } 
    public void setGoodsdt_code     ( String goodsdt_code    ) { this.goodsdt_code    = goodsdt_code    ; } 
    public void setOrder_no         ( String order_no        ) { this.order_no        = order_no        ; } 
    public void setOrder_g_seq      ( String order_g_seq     ) { this.order_g_seq     = order_g_seq     ; } 
    public void setOrder_d_seq      ( String order_d_seq     ) { this.order_d_seq     = order_d_seq     ; } 
    public void setOrder_w_seq      ( String order_w_seq     ) { this.order_w_seq     = order_w_seq     ; } 
    public void setGoods_select_no  ( String goods_select_no ) { this.goods_select_no = goods_select_no ; } 
    public void setInsert_id        ( String insert_id       ) { this.insert_id       = insert_id       ; } 
    public void setCounsel_qty      ( long   counsel_qty     ) { this.counsel_qty     = counsel_qty     ; } 
    public void setDely_type        ( String dely_type       ) { this.dely_type       = dely_type       ; } 
    public void setStock_chk_place  ( String stock_chk_place ) { this.stock_chk_place = stock_chk_place ; } 
    public void setDely_gb          ( String dely_gb         ) { this.dely_gb         = dely_gb         ; } 
    public void setPost_no          ( String post_no         ) { this.post_no         = post_no         ; } 
    public void setPost_seq         ( String post_seq        ) { this.post_seq        = post_seq        ; } 

    public void setWh_code          ( String wh_code         ) { this.wh_code         = wh_code         ; } 

    /** Get Method **/
    public long   getRow              () { return row             ; } 
    public String getSelectFlag       () { return selectFlag      ; } 
    public String getGoods_code       () { return goods_code      ; } 
    public String getGoodsdt_code     () { return goodsdt_code    ; } 
    public String getOrder_no         () { return order_no        ; } 
    public String getOrder_g_seq      () { return order_g_seq     ; } 
    public String getOrder_d_seq      () { return order_d_seq     ; } 
    public String getOrder_w_seq      () { return order_w_seq     ; } 
    public String getGoods_select_no  () { return goods_select_no ; } 
    public String getInsert_id        () { return insert_id       ; } 
    public long   getCounsel_qty      () { return counsel_qty     ; } 
    public String getDely_type        () { return dely_type       ; } 
    public String getStock_chk_place  () { return stock_chk_place ; } 
    public String getDely_gb          () { return dely_gb         ; } 
    public String getPost_no          () { return post_no         ; } 
    public String getPost_seq         () { return post_seq        ; }
    
    public String getWh_code          () { return wh_code         ; }
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
