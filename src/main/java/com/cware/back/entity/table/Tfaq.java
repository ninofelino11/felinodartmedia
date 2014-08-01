
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* faq
*
* @version 1.0, 2006/12/05
* @author Commerceware Ins.
*/
public class Tfaq extends BaseEntity {

    public Tfaq(){ super();}

    private long   faq_seq;
    private String faq_kinds;
    private String faq_code;
    private String question;
    private String answer;
    private long   search_cnt;
    private String display_yn;
    private long   display_priority;
    private String quick_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
    
    public String getAnswer() {
        return answer;
    }
    public long getDisplay_priority() {
        return display_priority;
    }
    public String getDisplay_yn() {
        return display_yn;
    }
    public String getFaq_code() {
        return faq_code;
    }
    public String getFaq_kinds() {
        return faq_kinds;
    }
    public long getFaq_seq() {
        return faq_seq;
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
    public String getQuestion() {
        return question;
    }
    public String getQuick_yn() {
        return quick_yn;
    }
    public long getSearch_cnt() {
        return search_cnt;
    }
    
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void setDisplay_priority(long display_priority) {
        this.display_priority = display_priority;
    }
    public void setDisplay_yn(String display_yn) {
        this.display_yn = display_yn;
    }
    public void setFaq_code(String faq_code) {
        this.faq_code = faq_code;
    }
    public void setFaq_kinds(String faq_kinds) {
        this.faq_kinds = faq_kinds;
    }
    public void setFaq_seq(long faq_seq) {
        this.faq_seq = faq_seq;
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
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setQuick_yn(String quick_yn) {
        this.quick_yn = quick_yn;
    }
    public void setSearch_cnt(long search_cnt) {
        this.search_cnt = search_cnt;
    }

    
} 