
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* MD
*
* @version 1.0, 2006/07/20
* @author Commerceware Ins.
*/
public class Trackcode extends BaseEntity {

    public Trackcode(){ super();}

    private String rack_code;
    private String wh_code;
    private String rack_grade;
    private String rack_note;
    private String rack_man_id;
    private String use_yn;
    private String rack_barcode;
    private String rack_gb;
    private String insert_date;
    private String insert_id;
    private long   catacity_qty;
    private String code2;
    private String code3;
        

    /** Set Method **/
    public void setRack_code( String rack_code ){ this.rack_code = rack_code; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setRack_grade( String rack_grade ){ this.rack_grade = rack_grade; }
    public void setRack_note( String rack_note ){ this.rack_note = rack_note; }
    public void setRack_man_id( String rack_man_id ){ this.rack_man_id = rack_man_id; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setRack_barcode( String rack_barcode ){ this.rack_barcode = rack_barcode; }
    public void setRack_gb( String rack_gb ){ this.rack_gb = rack_gb; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setCatacity_qty(long catacity_qty) { this.catacity_qty = catacity_qty; }
    public void setCode2( String code2 ){ this.code2 = code2; }
    public void setCode3( String code3 ){ this.code3 = code3; }

    /** Get Method **/
    public String getRack_code(){ return rack_code; }
    public String getWh_code(){ return wh_code; }
    public String getRack_grade(){ return rack_grade; }
    public String getRack_note(){ return rack_note; }
    public String getRack_man_id(){ return rack_man_id; }
    public String getUse_yn(){ return use_yn; }
    public String getRack_barcode(){ return rack_barcode; }
    public String getRack_gb(){ return rack_gb; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public long   getCatacity_qty() { return catacity_qty; }
    public String getCode2(){ return code2; }
    public String getCode3(){ return code3; }

}
