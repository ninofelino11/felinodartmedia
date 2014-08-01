
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 창고이동상세정보
*
* @version 1.0, 2009/03/30
* @author Commerceware Ins.
*/
public class Twhmoveoutdt extends BaseEntity {

    public Twhmoveoutdt(){ super();}

    private String move_out_seq;
    private String goods_code;
    private String goodsdt_code;
    private Long request_aqty;
    private Long request_bqty;
    private Long out_aqty;
    private Long out_bqty;

    /** Set Method **/
    public void setMove_out_seq  	( String move_out_seq )  	{ this.move_out_seq   	= move_out_seq; }
    public void setGoods_code     	( String goods_code )     	{ this.goods_code     	= goods_code; }
    public void setGoodsdt_code   	( String goodsdt_code )   	{ this.goodsdt_code    	= goodsdt_code; }
    public void setRequest_aqty		( Long request_aqty )		{ this.request_aqty 	= request_aqty; }
    public void setRequest_bqty		( Long request_bqty )		{ this.request_bqty 	= request_bqty; }
    public void setOut_aqty      	( Long out_aqty )      	{ this.out_aqty       	= out_aqty; }
    public void setOut_bqty      	( Long out_bqty )      	{ this.out_bqty       	= out_bqty; }

    /** Get Method **/
    public String getMove_out_seq(){ return move_out_seq; }
    public String getGoods_code  (){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public Long getRequest_aqty(){ return request_aqty; }
    public Long getRequest_bqty(){ return request_bqty; }
    public Long getOut_aqty    (){ return out_aqty; }
    public Long getOut_bqty    (){ return out_bqty; }


}