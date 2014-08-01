package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 테입상품정보
*
* @version 1.0, 2009/01/22
* @author Commerceware Ins.
*/
public class Tpgmtapegoods extends BaseEntity {

public Tpgmtapegoods(){ super();}

    private String tape_code;
    private String goods_code;
    private String goods_code_org;
    private String special_yn;
    private String guest;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setTape_code( String tape_code ){ this.tape_code = tape_code; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoods_code_org( String goods_code_org ){ this.goods_code_org = goods_code_org; }
    public void setSpecial_yn( String special_yn ){ this.special_yn = special_yn; }
    public void setGuest( String guest ){ this.guest = guest; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getTape_code(){ return tape_code; }
    public String getGoods_code(){ return goods_code; }
    public String getGoods_code_org(){ return goods_code_org; }
    public String getSpecial_yn(){ return special_yn; }
    public String getGuest(){ return guest; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

}