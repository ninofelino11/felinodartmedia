
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SEGMENTOPHIS 정보
*
* @version 1.0, 2007/04/13
* @author Commerceware Ins.
*/
public class Tsegmentophis extends BaseEntity {

public Tsegmentophis(){ super();}

    private String segment_code1;
    private String segment_code2;
    private String segment_code_ret;
    private String op_code;
    private String insert_date;
    private String insert_id;
    private String segment_name;
    private String memo;

    /** Set Method **/
    public void setSegment_code1( String segment_code1 ){ this.segment_code1 = segment_code1; }
    public void setSegment_code2( String segment_code2 ){ this.segment_code2 = segment_code2; }
    public void setSegment_code_ret( String segment_code_ret ){ this.segment_code_ret = segment_code_ret; }
    public void setOp_code( String op_code ){ this.op_code = op_code; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setSegment_name( String segment_name ){ this.segment_name = segment_name; }
    public void setMemo( String memo ){ this.memo = memo; }
    
    /** Get Method **/
    public String getSegment_code1(){ return segment_code1; }
    public String getSegment_code2(){ return segment_code2; }
    public String getSegment_code_ret(){ return segment_code_ret; }
    public String getOp_code(){ return op_code; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getSegment_name(){ return segment_name; }
    public String getMemo(){ return memo; }

} 