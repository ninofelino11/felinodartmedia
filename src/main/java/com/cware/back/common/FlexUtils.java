package com.cware.back.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class FlexUtils implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	public static RetrieveModel ArrayToRetrieveModel(ArrayList pram) {
		RetrieveModel retrieve = new RetrieveModel();

        Iterator i=pram.iterator();
        HashMap key = null;
        while(i.hasNext()){
            key = (HashMap)i.next();
            Collection c=key.keySet();
            Iterator i1=c.iterator();
            while(i1.hasNext()){
           	 Object key1=i1.next();
           	 Object value= key.get(key1.toString());
           	 retrieve.put(key1.toString(), value == null ?"":key.get(key1.toString()).toString());
            }
        }
        retrieve.put("SYSDATETIME", DateUtils.getLocalDateTime(DateUtils.CWARE_JAVA_DATETIME_FORMAT));
		return retrieve;
	}

	public static HeaderModel ArrayToHeaderModel(ArrayList pram) {
		HeaderModel retrieve = new HeaderModel();

        Iterator i=pram.iterator();
        HashMap key = null;
        while(i.hasNext()){
            key = (HashMap)i.next();
            Collection c=key.keySet();
            Iterator i1=c.iterator();
            while(i1.hasNext()){
           	 Object key1=i1.next();
           	 Object value= key.get(key1.toString());
//       	 retrieve.put(key1.toString(), key.get(key1.toString()).toString());
           	 retrieve.put(key1.toString(), value == null ?"":key.get(key1.toString()).toString());
            }
        }
        retrieve.put("SYSDATETIME", DateUtils.getLocalDateTime(DateUtils.CWARE_JAVA_DATETIME_FORMAT));
		return retrieve;
	}

}
