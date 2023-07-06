package org.zhscript.clas;

import android.webkit.WebView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created by zzzzzzzzzzz on 16-12-11.
 */

public class UrlBig_ {
    public static final String flag_head_ = "##";

    boolean adblock_;
    public boolean adblock__() {return adblock_;}
    public static final String adblock_flag_ = flag_head_ + "adblock";

    final String
            url_flag_1_ = flag_head_ + "..",
            url_flag_2_ = "..";
    String[] adblock_urls_;
    public String adblock_history_ = "";
    public String host_;

    boolean to_;
    int x_, y_;
    final String
            xy_flag_1_ = flag_head_ + "p",
            xy_flag_2_ = "o",
            xy_flag_3_ = "s";

    boolean to2_;
    float sca_ = 1;
    final String
            sca_flag_1_ = flag_head_ + "s",
            sca_flag_2_ = "c";

    public long id_;
    final String
            id_flag_1_ = flag_head_ + "i",
            id_flag_2_ = "d";
    public String id2__() {return id_flag_1_ + id_ + id_flag_2_;}

    boolean not_ud_;
    public boolean not_ud__() {return not_ud_;}
    final String not_ud_flag_ = flag_head_ + "notud";

    String file_;
    public String file__() {return file_;}
    final String
            file_flag_1_ = flag_head_ + "f",
            file_flag_2_ = "..";

    public String url2__(WebView wv) {
        int x = wv.getScrollX(), y = wv.getScrollY();
        String ret = "";
        if(adblock_)
            ret += adblock_flag_ + url_flag_1_ + adblock_url__(",") + url_flag_2_;
        ret += xy_flag_1_ + x + xy_flag_2_ + y + xy_flag_3_ +
                sca_flag_1_ + sca_ + sca_flag_2_ +
                id2__();
        if(not_ud_)
            ret += not_ud_flag_;
        if(file_ != null)
            ret += file_flag_1_ + "‘参数0’"/*file_*/ + file_flag_2_;
        return ret;
    }

    void base__(UrlBig_ o) {
        adblock_ = o.adblock_;
        adblock_urls_ = o.adblock_urls_;
        adblocks_ = o.adblocks_;
        id_ = o.id_;
        not_ud_ = o.not_ud_;
        file_ = o.file_;
    }

    void reset__() {
        adblock_ = false;
        to_ = false;
        to2_ = false;
        id_ = 0;
    }

    public String parse__(String url, boolean is_reload, Zs_ zs, Tag_ tag) {
        boolean adblock = false;
        String adblock_url = null;
        for (;;) {
            {
                int i = url.lastIndexOf(adblock_flag_);
                if (i > 0) {
                    adblock = adblock_ = true;
                    url = url.substring(0, i) + url.substring(i + adblock_flag_.length());
                    continue;
                }
            }
            {
                int i = url.lastIndexOf(url_flag_1_);
                if (i > 0) {
                    int i4 = i + url_flag_1_.length();
                    int i2 = url.indexOf(url_flag_2_, i4);
                    if (i2 > 0) {
                        adblock_url = url.substring(i4, i2);
                        System.out.println("adblock_url_" + adblock_url);
                        url = url.substring(0, i) + url.substring(i2 + url_flag_2_.length());
                        continue;
                    }
                }
            }
            {
                int i = url.lastIndexOf(xy_flag_1_);
                if (i > 0) {
                    int i4 = i + xy_flag_1_.length();
                    int i2 = url.indexOf(xy_flag_2_, i4);
                    if (i2 > 0) {
                        int i5 = i2 + xy_flag_2_.length();
                        int i3 = url.indexOf(xy_flag_3_, i5);
                        if (i3 > 0) {
                            if(!is_reload) {
                                String x = url.substring(i4, i2), y = url.substring(i5, i3);
                                x_ = Integer.parseInt(x);
                                y_ = Integer.parseInt(y);
                                System.out.println("xy_" + x_ + "," + y_);
                                to_ = true;
                            }
                            url = url.substring(0, i) + url.substring(i3 + xy_flag_3_.length());
                            continue;
                        }
                    }
                }
            }
            {
                int i = url.lastIndexOf(sca_flag_1_);
                if (i > 0) {
                    int i4 = i + sca_flag_1_.length();
                    int i2 = url.indexOf(sca_flag_2_, i4);
                    if (i2 > 0) {
                        if(!is_reload) {
                            String sca = url.substring(i4, i2);
                            sca_ = Float.valueOf(sca);
                            System.out.println("sca_" + sca);
                            to2_ = true;
                        }
                        url = url.substring(0, i) + url.substring(i2 + sca_flag_2_.length());
                        continue;
                    }
                }
            }
            {
                int i = url.lastIndexOf(id_flag_1_);
                if (i > 0) {
                    int i4 = i + id_flag_1_.length();
                    int i2 = url.indexOf(id_flag_2_, i4);
                    if (i2 > 0) {
                        String id = url.substring(i4, i2);
                        id_ = Long.parseLong(id);
                        System.out.println("id_" + id_);
                        url = url.substring(0, i) + url.substring(i2 + xy_flag_2_.length());
                        continue;
                    }
                }
            }
            {
                int i = url.lastIndexOf(not_ud_flag_);
                if (i > 0) {
                    not_ud_ = true;
                    url = url.substring(0, i) + url.substring(i + not_ud_flag_.length());
                    continue;
                }
            }
            {
                int i = url.lastIndexOf(file_flag_1_);
                if (i > 0) {
                    int i4 = i + file_flag_1_.length();
                    int i2 = url.indexOf(file_flag_2_, i4);
                    if (i2 > 0) {
                        file_ = url.substring(i4, i2);
                        System.out.println("file_" + file_);
                        url = url.substring(0, i) + url.substring(i2 + file_flag_2_.length());
                        continue;
                    }
                }
            }
            break;
        }
        if (adblock && adblock_url == null) {
            try {
                URL url2 = new URL(url);
                adblock_url = url2.getHost() + "*.html$";
                System.out.println("adblock_url_" + adblock_url);
            } catch (MalformedURLException e) {
                adblock_ = false;
            }
        }
        if(!is_reload) {
            if (adblock && adblock_url != null) {
                try {
                    host_ = new URL(url).getHost();
                } catch (MalformedURLException e) {}
                mk_adblock_url__(adblock_url, zs, tag);
            }
        }
        return url;
    }

    class Adblock_ {
        String s_;
	    Pattern p_;
        Adblock_(String s) {
            s_ = s;
            String s2 = Regex_.s__(s, true);
            System.out.println("_" + s + "_" + s2);
	        p_ = Pattern.compile(s2);
        }
        boolean fangguo__(String url) {
            //return url.contains(s_);
	        return p_.matcher(url).matches();
        }
    }
    Adblock_[] adblocks_;

    public static final String adblock_u_zs_ = "adblock-u.zs";
    public void mk_adblock_url__(String adblock_url, Zs_ zs, Tag_ tag) {
        System.out.println("adblock_urls");
        adblock_urls_ = adblock_url.split(",|\n");
        String[] a = zs.i__(tag.file_.get(adblock_u_zs_), host_);
        adblocks_ = new Adblock_[adblock_urls_.length + a.length];
        int i = 0;
        for (; i < adblock_urls_.length; i++) {
            adblocks_[i] = new Adblock_(adblock_urls_[i]);
        }
        for(int i2 = 0; i2 < a.length; i2++) {
            adblocks_[i + i2] = new Adblock_(a[i2]);
        }
        adblock_history_ = "";
    }

    boolean adblock__(String url) {
        if(adblock_) {
            if(Wzs_.is_zhongjian__(url))
                return false;
            for (Adblock_ i : adblocks_) {
                if (i.fangguo__(url)) {
                    //System.out.println(i.s_ + "\t" + url);
                    return false;
                }
            }
            String url2 = url + "\n";
            int i = adblock_history_.indexOf(url2);
            if(i >= 0) {
                if(i == 0 || adblock_history_.charAt(i - 1) != ' ')
                    adblock_history_ = adblock_history_.substring(0, i) + "(2) " + adblock_history_.substring(i);
                else {
                    int i1 = adblock_history_.lastIndexOf('(', i) + 1;
                    String s = adblock_history_.substring(i1, i -= 2);
                    int i2 = Integer.valueOf(s) + 1;
                    adblock_history_ = adblock_history_.substring(0, i1) + i2 + adblock_history_.substring(i);
                }
            } else
                adblock_history_ = url2 + adblock_history_;
            System.out.println("阻止 " + url);
            return true;
        }
        return false;
    }

    public String adblock_url__(String sp) {
        String adblock_url = "";
        if(adblock_urls_ != null)
            for (String s : adblock_urls_) {
                if(!adblock_url.isEmpty())
                    adblock_url += sp;
                adblock_url += s;
            }
        return adblock_url;
    }
}
