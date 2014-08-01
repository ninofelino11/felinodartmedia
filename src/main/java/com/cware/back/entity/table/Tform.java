
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 형태
*
* @version 1.0, 2006/07/19
* @author Commerceware Ins.
*/
public class Tform extends BaseEntity {

    public Tform(){ super();}

    private String lgroup;
    private String mgroup;
    private String sgroup;
    private String dgroup;
    private String form_code;
    private String form_name;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setLgroup( String lgroup ){ this.lgroup = lgroup; }
    public void setMgroup( String mgroup ){ this.mgroup = mgroup; }
    public void setSgroup( String sgroup ){ this.sgroup = sgroup; }
    public void setDgroup( String dgroup ){ this.dgroup = dgroup; }
    public void setForm_code( String form_code ){ this.form_code = form_code; }
    public void setForm_name( String form_name ){ this.form_name = form_name; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getLgroup(){ return lgroup; }
    public String getMgroup(){ return mgroup; }
    public String getSgroup(){ return sgroup; }
    public String getDgroup(){ return dgroup; }
    public String getForm_code(){ return form_code; }
    public String getForm_name(){ return form_name; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 