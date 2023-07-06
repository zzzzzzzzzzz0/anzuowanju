package org.zhscript.clas;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzzzzzzzzzz on 17-1-5.
 */

public class Zhuanping_ {
    public static Dlg_.Click dlg_click_ = new Dlg_.Click() {
        @Override
        public void click__(String[] a2, String[] a, Activity c) {
            set__(a2[0], true, c);
        }
    };

    public static void set__(String s, boolean write, Activity c) {
        int i = get__(s), i2;
        switch (i) {
            //自动
            case     ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED:
                i2 = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
                break;
            //仅横屏
            case     ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                i2 = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                break;
            //仅竖屏
            case     ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                i2 = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                break;
            //仅横屏(反)
            case     ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE:
                i2 = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                break;
            //仅竖屏(反)
            case     ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT:
                i2 = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                break;
            //仅横屏(传感器)
            case     ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE:
                i2 = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
                break;
            //仅竖屏(传感器)
            case     ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT:
                i2 = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
                break;
            //物理方向传感器
            case     ActivityInfo.SCREEN_ORIENTATION_SENSOR:
                i2 = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
                break;
            //方向传感器
            case     ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR:
                i2 = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR;
                break;
            //首选方向
            case     ActivityInfo.SCREEN_ORIENTATION_USER:
                i2 = ActivityInfo.SCREEN_ORIENTATION_USER;
                break;
            default:
                return;
        }
        c.setRequestedOrientation(i2);
        if(write) {
            if(i2 == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                c.deleteFile(filename_);
            else
                FileOp_.write__("“" + s + "”、", filename_, c);
        }
    }
    public static String filename_ = Url_.init_ + "zhuanping.zs";

    //@ActivityInfo.ScreenOrientation
    static int get__(String s) {
        int i = item_.get(s);
        return i >= 1000 ? i % 100 : i;
    }

    /*
    设定Activity主窗口的方向，数组中的方向会设定给R.attr类中的screenOrientation属性，
    screenOrientation的属性值必须是以下常量值。
    SCREEN_ORIENTATION_UNSPECIFIED：	不指定方向，让系统决定Activity的最佳方向。
    SCREEN_ORIENTATION_LANDSCAPE：	希望Activity在横向屏上显示，也就是说横向的宽度要大于纵向的高度，并且忽略方向传感器的影响。
    SCREEN_ORIENTATION_PORTRAIT：	希望Activity在纵向屏上显示，也就是说纵向的高度要大于横向的宽度，并且忽略方向传感器的影响。
    SCREEN_ORIENTATION_USER：		使用用户设备的当前首选方向。
    SCREEN_ORIENTATION_BEHIND：		始终保持与屏幕一致的方向，不管这个Activity在前台还是后台。
    SCREEN_ORIENTATION_SENSOR：		Activity的方向由物理方向传感器来决定，按照用户旋转设备的方向来显示。
    SCREEN_ORIENTATION_NOSENSOR：	始终忽略方向传感器的判断，当用户旋转设备时，显示不跟着旋转。
    SCREEN_ORIENTATION_SENSOR_LANDSCAPE：	希望Activity在横向屏幕上显示，但是可以根据方向传感器指示的方向来进行改变。
    SCREEN_ORIENTATION_SENSOR_PORTRAIT：		希望Activity在纵向屏幕上显示，但是可以根据方向传感器指示的方向来进行改变。
    SCREEN_ORIENTATION_REVERSE_LANDSCAPE：	希望Activity在横向屏幕上显示，但与正常的横向屏幕方向相反。
    SCREEN_ORIENTATION_REVERSE_PORTRAIT：	希望Activity在纵向屏幕上显示，但与正常的纵向屏幕方向相反
    SCREEN_ORIENTATION_FULL_SENSOR:			Activity的方向由方向传感器来决定，显示会根据用户设备的移动情况来旋转。
     */
    static final HashMap<String, Integer> item_ = new HashMap<String, Integer>() {{
        put("自动",           ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        put("仅横屏",         ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE         + 1000);
        put("仅竖屏",         ActivityInfo.SCREEN_ORIENTATION_PORTRAIT          + 1100);
        put("仅横屏-反",      ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE + 1200);
        put("仅竖屏-反",      ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT  + 1300);
        put("仅横屏-传感器",  ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE  + 1400);
        put("仅竖屏-传感器",  ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT   + 1500);
        put("物理方向传感器", ActivityInfo.SCREEN_ORIENTATION_SENSOR            + 1600);
        put("方向传感器",     ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR       + 1700);
        put("首选方向",       ActivityInfo.SCREEN_ORIENTATION_USER              + 1800);
    }};

    static String[] item2_ /*= item_.keySet().toArray(new String[] {})*/;
    public static String[] item__() {
        if(item2_ == null) {
            ArrayList<Map.Entry<String, Integer>> al =
                    new ArrayList<Map.Entry<String, Integer>>(item_.entrySet());
            Collections.sort(al, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue() - o2.getValue();
                }
            });
            item2_ = new String[al.size()];
            for(int i = 0; i < al.size(); i++) {
                Map.Entry<String, Integer> e = al.get(i);
                System.out.println(i + ") " + e);
                item2_[i] = e.getKey();
            }
        }
        return item2_;
    }
}
