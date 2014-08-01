package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 추첨프로모션 대상 고객정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tlotterypromocust extends BaseEntity {

public Tlotterypromocust(){ super();}

	private String seq;
    private String lottery_promo_no;
    private String cust_no;
    private String order_no;
    private String promo_no;
    private String cancel_yn;
    private String cancel_date;
    private String cancel_id;
    private String remark;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setSeq( String seq ){ this.seq = seq; }
    public void setLottery_promo_no( String lottery_promo_no ){ this.lottery_promo_no = lottery_promo_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setCancel_yn( String cancel_yn ){ this.cancel_yn = cancel_yn; }
    public void setCancel_date( String cancel_date ){ this.cancel_date = cancel_date; }
    public void setCancel_id( String cancel_id ){ this.cancel_id = cancel_id; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getSeq(){ return seq; }
    public String getLottery_promo_no(){ return lottery_promo_no; }
    public String getCust_no(){ return cust_no; }
    public String getOrder_no(){ return order_no; }
    public String getPromo_no(){ return promo_no; }
    public String getCancel_yn(){ return cancel_yn; }
    public String getCancel_date(){ return cancel_date; }
    public String getCancel_id(){ return cancel_id; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

} 