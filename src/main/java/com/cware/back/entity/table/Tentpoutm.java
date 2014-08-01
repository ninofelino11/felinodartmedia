
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 업체확정M 정보
*
* @version 1.0, 2006/08/09
* @author Commerceware Ins.
*/
public class Tentpoutm extends BaseEntity {

public Tentpoutm(){ super();}

    private String eout_no;
    private String eout_quest_no;
    private String wh_code;
    private String eout_gb;
    private String eout_med;
    private String eout_date;
    private String eout_man_id;
    private String eout_proc_id;
    private String eout_receiver;
    private String eout_prt_cnt;
    private String eout_remark;
    private String insert_date;

    /** Set Method **/
    public void setEout_no      ( String eout_no )      { this.eout_no       = eout_no; }
    public void setEout_quest_no( String eout_quest_no ){ this.eout_quest_no = eout_quest_no; }
    public void setWh_code      ( String wh_code )      { this.wh_code       = wh_code; }
    public void setEout_gb      ( String eout_gb )      { this.eout_gb       = eout_gb; }
    public void setEout_med     ( String eout_med )     { this.eout_med      = eout_med; }
    public void setEout_date    ( String eout_date )    { this.eout_date     = eout_date; }
    public void setEout_man_id  ( String eout_man_id )  { this.eout_man_id   = eout_man_id; }
    public void setEout_proc_id ( String eout_proc_id ) { this.eout_proc_id  = eout_proc_id; }
    public void setEout_receiver( String eout_receiver ){ this.eout_receiver = eout_receiver; }
    public void setEout_prt_cnt ( String eout_prt_cnt ) { this.eout_prt_cnt  = eout_prt_cnt; }
    public void setEout_remark  ( String eout_remark )  { this.eout_remark   = eout_remark; }
    public void setInsert_date  ( String insert_date )  { this.insert_date   = insert_date; }

    /** Get Method **/
    public String getEout_no      (){ return eout_no; }
    public String getEout_quest_no(){ return eout_quest_no; }
    public String getWh_code      (){ return wh_code; }
    public String getEout_gb      (){ return eout_gb; }
    public String getEout_med     (){ return eout_med; }
    public String getEout_date    (){ return eout_date; }
    public String getEout_man_id  (){ return eout_man_id; }
    public String getEout_proc_id (){ return eout_proc_id; }
    public String getEout_receiver(){ return eout_receiver; }
    public String getEout_prt_cnt (){ return eout_prt_cnt; }
    public String getEout_remark  (){ return eout_remark; }
    public String getInsert_date  (){ return insert_date; }

} 