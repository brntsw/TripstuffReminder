package com.mit.bruno.tripstuffreminder.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by techresult on 24/03/2015.
 */
public class DateUtils {

    public long valueFromFormattedDate(String strDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = (Date)formatter.parse(strDate);
        return date.getTime();
    }

    public String convertCurrentTimeToDate(){
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultDate = new Date(currentTimeMillis);
        return sdf.format(resultDate);
    }

    public boolean isToday(long value){
        return android.text.format.DateUtils.isToday(value);
    }

    public boolean isBefore(String date1, String date2){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean value = false;

        try {
            if(sdf.parse(date1).before(sdf.parse(date2))){
                value = true;
            }
            else{
                value = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return value;
    }

    public String convertFromStrokeToSlash(String date){
        String day = date.substring(8, 10);
        String month = date.substring(5,7);
        String year = date.substring(0,4);

        return day + "/" + month + "/" + year;
    }

}
