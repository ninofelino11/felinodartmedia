
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 공지상세 정보
*
* @version 1.0, 2006/07/14
*/
public class Tboardcontentd extends BaseEntity {

    public Tboardcontentd(){ super();}

    private String board_no;
    private String category_code;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setBoard_no( String board_no ){ this.board_no = board_no; }
    public void setCategory_code( String category_code ){ this.category_code = category_code; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getBoard_no(){ return board_no; }
    public String getCategory_code(){ return category_code; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


} 