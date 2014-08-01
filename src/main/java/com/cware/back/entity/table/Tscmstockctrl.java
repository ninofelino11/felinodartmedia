
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SCM재고조정내역
*
* @version 1.0, 2009/06/02
* @author Commerceware Ins.
*/
public class Tscmstockctrl extends BaseEntity {

	private static final long serialVersionUID = 1L;


	public Tscmstockctrl(){ super();}

    private String ctrl_no;
    private	String ctrl_date;
    private String entp_code;
    private String grade;
    private String goods_code;
    private String goodsdt_code;
    private long   stock_qty;
    private long   ctrl_qty;
    private String ctrl_note;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setCtrl_no( String ctrl_no ){ this.ctrl_no = ctrl_no; }
    public void setCtrl_date( String ctrl_date ){ this.ctrl_date = ctrl_date; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setGrade( String grade ){ this.grade = grade; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setStock_qty( long stock_qty ){ this.stock_qty = stock_qty; }
    public void setCtrl_qty( long ctrl_qty ){ this.ctrl_qty = ctrl_qty; }
    public void setCtrl_note( String ctrl_note ){ this.ctrl_note = ctrl_note; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }


    /** Get Method **/
    public String getCtrl_no(){ return ctrl_no; }
    public String getCtrl_date(){ return ctrl_date; }
    public String getEntp_code(){ return entp_code; }
    public String getGrade(){ return grade; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public long getStock_qty(){ return stock_qty; }
    public long getCtrl_qty(){ return ctrl_qty; }
    public String getCtrl_note(){ return ctrl_note; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

}