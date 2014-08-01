
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tscmstock 정보
*
* @version 1.0, 2009/06/03
* @author  commerceware.co.kr
*/
public class Tscmstock extends BaseEntity {

	private static final long serialVersionUID = 1L;

    public Tscmstock(){ super();}

    private String entp_code;
    private String goods_code;
    private String goodsdt_code;
    private long   aqty;                //double
    private long   bqty;                //double

    /** Set Method **/
    public void setEntp_Code( String entp_code ){ this.entp_code = entp_code; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setAqty( long aqty ){ this.aqty = aqty; }
    public void setBqty( long bqty ){ this.bqty = bqty; }

    /** Get Method **/
    public String getEntp_Code(){ return entp_code; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public long   getAqty(){ return aqty; }
    public long   getBqty(){ return bqty; }

}