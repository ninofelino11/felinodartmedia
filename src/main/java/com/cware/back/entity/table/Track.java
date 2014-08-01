
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Track 정보
*
* @version 1.0, 2006/07/25
* @author  commerceware.co.kr
*/
public class Track extends BaseEntity {

    public Track(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private String rack_code;
    private String wh_code;
    private long rack_qty;        //double
    private long picking_qty;     //double

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setRack_code( String rack_code ){ this.rack_code = rack_code; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setRack_qty( long rack_qty ){ this.rack_qty = rack_qty; }
    public void setPicking_qty( long picking_qty ){ this.picking_qty = picking_qty; }

    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getRack_code(){ return rack_code; }
    public String getWh_code(){ return wh_code; }
    public long getRack_qty(){ return rack_qty; }
    public long getPicking_qty(){ return picking_qty; }


}