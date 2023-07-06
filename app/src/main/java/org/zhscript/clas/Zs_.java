package org.zhscript.clas;

import android.webkit.JavascriptInterface;

/**
 * Created by zzzzzzzzzzz on 16-12-6.
 */

public abstract class Zs_ {
    @JavascriptInterface
    public String fn2(int wvid, String code, String... a) {
        return Util_.s__(i2__(wvid, code, a));
    }
    @JavascriptInterface
    public String fn(String code, String... a) {
        return fn2(0, code, a);
    }

    @JavascriptInterface
    public String f1(String code) {
        return fn(code);
    }
    @JavascriptInterface
    public String f2(String code, String arg1) {
        return fn(code, arg1);
    }
    @JavascriptInterface
    public String f3(String code, String arg1, String arg2) {
        return fn(code, arg1, arg2);
    }
    @JavascriptInterface
    public String f4(String code, String arg1, String arg2, String arg3) {
        return fn(code, arg1, arg2, arg3);
    }
    @JavascriptInterface
    public String f5(String code, String arg1, String arg2, String arg3, String arg4) {
        return fn(code, arg1, arg2, arg3, arg4);
    }
    @JavascriptInterface
    public String f6(String code, String arg1, String arg2, String arg3, String arg4, String arg5) {
        return fn(code, arg1, arg2, arg3, arg4, arg5);
    }

    public abstract String[] i__(String src, boolean src_is_file, String src2,
                                 Tag_.Ctrl_ ctrl, Object resource, String[] a2, long qv_up, String... a);
    public String[] i__(String src, Tag_.Ctrl_ ctrl, Object resource, long qv_up, String... a) {
        return i__(src, false, null, ctrl, resource, null, qv_up, a);
    }
    public String[] i__(String src, Object resource, long qv_up, String... a) {
        return i__(src, null, resource, qv_up, a);
    }
    public String[] i__(String src, long qv_up, String... a) {
        return i__(src, null, qv_up, a);
    }
    public String[] i__(String src, String... a) {
        return i__(src, 0, a);
    }
    public String[] i2__(String src, String... a) {
        return i__(src, true, null, null, null, null, 0, a);
    }
    public abstract String[] i2__(int wvid, String src, String... a);
}
