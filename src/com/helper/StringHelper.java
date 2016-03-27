/*
 * Created on Dec 27, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.helper;

import java.text.CharacterIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author user
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StringHelper {

	public static String[] stringSplit(String target,String delim){
		String[] ret=new String[255];
		int index=-1;
		StringTokenizer st = new StringTokenizer(target,delim);
		while(st.hasMoreTokens()){
			String obj=st.nextToken();
			if(obj.indexOf("\"")!=-1)
				obj=obj.substring(1,obj.lastIndexOf("\""));
			ret[++index]=obj;
		}
		String[] ret1 = new String[index+1];
		for(int i=0;i<=index;i++){
			ret1[i]=ret[i];
		}
		return ret1;
	
	}
	public static String emptyToStringNull(String d){
		String ret = d;
		if(ret.equals("")){
			ret = "NULL";
		}
		return ret;
	}

   public static String toSentenceCase(String s){
       String s2 = "";
       s2 += Character.toUpperCase(s.charAt(0));
       for (int i = 1; i < s.length(); i++) {
           if (Character.isUpperCase(s.charAt(i))) {
               s2 += Character.toLowerCase(s.charAt(i));
           } else {
               s2 += s.charAt(i);
           }
       }
       return s2;
   }
 


	public static String nullToStringEmpty(String d){
		String ret = d;
		if(ret==null){
			ret =  "";
		}
		
		return ret;
	}
	public static String nullObjectToStringEmpty(Object d){
		String dual="";
		if(d==null){
			dual =  "";
		}
		else
			dual=d.toString().trim();
		
		return dual;
	}
	public static int nullObjectToIntegerEmpty(Object d){
		int i=0;
		if(d!=null){
			String dual=d.toString().trim();
			try{
				i=new Integer(dual).intValue();
			}catch (Exception e) {
				System.out.println("Unable to find integer value");	
			}
		}
		return i;
	}
	public static void main(String[] args) {
		System.out.println(usualRound(new Float("2.0").floatValue()));
		
	}
	 public static float usualRound(float f) {
		 	try{
		 		String flo = (f+"").substring(0,(f+"").indexOf(".")+3);
		 		f = new Float(flo).floatValue();
		 	}catch (Exception e) {}
		 	float result = ((float)((int)(100*f)))/100;
	        System.out.println(100*f);
	        if ( ((int)(1000 * f) % 10) < 5 )
	            return result;
	        return (result + 0.01f);
	    }
		public static String CovertIdAry2String(int[] id_array){
			if(id_array!=null){
				String inStr = "";
				for(int i=0; i<id_array.length; i++){
					inStr += id_array[i];
					if((i+1)<id_array.length) inStr += ", ";
				}
				return (inStr.equals("")?null:inStr);
			}
			return null;
		}


    public static void print(Object array[]) {
        int i = 0;
        for (i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }


public static String convertDate(String logTime){
      SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String textDate = logTime;
        try {
            Date date = sdfInput.parse(textDate);
            textDate=sdfOutput.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(StringHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
            return textDate;
}


    public static String backlashReplace(String myStr) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(myStr);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '\\') {
                result.append("/");
            } else {
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }
}
