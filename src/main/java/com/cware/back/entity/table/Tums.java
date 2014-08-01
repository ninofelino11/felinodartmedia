
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* UMS
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tums extends BaseEntity {

    public Tums(){ super();}

    private String ums_no;
    private String ums_flag;
    private String order_no;
    private String cust_no;
    private String slip_i_no;
    private String receipt_no;
    private String repay_no;
    private String attach_yn;
    private String file_name;
    private String remark1;
    private String remark2;
    private String insert_date;

    /** Set Method **/
    public void setUms_no( String ums_no ){ this.ums_no = ums_no; }
    public void setUms_flag( String ums_flag ){ this.ums_flag = ums_flag; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setSlip_i_no( String slip_i_no ){ this.slip_i_no = slip_i_no; }
    public void setReceipt_no( String receipt_no ){ this.receipt_no = receipt_no; }
    public void setRepay_no( String repay_no ){ this.repay_no = repay_no; }
    public void setAttach_yn( String attach_yn ){ this.attach_yn = attach_yn; }
    public void setFile_name( String file_name ){ this.file_name = file_name; }
    public void setRemark1( String remark1 ){ this.remark1 = remark1; }
    public void setRemark2( String remark2 ){ this.remark2 = remark2; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }

    /** Get Method **/
    public String getUms_no(){ return ums_no; }
    public String getUms_flag(){ return ums_flag; }
    public String getOrder_no(){ return order_no; }
    public String getCust_no(){ return cust_no; }
    public String getSlip_i_no(){ return slip_i_no; }
    public String getReceipt_no(){ return receipt_no; }
    public String getRepay_no(){ return repay_no; }
    public String getAttach_yn(){ return attach_yn; }
    public String getFile_name(){ return file_name; }
    public String getRemark1(){ return remark1; }
    public String getRemark2(){ return remark2; }
    public String getInsert_date(){ return insert_date; }


} 