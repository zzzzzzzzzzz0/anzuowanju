package org.zhscript.clas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 17-1-5.
 */

public class Dlg_ {
    public static abstract class Click {
        public abstract void click__(String[] a2, String[] a, Activity c);
    }

    final static String x_ = "*", xx_ = "**", no_ = "";

    public static String[] dlg__(final String[] a, boolean has_x, final Activity c, final Click oc) {
        AlertDialog.Builder ab = new AlertDialog.Builder(c);
        ArrayList<String> al = new ArrayList<>();
        final ArrayList<Boolean> al2 = new ArrayList<>();
        int i = 0, checked = -1;
        String typ = no_, ok = "确定", cancel = "取消";
        switch(a[i]) {
            case x_: case xx_: case no_:
                typ = a[i];
                i += 2;
                break;
            default:
                if(has_x)
                    throw I_.buzhichi__(a[i] +
                            " 非 " + x_ +
                            " 或 " + xx_ +
                            " 或 " + no_);
                break;
        }
        for(; i < a.length; i++) {
            String s = a[i];
            switch (s) {
                case "-标题":
                    ab.setTitle(a[++i]);
                    continue;
                case "*":
                    checked = al.size() - 1;
                    if(checked >= 0)
                        al2.set(checked, true);
                    continue;
                case "-确定":
                case "-取消":
                    if(!typ.equals(xx_))
                        throw I_.buzhichi__(s + " 仅用于 " + xx_);
                    if(s.equals("-确定"))
                        ok = a[++i];
                    else
                        cancel = a[++i];
                    continue;
            }
            al.add(s);
            al2.add(false);
        }
        final String[] a2 = al.toArray(new String[0]);
        switch (typ) {
            case no_:
                ab.setItems(a2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        oc.click__(new String[] {a2[which], String.valueOf(which + 1)}, a, c);
                    }
                });
                break;
            case x_:
                ab.setSingleChoiceItems(a2, checked, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        oc.click__(new String[] {a2[which], String.valueOf(which + 1)}, a, c);
                        dialog.dismiss();
                    }
                });
                break;
            case xx_: {
                boolean[] b = new boolean[al2.size()];
                for(int i2 = 0; i2 < b.length; i2++)
                    b[i2] = al2.get(i2);
                ab.setMultiChoiceItems(a2, b, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        al2.set(which, isChecked);
                    }
                })
                .setPositiveButton(ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<String> al = new ArrayList<String>();
                        for (int i = 0; i < al2.size(); i++) {
                            if(al2.get(i)) {
                                al.add(a2[i]);
                                al.add(String.valueOf(i + 1));
                            }
                        }
                        oc.click__(al.toArray(new String[0]), a, c);
                    }
                })
                .setNegativeButton(cancel, null);
                break;
            }
        }
        ab.show();

        return null;
    }

    public static void msg__(String s, Context c) {
        AlertDialog ad = new AlertDialog.Builder(c).create();
        ad.setCanceledOnTouchOutside(true);
        ad.setMessage(s);
        ad.show();
    }
}
