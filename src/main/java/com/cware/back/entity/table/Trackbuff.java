
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Trackbuff 정보
*
* @version 1.0, 2006/07/25
* @author  commerceware.co.kr
*/
public class Trackbuff extends BaseEntity {

    public Trackbuff(){ super();}

    private String rack_code;
    private String goods_code;
    private String goodsdt_code;
    private String job_date;
    private String proc_id;
    private String wh_code;
    private String job_gb;
    private long   inout_qty;        //
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setRack_code( String rack_code ){ this.rack_code = rack_code; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setJob_date( String job_date ){ this.job_date = job_date; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setJob_gb( String job_gb ){ this.job_gb = job_gb; }
    public void setInout_qty( long inout_qty ){ this.inout_qty = inout_qty; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    
    public String getRack_code(){ return rack_code; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getJob_date(){ return job_date; }
    public String getProc_id(){ return proc_id; }
    public String getWh_code(){ return wh_code; }
    public String getJob_gb(){ return job_gb; }
    public long   getInout_qty(){ return inout_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    
}