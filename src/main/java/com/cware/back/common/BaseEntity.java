package com.cware.back.common;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Base Entity
*
* @version 1.0, 2006/07/25
* @author commerceware.co.kr
*/
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 8676789412691663787L;
	private String cwareAction;
    private String cwareInfo;
    private String rtnCode;
    private String rtnMsg;

    public void setCwareAction ( String cwareAction ){ this.cwareAction = cwareAction; }
    public void setCwareInfo ( String cwareInfo ){ this.cwareInfo = cwareInfo; }
    public void setRtnCode ( String rtnCode ){ this.rtnCode = rtnCode; }
    public void setRtnMsg ( String rtnMsg ){ this.rtnMsg = rtnMsg; }

    public String getCwareAction (){ return cwareAction; }
    public String getCwareInfo (){ return cwareInfo; }
    public String getRtnCode () { return this.rtnCode; }
    public String getRtnMsg () { return this.rtnMsg; }

    public void setRtnSetting ( String rtnCode, String rtnMsg ) {
        this.rtnCode = rtnCode;
        this.rtnMsg = rtnMsg;
    }
     
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}
	
	public String toStringLog(){
	    return StringUtils.replaceChars(
	                       StringUtils.replaceChars(
	                       StringUtils.replaceChars(
	                       ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE),"[","\n"),"]","\n"),",","\n");	    
	}
 
	public void setBlank() {
		try {
			String[] keys = (String[]) BeanUtils.describe(this).keySet().toArray(new String[0]);
					
			for (int i = 0; i < keys.length; i++) {
			    
				if (PropertyUtils.getPropertyType(this, keys[i]).getName().equals("java.lang.String")) {
					if (PropertyUtils.getSimpleProperty(this, keys[i]) == null) {
						PropertyUtils.setSimpleProperty(this, keys[i], "");
					}
				}
			}
		} catch (Exception e) {
		}
	}

}
