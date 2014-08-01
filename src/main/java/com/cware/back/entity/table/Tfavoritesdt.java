
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TFAVORITESDT
*
* @version 1.0, 2011/01/14
* @author Commerceware Ins.
*/
public class Tfavoritesdt extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Tfavoritesdt(){ super();}

    private String user_id;
    private String group_code;
    private String program_id;
    private String sort;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setUser_Id( String user_id ){ this.user_id = user_id; }
    public void setGroup_Code( String group_code ){ this.group_code = group_code; }
    public void setProgram_Id( String program_id ){ this.program_id = program_id; }
    public void setSort( String sort ){ this.sort = sort; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getUser_Id(){ return user_id; }
    public String getGroup_Code(){ return group_code; }
    public String getProgram_Id(){ return program_id; }
    public String getSort(){ return sort; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


}
