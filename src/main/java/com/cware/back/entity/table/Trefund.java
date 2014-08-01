
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 환불
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Trefund extends BaseEntity {

public Trefund(){ super();}

    private String repay_no;
    private String receipt_no;
    private String cust_no;
    private String do_flag;
    private String old_do_flag;
    private String settle_gb;
    private String claim_code;
    private String bank_code;
    private String bank_deposit_no;
    private String depositor;
    private String repay_note;
    private double repay_amt;
    private double real_repay_amt;
    private String repay_commission;
    private String real_date;
    private String insert_date;
    private String insert_id;
    private String trans_date;
    private String trans_id;
    private String end_date;
    private String end_id;

    /** Set Method **/
    public void setRepay_no( String repay_no ){ this.repay_no = repay_no; }
    public void setReceipt_no( String receipt_no ){ this.receipt_no = receipt_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setDo_flag( String do_flag ){ this.do_flag = do_flag; }
    public void setSettle_gb( String settle_gb ){ this.settle_gb = settle_gb; }
    public void setClaim_code( String claim_code ){ this.claim_code = claim_code; }
    public void setBank_code( String bank_code ){ this.bank_code = bank_code; }
    public void setBank_deposit_no( String bank_deposit_no ){ this.bank_deposit_no = bank_deposit_no; }
    public void setDepositor( String depositor ){ this.depositor = depositor; }
    public void setRepay_note( String repay_note ){ this.repay_note = repay_note; }
    public void setRepay_amt( double repay_amt ){ this.repay_amt = repay_amt; }
    public void setReal_repay_amt( double real_repay_amt ){ this.real_repay_amt = real_repay_amt; }
    public void setRepay_commission( String repay_commission ){ this.repay_commission = repay_commission; }
    public void setReal_date( String real_date ){ this.real_date = real_date; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setTrans_date( String trans_date ){ this.trans_date = trans_date; }
    public void setTrans_id( String trans_id ){ this.trans_id = trans_id; }
    public void setEnd_date( String end_date ){ this.end_date = end_date; }
    public void setEnd_id( String end_id ){ this.end_id = end_id; }
	public void setOld_do_flag(String old_do_flag) {this.old_do_flag = old_do_flag;}

    /** Get Method **/
    public String getRepay_no(){ return repay_no; }
    public String getReceipt_no(){ return receipt_no; }
    public String getCust_no(){ return cust_no; }
    public String getDo_flag(){ return do_flag; }
    public String getSettle_gb(){ return settle_gb; }
    public String getClaim_code(){ return claim_code; }
    public String getBank_code(){ return bank_code; }
    public String getBank_deposit_no(){ return bank_deposit_no; }
    public String getDepositor(){ return depositor; }
    public String getRepay_note(){ return repay_note; }
    public double getRepay_amt(){ return repay_amt; }
    public double getReal_repay_amt(){ return real_repay_amt; }
    public String getRepay_commission(){ return repay_commission; }
    public String getReal_date(){ return real_date; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getTrans_date(){ return trans_date; }
    public String getTrans_id(){ return trans_id; }
    public String getEnd_date(){ return end_date; }
    public String getEnd_id(){ return end_id; }
	public String getOld_do_flag() {return old_do_flag;}

}