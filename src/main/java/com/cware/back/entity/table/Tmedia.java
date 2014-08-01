package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Register Media
*
* @version 1.0, 2006/07/10
* @author Commerceware Ins.
*/

public class Tmedia extends BaseEntity {

    public Tmedia(){ super();}

    private String media_code;
    private String media_year;
    private String media_name;
    private String media_gb;
    private String media_style;
    private String media_month;
    private String use_yn;
    private String catalog_page;
    private String catalog_size;
    private String weight;
    private String paper_quality;
    private String print_med;
    private String valid_bdate;
    private String valid_edate;
    private String send_date;
    private String goods_cnt;
    private String product_cnt;
    private String send_note;
    private String special_note;
    private String goods_code;
    private String page_cost;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setMedia_code( String media_code )      { this.media_code    = media_code; }
    public void setMedia_year( String media_year )      { this.media_year    = media_year; }
    public void setMedia_name( String media_name )      { this.media_name    = media_name; }
    public void setMedia_gb( String media_gb )          { this.media_gb      = media_gb; }
    public void setMedia_style( String media_style )    { this.media_style   = media_style; }
    public void setMedia_month( String media_month )    { this.media_month   = media_month; }
    public void setUse_yn( String use_yn )              { this.use_yn        = use_yn; }
    public void setCatalog_page( String catalog_page )  { this.catalog_page  = catalog_page; }
    public void setCatalog_size( String catalog_size )  { this.catalog_size  = catalog_size; }
    public void setWeight( String weight )              { this.weight        = weight; }
    public void setPaper_quality( String paper_quality ){ this.paper_quality = paper_quality; }
    public void setPrint_med( String print_med )        { this.print_med     = print_med; }
    public void setValid_bdate( String valid_bdate )    { this.valid_bdate   = valid_bdate; }
    public void setValid_edate( String valid_edate )    { this.valid_edate   = valid_edate; }
    public void setSend_date( String send_date )        { this.send_date     = send_date; }
    public void setGoods_cnt( String goods_cnt )        { this.goods_cnt     = goods_cnt; }
    public void setProduct_cnt( String product_cnt )    { this.product_cnt   = product_cnt; }
    public void setSend_note( String send_note )        { this.send_note     = send_note; }
    public void setSpecial_note( String special_note )  { this.special_note  = special_note; }
    public void setInsert_date( String insert_date )    { this.insert_date   = insert_date; }
    public void setInsert_id( String insert_id )        { this.insert_id     = insert_id; }
    public void setModify_date( String modify_date )    { this.modify_date   = modify_date; }
    public void setModify_id( String modify_id )        { this.modify_id     = modify_id; }
    public void setGoods_code(String goods_code) 		{ this.goods_code = goods_code;	}
    public void setPage_cost(String page_cost) 		{ this.page_cost = page_cost;	}

    /** Get Method **/
    public String getMedia_code()   { return media_code; }
    public String getMedia_year()   { return media_year; }
    public String getMedia_name()   { return media_name; }
    public String getMedia_gb()     { return media_gb; }
    public String getMedia_style()  { return media_style; }
    public String getMedia_month()  { return media_month; }
    public String getUse_yn()       { return use_yn; }
    public String getCatalog_page() { return catalog_page; }
    public String getCatalog_size() { return catalog_size; }
    public String getWeight()       { return weight; }
    public String getPaper_quality(){ return paper_quality; }
    public String getPrint_med()    { return print_med; }
    public String getValid_bdate()  { return valid_bdate; }
    public String getValid_edate()  { return valid_edate; }
    public String getSend_date()    { return send_date; }
    public String getGoods_cnt()    { return goods_cnt; }
    public String getProduct_cnt()  { return product_cnt; }
    public String getSend_note()    { return send_note; }
    public String getSpecial_note() { return special_note; }
    public String getInsert_date()  { return insert_date; }
    public String getInsert_id()    { return insert_id; }
    public String getModify_date()  { return modify_date; }
    public String getModify_id()    { return modify_id; }
    public String getGoods_code()   { return goods_code;	}
    public String getPage_cost()   { return page_cost;	}

}