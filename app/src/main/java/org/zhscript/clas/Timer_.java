package org.zhscript.clas;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

/**
 * Created by zzzzzzzzzzz on 17-1-9.
 */

public class Timer_ {
    public static Opt parse__(String[] a) {
        Opt opt = new Opt();
        opt.src = a[1];
        opt.src2 = a[0];
        //forced up to 60000
        opt.ms2 = "";
        opt.typ = "";
        for(int i = 2; i < a.length; i++) {
            String s = a[i];
            switch (s) {
                case "-重复":
                    opt.typ = "r";
                    continue;
                case "-时间": {
                    opt.tm = new int[6];
                    for(int i2 = 0; i2 < opt.tm.length; i2++) {
                        opt.tm[i2] = Integer.valueOf(a[++i]);
                    }
                    continue;
                }
            }
            opt.ms2 += s;
        }
        return opt;
    }

    public static class Opt {
        public String typ, src, src2, ms2;
        public int[] tm;
    }

    public static void start__(String typ, String src, String src2, int[] tm, String ms2, Zs_ zs, I_ i, Context c) {
        DateTime_.MS_ ms = new DateTime_.MS_();
        if(tm != null) {
            DateTime_.time__(tm, ms2, ms);
        } else {
            DateTime_.time__(ms2, ms);
        }
        AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        Broadcast_ br = new Broadcast_(zs, i, typ.isEmpty(), c, "TIMER_ACTION" + src2) {
            @Override
            String[] args__() {
                return new String[] {ms2};
            }
        };
        PendingIntent pi = br.pi__(src, src2, c);
        switch (typ) {
            case "r":
                am.setRepeating(AlarmManager.RTC_WAKEUP, ms.l_, ms.i_, pi);
                break;
            default:
                am.set(AlarmManager.RTC_WAKEUP, ms.l_ + ms.i_, pi);
                break;
        }

        /*am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                1000,
                pi);*/
    }
}
