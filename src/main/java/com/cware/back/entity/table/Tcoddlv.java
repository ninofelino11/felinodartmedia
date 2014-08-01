
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tcoddlv 정보
*
* @version 1.0, 2006/08/03
* @author  commerceware.co.kr
*/
public class Tcoddlv extends BaseEntity {
    public Tcoddlv(){ super();}
  
    private String slip_i_no      ;
    private String slip_no        ;
    private String order_no       ;
    private String order_g_seq    ;
    private String order_d_seq    ;
    private String order_w_seq    ;
    private String out_date       ;
    private String dely_flag      ;
    private String not_dely_code  ;
    private String dely_date      ;
    private String d_receive_date ;
    private String d_insert_date  ;
    private String d_insert_id    ;
    private String decision       ;
    private String receipt_date   ;
    private String r_receive_date ;
    private String r_insert_date  ;
    private String r_insert_id    ;
    private String receive_yn     ;
    private double receipt_amt    ;
    

    /** Set Method **/
    public void setSlip_i_no      ( String slip_i_no     ){ this.slip_i_no      = slip_i_no     ; }  
    public void setSlip_no        ( String slip_no       ){ this.slip_no        = slip_no       ; }
    public void setOrder_no       ( String order_no      ){ this.order_no       = order_no      ; }
    public void setOrder_g_seq    ( String order_g_seq   ){ this.order_g_seq    = order_g_seq   ; }
    public void setOrder_d_seq    ( String order_d_seq   ){ this.order_d_seq    = order_d_seq   ; }
    public void setOrder_w_seq    ( String order_w_seq   ){ this.order_w_seq    = order_w_seq   ; }
    public void setOut_date       ( String out_date      ){ this.out_date       = out_date      ; }
    public void setDely_flag      ( String dely_flag     ){ this.dely_flag      = dely_flag     ; }
    public void setNot_dely_code  ( String not_dely_code ){ this.not_dely_code  = not_dely_code ; }
    public void setDely_date      ( String dely_date     ){ this.dely_date      = dely_date     ; }
    public void setD_receive_date ( String d_receive_date){ this.d_receive_date = d_receive_date; }
    public void setD_insert_date  ( String d_insert_date ){ this.d_insert_date  = d_insert_date ; }
    public void setD_insert_id    ( String d_insert_id   ){ this.d_insert_id    = d_insert_id   ; }
    public void setDecision       ( String decision      ){ this.decision       = decision      ; }
    public void setReceipt_date   ( String receipt_date  ){ this.receipt_date   = receipt_date  ; }
    public void setR_receive_date ( String r_receive_date){ this.r_receive_date = r_receive_date; }
    public void setR_insert_date  ( String r_insert_date ){ this.r_insert_date  = r_insert_date ; }
    public void setR_insert_id    ( String r_insert_id   ){ this.r_insert_id    = r_insert_id   ; }
    public void setReceive_yn     ( String receive_yn    ){ this.receive_yn     = receive_yn    ; }
    public void setreceipt_amt    ( double receipt_amt   ){ this.receipt_amt    = receipt_amt   ; }
        
    /** get**/
    public String getSlip_i_no        (){ return slip_i_no     ; }
    public String getSlip_no          (){ return slip_no       ; }
    public String getOrder_no         (){ return order_no      ; }
    public String getOrder_g_seq      (){ return order_g_seq   ; }
    public String getOrder_d_seq      (){ return order_d_seq   ; }
    public String getOrder_w_seq      (){ return order_w_seq   ; }
    public String getOut_date         (){ return out_date      ; }
    public String getDely_flag        (){ return dely_flag     ; }
    public String getNot_dely_code    (){ return not_dely_code ; }
    public String getDely_date        (){ return dely_date     ; }
    public String getD_receive_date   (){ return d_receive_date; }
    public String getD_insert_date    (){ return d_insert_date ; }
    public String getD_insert_id      (){ return d_insert_id   ; }
    public String getDecision         (){ return decision      ; }
    public String getReceipt_date     (){ return receipt_date  ; }
    public String getR_receive_date   (){ return r_receive_date; }
    public String getR_insert_date    (){ return r_insert_date ; }
    public String getR_insert_id      (){ return r_insert_id   ; }
    public String getReceive_yn       (){ return receive_yn    ; }
    public double getreceipt_amt      (){ return receipt_amt   ; }
   
}