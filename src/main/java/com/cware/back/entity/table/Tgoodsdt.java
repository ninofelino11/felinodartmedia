
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품단품정보
*
* @version 1.0, 2006/07/27
* @author Commerceware Ins.
*/
public class Tgoodsdt extends BaseEntity {

    public Tgoodsdt(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private String goods_name;
    private String color_code;
    private String color_name;
    private String size_code;
    private String size_name;
    private String pattern_code;
    private String pattern_name;
    private String form_code;
    private String form_name;
    private String other_text;
    private String goodsdt_info;
    private String sale_gb;
    
    private String fix_rack_code;
    private String free_rack_code;
    private String rep_fix_rack_code;
    private String rep_free_rack_code;
    
    private String barcode;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setGoods_name( String goods_name ){ this.goods_name = goods_name; }
    public void setColor_code( String color_code ){ this.color_code = color_code; }
    public void setColor_name( String color_name ){ this.color_name = color_name; }
    public void setSize_code( String size_code ){ this.size_code = size_code; }
    public void setSize_name( String size_name ){ this.size_name = size_name; }
    public void setPattern_code( String pattern_code ){ this.pattern_code = pattern_code; }
    public void setPattern_name( String pattern_name ){ this.pattern_name = pattern_name; }
    public void setForm_code( String form_code ){ this.form_code = form_code; }
    public void setForm_name( String form_name ){ this.form_name = form_name; }
    public void setOther_text( String other_text ){ this.other_text = other_text; }
    public void setGoodsdt_info( String goodsdt_info ){ this.goodsdt_info = goodsdt_info; }
    public void setSale_gb( String sale_gb ){ this.sale_gb = sale_gb; }
    public void setFix_rack_code(String fix_rack_code) { this.fix_rack_code = fix_rack_code; }
    public void setFree_rack_code(String free_rack_code) { this.free_rack_code = free_rack_code; }
    public void setRep_fix_rack_code(String rep_fix_rack_code) { this.rep_fix_rack_code = rep_fix_rack_code; }
    public void setRep_free_rack_code(String rep_free_rack_code) { this.rep_free_rack_code = rep_free_rack_code; }
    public void setBarcode( String barcode ){ this.barcode = barcode; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getGoods_name(){ return goods_name; }
    public String getColor_code(){ return color_code; }
    public String getColor_name(){ return color_name; }
    public String getSize_code(){ return size_code; }
    public String getSize_name(){ return size_name; }
    public String getPattern_code(){ return pattern_code; }
    public String getPattern_name(){ return pattern_name; }
    public String getForm_code(){ return form_code; }
    public String getForm_name(){ return form_name; }
    public String getOther_text(){ return other_text; }
    public String getGoodsdt_info(){ return goodsdt_info; }
    public String getSale_gb(){ return sale_gb; }
    public String getFix_rack_code() { return fix_rack_code; }
    public String getFree_rack_code() { return free_rack_code; }
    public String getRep_fix_rack_code() { return rep_fix_rack_code; }
    public String getRep_free_rack_code() { return rep_free_rack_code; }
    public String getBarcode(){ return barcode; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 