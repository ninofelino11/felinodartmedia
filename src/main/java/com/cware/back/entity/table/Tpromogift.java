
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로모션 상세 정보2
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpromogift extends BaseEntity {

    public Tpromogift(){ super();}

    private String promo_no;
    private String promo_seq;
    private String gift_goods_code;
    private String gift_qty;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setPromo_seq( String promo_seq ){ this.promo_seq = promo_seq; }
    public void setGift_goods_code( String gift_goods_code ){ this.gift_goods_code = gift_goods_code; }
    public void setGift_qty( String gift_qty ){ this.gift_qty = gift_qty; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getPromo_no(){ return promo_no; }
    public String getPromo_seq(){ return promo_seq; }
    public String getGift_goods_code(){ return gift_goods_code; }
    public String getGift_qty(){ return gift_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

}