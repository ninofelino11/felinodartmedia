
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tentpslip
*
* @version 1.0, 2006/07/31
*/
public class Tentpslip extends BaseEntity {

    public Tentpslip(){ super();}

    private String entpslip_no;
    private String entpslip_flag;
    private String entp_code;
    private String entp_ok;
    private long   send_no;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;


    /** Set Method **/
    public void setEntpslip_no( String entpslip_no ){ this.entpslip_no = entpslip_no; }
    public void setEntpslip_flag( String entpslip_flag ){ this.entpslip_flag = entpslip_flag; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setEntp_ok( String entp_ok ){ this.entp_ok = entp_ok; }
    public void setSend_no( long send_no ){ this.send_no = send_no; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModifyDate( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getEntpslip_no(){ return entpslip_no; }
    public String getEntpslip_flag(){ return entpslip_flag; }
    public String getEntp_code(){ return entp_code; }
    public String getEntp_ok(){ return entp_ok; }
    public long   getSend_no(){ return send_no; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
    
    private String rtnCode;
    private String rtnMsg;
    
    public void setRtnSetting   ( String rtnCode, String rtnMsg ) {
        this.rtnCode = rtnCode;
        this.rtnMsg  = rtnMsg;
    }
    public String getRtnCode () { return this.rtnCode; }
    public String getRtnMsg  () { return this.rtnMsg;  }


} 