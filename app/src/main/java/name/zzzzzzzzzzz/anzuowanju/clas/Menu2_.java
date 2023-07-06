package name.zzzzzzzzzzz.anzuowanju.clas;

import android.view.MenuItem;
import android.webkit.WebView;

import org.zhscript.clas.Switch_;

/**
 * Created by zzzzzzzzzzz on 17-1-14.
 */

public class Menu2_ {
    final int group_id_ = 200;

    Switch_ switch_;
    android.view.Menu menu_;

    public void with__(android.view.Menu menu, Switch_ s) {
        menu_ = menu;
        switch_ = s;

        new__(switch_.wv__(0));
    }

    MenuItem mi__(WebView wv) {
        for(int i = 0; i < menu_.size(); i++) {
            MenuItem mi = menu_.getItem(i);
            if(mi.getItemId() == wv.hashCode())
                return mi;
        }
        return null;
    }

    public void delete__(WebView wv) {
        MenuItem mi = mi__(wv);
        if(mi != null)
            menu_.removeItem(mi.getItemId());
    }
    public void new__(WebView wv) {
        MenuItem mi = mi__(wv);
        if(mi != null)
            return;
        menu_.add(group_id_, wv.hashCode(), 0, "……");
    }
    public void title__(WebView wv) {
        MenuItem mi = mi__(wv);
        if(mi != null)
            mi.setTitle(wv.getTitle());
    }

    public boolean select__(MenuItem item) {
        switch (item.getGroupId()) {
            case group_id_: {
                WebView wv = null;
                int id = item.getItemId();
                for (int i = 0; i < switch_.len__(); i++) {
                    WebView wv2 = switch_.wv__(i);
                    if(wv2.hashCode() == id) {
                        wv = wv2;
                        break;
                    }
                }
                if (wv != null) {
                    switch_.show__(wv);
                } else
                    System.err.println("cant select " + id);
                return true;
            }
        }
        return false;
    }
}
