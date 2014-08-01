
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 입고정보DT
*
* @version 1.0, 2006/08/07
* @author Commerceware Ins.
*/
public class Tindt extends BaseEntity {

public Tindt(){ super();}

    private String in_no;
    private String goods_code;
    private String goodsdt_code;
    private long   in_qty;
    private String lot_no;
    private long   alloc_qty;

    /** Set Method **/
    public void setIn_no( String in_no ){ this.in_no = in_no; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setIn_qty( long   in_qty ){ this.in_qty = in_qty; }
    public void setLot_no(String lot_no) { this.lot_no = lot_no; }
    public void setAlloc_qty(long alloc_qty) { this.alloc_qty = alloc_qty; }
    
    /** Get Method **/
    public String getIn_no(){ return in_no; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public long   getIn_qty(){ return in_qty; }
    public String getLot_no() { return lot_no; }
    public long getAlloc_qty() { return alloc_qty; }
    
    

} 