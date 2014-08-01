
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 업체담당자
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tentpuser extends BaseEntity {

    public Tentpuser(){ super();}
    
    private String entp_code;
    private String entp_man_seq;
    private String entp_man_gb;
    private String entp_man_name;
    private String entp_man_level;
    private String entp_man_ddd;
    private String entp_man_tel1;
    private String entp_man_tel2;
    private String entp_man_tel3;
    private String entp_man_fax1;
    private String entp_man_fax2;
    private String entp_man_fax3;
    private String entp_man_hp1;
    private String entp_man_hp2;
    private String entp_man_hp3;
    private String email_addr;
    private String post_no;
    private String post_seq;
    private String addr;
    private String trans_note;
    private String default_yn;
    private String use_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setEntp_man_seq( String entp_man_seq ){ this.entp_man_seq = entp_man_seq; }
    public void setEntp_man_gb( String entp_man_gb ){ this.entp_man_gb = entp_man_gb; }
    public void setEntp_man_name( String entp_man_name ){ this.entp_man_name = entp_man_name; }
    public void setEntp_man_level( String entp_man_level ){ this.entp_man_level = entp_man_level; }
    public void setEntp_man_ddd( String entp_man_ddd ){ this.entp_man_ddd = entp_man_ddd; }
    public void setEntp_man_tel1( String entp_man_tel1 ){ this.entp_man_tel1 = entp_man_tel1; }
    public void setEntp_man_tel2( String entp_man_tel2 ){ this.entp_man_tel2 = entp_man_tel2; }
    public void setEntp_man_tel3( String entp_man_tel3 ){ this.entp_man_tel3 = entp_man_tel3; }
    public void setEntp_man_fax1( String entp_man_fax1 ){ this.entp_man_fax1 = entp_man_fax1; }
    public void setEntp_man_fax2( String entp_man_fax2 ){ this.entp_man_fax2 = entp_man_fax2; }
    public void setEntp_man_fax3( String entp_man_fax3 ){ this.entp_man_fax3 = entp_man_fax3; }
    public void setEntp_man_hp1( String entp_man_hp1 ){ this.entp_man_hp1 = entp_man_hp1; }
    public void setEntp_man_hp2( String entp_man_hp2 ){ this.entp_man_hp2 = entp_man_hp2; }
    public void setEntp_man_hp3( String entp_man_hp3 ){ this.entp_man_hp3 = entp_man_hp3; }
    public void setEmail_addr( String email_addr ){ this.email_addr = email_addr; }
    public void setTrans_note( String trans_note ){ this.trans_note = trans_note; }
    public void setDefault_yn( String default_yn ){ this.default_yn = default_yn; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    public void setPost_no(String postNo) {
		post_no = postNo;
	}
    public void setPost_seq(String postSeq) {
		post_seq = postSeq;
	}
    public void setAddr(String addr) {
		this.addr = addr;
	}

    /** Get Method **/
    public String getEntp_code(){ return entp_code; }
    public String getEntp_man_seq(){ return entp_man_seq; }
    public String getEntp_man_gb(){ return entp_man_gb; }
    public String getEntp_man_name(){ return entp_man_name; }
    public String getEntp_man_level(){ return entp_man_level; }
    public String getEntp_man_ddd(){ return entp_man_ddd; }
    public String getEntp_man_tel1(){ return entp_man_tel1; }
    public String getEntp_man_tel2(){ return entp_man_tel2; }
    public String getEntp_man_tel3(){ return entp_man_tel3; }
    public String getEntp_man_fax1(){ return entp_man_fax1; }
    public String getEntp_man_fax2(){ return entp_man_fax2; }
    public String getEntp_man_fax3(){ return entp_man_fax3; }
    public String getEntp_man_hp1(){ return entp_man_hp1; }
    public String getEntp_man_hp2(){ return entp_man_hp2; }
    public String getEntp_man_hp3(){ return entp_man_hp3; }
    public String getEmail_addr(){ return email_addr; }
    public String getTrans_note(){ return trans_note; }
    public String getDefault_yn(){ return default_yn; }
    public String getUse_yn(){ return use_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
	public String getPost_no() {
		return post_no;
	}
	public String getPost_seq() {
		return post_seq;
	}
	public String getAddr() {
		return addr;
	}
} 