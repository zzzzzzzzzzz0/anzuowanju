package org.zhscript.clas;

import android.content.Context;
//import android.support.design.widget.Snackbar;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by zzzzzzzzzzz on 17-2-12.
 */

public class Msgbox_ {
    //吐司
    public static void toast__(Object s, Context c) {
        Toast.makeText(c, s.toString(), Toast.LENGTH_SHORT).show();
    }
    //快餐店;  小吃店;  小吃吧;  快餐厅
    public static void snackbar__(String s, View v) {
        Snackbar.make(v, s, Snackbar.LENGTH_LONG).setAction(null, null).show();
    }
    public void msgbox__(String s, Context c) {
        if(s == null || s.isEmpty())
            return;
        Dlg_.msg__(s, c);
    }

    public void msgbox__(String s) {
        System.out.println(s);
    }
    public void toast__(Object s) {
        System.out.println(s);
    }
    public void snackbar__(String s) {
        System.out.println(s);
    }
}
