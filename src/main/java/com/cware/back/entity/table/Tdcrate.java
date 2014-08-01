
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 할인율정보
*
* @version 1.0, 2006/08/30
* @author Commerceware Ins.
*/
public class Tdcrate extends BaseEntity {

public Tdcrate(){ super();}

    private String dc_type;
    private double margin_from;
    private double margin_to;
    private double dc_rate;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String dc_type_pk;
    private double margin_from_pk;

    /** Set Method **/
    public void setDc_type( String dc_type ){ this.dc_type = dc_type; }
    public void setMargin_from( double margin_from ){ this.margin_from = margin_from; }
    public void setMargin_to( double margin_to ){ this.margin_to = margin_to; }
    public void setDc_rate( double dc_rate ){ this.dc_rate = dc_rate; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    public void setDc_type_pk( String dc_type_pk ){ this.dc_type_pk = dc_type_pk; }
    public void setMargin_from_pk( double margin_from_pk ){ this.margin_from_pk = margin_from_pk; }

    /** Get Method **/
    public String getDc_type(){ return dc_type; }
    public double getMargin_from(){ return margin_from; }
    public double getMargin_to(){ return margin_to; }
    public double getDc_rate(){ return dc_rate; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
    public String getDc_type_pk(){ return dc_type_pk; }
    public double getMargin_from_pk(){ return margin_from_pk; }

} 