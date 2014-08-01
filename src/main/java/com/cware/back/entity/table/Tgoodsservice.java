
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품서비스정보 
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tgoodsservice extends BaseEntity {

public Tgoodsservice(){ super();}

    private String goods_code;
    private String as_term;
    private String as_dely_charge;
    private String as_receive_type;
    private String as_dely_type;
    private String as_com1;
    private String as_com2;
    private String as_repair_term;
    private String as_repair_amt;
    private String as_note;
    private String entp_code;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setAs_term( String as_term ){ this.as_term = as_term; }
    public void setAs_dely_charge( String as_dely_charge ){ this.as_dely_charge = as_dely_charge; }
    public void setAs_receive_type( String as_receive_type ){ this.as_receive_type = as_receive_type; }
    public void setAs_dely_type( String as_dely_type ){ this.as_dely_type = as_dely_type; }
    public void setAs_com1( String as_com1 ){ this.as_com1 = as_com1; }
    public void setAs_com2( String as_com2 ){ this.as_com2 = as_com2; }
    public void setAs_repair_term( String as_repair_term ){ this.as_repair_term = as_repair_term; }
    public void setAs_repair_amt( String as_repair_amt ){ this.as_repair_amt = as_repair_amt; }
    public void setAs_note( String as_note ){ this.as_note = as_note; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getAs_term(){ return as_term; }
    public String getAs_dely_charge(){ return as_dely_charge; }
    public String getAs_receive_type(){ return as_receive_type; }
    public String getAs_dely_type(){ return as_dely_type; }
    public String getAs_com1(){ return as_com1; }
    public String getAs_com2(){ return as_com2; }
    public String getAs_repair_term(){ return as_repair_term; }
    public String getAs_repair_amt(){ return as_repair_amt; }
    public String getAs_note(){ return as_note; }
    public String getEntp_code(){ return entp_code; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

} 