
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* cms 기본정보
*
* @version 1.0, 2006/08/02
* @author Commerceware Ins.
*/
public class Tcms extends BaseEntity {

public Tcms(){ super();}

    private String cms_no;
    private String link_gb;
    private String tr_code;
    private String entp_code;
    private String bank_code;
    private String msg_code;
    private String msg_gubun;
    private String send_cnt;
    private String data_cnt;
    private String serial_no;
    private String data_no;
    private String send_date;
    private String bank_account;
    private String transaction_gubun;
    private String send_bank;
    private double send_amt;
    private double remain_amt;
    private String giro_code;
    private String cust_name;
    private String phone_resi_no;
    private String cms_auto_yn;
    private String trans_date;
    private String virtual_yn;

    /** Set Method **/
    public void setCms_no           ( String cms_no )           { this.cms_no            = cms_no; }
    public void setLink_gb          ( String link_gb )          { this.link_gb           = link_gb; }
    public void setTr_code          ( String tr_code )          { this.tr_code           = tr_code; }
    public void setEntp_code        ( String entp_code )        { this.entp_code         = entp_code; }
    public void setBank_code        ( String bank_code )        { this.bank_code         = bank_code; }
    public void setMsg_code         ( String msg_code )         { this.msg_code          = msg_code; }
    public void setMsg_gubun        ( String msg_gubun )        { this.msg_gubun         = msg_gubun; }
    public void setSend_cnt         ( String send_cnt )         { this.send_cnt          = send_cnt; }
    public void setData_cnt         ( String data_cnt )         { this.data_cnt          = data_cnt; }
    public void setSerial_no        ( String serial_no )        { this.serial_no         = serial_no; }
    public void setData_no          ( String data_no )          { this.data_no           = data_no; }
    public void setSend_date        ( String send_date )        { this.send_date         = send_date; }
    public void setBank_account     ( String bank_account )     { this.bank_account      = bank_account; }
    public void setTransaction_gubun( String transaction_gubun ){ this.transaction_gubun = transaction_gubun; }
    public void setSend_bank        ( String send_bank )        { this.send_bank         = send_bank; }
    public void setSend_amt         ( double   send_amt )         { this.send_amt          = send_amt; }
    public void setRemain_amt       ( double   remain_amt )       { this.remain_amt        = remain_amt; }
    public void setGiro_code        ( String giro_code )        { this.giro_code         = giro_code; }
    public void setCust_name        ( String cust_name )        { this.cust_name         = cust_name; }
    public void setPhone_resi_no    ( String phone_resi_no )    { this.phone_resi_no     = phone_resi_no; }
    public void setCms_auto_yn      ( String cms_auto_yn )      { this.cms_auto_yn       = cms_auto_yn; }
    public void setTrans_date       ( String trans_date )       { this.trans_date        = trans_date; }
    public void setVirtual_yn       ( String virtual_yn )       { this.virtual_yn        = virtual_yn; }

    /** Get Method **/
    public String getCms_no           (){ return cms_no; }
    public String getLink_gb          (){ return link_gb; }
    public String getTr_code          (){ return tr_code; }
    public String getEntp_code        (){ return entp_code; }
    public String getBank_code        (){ return bank_code; }
    public String getMsg_code         (){ return msg_code; }
    public String getMsg_gubun        (){ return msg_gubun; }
    public String getSend_cnt         (){ return send_cnt; }
    public String getData_cnt         (){ return data_cnt; }
    public String getSerial_no        (){ return serial_no; }
    public String getData_no          (){ return data_no; }
    public String getSend_date        (){ return send_date; }
    public String getBank_account     (){ return bank_account; }
    public String getTransaction_gubun(){ return transaction_gubun; }
    public String getSend_bank        (){ return send_bank; }
    public double getSend_amt         (){ return send_amt; }
    public double getRemain_amt       (){ return remain_amt; }
    public String getGiro_code        (){ return giro_code; }
    public String getCust_name        (){ return cust_name; }
    public String getPhone_resi_no    (){ return phone_resi_no; }
    public String getCms_auto_yn      (){ return cms_auto_yn; }
    public String getTrans_date       (){ return trans_date; }
    public String getVirtual_yn       (){ return virtual_yn; }

} 