package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 추첨프로모션 사은품 상품정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpromogoodsdt extends BaseEntity {

public Tpromogoodsdt(){ super();}

    private String promo_no;
    private String promo_seq;
    private String promo_goods_code;
    private String promo_goodsdt_code;
    private String promo_qty;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setPromo_seq( String promo_seq ){ this.promo_seq = promo_seq; }
    public void setPromo_goods_code( String promo_goods_code ){ this.promo_goods_code = promo_goods_code; }
    public void setPromo_goodsdt_code( String promo_goodsdt_code ){ this.promo_goodsdt_code = promo_goodsdt_code; }
    public void setPromo_qty( String promo_qty ){ this.promo_qty = promo_qty; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getPromo_no(){ return promo_no; }
    public String getPromo_seq(){ return promo_seq; }
    public String getPromo_goods_code(){ return promo_goods_code; }
    public String getPromo_goodsdt_code(){ return promo_goodsdt_code; }
    public String getPromo_qty(){ return promo_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

} 