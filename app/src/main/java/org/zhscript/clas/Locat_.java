package org.zhscript.clas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

/**
 * Created by zzzzzzzzzzz on 16-12-16.
 */

public class Locat_ {
    Zs_ zs_;
    Context c_;
    public Locat_(Zs_ zs) {
        zs_ = zs;
    }

    String code_;
    boolean z__(Location l) throws IOException {
        double lat = l.getLatitude(), lon = l.getLongitude();
        Geocoder gc = new Geocoder(c_);
        String city = "";
        try {
            int i = 0;
            for (Address a : gc.getFromLocation(lat, lon, 5)) {
                if(i > 0)
                    city += "；";
                city += a.getCountryName() + a.getLocality();
                if(a.getSubThoroughfare() != null)
                    city += a.getSubThoroughfare();
                i++;
            }
        } catch (IOException e) {
            city += e.getLocalizedMessage();
        }
        String[] ret = zs_.i__(code_,
                "经度", String.format("%f", lon),
                "纬度", String.format("%f", lat),
                "城市", city);
        return ret.length == 1 && ret[0].equals("up");
    }

    class Locat_Opt extends Opt_ {
        boolean use_last = true, use2 = true, use_network, use_gps;

        @Override
        Item_[] items__() {
            return new Item_[] {
                    new Opt_.Item_("network", () -> {
                        use_network = true;
                        use2 = false;
                    }),
                    new Opt_.Item_("gps", () -> {
                        use_gps = true;
                        use2 = false;
                    }),
                    new Opt_.Item_("nolast", () -> {
                        use_last = false;
                    }),
            };
        }
    }

    public void z__(String[] a, I_ i) throws IOException {
        Locat_Opt opt = new Locat_Opt();
        opt.parse__(a, 1);

        code_ = a[0];
        c_ = i.context__();
        lm_ = (LocationManager) c_.getSystemService(Context.LOCATION_SERVICE);
        /*List<String> providers = lm_.getProviders(true);
        String p;
        if (providers.contains(LocationManager.GPS_PROVIDER))
            p = LocationManager.GPS_PROVIDER;
        else if (providers.contains(LocationManager.NETWORK_PROVIDER))
            p = LocationManager.NETWORK_PROVIDER;
        else
            return false;*/
        if(!i.permi_.z__(Permi_.Companion.getLocat_(), i))
            throw I_.buliao__()/*"定位无权"*/;
        if (ActivityCompat.checkSelfPermission(c_, Manifest.permission.ACCESS_FINE_LOCATION  ) != PackageManager.PERMISSION_GRANTED
        &&  ActivityCompat.checkSelfPermission(c_, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        lm_.removeUpdates(update_);
        if(opt.use_last) {
            Location l = null;
            if(l == null && (opt.use2 || opt.use_network))
                l = lm_.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(l == null && (opt.use2 || opt.use_gps))
                l = lm_.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(l != null) {
                z__(l);
                return;
            }
        }
        if(opt.use2 || opt.use_network)
            lm_.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, update_);
        if(opt.use2 || opt.use_gps)
            lm_.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, update_);
    }

    LocationManager lm_;

    LocationListener update_ = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            try {
                if(!z__(location))
                    lm_.removeUpdates(update_);
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
