package org.zhscript.clas;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 17-7-31.
 */

public class Query_ {
	public static String[] query__(String[] a, Zs_ zs, long qv_up, Object resource, Context c) {
		String code = null;
		switch (a.length) {
			case 0:case 1:
				break;
			default:
				code = a[a.length - 1];
				break;
		}
		ContentResolver resolver = c.getContentResolver();
		//String _1 = android.provider.ContactsContract.Data.DISPLAY_NAME;
		//Uri _1 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		//String _2 = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		//String _3 = ContactsContract.CommonDataKinds.Phone.NUMBER;
		Cursor cursor = resolver.query(Uri.parse(a[0]),
				a.length > 2 && a[1] != null ? a[1].split(",") : null,
				a.length > 3 ? a[2] : null, null, null);
		ArrayList<String> ret = new ArrayList<>();
		while (cursor.moveToNext()) {
			if(code == null || code.isEmpty()) {
				for(int i = 0; i < cursor.getColumnCount(); i++)
					ret.add(cursor.getString(i));
			} else {
				ArrayList<String> al = new ArrayList<>();
				for(int i = 0; i < cursor.getColumnCount(); i++) {
					//cursor.getType(i);
					al.add(cursor.getString(i));
				}
				for(String s : zs.i__(code, resource, qv_up, al.toArray(new String[0])))
					ret.add(s);
			}
		}
		cursor.close();
		return ret.toArray(new String[0]);
	}
}
