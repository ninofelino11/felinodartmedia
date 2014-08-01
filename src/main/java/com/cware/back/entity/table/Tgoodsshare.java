
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품정보공유 TGOODSSHARE
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tgoodsshare extends BaseEntity {

public Tgoodsshare(){ super();}

    private String goods_code;
    private long seq_no;
    private String title;
    private String contents;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String delete_yn;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setSeq_no( long seq_no ){ this.seq_no = seq_no; }
    public void setTitle( String title ){ this.title = title; }
    public void setContents( String contents ){ this.contents = contents; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    public void setDelete_yn( String delete_yn ){ this.delete_yn = delete_yn; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public long   getSeq_no(){ return seq_no; }
    public String getTitle(){ return title; }
    public String getContents(){ return contents; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
    public String getDelete_yn(){ return delete_yn; }

} 