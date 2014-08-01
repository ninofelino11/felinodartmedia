
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 카테고리상품
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcategorygoods extends BaseEntity {

    public Tcategorygoods(){ super();}
    
    private String category_code;
    private String goods_code;
    private String display_yn;
    private String display_yn_org;
    private String display_priority;
    private String display_start_date;
    private String display_end_date;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setCategory_code( String category_code ){ this.category_code = category_code; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setDisplay_yn( String display_yn ){ this.display_yn = display_yn; }
    public void setDisplay_yn_org( String display_yn_org ){ this.display_yn_org = display_yn_org; }
    public void setDisplay_priority( String display_priority ){ this.display_priority = display_priority; }
    public void setDisplay_start_date( String display_start_date ){ this.display_start_date = display_start_date; }
    public void setDisplay_end_date( String display_end_date ){ this.display_end_date = display_end_date; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCategory_code(){ return category_code; }
    public String getGoods_code(){ return goods_code; }
    public String getDisplay_yn(){ return display_yn; }
    public String getDisplay_yn_org(){ return display_yn_org; }
    public String getDisplay_priority(){ return display_priority; }
    public String getDisplay_start_date(){ return display_start_date; }
    public String getDisplay_end_date(){ return display_end_date; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 