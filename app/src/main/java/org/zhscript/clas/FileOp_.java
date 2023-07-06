package org.zhscript.clas;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * Created by zzzzzzzzzzz on 16-12-13.
 */

public abstract class FileOp_ {
    public InputStream open__(String filename) {
        return open__(filename, true);
    }
    protected abstract InputStream open__(String filename, boolean use_save);
    public static InputStream open__(String filename, boolean use_save, Context c) {
        if(c == null || filename.startsWith("/")) {
            try {
                return new FileInputStream(filename);
            }
            catch (FileNotFoundException e) {}
            return null;
        }
        if(use_save) {
            try {
                return c.openFileInput(filename);
            }
            catch (FileNotFoundException e) {}
            //File test/arg.wzs contains a path separator
            catch (IllegalArgumentException e) {}
        }
        try {
            return c.getAssets().open(filename);
        } catch (IOException e) {}
        return null;
    }

    public abstract void write__(String s, String filename, boolean append);
	public void write__(String s, String filename) {
		write__(s, filename, false);
	}
    public static void write__(String s, String filename, boolean append, Context c) {
        try {
            FileOutputStream fos;
            if(c == null || filename.startsWith("/")) {
                fos = new FileOutputStream(filename, append);
            } else {
                //MODE_PRIVATE 只能被应用本身访问
                //MODE_APPEND 追加
                //MODE_WORLD_READABLE 可以被其他应用读取 Deprecated
                //MODE_WORLD_WRITEABLE 可以被其他应用写入 Deprecated
                int mode = Context.MODE_PRIVATE;
                if(append)
                    mode |= Context.MODE_APPEND;
                fos = c.openFileOutput(filename, mode);
            }
            fos.write(s.getBytes());
            fos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void write__(String s, String filename, Context c) {
        write__(s, filename, false, c);
    }

    public static String read__(InputStream is) throws IOException {
        if(is == null)
            return "";
        int lenght = is.available();
        byte[] buffer = new byte[lenght];
        is.read(buffer);
        is.close();
        return new String(buffer, "utf-8");
    }
    public String read__(String filename) throws IOException {
        return read__(open__(filename));
    }

    public static String copydir__(File from, File to) throws IOException {
        String s = "";
        File[] ls = from.listFiles();
        if(ls == null)
            throw I_.buzhichi__("有无权限？" + from.getName());
        for (File f : ls) {
            FileChannel i = new FileInputStream(f).getChannel();
            File f2 = new File(to, f.getName());
            f2.getParentFile().mkdirs();
            s += /*f + " " +*/ f2 + "\n";
            FileChannel o = new FileOutputStream(f2).getChannel();
            i.transferTo(0, i.size(), o);
            i.close();
            o.close();
        }
        return s;
    }

    public static File ext_dir__(String sub, I_ i) {
        if(!i.permi_.z__(Permi_.Companion.getExt_dir_(), i))
            throw I_.buliao__()/*"读写无权"*/;
        File f = Environment.getExternalStorageDirectory();
        wai:
        for(;;) {
            if(f != null) {
                if(f.canWrite())
                    break;
            }
            return null;
        }
        return new File(f, sub);
    }

    public static String for_del__(File f) {
        String s = "";
        if(f.exists()) {
            if(f.isDirectory()) {
                s += f.getName();
                for(File f1 : f.listFiles())
                    s += for_del__(f1);
                s += '\n';
                f.delete();
            } else
                s += f.delete() ? 1 : 0;
        }
        return s;
    }

    public interface Del_ok_ {
	    void z__(String name);
    }
    public static String[] del__(String[] a, Context c, Del_ok_ del_ok) {
        for(String name : a) {
            if(is_pub__(name)) {
                File f = new File(name);
                boolean b = f.delete();
                System.out.println("del " + f + " = " + b);
                if(b) {
                    for(;;) {
                        f = f.getParentFile();
                        if (f == null)
                            break;
                        b = f.delete();
                        System.out.println("del " + f + " = " + b);
                        if(!b)
                            break;
                    }
                } else
                    return null;
            } else {
                if(c.deleteFile(name))
                    del_ok.z__(name);
            }
        }
        return new String[] {"1"};
    }

    public static String[] mv__(String[] a) {
        String src = a[0];
        if(is_pub__(src)) {
            String tgt = a[1];
            if(tgt.endsWith("/"))
                tgt += src.substring(src.lastIndexOf("/") + 1);
            if(new File(src).renameTo(new File(tgt)))
                return new String[] {tgt};
        }
        return null;
    }

    public static String[] exist__(String[] a) {
        for(String name : a) {
            if(!new File(name).exists())
                return null;
        }
        return new String[] {"1"};
    }

    public static File get__(String val, Context c) {
        File f2 = null;
        if(is_pub__(val)) {
            f2 = new File(val);
            if(!f2.exists())
                f2 = null;
        } else
            for(File f : c.getFilesDir().listFiles((dir, s) -> s.equals(val))) {
                f2 = f;
                break;
            }
        if(f2 == null)
            throw I_.buzhichi__("没找到文件 " + val);
	    return f2;
    }

    public static boolean is_pub__(String path) {
	    return path.startsWith("/");
    }
}
