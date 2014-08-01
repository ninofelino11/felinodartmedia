package com.cware.back.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Common method
 *
 * @version 1.0, 2005/02/03
 * @author  kim Sungtaek <webzest@commerceware.co.kr>
 */
public class ComUtils implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

	/**
     * Desc     : get Messeage from default property file
     *
     * @param   String key  : 구분자
     * @param   String arg1
     * @param   String arg2
     * @param   String arg3
     * @return  String
     */
	public static String getMessage(String key){
		ResourceBundle objRb=ResourceBundle.getBundle(Construct.RESOURCE_BUNDLE_NAME);
		String rtnVal = NVL(objRb.getString(key));
		return rtnVal;
	}
	public static String getMessage(String key, String arg1){
		String rtnVal = "";
		rtnVal = getMessage(key);
		rtnVal = replace(rtnVal, "{0}", arg1);
		return rtnVal;
	}
	public static String getMessage(String key, String arg1, String arg2){
		String rtnVal = "";
		rtnVal = getMessage(key);
		rtnVal = replace(rtnVal, "{0}", arg1);
		rtnVal = replace(rtnVal, "{1}", arg2);
		return rtnVal;
	}
	public static String getMessage(String key, String arg1, String arg2, String arg3){
		String rtnVal = "";
		rtnVal = getMessage(key);
		rtnVal = replace(rtnVal, "{0}", arg1);
		rtnVal = replace(rtnVal, "{1}", arg2);
		rtnVal = replace(rtnVal, "{2}", arg3);
		return rtnVal;
	}

    /**
     * Desc     : get Messeage from bundle property file
     *
     * @param   String module  : bundle property file
     * @param   String key     : 구분자
     * @param   String arg1
     * @param   String arg2
     * @param   String arg3
     * @return  String
     */
	public static String getMessageB(String module, String key){
		ResourceBundle objRb=ResourceBundle.getBundle(module);
		String rtnVal = NVL(objRb.getString(key));
		return rtnVal;
	}
	public static String getMessageB(String module, String key, String arg1){
		String rtnVal = "";
		rtnVal = getMessage(module, key);
		rtnVal = replace(rtnVal, "{0}", arg1);
		return rtnVal;
	}
	public static String getMessageB(String module, String key, String arg1, String arg2){
		String rtnVal = "";
		rtnVal = getMessage(module, key);
		rtnVal = replace(rtnVal, "{0}", arg1);
		rtnVal = replace(rtnVal, "{1}", arg2);
		return rtnVal;
	}
	public static String getMessageB(String module, String key, String arg1, String arg2, String arg3){
		String rtnVal = "";
		rtnVal = getMessage(module, key);
		rtnVal = replace(rtnVal, "{0}", arg1);
		rtnVal = replace(rtnVal, "{1}", arg2);
		rtnVal = replace(rtnVal, "{2}", arg3);
		return rtnVal;
	}


//	public static String getMessage(String key){
//		ResourceBundle objRb=ResourceBundle.getBundle(Construct.RESOURCE_BUNDLE_NAME);
//		String rtnVal = NVL(objRb.getString(key));
//		return rtnVal;
//	}
//	public static String getMessage(String module, String key){
//		ResourceBundle objRb=ResourceBundle.getBundle(module);
//		String rtnVal = NVL(objRb.getString(key));
//		return rtnVal;
//	}
//	public static String getMessage(String module, String key, String arg1){
//		String rtnVal = "";
//		rtnVal = getMessage(module, key);
//		rtnVal = replace(rtnVal, "{0}", arg1);
//		return rtnVal;
//	}
//	public static String getMessage(String module, String key, String arg1, String arg2){
//		String rtnVal = "";
//		rtnVal = getMessage(module, key);
//		rtnVal = replace(rtnVal, "{0}", arg1);
//		rtnVal = replace(rtnVal, "{1}", arg2);
//		return rtnVal;
//	}
//	public static String getMessage(String module, String key, String arg1, String arg2, String arg3){
//		String rtnVal = "";
//		rtnVal = getMessage(module, key);
//		rtnVal = replace(rtnVal, "{0}", arg1);
//		rtnVal = replace(rtnVal, "{1}", arg2);
//		rtnVal = replace(rtnVal, "{2}", arg3);
//		return rtnVal;
//	}

  /**
   * <PRE>
   * Desc     : Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
   * </PRE>
   * @param   str       : 원본
   * @param   separator : 구분자
   * @return  String[]
   */
    public static String[] split(String str, String separator){
        return StringUtils.split(str, separator);
    }

  /**
   * <PRE>
   * Desc     : Replaces multiple characters in a String in one go.
   * </PRE>
   * @param   str     : 바꾸려는 문자열을 가진 원본
   * @param   pattern : 찾을 문자열
   * @param   replace : 바꿔줄 문자열
   * @return  String
   */
  public static String replaceChars(String text, String repl, String with)
  {
    return StringUtils.replaceChars(text, repl, with);
  }

  /** GET ROUND
  // ROUND_CEILING : 올림
  // ROUND_HALF_UP : 반올림
  // ROUND_DOWN    : 버림
  **/
/* 미사용 - 2007.03.23 주석처리
  public static double getRound( double orgVal, int decimalPlace){
	  double     chVal = 0;
      BigDecimal bd    = new BigDecimal("0");

      if(orgVal == 0 ) return 0;

      bd    = BigDecimal.valueOf(orgVal).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
      chVal = bd.doubleValue();

//
//      BigDecimal bd = new BigDecimal(orgVal);
//      //bd = bd.setScale(decimalPlace,BigDecimal.ROUND_CEILING);
//      bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
//      chVal = Double.parseDouble(bd.toString());
//
      return  chVal;
  }
*/
    /** GET RATE
    // ROUND_CEILING : 올림
    // ROUND_HALF_UP : 반올림
    // ROUND_DOWN    : 버림
    // 버림(버림(insAmt / orgAmt, 10) * 100, 2)
    **/
/* 미사용 - 2007.03.23 주석처리
  public static double getDcRate(double orgAmt, double insAmt){
        BigDecimal bd = new BigDecimal("0");

        if(insAmt == 0 || orgAmt == 0) return 0;

        bd = BigDecimal.valueOf(insAmt)
             .divide(BigDecimal.valueOf(orgAmt), Construct.CALCULATE_SCALE, Construct.roundMode)
             .multiply(BigDecimal.valueOf(100))
             .setScale(Construct.DEFAULT_SCALE_RATE, Construct.roundMode);

        return  bd.doubleValue();
    }
*/
    /** GET AMT
    // ROUND_CEILING : 올림
    // ROUND_HALF_UP : 반올림
    // ROUND_DOWN    : 버림
    // 버림(버림( (amt * rate) / 100, 10) * qty, 2)
    **/
/* 미사용 - 2007.03.23 주석처리
    public static double getDcAmt( double amt, double rate, long qty){
        BigDecimal bd = new BigDecimal("0");

        if(rate < 0 || amt == 0 || qty == 0) return 0;

        bd    = BigDecimal.valueOf(amt)
                .multiply(BigDecimal.valueOf(rate))
                .divide(BigDecimal.valueOf(100), Construct.CALCULATE_SCALE, Construct.roundMode)
                .multiply(BigDecimal.valueOf(qty))
                .setScale(Construct.DEFAULT_SCALE_AMT, Construct.roundMode);

        return  bd.doubleValue();
    }
*/

//= START ---------------- 부동 소수점 연산 오류 보정 및 반올림 정책 적용
	/**
	 * 부동 소수점 연산 오류 보정 및 반올림 정책 적용
	 * @param argAmt : 최종 금액
	 * @return
	 */
	public static double modAmt ( double argAmt){
		return modAmt ( argAmt, Construct.DEFAULT_MODE, Construct.DEFAULT_FINAL_SIZE );
	}

	/**
	 * 원단위 처리 (절사처리)
	 * @param argAmt
	 * @return
	 */
	public static double modAmtRemoveWon ( double argAmt){
		//= -1 인 경우만 원단위 절사(한국) 이외의 경우 Construct.DEFAULT_FINAL_SIZE 에 의한 절사처리.
		return (Construct.FINAL_D_POINT == -1)? modAmt( modAmt ( argAmt / 10, Construct.MODE_FLOOR, 0) * 10, Construct.DEFAULT_MODE, 0) : 
			modAmt(argAmt, Construct.MODE_FLOOR);
	}

	/**
	 * 부동 소수점 연산 오류 보정 및 반올림 정책 적용
	 * @param argAmt : 최종 금액
	 * @param argMod : 반올림 정책
	 * @return
	 */
	public static double modAmt ( double argAmt, int argMod ){
		return modAmt ( argAmt, argMod, Construct.DEFAULT_FINAL_SIZE );
	}

	/**
	 * 부동 소수점 연산 오류 보정 및 반올림 정책 적용
   * 모든 연산의 반올림을 사용한다. default
   * 최종 할인금액과 적립금을 계산하는 순간만 버림으로 사용
   *
   * 고려대상
   * 금액 할인의 금액 분배         ; 금액 할인 상품단위로 분배되는 경우        >> 반올림
   * 상품 할인의 금액 분배         ; Set상품일 경우 구성상품으로 분배되는 경우  >> 반올림
   * 부분반품, 부분취소 접수받을 때  ;  반품, 취소되어 분배되는 경우            >> 반올림
   * 적립금 분배                  ; Set상품일 경우 구성상품으로 분배되는 경우  >> 반올림
	 * @param argAmt       : 최종 금액
	 * @param argMod       : 반올림 정책
	 * @param argFinalSize : 최종 소수점 유효 자리수
	 * @return double
	 */
	public static double modAmt ( double argAmt, int argMod, int argFinalSize ){
		BigDecimal decimalValue = null;
		try{
			if(argAmt == 0) return 0;

			decimalValue = BigDecimal.valueOf(argAmt);

			//= Const.VALID_DECIMAL 까지 넘는 size 일 경우  Const.VALID_DECIMAL, Const.DEFAULT_MODE 로 세팅
			decimalValue = decimalValue.setScale(Construct.VALID_DECIMAL, Construct.DEFAULT_MODE);

			//= argMod, argFinalSize 로 재조정.
			decimalValue = decimalValue.setScale(argFinalSize, argMod);

		}catch(ArithmeticException e){ return 0;}

		return decimalValue.doubleValue();
	}

//= END ---------------- 부동 소수점 연산 오류 보정 및 반올림 정책 적용


    // get Parse Pay Month
    public static String getParsePayMonth(String norest_allot_months){
        String convertStr = StringUtils.leftPad(norest_allot_months,36,"0");
        String rtnValue   = "";
        String sep        = "";

        for(int i=0 ; i < 36 ; i++){
            if( convertStr.substring(i,i+1).equals("1") ){
                if(!sep.equals("-")){
                    rtnValue += ",";
                    if( i == 0 ) {
                        rtnValue += "일시불";
                    }else{
                        rtnValue += ""+(i+1);
                    }
                    if( i < 34 ){
                        if( convertStr.substring(i+1,i+2).equals("1") && convertStr.substring(i+2,i+3).equals("1") ){
                            sep = "-";
                            rtnValue += sep;
                        }
                    }
                }else{
                    if( i < 35 ){
                        if( !convertStr.substring(i+1,i+2).equals("1")){
                            rtnValue += ""+(i+1);
                            sep = "";
                        }
                    }else{
                        rtnValue += ""+(i+1);
                    }
                }
            }
        }

        if(!rtnValue.equals("")) rtnValue = rtnValue.substring(1);
        if(rtnValue.equals(""))  rtnValue = "불가";

        return rtnValue;
    }

    // Right Left a String with a specified character.
    public static String lpad(String s, int n , String replace )
    {
        return  StringUtils.leftPad(s, n, replace);
    }
    // Right pad a String with a specified character.
    public static String rpad(String s, int n , String replace )
    {
        return  StringUtils.rightPad(s, n, replace);
    }
    // +1 => Left pad a String with a specified character.
    public static String increaseLpad(String s, int n , String replace )
    {
        long tempValue = Long.parseLong(s);
        tempValue = tempValue + 1;
        s = Long.toString(tempValue);
        return  lpad(s, n, replace);
    }

    // CheckBox Value Mapping
    // i)   0  >> 1
    // ii) any >> 0
    public static String checkBoxValueSet(String inputValue){
        String rtnValue = "0";
        if( inputValue == null ) return rtnValue;
        if( inputValue.equals("") ) return rtnValue;
        if( !inputValue.equals("0") ) rtnValue = "1";
        return rtnValue;
    }

    // Object - > String
    public static String objToStr(Object objVal){
        return objToStr(objVal, "");
    }
    // Object - > String
    public static String objToStr(Object objVal, String emptyValue){
        if(objVal == null)		return emptyValue;
        if(objVal.equals(""))	return emptyValue;
        emptyValue = objVal.toString();
        return emptyValue;
    }

    // Object - > int
    public static int objToInt(Object objVal){
        return objToInt(objVal, 0);
    }
    // Object - > int
    public static int objToInt(Object objVal, int emptyValue){
        if(objVal == null) return emptyValue;
        if(objVal.equals("")) return emptyValue;
        emptyValue = Integer.parseInt(objVal.toString());
        return emptyValue;
    }

    // Object - > long
    public static long objToLong(Object objVal){
        return objToLong(objVal, 0);
    }
    // Object - > long
    public static long objToLong(Object objVal, long emptyValue){
        if(objVal == null) return emptyValue;
        if(objVal.equals("")) return emptyValue;
        emptyValue = (long)Double.parseDouble(objVal.toString());
        return emptyValue;
    }

    // Object - > long
    public static long objToDoubleToLong(Object objVal){
    	long emptyValue = 0;
        if(objVal == null)		return emptyValue;
        if(objVal.equals(""))	return emptyValue;
        emptyValue = (long)objToDouble(objVal);
        return emptyValue;
    }

    // Object - > double
    public static double objToDouble(Object objVal){
        return objToDouble(objVal, 0);
    }
    // Object - > double
    public static double objToDouble(Object objVal, double emptyValue){
        if(objVal == null) return emptyValue;
        if(objVal.equals("")) return emptyValue;
        emptyValue = Double.parseDouble(objVal.toString());
        return emptyValue;
    }
    // Double - > String
    public static String objToDoubleToStr(Object objVal){
    	String emptyValue = "";
        if(objVal == null)		return emptyValue;
        emptyValue = objToStr(objToDoubleToLong(objVal));//objVal.toString();
        return emptyValue;
    }

    /** String 을 구분자에 따라 잘라서 배열에 저장 **/
  public static String[] getStringToArray(String strVale, String CheckValue, int ArrayCount){
    String [] tempArray = new String[ArrayCount];
    if(strVale == null)    return tempArray;
    if(strVale.equals("")) return tempArray;

    int s = 0;
    int e = 0;
    int i = 0;

    while ((e = strVale.indexOf(CheckValue, s)) >= 0)
    {
      tempArray[i] = strVale.substring(s, e);
      s = e+CheckValue.length();
      i++;
    }
    return tempArray;
  }



  /**
   * <PRE>
   * Desc     : 123,456 -> int type convert.
   * </PRE>
   * @param   paramMoney : \123,456 Format
   * @return  int
   */
  public static int setCurrencyToInt(String paramMoney){
    int money = 0;
    StringBuffer strMoney = new StringBuffer(paramMoney);

    for (int i=0; i<strMoney.length(); i++)
    {
      if (strMoney.charAt(i) == ',')
      {
        strMoney.deleteCharAt(i);
      }
    }

    try
    {
      money = Integer.parseInt(strMoney.toString());
    } catch (NumberFormatException nfe) {
      money = -1; // error -1 return
    }
    return money;
  }

  /**
   * <PRE>
   * Desc     : 123,456 -> double type convert.
   * </PRE>
   * @param   paramMoney : \123,456 Format
   * @return  double
   */
  public static double setCurrencyToDouble(String paramMoney){
    double money = 0;
    StringBuffer strMoney = new StringBuffer(paramMoney);
    for (int i=0; i<strMoney.length(); i++)
    {
      if (strMoney.charAt(i) == ',')
      {
          strMoney.deleteCharAt(i);
      }
    }

    try
    {
      money = Double.parseDouble(strMoney.toString());
    } catch (NumberFormatException nfe) {
      money = -1; // error -1 return
    }
    return money;
  }

  /**
   * <PRE>
   * Desc     : 123,456 -> String type convert.
   * </PRE>
   * @param   paramMoney : \123,456 Format
   * @return  String
   */
  public static String setCurrencyToStr(String paramMoney)
  {
    if (paramMoney == null)
    {
      paramMoney = "";
    }

    StringBuffer strMoney = new StringBuffer(paramMoney);
    for (int i=0; i<strMoney.length(); i++)
    {
      if (strMoney.charAt(i) == ',' || strMoney.charAt(i) == '-' || strMoney.charAt(i) == '/')
      {
        strMoney.deleteCharAt(i);
      }
    }

    if (isNumber(strMoney.toString()))
    {
      return strMoney.toString();
    } else {
      return null;
    }
  }

  /**
   * <PRE>
   * Desc     : int -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : int
   * @return  String
   */
  public static String setIntToCurrency(int paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : double -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : double
   * @return  String
   */
  public static String setIntToCurrency(double paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : long -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : double
   * @return  String
   */
  public static String setIntToCurrency(long paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : long -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : double
   * @return  String
   */
  public static String setLongToCurrency(long paramMoney)
  {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramMoney);
  }

  /**
   * <PRE>
   * Desc     : String -> 123,456 type convert.
   * </PRE>
   * @param   paramMoney : String
   * @return  String
   */
  public static String setStrToCurrency(String paramMoney)
  {
    if (paramMoney.equals(""))
      return "0";
    if (!isNumber(paramMoney))
      return null;

    Long paramLong = new Long(paramMoney);
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(paramLong.longValue());
  }

  /**
   * <PRE>
   * Desc     : number check
   * </PRE>
   * @param   strNumber : String
   * @return  boolean
   */
  public static boolean isNumber(String strNumber)
  {
    boolean isSuccess = true;

    try
    {
      new Long(strNumber);
    } catch (NumberFormatException nfe) {
      isSuccess = false;
    }
    return isSuccess;
  }

  /**
   * <PRE>
   * Desc     : 숫자에 대해(금액) TRUNC
   * </PRE>
   * @param   param : 숫자
   * @param   param : 숫자
   * @return  float
   */
  public static float Truncate(float param, float tunc)
  {
    param = param / tunc;
    Float floatTrunc = new Float(param);
    Integer IntTrunc = new Integer(floatTrunc.intValue());
    param = IntTrunc.floatValue();
    param = param  * tunc;
    return param;
  }

  /**
   * <PRE>
   * Desc     : 정수를 받아서 반올림(5이하 버림, 5이상 올림)
   * </PRE>
   * @param   number : 정수
   * @return  long
   */
  public static long Round(int number)
  {
    Integer i = new Integer(number);
    double d_number = i.doubleValue();
    long round_number = Math.round(d_number/10);
    long exchange_number = round_number * 10;
    return exchange_number;
  }

  /**
   * <PRE>
   * Desc     : double를 받아서 반올림(5이하 버림, 5이상 올림)
   * </PRE>
   * @param   number : double
   * @return  long
   */
  public static long Round(double number)
  {
    long round_number = Math.round(number/10);
    long exchange_number = round_number * 10;
    return exchange_number;
  }

  /**
   * <PRE>
   * Desc     : String 형식을 받아서 Html 형식으로 변환
   * </PRE>
   * @param   comment : String
   * @return  String
   */
  public static String convertHtmlBr(String comment)
  {
    int length = comment.length();
    StringBuffer buffer = new StringBuffer();

    if (comment.equals(null))
    {
      buffer.append("");
      return buffer.toString();
    }

    for (int i = 0; i < length; ++i)
    {
      String comp = comment.substring(i, i+1);
      if ("\r".compareTo(comp) == 0)
      {
        comp = comment.substring(++i, i+1);
        if ("\n".compareTo(comp) == 0)
          buffer.append("<BR>\r");
        else
          buffer.append("\r");
      }
      buffer.append(comp);
    }
    return buffer.toString();
  }

	/**
     * db에서 사용할수 없는 값들을 변환(&,',^)   web --> db
     * @param dbstring 바꿀 문자열
     * @return temp 바꾼 문자열
     */
    public static String script2web(String dbstring){
        int index=0;
        String temp = dbstring;

        while((index=temp.indexOf("~&"))>=0) {
            temp = temp.substring(0,index)+"{"+temp.substring(index+2);
        }
        while((index=temp.indexOf("~`"))>=0) {
            temp = temp.substring(0,index)+"\\"+temp.substring(index+2);
        }
        while((index=temp.indexOf("~^"))>=0) {
            //temp = temp.substring(0,index)+"\r\n"+temp.substring(index+2);
			temp = temp.substring(0,index)+"<br>"+temp.substring(index+2);
        }
        return temp;
    }

	/**
     * db에서 사용할수 없는 값들을 변환()   text --> db
     * @param dbstring 바꿀 문자열
     * @return temp 바꾼 문자열
     */
    public static String text2db(String desc){
        if(desc==null || desc.length()==0 ) return "";
        if(desc.length() == 1){
            if(desc.equals("\\")){
                return " ";
            }else if(desc.equals("\"")){
                return "'";
            }else{
                return desc;
            }
        }

        String temp = desc;
        int index=0;

        while((index=temp.indexOf('"'))>=0) {
            temp = temp.substring(0,index)+"'"+temp.substring(index+1);
        }

        String lastStr1 = temp.substring(temp.length()-1, temp.length());

        if (lastStr1.equals("\\") ){
		    return temp.substring(0, temp.length()-1) + " ";
        }

        return temp;

    }

	/**
     * web에서 사용할수 없는 값들을 변환   db --> web
     * @param desc 바꿀 문자열
     * @return temp 바꾼 문자열
     */
    public static String db2script(String desc){
        if(desc==null || desc.length()==0 )
			return "";

        String temp = desc;
        int index=-2;

/*        while((index=temp.indexOf('\'',index+2))>=0) {
            temp = temp.substring(0,index)+"\'\'"+temp.substring(index+1);
        }
*/
        while((index=temp.indexOf('{'))>=0) {
            temp = temp.substring(0,index)+"~&"+temp.substring(index+1);
        }
        while((index=temp.indexOf('\\'))>=0) {
            temp = temp.substring(0,index)+"~`"+temp.substring(index+1);
        }
        while((index=temp.indexOf("\r\n"))>=0) {
            temp = temp.substring(0,index)+"~^"+temp.substring(index+2);
        }
        while((index=temp.indexOf('\n'))>=0) {
            temp = temp.substring(0,index)+"~^"+temp.substring(index+1);
        }
        while((index=temp.indexOf('"'))>=0) {
            temp = temp.substring(0,index)+"`"+temp.substring(index+1);
        }
/*
        while((index=temp.indexOf("'",index+2))>=0) {
            temp = temp.substring(0,index)+"`"+temp.substring(index+2);
        }
*/
        return temp;
    }



  /**
   * <PRE>
   * Desc     : 변수가 한글이 포함되어 있는지 Check
   * </PRE>
   * @param   argStr : 문자열
   * @return  boolean
   */
  public static boolean isString(String argStr)
  {
    // 문자열의 길이와 문자열의 바이트배열의 길이를 비교해서 체크
    if (argStr.length() == argStr.getBytes().length)
      return false;
    else
      return true;
  }

  /**
   * <PRE>
   * Desc     : 특정문자 변환 Check
   * </PRE>
   * @param   str     : 바꾸려는 문자열을 가진 원본
   * @param   pattern : 찾을 문자열
   * @param   replace : 바꿔줄 문자열
   * @return  String
   */
  public static String replace(String str, String pattern, String replace)
  {
    int s = 0;
    int e = 0;
    StringBuffer result = new StringBuffer();

    while ((e = str.indexOf(pattern, s)) >= 0)
    {
      result.append(str.substring(s, e));
      result.append(replace);
      s = e+pattern.length();
    }

    result.append(str.substring(s));
    return result.toString();
  }

  /**
   * <PRE>
   * Desc     : 특수 char(& , " ) 를 ( , , ' ) 로 변환
   * </PRE>
   * @param   str : 특수 char(& , " )
   * @return  String
   */
  public static String charReplace(String str)
  {
    str = str.replace('&',',');
    str = str.replace('"', ' ');
    return str;
  }

  /**
   * Desc     : 좌측버튼 URL
   */
  public static final String RIGHT_BTN_URL = "/common/images/button/btn_list_forward.gif";
  /**
   * Desc     : 우측버튼 URL
   */
  public static final String LEFT_BTN_URL  = "/common/images/button/btn_list_previous.gif";

  /**
   * <PRE>
   * Desc     : GET 방식의 게시판의 counter
   * </PRE>
   * @param   int    : current_page
   * @param   int    : total_page
   * @param   Stirng : callee_url
   * @return  myIndexList(true, 10, current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, callee_url, null) call
   */
  public static String myIndexList(int current_page, int total_page, String callee_url)
  {
    return myIndexList(true, 10, current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, callee_url, null);
  }

  /**
   * <PRE>
   * Desc     : POST 방식의 게시판의 counter(1)
   * </PRE>
   * @param   int : current_page
   * @param   int : total_page
   * @return  myIndexList(false, 10,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null) call
   */
  public static String myIndexList(int current_page, int total_page)
  {
    return myIndexList(false, 10,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null);
  }

  /**
   * <PRE>
   * Desc     : POST 방식의 게시판의 counter(2)
   * </PRE>
   * @param   int : list_limit
   * @param   int : current_page
   * @param   int : total_page
   * @return  myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null) call
   */
  public static String myIndexList(int list_limit, int current_page, int total_page)
  {
    return myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", null);
  }

  /**
   * <PRE>
   * Desc     : POST 방식의 게시판의 counter(3)
   * </PRE>
   * @param   int    : list_limit
   * @param   int    : current_page
   * @param   int    : total_page
   * @param   String : font_color
   * @return  myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", font_color) call
   */
  public static String myIndexList(int list_limit, int current_page, int total_page, String font_color)
  {
    return myIndexList(false ,list_limit,current_page,total_page, LEFT_BTN_URL, RIGHT_BTN_URL, "goToPage", font_color);
  }

  /**
   * <PRE>
   * Desc     : Navigation Index List
   * </PRE>
   * @param   method_type T/F : get/post
   * @param   list_limit      : setting number (displayed number)
   * @param   current_page
   * @param   total_page
   * @param   left_image_url
   * @param   right_image_url
   * @param   callee_url
   * @return  html로 변환
   */
  public static String myIndexList(boolean method_type, int list_limit, int current_page, int total_page,
                                   String left_image_url, String right_image_url, String callee_url, String font_color)
  {
    int startpage;
    int endpage;
    int curpage;
    StringBuffer returnList = new StringBuffer();

    if (list_limit    < 0)       list_limit      = 0;
    if (current_page  < 0)       current_page    = 0;
    if (total_page    < 0)       total_page      = 0;
    if (left_image_url  == null) left_image_url  = "";
    if (right_image_url == null) right_image_url = "";
    if (callee_url      == null) callee_url      = "";
    if (font_color      == null) font_color      = "";


    startpage = ((current_page - 1) / list_limit) * list_limit + 1;

    endpage = (((startpage - 1) +  list_limit) / list_limit) * list_limit;

    if (total_page <= endpage)
    {
      endpage = total_page;
    }

    /**
    if (current_page > list_limit){
        curpage = startpage - 1;
        if (method_type)
            returnList.append("<a href='"+callee_url+"?page="+curpage+"'>");
        else
            returnList.append("<a href='javascript:"+callee_url+"("+curpage+");'>");

        returnList.append("<img src='"+left_image_url+"' border='0' align='absmiddle'></a>");
        returnList.append("... ");
        returnList.append("\n");
    }
    **/

    curpage = startpage;
    while (curpage <= endpage)
    {
      if (curpage == current_page)
      {
        returnList.append("<u>"+current_page+"</u>&nbsp;\n");
      }
      else
      {
        if (method_type)
          returnList.append("<u><a href='"+callee_url+"?page="+curpage+"'>"+curpage+"</a></u>&nbsp;\n");
        else
          returnList.append("<u><a href='javascript:"+callee_url+"("+curpage+");'>"+curpage+"</a></u>&nbsp;\n");
      }
      curpage++;
    }

    /**
    if (total_page > endpage){
        returnList.append(" ...");
        if (method_type)
            returnList.append("<a href='"+callee_url+"?page="+curpage+"'>");
        else
            returnList.append("<a href='javascript:"+callee_url+"("+curpage+");'>");

        returnList.append("<img src='"+right_image_url+"' border='0' align='absmiddle'></a>");
        returnList.append("\n");
    }
    **/

    return returnList.toString();
  }

  /**
   * <PRE>
   * Desc     : String[] => Integer[]으로 변환
   * </PRE>
   * @param   strs : String[]
   * @return  Integer[]
   */
  public static Integer[] setStrToInteger(String[] strs)
  {
    if ( strs == null ) return null;
    Integer[] ints = new Integer[strs.length];
    for (int i=0 ; i< ints.length ; i++)
    {
      try
      {
        ints[i] = new Integer(strs[i]);
      } catch (NumberFormatException e) {
        ints[i] = new Integer(0);
      }
    }
    return ints;
  }

  /**
   * <PRE>
   * Desc     : 원하는 값의 존재 유무 (해당번째)
   * </PRE>
   * @param   strs : 해당문자열
   * @param   comp : 비교값
   * @return  int
   */
  public static int getArrayCompare(String strs, String comp)
  {
    return strs.indexOf(comp, 0);
  }

  /**
   * <PRE>
   * Desc     : 원하는 값의 존재 유무
   * </PRE>
   * @param   strs[] : 해당문자열
   * @param   comp   : 비교값
   * @return  boolean
   */
  public static boolean getArrayCompare(String[] strs, String comp)
  {
    if ( strs == null ) return false;

    for (int i=0 ; i< strs.length ; i++)
    {
      if (strs[i].equals(comp)) return true;
    }
    return false;
  }

  /**
   * <PRE>
   * Desc     : 원하는 위치값의 존재 유무
   * </PRE>
   * @param   strs[]   : 해당문자열
   * @param   position : 위치값
   * @param   comp     : 비교값
   * @return  boolean
   */
  public static boolean getArrayCompare(String[] strs, int position, String comp)
  {
    if ( strs == null ) return false;
    if ( strs.length < position ) return false;
    if ( strs[position].equals(comp) ) return true;

    return false;
  }

  /**
   * <PRE>
   * Desc     : 유효한 값의 갯수
   * </PRE>
   * @param   strs[] : 문자열
   * @return  int
   */
  public static int getArrayCount(String[] strs)
  {
    int count = 0;
    try
    {
      if ( strs == null ) return count;
      for (int i=0 ; i< strs.length ; i++)
      {
        if (!strs[i].equals("") && strs[i] != null)
          count++;
      }
    } catch (Exception e) {
    }

    return count;
  }

  /**
   * <PRE>
   * Desc     : String[] => Double[]으로 변환
   * </PRE>
   * @param   strs[]
   * @return  Double[]
   */
  public static Double[] setStrToDouble(String[] strs)
  {
    if ( strs == null ) return null;
    Double[] doubles = new Double[strs.length];

    for (int i=0 ; i< doubles.length ; i++)
    {
      try
      {
        doubles[i] = new Double(strs[i]);
      } catch (NumberFormatException e) {
        doubles[i] = new Double(0);
      }
    }
    return doubles;
  }

  /**
   * <PRE>
   * Desc     : String 으로 받은 인수를 size 1 씩 짤라서 지정된 size 의 배열에 저장
   * </PRE>
   * @param   Amt   : 문자열
   * @param   Count : size
   * @return  String[] : 문자열을 배열에 setting
   */
  public static String[] arrayAmtSetting(String Amt, int Count)
  {
    String [] AmtArray = new String[Count];
    int AmtLength = Amt.length();
    int compLength = Count - AmtLength;

    for (int i = 0 ; i < Count ; i++)
    {
      if (compLength > i)
      {
        AmtArray[i] = "";
      } else {
        AmtArray[i] = Amt.substring(i-compLength,i-compLength+1);
      }
    }
    return AmtArray;
  }


  /**
   * <PRE>
   * Desc     : KOREA 로 변경
   * </PRE>
   * @param   en 문자열
   * @return  String
   */
  public static String enToKo(String en)
  {
    String korean=null;
    try
    {
      korean = new String(en.getBytes("8859_1"),"KSC5601");
    } catch(Exception e) {
      //e.printStackTrace();
      return korean;
    }
    return korean;
  }

  /**
   * <PRE>
   * Desc     : DB에 Data를 저장할때
   * </PRE>
   * @param   ko : korea 문자열
   * @return  String
   */
  public static String koToEn(String ko)
  {
    String english=null;
    try
    {
      english = new String(ko.getBytes("KSC5601"),"8859_1");
    } catch(Exception e) {
      e.printStackTrace();
      //return english;
    }
    return english;
  }

    // get norest allot months
    public static Vector getAllotMonthCal(String norest_allot_months){
        String convertStr = StringUtils.leftPad(norest_allot_months,36,"0");
        Vector vtRtn = new Vector();

        for(int i=0 ; i < 36 ; i++){
            if( convertStr.substring(i,i+1).equals("1") ){
                vtRtn.add(new Integer(i+1));
            }
        }
        return vtRtn;

/**
        String arrayVal[] = new String[36];
        int cnt = 0;
        for ( int i = 0 ; i < 36 ; i++ ){
            if(convertStr.substring(i,i+1).equals("1")){;
                arrayVal[cnt] = Integer.toString(i+1);
                cnt++;
            }
        }

        String rtnArray[] = new String[cnt];
        for( int j = 0 ; j < cnt; j++){
            rtnArray[j] = arrayVal[j];
        }
        return rtnArray;
**/
    }

	/**
	 *  주어진 스트링의 쿠키 값 리턴
	 */
    public static String getCookie(HttpServletRequest request, String name)
			throws ServletException, IOException
	{

		String value = null;
		Cookie[] cookies = request.getCookies();

		if (cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++)
			{
				if ( cookies[i].getName().equals(name) )
				{
					value = cookies[i].getValue();
					return value;
				}
			}
		}

		return null;
	}

	/**
	 * <PRE>
	 * Desc     : Null String을 "" String으로 바꿔준다.
	 * </PRE>
	 * @param   String  문자열
	 * @return  String  문자열 or ""
	 */
	public static String NVL(String str){
		if(str == null || str.length() <= 0)
			return "";
		else
			return str;
	}


	/**
	 * <PRE>
	 * Desc     : Null String을 replace String으로 바꿔준다.
	 * </PRE>
	 * @param   String  검사 문자열
	 * @param   String  바뀔 문자열
	 * @return  String  문자열
	 */
	public static String NVL(String str, String replace){
		if(str == null || str.length() <= 0)
			return replace;
		else
			return str;
	}

	/**
	 * <PRE>
	 * Desc     : Null String을 replace String으로 바꿔준다.
	 *            Null 인 경우만 check 해서 replace 해주도록 변경
	 * </PRE>
	 * @param   String  검사 문자열
	 * @param   String  바뀔 문자열
	 * @return  String  문자열
	 */
	public static String isNull(String str, String replace){
		if(str == null)
			return replace;
		else
			return str;
	}



   	public static String replaceOne(String str) {

		if ( str == null ) return null;

		StringBuffer buff = new StringBuffer();
		char charArray[] = str.toCharArray();
		for (int i = 0; i<charArray.length; i++) {
			buff.append(charArray[i]);
			if (charArray[i]==39) {
				buff.append("\'");
			}
		}
		str = new String(buff);
		return str;
	}

   	public static String replaceQue(String str) {

		if ( str == null ) return null;

		StringBuffer buff = new StringBuffer();
		char charArray[] = str.toCharArray();
		for (int i = 0; i<charArray.length; i++) {
			if (charArray[i] != 39 && charArray[i] != 34) {
				buff.append(charArray[i]);
			}
		}
		str = new String(buff);
		return str;
	}

    /** GET AMT
    // ROUND_CEILING : 올림
    // ROUND_HALF_UP : 반올림
    // ROUND_DOWN    : 버림
    **/
/* 미사용 2007.03.23 주석처리
    public static String getCalAmt( String orgAmt, String insRate){
        int decimalPlace = 0;
        if(insRate.equals("0")) return "0";
        double     rtnAmt = ( Double.parseDouble(orgAmt) * Double.parseDouble(insRate) / 100 );
        BigDecimal bd     = new BigDecimal(rtnAmt);
        bd     = bd.setScale(decimalPlace,BigDecimal.ROUND_CEILING);
        //bd     = bd.setScale(decimalPlace,BigDecimal.ROUND_DOWN);
        return  bd.toString();
    }

    public static double getSetCalDcAmt( double inSetGoodsAmt, double setGoodsAmt, double setGoodsDcAmt, long inSetGoodsCount){
        try{
            double rtnVal = 0;
            if(inSetGoodsAmt == 0 || setGoodsAmt == 0 || setGoodsDcAmt == 0) return 0;
            BigDecimal bd     = new BigDecimal( (double)inSetGoodsAmt / (double)setGoodsAmt * (double)setGoodsDcAmt );
            bd     = bd.setScale(0,BigDecimal.ROUND_DOWN);
            rtnVal = ( Double.parseDouble(bd.toString()) / inSetGoodsCount * inSetGoodsCount);
            return  rtnVal;
        }catch(Exception e){return 0;}
    }
*/


     /**
     * 문자 하나가 알파벳인지 검사
     *
     * @param   str 검사 하고자 하는 문자
     * @return  알파벳인지의 여부에 따라 'true' or 'false'
     */
    public static boolean isAlpha(char c) {
        if ((c < 'a' || c > 'z') &&
            (c < 'A' || c > 'Z') &&
            c != '_' && c != ' ')
            return false;
        return true;
    }


    /**
     * 문자열이 알파벳인지 검사
     *
     * @param   str 검사 하고자 하는 문자열
     * @return  알파벳인지의 여부에 따라 'true' or 'false'
     */
    public static boolean isAlpha(String str) {
        if (str == null) return false;

        str = str.trim();
        int len = str.length();
        if (len == 0)
            return false;

        for (int i = 0; i < len; i++) {
            if (!isAlpha(str.charAt(i)))
                return false;
        }
        return true;
    }


    /**
     * 상품 코드를 Barcode로 변환
     *
     * @param   str1 상품코드
     * @param   str2 단품코드
     * @return  Barcode
     */
    public static String getBarcode(String str1, String str2) {
        //= CODE 39
        String[] code = {   "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
    						"B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L",
    						"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
    						"X", "Y", "Z", "-", ",", " ", "$", "/", "+", "%"};
        String barcode = (str1 + str2).toUpperCase();
        int lencode = barcode.length();
        int sumcode = 0;
        int k = 0;
        String cutcode = "";

        for(int i=0; i<lencode; i++){
        	cutcode = barcode.substring(i, i+1);
        	k=0;
        	for(int j=0; j<43; j++){
        		//= CODE39에 대응되는 값의 위치를 찾는다.
        		k=j;
        		if( cutcode.equals(code[j]) ) break;
        	}
        	//= 대응되는 위치값을 더한다.
        	sumcode = sumcode + k;
        }

        //= BarCode를 생성한다.
        barcode = barcode + code[(sumcode % 43)];

        return barcode;
    }


    /**
     * ArrayList를 Message 배열로 변환
     *
     * @param   ArrayList
     * @return  Message 배열
     */
    public static Message[] getHashMapArr(ArrayList arrList){
    	if(arrList == null || arrList.size() == 0) return null;
    	Collection collection = new ArrayList();
    	Iterator i=arrList.iterator();
    	Message msg = null;
        while(i.hasNext()){
        	msg = new Message();
        	msg.setMessage((HashMap)i.next());
        	collection.add( msg );
        }
        return (Message[])collection.toArray(new Message[0]);
    }

	public static String toXml(List<?> list){
		StringBuffer xml = new StringBuffer();
		for(int i=0;i<list.size();i++){
			xml.append(((String)list.get(i)).replaceAll("\\<(\\?)[xml](\\w+)(.*)(\\?)\\>", ""));
			// 특수문자는 앞에 \\
			// [xml] xml 세글자
			// [a-z] a부터 z까지 한개의 문자
			// [a-z]+ 한개 이상의 문자
			// [a-z]* 0개 이상의 문자
			// \\w 공백 문자 한개 이상
			// .* 모든 문자 0개 이상
			// () 그룹
		}
		return xml.toString();
	}
//	public static void main(String[] args)
//	{
//		System.out.println("시작");
//		System.out.println("getArrayCompare : " + args[0] + " : " + ComUtils.getArrayCompare(args[0], "?"));
//	}


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public static HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify

            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
        if (log.isDebugEnabled()) {
            if(hmSheet != null){
                Collection c=hmSheet.keySet();
                Iterator i=c.iterator();
                while(i.hasNext()){
                    Object key=i.next();
                    log.debug(key.toString());
                }
            }
            log.debug("Retrieve Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

} // end of class
