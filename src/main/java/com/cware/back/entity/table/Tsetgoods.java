
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SET상품
*
* @version 1.0, 2006/07/27
* @author Commerceware Ins.
*/
public class Tsetgoods extends BaseEntity {

    public Tsetgoods(){ super();}

    private String goods_code;
    private String in_goods_seq;
    private String in_goods_code;
    private double sale_price;
    private long   set_qty;
    private String goodsdt_fix_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setIn_goods_seq( String in_goods_seq ){ this.in_goods_seq = in_goods_seq; }
    public void setIn_goods_code( String in_goods_code ){ this.in_goods_code = in_goods_code; }
    public void setSale_price( double sale_price ){ this.sale_price = sale_price; }
    public void setSet_qty( long set_qty ){ this.set_qty = set_qty; }
    public void setGoodsdt_fix_yn( String goodsdt_fix_yn ){ this.goodsdt_fix_yn = goodsdt_fix_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getIn_goods_seq(){ return in_goods_seq; }
    public String getIn_goods_code(){ return in_goods_code; }
    public double getSale_price(){ return sale_price; }
    public long   getSet_qty(){ return set_qty; }
    public String getGoodsdt_fix_yn(){ return goodsdt_fix_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
    
} 