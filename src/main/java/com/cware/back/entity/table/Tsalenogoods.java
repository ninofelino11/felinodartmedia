
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 판매불가상품정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsalenogoods extends BaseEntity {

public Tsalenogoods(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private String sale_no_seq;
    private String sale_gb;
    private String sale_no_code;
    private String sale_no_note;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setSale_no_seq( String sale_no_seq ){ this.sale_no_seq = sale_no_seq; }
    public void setSale_gb( String sale_gb ){ this.sale_gb = sale_gb; }
    public void setSale_no_code( String sale_no_code ){ this.sale_no_code = sale_no_code; }
    public void setSale_no_note( String sale_no_note ){ this.sale_no_note = sale_no_note; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getSale_no_seq(){ return sale_no_seq; }
    public String getSale_gb(){ return sale_gb; }
    public String getSale_no_code(){ return sale_no_code; }
    public String getSale_no_note(){ return sale_no_note; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

} 