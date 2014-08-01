
package com.cware.back.entity.common;

import com.cware.back.common.BaseEntity;

/**
* 주문 및 CS 처리시에 주문 결제 Sheet 정보
*
* @version 1.0, 2006/07/22
* @author Commerceware Ins.
*/
public class SheetOrderreceipts extends BaseEntity {

    public SheetOrderreceipts(){ super();}

    private String receipt_no;
    private String order_no;
    private String cust_no;
    private String do_flag;
    private String settle_gb;
    private String card_bank_code;
    private String bank_seq;
    private String card_no;
    private String depositor;
    private String valid_date;
    private double quest_amt;
    private String receipt_plan_date;
    private String ok_no;
    private String ok_date;
    private String ok_med;
    private String ok_error_code;
    private String van_comp;
    private long   pay_month;
    private String norest_yn;
    private double norest_rate;
    private double norest_amt;
    private double receipt_amt;
    private String receipt_date;
    private String end_yn;
    private String cancel_yn;
    private String cancel_code;
    private String cancel_date;
    private String cancel_id;
    private String saveamt_use_flag;
    private String insert_date;
    private String insert_id;
    private String remark;
    private String comp_name;
    private String comp_card_no;
    private String divide_yn;
    private String comp_receipt_no;
    private String remark2_v;
    private String vaccount_yn;
    private String depo_resi_no;
    private String cvv;
    private String remark1_v;
    private String remark4_n;

    /** Set Method **/
    public void setReceipt_no( String receipt_no ){ this.receipt_no = receipt_no; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setDo_flag( String do_flag ){ this.do_flag = do_flag; }
    public void setSettle_gb( String settle_gb ){ this.settle_gb = settle_gb; }
    public void setCard_bank_code( String card_bank_code ){ this.card_bank_code = card_bank_code; }
    public void setBank_seq( String bank_seq ){ this.bank_seq = bank_seq; }
    public void setCard_no( String card_no ){ this.card_no = card_no; }
    public void setDepositor( String depositor ){ this.depositor = depositor; }
    public void setValid_date( String valid_date ){ this.valid_date = valid_date; }
    public void setQuest_amt( double quest_amt ){ this.quest_amt = quest_amt; }
    public void setReceipt_plan_date( String receipt_plan_date ){ this.receipt_plan_date = receipt_plan_date; }
    public void setOk_no( String ok_no ){ this.ok_no = ok_no; }
    public void setOk_date( String ok_date ){ this.ok_date = ok_date; }
    public void setOk_med( String ok_med ){ this.ok_med = ok_med; }
    public void setOk_error_code( String ok_error_code ){ this.ok_error_code = ok_error_code; }
    public void setVan_comp( String van_comp ){ this.van_comp = van_comp; }
    public void setPay_month( long   pay_month ){ this.pay_month = pay_month; }
    public void setNorest_yn( String norest_yn ){ this.norest_yn = norest_yn; }
    public void setNorest_rate( double norest_rate ){ this.norest_rate = norest_rate; }
    public void setNorest_amt( double norest_amt ){ this.norest_amt = norest_amt; }
    public void setReceipt_amt( double receipt_amt ){ this.receipt_amt = receipt_amt; }
    public void setReceipt_date( String receipt_date ){ this.receipt_date = receipt_date; }
    public void setEnd_yn( String end_yn ){ this.end_yn = end_yn; }
    public void setCancel_yn( String cancel_yn ){ this.cancel_yn = cancel_yn; }
    public void setCancel_code( String cancel_code ){ this.cancel_code = cancel_code; }
    public void setCancel_date( String cancel_date ){ this.cancel_date = cancel_date; }
    public void setCancel_id( String cancel_id ){ this.cancel_id = cancel_id; }
    public void setSaveamt_use_flag( String saveamt_use_flag ){ this.saveamt_use_flag = saveamt_use_flag; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setComp_name( String comp_name ){ this.comp_name = comp_name; }
    public void setComp_card_no( String comp_card_no ){ this.comp_card_no = comp_card_no; }
    public void setDivide_yn( String divide_yn ){ this.divide_yn = divide_yn; }
    public void setComp_receipt_no( String comp_receipt_no ){ this.comp_receipt_no = comp_receipt_no; }
    public void setRemark2_v( String remark2_v ){ this.remark2_v = remark2_v; }
    public void setVaccount_yn( String vaccount_yn ){ this.vaccount_yn = vaccount_yn; }
    public void setDepo_resi_no( String depo_resi_no ){ this.depo_resi_no = depo_resi_no; }
    public void setCvv( String cvv ){ this.cvv = cvv; }
    public void setRemark1_v( String remark1_v ){ this.remark1_v = remark1_v; }
    public void setRemark4_n( String remark4_n ){ this.remark4_n = remark4_n; }

    public void initSheetOrderreceipts(){
        setCwareAction          ( "" );
        setCwareInfo            ( "" );
        this.receipt_no        = "";
        this.order_no          = "";
        this.cust_no           = "";
        this.do_flag           = "";
        this.settle_gb         = "";
        this.card_bank_code    = "";
        this.bank_seq          = "";
        this.card_no           = "";
        this.depositor         = "";
        this.valid_date        = "";
        this.quest_amt         = 0;
        this.receipt_plan_date = "";
        this.ok_no             = "";
        this.ok_date           = "";
        this.ok_med            = "";
        this.ok_error_code     = "";
        this.van_comp          = "";
        this.pay_month         = 0;
        this.norest_yn         = "0";
        this.norest_rate       = 0;
        this.norest_amt        = 0;
        this.receipt_amt       = 0;
        this.receipt_date      = "";
        this.end_yn            = "0";
        this.cancel_yn         = "0";
        this.cancel_code       = "";
        this.cancel_date       = "";
        this.cancel_id         = "";
        this.saveamt_use_flag  = "";
        this.insert_date       = "";
        this.insert_id         = "";
        this.remark            = "";
        this.comp_name         = "";
        this.comp_card_no      = "";
        this.divide_yn         = "";
        this.comp_receipt_no   = "";
        this.remark2_v         = "";
        this.vaccount_yn       = "";
        this.depo_resi_no      = "";
        this.cvv               = "";
        this.remark1_v         = "";
        this.remark4_n         = "";
    }

    /** Get Method **/
    public String getReceipt_no(){ return receipt_no; }
    public String getOrder_no(){ return order_no; }
    public String getCust_no(){ return cust_no; }
    public String getDo_flag(){ return do_flag; }
    public String getSettle_gb(){ return settle_gb; }
    public String getCard_bank_code(){ return card_bank_code; }
    public String getBank_seq(){ return bank_seq; }
    public String getCard_no(){ return card_no; }
    public String getDepositor(){ return depositor; }
    public String getValid_date(){ return valid_date; }
    public double getQuest_amt(){ return quest_amt; }
    public String getReceipt_plan_date(){ return receipt_plan_date; }
    public String getOk_no(){ return ok_no; }
    public String getOk_date(){ return ok_date; }
    public String getOk_med(){ return ok_med; }
    public String getOk_error_code(){ return ok_error_code; }
    public String getVan_comp(){ return van_comp; }
    public long   getPay_month(){ return pay_month; }
    public String getNorest_yn(){ return norest_yn; }
    public double getNorest_rate(){ return norest_rate; }
    public double getNorest_amt(){ return norest_amt; }
    public double getReceipt_amt(){ return receipt_amt; }
    public String getReceipt_date(){ return receipt_date; }
    public String getEnd_yn(){ return end_yn; }
    public String getCancel_yn(){ return cancel_yn; }
    public String getCancel_code(){ return cancel_code; }
    public String getCancel_date(){ return cancel_date; }
    public String getCancel_id(){ return cancel_id; }
    public String getSaveamt_use_flag(){ return saveamt_use_flag; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getRemark(){ return remark; }
    public String getComp_name(){ return comp_name; }
    public String getComp_card_no(){ return comp_card_no; }
    public String getDivide_yn(){ return divide_yn; }
    public String getComp_receipt_no(){ return comp_receipt_no; }
    public String getRemark2_v(){ return remark2_v; }
    public String getVaccount_yn(){ return vaccount_yn; }
    public String getDepo_resi_no(){ return depo_resi_no; }
    public String getCvv(){ return cvv; }
    public String getRemark1_v(){ return remark1_v; }
    public String getRemark4_n(){ return remark4_n; }


} 