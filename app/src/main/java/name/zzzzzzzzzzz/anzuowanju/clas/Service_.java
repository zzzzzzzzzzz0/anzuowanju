package name.zzzzzzzzzzz.anzuowanju.clas;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import org.zhscript.clas.*;

import java.io.IOException;

/**
 * Created by zzzzzzzzzzz on 17-6-2.
 */

public class Service_ extends Service {
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy");
		if(server_ != null) {
			server_.stop__();
			server_ = null;
		}
	}

	Server_ server_;

	@Override
	//@IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true)
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand(" + intent + "," + flags + "," + startId);
		if(intent != null) {
			All_ all = All_.this__(null);
			switch (intent.getStringExtra("")) {
				case "server": {
					try {
						server_ = new Server_(intent.getIntExtra("port", 0), all.i_, all.zs_);
					} catch (IOException e) {
						all.i_.sendMessage__(e);
					}
					break;
				}
				case "定时":
					Timer_.start__(intent.getStringExtra("typ"),
							intent.getStringExtra("src"),
							intent.getStringExtra("src2"),
							intent.getIntArrayExtra("tm"),
							intent.getStringExtra("ms2"),
                            all.zs_, all.i_, all.getContext());
					break;
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}

	public static String[] service__(String src, String[] a, Context c) {
		Intent i = new Intent(c, Service_.class);
		i.putExtra("", src);
		switch (src) {
			case "server": {
				i.putExtra("port", Integer.valueOf(a[1]));
				break;
			}
			case "定时": {
				Timer_.Opt opt = Timer_.parse__(a);
				i.putExtra("typ", opt.typ);
				i.putExtra("src", opt.src);
				i.putExtra("src2", opt.src2);
				i.putExtra("tm", opt.tm);
				i.putExtra("ms2", opt.ms2);
				break;
			}
		}
		c.startService(i);
		return null;
	}
}
