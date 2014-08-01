package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TUSERTRACKING
*
* @version 1.0, 2010/10/28
* @author Commerceware Ins.
*/
public class Tusertracking extends BaseEntity {

public Tusertracking(){ super();}

    private String user_id;
    private String ip_addr;
    private String session_id;
    private String command;
    private String program_id;
    private String insert_date;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ipAddr) {
		ip_addr = ipAddr;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String sessionId) {
		session_id = sessionId;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getProgram_id() {
		return program_id;
	}
	public void setProgram_id(String programId) {
		program_id = programId;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}
    
    

} 