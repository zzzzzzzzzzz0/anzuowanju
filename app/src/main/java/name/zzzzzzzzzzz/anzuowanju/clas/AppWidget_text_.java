package name.zzzzzzzzzzz.anzuowanju.clas;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import org.zhscript.clas.*;

import java.util.HashMap;

import name.zzzzzzzzzzz.anzuowanju.Main_;
import name.zzzzzzzzzzz.anzuowanju.R;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget_text_ extends AppWidgetProvider {
    public static final String zs_ = "widget.zs",
                                  //id_zs_ = "widget-id.zs",
                                  text_click_ = ".appwidget_text_click";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //context.getString(R.string.appwidget_text);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_);

        String[] a = null;
        {
            All_ all = All_.this__(context);
            String code;
            if(!all.tag_.file_.containsKey(zs_))
                all.tag_file_read__(zs_, context);
            code = all.tag_.file_.get(zs_);
            /*
            在7-armv7a上就没问题
            /data/app/name.zzzzzzzzzzz.anzuowanju-1/lib/x86_64/libzhscript2.so
            (std::function<bool (def::Item*)>::operator()(def::Item*) const+99)
            (Qv::for_def__(std::function<bool (def::Item*)>)+72)
            (segm::no::Item::mk_z2data(unsigned long, segm::All const*, unsigned long, Qv&)+279)
            (segm::no::Item::z2__(segm::Z2_Data const*, segm::All*, unsigned long&, keyword::Item const&, Qv&, segm::List&, Ret&)+1603)
            (segm::no::Item::z2__(segm::Z2_Data const*, segm::All*, unsigned long&, keyword::Item const&, Qv&, segm::List&, Ret&)+1774)
            */
            try {
                a = all.zs_.i__(code, String.valueOf(appWidgetId));
                //System.out.println(Util_.s__(a));
                if (a.length > 0)
                    views.setTextViewText(R.id.appwidget_text, a[0]);
            } catch (Exception e) {
                views.setTextViewText(R.id.appwidget_text, e.getLocalizedMessage());
            }
        }
        if(a != null && a.length > 1) {
            Intent i = new Intent(text_click_);
            Start_.it__(a[1], i);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
            views.setOnClickPendingIntent(R.id.appwidget_text, pi);
        } else {
            views.setOnClickPendingIntent(R.id.appwidget_text, null);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //String s = "";
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            /*if(!s.isEmpty())
                s += "、";
            s += appWidgetId;*/
        }
        //FileOp_.write__(s, id_zs_, context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent.getAction().equals(text_click_)) {
            //System.out.println(intent);
            //Msgbox_.toast__(intent, context);
            Intent i = new Intent(context, Main_.class);
            Start_.it__(intent.getStringExtra(Start_.ext_src_), i);
            Start_.new_task__(i);
            context.startActivity(i);
        }
    }

    static String[] i__(String[] a, Context c) {
        AppWidgetManager awm = AppWidgetManager.getInstance(c);
        //RemoteViews rv = new RemoteViews(c.getPackageName(), R.layout.app_widget_);
        String opt = a[0];
        switch (opt) {
            case "更新": {
                int[] i2 = new int[a.length - 1];
                for(int i = 0; i < i2.length; i++) {
                    int id = Integer.valueOf(a[i + 1]);
                    //System.out.println(opt + i1);
                    updateAppWidget(c, awm, id);
                    i2[i] = id;
                }
                //awm.updateAppWidget(i2, rv);
                return null;
            }
            case "ids": {
                int[] ids = awm.getAppWidgetIds(new ComponentName(
                        Main_.pack_name_,
                        AppWidget_text_.class.getName()));
                String[] s = new String[ids.length];
                for(int i = 0; i < ids.length; i++)
                    s[i] = String.valueOf(ids[i]);
                return s;
            }
            default:
                throw I_.buzhichi__(opt);
        }
    }
}

