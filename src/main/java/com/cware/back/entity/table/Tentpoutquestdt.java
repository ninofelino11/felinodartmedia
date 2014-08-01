
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 업체반출요청상세정보
*
* @version 1.0, 2006/07/12
* @author Commerceware Ins.
*/
public class Tentpoutquestdt extends BaseEntity {

    public Tentpoutquestdt(){ super();}

    private String eout_quest_no;
    private String goods_code;
    private String goodsdt_code;
    private String eout_quest_aqty;
    private String eout_quest_bqty;
    private String eout_code;
    private String eout_note;

    /** Set Method **/
    public void setEout_quest_no  ( String eout_quest_no )  { this.eout_quest_no   = eout_quest_no; }
    public void setGoods_code     ( String goods_code )     { this.goods_code      = goods_code; }
    public void setGoodsdt_code   ( String goodsdt_code )   { this.goodsdt_code    = goodsdt_code; }
    public void setEout_quest_aqty( String eout_quest_aqty ){ this.eout_quest_aqty = eout_quest_aqty; }
    public void setEout_quest_bqty( String eout_quest_bqty ){ this.eout_quest_bqty = eout_quest_bqty; }
    public void setEout_code      ( String eout_code )      { this.eout_code       = eout_code; }
    public void setEout_note      ( String eout_note )      { this.eout_note       = eout_note; }

    /** Get Method **/
    public String getEout_quest_no  (){ return eout_quest_no; }
    public String getGoods_code     (){ return goods_code; }
    public String getGoodsdt_code   (){ return goodsdt_code; }
    public String getEout_quest_aqty(){ return eout_quest_aqty; }
    public String getEout_quest_bqty(){ return eout_quest_bqty; }
    public String getEout_code      (){ return eout_code; }
    public String getEout_note      (){ return eout_note; }


} 