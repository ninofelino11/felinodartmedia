
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 카드청구정보
*
* @version 1.0, 2006/08/17
* @author Commerceware Ins.
*/
public class Tcardreq extends BaseEntity {


    public Tcardreq(){ super();}

    private String request_no    ;
    private String cust_no       ;
    private String order_no      ;
    private String receipt_no    ;
    private String request_gb    ;
    private String card_code     ;
    private String card_no       ;
    private double request_amt   ;
    private String valid_date    ;
    private long   pay_month     ;
    private String norest_yn     ;
    private double norest_rate   ;
    private double norest_amt    ;
    private double charge_rate   ;
    private double charge        ;
    private String ok_no         ;
    private String ok_date       ;
    private String business_no   ;
    private String join_no       ;
    private String trans_yn      ;
    private String trans_date    ;
    private String end_date      ;
    private String end_id        ;
    private String retrun_gb     ;
    private String request_result;
    private String result_note   ;
    private String result_gb     ;
    private long   redemand_cnt  ;
    private String insert_date   ;
    private String insert_id     ;
    private String modify_date   ;
    private String modify_id     ;
    private String close_yn      ;
    private String ctrl_reason   ;
    private String return_date   ;
    private String remark1_v     ;
    private String remark2_v     ;
    private long   remark3_n     ;
    private long   remark4_n     ;
    private String remark5_d     ;
    private String remark6_d     ;

    /** Set Method **/
    public void setRequest_no    ( String request_no     ){ this.request_no     = request_no    ; }
    public void setCust_no       ( String cust_no        ){ this.cust_no        = cust_no       ; }
    public void setOrder_no      ( String order_no       ){ this.order_no       = order_no      ; }
    public void setReceipt_no    ( String receipt_no     ){ this.receipt_no     = receipt_no    ; }
    public void setRequest_gb    ( String request_gb     ){ this.request_gb     = request_gb    ; }
    public void setCard_code     ( String card_code      ){ this.card_code      = card_code     ; }
    public void setCard_no       ( String card_no        ){ this.card_no        = card_no       ; }
    public void setRequest_amt   ( double   request_amt    ){ this.request_amt    = request_amt   ; }
    public void setValid_date    ( String valid_date     ){ this.valid_date     = valid_date    ; }
    public void setPay_month     ( long   pay_month      ){ this.pay_month      = pay_month     ; }
    public void setNorest_yn     ( String norest_yn      ){ this.norest_yn      = norest_yn     ; }
    public void setNorest_rate   ( double norest_rate    ){ this.norest_rate    = norest_rate   ; }
    public void setNorest_amt    ( double   norest_amt     ){ this.norest_amt     = norest_amt    ; }
    public void setCharge_rate   ( double charge_rate    ){ this.charge_rate    = charge_rate   ; }
    public void setCharge        ( double charge         ){ this.charge         = charge        ; }
    public void setOk_no         ( String ok_no          ){ this.ok_no          = ok_no         ; }
    public void setOk_date       ( String ok_date        ){ this.ok_date        = ok_date       ; }
    public void setBusiness_no   ( String business_no    ){ this.business_no    = business_no   ; }
    public void setJoin_no       ( String join_no        ){ this.join_no        = join_no       ; }
    public void setTrans_yn      ( String trans_yn       ){ this.trans_yn       = trans_yn      ; }
    public void setTrans_date    ( String trans_date     ){ this.trans_date     = trans_date    ; }
    public void setEnd_date      ( String end_date       ){ this.end_date       = end_date      ; }
    public void setEnd_id        ( String end_id         ){ this.end_id         = end_id        ; }
    public void setRetrun_gb     ( String retrun_gb      ){ this.retrun_gb      = retrun_gb     ; }
    public void setRequest_result( String request_result ){ this.request_result = request_result; }
    public void setResult_note   ( String result_note    ){ this.result_note    = result_note   ; }
    public void setResult_gb     ( String result_gb      ){ this.result_gb      = result_gb     ; }
    public void setRedemand_cnt  ( long   redemand_cnt   ){ this.redemand_cnt   = redemand_cnt  ; }
    public void setInsert_date   ( String insert_date    ){ this.insert_date    = insert_date   ; }
    public void setInsert_id     ( String insert_id      ){ this.insert_id      = insert_id     ; }
    public void setModify_date   ( String modify_date    ){ this.modify_date    = modify_date   ; }
    public void setModify_id     ( String modify_id      ){ this.modify_id      = modify_id     ; }
    public void setClose_yn      ( String close_yn       ){ this.close_yn       = close_yn      ; }
    public void setCtrl_reason   ( String ctrl_reason    ){ this.ctrl_reason    = ctrl_reason   ; }
    public void setReturn_date   ( String return_date    ){ this.return_date    = return_date   ; }
    public void setRemark1_v     ( String remark1_v      ){ this.remark1_v      = remark1_v     ; }
    public void setRemark2_v     ( String remark2_v      ){ this.remark2_v      = remark2_v     ; }
    public void setRemark3_n     ( long   remark3_n      ){ this.remark3_n      = remark3_n     ; }
    public void setRemark4_n     ( long   remark4_n      ){ this.remark4_n      = remark4_n     ; }
    public void setRemark5_d     ( String remark5_d      ){ this.remark5_d      = remark5_d     ; }
    public void setRemark6_d     ( String remark6_d      ){ this.remark6_d      = remark6_d     ; }

    /** Get Method **/
    public String getRequest_no    (){ return request_no    ; }
    public String getCust_no       (){ return cust_no       ; }
    public String getOrder_no      (){ return order_no      ; }
    public String getReceipt_no    (){ return receipt_no    ; }
    public String getRequest_gb    (){ return request_gb    ; }
    public String getCard_code     (){ return card_code     ; }
    public String getCard_no       (){ return card_no       ; }
    public double   getRequest_amt   (){ return request_amt   ; }
    public String getValid_date    (){ return valid_date    ; }
    public long   getPay_month     (){ return pay_month     ; }
    public String getNorest_yn     (){ return norest_yn     ; }
    public double getNorest_rate   (){ return norest_rate   ; }
    public double   getNorest_amt    (){ return norest_amt    ; }
    public double getCharge_rate   (){ return charge_rate   ; }
    public double getCharge        (){ return charge        ; }
    public String getOk_no         (){ return ok_no         ; }
    public String getOk_date       (){ return ok_date       ; }
    public String getBusiness_no   (){ return business_no   ; }
    public String getJoin_no       (){ return join_no       ; }
    public String getTrans_yn      (){ return trans_yn      ; }
    public String getTrans_date    (){ return trans_date    ; }
    public String getEnd_date      (){ return end_date      ; }
    public String getEnd_id        (){ return end_id        ; }
    public String getRetrun_gb     (){ return retrun_gb     ; }
    public String getRequest_result(){ return request_result; }
    public String getResult_note   (){ return result_note   ; }
    public String getResult_gb     (){ return result_gb     ; }
    public long   getRedemand_cnt  (){ return redemand_cnt  ; }
    public String getInsert_date   (){ return insert_date   ; }
    public String getInsert_id     (){ return insert_id     ; }
    public String getModify_date   (){ return modify_date   ; }
    public String getModify_id     (){ return modify_id     ; }
    public String getClose_yn      (){ return close_yn      ; }
    public String getCtrl_reason   (){ return ctrl_reason   ; }
    public String getReturn_date   (){ return return_date   ; }
    public String getRemark1_v     (){ return remark1_v     ; }
    public String getRemark2_v     (){ return remark2_v     ; }
    public long   getRemark3_n     (){ return remark3_n     ; }
    public long   getRemark4_n     (){ return remark4_n     ; }
    public String getRemark5_d     (){ return remark5_d     ; }
    public String getRemark6_d     (){ return remark6_d     ; }

}