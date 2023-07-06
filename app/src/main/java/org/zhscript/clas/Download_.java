package org.zhscript.clas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by zzzzzzzzzzz on 17-1-4.
 */

public abstract class Download_ implements DownloadListener {
	class Data {
		String url_, ua_, dispo_, mime_;
		long len_;
		String jindu_;
		long write_;
	}

	HashMap<Integer, Data> data_ = new HashMap<>();

	int notif__(Data data, String jindu, String err, int id) {
		if(jindu.equals(data.jindu_))
			return 0;
		data.jindu_ = jindu;
		return Notif_.notif__(zs_.i2__("dl-notif.zs",
				data.url_, data.ua_, data.dispo_, data.mime_, String.valueOf(data.len_),
				jindu, err, id == 0 ? "" : String.valueOf(id)),
				i_.context__(), i_.class__());
	}

	int begin__(Data data) {
		int id = notif__(data, "", null, 0);
		data_.put(id, data);
		return id;
	}
	void write__(int id, int len) {
		Data data = data_.get(id);
		data.write_ += len;
		notif__(data, String.format("%.1f", 100f * data.write_ / data.len_), null, id);
	}
	void end__(int id, String ret, String err) {
		if(err == null)
			Notif_.cancel__(id, i_.context__());
		else {
			Data data = data_.get(id);
			notif__(data, "x", err, id);
		}
		data_.remove(id);
	}
	public abstract void start__(Intent i);

	I_ i_;
	Zs_ zs_;
	public Download_(I_ i, Zs_ zs) {
		i_ = i;
		zs_ = zs;
	}

	@Override
	public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
		//start__(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		Data data = new Data();
		data.url_ = url;
		data.ua_ = userAgent;
		data.dispo_ = contentDisposition;
		data.mime_ = mimetype;
		data.len_ = contentLength;
		int id = begin__(data);
		new Task_().execute(url, mimetype, String.valueOf(id));
	}

	class Task_ extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String ret = null;
			int id = Integer.valueOf(params[2]);
			try {
				URL url = new URL(params[0]);
				String name = (url.getHost() + url.getPath());
				{
					String s = Url_.decode__(name);
					if(!s.isEmpty())
						name = s;
				}
				{
					final int limit = 60;
					if(name.length() > limit)
						name = name.substring(name.length() - limit);
				}
				for(; name.length() > 0;) {
					switch (name.charAt(0)) {
						case '/':case '.':
							name = name.substring(1);
							continue;
					}
					break;
				}
				name = name.replace('/', '_');
				File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
				if(!file.exists()) {
					InputStream is = url.openStream();
					FileOutputStream fos = new FileOutputStream(file);
					byte[] buf = new byte[1024];
					int len;
					while ((len = is.read(buf)) != -1) {
						fos.write(buf, 0, len);
						write__(id, len);
					}
					is.close();
					fos.flush();
					fos.close();
				}
				//url.openConnection().getContentType()
				ret = file + ":" + params[1];
			} catch (MalformedURLException e) {
				end2__(id, e);
				return null;
			} catch (FileNotFoundException e) {
				end2__(id, e);
				return null;
			} catch (IOException e) {
				end2__(id, e);
				return null;
			}
			end__(id, ret, null);
			return ret;
		}

		void end2__(int id, Exception e) {
			e.printStackTrace();
			end__(id, null, e.getLocalizedMessage());
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			if(s == null)
				return;
			Intent it = new Intent(Intent.ACTION_VIEW);
			it.addCategory(Intent.CATEGORY_DEFAULT);
			it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			String type = "*/*";
			int i = s.indexOf(":");
			if(i >= 0) {
				type = s.substring(i + 1);
				s = s.substring(0, i);
			}
			it.setDataAndType(i_.fp__().uri__(s, it, i_.context__()), type);
			start__(it);
		}
	}
}
