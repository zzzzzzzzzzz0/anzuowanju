package org.zhscript.clas;

import org.zhscript.Exception_;
import org.zhscript.Shell_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by zzzzzzzzzzz on 17-2-27.
 */

public class Util_ {
    public static String s__(String[] a, String n) {
        String s = "";
        if (a != null) {
            boolean b = true;
            for (String s1 : a) {
                if (b)
                    b = false;
                else
                    s += n;
                s += s1;
            }
        }
        return s;
    }
    public static String s__(String[] a) {return s__(a, "\n");}

    public static String replace__(String[] a) {
        String s = a[0];
        for (int i = 1; i < a.length; i++) {
            String from = a[i];
            if(++i == a.length) {
                s = s.replace(from, "");
                break;
            }
            String to = a[++i];
            s = s.replace(from, to);
        }
        return s;
    }

    public static String[] set_ext__(String[] a) {
        String path = a[0];
        {
            int i = path.lastIndexOf('/');
            if(i < 0) i = 0;
            i = path.indexOf('.', i);
            if(i < 0) return null;
            path = path.substring(0, i + 1);
        }
        String[] a2 = new String[a.length - 1];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = path + a[i + 1];
        }
        return a2;
    }

    public static String[] a2__(String[] a, int i, int len2) {
        String[] a2 = new String[len2];
        System.arraycopy(a, i, a2, 0, a2.length);
        return a2;
    }
    public static String[] a2__(String[] a, int i) {
        return a2__(a, i, a.length - i);
    }

    public static String[] encode__(String[] a) {
        String[] a2 = new String[a.length];
        for (int i2 = 0; i2 < a2.length; i2++) {
            String s2 = "", s = a[i2];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '&':
                        s2 += "&amp;";
                        break;
                    case '"':
                        s2 += "&quot;";
                        break;
                    case '\'':
                        s2 += "&apos;";
                        break;
                    case '\n':
                        s2 += "\\n";
                        break;
                    case '\r':
                        s2 += "\\r";
                        break;
                    case '\\':
                        s2 += "\\\\";
                        break;
                    default:
                        s2 += c;
                        break;
                }
            }
            a2[i2] = s2;
            //System.out.println(i2 + ") " + a2[i2]);
        }
        return a2;
    }

    public static String[] arg__(String[] a) {
        int len1 = Integer.valueOf(a[0]);
        String[] a2 = new String[len1 + 1];
        /*for(int i = 0; i < a2.length; i++)
            a2[i] = "";*/
        int i1b = len1 + 1;
        ArrayList<Integer> al1 = new ArrayList<>();
        for(int i = 1; i < i1b && i < a.length; i++) {
            String s2 = a[i];
            int i11 = -1, i111 = -1, i2 = i - 1;
            if(s2 == null || s2.isEmpty()) {
                int i12 = i2 + i1b;
                if(i12 < a.length) {
                    i11 = i12;
                    i111 = i11 - 1;
                }
            } else {
                for (int i1 = i1b; i1 < a.length; i1++) {
                    String s1 = a[i1];
                    if (s1 == null)
                        continue;
                    if (!s1.equals(s2))
                        continue;
                    i111 = i1;
                    if(s2.endsWith("-")) {
                        a2[i2] = "1";
                        break;
                    }
                    i11 = i1 + 1;
                    break;
                }
            }
            if(i11 >= 0) {
                a2[i2] = a[i11];
                al1.add(i11);
            }
            if(i111 >= 0) {
                al1.add(i111);
            }
        }
        String s = "";
        for(int i1 = i1b; i1 < a.length; i1++) {
            if(al1.contains(i1))
                continue;
            if(!s.isEmpty())
                s += "、";
            String s1 = a[i1];
            //System.out.println(i1+")"+s1);
            if(s1 == null) {
                s += Shell_.null_;
                continue;
            }
            if(!s1.isEmpty())
                s += "下原样" + s1 + "上原样";
        }
        a2[len1] = s;
        //System.out.println(a2[len1]);
        return a2;
    }

    public static String[][] arg2__(String[] a, String... a2) {
        String[][] ret = new String[2][];
        ArrayList<String> al3 = new ArrayList<>();
        ArrayList<String> al1 = new ArrayList<>();
        Collections.addAll(al1, a);
        ArrayList<String> al2 = new ArrayList<>();
        Collections.addAll(al2, a2);

        for(Iterator<String> it = al1.iterator(); it.hasNext();) {
            String s = it.next();
            for(Iterator<String> it2 = al2.iterator(); it2.hasNext();) {
                if(it2.next().equals(s)) {
                    it2.remove();
                    it.remove();
                    if(it.hasNext()) {
                        al3.add(it.next());
                        it.remove();
                    }
                    break;
                }
            }
        }

        ret[0] = al3.toArray(new String[0]);
        ret[1] = al1.toArray(new String[0]);
        return ret;
    }

    static class Foreach_Opt extends Opt_ {
        int len1 = 1, i_ = 0;
        boolean xuhao = false;
        @Override
        Item_[] items__() {
            return new Item_[] {
                    new Item_("-个", 1, (s) -> {
                        len1 = Integer.valueOf(s[0]);
                    }),
                    new Item_("-序号", () -> {
                        xuhao = true;
                    }),
                    new Item_((i) -> {
                        i_ = i;
                    })
            };
        }
    }

    public static String[] foreach__(String[] a, Zs_ zs, long qv_up, Object resource) {
        Foreach_Opt opt = new Foreach_Opt();
        opt.parse__(a);
        String src = a[opt.i_++];
        if(opt.xuhao)
            opt.len1++;
        String ret = "";
        ArrayList<String> ret2 = new ArrayList<>();
        boolean to_ret2 = false;
        String[] a2 = new String[opt.len1];
        for(int i3 = 0; opt.i_ < a.length;) {
            int i2 = 0;
            if(opt.xuhao)
                a2[i2++] = Integer.toString(++i3);
            for(; i2 < a2.length;)
                a2[i2++] = a[opt.i_++];
            String[] a3 = null;
            int i4 = 0;
            try {
                a3 = zs.i__(src, resource, qv_up, a2);
            } catch (Exception_ e) {
                i4 = Exception_.for__(e);
            }
            if(a3 != null)
                switch (a3.length) {
                    case 0:
                        break;
                    case 1:
                        ret += a3[0];
                        break;
                    default: {
                        ret += a3[0];
                        ret2.add(ret);
                        int i = 1;
                        for(; i < a3.length - 1; i++)
                            ret2.add(a[i]);
                        ret = a3[i];
                        to_ret2 = true;
                        break;
                    }
                }
           if(i4 == Exception_.IS_BREAK)
                break;
        }
        if(to_ret2)
            ret2.add(ret);
        return to_ret2 ? ret2.toArray(new String[0]) : new String[] {ret};
    }
}
