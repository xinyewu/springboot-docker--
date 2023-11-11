package com.yc;

public class StringUtils {
    public static boolean empty(String str){
        if (str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
}
