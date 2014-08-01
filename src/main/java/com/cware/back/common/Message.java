package com.cware.back.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 개별 프로세스 처리후 리턴되는 값 세팅 (ex) 결과값1,2,3 
 * @version 1.0, 2006/04/01
 * @author  kim Sungtaek <webzest@commerceware.co.kr>
 */
public class Message {
	private Map map;

	/**
	 * 기본 생성자
	 */
	public Message() {
		this.map = new HashMap();
	}

	/**
	 * @param key
	 * @return object
	 */
	public Object get(String key) {
		return map.get(key);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, int value) {
		map.put(key, new Integer(value));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, float value) {
		map.put(key, new Float(value));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, long value) {
		map.put(key, new Long(value));
	}
		
	/**
	 * @param key
	 * @param d
	 */
	public void put(String key, double value) {
		put(key, new Double(value));
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
	    if(value == null) value = ""; 
		map.put(key, new String(value));
	}
			
	/**
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		map.put(key, value);
	}

	/**
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
	    if(map.containsKey(key) == false) return 0;  
	    if(ComUtils.objToStr(map.get(key),"").equals("")) return 0;
		return Integer.parseInt(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public float getFloat(String key) {
	    if(map.containsKey(key) == false) return 0;  
	    if(ComUtils.objToStr(map.get(key),"").equals("")) return 0;
		return Float.parseFloat(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
	    if(map.containsKey(key) == false) return 0;  
	    if(ComUtils.objToStr(map.get(key),"").equals("")) return 0;
		return (long)Double.parseDouble(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
	    if(map.containsKey(key) == false) return 0;  
	    if(ComUtils.objToStr(map.get(key),"").equals("")) return 0;
		return Double.parseDouble(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public String getString(String key) {
        if(map.containsKey(key) == false) return "";  
		return ComUtils.objToStr(map.get(key),"");	  
/**
	    if(map.containsKey(key) == false) return "";   
	    if(ComUtils.objToStr(map.get(key),"").equals("")) return "";    
		String wd = (String) map.get(key);
		return wd;
**/  
	}

	/**
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
	    if(map.containsKey(key) == false) return null;  
		return map.get(key);
	}
		
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return map.toString();
	}

	public Map get() {
		return this.map;
	}	
	
	public void setMessage(HashMap hm){
		this.map = hm;
	}
	
	public Map replaceCase(String mode){
        Iterator iterator = this.map.entrySet().iterator();
        Entry entry = null;
        HashMap rtnMap = new HashMap();
		if(mode.equals("lower")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            rtnMap.put(entry.getKey().toString().toLowerCase(), entry.getValue());
	        }
		}else if(mode.equals("upper")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            rtnMap.put(entry.getKey().toString().toUpperCase(), entry.getValue());
	        }
		}
		setMessage(rtnMap); 
		return this.map;
	}
}
