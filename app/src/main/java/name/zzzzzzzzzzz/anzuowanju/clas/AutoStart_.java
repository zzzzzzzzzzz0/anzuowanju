package name.zzzzzzzzzzz.anzuowanju.clas;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.zhscript.clas.*;

import java.io.IOException;

import name.zzzzzzzzzzz.anzuowanju.Main_;

/**
 * Created by zzzzzzzzzzz on 17-1-14.
 */

public class AutoStart_ extends BroadcastReceiver {
	final String zs_ = "autostart.zs";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			All_ all = All_.this__(context);
			String code;
			try {
				code = FileOp_.read__(FileOp_.open__(zs_, true, context));
			} catch (IOException e) {
				System.err.println(e);
				return;
			}
			int len = all.shell_.len(code);
			System.out.println(zs_ + " len=" + len);
			if(len <= 0) {
				return;
			}
			Intent i = new Intent(context, Main_.class);
			Start_.it__("加载“" + zs_ + "”", i);
			Start_.new_task__(i);
			context.startActivity(i);
		}
	}
}
