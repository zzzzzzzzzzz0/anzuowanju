package org.zhscript.clas;

import android.webkit.WebView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zzzzzzzzzzz on 16-12-11.
 */

public class Tag_ {
    public static class Finished_ {
        public String src_;
        public String[] a_;
        public Finished_(String src, String[] a) {
            src_ = src;
            a_ = a;
        }
    }

    public static class Ctrl_ {
        public UrlBig_ ub_;
        public String url3_;
        public boolean clear_history_, is_reload_;
        public ArrayList<String> xianguan_ = new ArrayList<>();
        public ArrayList<Finished_> finished_ = new ArrayList<>();
    }
    private HashMap<WebView, Ctrl_> ctrl_ = new HashMap<>();

    public Ctrl_ ctrl__(WebView wv, boolean auto) {
        Ctrl_ c = ctrl_.get(wv);
        if(c == null && auto) {
            ctrl_.put(wv, c = new Ctrl_());
        }
        return c;
    }
    public Ctrl_ ctrl__(WebView wv) {
        return ctrl__(wv, false);
    }

    public void delete__(WebView wv) {
        Ctrl_ c = ctrl_.remove(wv);
        if(c == null) {
            System.out.println("Ctrl_delete__ no");
            return;
        }
        c.ub_.reset__();
    }

    public HashMap<String, String> file_ = new HashMap<>();
}
