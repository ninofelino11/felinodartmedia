
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 제조업체정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tmakecomp extends BaseEntity {

public Tmakecomp(){ super();}

    private String makeco_code;
    private String makeco_name;
    private String makeco_post;
    private String makeco_post_seq;
    private String makeco_addr;
    private String makeco_ddd;
    private String makeco_tel1;
    private String makeco_tel2;
    private String makeco_tel3;
    private String makeco_fax1;
    private String makeco_fax2;
    private String makeco_fax3;
    private String makeco_remark;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setMakeco_code( String makeco_code ){ this.makeco_code = makeco_code; }
    public void setMakeco_name( String makeco_name ){ this.makeco_name = makeco_name; }
    public void setMakeco_post( String makeco_post ){ this.makeco_post = makeco_post; }
    public void setMakeco_post_seq( String makeco_post_seq ){ this.makeco_post_seq = makeco_post_seq; }
    public void setMakeco_addr( String makeco_addr ){ this.makeco_addr = makeco_addr; }
    public void setMakeco_ddd( String makeco_ddd ){ this.makeco_ddd = makeco_ddd; }
    public void setMakeco_tel1( String makeco_tel1 ){ this.makeco_tel1 = makeco_tel1; }
    public void setMakeco_tel2( String makeco_tel2 ){ this.makeco_tel2 = makeco_tel2; }
    public void setMakeco_tel3( String makeco_tel3 ){ this.makeco_tel3 = makeco_tel3; }
    public void setMakeco_fax1( String makeco_fax1 ){ this.makeco_fax1 = makeco_fax1; }
    public void setMakeco_fax2( String makeco_fax2 ){ this.makeco_fax2 = makeco_fax2; }
    public void setMakeco_fax3( String makeco_fax3 ){ this.makeco_fax3 = makeco_fax3; }
    public void setMakeco_remark( String makeco_remark ){ this.makeco_remark = makeco_remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getMakeco_code(){ return makeco_code; }
    public String getMakeco_name(){ return makeco_name; }
    public String getMakeco_post(){ return makeco_post; }
    public String getMakeco_post_seq(){ return makeco_post_seq; }
    public String getMakeco_addr(){ return makeco_addr; }
    public String getMakeco_ddd(){ return makeco_ddd; }
    public String getMakeco_tel1(){ return makeco_tel1; }
    public String getMakeco_tel2(){ return makeco_tel2; }
    public String getMakeco_tel3(){ return makeco_tel3; }
    public String getMakeco_fax1(){ return makeco_fax1; }
    public String getMakeco_fax2(){ return makeco_fax2; }
    public String getMakeco_fax3(){ return makeco_fax3; }
    public String getMakeco_remark(){ return makeco_remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

} 