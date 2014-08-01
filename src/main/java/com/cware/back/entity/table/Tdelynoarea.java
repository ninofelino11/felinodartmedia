package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 배송불가정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tdelynoarea extends BaseEntity {

public Tdelynoarea(){ super();}

    private String goods_code;
    private String area_gb;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setArea_gb( String area_gb ){ this.area_gb = area_gb; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getArea_gb(){ return area_gb; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

} 