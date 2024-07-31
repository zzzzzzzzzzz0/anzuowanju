package name.zzzzzzzzzzz.anzuowanju.clas;

import android.webkit.WebView;

import java.io.IOException;

import org.zhscript.clas.FileOp_;
import org.zhscript.clas.Msgbox_;
import org.zhscript.clas.Switch_;
import org.zhscript.clas.Tag_;
import org.zhscript.clas.UrlBig_;
import org.zhscript.clas.Wzs_;
import org.zhscript.clas.Zs_;

/**
 * Created by zzzzzzzzzzz on 17-1-6.
 */

public class Cang_ {
    Switch_ switch_;
    Tag_ tag_;
    FileOp_ fo_;

    public Cang_(Switch_ s, Tag_ tag, FileOp_ fo) {
        switch_ = s;
        tag_ = tag;
        fo_ = fo;
    }

    public static final int max_ = -1, cur_ = -2;
    int i__(int i) {
        switch(i) {
            case max_:
                return switch_.len__() - 1;
            case cur_:
                return switch_.i__();
        }
        return i;
    }

    public static final String cang_zs_ = "cang.zs";
    public boolean add_or_ud__(int from, int to, boolean add, boolean shuang, boolean no_id, Msgbox_ mb, Zs_ zs) throws IOException {
        from = i__(from);
        to = i__(to);
        int s5 = 0;
        for(int i4 = from; i4 <= to; i4++) {
            WebView wv = switch_.wv__(i4);
            Tag_.Ctrl_ ctrl = tag_.ctrl__(wv);
            if(ctrl == null) {
                System.out.println("add_or_ud__ ctrl=null " + i4);
                continue;
            }
            String err = save__(ctrl, wv, add, shuang, no_id, zs);
            if(!err.isEmpty()) {
                mb.toast__(err);
            }
            s5++;
        }
        return s5 > 0;
    }

    public String save__(Tag_.Ctrl_ ctrl, WebView wv, boolean add, boolean shuang, boolean no_id, Zs_ zs) throws IOException {
        UrlBig_ ub = ctrl.ub_;
        if(ub == null || (ub.not_ud__() && !add))
            return "";
        String url = wv.getUrl();
        if (ub.id_ == 0) {
            if(!ub.adblock__() && !add)
                return "";
            ub.id_ = wv.hashCode(); //System.currentTimeMillis()
        }
        String id2 = ub.id2__(), title = wv.getTitle();
        System.out.println("Cang_save__" + ub.id_ + " " + title);
        boolean bad = false;
        switch (title) {
            case "301 Moved Permanently":
            case "502 Bad Gateway":
            case "网页无法打开":
            case "":
                bad = true;
                break;
            default: {
                if(Wzs_.is_zhongjian__(url) || url.equals(Switch_.blank_) || title.startsWith("http")) {
                    bad = true;
                    break;
                }
                int i = title.indexOf(UrlBig_.flag_head_);
                if(i > 0) {
                    title = title.substring(0, i);
                    if(url.startsWith("http")) {
                        //有可能是网页加载失败
                        bad = true;
                        break;
                    }
                }
                if(ub.host_ != null) {
                    i = url.indexOf(UrlBig_.flag_head_);
                    String url1 = i > 0 ? url.substring(0, i) : url;
                    if(!(url1).contains(ub.host_)) {
                            /*bad = true;
                            break;*/
                        System.out.println("!ub.host_" + ub.host_);
                    }
                }
                break;
            }
        }
        if(bad) {
            String err = "未收藏 " + title;
            System.out.println(err + " " + url);
            return err;
        }
        String code2 = flag_ + (no_id ? url : ub.parse__(url, true, zs, tag_) + ub.url2__(wv));
        if(ub.file__() == null) {
            title = title
                    .replace('“', '"')
                    .replace('”', '"')
                    .replace("‘", "'")
                    .replace("’", "'")
                    .replace("。", ". ");
            code2 += "”、“" + title;
            if(ub.adblock__())
                code2 += " (ad";/*<img src=adblock.png>*/
        }
        code2 += "”";
        String filename = ub.file__() != null ? ub.file__() : cang_zs_;
        String s = fo_.read__(filename);
        if(ub.file__() == null) {
            if(!no_id) {
                for (; ; ) {
                    int i = s.indexOf(id2);
                    if (i >= 0) {
                        String[] a = get__(i, id2.length(), s, ub.adblock__());
                        if(a != null) {
                            s = a[0] + code2 + a[1];
                            break;
                        }
                    }
                    i = s.indexOf(url);
                    if (i >= 0) {
                        String[] a = get__(i, url.length(), s, ub.adblock__());
                        if(a != null) {
                            s = a[0] + code2 + a[1];
                            break;
                        }
                    }
                    s += "\n加" + (shuang ? "爽" : "") + code2 + "。";
                    break;
                }
            }
        } else {
            int i = s.indexOf("开" + flag_);
            if(i < 0)
                i = s.indexOf("开爽" + flag_);
            int i3 = s.indexOf("。", i);
            s = s.substring(0, i) + "开" + code2 + s.substring(i3);
        }
        //System.out.println(s);
        fo_.write__(s, filename);
        return "";
    }
    final String flag_ = "链“";
    String[] get__(int i, int skip, String s, boolean adblock) {
        int i2 = s.lastIndexOf(flag_, i);
        if (i2 > 0) {
            int i3 = s.indexOf("。", i + skip);
            if (i3 > 0) {
                String s1 = s.substring(0, i2);
                if(adblock && s1.endsWith("爽"))
                    s1 = s1.substring(0, s1.length() - 1);
                return new String[] {s1, s.substring(i3)};
            }
        }
        return null;
    }
}
