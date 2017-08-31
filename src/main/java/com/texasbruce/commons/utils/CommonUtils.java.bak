package com.texasbruce.commons.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CommonUtils {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommonUtils.class);

	public static boolean contained(String inputString1, String inputString2)
			throws Exception {
		if (inputString1 == null || inputString2 == null)
			return false;

		if (inputString1.indexOf(inputString2) != -1)
			return true;
		else
			return false;
	}

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().equals("") || s.trim().equalsIgnoreCase("null");
    }

    public static Object pickNotNull(Object lhs, Object rhs) {
        return lhs == null ? rhs : lhs;
    }

    public static String pickNotNull(String lhs, String rhs) {
        return isNullOrEmpty(lhs) ? rhs : lhs;
    }

    public static String stringJoin(String[] arr, String sep) {
        String result = null;
        if (arr != null && sep != null) {
            result = "";
            for (int i = 0; i < arr.length - 1; i++) {
                result += arr[i] + sep;
            }
            if (arr.length > 0) {
                result += arr[arr.length - 1];
            }
        }
        return result;
    }

    public static String stringJoin(List<? extends Object> arr, String sep) {
        String result = null;
        if (arr != null && sep != null) {
            result = "";
            for (int i = 0; i < arr.size() - 1; i++) {
                result += arr.get(i).toString() + sep;
            }
            if (arr.size() > 0) {
                result += arr.get(arr.size() - 1).toString();
            }
        }
        return result;
    }
    
    public static List<String> stringSplit (Object obj, String sep) {
        List<String> result = null;
        if (obj != null && sep != null) {
            String[] arr = obj.toString().split("sep");
            result = java.util.Arrays.asList(arr);
        }
        return result;
    }

    public static Calendar stringYYYYMMDD2date(String s) {
        GregorianCalendar cal = new GregorianCalendar();
        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            date = sdf.parse(s);
        }
        catch (Exception e) {
            logger.info("stringYYYYMMDD2date - " + e.getMessage());
        }
        if (date == null) {
            return null;
        }
        else {
            cal.setTime(date);
            return cal;
        }

    }

    public static Timestamp stringYYYYMMDD2Timestamp(String s) {
        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            date = sdf.parse(s);
        }
        catch (Exception e) {
            logger.info("stringYYYYMMDD2Timestamp" + e.getMessage());
        }
        if (date == null) {
            return null;
        }
        else {
            return new Timestamp(date.getTime());
        }

    }

    public static String removeLeadingZeros(Object s) {
        String result = null;

        if (s != null) {
            result = s.toString().replaceAll("^0*", "");
        }

        return result;
    }
    

    public static String substring (String s, int from, int to) {
        String result;
        
        try {
            if (s == null) {
                result = null;
            }
            else if (from < 0) {
                from = s.length() + from;
                result = substring(s, from, to);
            }
            else if (from >= s.length()) {
                result = null;
            }
            else if (to < 0) {
                to = s.length() + to + 1;
                result = substring(s, from, to);
            }
            else if (to < from) {
                result = null;
            }
            else if (to > s.length()) {
                result = s.substring(from);
            }
            else {
                result = s.substring(from, to);
            }
        }
        catch (Exception e) {
            logger.error("Unexpected Exception - ", e);
            result = null;
        }
        
        return result;
    }

    /**
     * Escape string for xml usage. This converts all bytes (integer) into its string representation
     * separated by comma. String size will be increased 2-5 times
     * @param rawString
     * @return
     */
    public static String stringifyString (String rawString) {
        if (rawString == null) {
            return null;
        }
        
        String result = null;
        
        try {
            byte[] ba = rawString.getBytes();
            String[] sa = new String[ba.length];

            for (int i = 0; i < ba.length; i++) {
                sa[i] = Byte.toString(ba[i]);
            }
            
            result = com.texasbruce.commons.utils.CommonUtils.stringJoin(sa, ",");

        } 
        catch (Exception e) {
            logger.debug("stringifyString - " + formatException(e));
        }
        
        return result;
    }
    
    
    public static String unstringifyString (String processedString) {
        if (processedString == null) {
            return null;
        }
        
        String result = null;
        
        try {
            String[] sa = processedString.split(",");
            byte[] ba = new byte[sa.length];
            
            for (int i = 0; i < sa.length; i++) {
                ba[i] = Byte.parseByte(sa[i]);
            }
            
            result = new String(ba);
        }
        catch (Exception e) {
            logger.debug("unstringifyString - " + formatException(e));
        }
        
        return result;
    }
        
    public static String stringifyByteArray (byte[] ba) {
        if (ba == null) {
            return null;
        }
        
        String result = null;
        
        try {
            String[] sa = new String[ba.length];

            for (int i = 0; i < ba.length; i++) {
                sa[i] = Byte.toString(ba[i]);
            }
            
            result = com.texasbruce.commons.utils.CommonUtils.stringJoin(sa, ",");

        } 
        catch (Exception e) {
            logger.debug("stringifyString - " + formatException(e));
        }
        
        return result;
    }
    
    
    public static byte[] unstringifyByteArray (String processedString) {
        if (processedString == null) {
            return null;
        }
        
        byte[] result = null;
        
        try {
            String[] sa = processedString.split(",");
            byte[] ba = new byte[sa.length];
            
            for (int i = 0; i < sa.length; i++) {
                ba[i] = Byte.parseByte(sa[i]);
            }
            
            result = ba;
        }
        catch (Exception e) {
            logger.debug("unstringifyString - " + formatException(e));
        }
        
        return result;
    }
    
        
    public static String formatException (Exception e) {
        if (e == null || e.getStackTrace() == null) {
            return null;
        }
        
        String result = e.getMessage() + "\n"+ e.getClass().getCanonicalName()  ;
        
        StackTraceElement[] stes = e.getStackTrace();
        for (int i = 0; i < 5 && i < stes.length; i++) {
            result += "\tat " + stes[i].toString() + "\n";
        }
        
        
        Throwable t = e.getCause();
        if (t != null) {
            result += "Caused by:" + t.getMessage() + "\n";
         
            StackTraceElement[] stes2 = t.getStackTrace();
            for (int i = 0; i < 5 && i < stes2.length; i++) {
                result += "\tat " + stes2[i].toString() + "\n";
            }
            
        }
        
        return result;
    }
    
    public static void retry (Runnable lambda, int retryCount, java.util.concurrent.Callable<Boolean> failCondition) throws Exception{
        for (int i = 0; i < retryCount; i++) {
            lambda.run();
            if (failCondition.call() == Boolean.FALSE) {
                break;
            }
        }
    }
    

}
