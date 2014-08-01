
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문진행
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Torderproc extends BaseEntity {

    public Torderproc(){ super();}

    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String order_p_seq;
    private String do_flag;
    private String proc_note;
    private String proc_date;
    private String proc_id;

    /** Set Method **/
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setOrder_d_seq( String order_d_seq ){ this.order_d_seq = order_d_seq; }
    public void setOrder_w_seq( String order_w_seq ){ this.order_w_seq = order_w_seq; }
    public void setOrder_p_seq( String order_p_seq ){ this.order_p_seq = order_p_seq; }
    public void setDo_flag( String do_flag ){ this.do_flag = do_flag; }
    public void setProc_note( String proc_note ){ this.proc_note = proc_note; }
    public void setProc_date( String proc_date ){ this.proc_date = proc_date; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }

    /** Get Method **/
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getOrder_d_seq(){ return order_d_seq; }
    public String getOrder_w_seq(){ return order_w_seq; }
    public String getOrder_p_seq(){ return order_p_seq; }
    public String getDo_flag(){ return do_flag; }
    public String getProc_note(){ return proc_note; }
    public String getProc_date(){ return proc_date; }
    public String getProc_id(){ return proc_id; }


} 