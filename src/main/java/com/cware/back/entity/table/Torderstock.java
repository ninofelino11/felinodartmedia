
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문재고정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Torderstock extends BaseEntity {

public Torderstock(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private long   order_qty;
    private long   out_plan_qty;
    private long   broad_qty;
    private long   tot_sale_qty;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
    /* add 2007.01.24 */
    private String wh_code;
    
    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setOrder_qty( long order_qty ){ this.order_qty = order_qty; }
    public void setOut_plan_qty( long out_plan_qty ){ this.out_plan_qty = out_plan_qty; }
    public void setBroad_qty( long broad_qty ){ this.broad_qty = broad_qty; }
    public void setTot_sale_qty( long tot_sale_qty ){ this.tot_sale_qty = tot_sale_qty; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    
    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public long   getOrder_qty(){ return order_qty; }
    public long   getOut_plan_qty(){ return out_plan_qty; }
    public long   getBroad_qty(){ return broad_qty; }
    public long   getTot_sale_qty(){ return tot_sale_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

    public String getWh_code(){ return wh_code; }
} 