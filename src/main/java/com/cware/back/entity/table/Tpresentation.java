
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* presentation
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpresentation extends BaseEntity {

    public Tpresentation(){ super();}
    
    private String presentation_code;
    private String presentation_kinds;
    private String presentation_gb;
    private String category_code;
    private String goods_code;
    private String display_yn;
    private String display_priority;
    private String display_start_date;
    private String display_end_date;
    private String presentation_desc;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setPresentation_code( String presentation_code ){ this.presentation_code = presentation_code; }
    public void setPresentation_kinds( String presentation_kinds ){ this.presentation_kinds = presentation_kinds; }
    public void setPresentation_gb( String presentation_gb ){ this.presentation_gb = presentation_gb; }
    public void setCategory_code( String category_code ){ this.category_code = category_code; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setDisplay_yn( String display_yn ){ this.display_yn = display_yn; }
    public void setDisplay_priority( String display_priority ){ this.display_priority = display_priority; }
    public void setDisplay_start_date( String display_start_date ){ this.display_start_date = display_start_date; }
    public void setDisplay_end_date( String display_end_date ){ this.display_end_date = display_end_date; }
    public void setPresentation_desc( String presentation_desc ){ this.presentation_desc = presentation_desc; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getPresentation_code(){ return presentation_code; }
    public String getPresentation_kinds(){ return presentation_kinds; }
    public String getPresentation_gb(){ return presentation_gb; }
    public String getCategory_code(){ return category_code; }
    public String getGoods_code(){ return goods_code; }
    public String getDisplay_yn(){ return display_yn; }
    public String getDisplay_priority(){ return display_priority; }
    public String getDisplay_start_date(){ return display_start_date; }
    public String getDisplay_end_date(){ return display_end_date; }
    public String getPresentation_desc(){ return presentation_desc; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 