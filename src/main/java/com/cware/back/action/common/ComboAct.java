
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.StoreException;
import com.cware.back.entity.common.BaseCombo;
import com.cware.back.service.common.ComboSvc;

/**
 * Combo Box Action Class
 *
 * @version 1.0, 2006/06/14
 * @author lee suho [webzest@commerceware.co.kr]
 */
public class ComboAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public ComboAct()   { }

    /**
    * <PRE>
    * Desc : 조회 Method
    * </PRE>
    * @param   poolName      : DB 의 서비스명
    * @param   flag          : 콤보박스의 조회구분( int 형으로 1부터증가처리)
    * @param   paramValues   : 조회구분 에 따라 조회조건을 처리하기 위해서 배열로 저장
    * @return  ArrayList
    */
    public BaseCombo[] retrieve(Connection con, int flag, String[] paramValues) throws StoreException {
        BaseCombo[] basecombo = null;
        try{
            ComboSvc svc = new ComboSvc();
            basecombo = svc.retrieve(con, flag, paramValues);
        }catch(StoreException se){
            basecombo = null;
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            basecombo = null;
            throw new StoreException(e.getMessage());
        }
        return basecombo;
    }

    /**
    * <PRE>
    * Desc : 조회 Method
    * </PRE>
    * @param   poolName      : DB 의 서비스명
    * @param   flag          : 콤보박스의 조회구분( int 형으로 1부터증가처리)
    * @param   paramValues   : 조회구분 에 따라 조회조건을 처리하기 위해서 배열로 저장
    * @param   designPattern : 결과값을 일반 select box 형태의 String 으로 준다면 : 1 , GRID에서 사용한다면 : 2
    *                          결과값을 일반 select box 형태의 String 으로 Code+' '+Name로 준다면 : 3
    * @return  String
    */
    public BaseCombo[] retrieve(Connection con, int flag, String[] paramValues, int designPattern) throws StoreException {
        StringBuffer rtnValue  = null;
        int          rtnAlSize = 0;
        BaseCombo[]  entitys = null;
        try{
            ComboSvc     svc     = new ComboSvc();
            entitys = svc.retrieve(con, flag, paramValues);

            /* Flex 에서는 사용하지 않음. 2008. 9. 25 park.
            if( entitys != null ){
                switch ( designPattern ){
                      case Construct.P_COMBO_DISPLAY_HTML  : {
                        rtnValue  = new StringBuffer();
                        for(int i = 0 ; i < entitys.length; i++){
                            rtnValue.append("<option value='"+entitys[i].getCode()+"'> "+entitys[i].getCodeName()+" </option>");
                        }
                        break;

                    } case Construct.P_COMBO_DISPLAY_HTML_CODE_NAME  : {
                        rtnValue  = new StringBuffer();
                        for(int i = 0 ; i < entitys.length; i++){
                            rtnValue.append("<option value='"+entitys[i].getCode()+"'> "+entitys[i].getCode()+' '+entitys[i].getCodeName()+" </option>");
                        }
                        break;

                    } case Construct.P_COMBO_DISPLAY_GRID  : {
                        rtnValue  = new StringBuffer();
                        for(int i = 0 ; i < entitys.length; i++){
                            rtnValue.append("#"+entitys[i].getCode()+";"+entitys[i].getCodeName());
                            if(i < (entitys.length-1)) rtnValue.append("|");
                        }
                        break;

                    } case Construct.P_COMBO_DISPLAY_HTML_BLANK  : {
                        rtnValue  = new StringBuffer();
                            rtnValue.append("<option value=''></option>");
                        for(int i = 0 ; i < entitys.length; i++){
                            rtnValue.append("<option value='"+entitys[i].getCode()+"'> "+entitys[i].getCodeName()+" </option>");
                        }
                        break;

                    } case Construct.P_COMBO_DISPLAY_HTML_CODE_NAME_BLANK  : {
                        rtnValue  = new StringBuffer();
                            rtnValue.append("<option value=''></option>");
                        for(int i = 0 ; i < entitys.length; i++){
                            rtnValue.append("<option value='"+entitys[i].getCode()+"'> "+entitys[i].getCode()+' '+entitys[i].getCodeName()+" </option>");
                        }
                        break;

	                } case Construct.P_COMBO_DISPLAY_HTML_FULL_VALUE  : {
	                    rtnValue  = new StringBuffer();
	                    for(int i = 0 ; i < entitys.length; i++){
	                        rtnValue.append("<option value='" + entitys[i].getCode()
	                        		           + "' remark1='" + entitys[i].getRemark1()
	                        		           + "' remark2='" + entitys[i].getRemark2()
	                        		           + "' remark='" + entitys[i].getRemark()
	                        		           + "' codesname='" + entitys[i].getCodeSName()
	                        		           + "' codegroup='" + entitys[i].getCodeGroup()
	                        		           + "'> "        + entitys[i].getCodeName() + " </option>");
	                    }
	                    break;

		            } case Construct.P_COMBO_DISPLAY_HTML_FULL_VALUE_BLANK  : {
		                rtnValue  = new StringBuffer();
	                    rtnValue.append("<option value='' remark1='' remark2='' remark='' codesname='' codegroup=''></option>");
	                    for(int i = 0 ; i < entitys.length; i++){
		                    rtnValue.append("<option value='" + entitys[i].getCode()
                         		               + "' remark1='" + entitys[i].getRemark1()
                		                       + "' remark2='" + entitys[i].getRemark2()
                		                       + "' remark='" + entitys[i].getRemark()
	                        		           + "' codesname='" + entitys[i].getCodeSName()
	                        		           + "' codegroup='" + entitys[i].getCodeGroup()
		                    		           + "'> "        + entitys[i].getCodeName() + " </option>");
		                }
		                break;

		            }
                }

            }*/
        }catch(StoreException se){
        	entitys = null;
        	throw new StoreException(se.getMessage());
        }catch(Exception e){
        	entitys = null;
        	throw new StoreException(e.getMessage());
        }
        return entitys;
    }

}
