package name.zzzzzzzzzzz.anzuowanju.clas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.zhscript.*;
import org.zhscript.clas.*;

import name.zzzzzzzzzzz.anzuowanju.Edit_;
import name.zzzzzzzzzzz.anzuowanju.Miio_;

import static org.zhscript.clas.Util_.s__;

/**
 * Created by zzzzzzzzzzz on 17-2-8.
 */

public class All_ {
    public I_ i_ = new I_();

    public Msgbox_ msgbox_ = new Msgbox_();

    public static final int msg_ = 10, n_edit_ = 100, n_hellolight_ = 101, n_upload_ = 105,
        back_press_ = 11, appmenu_o_ = 12, appmenu_x_ = 13;

    public FileOp_ file_op_ = new FileOp_() {
        @Override
        public InputStream open__(String filename, boolean use_save) {
            return open__(filename, use_save, getContext());
        }
        @Override
        public void write__(String s, String filename, boolean append) {
            write__(s, filename, append, getContext());
            and__(filename);
        }
    };
    public Tag_ tag_ = new Tag_();
    public Shell_ shell_ = new Shell_() {
        @Override
        public String[] suidao__(String[] a, long qv_up//, Object resource
        ) {
            if(a.length >= 1) {
                return suidao2__(a[0], qv_up,
                        null
                        //(Resource_)resource
                        , Util_.a2__(a, 1));
            }
            return null;
        }
    };
    public Zs_ zs_ = new Zs_() {
        @Override
        public String[] i__(String src, boolean src_is_file, String src2,
                            Tag_.Ctrl_ ctrl, Object resource, String[] a2, long qv_up, String... a) {
            if(ctrl != null)
                try {
                    Result_ result = new Result_();
                    shell_.xianguan(src_is_file ? file_op_.read__(src) : src, result);
                    boolean first = true;
                    for(String s : result.a_) {
                        if(!ctrl.xianguan_.contains(s)) {
                            ctrl.xianguan_.add(s);
                            if(first) {
                                first = false;
                                System.out.println("xianguan - " + (src_is_file ? src : "src"));
                            }
                            System.out.println("xianguan " + s);
                        }
                    }
                } catch (IOException e) { }
            return shell__(src, src_is_file, src2, qv_up, (Resource_)resource, a2, a);
        }

        @Override
        public String[] i2__(int wvid, String src, String... a) {
            Tag_.Ctrl_ ctrl = null;
            if(wvid != 0)
                for (int i = 0; i < switch_.len__(); i++) {
                    WebView wv = switch_.wv__(i);
                    if(wv.hashCode() == wvid) {
                        ctrl = tag_.ctrl__(wv);
                        break;
                    }
                }
            try {
                return i__(src, false, null, ctrl, null, null, 0, a);
            } catch (Exception_ e) {
                msgbox__(e.getLocalizedMessage());
                return null;
            }
        }
    };
    public ActivityResult_ ar_ = new ActivityResult_(zs_);
    public Switch_ switch_;
    public Web_ web_;
    Cang_ cang_;
    public Menu2_ menu2_ = new Menu2_();
    Sms_ sms_;
    Locat_ locat_ = new Locat_(zs_);
    public Upload_ upload_ = new Upload_() {
        @Override
        public void select__(Intent i) {
            i_.activity__().startActivityForResult(i, n_upload_);
        }
    };
    TTS_ tts_ = new TTS_();

    Context getContext() {return i_.context__();}

    public String[] shell__(String src, boolean src_is_file, String src2, long qv_up, Object resource, String[] a2, String... a) {
        Result_ result = new Result_();
        try {
            shell_.z(src, src_is_file, src2, a2, a, qv_up, //resource,
                    result);
        } catch (Exception2_ e) {
            throw new Exception_(result);
        } catch (Exception e) {
            i_.sendMessage__(e);
        }
        if(result.is_exit_) {
            exit__();
            return null;
        }
        return result.a_;
    }
    public String[] shell__(String src, boolean src_is_file) {
        return shell__(src, src_is_file, null, 0, null, null);
    }
    public String[] shell__(String src) {
        return shell__(src, true);
    }

    enum Dong2_ {
        reload_index, reload, loadurl, page_new, msgbox, loading_show_, loading_hide_, js_, view_, None
    }

    class SndMsg_ {
        Menu_ m_ = Menu_.None;
        Dong2_ d2_ = Dong2_.None;
        String s_;
        boolean b_;
        int i1_ = -1;
        String[] a_;
        SndMsg_() {i_.sendMessage__(this);}
        SndMsg_(Menu_ m) {this(); m_ = m;}
        SndMsg_(Menu_ m, String s, boolean b) {this(); m_ = m; s_ = s; b_ = b;}
        SndMsg_(Dong2_ d2) {this(); d2_ = d2;}
        SndMsg_(Dong2_ d2, String s) {this(); d2_ = d2; s_ = s;}
        SndMsg_(Dong2_ d2, String s, boolean b) {this(); d2_ = d2; s_ = s; b_ = b;}
        SndMsg_(Dong2_ d2, int s) {this(); d2_ = d2; i1_ = s;}
        SndMsg_(Dong2_ d2, String[] s, int i) {this(); d2_ = d2; a_ = s; i1_ = i;}
    }

    public void suidao3__(Object o) {
        try {
            if(o instanceof SndMsg_) {
                SndMsg_ d = (SndMsg_) o;
                switch (d.m_) {
                    case None:
                        switch (d.d2_) {
                            case js_:
                                Js_.run__(d.a_, switch_.wv__(d.i1_), zs_, 0, null);
                                break;
                            case view_:
                                i_.view__(d.s_);
                                break;
                            case reload_index:
                                if (!switch_.has_index__())
                                    break;
                                d.i1_ = 0;
                            case reload: {
                                WebView wv = d.i1_ >= 0 ? switch_.wv__(d.i1_) : wv__();
                                System.out.println("reload " + d.i1_ + " " + wv.getUrl());
                                wv.reload();
                                break;
                            }
                            case loadurl:
                                web_.loadurl__(Url_.url__(d.s_, d.b_));
                                break;
                            case page_new:
                                web_.loadurl_new__(Url_.url__(d.s_, d.b_));
                                break;
                            case msgbox:
                                msgbox_.msgbox__(d.s_);
                                break;
                            case loading_show_:
                                loading_.setText(d.s_);
                                if(loading_.getVisibility() != View.VISIBLE)
                                    loading_.setVisibility(View.VISIBLE);
                                break;
                            case loading_hide_:
                                if(loading_.getVisibility() != View.GONE)
                                    loading_.setVisibility(View.GONE);
                                break;
                        }
                        break;
                    case go_back:
                        if (d.b_) {
                            if (wv__().canGoBack   ()) {wv__().goBack   (); break;}
                        } else {
                            if (wv__().canGoForward()) {wv__().goForward(); break;}
                        }
                        msgbox_.snackbar__("can't " + d.s_);
                        break;
                    case close:
                        if (close__())
                            break;
                    case exit:
                        cang_.add_or_ud__(0, Cang_.max_, false, false, false, msgbox_, zs_);
                        Broadcast_.all_unreg__(getContext());
                        System.exit(0);
                        break;
                    case clearhistory: {
                        Context c = getContext();
                        File cd = c.getCacheDir();
                        FileOp_.for_del__(cd);
                        switch_.clear__();
                        CookieManager.getInstance().removeAllCookie();
                        c.deleteDatabase("webview.db");
                        c.deleteDatabase("webviewCache.db");
                        break;
                    }
                    case import1:
                        tag_file_read__();
                        switch_.wv__(0).reload();
                        if (switch_.i__() != 0)
                            wv__().reload();
                        break;
                }
                return;
            }
            if(o instanceof Exception) {
                Exception e = (Exception) o;
                System.err.println(e);
                msgbox_.toast__(e.getLocalizedMessage());
                return;
            }
            msgbox_.msgbox__(o.toString());
        } catch (IOException e) {
            msgbox_.msgbox__(e.getLocalizedMessage());
        }
    }

    public String[] suidao2__(String src, long qv_up, Resource_ resource, String... a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Consumer<Object> ddddd = (s) -> {
                System.out.println(src + ";" + s__(a, ",") + ";" + s);
            };
            Supplier<Integer> dddd = () -> {
                    ddddd.accept("");
                return 0;
            };
            //dddd.get();
        }
        try {
            Menu_ m = Menu_.get__(src);
            switch (m) {
                case go_back:
                    return go_back__(true, src);
                case go_forward:
                    return go_back__(false, src);
                case PgUp:
                    return pgup__(true);
                case PgDn:
                    return pgup__(false);
                case add_or_ud:
                case add_shuang:
                case add_no_id:
                    if (cang_.add_or_ud__(Cang_.cur_, Cang_.cur_, true,
                            m == Menu_.add_shuang,
                            m == Menu_.add_no_id,
                            msgbox_, zs_)) {
                        reload_after_change__(Cang_.cang_zs_);
                        if(m != Menu_.add_or_ud)
                            close_1__();
                    }
                    return null;
                case edit_url: {
                    String url = wv__().getUrl();
                    //System.out.println("url="+url);
                    i_.edit__(Edit_.url_, new String[]{url}, null);
                    return null; }
                case edit_adblock: {
                    Tag_.Ctrl_ ctrl = tag_.ctrl__(wv__());
                    if (ctrl == null) {
                        msg_no__();
                        return null;
                    }
                    UrlBig_ ub = ctrl.ub_;
                    String text = ub.adblock_url__("\n");
                    if (text.isEmpty())
                        msg_no__();
                    else
                        i_.edit__(UrlBig_.adblock_flag_, new String[]{text}, null);
                    return null;
                }
                case adblock_url: {
                    Tag_.Ctrl_ ctrl = tag_.ctrl__(wv__());
                    if (ctrl == null) {
                        msg_no__();
                        return null;
                    }
                    UrlBig_ ub = ctrl.ub_;
                    if (ub.adblock_history_.isEmpty()) {
                        msg_no__();
                        return null;
                    }
                    web_.new__("<pre>" + ub.adblock_history_ + "</pre>", getContext());
                    return null;
                }
                case zhuanping:
                    if (a.length > 0) {
                        String s = a[0];
                        switch (s) {
                            case "自动2":
                                if (file_op_.open__(Zhuanping_.filename_) != null) {
                                    init__(Zhuanping_.filename_);
                                    return null;
                                } else
                                    s = "自动";
                                break;
                        }
                        Zhuanping_.set__(s, a.length > 1, i_.activity__());
                    } else
                        Dlg_.dlg__(Zhuanping_.item__(), false, i_.activity__(), Zhuanping_.dlg_click_);
                    return null;
                case export:
                    return new String[]{FileOp_.copydir__(getContext().getFilesDir(), i_.export_dir__())};
                case import1: {
                    String[] ret = new String[]{FileOp_.copydir__(i_.export_dir__(), getContext().getFilesDir())};
                    new SndMsg_(m);
                    return ret;
                }
                case clearhistory:
                case close:
                case exit:
                    new SndMsg_(m);
                    return null;
            }
            switch (src) {
                case "js": case "js2": {
                    String[] a2 = a;
                    int i = switch_.i__();
                    while (a2.length > 0) {
                        switch (a2[0]) {
                            case "-上页":
                                i--;
                                a2 = Util_.a2__(a, 1);
                                continue;
                        }
                        break;
                    }
                    switch (src) {
                        case "js2":
                            new SndMsg_(Dong2_.js_, a2, i);
                            return null;
                    }
                    return Js_.run__(a2, switch_.wv__(i), zs_, qv_up, resource);
                }
                case "换码":
                    return Util_.encode__(a);
                case "单引号": {
                    String s = a[0];
                    return new String[]{s.startsWith("'") && s.endsWith("'") ? s : "'" + s + "'"};
                }
                case "多串替换":
                    return new String[]{Util_.replace__(a)};
                case "改后缀":
                    return Util_.set_ext__(a);
                case "参":
                    return Util_.arg__(a);
                case "迭代":
                    return Util_.foreach__(a, zs_, qv_up, resource);
                case "url":
                    return new String[]{Url_.url__(a[0], a.length > 1)};
                case "url分拆":
                    return Url_.parse__(a[0]);
                case "url参分拆": {
                    ArrayList<String> al2 = new ArrayList<>();
                    Wzs_.q2al__(a[0], null, al2);
                    return al2.toArray(new String[0]);
                }
                case "urlencode":
                    return Url_.encode__(a);
                case "urldecode":
                    return Url_.decode__(a);
                case "re":
                    return Regex_.regex__(a, zs_, qv_up, resource);
                case "尾匹配":
                    return Regex_.ends__(a) ? new String[] {"1"} : null;
                case "头匹配":
                    return Regex_.ends__(a) ? new String[] {"1"} : null;
                case "宽":
                    return i_.px2dip__(switch_.width__());
                case "吐泡":
                    msgbox_.toast__(s__(a));
                    return null;
                case "推条":
                    msgbox_.snackbar__(s__(a));
                    return null;
                case "提示":
                    msgbox__(s__(a));
                    return null;
                case "刷通知":
                    Notif_.notif__(a, getContext(), i_.class__());
                    return null;
                case "菜单":
                    return Dlg_.dlg__(a, true, i_.activity__(), new Dlg_.Click() {
                        @Override
                        public void click__(String[] a2, String[] a, Activity c) {
                            shell__(a[1], false, null, qv_up, resource, null, a2);
                        }
                    });
                case "时间":
                    return new String[]{DateTime_.fmt__(a)};
                case "重定向":
                    return Exec_.exec__(a, zs_, qv_up, resource, i_);
                case "遍历目录":
                    return Dir_.for__(a, getContext().getAssets(),
                            getContext().getFilesDir(), zs_, qv_up, resource);
                case "遍历外目录":
                    return Dir_.for__(a, zs_, qv_up, resource);
                case "内存目录":
                    return new String[]{FileOp_.ext_dir__(a.length > 0 ? a[0] : "", i_).getAbsolutePath()};
                case "删除文件":
                    return FileOp_.del__(a, getContext(), (name) -> and__(name));
                case "移动文件":
                    return FileOp_.mv__(a);
                case "有文件":
                    return FileOp_.exist__(a);
                case "文件":
                    return new String[]{file_op_.read__(a[0])};
                case "写文件": {
                    String name = a[0], s = a[1];
                    boolean append = false;
                    if (a.length > 2) {
                        String opt = a[2];
                        switch (opt) {
                            case "追加":
                                append = true;
                                break;
                            default:
                                throw I_.buzhichi__(opt);
                        }
                    }
                    file_op_.write__(s, name, append);
                    return null;
                }
                case "刷新":
                    new SndMsg_(Dong2_.reload);
                    return null;
                case "网页":
                case "新网页": {
                    Dong2_ d2 = src.equals("网页") ? Dong2_.loadurl : Dong2_.page_new;
                    if (switch_.i__() == 0)
                        switch (d2) {
                            case loadurl:
                                if (switch_.has_index__()) {
                                    d2 = Dong2_.page_new;
                                    System.out.println(src + "-" + d2);
                                }
                                break;
                            /*case page_new:
                                if(!switch_.has_index__()) {
                                    d2 = Dong2_.loadurl;
                                    System.out.println(src + "-" + d2);
                                }
                                break;*/
                        }
                    return loadurl__(d2, a);
                }
                case "打开":
                    return open__(a);
                case "他打开":
                    return ta_open__(resource) ? new String[]{"1"} : null;
                case "系统版本号":
                    return new String[]{String.valueOf(Build.VERSION.SDK_INT)};
                case "系统类型":
                    return new String[]{Build.MODEL};
                case "应用版本号":
                    return new String[]{String.valueOf(getContext().getPackageManager()
                            .getPackageInfo(i_.pkg_name__(), 0).versionCode)};
                case "start":
                    return start__(a, qv_up, resource);
                case "下载":
                    return new Download2_().z__(a[0], getContext());
                case "sms":
                    return sms_.send__(a, getContext());
                case "定位":
                    locat_.z__(a, i_);
                    return null;
                case "定时":
                    return Service_.service__(src, a, getContext());
                case "日历":
                    return Calendar_.Companion.z__(a, i_);
                case "遍历应用":
                    return Package_.for__(a, zs_, qv_up, resource, getContext());
                case "查询":
                    return Query_.query__(a, zs_, qv_up, resource, getContext());
                case "tts":
                    return tts_.shuo__(a, ar_, i_);
                case "server":
                    switch (a[0]) {
                        case "ip":
                            return Server_.ip__(a.length > 1 ? Integer.valueOf(a[1]) : 4, getContext());
                        case "start":
                            return Service_.service__(src, a, getContext());
                        default:
                            throw I_.buzhichi__(a[0]);
                    }
                case "全屏":
                    new SndMsg_(Dong2_.view_, "fs");
                    return null;
                case "闪屏":
                    switch (a[0]) {
                        case "去除":
                            loading_hide__();
                            break;
                        default:
                            loading_show__(a[0]);
                            break;
                    }
                    return null;
                case "小部件":
                    return AppWidget_text_.i__(a, getContext());
                case "编辑": {
                    String[][] ret2;
                    String[] a2;
                    ret2 = Util_.arg2__(a, "-名限");
                    a2 = ret2[0];
                    if (a2.length > 0)
                        if (!Regex_.ms__(ret2[1][0], a2[0]))
                            throw I_.buzhichi__("名不合乎");
                    ret2 = Util_.arg2__(ret2[1], "-类型");
                    a2 = ret2[0];
                    String typ = a2.length > 0 ? a2[0] : null;
                    ret2 = Util_.arg2__(ret2[1], "-注意");
                    a2 = ret2[0];
                    String zhuyi = a2.length > 0 ? a2[0] : null;
                    ret2 = Util_.arg2__(ret2[1], "-后代码");
                    a2 = ret2[0];
                    String hou = a2.length > 0 ? a2[0] : null;
                    ret2 = Util_.arg2__(ret2[1], "-改后");
                    a2 = ret2[0];
                    String gaihou = a2.length > 0 ? a2[0] : null;
                    Bundle b = new Bundle();
                    b.putString("zhuyi", zhuyi);
                    b.putString("hou", hou);
                    b.putString("gaihou", gaihou);
                    i_.edit__(typ, ret2[1], b);
                    return null;
                }
                case "分任务":
                    new Thread(() -> {
                        zs_.i__(a[0], Util_.a2__(a, 1));
                    }).start();
                    return null;
                case "显桌面":
                    showdesktop__();
                    return null;
                case "导出目录":
                    return new String[]{i_.export_dir__().getAbsolutePath()};
                case "快捷方式":
                    return Shortcut_.shortcut__(a, i_);
                case "毕后": {
                    Tag_.Ctrl_ c = tag_.ctrl__(wv__());
                    c.finished_.add(new Tag_.Finished_(a[0], Util_.a2__(a, 1)));
                    return null;
                }
                case "退按时":
                    on_back_press_ = a[0];
                    return null;
                case "主菜单":
                    i_.send_msg2__(a[0].equals("打开") ? appmenu_o_ : appmenu_x_);
                    return null;
                case "hellolight":
                    i_.start__(n_hellolight_, null);
                    return null;
                case "mi插座":
                    new Miio_().chazuo__(a[0], a[1], Integer.valueOf(a[2]));
                    return null;
                case "test":
                    return Test_.test__(a, i_);
                case "test2":
                    return Test2_.Companion.z__(a, i_);
                case "test3": {
                    /*String url = Wzs_.wzs_ + Web_.root_ + "hella.zsa";
                    String[] a2 = new String[]{url};
                    return loadurl__(Dong2_.page_new, a2);
                    //return open__(a2);*/
                    return start__("“<a href=\"javascript:z$('弄个go_back')\">关闭</a><br>”"
                            + "<pre>" + "ub.adblock_history_" + "</pre>", qv_up, resource);
                }
                case "test4": {
                    return Shortcut_.shortcut__(new String[] {"edit", "弄个网页、edit.zsa",
                        "-图标", "罗盘"}, i_);
                }
            }
            throw I_.buzhichi__();
        } catch (BuLiao_ e) {
        /*} catch (Exception_ e) {
            switch (src) {
                case "re": throw e;
            }
            msgbox__(e, src, a);*/
        } catch (SecurityException e) {
            if (i_.permi_.z__(e, i_))
                return null;
            msgbox__(e, src, a);
        } catch (Exception e) {
            msgbox__(e, src, a);
        }
        return null;
    }

    public String on_back_press_;

    void msgbox__(Exception e, String src, String[] a) {
        String s = src + "\n" + s__(a) + "\n\n" + e.getClass().getSimpleName() + "\n" + e.getLocalizedMessage();
        System.out.println(s);
        msgbox__(s);
    }
    String[] start__(String code, long qv_up, Resource_ resource) {
        return start__(new String[] {"view", Wzs_.zs_ + code}, qv_up, resource);
    }
    //String[] a = new String[] {"call", "tel:110"};
    String[] start__(String[] a, long qv_up, Resource_ resource) {
        return Start_.start__(a, ar_, i_, zs_, qv_up, resource);
    }
    public void init__(String filename) {
        try {
            String tag = filename.substring(Url_.init_.length(), filename.indexOf("."));
            String src = suidao_ + tag + "、" + file_op_.read__(filename);
            shell__(src, false);
        } catch (Exception e) {
            i_.sendMessage__(e);
        }

    }
    public void exit__() {new SndMsg_(Menu_.exit);}
    public boolean close__() throws IOException {
        cang_.add_or_ud__(Cang_.cur_, Cang_.cur_, false, false, false, msgbox_, zs_);
        return close_1__();
    }
    boolean close_1__() throws IOException {
        boolean b = switch_.delete__(wv__());
        if(switch_.len__() == 1) {
            String url = switch_.wv0__().getUrl();
            if(url == null || url.equals(Switch_.blank_))
                exit__();
        }
        return b;
    }
    boolean ta_open__(Resource_ resource) {
        if(Resource_.oni__(resource)) return false;
        if(switch_.has_index__()) return false;
        return true;
    }
    public void loading_show__(String s) {
        new SndMsg_(Dong2_.loading_show_, s);
    }
    public void loading_hide__() {
        new SndMsg_(Dong2_.loading_hide_);
    }
    String[] open__(String[] a) {
        String url = a[0];
        b2: {
            Url_.Type_ t = Url_.type__(url);
            b1: {
                switch (t) {
                    case tp:case f:
                        if (a.length > 1) break b1;
                        //suidao2__("菜单", qv_up, resource, "", suidao_ + "新网页、‘参数1’", "-标题", "是否打开", url);
                        loadurl__(Dong2_.page_new, a);
                        break;
                    case p:
                        if (a.length > 1) break b1;
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        break;
                    default:
                        return null;
                }
                break b2;
            }
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(t == Url_.Type_.f ? i_.fp__().uri__(url, i, getContext()) : Uri.parse(url), a[1]);
            getContext().startActivity(i);
        }
        return new String[] {"1"};
    }
    String[] loadurl__(Dong2_ d2, String[] a) {
        String[] a2 = a;
        boolean www = false;
        for1:
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case "-www":
                    www = true;
                    a2 = Util_.a2__(a2, i + 1);
                    break;
                default:
                    break for1;
            }
        }
        new SndMsg_(d2, Wzs_.url__(a2), www);
        return null;
    }
    void and__(String filename) {
        if (tag_.file_.containsKey(filename))
            tag_file_read__(filename);
        reload_after_change__(filename);
    }
    void reload_after_change__(String filename) {
        for (int i = 0; i < switch_.len__(); i++) {
            Tag_.Ctrl_ ctrl = tag_.ctrl__(switch_.wv__(i));
            if(ctrl != null && ctrl.xianguan_.contains(filename)) {
                new SndMsg_(Dong2_.reload, i);
            }
        }
    }
    void showdesktop__() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(i);
    }
    String[] pgup__(boolean b) {
        if(b)
            switch_.back__();
        else
            switch_.next__();
        msgbox_.toast__((switch_.i__() + 1) + " / " + switch_.len__());
        return new String[0];
    }
    String[] go_back__(boolean b, String s) {
        if(b)
            i_.send_msg2__(back_press_);
        else
            new SndMsg_(Menu_.go_back, s, b);
        return null;
    }
    void msg_no__() {
        msgbox_.snackbar__("无");
    }
    void msgbox__(String s) {
        //msgbox_.msgbox__(s);
        new SndMsg_(Dong2_.msgbox, s);
    }
    WebView wv__() {return switch_.wv__();}

    public void tag_file_read__(String name, Context c) {
        InputStream is = FileOp_.open__(name, true, c);
        if(is == null) {
            System.err.println(name);
            return;
        }
        try {
            tag_.file_.put(name, FileOp_.read__(is));
        } catch (IOException e) {
            i_.sendMessage__(e);
            return;
        }
        System.out.println("tag_file_read__" + name);
        switch (name) {
            case Sms_.sms_zs_:
                if(sms_ != null)
                    sms_.br2__(c);
                break;
        }
    }
    public void tag_file_read__(String name) {
        tag_file_read__(name, getContext());
    }
    public void tag_file_read__() {
        tag_file_read__(Web_.replace_zs_);
        tag_file_read__(Web_.redir_zs_);
        tag_file_read__(Sms_.sms_zs_);
        tag_file_read__(AppWidget_text_.zs_);
        tag_file_read__(UrlBig_.adblock_u_zs_);
    }

    public static final int menugroup_17_ = 17;
    TextView loading_;

    public final String suidao_ = "弄个";
    private void init__(Context c) {
        if(c != null) {
            i_.context__(c);
            shell_.init(c.getAssets(), c.getFilesDir().getAbsolutePath(), suidao_);
        }
        shell__("def.zs");
    }
    public void init__(View wv, View vf, View tv, Menu menu, boolean has_index) {
        loading_ = (TextView)tv;

        tag_file_read__();
        switch_ = new Switch_(wv, vf, has_index) {
            @Override
            protected void on_delete__(WebView wv) {
                tag_.delete__(wv);
                menu2_.delete__(wv);
            }

            @Override
            protected void on_new__(WebView wv) {
                menu2_.new__(wv);
            }
        };
        if(has_index) {
            String[] a = shell__("menu17");
            for(int i1 = 0; i1 < a.length; i1++) {
                if(a[i1].isEmpty()) continue;
                menu.add(menugroup_17_, i1 + 1, 0, a[i1]);
            }
        }
        menu2_.with__(menu, switch_);
        web_ = new Web_(switch_, zs_, file_op_, tag_, i_, new Download_(i_, zs_) {
            @Override
            public void start__(Intent i) {
                try {
                    getContext().startActivity(i);
                } catch (Exception e) {
                    msgbox__(e.getLocalizedMessage());
                }
            }
        }, upload_, new Web_.OnPageFinished() {
            @Override
            public void onPageFinished(WebView view, String url) {
                menu2_.title__(view);
                Tag_.Ctrl_ c = tag_.ctrl__(view);
                if (c != null) {
                    for (Tag_.Finished_ i : c.finished_) {
                        suidao2__(i.src_, 0, null, i.a_);
                    }
                    c.finished_.clear();

                    try {
                        cang_.save__(c, view, false, false, false, zs_);
                    } catch (Exception e) {}
                }
            }
        });
        cang_ = new Cang_(switch_, tag_, file_op_);
        sms_ = new Sms_(shell_, zs_, i_, tag_, getContext());

        loading_hide__();
    }
    public void delete__() {
        tts_.delete__();
    }

    private All_() {}
    private static All_ this_;
    public static All_ this__(Context c) {
        if(this_ == null) {
            this_ = new All_();
            this_.init__(c);
        }
        return this_;
    }
}
