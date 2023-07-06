package org.zhscript.clas

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class Download2_ {
    val ln_flag_ = "_/"
    fun name__(url:URL):String {
        var name = url.path
        //println(name)
        if (name == "/")
            return "index.html"
        val i = name.lastIndexOf(ln_flag_)
        //println(ln_flag_ + " " + i)
        if (i >= 0) {
            val i = name.indexOf('/', i + ln_flag_.length - 1)
            if (i >= 0) {
                name = name.substring(i)
            }
        }
        //println(name)
        return name.substring(1)
                .replace("/index.", ".")
                .replace(Regex("[/|<>]+"), "_")
    }

    fun z__(s:String, c: Context):Array<String>? {
        val url = Uri.parse(s)
        val r = DownloadManager.Request(url)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            r.allowScanningByMediaScanner()
            r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        }
        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name__(URL(s)));
        val dm = c.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(r)

        return null
    }

    fun z__(s:String):Array<String>? {
        val url = URL(s)
        val conn = url.openConnection()
        conn.doInput = true
        conn.doOutput = true
        val is1 = conn.getInputStream()
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val f = File(Environment.getExternalStorageDirectory(), name__(url))
            println(f)
            val fos = FileOutputStream(f)
            var buf = byteArrayOf()
            //buf.size = 1024
            while (true) {
                val len = is1.read(buf)
                if (len == -1) break
                fos.write(buf, 0, len)
            }
            fos.close()
            is1.close()
        }

        return null
    }
}