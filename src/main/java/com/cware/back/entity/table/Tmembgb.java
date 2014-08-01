package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 고객등급
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tmembgb extends BaseEntity {

public Tmembgb(){ super();}

    private String memb_gb;
    private String seq;
    private String type;
    private String val1;
    private String val2;
    private String val3;
    private String content;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setMemb_gb( String memb_gb ){ this.memb_gb = memb_gb; }
    public void setSeq( String seq ){ this.seq = seq; }
    public void setType( String type ){ this.type = type; }
    public void setVal1( String val1 ){ this.val1 = val1; }
    public void setVal2( String val2 ){ this.val2 = val2; }
    public void setVal3( String val3 ){ this.val3 = val3; }
    public void setContent( String content ){ this.content = content; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getMemb_gb(){ return memb_gb; }
    public String getSeq(){ return seq; }
    public String getType(){ return type; }
    public String getVal1(){ return val1; }
    public String getVal2(){ return val2; }
    public String getVal3(){ return val3; }
    public String getContent(){ return content; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

} 