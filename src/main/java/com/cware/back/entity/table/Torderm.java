
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문 Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Torderm extends BaseEntity {

    public Torderm(){ super();}

    private String order_no;
    private String cust_no;
    private String order_date;
    private String order_media;
    private String insert_id;
    private String password;
    private String with_code;
    private String ip_addr;
    private String memb_gb;
    private String employee_id;

    /** Set Method **/
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setOrder_date( String order_date ){ this.order_date = order_date; }
    public void setOrder_media( String order_media ){ this.order_media = order_media; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setPassword( String password ){ this.password = password; }
    public void setWith_code( String with_code ){ this.with_code = with_code; }
    public void setIp_addr( String ip_addr ){ this.ip_addr = ip_addr; }
    public void setMemb_gb(String memb_gb) {this.memb_gb = memb_gb;	}


    /** Get Method **/
    public String getOrder_no(){ return order_no; }
    public String getCust_no(){ return cust_no; }
    public String getOrder_date(){ return order_date; }
    public String getOrder_media(){ return order_media; }
    public String getInsert_id(){ return insert_id; }
    public String getPassword(){ return password; }
    public String getWith_code(){ return with_code; }
    public String getIp_addr(){ return ip_addr; }
    public String getMemb_gb() {return memb_gb;	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}



}