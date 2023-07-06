package org.zhscript.clas

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.provider.CalendarContract
import java.util.*


class Calendar_ {
    fun chkacc__(c: Context) : Long {
        val cur = c.contentResolver.query(Uri.parse(url_), null, null, null, null) ?: return -1
        if (cur.moveToFirst()) {
            return cur.getLong(cur.getColumnIndex(CalendarContract.Calendars._ID))
        }
        return -1
    }

    fun addacc__(c: Context, i: I_) : Long {
        var values = ContentValues()
        values.put(CalendarContract.Calendars.NAME, i.app_name__())
        values.put(CalendarContract.Calendars.ACCOUNT_NAME, i.account_name__())
        values.put(CalendarContract.Calendars.ACCOUNT_TYPE, i.pkg_name__())
        values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, i.app_name__())
        values.put(CalendarContract.Calendars.VISIBLE, 1)
        values.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE)
        values.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER)
        values.put(CalendarContract.Calendars.SYNC_EVENTS, 1)
        values.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, TimeZone.getDefault().id)
        values.put(CalendarContract.Calendars.OWNER_ACCOUNT, i.account_name__())
        values.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0)

        val uri = Uri.parse(url_).buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, i.account_name__())
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, i.pkg_name__())
                .build()
        val res = c.contentResolver.insert(uri, values)
        if (res != null)
            return ContentUris.parseId(res)
        return -1
    }

    companion object {
        val   url_ = "content://com.android.calendar/calendars"
        val evurl_ = "content://com.android.calendar/events"
        var reurl_ = "content://com.android.calendar/reminders"

        fun time__(s:String, ms: DateTime_.MS_):Boolean {
            val mrs = Regex("""(\d{4})-(\d{1,2})-(\d{1,2})-(\d{1,2})-(\d{1,2})-(\d{1,2})""").findAll(s)
            if (mrs.count() > 0) {
                val l = mrs
                        .toList()[0]
                        .groupValues
                        //.filterIndexed { index, _ ->  index > 0}
                        .drop(1)
                        .map {it.toInt()}
                val cal = Calendar.getInstance()
                cal.set(l[0], l[1] - 1, l[2], l[3], l[4], l[5])
                //DateTime_.println__(cal)
                ms.l_ = cal.timeInMillis
                return true
            }
            return false
        }

        fun z__(a: Array<String>, i: I_) : Array<String>? {
            when(a[0]) {
                "提醒" -> {
                    val c = i.context__()
                    val thi = Calendar_()
                    var id = thi.chkacc__(c)
                    if (id == -1L) {
                        id = thi.addacc__(c, i)
                        if (id == -1L) {
                            throw Buzhichi_("acc")
                        }
                    }

                    var title = a[0]
                    var desc = ""
                    if (a.size > 1) {
                        title = a[1]
                    }
                    if (a.size > 2) {
                        desc = a[2]
                    }
                    val start = ms__(a, 3, "", System.currentTimeMillis())
                    val end = ms__(a, 4, "", start + 1000 * 60 * 1)
                    //println("$start\n$end")

                    val values = ContentValues()
                    values.put("title", title)
                    values.put("description", desc)
                    values.put("calendar_id", id)
                    values.put(CalendarContract.Events.DTSTART, start)
                    values.put(CalendarContract.Events.DTEND, end)
                    values.put(CalendarContract.Events.HAS_ALARM, 1)
                    //"Asia/Shanghai"
                    values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)

                    val res = c.contentResolver.insert(Uri.parse(evurl_), values)
                            ?: throw Buzhichi_(evurl_)

                    if (a.size <= 5) {
                        tixing__(0, res, c)
                    } else {
                        for (i in 5 until a.size) {
                            tixing__(ms__(a, i, "m", 0), res, c)
                        }
                    }
                }
                else -> throw Buzhichi_(null)
            }
            return null
        }

        private fun tixing__(tiqian: Long, res: Uri, c: Context) {
            val values2 = ContentValues()
            values2.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(res))
            values2.put(CalendarContract.Reminders.MINUTES, tiqian)
            values2.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)

            c.contentResolver.insert(Uri.parse(reurl_), values2)
                    ?: throw Buzhichi_(reurl_)
        }

        private fun ms__(a: Array<String>, i: Int, use: String, l: Long): Long {
            if (a.size > i) {
                val s = a[i]
                if (s.isNotEmpty()) {
                    var ms = DateTime_.MS_()
                    when(use) {
                        "m" -> {
                            DateTime_.time__(s, ms)
                            return ms.i_ / (60 * 1000L)
                        }
                        else -> {
                            if (!time__(s, ms)) {
                                DateTime_.time__(s, ms)
                            }
                            return ms.l_ + ms.i_
                        }
                    }
                }
            }
            return l
        }
    }
}