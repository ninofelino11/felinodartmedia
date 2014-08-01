
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 입고예정수량산정정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tinplanqty extends BaseEntity {

public Tinplanqty(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private String seq;
    private String start_date;
    private String end_date;
    private long   lead_time;
    private long   daily_capa_qty;
    private long   inplan_qty;
    private long   max_sale_qty;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setSeq( String seq ){ this.seq = seq; }
    public void setStart_date( String start_date ){ this.start_date = start_date; }
    public void setEnd_date( String end_date ){ this.end_date = end_date; }
    public void setLead_time( long   lead_time ){ this.lead_time = lead_time; }
    public void setDaily_capa_qty( long   daily_capa_qty ){ this.daily_capa_qty = daily_capa_qty; }
    public void setInplan_qty( long   inplan_qty ){ this.inplan_qty = inplan_qty; }
    public void setMax_sale_qty( long   max_sale_qty ){ this.max_sale_qty = max_sale_qty; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getSeq(){ return seq; }
    public String getStart_date(){ return start_date; }
    public String getEnd_date(){ return end_date; }
    public long   getLead_time(){ return lead_time; }
    public long   getDaily_capa_qty(){ return daily_capa_qty; }
    public long   getInplan_qty(){ return inplan_qty; }
    public long   getMax_sale_qty(){ return max_sale_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

}