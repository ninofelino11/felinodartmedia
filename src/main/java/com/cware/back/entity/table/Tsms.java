/**
* SMS
*
* @version 1.0, 2008/07/02
* @author Commerceware Ins.
*/
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

public class Tsms extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Tsms(){ super();}

	private String sms_no;
	private String sms_flag;
	private String order_no;
	private String cust_no;
	private String cust_name;
	private String hp_no;
	private String slip_i_no;
	private String receipt_no;
	private String repay_no;
	private String callback;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String insert_date;
	
	/** Set Method **/
	public void setSms_no      ( String sms_no      ) { this.sms_no      = sms_no      ;}      
	public void setSms_flag    ( String sms_flag    ) { this.sms_flag    = sms_flag    ;}
	public void setOrder_no    ( String order_no    ) { this.order_no    = order_no    ;}
	public void setCust_no     ( String cust_no     ) { this.cust_no     = cust_no     ;}
	public void setCust_name   ( String cust_name   ) { this.cust_name   = cust_name   ;}
	public void setHp_no       ( String hp_no       ) { this.hp_no       = hp_no       ;}
	public void setSlip_i_no   ( String slip_i_no   ) { this.slip_i_no   = slip_i_no   ;}
	public void setReceipt_no  ( String receipt_no  ) { this.receipt_no  = receipt_no  ;}
	public void setRepay_no    ( String repay_no    ) { this.repay_no    = repay_no    ;}
	public void setCallback    ( String callback    ) { this.callback    = callback    ;}
	public void setRemark1     ( String remark1     ) { this.remark1     = remark1     ;}
	public void setRemark2     ( String remark2     ) { this.remark2     = remark2     ;}
	public void setRemark3     ( String remark3     ) { this.remark3     = remark3     ;}
	public void setRemark4     ( String remark4     ) { this.remark4     = remark4     ;}
	public void setInsert_date ( String insert_date ) { this.insert_date = insert_date ;}

	
	
	/** Get Method **/
	public String getSms_no      () { return this.sms_no     ;}      
	public String getSms_flag    () { return this.sms_flag   ;}
	public String getOrder_no    () { return this.order_no   ;}
	public String getCust_no     () { return this.cust_no    ;}
	public String getCust_name   () { return this.cust_name  ;}
	public String getHp_no       () { return this.hp_no      ;}
	public String getSlip_i_no   () { return this.slip_i_no  ;}
	public String getReceipt_no  () { return this.receipt_no ;}
	public String getRepay_no    () { return this.repay_no   ;}
	public String getCallback    () { return this.callback   ;}
	public String getRemark1     () { return this.remark1    ;}
	public String getRemark2     () { return this.remark2    ;}
	public String getRemark3     () { return this.remark3    ;}
	public String getRemark4     () { return this.remark4    ;}
	public String getInsert_date () { return this.insert_date;}

}
