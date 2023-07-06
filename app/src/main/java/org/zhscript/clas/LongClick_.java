package org.zhscript.clas;

import android.view.View;
import android.webkit.WebView;

/**
 * Created by zzzzzzzzzzz on 17-11-6.
 */

public class LongClick_ implements View.OnLongClickListener {
    @Override
    public boolean onLongClick(View v) {
        WebView wv = (WebView) v;
        WebView.HitTestResult result = wv.getHitTestResult();
        if (null == result)
            return false;
        int type = result.getType();
        switch (type) {
            // 选中的文字
            case WebView.HitTestResult.EDIT_TEXT_TYPE:
                break;
            // 拨号
            case WebView.HitTestResult.PHONE_TYPE:
                break;
            // Email
            case WebView.HitTestResult.EMAIL_TYPE:
                break;
            // 地图
            case WebView.HitTestResult.GEO_TYPE:
                break;
            // 超链接
            case WebView.HitTestResult.SRC_ANCHOR_TYPE:
            // 带有链接的图片
            case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE: {
                String url = result.getExtra();
                String typ = (type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE ? "图片" : "") + "链接";
                int i = url.indexOf(id_);
                if(i >= 0) {
                    i += id_.length();
                    int i2 = url.indexOf("#", i);
                    if(i2 > 0) {
                        String id = url.substring(i, i2);
                        //System.out.println("id "+id);
                        Js_.run__(new String[] {
                            "if(typeof('" + on_ + "')!='undefined')" +
                            on_ + "('" + id + "','" + typ + "','" + url + "');"
                        }, wv, null, 0, null);
                        return true;
                    }
                }
                String[] ret = zs_.i2__("longclick.zs", url, typ);
                //System.out.println(ret.length);
                if(ret.length == 1)
                    return true;
                break;
            }
            // 图片
            case WebView.HitTestResult.IMAGE_TYPE:
                break;
            case WebView.HitTestResult.UNKNOWN_TYPE:
                break;
        }
        return false;
    }

    public LongClick_(Zs_ zs) {
        zs_ = zs;
    }

    Zs_ zs_;
    final String id_ = "#longclicktag", on_ = "onlongclick__";
}
