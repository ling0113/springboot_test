package com.springboot_test.utils;


import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/**
 * @author kingboy
 * @Date 2017/7/22 下午7:16
 * @Description LocaDateTimeUtilsTest is used to 测试LocalDateTime工具
 */
public class LocaDateTimeUtilsTest {

    @Test
    public void format_test() {
        System.out.println(LocalDateTimeUtils.formatNow("yyyy年MM月dd日 HH:mm:ss"));
    }

    @Test
    public void betweenTwoTime_test() {
        LocalDateTime start = LocalDateTime.of(1993, 10, 13, 11, 11);
        LocalDateTime end = LocalDateTime.of(1994, 11, 13, 13, 13);
        System.out.println("年:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.YEARS));
        System.out.println("月:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MONTHS));
        System.out.println("日:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.DAYS));
        System.out.println("半日:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));
        System.out.println("小时:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HOURS));
        System.out.println("分钟:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MINUTES));
        System.out.println("秒:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.SECONDS));
        System.out.println("毫秒:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MILLIS));
        //=============================================================================================
        /*
                                      年:1
                                      月:13
                                      日:396
                                      半日:792
                                      小时:9506
                                      分钟:570362
                                      秒:34221720
                                      毫秒:34221720000
        */
    }

    @Test
    public void plus_test() {
        //增加二十分钟
        System.out.println(LocalDateTimeUtils.formatTime(LocalDateTimeUtils.plus(LocalDateTime.now(),
                20,
                ChronoUnit.MINUTES), "yyyy年MM月dd日 HH:mm"));
        //增加两年
        System.out.println(LocalDateTimeUtils.formatTime(LocalDateTimeUtils.plus(LocalDateTime.now(),
                2,
                ChronoUnit.YEARS), "yyyy年MM月dd日 HH:mm"));
        //=============================================================================================
        /*
                                        2017年07月22日 22:53
                                        2019年07月22日 22:33
         */
    }

    @Test
    public void dayStart_test() {
        System.out.println(LocalDateTimeUtils.getDayStart(LocalDateTime.now()));
        System.out.println(LocalDateTimeUtils.getDayEnd(LocalDateTime.now()));
        //=============================================================================================
        /*
                                        2017-07-22T00:00
                                2017-07-22T23:59:59.999999999
         */
    }

    @Test
    public void dayStart_test2() {
        LocalDateTime now = LocalDateTime.now();
    }

    @Test
    public void dayStart_test3() {
        Integer a = 100;
        Integer b = 100;
        Integer x = 1000;
        Integer y = 1000;
        int m = 10;
        int n = 10;
        System.out.println();
        System.out.println(a.equals(b));
        System.out.println(x.equals(y));


    }

}

