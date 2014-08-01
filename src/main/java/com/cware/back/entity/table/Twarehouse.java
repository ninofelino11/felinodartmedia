package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 창고 기본정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Twarehouse extends BaseEntity {

    public Twarehouse(){ super();}

    private String wh_code;
    private String wh_name;
    private String wh_real_yn;
    private String stock_check_yn;
    private String wh_kind_flag;
    private String wh_post;
    private String wh_post_seq;
    private String wh_addr;
    private String wh_ddd;
    private String wh_tel1;
    private String wh_tel2;
    private String wh_tel3;
    private String wh_fax_ddd;
    private String wh_fax1;
    private String wh_fax2;
    private String wh_scale;
    private String wh_person;
    private String wh_note;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setWh_code       ( String wh_code )       { this.wh_code        = wh_code; }
    public void setWh_name       ( String wh_name )       { this.wh_name        = wh_name; }
    public void setWh_real_yn    ( String wh_real_yn )    { this.wh_real_yn     = wh_real_yn; }
    public void setStock_check_yn( String stock_check_yn ){ this.stock_check_yn = stock_check_yn; }
    public void setWh_kind_flag  ( String wh_kind_flag )  { this.wh_kind_flag   = wh_kind_flag; }
    public void setWh_post       ( String wh_post )       { this.wh_post        = wh_post; }
    public void setWh_post_seq   ( String wh_post_seq )   { this.wh_post_seq    = wh_post_seq; }
    public void setWh_addr       ( String wh_addr )       { this.wh_addr        = wh_addr; }
    public void setWh_ddd        ( String wh_ddd )        { this.wh_ddd         = wh_ddd; }
    public void setWh_tel1       ( String wh_tel1 )       { this.wh_tel1        = wh_tel1; }
    public void setWh_tel2       ( String wh_tel2 )       { this.wh_tel2        = wh_tel2; }
    public void setWh_tel3       ( String wh_tel3 )       { this.wh_tel3        = wh_tel3; }
    public void setWh_fax_ddd    ( String wh_fax_ddd )    { this.wh_fax_ddd     = wh_fax_ddd; }
    public void setWh_fax1       ( String wh_fax1 )       { this.wh_fax1        = wh_fax1; }
    public void setWh_fax2       ( String wh_fax2 )       { this.wh_fax2        = wh_fax2; }
    public void setWh_scale      ( String wh_scale )      { this.wh_scale       = wh_scale; }
    public void setWh_person     ( String wh_person )     { this.wh_person      = wh_person; }
    public void setWh_note       ( String wh_note )       { this.wh_note        = wh_note; }
    public void setInsert_date   ( String insert_date )   { this.insert_date    = insert_date; }
    public void setInsert_id     ( String insert_id )     { this.insert_id      = insert_id; }
    public void setModify_date   ( String modify_date )   { this.modify_date    = modify_date; }
    public void setModify_id     ( String modify_id )     { this.modify_id      = modify_id; }

    /** Get Method **/
    public String getWh_code       (){ return wh_code; }
    public String getWh_name       (){ return wh_name; }
    public String getWh_real_yn    (){ return wh_real_yn     == null ? "0" : wh_real_yn; }
    public String getStock_check_yn(){ return stock_check_yn == null ? "0" : stock_check_yn; }
    public String getWh_kind_flag  (){ return wh_kind_flag; }
    public String getWh_post       (){ return wh_post; }
    public String getWh_post_seq   (){ return wh_post_seq; }
    public String getWh_addr       (){ return wh_addr; }
    public String getWh_ddd        (){ return wh_ddd; }
    public String getWh_tel1       (){ return wh_tel1; }
    public String getWh_tel2       (){ return wh_tel2; }
    public String getWh_tel3       (){ return wh_tel3; }
    public String getWh_fax_ddd    (){ return wh_fax_ddd; }
    public String getWh_fax1       (){ return wh_fax1; }
    public String getWh_fax2       (){ return wh_fax2; }
    public String getWh_scale      (){ return wh_scale; }
    public String getWh_person     (){ return wh_person; }
    public String getWh_note       (){ return wh_note; }
    public String getInsert_date   (){ return insert_date; }
    public String getInsert_id     (){ return insert_id; }
    public String getModify_date   (){ return modify_date; }
    public String getModify_id     (){ return modify_id; }


} 