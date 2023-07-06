package org.zhscript.clas;

import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zzzzzzzzzzz on 17-1-4.
 */

public abstract class Upload_ {
    ValueCallback<Uri> file_;
    ValueCallback<Uri[]> files_;

    public boolean result__(int resultCode, Intent data) {
        boolean ret = false;
        Uri u = null;
        if(resultCode == RESULT_OK) {
            if(data != null)
                u = data.getData();
            ret = true;
        }
        if(file_ != null) {
            file_.onReceiveValue(u);
        }
        if(files_ != null) {
            files_.onReceiveValue(u != null ? new Uri[] {u} : null);
        }
        file_ = null;
        files_ = null;
        return ret;
    }

    public void select__(ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        file_ = uploadFile;
        select__(acceptType, capture);
    }

    public boolean select__(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        files_ = filePathCallback;
        String acceptType = null;
        String[] s = fileChooserParams.getAcceptTypes();
        if(s.length > 0)
            acceptType = s[0];
        select__(acceptType, null);
        return true;
    }

    void select__(String acceptType, String capture) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType(acceptType != null && !acceptType.isEmpty() ? acceptType : "*/*");
        select__(Intent.createChooser(i, capture != null ? capture : "选择文件"));
    }

    public abstract void select__(Intent i);
}
