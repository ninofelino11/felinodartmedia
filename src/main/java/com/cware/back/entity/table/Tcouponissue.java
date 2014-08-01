
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 쿠폰 발행
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcouponissue extends BaseEntity {

    public Tcouponissue(){ super();}

    private String seq;
    private String promo_no;
    private String cust_no;
    private String get_order_no;
    private String use_yn;
    private String use_order_no;
    private String cancel_yn;
    private String remark;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String use_start_date;
    private String use_end_date;


    /** Set Method **/
    public void setSeq( String seq ){ this.seq = seq; }
    public void setPromo_no( String promo_no ){ this.promo_no = promo_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setGet_order_no( String get_order_no ){ this.get_order_no = get_order_no; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setUse_order_no( String use_order_no ){ this.use_order_no = use_order_no; }
    public void setCancel_yn( String cancel_yn ){ this.cancel_yn = cancel_yn; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getSeq(){ return seq; }
    public String getPromo_no(){ return promo_no; }
    public String getCust_no(){ return cust_no; }
    public String getGet_order_no(){ return get_order_no; }
    public String getUse_yn(){ return use_yn; }
    public String getUse_order_no(){ return use_order_no; }
    public String getCancel_yn(){ return cancel_yn; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

    /* extra property */
    private String get_order_g_seq;
    public void setGet_order_g_seq( String get_order_g_seq ){ this.get_order_g_seq = get_order_g_seq; }
    public String getGet_order_g_seq(){ return get_order_g_seq; }

	public String getUse_start_date() {
		return use_start_date;
	}
	public void setUse_start_date(String useStartDate) {
		use_start_date = useStartDate;
	}
	public String getUse_end_date() {
		return use_end_date;
	}
	public void setUse_end_date(String useEndDate) {
		use_end_date = useEndDate;
	}

}