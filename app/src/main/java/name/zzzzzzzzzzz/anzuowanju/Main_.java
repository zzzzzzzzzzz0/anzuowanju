package name.zzzzzzzzzzz.anzuowanju;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;

import org.zhscript.clas.*;
import name.zzzzzzzzzzz.anzuowanju.clas.*;

public class Main_ extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /*static {
        System.loadLibrary("native-lib");
    }
    public native String stringFromJNI();*/

    public static final String pack_name_ = Main_.class.getPackage().getName(), app_name_;
    static {
        app_name_ = pack_name_.substring(pack_name_.lastIndexOf(".") + 1);
    }

    All_ all_;

    Handler h_ = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                switch (msg.what) {
                    case All_.msg_:
                        all_.suidao3__(msg.obj);
                        break;
                    case All_.back_press_:
                        onBackPressed();
                        break;
                    case All_.appmenu_o_:
                        drawer_.openDrawer(GravityCompat.START);
                        break;
                    case All_.appmenu_x_:
                        drawer_.closeDrawer(GravityCompat.START);
                        break;
                }
        }
    };

    WebView wv__() {return all_.switch_.wv__();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
        Menu_.with__(nv.getMenu());

        drawer_ = (DrawerLayout) findViewById(R.id.drawer_layout);

        all_ = All_.this__(this);
        all_.i_ = new I_() {
            @Override
            public String pkg_name__() {return pack_name_;}
            @Override
            public String app_name__() {return app_name_;}
            @Override
            public String account_name__() {return app_name_ + "@zhscript.org";}
            @Override
            public Activity activity__() {return Main_.this;}
            @Override
            public Context context__() {return Main_.this;}
            @Override
            public Class<?> class__() {return Main_.class;}

            @Override
            public void sendMessage__(Object o) {
                h_.sendMessage(h_.obtainMessage(All_.msg_, o));
            }
            @Override
            public void send_msg2__(int i) {
                h_.sendMessage(h_.obtainMessage(i));
            }
            @Override
            public File export_dir__() {
                File f = FileOp_.ext_dir__(app_name_ + "-export", this);
                if(f == null)
                    throw I_.buzhichi__("无卡？有卡无权？");
                return f;
            }
            @Override
            public void edit__(String typ, String[] a, Bundle b2) {
                Bundle b = new Bundle();
                b.putString("typ", typ != null ? typ : "");
                b.putStringArray("a", a);
                b.putBundle("", b2);
                start__(All_.n_edit_, b);
            }
            @Override
            public void start__(int forr, Bundle b) {
                Intent i;
                switch (forr) {
                    case All_.n_edit_:
                        i = new Intent(Main_.this, Edit_.class);
                        break;
                    case All_.n_hellolight_:
                        i = new Intent(Main_.this, com.hello.hellolight.hellolight___.class);
                        break;
                    default:
                        return;
                }
                if(b != null)
                    i.putExtras(b);
                startActivityForResult(i, forr);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
            @Override
            protected int ic_launcher__() {return R.mipmap.ic_launcher;}
            @Override
            public void view__(String op) {
                switch (op) {
                    case "fs":
                        getWindow().getDecorView().setSystemUiVisibility(
                                View.SYSTEM_UI_FLAG_IMMERSIVE
                                        // Set the content to appear under the system bars so that the
                                        // content doesn't resize when the system bars hide and show.
                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                        // Hide the nav bar and status bar
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
                        break;
                }
            }
        };
        all_.i_.fp__();
        all_.msgbox_ = new Msgbox_() {
            @Override
            public void toast__(Object s) {toast__(s, Main_.this);}
            @Override
            public void snackbar__(String s) {snackbar__(s, wv__());}
            @Override
            public void msgbox__(String s) {msgbox__(s, Main_.this);}
        };
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
                all_.msgbox_.toast__(throwable);
            }
        });
        it__(getIntent(), nv.getMenu());
    }

    void it__(Intent i, Menu m) {
        String s = i.getStringExtra(Start_.ext_src_);
        if(m != null)
            all_.init__(findViewById(R.id.webView1), findViewById(R.id.viewFlipper1),
                    findViewById(R.id.textView), m, s == null);
        all_.shell__("def.zs");
        if (s != null) {
            String[] a = i.getStringArrayExtra(Start_.ext_arg_);
            System.out.println("it__ " + s + " " + Util_.s__(a));
            all_.zs_.i__(s, new Resource_(m == null), 0, a);
            return;
        }
        Uri uri = i.getData();
        if(uri != null) {
            String url = uri.toString();
            String[] a2 = all_.zs_.i__("it-url.zs", true, null, null,
                    new Resource_(m == null), null, 0, url);
            switch (a2.length) {
                case 1:
                    url = a2[0];
                    break;
                default:
                    return;
            }
            url = Wzs_.scheme__(url);
            if(m == null) {
                all_.web_.loadurl_new__(url);
                return;
            }
            all_.web_.loadurl__(url);
            return;
        }
        if(m == null)
            return;
        all_.web_.loadurl__(Web_.index_);
        for (String name2 : getFilesDir().list((dir, name) -> name.startsWith(Url_.init_))) {
            all_.init__(name2);
        }
    }

    @Override
    protected void onDestroy() {
        all_.delete__();
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        it__(intent, null);
    }

    String get__(String name, Intent data) {
        if(data == null)
            return null;
        Bundle b = data.getBundleExtra("");
        if(b == null)
            return null;
        return b.getString(name);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case All_.n_edit_: {
                    boolean ret = false;
                    if (resultCode == RESULT_OK) {
                        String text = data.getStringExtra("text");
                        switch (data.getStringExtra("typ")) {
                            case UrlBig_.adblock_flag_: {
                                Tag_.Ctrl_ ctrl = all_.tag_.ctrl__(wv__());
                                ctrl.ub_.mk_adblock_url__(text, all_.zs_, all_.tag_);
                                ctrl.is_reload_ = true;
                                wv__().reload();
                                break;
                            }
                            case Edit_.url_:
                                if(!text.equals(wv__().getUrl()))
                                    wv__().loadUrl(text);
                                break;
                            default: {
                                String filename = data.getStringExtra("filename");
                                all_.file_op_.write__(text, filename);
                                String hou = get__("gaihou", data);
                                if (hou != null)
                                    all_.zs_.i__(hou);
                                break;
                            }
                        }
                        ret = true;
                    }
                    {
                        String hou = get__("hou", data);
                        if (hou != null) {
                            all_.zs_.i__(hou);
                            ret = true;
                        }
                    }
                    if(ret)
                        return;
                    break;
                }
                case All_.n_hellolight_:
                    on_back_press__();
                    break;
                case All_.n_upload_:
                    if (all_.upload_.result__(resultCode, data))
                        return;
                    break;
                default:
                    if (all_.ar_.on__(requestCode, resultCode, data, this))
                        return;
                    break;
            }
        } catch (Exception e) {
            all_.i_.sendMessage__("onActivityResult 时例外\n\n" + e);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void on_back_press__() {
        if(all_.on_back_press_ != null) {
            System.out.println("on_back_press_" + all_.on_back_press_);
            all_.zs_.i__(all_.on_back_press_);
            all_.on_back_press_ = null;
        }
    }

    @Override
    public void onBackPressed() {
        all_.loading_hide__();

        if (!close_drawer__()) {
            on_back_press__();
            if(wv__().canGoBack()) {
                wv__().goBack();
                return;
            }
            if(Wzs_.can_direct_close__(wv__().getUrl())) {
                try {
                    if (all_.close__())
                        return;
                } catch (IOException e) {
                    all_.i_.sendMessage__(e);
                }
            }
            drawer_.openDrawer(GravityCompat.START);
        }
    }

    DrawerLayout drawer_;
    boolean close_drawer__() {
        if (drawer_.isDrawerOpen(GravityCompat.START)) {
            drawer_.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getGroupId()) {
            case All_.menugroup_17_:
                all_.zs_.i__(item.getTitle().toString());
                break;
            default:
                if(all_.menu2_.select__(item))
                    break;
                if(all_.suidao2__(Menu_.get2__(item.getTitle().toString()), 0, null) != null)
                    return true;
                break;
        }
        close_drawer__();
        return true;
    }
}
