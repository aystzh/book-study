package aystzh.com.study.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    private static DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    public static Date simpleDateFormat(String date){
        Date d=null;
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
