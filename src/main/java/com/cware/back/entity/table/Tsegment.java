
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SEGMENT 정보
*
* @version 1.0, 2006/11/23
* @author Commerceware Ins.
*/
public class Tsegment extends BaseEntity {

public Tsegment(){ super();}

    private String segment_code;
    private String segment_name;
    private long   target_qty;
    private String memo;
    private String select_cond;
    private String insert_date;
    private String insert_id;
    private String modify_id;

    /** Set Method **/
    public void setSegment_code( String segment_code ){ this.segment_code = segment_code; }
    public void setSegment_name( String segment_name ){ this.segment_name = segment_name; }
    public void setTarget_qty( long target_qty ){ this.target_qty = target_qty; }
    public void setMemo( String memo ){ this.memo = memo; }
    public void setSelect_cond( String select_cond ){ this.select_cond = select_cond; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getSegment_code(){ return segment_code; }
    public String getSegment_name(){ return segment_name; }
    public long   getTarget_qty(){ return target_qty; }
    public String getMemo(){ return memo; }
    public String getSelect_cond(){ return select_cond; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_id(){ return modify_id; }

} 