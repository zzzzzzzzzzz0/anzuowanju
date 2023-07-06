package org.zhscript.clas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ViewFlipper;

import java.util.HashMap;

/**
 * Created by zzzzzzzzzzz on 16-12-6.
 */

public class Switch_ {
    WebView wv_;
    ViewFlipper vf_;
    boolean has_index_;
    public boolean has_index__() {return has_index_;}

    public static final String blank_ = "about:blank";

    public Switch_(View wv, View vf, boolean has_index) {
        wv_ = (WebView)wv;
        vf_ = (ViewFlipper)vf;
        has_index_ = has_index;
    }

    public int i__() {
        return vf_.getDisplayedChild();
    }
    public int len__() {
        return vf_.getChildCount();
    }
    public WebView wv__(int i) {
        return (WebView) vf_.getChildAt(i);
    }
    public WebView wv__() {
        return wv__(i__());
    }
    public WebView wv0__() {
        return wv_;
    }

    public int width__() {
        return vf_.getWidth();
    }

    HashMap<WebView, WebView> opener_ = new HashMap<>();
    public WebView new__(Context c) {
        WebView wv = new WebView(c);
        opener_.put(wv, wv__());
        vf_.addView(wv, i__() + 1, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        next__();
        on_new__(wv);
        return wv;
    }
    protected void on_new__(WebView wv) {}

    public static void load__(WebView wv, String html, String history_url, String base_url) {
        wv.loadDataWithBaseURL(base_url, html, "text/html", "utf-8", history_url);
    }

    public WebView new__(String html, String history_url, String base_url, Context c) {
        WebView wv = new__(c);
        load__(wv, html, history_url, base_url);
        return wv;
    }

    public void next__() {
        vf_.showNext();
    }

    public void back__() {
        vf_.showPrevious();
    }

    public boolean delete__(WebView wv) {
        if(wv == wv_) {
            if(has_index_) {
            }
            return false;
        }
        wv.loadUrl(blank_);
        show__(opener_.get(wv));
        opener_.remove(wv);
        vf_.removeView(wv);
        on_delete__(wv);
        return true;
    }
    protected void on_delete__(WebView wv) {}

    public void show__(WebView wv) {
        int i1 = -1;
        for(int i = 0; i < len__(); i++) {
            if(wv == wv__(i)) {
                i1 = i;
                break;
            }
        }
        if(i1 < 0)
            return;
        int i2 = i__();
        if(i1 < i2) {
            for(int i = i1; i < i2; i++) {
                back__();
            }
        } else {
            for(int i = i1; i > i2; i--) {
                next__();
            }
        }
    }

    public void clear__() {
        for(int i = 0; i < len__(); i++) {
            WebView wv = wv__(i);
            wv.clearHistory();
            wv.clearCache(true);
        }
    }
}
