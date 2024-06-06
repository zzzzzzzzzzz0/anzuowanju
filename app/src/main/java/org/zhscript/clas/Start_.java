package org.zhscript.clas;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 16-12-17.
 */

public class Start_ {
	public static String[] start__(String[] a, ActivityResult_ ar, I_ i0, Zs_ zs, long qv_up, Object resource) {
        Activity c = i0.activity__();
		int i = 0;
		switch (a[i]) {
			case "shutdown": {
				AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
				Intent it = new Intent("com.android.settings.action.REQUEST_POWER_OFF");
				PendingIntent pi = PendingIntent.getBroadcast(c, 0, it, PendingIntent.FLAG_CANCEL_CURRENT);
				am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
				return null;
			}
		}
		final String typ_app = "-app";
		String typ = "", die_code = null;
		for(; i < a.length; i++) {
			switch (a[i]) {
				case typ_app:
					typ = a[i];
					continue;
				case "-补代码":
					die_code = a[++i];
					continue;
			}
			break;
		}
		String act = a[i];
		Intent it;
		switch (typ) {
			case typ_app:
				wai:
				for (;;) {
					for (String n : act.split("\\|")) {
						it = c.getPackageManager().getLaunchIntentForPackage(n);
						if(it != null)
							break wai;
					}
					throw I_.buzhichi__("包名似乎无效");
				}
				i++;
				break;
			default:
				if (act.indexOf('.') < 0) {
					it = new Intent();
					act = "android.intent.action." + act.toUpperCase();
					it.setAction(act);
					i = 2;
					if (a.length > 1) {
						String url = a[1];
						if (url != null) {
							if(url.startsWith("-"))
								i = 1;
							else {
								Uri uri = Uri.parse(url);
								if (a.length > 2 && a[2].equals("-type")) {
									i = 4;
									it.setDataAndType(uri, a[3]);
								} else
									it.setData(uri);
							}
						}
					}
				} else {
					it = new Intent(act);
					i++;
				}
				break;
		}
		int sp = sp__(a, i);
		it__(a, i, sp, i0, it);
		try {
			if(sp < 0)
				c.startActivity(it);
			else
				c.startActivityForResult(it, ar.add__(a, sp + 1));
		} catch (Exception e) {
			if(die_code != null) {
				zs.i__(die_code, resource, qv_up, e.toString());
				return null;
			}
			throw e;
		}
		return null;
	}

	public static int sp__(String[] a, int i) {
		int sp = -1;
		for(int i1 = i; i1 < a.length; i1++) {
			if(a[i1].equals("----")) {
				sp = i1;
				break;
			}
		}
		return sp;
	}

	public static void it__(String[] a, int from, int to, I_ i0, Intent it) {
        FileProvider_ fp = i0.fp__();
        Activity c = i0.activity__();
		if(to < 0 || to > a.length)
			to = a.length;
		for(int i = from; i < to; i++) {
			String name = a[i];
			String val = a[++i];
			switch (name) {
				case "-包名":
					it.setPackage(val);
					continue;
				case "-优包名":
					Package_.set__(it, val, c);
					continue;
				case "-flags":
					switch (val) {
						case "CLEAR_TOP":
							it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							break;
						default:
							it.addFlags(to_int__(val));
							break;
					}
					continue;
				case "-组件":
					it.setComponent(new ComponentName(val, a[++i]));
					continue;
				case "-type":
					it.setType(val);
					continue;
				case "-data":
					it.setData(Uri.parse(val));
					continue;
				case "-data和type":
					it.setDataAndType(Uri.parse(val), a[++i]);
					continue;
				case "-文件":
					it.putExtra(Intent.EXTRA_STREAM, fp.uri__(FileOp_.get__(val, c), it, c));
					continue;
				case "-数文件": {
					ArrayList<Uri> al = new ArrayList<>();
					for(;;) {
						al.add(fp.uri__(FileOp_.get__(val, c), it, c));
						if(i + 1 >= to)
							break;
						val = a[++i];
						if(val.equals("-"))
							break;
					}
					it.putParcelableArrayListExtra(Intent.EXTRA_STREAM, al);
					continue;
				}
			}

			String typ = "s";
			int i1 = name.lastIndexOf(':');
			if(i1 >= 0) {
				typ = name.substring(i1 + 1);
				name = name.substring(0, i1);
			}
			if(name.startsWith("."))
				name = "android.intent.extra" + name.toUpperCase();
			System.out.println("it__ " + typ + " " + name + " " + val);
			/*if(val.equals("true")) {
				it.putExtra(name, true);
				continue;
			}
			if(val.equals("false")) {
				it.putExtra(name, false);
				continue;
			}
			try {
				double nval = Double.parseDouble(val);
				it.putExtra(name, nval);
				continue;
			} catch (NumberFormatException e) {}
			try {
				int nval = Integer.parseInt(val);
				it.putExtra(name, nval);
				continue;
			} catch (NumberFormatException e) {}
			it.putExtra(name, val);*/
			switch (typ) {
				case "s":
					it.putExtra(name, val);
					continue;
				case "b":
					it.putExtra(name, Boolean.valueOf(val));
					continue;
				case "i":
					it.putExtra(name, to_int__(val));
					continue;
				case "l":
					it.putExtra(name, Long.valueOf(val));
					continue;
				case "f":
					it.putExtra(name, Float.valueOf(val));
					continue;
				case "d":
					it.putExtra(name, Double.valueOf(val));
					continue;
                case "u":
                    it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION /*| Intent.FLAG_GRANT_WRITE_URI_PERMISSION*/);
                    switch(val) {
                        case "ic":
                            val = "android.resource://" + i0.pkg_name__() + "/raw/ic_launcher";
                            System.out.println(val);
                            break;
                    }
                    it.putExtra(name, Uri.parse(val));
                    continue;
			}
			throw I_.buzhichi__("- " + typ);
		}
	}

	public static final String ext_src_ = "s", ext_arg_ = "a";
	public static void it__(String src, String[] a, Intent it) {
		it.putExtra(ext_src_, src);
		if(a != null && a.length > 0)
			it.putExtra(ext_arg_, a);
	}
	public static void it__(String src, Intent it) {
		it__(src, null, it);
	}
	public static void it2__(String src, ArrayList<String> al, Intent it) {
		it__(src, al.isEmpty() ? null : al.toArray(new String[0]), it);
	}
	public static void it__(String[] a, Intent it) {
		if(a.length >= 1)
			it__(a[0], Util_.a2__(a, 1), it);
	}
	public static void it2__(ArrayList<String> al, Intent it) {
		it__(al.toArray(new String[0]), it);
	}
	public static void new_task__(Intent it) {
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	}

	public static int to_int__(String s) {
		if(s.startsWith("0x"))
			return Integer.valueOf(s.substring(2), 16);
		return Integer.valueOf(s);
	}

	public static int icon__(String s) {
		switch (s) {
			case "警告":
				return android.R.drawable.ic_dialog_alert;
			case "x":
				return android.R.drawable.ic_delete;
			case "星开":
				return android.R.drawable.star_big_on;
			case "星闭":
				return android.R.drawable.star_big_off;
			case "罗盘":
				return android.R.drawable.ic_menu_compass;
			case "文本":
				return android.R.drawable.ic_menu_edit;
			case "下载":
				return android.R.drawable.stat_sys_download;
			default:
				throw I_.buzhichi__("-" + s);
		}
	}

    public static void lock__(Activity c, Class<?> cls) {
        DevicePolicyManager dpm = (DevicePolicyManager)c.getSystemService(Context.DEVICE_POLICY_SERVICE);
        try {
            dpm.lockNow();
        } catch (SecurityException e) {
            Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, new ComponentName(c, cls));
            c.startActivityForResult(i, 1);
        }
    }
}
