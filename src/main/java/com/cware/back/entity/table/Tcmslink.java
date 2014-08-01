
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* cms link 정보
*
* @version 1.0, 2006/08/02
* @author Commerceware Ins.
*/
public class Tcmslink extends BaseEntity {

public Tcmslink(){ super();}

    private String cms_no;
    private String receipt_no;
    private String link_gb;
    private String link_no;
    private String serial_no;
    private String data_no;
    private double link_amt;
    private double repay_pb_amt;
    private String link_date;
    private String link_id;

    /** Set Method **/
    public void setCms_no      ( String cms_no )      { this.cms_no       = cms_no; }
    public void setReceipt_no  ( String receipt_no )  { this.receipt_no   = receipt_no; }
    public void setLink_gb     ( String link_gb )     { this.link_gb      = link_gb; }
    public void setLink_no     ( String link_no )     { this.link_no      = link_no; }
    public void setSerial_no   ( String serial_no )   { this.serial_no    = serial_no; }
    public void setData_no     ( String data_no )     { this.data_no      = data_no; }
    public void setLink_amt    ( double link_amt )    { this.link_amt     = link_amt; }
    public void setRepay_pb_amt( double repay_pb_amt ){ this.repay_pb_amt = repay_pb_amt; }
    public void setLink_date   ( String link_date )   { this.link_date    = link_date; }
    public void setLink_id     ( String link_id )     { this.link_id      = link_id; }

    /** Get Method **/
    public String getCms_no      (){ return cms_no; }
    public String getReceipt_no  (){ return receipt_no; }
    public String getLink_gb     (){ return link_gb; }
    public String getLink_no     (){ return link_no; }
    public String getSerial_no   (){ return serial_no; }
    public String getData_no     (){ return data_no; }
    public double getLink_amt    (){ return link_amt; }
    public double getRepay_pb_amt(){ return repay_pb_amt; }
    public String getLink_date   (){ return link_date; }
    public String getLink_id     (){ return link_id; }

} 