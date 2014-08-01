
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 발주Detail
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tbaljudt extends BaseEntity {

public Tbaljudt(){ super();}

    private String balju_no;
    private String goods_code;
    private String goodsdt_code;
    private String inqc_no;
    private long   balju_qty;
    private long   entp_conf_qty;
    private String entp_conf_note;

    /** Set Method **/
    public void setBalju_no( String balju_no ){ this.balju_no = balju_no; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setInqc_no( String inqc_no ){ this.inqc_no = inqc_no; }
    public void setBalju_qty( long balju_qty ){ this.balju_qty = balju_qty; }
    public void setEntp_conf_qty( long entp_conf_qty ){ this.entp_conf_qty = entp_conf_qty; }
    public void setEntp_conf_note( String entp_conf_note ){ this.entp_conf_note = entp_conf_note; }

    /** Get Method **/
    public String getBalju_no(){ return balju_no; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getInqc_no(){ return inqc_no; }
    public long   getBalju_qty(){ return balju_qty; }
    public long   getEntp_conf_qty(){ return entp_conf_qty; }
    public String getEntp_conf_note(){ return entp_conf_note; }

} 