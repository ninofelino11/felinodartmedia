
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.HashMap;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tuser;
import com.cware.back.service.common.UserSvc;

/**
 * User Action class
 *
 * @version 1.0, 2006/05/08
 * @author kim sungtaek [webzest@commerceware.co.kr]
 */
public class UserAct {

//    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

	public UserAct()	{ }

    public Tuser retrieveTuser(String poolName, String user_id, String passwd, String ip_addr) throws StoreException {
        Connection con  = null;
        Tuser      user = null;
        try{
            con   = DBUtils.getConnection(Construct.DB_POOL_NAME);
            UserSvc usersvc = new UserSvc();
            user = usersvc.retrieveTuser(con, user_id);
            if(user.getUser_id().equals("") || !user.getPasswd().equals(passwd)){
                user.setRtnSetting("000002", ComUtils.getMessage("msg.check_pass"));
                DBUtils.insertTlogin(con, user_id, ip_addr, "Login Fail", user_id + " / " + passwd);
            }else{
                user.setRtnSetting("000000"," ");
                DBUtils.insertTlogin(con, user_id, ip_addr, "Login Success", "");

                //= set default wh_code of user
                //= chekc user_gb = tshop.shop_id (SHOP 사용자)
                //= if isn't shop user then set default wh_code from tconfig.DEF_WH_CODE
                //= 2007.06.04 by kst :: 로그인시에 모든 정보 세팅하므로 아래정보는 주석처리함.
                //user.setWh_code(DBUtils.getConfig(con, "DEF_WH_CODE"));
            }
        }catch(StoreException se){
            user = new Tuser();
            user.setRtnSetting("000001",se.getMessage());
        }catch(Exception e){
            user = new Tuser();
            user.setRtnSetting("000001",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return user;
    }

    public HashMap[] retrieveUserProgram(String poolName, String user_id) throws StoreException {
        Connection con     = null;
        HashMap[]  hmArray = null;

        try{
            con   = DBUtils.getConnection(Construct.DB_POOL_NAME);
            UserSvc usersvc = new UserSvc();
            hmArray = usersvc.retrieveUserProgram(con, user_id);
        }catch(StoreException se){
            return null;
        }catch(Exception e){
            return null;
        }finally {
            DBUtils.freeConnection(con);
        }
        return hmArray;
    }

    public HashMap[] retrieveUserProgram(String poolName, String user_id, String lmenu_id) throws StoreException {
        Connection con     = null;
        HashMap[]  hmArray = null;

        try{
            con   = DBUtils.getConnection(Construct.DB_POOL_NAME);
            UserSvc usersvc = new UserSvc();
            hmArray = usersvc.retrieveUserProgram(con, user_id, lmenu_id);
        }catch(StoreException se){
            return null;
        }catch(Exception e){
            return null;
        }finally {
            DBUtils.freeConnection(con);
        }
        return hmArray;
    }

    public String getProgramToJsp(String lmenu_Id, String program_id) throws StoreException {

        String rtnStr = "/goods/";
        if(ComUtils.NVL(program_id,"").equals("")) return "";
        else if(lmenu_Id.equals("10")) rtnStr = "/manage/";
        else if(lmenu_Id.equals("20")) rtnStr = "/custcenter/";
        else if(lmenu_Id.equals("30")) rtnStr = "/logistics/";
        else if(lmenu_Id.equals("50")) rtnStr = "/broadcast/";
        else if(lmenu_Id.equals("60")) rtnStr = "/analysis/";
        else if(lmenu_Id.equals("90")) rtnStr = "/system/";

        program_id = program_id.substring(1);

        int s = 0;
        int e = 0;
        while ((e = program_id.indexOf("_", s)) >= 0) {
            program_id = program_id.substring(0,e)+program_id.substring(e+1,e+2).toUpperCase()+program_id.substring(e+2);
            s = e+1;
        }

        rtnStr = rtnStr + program_id + ".jsp";
        return  rtnStr;
    }

    public HashMap retrieveSecPerProgram( String poolName, String user_id, String program_id) throws StoreException {
        Connection con     = null;
        HashMap    rtnHm   = null;
        String argProgramId = "";

        try{
            con   = DBUtils.getConnection(Construct.DB_POOL_NAME);
            UserSvc usersvc = new UserSvc();
            program_id = program_id.substring( program_id.lastIndexOf("/")+1, program_id.lastIndexOf("."));

            argProgramId = "w_"+program_id.substring(0,1).toLowerCase();
            for(int i = 1 ; i < program_id.length() ; i++){
                if( Character.isUpperCase((program_id.charAt(i))) ){
                    argProgramId = argProgramId + "_"+ (program_id.substring(i,i+1)).toLowerCase();
                }else{
                    argProgramId = argProgramId + (program_id.substring(i,i+1));
                }
            }
            rtnHm = usersvc.retrieveSecPerProgram(con, user_id, argProgramId);
        }catch(StoreException se){
            return new HashMap();
        }catch(Exception e){
            return new HashMap();
        }finally {
            DBUtils.freeConnection(con);
        }
        return rtnHm;
    }

}
