package org.zhscript.clas;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import org.zhscript.Shell_;

/**
 * Created by zzzzzzzzzzz on 16-12-16.
 */

public class Sms_ {
    Shell_ shell_;
    Zs_ zs_;
    Tag_ tag_;

    public Sms_(Shell_ shell, Zs_ zs, I_ i, Tag_ tag, Context c) {
        shell_ = shell;
        zs_ = zs;
        tag_ = tag;
        br_ = new Broadcast_(zs, i, false, c, "SENT_SMS_ACTION", "DELIVERED_SMS_ACTION") {
            @Override
            String[] args__() {
                String res;
                int i = getResultCode();
                switch (i) {
                    case Activity.RESULT_OK:
                        res = "ok";
                        break;
                    default:
                        res = Integer.toString(i);
                        break;
                }
                return new String[] {res};
            }
        };
        br2_ = new Broadcast_(zs, i, false, null, "android.provider.Telephony.SMS_RECEIVED") {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                if (null != bundle) {
                    Object[] smsObj = (Object[]) bundle.get("pdus");
                    String addr = null, s = "";
                    long tm = 0, tm_old = 0;
                    for (Object object : smsObj) {
                        SmsMessage msg = SmsMessage.createFromPdu((byte[]) object);
                        tm = msg.getTimestampMillis();
                        if(tm_old != tm) {
                            if(tm_old != 0) {
                                out__(addr, s, tm_old);
                                s = "";
                            }
                            tm_old = tm;
                        }
                        addr = msg.getOriginatingAddress();
                        s += msg.getDisplayMessageBody();
                    }
                    out__(addr, s, tm);
                }
            }

            void out__(String addr, String s, long tm) {
                if(s.isEmpty())
                    return;
                zs_.i__(tag_.file_.get(sms_zs_), addr, s, DateTime_.fmt__(null, tm));
            }
        };
        br2__(c);
    }

    Broadcast_ br_, br2_;

    public static final String sms_zs_ = "sms.zs";
    public void br2__(Context c) {
        String code = tag_.file_.get(sms_zs_);
        boolean b = shell_.len(code) > 0;

        if(b) {
            if(!br2_.is_reg__())
                br2_.reg__(c);
        } else {
            if (br2_.is_reg__())
                br2_.unreg__(c);
        }
    }

    public String[] send__(String[] a, Context this1) {
        SmsManager sm = android.telephony.SmsManager.getDefault();
        PendingIntent pi1 = null, pi2 = null;
        if (a.length >= 3)
            pi1 = br_.pi1__(a[2], this1);
        if (a.length >= 4)
            pi2 = br_.pi1__(a[3], 1, this1);
        for (String s : sm.divideMessage(a[1])) {
            sm.sendTextMessage(a[0], null, s, pi1, pi2);
        }
        return null;
    }
}
