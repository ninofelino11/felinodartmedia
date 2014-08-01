
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 카테고리 Url
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcategoryurl extends BaseEntity {

    public Tcategoryurl(){ super();}

    private String categoryurl_code;
    private String categoryurl_kinds;
    private String category_code;
    private String name;
    private String target_url;
    private String image_url;
    private String display_yn;
    private String display_priority;
    private String display_start_date;
    private String display_end_date;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setCategoryurl_code( String categoryurl_code ){ this.categoryurl_code = categoryurl_code; }
    public void setCategoryurl_kinds( String categoryurl_kinds ){ this.categoryurl_kinds = categoryurl_kinds; }
    public void setCategory_code( String category_code ){ this.category_code = category_code; }
    public void setName( String name ){ this.name = name; }
    public void setTarget_url( String target_url ){ this.target_url = target_url; }
    public void setImage_url( String image_url ){ this.image_url = image_url; }
    public void setDisplay_yn( String display_yn ){ this.display_yn = display_yn; }
    public void setDisplay_priority( String display_priority ){ this.display_priority = display_priority; }
    public void setDisplay_start_date( String display_start_date ){ this.display_start_date = display_start_date; }
    public void setDisplay_end_date( String display_end_date ){ this.display_end_date = display_end_date; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCategoryurl_code(){ return categoryurl_code; }
    public String getCategoryurl_kinds(){ return categoryurl_kinds; }
    public String getCategory_code(){ return category_code; }
    public String getName(){ return name; }
    public String getTarget_url(){ return target_url; }
    public String getImage_url(){ return image_url; }
    public String getDisplay_yn(){ return display_yn; }
    public String getDisplay_priority(){ return display_priority; }
    public String getDisplay_start_date(){ return display_start_date; }
    public String getDisplay_end_date(){ return display_end_date; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 