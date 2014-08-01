package com.cware.back.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 조회 프로그램에서 Header 부문의 결과를 얻는다
 * @version 1.0, 2006/04/01
 * @author  kim Sungtaek <webzest@commerceware.co.kr>
 */
public class HeaderModel {
	private Map map;

	/**
	 * 기본 생성자
	 */
	public HeaderModel() {
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
		return Long.parseLong(map.get(key).toString());
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

}
