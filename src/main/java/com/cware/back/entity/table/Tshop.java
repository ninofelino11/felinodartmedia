package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* shop정보
*
* @version 1.0, 2007/06/05
* @author Commerceware Ins.
*/
public class Tshop extends BaseEntity {

    public Tshop(){ super();}

    private String shop_code;
    private String shop_name;
    private String wh_code;
    private String shop_cust_no;
    private String post_no;
    private String post_seq;
    private String addr;
    private String order_media;
    private String user_gb;
    private String dely_gb;
    private String receiver_gb;
    private String bank_code;
    private String bank_seq;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setShop_code     ( String shop_code )     { this.shop_code     = shop_code; }
    public void setShop_name     ( String shop_name )     { this.shop_name     = shop_name; }
    public void setWh_code       ( String wh_code )       { this.wh_code       = wh_code; }
    public void setShop_cust_no  ( String shop_cust_no )  { this.shop_cust_no  = shop_cust_no; }
    public void setPost_no       ( String post_no )       { this.post_no       = post_no; }
    public void setPost_seq      ( String post_seq )      { this.post_seq      = post_seq; }
    public void setAddr          ( String addr )          { this.addr          = addr; }
    public void setOrder_media   ( String order_media )   { this.order_media   = order_media; }
    public void setUser_gb       ( String user_gb )       { this.user_gb       = user_gb; }
    public void setDely_gb       ( String dely_gb )       { this.dely_gb       = dely_gb; }
    public void setReceiver_gb   ( String receiver_gb )   { this.receiver_gb   = receiver_gb; }
    public void setBank_code     ( String bank_code )     { this.bank_code     = bank_code; }
    public void setBank_seq      ( String bank_seq )      { this.bank_seq      = bank_seq; }
    public void setInsert_date   ( String insert_date )   { this.insert_date   = insert_date; }
    public void setInsert_id     ( String insert_id )     { this.insert_id     = insert_id; }
    public void setModify_date   ( String modify_date )   { this.modify_date   = modify_date; }
    public void setModify_id     ( String modify_id )     { this.modify_id     = modify_id; }

    /** Get Method **/
    public String getShop_code    (){ return shop_code; }
    public String getShop_name    (){ return shop_name; }
    public String getWh_code      (){ return wh_code; }
    public String getShop_cust_no (){ return shop_cust_no; }
    public String getPost_no      (){ return post_no; }
    public String getPost_seq     (){ return post_seq; }
    public String getAddr         (){ return addr; }
    public String getOrder_media  (){ return order_media; }
    public String getUser_gb      (){ return user_gb; }
    public String getDely_gb      (){ return dely_gb; }
    public String getReceiver_gb  (){ return receiver_gb; }
    public String getBank_code    (){ return bank_code; }
    public String getBank_seq     (){ return bank_seq; }
    public String getInsert_date  (){ return insert_date; }
    public String getInsert_id    (){ return insert_id; }
    public String getModify_date  (){ return modify_date; }
    public String getModify_id    (){ return modify_id; }


} 