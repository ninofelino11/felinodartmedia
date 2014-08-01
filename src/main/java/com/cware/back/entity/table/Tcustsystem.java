
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 고객SYSTEM
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcustsystem extends BaseEntity {

    public Tcustsystem(){ super();}

    private String cust_no;
    private String cust_gb;
    private String memb_gb;
    private String cust_warning;
    private String cust_char;
    private double use_deposit;
    private double use_pb_deposit;
    private double use_saveamt;
    private double grant_saveamt;
    private double return_saveamt;
    private double usable_saveamt;
    private double tot_saveamt;
    private String dm_yn;
    private String dm_no_gb;
    private String dm_no_id;
    private String dm_no_date;
    private String first_order_date;
    private String last_order_date;
    private String first_msale_gb;
    private long   tot_order_cnt;
    private double tot_order_amt;
    private long   tot_cancel_cnt;
    private double tot_cancel_amt;
    private long   tot_return_cnt;
    private double tot_return_amt;
    private String birthday_mmdd;
    private String modify_date;
    private String modify_id;
    private String org_cust_gb;
    private String convert_date;
    

    /** Set Method **/
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setCust_gb( String cust_gb ){ this.cust_gb = cust_gb; }
    public void setMemb_gb( String memb_gb ){ this.memb_gb = memb_gb; }
    public void setCust_warning( String cust_warning ){ this.cust_warning = cust_warning; }
    public void setCust_char( String cust_char ){ this.cust_char = cust_char; }
    public void setUse_deposit( double use_deposit ){ this.use_deposit = use_deposit; }
    public void setUse_pb_deposit( double use_pb_deposit ){ this.use_pb_deposit = use_pb_deposit; }
    public void setUse_saveamt( double use_saveamt ){ this.use_saveamt = use_saveamt; }
    public void setGrant_saveamt( double grant_saveamt ){ this.grant_saveamt = grant_saveamt; }
    public void setReturn_saveamt( double return_saveamt ){ this.return_saveamt = return_saveamt; }
    public void setUsable_saveamt( double usable_saveamt ){ this.usable_saveamt = usable_saveamt; }
    public void setTot_saveamt( double tot_saveamt ){ this.tot_saveamt = tot_saveamt; }
    public void setDm_yn( String dm_yn ){ this.dm_yn = dm_yn; }
    public void setDm_no_gb( String dm_no_gb ){ this.dm_no_gb = dm_no_gb; }
    public void setDm_no_id( String dm_no_id ){ this.dm_no_id = dm_no_id; }
    public void setDm_no_date( String dm_no_date ){ this.dm_no_date = dm_no_date; }
    public void setFirst_order_date( String first_order_date ){ this.first_order_date = first_order_date; }
    public void setLast_order_date( String last_order_date ){ this.last_order_date = last_order_date; }
    public void setFirst_msale_gb( String first_msale_gb ){ this.first_msale_gb = first_msale_gb; }
    public void setTot_order_cnt( long   tot_order_cnt ){ this.tot_order_cnt = tot_order_cnt; }
    public void setTot_order_amt( double tot_order_amt ){ this.tot_order_amt = tot_order_amt; }
    public void setTot_cancel_cnt( long   tot_cancel_cnt ){ this.tot_cancel_cnt = tot_cancel_cnt; }
    public void setTot_cancel_amt( double tot_cancel_amt ){ this.tot_cancel_amt = tot_cancel_amt; }
    public void setTot_return_cnt( long   tot_return_cnt ){ this.tot_return_cnt = tot_return_cnt; }
    public void setTot_return_amt( double tot_return_amt ){ this.tot_return_amt = tot_return_amt; }
    public void setBirthday_mmdd( String birthday_mmdd ){ this.birthday_mmdd = birthday_mmdd; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    public void setOrg_cust_gb( String org_cust_gb ){ this.org_cust_gb = org_cust_gb; }
    public void setConvert_date( String convert_date ){ this.convert_date = convert_date; }
    

    /** Get Method **/
    public String getCust_no(){ return cust_no; }
    public String getCust_gb(){ return cust_gb; }
    public String getMemb_gb(){ return memb_gb; }
    public String getCust_warning(){ return cust_warning; }
    public String getCust_char(){ return cust_char; }
    public double getUse_deposit(){ return use_deposit; }
    public double getUse_pb_deposit(){ return use_pb_deposit; }
    public double getUse_saveamt(){ return use_saveamt; }
    public double getGrant_saveamt(){ return grant_saveamt; }
    public double getReturn_saveamt(){ return return_saveamt; }
    public double getUsable_saveamt(){ return usable_saveamt; }
    public double getTot_saveamt(){ return tot_saveamt; }
    public String getDm_yn(){ return dm_yn; }
    public String getDm_no_gb(){ return dm_no_gb; }
    public String getDm_no_id(){ return dm_no_id; }
    public String getDm_no_date(){ return dm_no_date; }
    public String getFirst_order_date(){ return first_order_date; }
    public String getLast_order_date(){ return last_order_date; }
    public String getFirst_msale_gb(){ return first_msale_gb; }
    public long   getTot_order_cnt(){ return tot_order_cnt; }
    public double getTot_order_amt(){ return tot_order_amt; }
    public long   getTot_cancel_cnt(){ return tot_cancel_cnt; }
    public double getTot_cancel_amt(){ return tot_cancel_amt; }
    public long   getTot_return_cnt(){ return tot_return_cnt; }
    public double getTot_return_amt(){ return tot_return_amt; }
    public String getBirthday_mmdd(){ return birthday_mmdd; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
    public String getOrg_cust_gb(){ return org_cust_gb; }
    public String getConvert_date(){ return convert_date; }
    


} 