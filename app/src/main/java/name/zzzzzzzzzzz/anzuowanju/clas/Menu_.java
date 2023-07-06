package name.zzzzzzzzzzz.anzuowanju.clas;

import android.view.MenuItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zzzzzzzzzzz on 16-12-8.
 */

public enum Menu_ {
    exit("结束"),
    export(),
    import1(),
    add_or_ud("收藏或更新收藏"),
    add_shuang("收藏小说"),
    add_no_id("无 id 收藏"),
    edit_url("编辑 url"),
    adblock_url("被拦掉的链接"),
    edit_adblock("编辑拦截"),

    go_back("后退"),
    go_forward("前进"),
    clearhistory(),
    close("关闭"),
    PgUp("上一页"),
    PgDn("下一页"),

    zhuanping("转屏"),

    None();

    String s_;
    List<String> alias_;
    boolean has_;
    static final String sp1_ = "（", sp2_ = "）";

    Menu_() {}
    Menu_(String s) {
        s_ = s;
    }
    Menu_(String[] a) {
        alias_ = Arrays.asList(a);
    }
    String name__() {
        return name().replace("_", " ").replace("1", "");
    }
    String title__() {
        return s_ + sp1_ + name__() + sp2_;
    }

    public static Menu_ get__(String s) {
        for(Menu_ i : values()) {
            if(i.name().equals(s) || i.alias_ != null && i.alias_.contains(s))
                return i;
        }
        return None;
    }

    public static String get2__(String s) {
        if(s.endsWith(sp2_)) {
            int i3 = s.length() - sp2_.length();
            int i2 = s.lastIndexOf(sp1_, i3);
            if(i2 > 0) {
                s = s.substring(i2 + sp1_.length(), i3).replace(" ", "_");
            }
        }
        return s;
    }

    public static void with__(android.view.Menu menu) {
        for(int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            for(Menu_ m : values()) {
                if(mi.getTitle().equals(m.name__())) {
                    mi.setTitle(m.title__());
                    m.has_ = true;
                    break;
                }
            }
        }
        for(Menu_ i : values()) {
            if(i.s_ == null || i.has_)
                continue;
            menu.add(0, /*1000 + i.ordinal()*/i.hashCode(), 0, i.title__());
        }
    }
}
