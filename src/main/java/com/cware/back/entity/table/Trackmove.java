
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Trackbuff 정보
*
* @version 1.0, 2007/04/11
* @author  commerceware.co.kr
*/
public class Trackmove extends BaseEntity {

    public Trackmove(){ super();}

    private String rack_move_no;
    private String goods_code;
    private String goodsdt_code;
    private String wh_code;
    private String out_rack_code;
    private String out_grade;
    private String in_rack_code;
    private String in_grade;
    private long   move_qty;        //
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setRack_move_no( String rack_move_no ){ this.rack_move_no = rack_move_no; }            
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }        
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }                
    public void setOut_rack_code( String out_rack_code ){ this.out_rack_code = out_rack_code; }                    
    public void setOut_grade( String out_grade ){ this.out_grade = out_grade; }                    
    public void setIn_rack_code( String in_rack_code ){ this.in_rack_code = in_rack_code; }                        
    public void setIn_grade( String in_grade ){ this.in_grade = in_grade; }                        
    public void setMove_qty( long move_qty ){ this.move_qty = move_qty; }              
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }    
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }  

    public String getRack_move_no(){ return rack_move_no; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getWh_code(){ return wh_code; }
    public String getOut_rack_code(){ return out_rack_code; }
    public String getOut_grade(){ return out_grade; }
    public String getIn_rack_code(){ return in_rack_code; }
    public String getIn_grade(){ return in_grade; }
    public long   getMove_qty(){ return move_qty; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    
}