package name.zzzzzzzzzzz.anzuowanju.clas;

/**
 * Created by zzzzzzzzzzz on 17-10-22.
 */

public class Resource_ {
    public Resource_() {}

    private boolean oni_;
    public static boolean oni__(Resource_ r) {
        //System.out.println("oni__" + (r == null ? r : r.oni_));
        return r != null && r.oni_;
    }
    public Resource_(boolean oni) {
        oni_ = oni;
    }
}
