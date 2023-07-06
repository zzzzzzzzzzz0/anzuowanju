package org.zhscript;

import android.content.res.AssetManager;

/**
 * Created by zzzzzzzzzzz on 16-12-5.
 */

public abstract class Shell_ {
    static {
        System.loadLibrary("zhscript2");
    }

    public native void init(AssetManager am, String path, String suidao1);

    public native void z(String src, boolean src_is_file, String src2, String[] a2, String[] a,
                         long qv_up, //Object resource,
                         Result_ result) throws Exception2_;
    public abstract String[] suidao__(String[] a, long qv_up //, Object resource
    );

    public native int len(String code);
    public native int xianguan(String code, Result_ result);
    public static native long up(long qv);

    public static String null_ = "NULL";
}
