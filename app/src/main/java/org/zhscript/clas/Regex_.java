package org.zhscript.clas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zzzzzzzzzzz on 16-12-28.
 */

public class Regex_ {
    public static boolean ms__(String s1, String s2) {
        return m__(s1, s__(s2)).matches();
    }

    public static Matcher m__(String s1, String s2) {
        return Pattern.compile(s2).matcher(s1);
    }
    static Matcher m__(String[] a, int i) {
        return m__(a[i], s__(a[i + 1]));
    }

    public static String[] regex__(String[] a, Zs_ zs, long qv_up, Object resource) {
        switch (a[0]) {
            case "1":
                if(m__(a, 1).matches())
                    return new String[] {"1"};
                break;
            case "": {
                Matcher m = m__(a, 1);
                String s = "";
                while (m.find()) {
                    String[] a2 = new String[m.groupCount()];
                    for(int i = 1; i <= m.groupCount(); i++)
                        a2[i - 1] = m.group(i);
                    s += Util_.s__(zs.i__(a[3], resource, qv_up, a2));
                }
                return new String[] {s};
            }
            default:
                throw I_.buzhichi__("- " + a[0]);
        }
        return null;
    }

    static final char s_begin_ = '<', s_end_ = '>';
    public static String s__(String s, boolean big) {
        if(s == null)
            return s;
        String s2 = "";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '.':
                    s2 += '\\';
                    break;
                case '*':case '+':
                    s2 += '.';
                    break;
                case s_begin_:
                    for(; ++i < s.length();) {
                        c = s.charAt(i);
                        if(c == s_end_)
                            break;
                        s2 += c;
                    }
                    continue;
            }
            s2 += c;
        }
        if(big) {
            if (!starts__(new String[] {String.valueOf(s_begin_), "^", ".*", ".+"}))
                s2 = ".*" + s2;
            if (!ends__(new String[] {String.valueOf(s_end_), "$", ".*", ".+"}))
                s2 += ".*";
        }
        System.out.println("Regex_s__" + s + "," + big + " " + s2);
        return s2;
    }
    public static String s__(String s) {
        return s__(s, false);
    }

    public static Boolean starts__(String[] a) {
        String s = a[0];
        for (int i = 1; i < a.length; i++) {
            if(s.startsWith(a[i])) {
                return true;
            }
        }
        return false;
    }
    public static Boolean ends__(String[] a) {
        String s = a[0];
        for (int i = 1; i < a.length; i++) {
            if(s.endsWith(a[i])) {
                return true;
            }
        }
        return false;
    }
}
