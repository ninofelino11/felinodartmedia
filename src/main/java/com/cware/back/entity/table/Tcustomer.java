
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 고객
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcustomer extends BaseEntity {

    public Tcustomer(){ super();}

    private String cust_no;
    private String cust_name;
    private String cust_name1;
    private String cust_name2;
    private String cust_name3;
    private String memb_no;
    private String mem_id;
    private String passwd;
    private String passwd_hint;
    private String passwd_answer;
    private String id_insert_date;
    private String resident_no;
    private String sex;
    private String ename;
    private String birthday_yn;
    private String birthday;
    private String wedding_yn;
    private String wedding_date;
    private String job_code;
    private String comp_name;
    private String comp_deptname;
    private String country;
    private String email_addr;
    private String email_yn;
    private String email_flag;
    private String email_block_code;
    private String email_block_date;
    private String order_email_yn;
    private String sms_yn;
    private String receive_method;
    private String em_yn;
    private String em_no;
    private String nominate_id;
    private String nominate_yn;
    private String cust_source;
    private String withdrawal_yn;
    private String withdrawal_code;
    private String withdrawal_content;
    private String withdrawal_date;
    private String remark1_v;
    private String remark2_v;
    private String remark3_v;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setCust_name( String cust_name ){ this.cust_name = cust_name; }
	public void setCust_name1(String cust_name1) { this.cust_name1 = cust_name1; }
	public void setCust_name2(String cust_name2) { this.cust_name2 = cust_name2; }
	public void setCust_name3(String cust_name3) { this.cust_name3 = cust_name3; }
    public void setMemb_no( String memb_no ){ this.memb_no = memb_no; }
    public void setMem_id( String mem_id ){ this.mem_id = mem_id; }
    public void setPasswd( String passwd ){ this.passwd = passwd; }
    public void setPasswd_hint( String passwd_hint ){ this.passwd_hint = passwd_hint; }
    public void setPasswd_answer( String passwd_answer ){ this.passwd_answer = passwd_answer; }
    public void setId_insert_date( String id_insert_date ){ this.id_insert_date = id_insert_date; }
    public void setResident_no( String resident_no ){ this.resident_no = resident_no; }
    public void setSex( String sex ){ this.sex = sex; }
    public void setEname( String ename ){ this.ename = ename; }
    public void setBirthday_yn( String birthday_yn ){ this.birthday_yn = birthday_yn; }
    public void setBirthday( String birthday ){ this.birthday = birthday; }
    public void setWedding_yn( String wedding_yn ){ this.wedding_yn = wedding_yn; }
    public void setWedding_date( String wedding_date ){ this.wedding_date = wedding_date; }
    public void setJob_code( String job_code ){ this.job_code = job_code; }
    public void setComp_name( String comp_name ){ this.comp_name = comp_name; }
    public void setComp_deptname( String comp_deptname ){ this.comp_deptname = comp_deptname; }
    public void setCountry( String country ){ this.country = country; }
    public void setEmail_addr( String email_addr ){ this.email_addr = email_addr; }
    public void setEmail_yn( String email_yn ){ this.email_yn = email_yn; }
    public void setEmail_flag( String email_flag ){ this.email_flag = email_flag; }
    public void setEmail_block_code( String email_block_code ){ this.email_block_code = email_block_code; }
    public void setEmail_block_date( String email_block_date ){ this.email_block_date = email_block_date; }
    public void setOrder_email_yn( String order_email_yn ){ this.order_email_yn = order_email_yn; }
    public void setSms_yn( String sms_yn ){ this.sms_yn = sms_yn; }
    public void setReceive_method( String receive_method ){ this.receive_method = receive_method; }
    public void setEm_yn( String em_yn ){ this.em_yn = em_yn; }
    public void setEm_no( String em_no ){ this.em_no = em_no; }
    public void setNominate_id( String nominate_id ){ this.nominate_id = nominate_id; }
    public void setNominate_yn( String nominate_yn ){ this.nominate_yn = nominate_yn; }
    public void setCust_source( String cust_source ){ this.cust_source = cust_source; }
    public void setWithdrawal_yn( String withdrawal_yn ){ this.withdrawal_yn = withdrawal_yn; }
    public void setWithdrawal_code( String withdrawal_code ){ this.withdrawal_code = withdrawal_code; }
    public void setWithdrawal_content( String withdrawal_content ){ this.withdrawal_content = withdrawal_content; }
    public void setWithdrawal_date( String withdrawal_date ){ this.withdrawal_date = withdrawal_date; }
    public void setRemark1_v( String remark1_v ){ this.remark1_v = remark1_v; }
    public void setRemark2_v( String remark2_v ){ this.remark2_v = remark2_v; }
    public void setRemark3_v( String remark3_v ){ this.remark3_v = remark3_v; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCust_no(){ return cust_no; }
    public String getCust_name(){ return cust_name; }
    public String getCust_name1() { return cust_name1; }
	public String getCust_name2() { return cust_name2; }
	public String getCust_name3() { return cust_name3; }
    public String getMemb_no(){ return memb_no; }
    public String getMem_id(){ return mem_id; }
    public String getPasswd(){ return passwd; }
    public String getPasswd_hint(){ return passwd_hint; }
    public String getPasswd_answer(){ return passwd_answer; }
    public String getId_insert_date(){ return id_insert_date; }
    public String getResident_no(){ return resident_no; }
    public String getSex(){ return sex; }
    public String getEname(){ return ename; }
    public String getBirthday_yn(){ return birthday_yn; }
    public String getBirthday(){ return birthday; }
    public String getWedding_yn(){ return wedding_yn; }
    public String getWedding_date(){ return wedding_date; }
    public String getJob_code(){ return job_code; }
    public String getComp_name(){ return comp_name; }
    public String getComp_deptname(){ return comp_deptname; }
    public String getCountry(){ return country; }
    public String getEmail_addr(){ return email_addr; }
    public String getEmail_yn(){ return email_yn; }
    public String getEmail_flag(){ return email_flag; }
    public String getEmail_block_code(){ return email_block_code; }
    public String getEmail_block_date(){ return email_block_date; }
    public String getOrder_email_yn(){ return order_email_yn; }
    public String getSms_yn(){ return sms_yn; }
    public String getReceive_method(){ return receive_method; }
    public String getEm_yn(){ return em_yn; }
    public String getEm_no(){ return em_no; }
    public String getNominate_id(){ return nominate_id; }
    public String getNominate_yn(){ return nominate_yn; }
    public String getCust_source(){ return cust_source; }
    public String getWithdrawal_yn(){ return withdrawal_yn; }
    public String getWithdrawal_code(){ return withdrawal_code; }
    public String getWithdrawal_content(){ return withdrawal_content; }
    public String getWithdrawal_date(){ return withdrawal_date; }
    public String getRemark1_v(){ return remark1_v; }
    public String getRemark2_v(){ return remark2_v; }
    public String getRemark3_v(){ return remark3_v; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

}