package com.example.aldres.spacexdata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Aldres on 20.01.2018.
 */

public class UnixDateConverter {

    public static String convertDate (long unixDate) {
        Date date = new Date(unixDate*1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        String convertedDate = dateFormat.format(date);
        return convertedDate;
    }
}
