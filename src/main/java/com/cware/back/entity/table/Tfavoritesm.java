
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TFAVORITESM
*
* @version 1.0, 2011/01/14
* @author Commerceware Ins.
*/
public class Tfavoritesm extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Tfavoritesm(){ super();}

    private String user_id;
    private String group_code;
    private String group_name;
    private String p_group_id;
    private String sort;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setUser_Id( String user_id ){ this.user_id = user_id; }
    public void setGroup_Code( String group_code ){ this.group_code = group_code; }
    public void setGroup_Name( String group_name ){ this.group_name = group_name; }
    public void setP_Group_Id( String p_group_id ){ this.p_group_id = p_group_id; }
    public void setSort( String sort ){ this.sort = sort; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getUser_Id(){ return user_id; }
    public String getGroup_Code(){ return group_code; }
    public String getGroup_Name(){ return group_name; }
    public String getP_Group_Id(){ return p_group_id; }
    public String getSort(){ return sort; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


}
