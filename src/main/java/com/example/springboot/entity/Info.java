package com.example.springboot.entity;


import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Data
public class Info {

    private BigInteger trainId;

    private String trainNumber;

    private String trainName;

    private Time trainDepartTime;

    private Date trainDepartDate;

    private Time trainArriveTime;

    private Date trainArriveDate;

    private Integer passDay;

    private String trainDepartStation;

    private Long deID;

    private String trainArriveStation;

    private Long arID;

    private Long restHard;

    private Long restSoft;

    private Long restBusiness;

    private Long restHardSleepDown;

    private Long restHardSleepMiddle;

    private Long restHardSleepUp;

    private Long restSoftSleepDown;

    private Long restSoftSleepUp;

    private Long restFirst;

    private Long restSecond;

    private Long restSuper;

    public Integer calculatePassDay(Date deDate, Date arDate){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long date1 = 0;
        long date2 = 0;
        try {
            date1 = dateFormat.parse(dateFormat.format(deDate)).getTime();
            date2 = dateFormat.parse(dateFormat.format(arDate)).getTime();
            System.out.println(date1+" "+date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) ((date2-date1) / (1000 * 3600 * 24));

    }


    /*public Info() {
        this.restHard = 0L;

        this.restSoft = 0L;

        private Long restBusiness;

        private Long restHardSleepDown;

        private Long restHardSleepMiddle;

        private Long restHardSleepUp;

        private Long restSoftSleepDown;

        private Long restSoftSleepUp;

        private Long restFirst;

        private Long restSecond;

        private Long restSuper;
    }*/

}
