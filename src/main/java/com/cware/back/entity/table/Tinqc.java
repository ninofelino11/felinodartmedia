
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 반입검사
*
* @version 1.0, 2006/07/20
* @author Commerceware Ins.
*/
public class Tinqc extends BaseEntity {

    public Tinqc(){ super();}

    private String inqc_no;
    private String balju_no;
    private String goods_code;
    private String goodsdt_code;
    private String inqc_date;
    private String in_date;
    private String inqc_id;
    private String in_qty;
    private String inqc_qty;
    private String inqc_bad_qty;
    private String inqc_result_code;
    private String inqc_note;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setInqc_no         ( String inqc_no )         { this.inqc_no          = inqc_no; }
    public void setBalju_no        ( String balju_no )        { this.balju_no         = balju_no; }
    public void setGoods_code      ( String goods_code )      { this.goods_code       = goods_code; }
    public void setGoodsdt_code    ( String goodsdt_code )    { this.goodsdt_code     = goodsdt_code; }
    public void setInqc_date       ( String inqc_date )       { this.inqc_date        = inqc_date; }
    public void setIn_date         ( String in_date )         { this.in_date          = in_date; }
    public void setInqc_id         ( String inqc_id )         { this.inqc_id          = inqc_id; }
    public void setIn_qty          ( String in_qty )          { this.in_qty           = in_qty; }
    public void setInqc_qty        ( String inqc_qty )        { this.inqc_qty         = inqc_qty; }
    public void setInqc_bad_qty    ( String inqc_bad_qty )    { this.inqc_bad_qty     = inqc_bad_qty; }
    public void setInqc_result_code( String inqc_result_code ){ this.inqc_result_code = inqc_result_code; }
    public void setInqc_note       ( String inqc_note )       { this.inqc_note        = inqc_note; }
    public void setModify_date     ( String modify_date )     { this.modify_date      = modify_date; }
    public void setModify_id       ( String modify_id )       { this.modify_id        = modify_id; }

    /** Get Method **/
    public String getInqc_no         (){ return inqc_no; }
    public String getBalju_no        (){ return balju_no; }
    public String getGoods_code      (){ return goods_code; }
    public String getGoodsdt_code    (){ return goodsdt_code; }
    public String getInqc_date       (){ return inqc_date; }
    public String getIn_date         (){ return in_date; }
    public String getInqc_id         (){ return inqc_id; }
    public String getIn_qty          (){ return in_qty; }
    public String getInqc_qty        (){ return inqc_qty; }
    public String getInqc_bad_qty    (){ return inqc_bad_qty; }
    public String getInqc_result_code(){ return inqc_result_code; }
    public String getInqc_note       (){ return inqc_note; }
    public String getModify_date     (){ return modify_date; }
    public String getModify_id       (){ return modify_id; }


} 