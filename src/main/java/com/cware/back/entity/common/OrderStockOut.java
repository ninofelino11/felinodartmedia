
package com.cware.back.entity.common;

import com.cware.back.common.BaseEntity;

/**
* StockU
* 주문재고 검사 output beans
*
* @version 1.0, 2006/07/21
* @author  commerceware.co.kr
*/
public class OrderStockOut extends BaseEntity {

    public OrderStockOut(){ super();}

    private String stock_flag;
    private String stock_key;
    private String first_plan_date;
    private String out_plan_date;
    private String dely_hope_date;
    private String from_date;
    private String to_date;
    private long   order_qty;
    private String resultGeneral;
    private long   problemRow;
    private long   problemQty;
    private String resultSum;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private long   order_seq;
    private long   stock_qty;
    private String goods_select_no;
    
    private String wh_code;  /* add 2007.01.26 */

    /** Set Method **/
    public void setStock_flag       ( String stock_flag      ) { this.stock_flag      = stock_flag      ; }
    public void setStock_key        ( String stock_key       ) { this.stock_key       = stock_key       ; }
    public void setFirst_plan_date  ( String first_plan_date ) { this.first_plan_date = first_plan_date ; }
    public void setOut_plan_date    ( String out_plan_date   ) { this.out_plan_date   = out_plan_date   ; }
    public void setDely_hope_date   ( String dely_hope_date  ) { this.dely_hope_date  = dely_hope_date  ; }
    public void setFrom_date        ( String from_date       ) { this.from_date       = from_date       ; }
    public void setTo_date          ( String to_date         ) { this.to_date         = to_date         ; }
    public void setOrder_qty        ( long   order_qty       ) { this.order_qty       = order_qty       ; }
    public void setResultGeneral    ( String resultGeneral   ) { this.resultGeneral   = resultGeneral   ; }
    public void setProblemRow       ( long   problemRow      ) { this.problemRow      = problemRow      ; }
    public void setProblemQty       ( long   problemQty      ) { this.problemQty      = problemQty      ; }
    public void setResultSum        ( String resultSum       ) { this.resultSum       = resultSum       ; }
    public void setOrder_no         ( String order_no        ) { this.order_no        = order_no        ; }
    public void setOrder_g_seq      ( String order_g_seq     ) { this.order_g_seq     = order_g_seq     ; }
    public void setOrder_d_seq      ( String order_d_seq     ) { this.order_d_seq     = order_d_seq     ; }
    public void setOrder_w_seq      ( String order_w_seq     ) { this.order_w_seq     = order_w_seq     ; }
    public void setOrder_seq        ( long   order_seq       ) { this.order_seq       = order_seq       ; }
    public void setStock_qty        ( long   stock_qty       ) { this.stock_qty       = stock_qty       ; }
    public void setGoods_select_no  ( String goods_select_no ) { this.goods_select_no = goods_select_no ; }
                                                            
    public void setWh_code          ( String wh_code         ) { this.wh_code         = wh_code         ; } 

    /** Get Method **/
    public String getStock_flag      () { return stock_flag      ; }
    public String getStock_key       () { return stock_key       ; }
    public String getFirst_plan_date () { return first_plan_date ; }
    public String getOut_plan_date   () { return out_plan_date   ; }
    public String getDely_hope_date  () { return dely_hope_date  ; }
    public String getFrom_date       () { return from_date       ; }
    public String getTo_date         () { return to_date         ; }
    public long   getOrder_qty       () { return order_qty       ; }
    public String getResultGeneral   () { return resultGeneral   ; }
    public long   getProblemRow      () { return problemRow      ; }
    public long   getProblemQty      () { return problemQty      ; }
    public String getResultSum       () { return resultSum       ; }
    public String getOrder_no        () { return order_no        ; }
    public String getOrder_g_seq     () { return order_g_seq     ; }
    public String getOrder_d_seq     () { return order_d_seq     ; }
    public String getOrder_w_seq     () { return order_w_seq     ; }
    public long   getOrder_seq       () { return order_seq       ; }
    public long   getStock_qty       () { return stock_qty       ; }
    public String getGoods_select_no () { return goods_select_no ; }

    public String getWh_code         () { return wh_code         ; } 

}
