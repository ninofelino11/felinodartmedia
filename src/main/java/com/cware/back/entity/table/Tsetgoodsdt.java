
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SET단품상세
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsetgoodsdt extends BaseEntity {

    public Tsetgoodsdt(){ super();}
    
    private String goods_code;
    private String in_goods_code;
    private String in_goodsdt_code;
    private String in_goods_seq;
    private String in_goodsdt_seq;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setIn_goods_code( String in_goods_code ){ this.in_goods_code = in_goods_code; }
    public void setIn_goodsdt_code( String in_goodsdt_code ){ this.in_goodsdt_code = in_goodsdt_code; }
    public void setIn_goods_seq( String in_goods_seq ){ this.in_goods_seq = in_goods_seq; }
    public void setIn_goodsdt_seq( String in_goodsdt_seq ){ this.in_goodsdt_seq = in_goodsdt_seq; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getIn_goods_code(){ return in_goods_code; }
    public String getIn_goodsdt_code(){ return in_goodsdt_code; }
    public String getIn_goods_seq(){ return in_goods_seq; }
    public String getIn_goodsdt_seq(){ return in_goodsdt_seq; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 