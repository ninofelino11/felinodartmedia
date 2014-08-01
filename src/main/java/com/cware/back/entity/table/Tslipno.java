
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 운송장
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tslipno extends BaseEntity {

public Tslipno(){ super();}

    private String slipno_seq;
    private String dely_gb;
    private String wh_code;
    private String start_slipno;
    private String end_slipno;
    private String use_slipno;
    private String use_yn;
    private String insert_date;
    private String insert_id;
    private String proc_date;
    private String proc_id;
    private String proc_yn;

    /** Set Method **/
    public void setSlipno_seq( String slipno_seq ){ this.slipno_seq = slipno_seq; }
    public void setDely_gb( String dely_gb ){ this.dely_gb = dely_gb; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setStart_slipno( String start_slipno ){ this.start_slipno = start_slipno; }
    public void setEnd_slipno( String end_slipno ){ this.end_slipno = end_slipno; }
    public void setUse_slipno( String use_slipno ){ this.use_slipno = use_slipno; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setProc_date( String proc_date ){ this.proc_date = proc_date; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }
    public void setProc_yn( String proc_yn ){ this.proc_yn = proc_yn; }

    /** Get Method **/
    public String getSlipno_seq(){ return slipno_seq; }
    public String getDely_gb(){ return dely_gb; }
    public String getWh_code(){ return wh_code; }
    public String getStart_slipno(){ return start_slipno; }
    public String getEnd_slipno(){ return end_slipno; }
    public String getUse_slipno(){ return use_slipno; }
    public String getUse_yn(){ return use_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getProc_date(){ return proc_date; }
    public String getProc_id(){ return proc_id; }
    public String getProc_yn(){ return proc_yn; }

} 