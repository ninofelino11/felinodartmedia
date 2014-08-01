
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Config 정보
* @version 1.0, 2006/07/07
*/
public class Tconfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Tconfig(){ super();}
	
    private String item;
    private String val;
    private String content;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setItem( String item ){ this.item = item; }
    public void setVal( String val ){ this.val = val; }
    public void setContent( String content ){ this.content = content; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getItem(){ return item; }
    public String getVal(){ return val; }
    public String getContent(){ return content; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


} 