
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 예치금 사용
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tdeposituse extends BaseEntity {

    public Tdeposituse(){ super();}

    private String cust_no;
    private String use_seq;
    private String receipt_no;
    private String order_no;
    private String repay_no;
    private String proc_gb;
    private String proc_yn;
    private String trans_cust_no;
    private String depositamt_seq;
    private String depositamt_gb;
    private double use_amt;
    private String proc_id;
    private String proc_date;
    private String remark1_v;
    private String remark2_v;
    private long   remark3_n;
    private long   remark4_n;
    private String reamrk5_d;
    private String remark6_d;

    /** Set Method **/
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setUse_seq( String use_seq ){ this.use_seq = use_seq; }
    public void setReceipt_no( String receipt_no ){ this.receipt_no = receipt_no; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setRepay_no( String repay_no ){ this.repay_no = repay_no; }
    public void setProc_gb( String proc_gb ){ this.proc_gb = proc_gb; }
    public void setProc_yn( String proc_yn ){ this.proc_yn = proc_yn; }
    public void setTrans_cust_no( String trans_cust_no ){ this.trans_cust_no = trans_cust_no; }
    public void setDepositamt_seq( String depositamt_seq ){ this.depositamt_seq = depositamt_seq; }
    public void setDepositamt_gb( String depositamt_gb ){ this.depositamt_gb = depositamt_gb; }
    public void setUse_amt( double use_amt ){ this.use_amt = use_amt; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }
    public void setProc_date( String proc_date ){ this.proc_date = proc_date; }
    public void setRemark1_v( String remark1_v ){ this.remark1_v = remark1_v; }
    public void setRemark2_v( String remark2_v ){ this.remark2_v = remark2_v; }
    public void setRemark3_n( long   remark3_n ){ this.remark3_n = remark3_n; }
    public void setRemark4_n( long   remark4_n ){ this.remark4_n = remark4_n; }
    public void setReamrk5_d( String reamrk5_d ){ this.reamrk5_d = reamrk5_d; }
    public void setRemark6_d( String remark6_d ){ this.remark6_d = remark6_d; }

    /** Get Method **/
    public String getCust_no(){ return cust_no; }
    public String getUse_seq(){ return use_seq; }
    public String getReceipt_no(){ return receipt_no; }
    public String getOrder_no(){ return order_no; }
    public String getRepay_no(){ return repay_no; }
    public String getProc_gb(){ return proc_gb; }
    public String getProc_yn(){ return proc_yn; }
    public String getTrans_cust_no(){ return trans_cust_no; }
    public String getDepositamt_seq(){ return depositamt_seq; }
    public String getDepositamt_gb(){ return depositamt_gb; }
    public double getUse_amt(){ return use_amt; }
    public String getProc_id(){ return proc_id; }
    public String getProc_date(){ return proc_date; }
    public String getRemark1_v(){ return remark1_v; }
    public String getRemark2_v(){ return remark2_v; }
    public long   getRemark3_n(){ return remark3_n; }
    public long   getRemark4_n(){ return remark4_n; }
    public String getReamrk5_d(){ return reamrk5_d; }
    public String getRemark6_d(){ return remark6_d; }


} 