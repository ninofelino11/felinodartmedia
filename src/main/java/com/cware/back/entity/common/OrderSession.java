
package com.cware.back.entity.common;

import com.cware.back.common.BaseEntity;

/**
* OrderSession
* 주문 저장시에 필요한 값 세팅
* 
* @version 1.0, 2006/07/18
* @author  commerceware.co.kr
*/
public class OrderSession extends BaseEntity {

    public OrderSession(){ super();}

    private String order_no    = "";
    private String order_date  = "";
    private String cust_no     = "";
    
    /** Set Method **/
    public void setOrder_no     ( String order_no     ) { this.order_no     = order_no     ; }
    public void setOrder_date   ( String order_date   ) { this.order_date   = order_date   ; }    
    public void setCust_no      ( String cust_no      ) { this.cust_no      = cust_no      ; }    

    /** Get Method **/
    public String getOrder_no     () { return order_no     ; }
    public String getOrder_date   () { return order_date   ; }    
    public String getCust_no      () { return cust_no      ; }        

        
}