package org.zhscript.clas;

import android.content.res.AssetManager;

import org.zhscript.Exception_;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by zzzzzzzzzzz on 17-1-15.
 */

public class Dir_ {
    static class PathItem_ {
        String path_;
        boolean readonly_ = true;
        PathItem_(String path) {
            path_ = path;
        }
    }

    static void for__(String dirname, AssetManager am, Pattern p, ArrayList<PathItem_> al) throws IOException {
        String[] readonlyfiles = am.list(dirname);
        for (String s2 : readonlyfiles) {
            String path = dirname.isEmpty() ? s2 : dirname + "/" + s2;
            /*//变慢
            if(am.list(path).length > 0) {
                for__(path, am, p, al);
                continue;
            }*/
            if(p != null && !p.matcher(s2).matches())
                continue;
            al.add(new PathItem_(path));
        }
    }
    public static String[] for__(String[] a, AssetManager am, File canwritedir, Zs_ zs, long qv_up, Object resource) throws IOException {
        Pattern p = a.length >= 2 ? Pattern.compile(Regex_.s__(a[0])) : null;
        Pattern p2 = a.length >= 3 ? Pattern.compile(Regex_.s__(a[1])) : p;
        ArrayList<PathItem_> al = new ArrayList<>();

        for__("", am, p, al);
        for (File f : canwritedir.listFiles(((dir, name) -> p2 == null || p2.matcher(name).matches()))) {
            String s2 = f.getName();
            PathItem_ i2 = null;
            for (PathItem_ i : al) {
                if(i.path_.equals(s2)) {
                    i2 = i;
                    break;
                }
            }
            if(i2 == null) {
                i2 = new PathItem_(s2);
                al.add(i2);
            }
            i2.readonly_ = false;
        }

        String s = "", code = a[a.length - 1];
        for (PathItem_ i : al)
            try {
                for (String s1 : zs.i__(code, resource, qv_up, i.path_, i.readonly_ ? "1" : ""))
                    s += s1;
            } catch (Exception_ e) {
                if (Exception_.for__(e) == Exception_.IS_BREAK) {
                    break;
                }
            }
        return new String[] {s};
    }

    static long i_ = 0;
    static class Dir_Opt extends Opt_ {
        String tag_ = "p";

        class Dir {
            String dir_, tag_, reg_;
            Dir(String dir, String tag, String reg) {
                dir_ = dir;
                tag_ = tag;
                reg_ = Regex_.s__(reg);
            }
        }
        ArrayList<Dir>  dir_ = new ArrayList<>();
        String dir_tag_, dir_reg_;

        String src_;
        Zs_ zs_; long qv_up_; Object resource_;
        Dir_Opt(Zs_ zs, long qv_up, Object resource) {
            super();
            zs_ = zs; qv_up_ = qv_up; resource_ = resource;
        }

        @Override
        Item_[] items__() {
            return new Item_[] {
                    new Item_("-分", () -> {
                        //tag_ = "f" + tag_.substring(1);
                        tag_ += "f";
                    }),
                    new Item_("-名", () -> {
                        tag_ += "n";
                    }),
                    new Item_("-类型", () -> {
                        tag_ += "t";
                    }),
                    new Item_("-序号", () -> {
                        tag_ += "i";
                    }),
                    new Item_("-重置序号", () -> {
                        i_ = 0;
                    }),
                    new Item_("-标签", 1, (s) -> {
                        dir_tag_ = s[0];
                    }),
                    new Item_("-匹配", 1, (s) -> {
                        dir_reg_ = s[0];
                    }),
                    new Item_("-代码", 1, (s) -> {
                        src_ = s[0];
                    }),
                    new Item_(1, (s) -> {
                        dir_add__(s[0], dir_reg_);
                    }),
                    new Item_("-同名", 1, (s) -> {
                        String filename = s[0];
                        File f = new File(filename);
                        String dir = f.getParent();
                        if(dir == null)
                            throw I_.buzhichi__("文件名 " + filename);
                        String reg = f.getName();
                        int i2 = reg.indexOf(".");
                        if(i2 >= 0)
                            reg = reg.substring(0, i2 + 1);
                        reg += "+";
                        dir_add__(dir, reg);
                    }),
            };
        }
        void dir_add__(String dir, String reg) {
            if(dir.startsWith("-"))
                throw I_.buzhichi__(dir);
            dir_.add(new Dir(dir, dir_tag_, reg));
        }

        ArrayList<String> ret_ = new ArrayList<>();
        String ret2_ = "";


        void for__(File f, Dir d) {
            if(!f.exists())
                return;
            if(f.isDirectory()) {
                File[] ls;
                if(d.reg_ != null) {
                    ls = f.listFiles((dir, s) -> Regex_.m__(s, d.reg_).matches());
                } else
                    ls = f.listFiles();
                if(ls != null) {
                    List ls2 = Arrays.asList(ls);
                    Collections.sort(ls2, new Comparator<File>() {
                        @Override
                        public int compare(File file, File t1) {
                            if(is_idx__(file, t1)) {
                                return -1;
                            }
                            if(is_idx__(t1, file)) {
                                return 1;
                            }
                            return file.getAbsolutePath().compareTo(t1.getAbsolutePath());
                        }
                        boolean is_idx__(File file, File t1) {
                            if(file.getName().startsWith("index.")) {
                                if(file.getParent().equals(t1.getParent()))
                                    return true;
                            }
                            return false;
                        }
                    });
                    /*for (Object f2 : ls2) {
                        for__((File)f2, d);
                    }*/
                    for (File f2 : ls) {
                        for__(f2, d);
                    }
                }
            } else {
                String path = f.getAbsolutePath();
                for(int i = 0; i < tag_.length(); i++) {
                    switch (tag_.charAt(i)) {
                        case 'p':
                            ret_.add(path);
                            break;
                        case 'f':
                            ret_.add(d.dir_);
                            ret_.add(path.substring(d.dir_.length() + 1));
                            break;
                        case 'n':
                            ret_.add(f.getName());
                            break;
                        case 't': {
                            Url_.Ex_ ex = new Url_.Ex_(Web_.file_ + path);
                            ret_.add(ex.mime != null ? ex.mime : "");
                            break;
                        }
                        case 'i':
                            ret_.add(String.valueOf(++i_));
                            break;
                    }
                }
                if(d.tag_ != null)
                    ret_.add(d.tag_);
                if(src_ != null) {
                    String[] a = null;
                    try {
                        a = zs_.i__(src_, resource_, qv_up_, ret_.toArray(new String[0]));
                    } catch (Exception_ e) {
                        switch (Exception_.for__(e)) {
                            case Exception_.IS_CONTINUE:
                                break;
                            default:
                                throw e;
                        }
                    }
                    if(a != null)
                        for(String s : a)
                            ret2_ += s;
                    ret_.clear();
                }
            }
        }
        void for__() {
            for(Dir d : dir_) {
                File f = new File(d.dir_);
                d.dir_ = f.getAbsolutePath();
                for__(f, d);
            }
        }
        String[] for_ret__() {
            return src_ != null ? new String[] {ret2_} : ret_.toArray(new String[0]);
        }
    }

    public static String[] for__(String[] a, Zs_ zs, long qv_up, Object resource) {
        Dir_Opt opt = new Dir_Opt(zs, qv_up, resource);
        opt.parse__(a);
        try {
            opt.for__();
        } catch (Exception_ e) {
            Exception_.for__(e);
        }
        return opt.for_ret__();
    }
}
