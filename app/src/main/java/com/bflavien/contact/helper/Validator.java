package com.bflavien.contact.helper;

/**
 * Created by gregoire on 1/21/15.
 */
public class Validator {
    
    
    public static Boolean isValid(String s){
        return s!=null && !s.trim().isEmpty() && !s.trim().equals("") ;
    }

    public static String validate(String string) {
        if( string == null || string.trim().equals("") || string.trim().equals("null")){
            return "";
        }else{
            return string;
        }
    }

    public static boolean isValidEmail(String email, boolean allowBlank) {
        return !isValid(email) ? allowBlank : android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidLenght(String value, int maxValue, int minValue){
        if((maxValue != 0 && value.length() > maxValue) || (minValue != 0 && value.length() < minValue)) return false;
        return true;
    }

    /**
     * Delete + and spaces in the phone number
     * @param phone
     * @return
     */
    public static String changePhone(String phone){
        if(phone.startsWith("+")) return phone.substring(1).replaceAll(" ", "");
        else return phone.replaceAll(" ", "");
    }
    
}
