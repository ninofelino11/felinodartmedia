
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 업체확정Dt 정보
*
* @version 1.0, 2006/08/09
* @author Commerceware Ins.
*/
public class Tentpoutdt extends BaseEntity {

public Tentpoutdt(){ super();}

    private String eout_no;
    private String goods_code;
    private String goodsdt_code;
    private long   eout_aqty;
    private long   eout_bqty;
    private String eout_code;
    private String eout_note;

    /** Set Method **/
    public void setEout_no     ( String eout_no )     { this.eout_no      = eout_no; }
    public void setGoods_code  ( String goods_code )  { this.goods_code   = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setEout_aqty   ( long   eout_aqty )   { this.eout_aqty    = eout_aqty; }
    public void setEout_bqty   ( long   eout_bqty )   { this.eout_bqty    = eout_bqty; }
    public void setEout_code   ( String eout_code )   { this.eout_code    = eout_code; }
    public void setEout_note   ( String eout_note )   { this.eout_note    = eout_note; }

    /** Get Method **/
    public String getEout_no     (){ return eout_no; }
    public String getGoods_code  (){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public long   getEout_aqty   (){ return eout_aqty; }
    public long   getEout_bqty   (){ return eout_bqty; }
    public String getEout_code   (){ return eout_code; }
    public String getEout_note   (){ return eout_note; }

} 