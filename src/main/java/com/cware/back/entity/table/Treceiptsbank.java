package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 입금은행
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Treceiptsbank extends BaseEntity {

    public Treceiptsbank(){ super();}

    private String bank_code;
    private String bank_seq;
    private String bank_name;
    private String bank_deposit_no;
    private String bank_addr;
    private String bank_branch;
    private String bank_man_name;
    private String bank_man_ddd;
    private String bank_man_tel1;
    private String bank_man_tel2;
    private String bank_man_tel3;
    private String vaccount_yn;
    private String use_yn;
    private String remark;
    private String type;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setBank_code( String bank_code ){ this.bank_code = bank_code; }
    public void setBank_seq( String bank_seq ){ this.bank_seq = bank_seq; }
    public void setBank_name( String bank_name ){ this.bank_name = bank_name; }
    public void setBank_deposit_no( String bank_deposit_no ){ this.bank_deposit_no = bank_deposit_no; }
    public void setBank_addr( String bank_addr ){ this.bank_addr = bank_addr; }
    public void setBank_branch( String bank_branch ){ this.bank_branch = bank_branch; }
    public void setBank_man_name( String bank_man_name ){ this.bank_man_name = bank_man_name; }
    public void setBank_man_ddd( String bank_man_ddd ){ this.bank_man_ddd = bank_man_ddd; }
    public void setBank_man_tel1( String bank_man_tel1 ){ this.bank_man_tel1 = bank_man_tel1; }
    public void setBank_man_tel2( String bank_man_tel2 ){ this.bank_man_tel2 = bank_man_tel2; }
    public void setBank_man_tel3( String bank_man_tel3 ){ this.bank_man_tel3 = bank_man_tel3; }
    public void setVaccount_yn( String vaccount_yn ){ this.vaccount_yn = vaccount_yn; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setType( String type ){ this.type = type; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getBank_code(){ return bank_code; }
    public String getBank_seq(){ return bank_seq; }
    public String getBank_name(){ return bank_name; }
    public String getBank_deposit_no(){ return bank_deposit_no; }
    public String getBank_addr(){ return bank_addr; }
    public String getBank_branch(){ return bank_branch; }
    public String getBank_man_name(){ return bank_man_name; }
    public String getBank_man_ddd(){ return bank_man_ddd; }
    public String getBank_man_tel1(){ return bank_man_tel1; }
    public String getBank_man_tel2(){ return bank_man_tel2; }
    public String getBank_man_tel3(){ return bank_man_tel3; }
    public String getVaccount_yn(){ return vaccount_yn; }
    public String getUse_yn(){ return use_yn; }
    public String getRemark(){ return remark; }
    public String getType(){ return type; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


} 