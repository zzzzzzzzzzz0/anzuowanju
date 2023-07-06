package org.zhscript.clas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 17-1-11.
 */

public class Notif_ {
    static class Notif_Opt extends Opt_ {
        int icon = android.R.drawable.ic_dialog_info, id;
        String src;
        boolean is_arg;
        ArrayList<String> al = new ArrayList<>();
        NotificationCompat.Builder b;

        @Override
        Item_[] items__() {
            return new Item_[] {
                    new Item_("-图标", 1, (s) -> {
                        icon = Start_.icon__(s[0]);
                    }),
                    new Item_("-id", 1, (s) -> {
                        String s1 = s[0];
                        switch (s1) {
                            case "戳":
                                id = (int) System.currentTimeMillis();
                                break;
                            default:
                                id = Integer.valueOf(s1);
                                break;
                        }
                    }),
                    new Item_("-代码", 1, (i, s) -> {
                        src = s[0];
                        is_arg = true;
                    }),
                    new Item_(1, (i, s) -> {
                        String s1 = s[0];
                        al.add(s1);
                        if(is_arg)
                            return;
                        switch (i) {
                            case 1:
                                b.setContentText(s1);
                                break;
                            case 2:
                                b.setContentTitle(s1);
                                break;
                            case 3:
                                b.setTicker(s1);
                                break;
                            default:
                                throw I_.buzhichi__("-第" + i + "项");
                        }
                    }),
            };
        }
    }

    static NotificationManager nm__(Context c) {
        return (NotificationManager) c.getSystemService(c.NOTIFICATION_SERVICE);
    }

    public static int notif__(String[] a, Context c, Class<?> cls) {
        Notif_Opt opt = new Notif_Opt();
        opt.b = new NotificationCompat.Builder(c);
        opt.parse__(a);


        opt.b.setSmallIcon(opt.icon);

        if(opt.src != null) {
            opt.b.setAutoCancel(true);
            Intent i = new Intent(c, cls);
            Start_.it2__(opt.src, opt.al, i);
            PendingIntent pi = PendingIntent.getActivity(c, opt.id, i, PendingIntent.FLAG_CANCEL_CURRENT);
            opt.b.setContentIntent(pi);
        }

        Notification n = opt.b.build();

        nm__(c).notify(opt.id, n);

        return opt.id;
    }

    public static void cancel__(int id, Context c) {
        nm__(c).cancel(id);
    }
}
