package org.zhscript.clas;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zzzzzzzzzzz on 16-12-11.
 */

public class Input_ {
    Tag_ tag_;
    Zs_ zs_;
    FileOp_ fo_;

    public Input_(Zs_ zs, Tag_ tag, FileOp_ fo) {
        zs_ = zs;
        tag_ = tag;
        fo_ = fo;
    }

    /*
    loadData(s, "text/html; charset=utf-8", null)
    */
    String data__(String url, boolean use_save) {
        InputStream is = fo_.open__(url + ".zs", use_save);
        if(is != null) {
            try {
                String s = FileOp_.read__(is);
                String[] a = zs_.i__(s);
                if(a.length > 0) {
                    return a[0];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    WebResourceResponse wrr__(String s) {
        return new WebResourceResponse("text/html", "utf-8", s == null ? null
                : new ByteArrayInputStream(s.getBytes()));
    }

    final String reload_ = "c:reload/";
    public WebResourceResponse reload__(String url) {
        //System.out.println("reload__" + url);
        if(url.startsWith(reload_)) {
            return wrr__(data__(url.substring(reload_.length()), true));
        }
        return null;
    }
    public void reload__(WebView wv, boolean use_save, String url) {
        if(url == null)
            url = wv.getUrl();
        for(;;) {
            if (url.startsWith(Web_.root_)) {
                String url3 = url.substring(Web_.root_.length());
                int i = url3.lastIndexOf(".html");
                if (i >= 0) {
                    tag_.ctrl__(wv).url3_ = url3.substring(0, i);
                    break;
                }
            }
            System.err.println(url);
            return;
        }
        Tag_.Ctrl_ ctrl = tag_.ctrl__(wv);
        ctrl.clear_history_ = true;
        Web_.load__(wv, data__(ctrl.url3_, use_save));
        //wv.loadUrl(reload_ + url);
    }
    public void reload__(WebView wv) {
        reload__(wv, true, null);
    }
}
