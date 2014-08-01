
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TBank 정보
*
* @version 1.0, 2006/07/18
* @author  commerceware.co.kr [oh jungmin; jumgmin@commerceware.co.kr]
*/
public class Tbank extends BaseEntity {

    public Tbank(){ super();}

    private String bank_code;
    private String bank_name;
    private String use_yn;
    private String remark;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setBank_code   ( String bank_code ){ this.bank_code = bank_code; }
    public void setBank_name   ( String bank_name ){ this.bank_name = bank_name; }
    public void setUse_yn      ( String use_yn ){ this.use_yn = use_yn; }
    public void setRemark      ( String remark ){ this.remark = remark; }
    public void setInsert_date ( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id   ( String insert_id ){ this.insert_id = insert_id; }

    public String getBank_code   (){ return bank_code; }
    public String getBank_name   (){ return bank_name; }
    public String getUse_yn      (){ return use_yn; }
    public String getRemark      (){ return remark; }
    public String getInsert_date (){ return insert_date; }
    public String getInsert_id   (){ return insert_id; }


}