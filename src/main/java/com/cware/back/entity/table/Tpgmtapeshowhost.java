package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 테입쇼호스트정보
*
* @version 1.0, 2009/01/22
* @author Commerceware Ins.
*/
public class Tpgmtapeshowhost extends BaseEntity {

public Tpgmtapeshowhost(){ super();}

    private String tape_code;
    private String sno;
    private String sno_org;
    private String sno_rate;
    private String special_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setTape_code( String tape_code ){ this.tape_code = tape_code; }
    public void setSno( String sno ){ this.sno = sno; }
    public void setSno_org( String sno_org ){ this.sno_org = sno_org; }
    public void setSno_rate( String sno_rate ){ this.sno_rate = sno_rate; }
    public void setSpecial_yn( String special_yn ){ this.special_yn = special_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getTape_code(){ return tape_code; }
    public String getSno(){ return sno; }
    public String getSno_org(){ return sno_org; }
    public String getSno_rate(){ return sno_rate; }
    public String getSpecial_yn(){ return special_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

}