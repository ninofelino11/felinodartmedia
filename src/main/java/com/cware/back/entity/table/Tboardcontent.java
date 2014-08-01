
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 공지 정보
*
* @version 1.0, 2006/07/14
*/
public class Tboardcontent extends BaseEntity {

    public Tboardcontent(){ super();}

    private String board_no;
    private String board_code;
    private String board_title;
    private String board_content;
    private String search_cnt;
    private String board_kinds;
    private String insert_name;
    private String target_url;
    private String display_start_date;
    private String display_end_date;
    private String delete_yn;
    private String topdisplay_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;


    /** Set Method **/
    public void setBoard_no( String board_no ){ this.board_no = board_no; }
    public void setBoard_code( String board_code ){ this.board_code = board_code; }
    public void setBoard_title( String board_title ){ this.board_title = board_title; }
    public void setBoard_content( String board_content ){ this.board_content = board_content; }
    public void setSearch_cnt( String search_cnt ){ this.search_cnt = search_cnt; }
    public void setBoard_kinds( String board_kinds ){ this.board_kinds = board_kinds; }
    public void setInsert_name( String insert_name ){ this.insert_name = insert_name; }
    public void setTarget_url( String target_url ){ this.target_url = target_url; }
    public void setDisplay_start_date( String display_start_date ){ this.display_start_date = display_start_date; }
    public void setDisplay_end_date( String display_end_date ){ this.display_end_date = display_end_date; }
    public void setDelete_yn( String delete_yn ){ this.delete_yn = delete_yn; }
    public void setTopdisplay_yn( String topdisplay_yn ){ this.topdisplay_yn = topdisplay_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModifyDate( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getBoard_no(){ return board_no; }
    public String getBoard_code(){ return board_code; }
    public String getBoard_title(){ return board_title; }
    public String getBoard_content(){ return board_content; }
    public String getSearch_cnt(){ return search_cnt; }
    public String getBoard_kinds(){ return board_kinds; }
    public String getInsert_name(){ return insert_name; }
    public String getTarget_url(){ return target_url; }
    public String getDisplay_start_date(){ return display_start_date; }
    public String getDisplay_end_date(){ return display_end_date; }
    public String getDelete_yn(){ return delete_yn; }
    public String getTopdisplay_yn(){ return topdisplay_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


} 