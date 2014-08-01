package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 브랜드정보
*
* @version 1.0, 2006/08/12
* @author Commerceware Ins.
*/
public class Tbroadafter extends BaseEntity {

public Tbroadafter(){ super();}

    private String bd_date;
    private String bd_btime;
    private String prog_code;
    private String user_gb;
    private String after_code;
    private String after_code_org;
    private String after_info;
    private String goods_code;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setBd_date( String bd_date ){ this.bd_date = bd_date; }
    public void setBd_btime( String bd_btime ){ this.bd_btime = bd_btime; }
    public void setProg_code( String prog_code ){ this.prog_code = prog_code; }
    public void setUser_gb( String user_gb ){ this.user_gb = user_gb; }
    public void setAfter_code( String after_code ){ this.after_code = after_code; }
    public void setAfter_code_org( String after_code_org ){ this.after_code_org = after_code_org; }
    public void setAfter_info( String after_info ){ this.after_info = after_info; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getBd_date(){ return bd_date; }
    public String getBd_btime(){ return bd_btime; }
    public String getProg_code(){ return prog_code; }
    public String getUser_gb(){ return user_gb; }
    public String getAfter_code(){ return after_code; }
    public String getAfter_code_org(){ return after_code_org; }
    public String getAfter_info(){ return after_info; }
    public String getGoods_code(){ return goods_code; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

}