package org.zhscript.clas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.io.File;

/**
 * Created by zzzzzzzzzzz on 17-2-27.
 */

public class I_ {
    public String pkg_name__() {return null;}
    public String app_name__() {return null;}
    public String account_name__() {return null;}
    public Activity activity__() {return null;}
	public Class<?> class__() {return null;}

	private Context context_;
	public void context__(Context c) {
		context_ = c;
	}
	public Context context__() {return context_;}

	public String[] px2dip__(int i) {
		float i2;
		if(context_ == null)
			i2 = i * 1.333333f;
		else {
			final float scale_ = context_.getResources().getDisplayMetrics().density;
			i2 = i / scale_ + 0.5f;
		}
		return new String[]{String.valueOf((int) i2)};
	}

	public void sendMessage__(Object o) {}
	public void send_msg2__(int i) {}
	public File export_dir__() {return null;}
	public void start__(int forr, Bundle b) {}
	public void edit__(String typ, String[] a, Bundle b2) {}
	protected int ic_launcher__() {return 0;}
	public void view__(String op) {}

	private FileProvider_ fp_;
    public FileProvider_ fp__() {
        if(fp_ == null) {
            fp_ = new FileProvider_() {
                @Override
                public String authority__() {
                    return pkg_name__() + ".fileprovider";
                }
            };
        }
        return fp_;
    }
    public Permi_ permi_ = new Permi_();

    public static Buzhichi_ buzhichi__(String s) {return new Buzhichi_(s);}
    public static Buzhichi_ buzhichi__() {return new Buzhichi_(null);}
	public static BuLiao_ buliao__() {return new BuLiao_();}
}
