package org.zhscript.clas;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 17-2-4.
 */

public class Broadcast_ extends BroadcastReceiver {
    Zs_ zs_;
    I_ i_;
    boolean is_once_;
    String[] act_;
    Broadcast_(Zs_ zs, I_ i, boolean is_once, Context c, String ...act) {
        zs_ = zs;
        i_ = i;
        is_once_ = is_once;
        act_ = act;
        if(c != null)
            reg__(c);
    }

    PendingIntent pi__(String src, String src2, int act_idx, int flags, Context c) {
        Intent i = new Intent(act_[act_idx]);
        i.putExtra("src", src);
        i.putExtra("src2", src2);
        System.out.println(i.getAction()
                + "\nsrc2 " + i.getStringExtra("src2")
                + "\nsrc "  + i.getStringExtra("src"));
        return PendingIntent.getBroadcast(c, 0, i, flags);
    }
    PendingIntent pi__(String src, int act_idx, int flags, Context c) {
        return pi__(src, null, act_idx, flags, c);
    }
    PendingIntent pi__(String src, String src2, Context c) {
        return pi__(src, src2, 0, 0, c);
    }
    PendingIntent pi__(String src, Context c) {
        return pi__(src, null, c);
    }

    PendingIntent pi1__(String src, int act_idx, Context c) {
        return pi__(src, null, act_idx, PendingIntent.FLAG_ONE_SHOT, c);
    }
    PendingIntent pi1__(String src, Context c) {
        return pi1__(src, 0, c);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(is_once_)
            this.unreg__(context);
        System.out.println(intent.getAction() + " once=" + is_once_);
        try {
            zs_.i__(intent.getStringExtra("src"), false,
                    intent.getStringExtra("src2"), null, null,
                    null, 0, args__());
        } catch (Exception e) {
            i_.sendMessage__(e);
        }
    }

    String[] args__() {return null;}

    void reg__(Context c) {
        IntentFilter if1 = new IntentFilter();
        for(String s : act_)
            if1.addAction(s);
        c.registerReceiver(this, if1);
        al_reg_.add(this);
    }
    void unreg__(Context c) {
        al_reg_.remove(this);
        c.unregisterReceiver(this);
    }
    boolean is_reg__() {
        return al_reg_.contains(this);
    }
    static ArrayList<Broadcast_> al_reg_ = new ArrayList<>();
    public static void all_unreg__(Context c) {
        for(Broadcast_ thi : al_reg_) {
            try {
                c.unregisterReceiver(thi);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        al_reg_.clear();
    }
}
