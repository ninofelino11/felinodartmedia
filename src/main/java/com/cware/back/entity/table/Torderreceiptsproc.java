
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문결제진행
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Torderreceiptsproc extends BaseEntity {

    public Torderreceiptsproc(){ super();}

    private String receipt_no;
    private String receipt_seq;
    private String do_flag;
    private String proc_note;
    private double proc_amt;
    private String real_bank_code;
    private String real_bank_seq;
    private String proc_date;
    private String proc_id;

    /** Set Method **/
    public void setReceipt_no( String receipt_no ){ this.receipt_no = receipt_no; }
    public void setReceipt_seq( String receipt_seq ){ this.receipt_seq = receipt_seq; }
    public void setDo_flag( String do_flag ){ this.do_flag = do_flag; }
    public void setProc_note( String proc_note ){ this.proc_note = proc_note; }
    public void setProc_amt( double proc_amt ){ this.proc_amt = proc_amt; }
    public void setReal_bank_code( String real_bank_code ){ this.real_bank_code = real_bank_code; }
    public void setReal_bank_seq( String real_bank_seq ){ this.real_bank_seq = real_bank_seq; }
    public void setProc_date( String proc_date ){ this.proc_date = proc_date; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }

    /** Get Method **/
    public String getReceipt_no(){ return receipt_no; }
    public String getReceipt_seq(){ return receipt_seq; }
    public String getDo_flag(){ return do_flag; }
    public String getProc_note(){ return proc_note; }
    public double getProc_amt(){ return proc_amt; }
    public String getReal_bank_code(){ return real_bank_code; }
    public String getReal_bank_seq(){ return real_bank_seq; }
    public String getProc_date(){ return proc_date; }
    public String getProc_id(){ return proc_id; }


} 