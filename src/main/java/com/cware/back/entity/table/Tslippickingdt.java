
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tslippickingdt 정보
*
* @version 1.0, 2006/07/31
* @author  commerceware.co.kr
*/
public class Tslippickingdt extends BaseEntity {
    public Tslippickingdt(){ super();}

    private String picking_seq ;
    private String rack_code   ;
    private String goods_code  ;
    private String goodsdt_code;
    private long   picking_qty ;
    private long   reserv_qty  ;
    private long   del_qty  ;

    /** Set Method **/
    public void setPicking_seq  ( String picking_seq  ){ this.picking_seq  = picking_seq  ; }
    public void setRack_code    ( String rack_code    ){ this.rack_code    = rack_code    ; }
    public void setGoods_code   ( String goods_code   ){ this.goods_code   = goods_code   ; }
    public void setGoodsdt_code ( String goodsdt_code ){ this.goodsdt_code = goodsdt_code ; }
    public void setPicking_qty  ( long   picking_qty  ){ this.picking_qty  = picking_qty  ; }
    public void setReserv_qty   ( long   reserv_qty   ){ this.reserv_qty   = reserv_qty   ; }
    public void setDel_qty      ( long   del_qty      ){ this.del_qty      = del_qty      ; }

    public String getPicking_seq  (){ return picking_seq  ; }
    public String getRack_code    (){ return rack_code    ; }
    public String getGoods_code   (){ return goods_code   ; }
    public String getGoodsdt_code (){ return goodsdt_code ; }
    public long   getPicking_qty  (){ return picking_qty  ; }
    public long   getReserv_qty   (){ return reserv_qty   ; }
    public long   getDel_qty      (){ return del_qty      ; }

}