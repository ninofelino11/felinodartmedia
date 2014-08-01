
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 택배휴일관리
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tdelyday  extends BaseEntity {

    public Tdelyday(){ super();}

    private String dely_gb;
    private String yymmdd;
    private String day_gb;
    private String work_yn;
    private String off_name;
    private String insert_id;
    private String insert_date;
    private String modify_id;
    private String modify_date;

    /** Set Method **/
    public void setDely_gb( String dely_gb ){ this.dely_gb = dely_gb; }
    public void setYymmdd( String yymmdd ){ this.yymmdd = yymmdd; }
    public void setDay_gb( String day_gb ){ this.day_gb = day_gb; }
    public void setWork_yn( String work_yn ){ this.work_yn = work_yn; }
    public void setOff_name( String off_name ){ this.off_name = off_name; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }

    /** Get Method **/
    public String getDely_gb(){ return dely_gb; }
    public String getYymmdd(){ return yymmdd; }
    public String getDay_gb(){ return day_gb; }
    public String getWork_yn(){ return work_yn; }
    public String getOff_name(){ return off_name; }
    public String getInsert_id(){ return insert_id; }
    public String getInsert_date(){ return insert_date; }
    public String getModify_id(){ return modify_id; }
    public String getModify_date(){ return modify_date; }


} 