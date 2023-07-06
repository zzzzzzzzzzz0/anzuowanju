package org.zhscript.clas;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zzzzzzzzzzz on 17-4-1.
 */

public class Package_ {
    static public String[] for__(String[] a, Zs_ zs, long qv_up, Object resource, Context c) {
        PackageManager pm = c.getPackageManager();
        List <PackageInfo> ip=pm.getInstalledPackages(PackageManager.GET_PERMISSIONS|
                                                        PackageManager.GET_RECEIVERS|
                                                        PackageManager.GET_SERVICES|
                                                        PackageManager.GET_PROVIDERS);
        ArrayList<String[]> ls = new ArrayList<>();
        for(PackageInfo pi:ip) {
            ApplicationInfo ai = pi.applicationInfo;
            /*String[] reqPermission=pi.requestedPermissions;
            if(reqPermission!=null){
                for(int i=0;i<reqPermission.length;i++){
                    s+="\n"+reqPermission[i];
                }
            }
            PermissionInfo[] permission=pi.permissions;
            ServiceInfo[] services=pi.services;
            ProviderInfo[] providers=pi.providers;*/
            ls.add(new String[]{ai.loadLabel(pm).toString(),
                    pi.versionName, String.valueOf(pi.versionCode),
                    pi.packageName, ai.className, ai.nativeLibraryDir, ai.publicSourceDir});
        }

        class Sort_ implements Comparator {
            int i_;
            Sort_(int i) {i_ = i;}

            @Override
            public int compare(Object o, Object t1) {
                String[] a1 = (String[])o;
                String[] a2 = (String[])t1;
                return a1[i_].compareTo(a2[i_]);
            }
        }
        Collections.sort(ls, new Sort_(3));

        String s = "";
        for(String[] i : ls) {
            s += Util_.s__(zs.i__(a[0], resource, qv_up, i));
        }
        return new String[] {s};
    }

    static void set__(Intent it, String val, Context c) {
        val = Regex_.s__(val);
        PackageManager pm = c.getPackageManager();
        List<ResolveInfo> ls = pm.queryIntentActivities(it, PackageManager.GET_META_DATA);
        for (ResolveInfo ri : ls) {
            String name2 = ri.activityInfo.applicationInfo.packageName;
            //System.out.println(name2);
            if(name2.matches(val)) {
                it.setPackage(name2);
                break;
            }
        }
    }
}
