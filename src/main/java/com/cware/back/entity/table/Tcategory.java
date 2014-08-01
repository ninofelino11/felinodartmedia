
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 카테고리
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcategory extends BaseEntity {

    public Tcategory(){ super();}

    private String category_code;
    private String category_name;
    private String p_category_code;
    private String display_yn;
    private String display_priority;
    private String category_gb;
    private String display_goods_cnt;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setCategory_code( String category_code ){ this.category_code = category_code; }
    public void setCategory_name( String category_name ){ this.category_name = category_name; }
    public void setP_category_code( String p_category_code ){ this.p_category_code = p_category_code; }
    public void setDisplay_yn( String display_yn ){ this.display_yn = display_yn; }
    public void setDisplay_priority( String display_priority ){ this.display_priority = display_priority; }
    public void setCategory_gb( String category_gb ){ this.category_gb = category_gb; }
    public void setDisplay_goods_cnt( String display_goods_cnt ){ this.display_goods_cnt = display_goods_cnt; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCategory_code(){ return category_code; }
    public String getCategory_name(){ return category_name; }
    public String getP_category_code(){ return p_category_code; }
    public String getDisplay_yn(){ return display_yn; }
    public String getDisplay_priority(){ return display_priority; }
    public String getCategory_gb(){ return category_gb; }
    public String getDisplay_goods_cnt(){ return display_goods_cnt; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 