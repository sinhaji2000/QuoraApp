package org.quoraapp.quoraapp.utils;

import java.time.LocalDateTime;

public class CursorUtils {

    public static boolean isCursorValid(String cursor){

        if(cursor == null || cursor.isEmpty()){
            return false;
        }

        try{
            LocalDateTime.parse(cursor);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static LocalDateTime parseCursor(String cursor){
        if(!isCursorValid(cursor)){
            throw new IllegalArgumentException("Cursor Not Valid");
        }

        return LocalDateTime.parse(cursor);
    }
}
