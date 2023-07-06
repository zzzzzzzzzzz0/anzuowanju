package org.zhscript.clas;

/**
 * Created by zzzzzzzzzzz on 17-2-15.
 */

public abstract class Opt_ {
    interface Exec_ {
        void e__(String... s);
    }
    interface Exec2_ {
        void e__(int i, String... s);
    }
    interface Exec0_ {
        void e__();
    }
    interface ExecS_ {
        void e__(String s);
    }
    interface ExecI_ {
        void e__(int i);
    }

    static class Item_ {
        Exec_ exec_;
        Exec2_ exec2_;
        Exec0_ exec0_;
        ExecI_ exec_i_;
        ExecS_ exec_s_;
        String tag_;
        public boolean tag_equ__(String s) {
            if(tag_ == null)
                return false;
            return tag_.equals(s);
        }
        int argc_;
        public int argc__() {return argc_;}
        int i_;
        Item_(String tag, Exec_ exec) {
            tag_ = tag;
            exec_ = exec;
        }
        Item_(String tag, Exec0_ exec) {
            tag_ = tag;
            exec0_ = exec;
        }
        Item_(String tag, ExecS_ exec) {
            tag_ = tag;
            exec_s_ = exec;
        }
        Item_(String tag, int argc, Exec_ exec) {
            tag_ = tag;
            argc_ = argc;
            exec_ = exec;
        }
        Item_(String tag, int argc, Exec2_ exec) {
            tag_ = tag;
            argc_ = argc;
            exec2_ = exec;
        }
        Item_(int argc, Exec_ exec) {
            argc_ = argc;
            exec_ = exec;
        }
        Item_(int argc, Exec2_ exec) {
            argc_ = argc;
            exec2_ = exec;
        }
        Item_(ExecI_ exec) {
            argc_ = -1;
            exec_i_ = exec;
        }

        public String toString() {
            return tag_ + " (" + argc_ + ")";
        }
    }

    abstract Item_[] items__();
    Item_[] items_;
    Item_ item_;

    void init__() {
        for(Item_ i : items_) {
            i.i_ = 0;
        }
    }

    Opt_() {
        items_ = items__();
        for(Item_ i : items_) {
            if(i.tag_ == null) {
                item_ = i;
                break;
            }
        }
        init__();
    }

    int exec__(Item_ i, String[] a, int i2) {
        ++i.i_;
        if(i.argc__() > 0) {
            String[] a2 = Util_.a2__(a, i2 + (i.tag_ != null ? 1 : 0), i.argc__());
            if(i.exec_ != null)
                i.exec_.e__(a2);
            if(i.exec2_ != null)
                i.exec2_.e__(i.i_, a2);
        } else {
            if(i.exec_ != null)
                i.exec_.e__(new String[0]);
            if(i.exec2_ != null)
                i.exec2_.e__(i.i_, new String[0]);
            if(i.exec_s_ != null)
                i.exec_s_.e__(i.tag_);
            if(i.exec_i_ != null)
                i.exec_i_.e__(i2);
            if(i.exec0_ != null)
                i.exec0_.e__();
        }
        if(i.exec_i_ != null)
            return i.argc__();
        return i.argc__() - (i.tag_ == null ? 1 : 0);
    }

    void parse__(String[] a) {parse__(a, 0);}
    void parse__(String[] a, int from) {
        for(int i2 = from; i2 < a.length; i2++) {
            String s = a[i2];
            boolean has = false;
            for(Item_ i : items_) {
                if(i.tag_equ__(s)) {
                    has = true;
                    i2 += exec__(i, a, i2);
                    break;
                }
            }
            if(!has) {
                if(item_ != null) {
                    int i3 = exec__(item_, a, i2);
                    if (i3 < 0) {
                        return;
                    }
                    i2 += i3;
                } else {
                    String s2 = "";
                    for (Item_ i : items_) {
                        if (!s2.isEmpty())
                            s2 += " 或 ";
                        s2 += i;
                    }
                    throw I_.buzhichi__("-" + s + " 有效选项：" + s2);
                }
            }
        }
    }
}
