package org.zhscript.clas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zzzzzzzzzzz on 17-1-23.
 */

public class ActivityResult_ {
    private static int reqcode_ = 0;

    public abstract static class CallBack_ {
        protected abstract void result__(int resultCode, Intent data);
    }

    class Item_ {
        int reqcode_, rescode_ = RESULT_OK;
        String src_;
        CallBack_ cb_;
        ArrayList<String> argnames_ = new ArrayList<>();

        String[] result_(Intent data, Context c) {
            ArrayList<String> al = new ArrayList<>();
            for (String name : argnames_) {
                switch (name) {
                    case "data":
                        if(data != null)
                            al.add(data.getDataString());
                        continue;
                }
                final String data2 = "data:";
                if(name.startsWith(data2)) {
                    if(data != null) {
                        Cursor q = c.getContentResolver().query(data.getData(), null, null, null, null);
                        q.moveToFirst();
                        al.add(q.getString(q.getColumnIndex(name.substring(data2.length()))));
                    }
                    continue;
                }

                String typ = "s";
                int i1 = name.indexOf(':');
                if(i1 > 0) {
                    typ = name.substring(i1 + 1);
                    name = name.substring(0, i1);
                }
                switch (typ) {
                    case "s":
                        if(data != null)
                            al.add(data.getStringExtra(name));
                        break;
                    case "b":
                        if(data != null)
                            al.add(String.valueOf(data.getBooleanExtra(name, false)));
                        break;
                    case "i":
                        if(data != null)
                            al.add(String.valueOf(data.getIntExtra(name, 0)));
                        break;
                    case "l":
                        if(data != null)
                            al.add(String.valueOf(data.getLongExtra(name, 0)));
                        break;
                    case "f":
                        if(data != null)
                            al.add(String.valueOf(data.getFloatExtra(name, 0)));
                        break;
                    case "d":
                        if(data != null)
                            al.add(String.valueOf(data.getDoubleExtra(name, 0)));
                        break;
                    default:
                        throw I_.buzhichi__("- " + typ);
                }
            }
            return al.toArray(new String[0]);
        }
    }
    ArrayList<Item_> items_ = new ArrayList<>();

    public int add__(String[] a, int from, int to, CallBack_ cb, String[] defa, int reqcode) {
        Item_ i = new Item_();
        if(cb != null)
            i.cb_ = cb;
        boolean use_defa = true;
        if(a != null) {
            int i1 = from;
            i.src_ = a[i1++];
            if (to < 0 || to > a.length)
                to = a.length;
            if (reqcode == -1 && a.length > i1) {
                reqcode = Start_.to_int__(a[i1++]);
                if (a.length > i1) {
                    i.rescode_ = Start_.to_int__(a[i1++]);
                }
            }
            if (i1 < to) {
                for (; i1 < to; i1++)
                    i.argnames_.add(a[i1]);
                use_defa = false;
            }
        }
        if (use_defa && defa != null)
            for (String s : defa)
                i.argnames_.add(s);
        //查错
        i.result_(null, null);
        if(reqcode == -1) {
            reqcode = ++reqcode_;
            if(reqcode_ >= 1000)
                reqcode_ = 0;
        }
        i.reqcode_ = reqcode;
        items_.add(i);
        return reqcode;
    }
    public int add__(String[] a, int from) {
        return add__(a, from, -1, null, null, -1);
    }
    public int add__(CallBack_ cb) {
        return add__(null, 0, 0, cb, null, -1);
    }
    public int add__(String[] a, int from, String[] defa, int reqcode) {
        return add__(a, from, -1, null, defa, reqcode);
    }

    Zs_ zs_;

    public boolean on__(int requestCode, int resultCode, Intent data, Context c) {
        for (Item_ i : items_) {
            if(i.reqcode_ == requestCode) {
                if(i.src_ != null)
                    if(resultCode == i.rescode_)
                        zs_.i__(i.src_, i.result_(data, c));
                if(i.cb_ != null)
                    i.cb_.result__(resultCode, data);
                items_.remove(i);
                return true;
            }
        }
        return false;
    }

    public ActivityResult_(Zs_ zs) {
        zs_ = zs;
    }
}
