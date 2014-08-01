
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로모션 취소정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpromocancel extends BaseEntity {

public Tpromocancel(){ super();}

    private String order_no;
    private String cancel_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setCancel_yn( String cancel_yn ){ this.cancel_yn = cancel_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getOrder_no(){ return order_no; }
    public String getCancel_yn(){ return cancel_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

} 