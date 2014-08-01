
package com.cware.back.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Data Connection Control
 *
 * @version 1.0, 2005/02/03
 * @author  kim Sungtaek <webzest@commerceware.co.kr>
 */
public class DateUtils implements java.io.Serializable {

    /**
    * Commerceware 날짜 포맷(DATE)
    */
    public final static String CWARE_DATE_FORMAT = "yyyy/MM/dd";

    /**
    * Commerceware 날짜 포맷(DATETIME) - java용 format
    */
    public final static String CWARE_JAVA_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
    * Commerceware 날짜 포맷(DATETIME) - db용 format
    */
    public final static String CWARE_DB_DATETIME_FORMAT = "yyyy/MM/dd HH24:mi:ss";

    /**
    * 기본 날짜 포맷 - java용 format
    */
    public final static String DEFAULT_JAVA_DATE_FORMAT = "yyyyMMddHHmmss";

    /**
    * 기본 날짜 포맷 - db용 format
    */
    public final static String DEFAULT_DB_DATE_FORMAT = "yyyyMMddHH24miss";

    /**
    * 일반 날짜 포맷 (KICC 날짜 포맷)
    */
    public final static String GENERAL_DATE_FORMAT = "yyyyMMdd";

    /**
    * 기본 TimeZone
    * korea : JST, uk:Europe/London fr:Europe/London
    */
    public final static String DEFAULT_TIMEZONE = "JST";


    /**
    * <PRE>
    * Desc      : 현재일을 기준으로 (요청한 format)를 받아 날을 더한뒤 다시 String date (요청한 format)로 return
    * </PRE>
    */
    public static String addDay(int day, String format) {
        String dateAdd = getFormattedDate(addDay(getDate(getLocalDateTime(format), format), day), format);
        return dateAdd;
    }

    /**
    * <PRE>
    * Desc      : 현재일을 기준으로 (요청한 format)를 받아 달을 더한뒤 다시 String date (요청한 format)로 return
    * </PRE>
    */
    public static String addMonth(int month, String format) {
        String dateAdd = getFormattedDate(addMonth(getDate(getLocalDateTime(format), format), month), format);
        return dateAdd;
    }

    /**
    * <PRE>
    * Desc     : String date (요청한 format)를 받아 날을 더한뒤 다시 String date (요청한 format)로 return
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static String addDay(String date, int day, String format) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        String dateAdd = getFormattedDate(addDay(getDate(date, format), day), format);
        return dateAdd;
    }

     /**
    * <PRE>
    * Desc     :String date (요청한 format)를 받아 달을 더한뒤 다시 String date (요청한 format)로 return
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static String addMonth(String date, int month, String format) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        String dateAdd = getFormattedDate(addMonth(getDate(date, format), month), format);
        return dateAdd;
    }



    /**
    * <PRE>
    * Desc     : src_date가 시간상 target_date와 비교 check
    * </PRE>
    * src_date가 시간상 target_date와 비교 check
    * @param     format : 날짜 포맷
    * @return    src_date = target_date ; 0
    *            src_date < target_date ; -
    *            src_date > target_date ; +
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static int compareTo(String src_date, String target_date, String format) {
        Date src    = getDate(src_date, format);
        Date target = getDate(target_date, format);

        if ((src == null) || (target == null)){
            throw new java.lang.IllegalArgumentException();
        }
        return src.compareTo(target);
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 target_date와 비교 check (DEFAULT_JAVA_DATE_FORMAT을 사용)
    * </PRE>
    * src_date가 시간상 target_date와 비교 check
    * @param     format : 날짜 포맷
    * @return    src_date = target_date ; 0
    *            src_date < target_date ; -
    *            src_date > target_date ; +
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static int compareTo(String src_date, String target_date){
        return compareTo(src_date, target_date, DEFAULT_JAVA_DATE_FORMAT);
    }



    /**
    * <PRE>
    * Desc     : Date를 timezone과 format에 따른 날짜 String으로 변환해서 리턴
    * </PRE>
    * @param   date : 날짜(String)
    * @return  날짜 String을 리턴, 변환도중 error발생시 null을 리턴
    */
    public static String getYearMD(String date) {
        try{
            if (date.equals("") || date.equals(" ") || date.equals("null")  || date.equals(null)) {
                return " ";
            }
            java.util.TimeZone homeTz = java.util.TimeZone.getTimeZone(DEFAULT_TIMEZONE);
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy년 MM월 dd일");
            formatter.setTimeZone(homeTz);
            return formatter.format(getDate(date,"yyyyMMdd"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
    * <PRE>
    * Desc     : Date를 timezone과 format에 따른 날짜 String으로 변환해서 리턴
    * </PRE>
    * @param   date : 날짜(String)
    * @return  날짜 String을 리턴, 변환도중 error발생시 null을 리턴
    */
    public static String getYearMD(String date, String format) {
        try{
            String from = DEFAULT_JAVA_DATE_FORMAT.substring(0, date.length());
            if (date.equals("") || date.equals(" ") || date.equals("null")  || date.equals(null)) {
                return " ";
            }
            java.util.TimeZone homeTz = java.util.TimeZone.getTimeZone(DEFAULT_TIMEZONE);
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
            formatter.setTimeZone(homeTz);
            return formatter.format(getDate(date, from));
        } catch (Exception e) {
            return null;
        }
    }

    /**
    * <PRE>
    * Desc     : 일자에 요일 상수값 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static int getDayNumber(String date) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(getDate(date, "yyyy/MM/dd"));
        int dayNumber = cal.get(Calendar.DAY_OF_WEEK);
        return dayNumber;
    }

    /**
    * <PRE>
    * Desc     : 월에 마지막 일 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static int getLastDay(String date) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(getDate(date, "yyyy/MM/dd"));
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }

    /**
    * <PRE>
    * Desc     : Date를 timezone과 format에 따른 날짜 String으로 변환해서 리턴
    * </PRE>
    * @param   date     : 날짜
    * @param   format   : 날짜 포맷 (예) yyyyMMddHHmmss
    * @param   timezone : Timezone (예) JST
    * @return  날짜 String을 리턴, 변환도중 error발생시 null을 리턴
    */
    public static String getFormattedDate(Date date, String format, String timezone) {
        try{
            java.util.TimeZone homeTz = java.util.TimeZone.getTimeZone(timezone);
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
            formatter.setTimeZone(homeTz);
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
    * <PRE>
    * Desc     : Date를 format에 따른 날짜 String으로 변환해서 리턴 (DEFAULT_TIMEZONE을 사용)
    * </PRE>
    * @param   date   : 날짜
    * @param   format : 날짜 포맷 (예) yyyyMMddHHmmss
    * @return  날짜 String을 리턴, 변환도중 error발생시 null을 리턴
    */
    public static String getFormattedDate(Date date, String format) {
        return getFormattedDate(date, format, DEFAULT_TIMEZONE);
    }

    /**
    * <PRE>
    * Desc     : Date를 날짜 String으로 변환해서 리턴 (DEFAULT_TIMEZONE과 DEFAULT_JAVA_DATE_FORMAT을 사용)
    * </PRE>
    * @param   date : 날짜
    * @return  날짜 String을 리턴, 변환도중 error발생시 null을 리턴
    */
    public static String getFormattedDate(Date date) {
        return getFormattedDate(date, DEFAULT_JAVA_DATE_FORMAT);
    }


    /**
    * 날짜 String을 Date로 변환해서 리턴한다.
    * @param   date   : 날짜 String
    * @param   format : 날짜 포맷
    * @return  날짜 String을 Date로 변환해서 리턴, 변환도중 error발생시 null을 리턴
    */
    public static Date getDate(String date, String format) {
        if ((date == null) || (format == null)) return null;
        try{
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
            return formatter.parse(date, new java.text.ParsePosition(0));
        } catch (Exception e) {
            return null;
        }
    }

    /**
    * <PRE>
    * Desc     : 날짜 String을 Date로 변환해서 리턴 (DEFAULT_JAVA_DATE_FORMAT을 사용)
    * </PRE>
    * @return  날짜 String을 Date로 변환해서 리턴, 변환도중 error발생시 null을 리턴
    */
    public static Date getDate(String date) {
        return getDate(date, DEFAULT_JAVA_DATE_FORMAT);
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 target_date 이전인지를 check
    * </PRE>
    * @param     format : 날짜 포맷
    * @return    src_date가 target_date이전이면 true, 그렇지 않으면 false를 리턴
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static boolean before(String src_date, String target_date, String sformat) {
        Date src    = getDate(src_date, sformat);
        Date target = getDate(target_date, sformat);

        if ((src == null) || (target == null)) {
            throw new java.lang.IllegalArgumentException();
        }

        return src.before(target);
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 target_date 이전인지를 check (DEFAULT_JAVA_DATE_FORMAT을 사용)
    * </PRE>
    * @return    src_date가 target_date이전이면 true, 그렇지 않으면 false를 리턴
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static boolean before(String src_date, String target_date) {
        return before(src_date, target_date, DEFAULT_JAVA_DATE_FORMAT);
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 target_date 이후인지를 check
    * </PRE>
    * @param     format : 날짜 포맷
    * @return    src_date가 target_date이후이면 true, 그렇지 않으면 false를 리턴
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static boolean after(String src_date, String target_date, String format){
        Date src    = getDate(src_date, format);
        Date target = getDate(target_date, format);

        if ((src == null) || (target == null)){
            throw new java.lang.IllegalArgumentException();
        }

        return src.after(target);
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 target_date 이후인지를 check (DEFAULT_JAVA_DATE_FORMAT을 사용)
    * </PRE>
    * @return    src_date가 target_date이후이면 true, 그렇지 않으면 false를 리턴
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static boolean after(String src_date, String target_date){
        return after(src_date, target_date, DEFAULT_JAVA_DATE_FORMAT);
    }

    /**
    * src_date가 시간상 target_date와 같은지 check
    * @param     format : 날짜 포맷
    * @return    src_date가 target_date와 같으면 true, 그렇지 않으면 false를 리턴
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static boolean equals(String src_date, String target_date, String format) {
        Date src    = getDate(src_date, format);
        Date target = getDate(target_date, format);

        if ((src == null) || (target == null)){
            throw new java.lang.IllegalArgumentException();
        }

        return (src.compareTo(target) == 0) ? true:false;
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 target_date와 같은지 check
    * </PRE>
    * @return  src_date가 target_date와 같으면 true, 그렇지 않으면 false를 리턴
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static boolean equals(String src_date, String target_date) {
        return equals(src_date, target_date, DEFAULT_JAVA_DATE_FORMAT);
    }





    /**
    * <PRE>
    * Desc     : start_date와 end_date 사이의 날짜 간격을 리턴
    * </PRE>
    * @param     format : 날짜 포맷
    * @exception IllegalArgumentException Parameter가 null이거나 날짜 포맷과 맞지 않는 경우 발생
    */
    public static int difference(String start_date, String end_date, String format){
        Date start = getDate(start_date, format);
        Date end   = getDate(end_date, format);

        if ((start == null) || (end == null)) {
            throw new java.lang.IllegalArgumentException();
        }

        long lStart = (long) (start.getTime()/(1000*60*60*24));
        long lEnd = (long) (end.getTime()/(1000*60*60*24));

        return (int) Math.abs(lStart - lEnd);
    }

    /**
    * <PRE>
    * Desc     : start_date와 end_date 사이의 날짜 간격을 리턴 (DEFAULT_JAVA_DATE_FORMAT 사용)
    * </PRE>
    * @exception IllegalArgumentException Parameter가 null이거나 날짜 포맷과 맞지 않는 경우 발생
    */
    public static int difference(String start_date, String end_date){
        return difference(start_date, end_date, DEFAULT_JAVA_DATE_FORMAT);
    }


    /**
    * <PRE>
    * Desc     : src_date가 시간상 start_date와 end_date 사이에 있는지 check
    * </PRE>
    * @param     format : 날짜 포맷
    * @return    true if start_data <= src_date <= end_date, otherwise false
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생
    */
    public static boolean between(String src_date, String start_date, String end_date, String format) {
        Date src   = getDate(src_date, format);
        Date start = getDate(start_date, format);
        Date end   = getDate(end_date, format);

        if ((src == null) || (start == null) || (end == null)) {
            throw new java.lang.IllegalArgumentException();
        }
        return ((src.compareTo(start) < 0) || (src.compareTo(end) > 0)) ? false:true;
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 start_date와 end_date 사이에 있는지 check (DEFAULT_JAVA_DATE_FORMAT을 사용)
    * </PRE>
    * @return    true if start_data <= src_date <= end_date, otherwise false
    * @exception IllegalArgumentException 날짜 String이 포맷과 맞지 않을 경우 발생한다.
    */
    public static boolean between(String src_date, String start_date, String end_date){
        return between(src_date, start_date, end_date, DEFAULT_JAVA_DATE_FORMAT);
    }

    /**
    * <PRE>
    * Desc     : src_date가 시간상 start_date와 end_date 사이에 있는지 check
    * </PRE>
    * @return    true if start_data <= src_date <= end_date, otherwise false
    * @exception IllegalArgumentException Parameter가 null이거나 날짜 포맷과 맞지 않는 경우 발생
    */
    public static boolean between(Date src_date, Date start_date, Date end_date) {
        if ((src_date == null) || (start_date == null) || (end_date == null)) {
            throw new java.lang.IllegalArgumentException();
        }
        return ((src_date.compareTo(start_date) < 0) || (src_date.compareTo(end_date) > 0)) ? false:true;
    }

    /**
    * <PRE>
    * Desc     : date를 기준으로 n 분을 더한 날짜를 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null이면 발생
    */
    public static Date addMinute(Date date, int minute) {
        if (date == null) throw new java.lang.IllegalArgumentException();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(date);
        cal.add(Calendar.MINUTE , minute);
        return cal.getTime();
    }

    /**
    * <PRE>
    * Desc     : date를 기준으로 n 시간을 더한 날짜를 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null이면 발생
    */
    public static Date addHour(Date date, int hour) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(date);
        cal.add(Calendar.HOUR , hour);
        return cal.getTime();
    }

    /**
    * <PRE>
    * Desc     : date를 기준으로 n날을 더한 날짜를 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null이면 발생
    */
    public static Date addDay(Date date, int day) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR , day);
        return cal.getTime();
    }

    /**
    * <PRE>
    * Desc     : String date (yyyy/MM/dd)를 받아 날을 더한뒤 다시 String date (yyyy/MM/dd)로 return
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static String addDay(String date, int day) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        String dateAdd = getFormattedDate(addDay(getDate(date, "yyyy/MM/dd"), day), "yyyy/MM/dd");
        return dateAdd;
    }

    /**
    * <PRE>
    * Desc     : date를 기준으로 n주를 더한 날짜를 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static Date addWeek(Date date, int week) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, week);
        return cal.getTime();
    }

    /**
    * <PRE>
    * Desc     : date를 기준으로 n달을 더한 날짜를 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static Date addMonth(Date date, int month) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

     /**
    * <PRE>
    * Desc     : String date (yyyy/MM/dd)를 받아 달을 더한뒤 다시 String date (yyyy/MM/dd)로 return
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static String addMonth(String date, int month) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        String dateAdd = getFormattedDate(addMonth(getDate(date, "yyyy/MM/dd"), month), "yyyy/MM/dd");
        return dateAdd;
    }

    /**
    * <PRE>
    * Desc     : date를 기준으로 n년을 더한 날짜를 리턴
    * </PRE>
    * @exception IllegalArgumentException date가 null일 경우 발생
    */
    public static Date addYear(Date date, int year) {
        if (date == null) throw new java.lang.IllegalArgumentException();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
    * <PRE>
    * 현재(System TimeZone 및 Locale 기준 ) 날짜/시간정보를 얻는다.
    * </PRE>
    * @return   String "yyyyMMddHHmmss" 형태의 스트링을 반환한다.
    */
    public static String getLocalDateTime() {
        Calendar calLocal = Calendar.getInstance();
        return "" + calLocal.get(Calendar.YEAR)
                    + makeTowDigit(calLocal.get(Calendar.MONTH) + 1)
                    + makeTowDigit(calLocal.get(Calendar.DATE))
                    + makeTowDigit(calLocal.get(Calendar.HOUR_OF_DAY))
                    + makeTowDigit(calLocal.get(Calendar.MINUTE))
                    + makeTowDigit(calLocal.get(Calendar.SECOND));
    }

    /**
    * <PRE>
    * 현재(한국기준) 시간정보를 얻는다.
    * 	(ex1) format string "yyyyMMddhh" : return value is 1998121011 (0~23 hour type)
    * 	(ex2) format string "yyyyMMddHHmmss" : return value is 19990114232121
    * </PRE>
    * @param    format      time format
    * @return   formatted current korean time
    */
    public static String getLocalDateTime(String format) {
        SimpleDateFormat fmt= new SimpleDateFormat(format);
        long time = System.currentTimeMillis();
        String strTime = fmt.format(new java.util.Date(time));
        return strTime;
    }

    /**
    * <PRE>
    * 숫자를 문자열로 변환하는데, 2자리수 미민이면 두자리수로 맞춘다.
    * </PRE>
    * @return   String "00" 형태의 스트링을 반환한다.
    */
    protected static String makeTowDigit(int num){
        return (num < 10 ? "0" : "") + num;
    }

    /**
    * FunctionName    : getCurrentLongTime
    * @param          : none
    * Description     : current time format = YYYYMMDDHHMMSS
    * @return         : time String
    * @exception      : E2321
    **/
    public static String getCurrentLongTime() {
        String strCurDate = new String();
        strCurDate = getLocalDateTime();
        return strCurDate;
    }

//    /**
//    * Desc     : test용 method
//    */
//    public static void main(String[] args){}

}
