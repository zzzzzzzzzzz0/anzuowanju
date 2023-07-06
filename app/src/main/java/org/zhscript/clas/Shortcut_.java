package org.zhscript.clas;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zzzzzzzzzzz on 17-2-27.
 */

public class Shortcut_ {
    public static String[] shortcut__(String[] a, I_ i1) {
        if (!i1.permi_.z__(Permi_.Companion.getShortcut_(), i1))
            throw I_.buliao__();
        Context context = i1.context__();
        Intent i = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //i.putExtra("duplicate", false); //不允许重复创建
        String name = a[0];
        i.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        class Opt extends Opt_ {
            int rid = i1.ic_launcher__();
            int in_menu = 0;
            ArrayList<String> al = new ArrayList<>();

            @Override
            Item_[] items__() {
                return new Item_[] {
                        new Item_("-图标", 1, (s) -> {
                            rid = Start_.icon__(s[0]);
                        }),
                        new Item_("-菜单", (String s) -> {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                                throw I_.buzhichi__("-选项：" + s);
                            }
                            in_menu = 1;
                        }),
                        new Item_("-菜单2", (String s) -> {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                                throw I_.buzhichi__("-选项：" + s);
                            }
                            in_menu = 2;
                        }),
                        new Item_(1, (s) -> {
                            al.add(s[0]);
                        }),
                };
            }
        }
        Opt opt = new Opt();
        opt.parse__(a, 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager sm = (ShortcutManager)context.getSystemService(Context.SHORTCUT_SERVICE);
            if(sm != null) {
                Intent i2 = new Intent(context, i1.class__());
                i2.setAction(Intent.ACTION_VIEW);
                Start_.it2__(opt.al, i2);
                ShortcutInfo si = new ShortcutInfo.Builder(context,
                            name + "-" + String.valueOf(opt.in_menu)) //String.valueOf(opt.al.hashCode())
                        //.setShortLabelResId(R.string.lable_shortcut_static_search_disable)
                        //.setLongLabelResId(R.string.lable_shortcut_static_search_disable)
                        //.setIcon(Icon.createWithResources(context, R.mipmap.ic_shortcut))
                        .setIcon(Icon.createWithResource(context, opt.rid))
                        .setShortLabel(name)
                        .setIntent(i2)
                        .build();
                switch (opt.in_menu) {
                    case 1: {
                        List<ShortcutInfo> ls;
                        ls = sm.getDynamicShortcuts();
                        ls.add(si);
                        sm.setDynamicShortcuts(ls);
                        return null;
                    }
                    case 2: {
                        List<ShortcutInfo> ls;
                        ls = sm.getPinnedShortcuts();
                        ls.add(si);
                        sm.updateShortcuts(ls);
                        return null;
                    }
                    default:
                        if (sm.isRequestPinShortcutSupported()) {
                            boolean b = sm.requestPinShortcut(si, null);
                            return null;
                        }
                        break;
                }
            }
        }
        Context ac = context.getApplicationContext();
        Parcelable icon = Intent.ShortcutIconResource.fromContext(ac, opt.rid);
        i.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        Intent i2 = new Intent(ac, i1.class__());
        Start_.it2__(opt.al, i2);
        //Start_.new_task__(i2);
        i.putExtra(Intent.EXTRA_SHORTCUT_INTENT, i2);
        context.sendBroadcast(i);
        return null;
    }
}
