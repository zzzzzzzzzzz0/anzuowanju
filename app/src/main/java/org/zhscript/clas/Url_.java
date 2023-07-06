package org.zhscript.clas;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by zzzzzzzzzzz on 17-8-6.
 */

public class Url_ {
	public static final String js_ = "javascript:", init_ = "init-";

	public static String url__(String url, boolean www) {
		for(;;) {
			if(url == null)
				break;
			if(url.startsWith("about:") || url.startsWith(js_))
				break;
			if(url.indexOf("://") > 0)
				break;
			if(Wzs_.is__(url)) {
				url = Wzs_.url__(url);
				break;
			}
			if(www && url.lastIndexOf(".") < 0)
				url = "www." + url + ".com";
			url = (www ? "http://" : Web_.root_) + url;
			break;
		}
		return url;
	}

	public enum Type_ {
		tp, f, p, none
	}
	public static Type_ type__(String url) {
		if (url == null)
			return Type_.none;
		if(        url.startsWith("http://")
				|| url.startsWith("https://")
				|| url.startsWith("ftp://")
				)
			return Type_.tp;
		if(url.startsWith(Web_.file_))
			return Type_.f;
		if(        url.startsWith("market://")
				|| url.startsWith("vnd:youtube")
				|| Regex_.m__(url, "[a-zA-Z\\d-]+:.+").matches()
				)
			return Type_.p;
		return Type_.none;
	}

	public static String encode__(String s) {
		try {
			return URLEncoder.encode(s, "utf8");
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getLocalizedMessage());
			return "";
		}
	}
	public static String decode__(String s) {
		try {
			return URLDecoder.decode(s, "utf8");
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getLocalizedMessage());
			return "";
		}
	}
	public static String[] encode__(String[] a) {
		for(int i = 0; i < a.length; i++)
			a[i] = encode__(a[i]);
		return a;
	}
	public static String[] decode__(String[] a) {
		for(int i = 0; i < a.length; i++)
			a[i] = decode__(a[i]);
		return a;
	}

	public static String[] parse__(String url) {
		try {
			URL url2 = new URL(url);
			return new String[] {
					url2.getProtocol(),
					url2.getHost(),
					String.valueOf(url2.getPort()),
                    decode__(url2.getPath()),
					url2.getQuery() != null ? url2.getQuery() : "",
					url2.getRef(),
			};
		} catch (MalformedURLException e) {}
		return new String[] {};
	}

	static class Ex_ {
		String mime, enc;
		URLConnection c;

		public Ex_(String url) {
			try {
				URL url2 = new URL(url);
				c = url2.openConnection();
				mime = c.getContentType();
				enc = c.getContentEncoding();
				//System.out.println(url + " " + mime + " " + enc);
				if(mime != null) {
					int i = mime.indexOf(";");
					if (i >= 0) {
						String s = mime.substring(i + 1);
						mime = mime.substring(0, i);
						String s1 = "charset=";
						i = s.indexOf(s1);
						if (i >= 0) {
							enc = s.substring(i + s1.length());
						}
						//System.out.println(mime + " " + enc);
					}
				}
			} catch (MalformedURLException e) {
			} catch (IOException e) {}
		}
	}
}
