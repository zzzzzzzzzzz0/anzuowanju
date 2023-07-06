package org.zhscript.clas;

import android.os.Build;

import org.zhscript.Shell_;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 17-1-7.
 */

public class Wzs_ {
	public static final String wzs_ = "zsa", _wzs_;
	static {
		_wzs_ = "." + wzs_;
	}

    public static void q2al__(String s, ArrayList<String> al, ArrayList<String> al2) {
        if(s != null) {
            String[] a = s.split("&|=");
            for(int i = 0; i < a.length; i++) {
                if(a[i].equals/*startsWith*/(Wzs_.arg_)) {
                    if(++i >= a.length) {
                        if(al != null)
                            al.add("");
                        break;
                    }
                    if(al != null)
                        al.add(Url_.decode__(a[i]));
                } else {
                    al2.add(Url_.decode__(a[i]));
                    if(++i >= a.length) {
                        break;
                    }
                    al2.add(Url_.decode__(a[i]));
                }
            }
        }
    }

    public static String[] fx__(String url, Zs_ zs, long qv_up, Tag_.Ctrl_ ctrl) throws MalformedURLException {
		//System.out.println(url);
		URL url2 = new URL(url);
		String path = url2.getPath();
		if(path.endsWith(_wzs_)) {
			while (path.startsWith("/"))
				path = path.substring(1);
			path = Url_.decode__(path);
			ArrayList<String> al = new ArrayList<>(), al2 = new ArrayList<>();
            q2al__(url2.getQuery(), al, al2);
			return zs.i__(path, true, null, ctrl, null, al2.toArray(new String[0]), qv_up, al.toArray(new String[0]));
		}
		return null;
	}
	public static String[] fx__(String url, Zs_ zs, long qv_up) throws MalformedURLException {
		return fx__(url, zs, qv_up, null);
	}
	public static String[] fx__(String url, Zs_ zs) throws MalformedURLException {
		return fx__(url, zs, 0);
	}

	public static boolean is__(String url) {
		return url.endsWith(Wzs_._wzs_) || url.indexOf(Wzs_._wzs_ + "?") >= 0;
	}
	public static String url__(String url) {
		url = "./" + url;
		switch (Build.VERSION.SDK_INT) {
			case 17:
				return file_ + url;
		}
		return Web_.file_ + url;
	}

	private static final String file_;
	static {
		file_ = wzs_ + Web_.file_;
	}
	public static String[] file__(String url, Zs_ zs, long qv_up, Tag_.Ctrl_ ctrl) throws MalformedURLException {
		if(is_zhongjian__(url))
			url = url.substring(wzs_.length());
		return fx__(url, zs, qv_up, ctrl);
	}
	public static boolean is_zhongjian__(String url) {
		return url.startsWith(file_);
	}

    public static final String zs_ = "zhscript://";
	private static final String _html_;
    static {
        _html_ = Web_.root_ + "wzs.html?";
    }
    public static String scheme__(String url) {
        if(url.startsWith(zs_))
            return _html_ + url.substring(zs_.length());
        if(is_zhongjian__(url))
            return _html_ + url.substring(file_.length()).replace('?', '&');
        return url;
    }

	public static boolean can_direct_close__(String url) {
		//System.out.println(url);
		return url == null || url.startsWith(Web_.file_) || url.equals(Switch_.blank_)
				|| is_zhongjian__(url);
	}

	public static final String arg_ = "arg";
	public static String url__(String[] a) {
		String url = a[0];
		if(a.length > 1) {
			for(int i = 1; i < a.length; i++) {
				if(i == 1) {
					if (!url.endsWith("?"))
						url += url.contains("?") ? "&" : "?";
				} else
					url += "&";
				url += arg_ /*+ i*/ + "=" + Url_.encode__(a[i]);
			}
		}
		return url;
	}
}
