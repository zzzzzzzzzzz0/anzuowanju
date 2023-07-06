package org.zhscript.clas;

import android.os.Build;
import android.webkit.WebView;

/**
 * Created by zzzzzzzzzzz on 17-11-6.
 */

public class Js_ {
    public static String[] run__(String[] a, WebView wv, Zs_ zs, long qv_up, Object resource) {
        wv.post(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                wv.evaluateJavascript(a[0], (value) -> {
                    if(a.length > 1) {
                        zs.i__(a[1], resource, qv_up, value);
                    }
                });
            } else
                wv.loadUrl(Url_.js_ + a[0]);
        });
        return null;
    }
}
