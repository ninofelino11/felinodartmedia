
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SEGMENT 정보
*
* @version 1.0, 2006/11/23
* @author Commerceware Ins.
*/
public class Tsegmentcust extends BaseEntity {

public Tsegmentcust(){ super();}

    private String segment_code;
    private String cust_no;

    /** Set Method **/
    public void setSegment_code( String segment_code ){ this.segment_code = segment_code; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }

    /** Get Method **/
    public String getSegment_code(){ return segment_code; }
    public String getCust_no(){ return cust_no; }

} 