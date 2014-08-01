
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tlevel 정보
*
* @version 1.0, 2006/07/25
* @author  commerceware.co.kr
*/
public class Tlevel extends BaseEntity {

    public Tlevel(){ super();}

    private String grade_ctrl_no;
    private String goods_code;
    private String goodsdt_code;
    private String wh_code;
    private String out_rack_code;
    private String in_rack_code;
    private String grade_ctrl_date;
    private String grade_ctrl_code;
    private String grade_ctrl_note;
    private long   grade_ctrl_qty;                //double
    private String out_grade;
    private String in_grade;
    private String med_flag;
    private String insert_id;

    /** Set Method **/
    public void setGrade_ctrl_no( String grade_ctrl_no ){ this.grade_ctrl_no = grade_ctrl_no; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setOut_rack_code( String out_rack_code ){ this.out_rack_code = out_rack_code; }
    public void setIn_rack_code( String in_rack_code ){ this.in_rack_code = in_rack_code; }
    public void setGrade_ctrl_date( String grade_ctrl_date ){ this.grade_ctrl_date = grade_ctrl_date; }
    public void setGrade_ctrl_code( String grade_ctrl_code ){ this.grade_ctrl_code = grade_ctrl_code; }
    public void setGrade_ctrl_note( String grade_ctrl_note ){ this.grade_ctrl_note = grade_ctrl_note; }
    public void setGrade_ctrl_qty( long grade_ctrl_qty ){ this.grade_ctrl_qty = grade_ctrl_qty; }
    public void setOut_grade( String out_grade ){ this.out_grade = out_grade; }
    public void setIn_grade( String in_grade ){ this.in_grade = in_grade; }
    public void setMed_flag( String med_flag ){ this.med_flag = med_flag; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    
    public String getGrade_ctrl_no(){ return grade_ctrl_no; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getWh_code(){ return wh_code; }
    public String getOut_rack_code(){ return out_rack_code; }
    public String getIn_rack_code(){ return in_rack_code; }
    public String getGrade_ctrl_date(){ return grade_ctrl_date; }
    public String getGrade_ctrl_code(){ return grade_ctrl_code; }
    public String getGrade_ctrl_note(){ return grade_ctrl_note; }
    public long   getGrade_ctrl_qty(){ return grade_ctrl_qty; }
    public String getOut_grade(){ return out_grade; }
    public String getIn_grade(){ return in_grade; }
    public String getMed_flag(){ return med_flag; }
    public String getInsert_id(){ return insert_id; }
    

}