package com.example.springboot.service.impl;

import com.example.springboot.entity.Interval;

import java.math.BigInteger;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class test {

    public static class abc{
        int a;
    }


    public static void main(String[] args) {

/*
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        //java.sql.Date date1 = new java.sql.Date(calendar.getTime());
        //System.out.println(Date.v);*/

        String strDate = "2020-05-"+5;
        System.out.println(strDate);
        java.sql.Date date = java.sql.Date.valueOf(strDate);
        date = java.sql.Date.valueOf(date.toLocalDate().plusDays(1));
    }

    public static void add(Interval interval){
        interval.setRestBusiness(2L);
    }

    public static Integer calculatePassDay(Date deDate, Date arDate) {

        return 0;

        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long date1 = 0;
        long date2 = 0;
        try {
            date1 = dateFormat.parse(dateFormat.format(deDate)).getTime();
            date2 = dateFormat.parse(dateFormat.format(arDate)).getTime();
            System.out.println(date1+" "+date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) ((date2-date1) / (1000 * 3600 * 24));*/

    }
}
