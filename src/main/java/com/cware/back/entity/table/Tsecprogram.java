package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로그램기능
*
* @version 1.0, 2007/01/23
* @author Commerceware Ins.
*/
public class Tsecprogram extends BaseEntity {

    public Tsecprogram(){ super();}
    
    private String lmenu_id;
    private String mmenu_id;
    private String smenu_id;
    private String sort;
    private String program_id;
    private String program_name;
    private String is_proc_id;
    private String query_yn;
    private String insert_yn;
    private String delete_yn;
    private String save_yn;
    private String print_yn;
    private String new_yn;
    private String use_yn;
    private String remark;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
         
    /** Set Method **/
    public void setLmenu_id( String lmenu_id ){ this.lmenu_id = lmenu_id; }
    public void setMmenu_id( String mmenu_id ){ this.mmenu_id = mmenu_id; }
    public void setSmenu_id( String smenu_id ){ this.smenu_id = smenu_id; }
    public void setSort( String sort ){ this.sort = sort; }
    public void setProgram_id( String program_id ){ this.program_id = program_id; }
    public void setProgram_name( String program_name ){ this.program_name = program_name; }
    public void setIs_proc_id( String is_proc_id ){ this.is_proc_id = is_proc_id; }
    public void setQuery_yn( String query_yn ){ this.query_yn = query_yn; }
    public void setInsert_yn( String insert_yn ){ this.insert_yn = insert_yn; }
    public void setDelete_yn( String delete_yn ){ this.delete_yn = delete_yn; }
    public void setSave_yn( String save_yn ){ this.save_yn = save_yn; }
    public void setPrint_yn( String print_yn ){ this.print_yn = print_yn; }
    public void setNew_yn( String new_yn ){ this.new_yn = new_yn; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/    
    public String getLmenu_id(){ return lmenu_id; }
    public String getMmenu_id(){ return mmenu_id; }
    public String getSmenu_id(){ return smenu_id; }
    public String getSort(){ return sort; }
    public String getProgram_id(){ return program_id; }
    public String getProgram_name(){ return program_name; }
    public String getIs_proc_id(){ return is_proc_id; }
    public String getQuery_yn(){ return query_yn; }
    public String getInsert_yn(){ return insert_yn; }
    public String getDelete_yn(){ return delete_yn; }
    public String getSave_yn(){ return save_yn; }
    public String getPrint_yn(){ return print_yn; }
    public String getNew_yn(){ return new_yn; }
    public String getUse_yn(){ return use_yn; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 