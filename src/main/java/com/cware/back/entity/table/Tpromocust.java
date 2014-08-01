package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로모션 당첨 고객정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpromocust extends BaseEntity {

public Tpromocust(){ super();}

    private String promo_no;
    private String base_neg_flag;
    private String cust_no;
    private String remark;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setBase_neg_flag( String base_neg_flag ){ this.base_neg_flag = base_neg_flag; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getPromo_no(){ return promo_no; }
    public String getBase_neg_flag(){ return base_neg_flag; }
    public String getCust_no(){ return cust_no; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

} 