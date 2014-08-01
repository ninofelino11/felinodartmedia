package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 브랜드정보
*
* @version 1.0, 2006/08/12
* @author Commerceware Ins.
*/
public class Tbrand extends BaseEntity {

public Tbrand(){ super();}

    private String brand_code;
    private String brand_name;
    private String brand_image;
    private String brand_desc;
    private String url;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setBrand_code( String brand_code ){ this.brand_code = brand_code; }
    public void setBrand_name( String brand_name ){ this.brand_name = brand_name; }
    public void setBrand_image( String brand_image ){ this.brand_image = brand_image; }
    public void setBrand_desc( String brand_desc ){ this.brand_desc = brand_desc; }
    public void setUrl( String url ){ this.url = url; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getBrand_code(){ return brand_code; }
    public String getBrand_name(){ return brand_name; }
    public String getBrand_image(){ return brand_image; }
    public String getBrand_desc(){ return brand_desc; }
    public String getUrl(){ return url; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

} 