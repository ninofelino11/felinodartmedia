
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 업체기본정보
*
* @version 1.0, 2006/07/11
* @author Commerceware Ins.
*/
public class Tenterprise extends BaseEntity {

    public Tenterprise(){ super();}

    private String entp_code;
    private String entp_name;
    private String entp_gubun;
    private String s_idno;
    private String work_type;
    private String work_kind;
    private String entp_post;
    private String entp_post_seq;
    private String entp_addr;
    private String entp_ddd;
    private String entp_tel1;
    private String entp_tel2;
    private String entp_tel3;
    private String entp_fax1;
    private String entp_fax2;
    private String entp_fax3;
    private String email_addr;
    private String owner_name;
    private String dishonor_yn;
    private String secret_no;
    private String first_date;
    private String close_date;
    private String close_reason;
    private String etc;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String dely_gb;

    public String getDely_gb() {
		return dely_gb;
	}
	public void setDely_gb(String dely_gb) {
		this.dely_gb = dely_gb;
	}
	/** Set Method **/
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setEntp_name( String entp_name ){ this.entp_name = entp_name; }
    public void setEntp_gubun( String entp_gubun ){ this.entp_gubun = entp_gubun; }
    public void setS_idno( String s_idno ){ this.s_idno = s_idno; }
    public void setWork_type( String work_type ){ this.work_type = work_type; }
    public void setWork_kind( String work_kind ){ this.work_kind = work_kind; }
    public void setEntp_post( String entp_post ){ this.entp_post = entp_post; }
    public void setEntp_post_seq( String entp_post_seq ){ this.entp_post_seq = entp_post_seq; }
    public void setEntp_addr( String entp_addr ){ this.entp_addr = entp_addr; }
    public void setEntp_ddd( String entp_ddd ){ this.entp_ddd = entp_ddd; }
    public void setEntp_tel1( String entp_tel1 ){ this.entp_tel1 = entp_tel1; }
    public void setEntp_tel2( String entp_tel2 ){ this.entp_tel2 = entp_tel2; }
    public void setEntp_tel3( String entp_tel3 ){ this.entp_tel3 = entp_tel3; }
    public void setEntp_fax1( String entp_fax1 ){ this.entp_fax1 = entp_fax1; }
    public void setEntp_fax2( String entp_fax2 ){ this.entp_fax2 = entp_fax2; }
    public void setEntp_fax3( String entp_fax3 ){ this.entp_fax3 = entp_fax3; }
    public void setEmail_addr( String email_addr ){ this.email_addr = email_addr; }
    public void setOwner_name( String owner_name ){ this.owner_name = owner_name; }
    public void setDishonor_yn( String dishonor_yn ){ this.dishonor_yn = dishonor_yn; }
    public void setSecret_no( String secret_no ){ this.secret_no = secret_no; }
    public void setFirst_date( String first_date ){ this.first_date = first_date; }
    public void setClose_date( String close_date ){ this.close_date = close_date; }
    public void setClose_reason( String close_reason ){ this.close_reason = close_reason; }
    public void setEtc( String etc ){ this.etc = etc; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getEntp_code(){ return entp_code; }
    public String getEntp_name(){ return entp_name; }
    public String getEntp_gubun(){ return entp_gubun; }
    public String getS_idno(){ return s_idno; }
    public String getWork_type(){ return work_type; }
    public String getWork_kind(){ return work_kind; }
    public String getEntp_post(){ return entp_post; }
    public String getEntp_post_seq(){ return entp_post_seq; }
    public String getEntp_addr(){ return entp_addr; }
    public String getEntp_ddd(){ return entp_ddd; }
    public String getEntp_tel1(){ return entp_tel1; }
    public String getEntp_tel2(){ return entp_tel2; }
    public String getEntp_tel3(){ return entp_tel3; }
    public String getEntp_fax1(){ return entp_fax1; }
    public String getEntp_fax2(){ return entp_fax2; }
    public String getEntp_fax3(){ return entp_fax3; }
    public String getEmail_addr(){ return email_addr; }
    public String getOwner_name(){ return owner_name; }
    public String getDishonor_yn(){ return dishonor_yn; }
    public String getSecret_no(){ return secret_no; }
    public String getFirst_date(){ return first_date; }
    public String getClose_date(){ return close_date; }
    public String getClose_reason(){ return close_reason; }
    public String getEtc(){ return etc; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


}