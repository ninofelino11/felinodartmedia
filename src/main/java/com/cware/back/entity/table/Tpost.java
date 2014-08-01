
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TPost 정보
*
* @version 1.0, 2006/06/14
* @author  commerceware.co.kr [kim sungtaek; webzest@commerceware.co.kr]
*/
public class Tpost extends BaseEntity implements Cloneable{

    public Tpost(){ super();}

    private String post_no;
    private String post_seq;
    private String city_name;
    private String gu_name;
    private String dong_name;
    private String ddd;
    private String area_gb;
    private String post_gb;
    private String use_yn;
    private String delivery_point_count;
    private String note;
	private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setPost_no     ( String post_no ){ this.post_no = post_no; }
    public void setPost_seq    ( String post_seq ){ this.post_seq = post_seq; }
    public void setCity_name   ( String city_name ){ this.city_name = city_name; }
    public void setGu_name     ( String gu_name ){ this.gu_name = gu_name; }
    public void setDong_name   ( String dong_name ){ this.dong_name = dong_name; }
    public void setDdd         ( String ddd ){ this.ddd = ddd; }
    public void setArea_gb     ( String area_gb ){ this.area_gb = area_gb; }
    public void setPost_gb     ( String post_gb ){ this.post_gb = post_gb; }
    public void setUse_yn      ( String use_yn ){ this.use_yn = use_yn; }
    public void setDelivery_point_count      ( String delivery_point_count ){ this.delivery_point_count = delivery_point_count; }
    public void setInsert_date ( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id   ( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date ( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id   ( String modify_id ){ this.modify_id = modify_id; }

    public String getPost_no     (){ return post_no; }
    public String getPost_seq    (){ return post_seq; }
    public String getCity_name   (){ return city_name; }
    public String getGu_name     (){ return gu_name; }
    public String getDong_name   (){ return dong_name; }
    public String getDdd         (){ return ddd; }
    public String getArea_gb     (){ return area_gb; }
    public String getPost_gb     (){ return post_gb; }
    public String getUse_yn      (){ return use_yn; }
    public String getDelivery_point_count      (){ return delivery_point_count; }
    public String getInsert_date (){ return insert_date; }
    public String getInsert_id   (){ return insert_id; }
    public String getModify_date (){ return modify_date; }
    public String getModify_id   (){ return modify_id; }
    public String getNote() {return note;	}
	public void setNote(String note) {	this.note = note;	}

    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }


}