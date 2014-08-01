
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 고객상담 처리자
*
* @version 1.0, 2006/07/19
* @author Commerceware Ins.
*/
public class Tcounseluser extends BaseEntity {

    public Tcounseluser(){ super();}

    private String proc_lgroup_fr;
    private String proc_mgroup_fr;
    private String proc_lgroup_to;
    private String proc_mgroup_to;
    private String dely_type;
    private String proc_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setProc_lgroup_fr( String proc_lgroup_fr ){ this.proc_lgroup_fr = proc_lgroup_fr; }
    public void setProc_mgroup_fr( String proc_mgroup_fr ){ this.proc_mgroup_fr = proc_mgroup_fr; }
    public void setProc_lgroup_to( String proc_lgroup_to ){ this.proc_lgroup_to = proc_lgroup_to; }
    public void setProc_mgroup_to( String proc_mgroup_to ){ this.proc_mgroup_to = proc_mgroup_to; }
    public void setDely_type( String dely_type ){ this.dely_type = dely_type; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getProc_lgroup_fr(){ return proc_lgroup_fr; }
    public String getProc_mgroup_fr(){ return proc_mgroup_fr; }
    public String getProc_lgroup_to(){ return proc_lgroup_to; }
    public String getProc_mgroup_to(){ return proc_mgroup_to; }
    public String getDely_type(){ return dely_type; }
    public String getProc_id(){ return proc_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 