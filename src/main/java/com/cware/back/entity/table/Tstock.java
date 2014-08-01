
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tstock 정보
*
* @version 1.0, 2006/07/25
* @author  commerceware.co.kr
*/
public class Tstock extends BaseEntity {

    public Tstock(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private String wh_code;
    private long   aqty;                //double
    private long   bqty;                //double
    private long   balju_qty;           //double
    private long   eout_quest_aqty;     //double
    private long   eout_quest_bqty;     //double
    private String temp1;
    private String temp2;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setAqty( long aqty ){ this.aqty = aqty; }
    public void setBqty( long bqty ){ this.bqty = bqty; }
    public void setBalju_qty( long balju_qty ){ this.balju_qty = balju_qty; }
    public void setEout_quest_aqty( long eout_quest_aqty ){ this.eout_quest_aqty = eout_quest_aqty; }
    public void setEout_quest_bqty( long eout_quest_bqty ){ this.eout_quest_bqty = eout_quest_bqty; }
    public void setTemp1(String temp1) { this.temp1 = temp1; }
    public void setTemp2(String temp2) { this.temp2 = temp2; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getWh_code(){ return wh_code; }
    public long   getAqty(){ return aqty; }
    public long   getBqty(){ return bqty; }
    public long   getBalju_qty(){ return balju_qty; }
    public long   getEout_quest_aqty(){ return eout_quest_aqty; }
    public long   getEout_quest_bqty(){ return eout_quest_bqty; }
    public String getTemp1() { return temp1; }
    public String getTemp2() { return temp2; }
    
}