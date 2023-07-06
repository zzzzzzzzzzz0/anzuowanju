package org.zhscript.clas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zzzzzzzzzzz on 17-1-24.
 */

public class DateTime_ {
    public static String fmt__(String s, long l) {
        return new SimpleDateFormat(
                s != null ? s : "yyyy-MM-dd HH:mm:ss").format(new Date(
                l >= 0 ? l : System.currentTimeMillis()));
    }

    public static String fmt__(String[] a) {
        return fmt__(
                a != null && a.length > 0 ? a[0] : null,
                a != null && a.length > 1 ? Long.valueOf(a[1]) : -1);
    }

    public static class MS_ {
        long l_;
        int i_;
    }

    public static void time__(int[] a, String s, MS_ ms) {
        Calendar cal = Calendar.getInstance();
        cal.set(a[0], a[1], a[2], a[3], a[4], a[5]);
        println__(cal);
        String s2 = "";
        for(int i2 = 0;; i2++) {
            if(i2 >= s.length()) {
                if(!s2.isEmpty()) {
                    cal.add(Calendar.MILLISECOND, Integer.valueOf(s2));
                }
                break;
            }
            char c2 = s.charAt(i2);
            switch (c2__(c2)) {
                default:
                    s2 += c2;
                    continue;
                case 's':
                    cal.add(Calendar.SECOND, Integer.valueOf(s2));
                    break;
                case 'm':
                    cal.add(Calendar.MINUTE, Integer.valueOf(s2));
                    break;
                case 'h':
                    cal.add(Calendar.HOUR_OF_DAY, Integer.valueOf(s2));
                    break;
                case 'd':
                    cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf(s2));
                    break;
            }
            s2 = "";
        }
        println__(cal);
        ms.l_ = cal.getTimeInMillis();
    }
    public static void time__(String s, MS_ ms) {
        ms.l_ = System.currentTimeMillis();

        String s2 = "";
        int n;
        for(int i2 = 0;; i2++) {
            if(i2 >= s.length()) {
                if(!s2.isEmpty())
                    ms.i_ += Integer.valueOf(s2);
                break;
            }
            char c2 = s.charAt(i2);
            switch (c2__(c2)) {
                default:
                    s2 += c2;
                    continue;
                case 's':
                    n = 1000;
                    break;
                case 'm':
                    n = 1000 * 60;
                    break;
                case 'h':
                    n = 1000 * 60 * 60;
                    break;
                case 'd':
                    n = 1000 * 60 * 60 * 24;
                    break;
            }
            ms.i_ += Integer.valueOf(s2) * n;
            s2 = "";
        }
    }

    static char c2__(char c2) {
        switch (c2) {
            case 's': case '秒':
                return 's';
            case 'm': case '分':
                return 'm';
            case 'h': case '时':
                return 'h';
            case 'd': case '天':
                return 'd';
        }
        return ' ';
    }

    public static void println__(Calendar cal) {
        System.out.println(cal.get(Calendar.YEAR) + "Y"
                        + cal.get(Calendar.MONTH) + "M"
                        + cal.get(Calendar.DATE) + "d"
                        + cal.get(Calendar.HOUR_OF_DAY) + "h"
                        + cal.get(Calendar.MINUTE) + "m"
                        + cal.get(Calendar.SECOND) + "s");
    }
}
