
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 재고조정 정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tstockctrl extends BaseEntity {

public Tstockctrl(){ super();}

    private String ctrl_no;
    private String goods_code;
    private String goodsdt_code;
    private String wh_code;
    private String rack_code;
    private String check_code;
    private String ctrl_gb;
    private String insert_id;
    private String ctrl_date;
    private String ctrl_code;
    private String ctrl_note;
    private String rack_grade;
    private long rack_qty;
    private long stockcheck_qty;
    private long ctrl_qty;

    /** Set Method **/
    public void setCtrl_no( String ctrl_no ){ this.ctrl_no = ctrl_no; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setRack_code( String rack_code ){ this.rack_code = rack_code; }
    public void setCheck_code( String check_code ){ this.check_code = check_code; }
    public void setCtrl_gb( String ctrl_gb ){ this.ctrl_gb = ctrl_gb; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setCtrl_date( String ctrl_date ){ this.ctrl_date = ctrl_date; }
    public void setCtrl_code( String ctrl_code ){ this.ctrl_code = ctrl_code; }
    public void setCtrl_note( String ctrl_note ){ this.ctrl_note = ctrl_note; }
    public void setRack_grade( String rack_grade ){ this.rack_grade = rack_grade; }
    public void setRack_qty( long rack_qty ){ this.rack_qty = rack_qty; }
    public void setStockcheck_qty( long stockcheck_qty ){ this.stockcheck_qty = stockcheck_qty; }
    public void setCtrl_qty( long ctrl_qty ){ this.ctrl_qty = ctrl_qty; }

    /** Get Method **/
    public String getCtrl_no(){ return ctrl_no; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getWh_code(){ return wh_code; }
    public String getRack_code(){ return rack_code; }
    public String getCheck_code(){ return check_code; }
    public String getCtrl_gb(){ return ctrl_gb; }
    public String getInsert_id(){ return insert_id; }
    public String getCtrl_date(){ return ctrl_date; }
    public String getCtrl_code(){ return ctrl_code; }
    public String getCtrl_note(){ return ctrl_note; }
    public String getRack_grade(){ return rack_grade; }
    public long getRack_qty(){ return rack_qty; }
    public long getStockcheck_qty(){ return stockcheck_qty; }
    public long getCtrl_qty(){ return ctrl_qty; }

}