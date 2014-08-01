
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* boardinfo 정보
* @version 1.0, 2006/12/04
*/
public class Tboardinfo extends BaseEntity {

    public Tboardinfo(){ super();}
    
    private String board_code;
    private String board_desc;
    private String display_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
    public String getBoard_code() {
        return board_code;
    }
    public String getBoard_desc() {
        return board_desc;
    }
    public String getDisplay_yn() {
        return display_yn;
    }
    public String getInsert_date() {
        return insert_date;
    }
    public String getInsert_id() {
        return insert_id;
    }
    public String getModify_date() {
        return modify_date;
    }
    public String getModify_id() {
        return modify_id;
    }
    
    
    public void setBoard_code(String board_code) {
        this.board_code = board_code;
    }
    public void setBoard_desc(String board_desc) {
        this.board_desc = board_desc;
    }
    public void setDisplay_yn(String display_yn) {
        this.display_yn = display_yn;
    }
    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }
    public void setInsert_id(String insert_id) {
        this.insert_id = insert_id;
    }
    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }
    public void setModify_id(String modify_id) {
        this.modify_id = modify_id;
    }

    

} 