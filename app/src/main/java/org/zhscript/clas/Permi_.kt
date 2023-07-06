package org.zhscript.clas

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build.VERSION.SDK_INT
import android.support.v4.app.ActivityCompat
import java.util.ArrayList

class Permi_ {
    val code_ = 102

    fun z__(a:Array<String>, /*pkgname:String,*/ i: I_):Boolean {
        if (SDK_INT < 23) return true

        val c: Activity = i.activity__()
        if (!has__(a, /*pkgname,*/ c)) {
            c.requestPermissions(a, code_)
            i.sendMessage__("请重试")
            return has__(a, /*pkgname,*/ c)
        }

        return true
    }

    private fun has__(a:Array<String>, /*pkgname:String,*/ c: Activity):Boolean {
        //val pm = c.packageManager
        for (s in a) {
            /*if (pm.checkPermission(s, pkgname) != PackageManager.PERMISSION_GRANTED) {
                return false
            }*/
            if (!has__(s, c)) {
                return false
            }
        }
        return true
    }
    private fun has__(s:String, c: Activity):Boolean {
        return ActivityCompat.checkSelfPermission(c, s) == PackageManager.PERMISSION_GRANTED
    }

    private val aa_:Array<Array<String>> = arrayOf(sms_, phone_, contact_, shortcut_,
            ext_dir_, locat_/*, calendar_*/)
    fun z__(e:SecurityException, i: I_):Boolean {
        var a2 = ArrayList<String>()
        for (r in Regex("""android\.permission\.[A-Z_]+""").findAll(e.message!!)) {
            a2.add(r.value)
        }
        if (a2.isNotEmpty()) {
            z__(a2.toTypedArray(), i)
            return true
        }
        /*for(a in aa_) {
            for(s in a) {
                if (e.message!!.contains(s)) {
                    z__(a, i)
                    return true
                }
            }
        }*/
        return false
    }

    companion object {
        val locat_ = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
        val ext_dir_ = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                               Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val sms_ = arrayOf(Manifest.permission.SEND_SMS)
        val phone_ = arrayOf(Manifest.permission.CALL_PHONE)
        val contact_ = arrayOf(Manifest.permission.READ_CONTACTS,
                               Manifest.permission.WRITE_CONTACTS)
        val shortcut_ = arrayOf(Manifest.permission.INSTALL_SHORTCUT/*,
                                Manifest.permission.UNINSTALL_SHORTCUT*/)
        /*val calendar_ = arrayOf(Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR)*/
    }
}