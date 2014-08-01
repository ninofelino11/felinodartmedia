
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 배달지
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Treceiver extends BaseEntity {

    public Treceiver(){ super();}

    private String cust_no;
    private String receiver_seq;
    private String receiver_gb;
    private String use_yn;
    private String default_yn;
    private String receiver;
    private String receiver1;
    private String receiver2;
    private String receiver3;
    private String tel;
    private String receiver_ddd;
    private String receiver_tel1;
    private String receiver_tel2;
    private String receiver_tel3;
    private String receiver_post;
    private String receiver_post_seq;
    private String receiver_addr;
    private String receiver_hp;
    private String receiver_hp1;
    private String receiver_hp2;
    private String receiver_hp3;
    private String receiver_hp1_se;
    private String receiver_hp2_se;
    private String receiver_hp3_se;
    private String last_use_date;
    private String before_seq;
    private String valid_tel_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setReceiver_seq( String receiver_seq ){ this.receiver_seq = receiver_seq; }
    public void setReceiver_gb( String receiver_gb ){ this.receiver_gb = receiver_gb; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setDefault_yn( String default_yn ){ this.default_yn = default_yn; }
    public void setReceiver( String receiver ){ this.receiver = receiver; }
    public void setTel( String tel ){ this.tel = tel; }
    public void setReceiver_ddd( String receiver_ddd ){ this.receiver_ddd = receiver_ddd; }
    public void setReceiver_tel1( String receiver_tel1 ){ this.receiver_tel1 = receiver_tel1; }
    public void setReceiver_tel2( String receiver_tel2 ){ this.receiver_tel2 = receiver_tel2; }
    public void setReceiver_tel3( String receiver_tel3 ){ this.receiver_tel3 = receiver_tel3; }
    public void setReceiver_post( String receiver_post ){ this.receiver_post = receiver_post; }
    public void setReceiver_post_seq( String receiver_post_seq ){ this.receiver_post_seq = receiver_post_seq; }
    public void setReceiver_addr( String receiver_addr ){ this.receiver_addr = receiver_addr; }
    public void setReceiver_hp( String receiver_hp ){ this.receiver_hp = receiver_hp; }
    public void setReceiver_hp1( String receiver_hp1 ){ this.receiver_hp1 = receiver_hp1; }
    public void setReceiver_hp2( String receiver_hp2 ){ this.receiver_hp2 = receiver_hp2; }
    public void setReceiver_hp3( String receiver_hp3 ){ this.receiver_hp3 = receiver_hp3; }
    public void setReceiver_hp1_se( String receiver_hp1_se ){ this.receiver_hp1_se = receiver_hp1_se; }
    public void setReceiver_hp2_se( String receiver_hp2_se ){ this.receiver_hp2_se = receiver_hp2_se; }
    public void setReceiver_hp3_se( String receiver_hp3_se ){ this.receiver_hp3_se = receiver_hp3_se; }
    public void setLast_use_date( String last_use_date ){ this.last_use_date = last_use_date; }
    public void setBefore_seq( String before_seq ){ this.before_seq = before_seq; }
    public void setValid_tel_yn( String valid_tel_yn ){ this.valid_tel_yn = valid_tel_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCust_no(){ return cust_no; }
    public String getReceiver_seq(){ return receiver_seq; }
    public String getReceiver_gb(){ return receiver_gb; }
    public String getUse_yn(){ return use_yn; }
    public String getDefault_yn(){ return default_yn; }
    public String getReceiver(){ return receiver; }
    public String getTel(){ return tel; }
    public String getReceiver_ddd(){ return receiver_ddd; }
    public String getReceiver_tel1(){ return receiver_tel1; }
    public String getReceiver_tel2(){ return receiver_tel2; }
    public String getReceiver_tel3(){ return receiver_tel3; }
    public String getReceiver_post(){ return receiver_post; }
    public String getReceiver_post_seq(){ return receiver_post_seq; }
    public String getReceiver_addr(){ return receiver_addr; }
    public String getReceiver_hp(){ return receiver_hp; }
    public String getReceiver_hp1(){ return receiver_hp1; }
    public String getReceiver_hp2(){ return receiver_hp2; }
    public String getReceiver_hp3(){ return receiver_hp3; }
    public String getReceiver_hp1_se(){ return receiver_hp1_se; }
    public String getReceiver_hp2_se(){ return receiver_hp2_se; }
    public String getReceiver_hp3_se(){ return receiver_hp3_se; }
    public String getLast_use_date(){ return last_use_date; }
    public String getBefore_seq(){ return before_seq; }
    public String getValid_tel_yn(){ return valid_tel_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
	public String getReceiver1() {
		return receiver1;
	}
	public void setReceiver1(String receiver1) {
		this.receiver1 = receiver1;
	}
	public String getReceiver2() {
		return receiver2;
	}
	public void setReceiver2(String receiver2) {
		this.receiver2 = receiver2;
	}
	public String getReceiver3() {
		return receiver3;
	}
	public void setReceiver3(String receiver3) {
		this.receiver3 = receiver3;
	}


} 