
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 고객결제내역
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcustsettle extends BaseEntity {

    public Tcustsettle(){ super();}

    private String cust_no;
    private String settle_seq;
    private String settle_gb;
    private String card_bank_code;
    private String bank_seq;
    private String card_no;
    private String valid_date;
    private String depositor;
    private String family_gb;
    private String depo_resi_no;
    private String cvv;
    private String use_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	/** Set Method **/
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setSettle_seq( String settle_seq ){ this.settle_seq = settle_seq; }
    public void setSettle_gb( String settle_gb ){ this.settle_gb = settle_gb; }
    public void setCard_bank_code( String card_bank_code ){ this.card_bank_code = card_bank_code; }
    public void setBank_seq( String bank_seq ){ this.bank_seq = bank_seq; }
    public void setCard_no( String card_no ){ this.card_no = card_no; }
    public void setValid_date( String valid_date ){ this.valid_date = valid_date; }
    public void setDepositor( String depositor ){ this.depositor = depositor; }
    public void setFamily_gb( String family_gb ){ this.family_gb = family_gb; }
    public void setDepo_resi_no( String depo_resi_no ){ this.depo_resi_no = depo_resi_no; }
    public void setCvv( String cvv ){ this.cvv = cvv; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCust_no(){ return cust_no; }
    public String getSettle_seq(){ return settle_seq; }
    public String getSettle_gb(){ return settle_gb; }
    public String getCard_bank_code(){ return card_bank_code; }
    public String getBank_seq(){ return bank_seq; }
    public String getCard_no(){ return card_no; }
    public String getValid_date(){ return valid_date; }
    public String getDepositor(){ return depositor; }
    public String getFamily_gb(){ return family_gb; }
    public String getDepo_resi_no(){ return depo_resi_no; }
    public String getCvv(){ return cvv; }
    public String getUse_yn(){ return use_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


}