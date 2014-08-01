package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 사용자 프로그램 권한
*
* @version 1.0, 2006/07/21
* @author Commerceware Ins.
*/
public class Tsecperprogram extends BaseEntity {

    public Tsecperprogram(){ super();}

    private String user_id;
    private String lmenu_id;
    private String mmenu_id;
    private String program_id;
    private String query_yn;
    private String insert_yn;
    private String delete_yn;
    private String save_yn;
    private String print_yn;
    private String excel_yn;
    private String etc_yn;
    private String add_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setUser_id( String user_id ){ this.user_id = user_id; }
    public void setLmenu_id( String lmenu_id ){ this.lmenu_id = lmenu_id; }
    public void setMmenu_id( String mmenu_id ){ this.mmenu_id = mmenu_id; }
    public void setProgram_id( String program_id ){ this.program_id = program_id; }
    public void setQuery_yn( String query_yn ){ this.query_yn = query_yn; }
    public void setInsert_yn( String insert_yn ){ this.insert_yn = insert_yn; }
    public void setDelete_yn( String delete_yn ){ this.delete_yn = delete_yn; }
    public void setSave_yn( String save_yn ){ this.save_yn = save_yn; }
    public void setPrint_yn( String print_yn ){ this.print_yn = print_yn; }
    public void setExcel_yn( String excel_yn ){ this.excel_yn = excel_yn; }
    public void setEtc_yn( String etc_yn ){ this.etc_yn = etc_yn; }
    public void setAdd_yn( String add_yn ){ this.add_yn = add_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getUser_id(){ return user_id; }
    public String getLmenu_id(){ return lmenu_id; }
    public String getMmenu_id(){ return mmenu_id; }
    public String getProgram_id(){ return program_id; }
    public String getQuery_yn(){ return query_yn; }
    public String getInsert_yn(){ return insert_yn; }
    public String getDelete_yn(){ return delete_yn; }
    public String getSave_yn(){ return save_yn; }
    public String getPrint_yn(){ return print_yn; }
    public String getExcel_yn(){ return excel_yn; }
    public String getEtc_yn(){ return etc_yn; }
    public String getAdd_yn(){ return add_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


}