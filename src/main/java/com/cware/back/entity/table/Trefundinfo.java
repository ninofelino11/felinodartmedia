
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 환불정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Trefundinfo extends BaseEntity {

    public Trefundinfo(){ super();}

    private String cust_no;
    private String claim_date;
    private String order_no;
    private String settle_gb;
    private String bank_code;
    private String bank_deposit_no;
    private String depositor;
    private String repay_note;
    private String remark;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setClaim_date( String claim_date ){ this.claim_date = claim_date; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setSettle_gb( String settle_gb ){ this.settle_gb = settle_gb; }
    public void setBank_code( String bank_code ){ this.bank_code = bank_code; }
    public void setBank_deposit_no( String bank_deposit_no ){ this.bank_deposit_no = bank_deposit_no; }
    public void setDepositor( String depositor ){ this.depositor = depositor; }
    public void setRepay_note( String repay_note ){ this.repay_note = repay_note; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCust_no(){ return cust_no; }
    public String getClaim_date(){ return claim_date; }
    public String getOrder_no(){ return order_no; }
    public String getSettle_gb(){ return settle_gb; }
    public String getBank_code(){ return bank_code; }
    public String getBank_deposit_no(){ return bank_deposit_no; }
    public String getDepositor(){ return depositor; }
    public String getRepay_note(){ return repay_note; }
    public String getRemark(){ return remark; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

} 