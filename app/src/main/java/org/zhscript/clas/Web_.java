package org.zhscript.clas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.zhscript.Shell_;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 16-12-6.
 */

public class Web_ {
    Switch_ switch_;
    FileOp_ fo_;
    Tag_ tag_;
    Zs_ zs_;
    DownloadListener dl_;
    Upload_ ul_;
    UrlBig_ ub_ = new UrlBig_();
    I_ i_;

    public static final String
            file_ = "file://",
            root_ = file_ + "/android_asset/",
            xiaolian_ = root_ + "xiaolian.png",
            index_ = root_ + "index.html",
            replace_zs_ = "replace.zs",
            redir_zs_ = "redir.zs";

    public static abstract class OnPageFinished {
        public abstract void onPageFinished(WebView view, String url);
    }
    OnPageFinished opf_;

    public Web_(Switch_ s, Zs_ zs, FileOp_ fo, Tag_ tag, I_ i, DownloadListener dl, Upload_ ul, OnPageFinished opf) {
        switch_ = s;
        fo_ = fo;
        tag_ = tag;
        zs_ = zs;
        dl_ = dl;
        ul_ = ul;
        opf_ = opf;
        i_ = i;
    }

    String url__(String url, Tag_.Ctrl_ ctrl) {
        if(url == null)
            return xiaolian_;
        return ub_.parse__(url, ctrl != null && ctrl.is_reload_, zs_, tag_);
    }

    public void loadurl__(String url, WebView wv) {
        ub_.reset__();
        System.out.println("loadurl__" + url);
        wv.loadUrl(url__(url, null));
        tag_.ctrl__(wv, true).ub_ = ub_;
        init__(wv);
    }
    public void loadurl__(String url) {
        loadurl__(url, switch_.wv__());
    }

    WebResourceResponse wrr__(String[] a) {
        if(a != null) {
            switch (a.length) {
                case 3:
                    return new WebResourceResponse(a[0], a[1], fo_.open__(a[2]));
                case 1:
                    if(a[0].equals("x"))
                        return new WebResourceResponse(null, null, null);
                    return new WebResourceResponse("text/html", "utf-8",
                            new ByteArrayInputStream(a[0].getBytes()));
            }
        }
        return null;
    }
    WebResourceResponse wrr__(WebView view, String url4) {
        Tag_.Ctrl_ ctrl = tag_.ctrl__(view, true);
        ctrl.ub_ = ub_;
        String url = url__(url4, ctrl);
        //System.out.println("4 " + url4);
        //System.out.println("  " + url);
        boolean yes = url.equals(url4);
        if(ub_.adblock__(url))
            return new WebResourceResponse(null, null, null);
        try {
            String[] a = null;
            a = Wzs_.file__(url4, zs_, 0, ctrl);
            if(a == null) {
                synchronized (view) {
                    a = zs_.i__(tag_.file_.get(replace_zs_), ctrl, null, 0, url);
                    view.wait(0, 1);
                    //System.out.println(Util_.s__(a));
                }
                if(a == null || a.length == 0)
                    a = Wzs_.fx__(url4, zs_, 0, ctrl);
            }
            {
                WebResourceResponse wrr = wrr__(a);
                if(wrr != null)
                    return wrr;
            }
            if(a != null && a.length == 2) {
                url = a[0];
                yes = false;
            }
            Url_.Ex_ ex = new Url_.Ex_(url);
            if(ex.mime != null || ex.enc != null) {
                a = zs_.i__(tag_.file_.get(redir_zs_), ctrl, null, 0, url, ex.mime, ex.enc);
                WebResourceResponse wrr = wrr__(a);
                if(wrr != null)
                    return wrr;
            }
            if(!yes) {
                return new WebResourceResponse(ex.mime, ex.enc, ex.c.getInputStream());
            }
        } catch (Exception e) {
            i_.sendMessage__(e);
        }
        return null;
    }

    void init__(WebView wv) {
        {
            WebSettings ws = wv.getSettings();
            ws.setJavaScriptEnabled(true);
            ws.setJavaScriptCanOpenWindowsAutomatically(true);
            ws.setPluginState(WebSettings.PluginState.ON);
            ws.setAllowFileAccess(true);

            ws.setSupportZoom(true);
            //两个手指触摸缩放
            ws.setBuiltInZoomControls(true);
            ws.setDisplayZoomControls(false);

            //多窗口
            ws.setSupportMultipleWindows(true);

            //ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        //android7 webview 跳转页面空白
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WebSettings ws = wv.getSettings();
            //ws.setUseWideViewPort(true);
            //ws.setLoadWithOverviewMode(true);
            //ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            //wv.setBackgroundColor(android.graphics.Color.parseColor("#ffffff"));
            //wv.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        }*/

        //wv.setDataDirectorysuffix();

        wv.addJavascriptInterface(zs_, "z$");
        wv.addJavascriptInterface(new Zs2_() {
            @Override
            int wvid__() {return wv.hashCode();}
        }, "z$2");
        //否则会调用系统默认的webview来加载数据
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(Wzs_.is_zhongjian__(url)) {
                    return false;
                }
                switch (Url_.type__(url)) {
                    case tp:
                        view.loadUrl(url);
                        return true;
                    case p:
                        try {
                            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        } catch (Exception e) {
                            i_.sendMessage__(e);
                        }
                        return true;
                }
                if(Build.VERSION.SDK_INT < 21) {
                    if (wrr__(view, url) != null)
                        return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
            /*@Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    view.loadUrl(request.getUrl().toString());
                else
                    view.loadUrl(request.toString());
                return true;
            }*/

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                WebResourceResponse wrr = wrr__(view,
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
                        request.getUrl().toString() : request.toString());
                if(wrr != null)
                    return wrr;
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(ub_.to2_) {
	                ub_.to2_ = false;
                    int i = (int)(ub_.sca_ * 100);
                    if(i != 100) {
                        System.out.println("sca_" + i);
                        view.setInitialScale(i);
                    }
                }
                if(ub_.to_) {
	                ub_.to_ = false;
                    view.scrollTo(ub_.x_, ub_.y_);
                }
                opf_.onPageFinished(view, url);

                super.onPageFinished(view, url);
            }

	        @Override
	        public void onScaleChanged(WebView view, float oldScale, float newScale) {
		        //System.out.println(ub_.id_ + " " + oldScale + "/" + newScale);
		        ub_.sca_ = newScale;
		        super.onScaleChanged(view, oldScale, newScale);
	        }

	        @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);

                Tag_.Ctrl_ ctrl = tag_.ctrl__(view);
                if(ctrl != null && ctrl.clear_history_) {
                    ctrl.clear_history_ = false;
                    view.clearHistory();
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                switch(error.getPrimaryError()) {
                    case SslError.SSL_DATE_INVALID:
                    case SslError.SSL_EXPIRED:
                    case SslError.SSL_INVALID:
                    case SslError.SSL_UNTRUSTED:
                        handler.proceed();
                        break;
                    default:
                        handler.cancel();
                        break;
                }
            }
        });
        wv.setWebChromeClient(new WebChromeClient() {
            //上传文件的选择框
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String capture) {
                ul_.select__(uploadFile, acceptType, capture);
            }
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType) {
                openFileChooser(uploadFile, acceptType, null);
            }
            public void openFileChooser(ValueCallback<Uri> uploadFile) {
                openFileChooser(uploadFile, null);
            }
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                return ul_.select__(webView, filePathCallback, fileChooserParams);
            }

            //播放HTML5中的video
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
            }

            //多窗口
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog,
                                          boolean isUserGesture, Message resultMsg) {
                WebView wv = new__(view.getContext());

                WebView.WebViewTransport wvt = (WebView.WebViewTransport) resultMsg.obj;
                wvt.setWebView(wv);
                resultMsg.sendToTarget();
                return true;
            }
            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
                switch_.delete__(window);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                switch_.show__(view);
                if(message.endsWith(" 哈")) {
                    result.confirm();
                    return true;
                }
                return super.onJsAlert(view, url, message, result);
            }
        });
        wv.setDownloadListener(dl_);
        wv.setOnLongClickListener(new LongClick_(zs_));
    }

    static void load__(WebView wv, String html) {
        Switch_.load__(wv, html, null, root_);
    }
    public void load__(String html) {load__(switch_.wv__(), html);}
    public WebView new__(String html, Context c) {
        return switch_.new__(html, null, root_, c);
    }
    WebView new__(Context c, String url) {
        Web_ w = new Web_(switch_, zs_, fo_, tag_, i_, dl_, ul_, opf_);
        w.ub_.base__(ub_);
        String url2 = null;
        if(url != null) {
            url2 = w.ub_.parse__(url, false, zs_, tag_);
        }
        WebView wv = null;
        if(w.ub_.id_ != 0)
            for(WebView wv2 : switch_.opener_.keySet()) {
                Tag_.Ctrl_ c2 = tag_.ctrl__(wv2);
                if(c2 != null) {
                    if (c2.ub_.id_ == w.ub_.id_) {
                        wv = wv2;
                        switch_.show__(wv);
                        break;
                    }
                }
            }
        if(wv == null)
            wv = switch_.new__(c);
        w.init__(wv);
        if(url2 != null) {
            wv.loadUrl(url2);
        }
        return wv;
    }
    WebView new__(Context c) {
        return new__(c, null);
    }
    public void loadurl_new__(String url) {
        new__(i_.context__(), url);
        //loadurl__(url, wv);
    }
}
