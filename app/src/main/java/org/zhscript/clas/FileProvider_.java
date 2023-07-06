package org.zhscript.clas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by zzzzzzzzzzz on 17-10-16.
 */

public abstract class FileProvider_ {
    public abstract String authority__();

    public Uri uri__(File f, Intent it, Context c) {
        Uri u;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            u = FileProvider.getUriForFile(c, authority__(), f);
            it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else
            u = Uri.fromFile(f);
        return u;
    }
    public Uri uri__(String s, Intent it, Context c) {
        if(s.startsWith(Web_.file_))
            s = s.substring(Web_.file_.length());
        return uri__(new File(s), it, c);
    }
}
