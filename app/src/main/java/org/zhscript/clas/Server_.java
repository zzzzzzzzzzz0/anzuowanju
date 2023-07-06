package org.zhscript.clas;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import static org.nanohttpd.protocols.http.response.Response.newChunkedResponse;
import static org.nanohttpd.protocols.http.response.Response.newFixedLengthResponse;

/*import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;*/

/**
 * Created by zzzzzzzzzzz on 17-1-15.
 */

public class Server_ {
    /*ServerSocket ss_;

    class T extends Thread {
        @Override
        public void run() {
            byte[] b = new byte[1024];
            for(;;) {
                try {
                    Socket s = ss_.accept();
                    OutputStream os = s.getOutputStream();
                    //os.write(("HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=utf-8\r\n\r\n").getBytes());
                    os.write("hi".getBytes());
                    os.flush();
                    s.shutdownOutput();
                    InputStream is = s.getInputStream();
                    String s2 = "";
                    for(;;) {
                        int len = is.read(b);
                        if(len < 0)
                            break;
                        s2 += new String(b, 0, len);
                    }
                    System.out.println(s2);
                    os.close();
                    is.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    class T extends NanoHTTPD {
        public T() {
            super(port_);
        }

        @Override
        public Response handle(IHTTPSession session) {
            String url = session.getUri();
            if(url.equals("/"))
                url = "index" + Wzs_._wzs_;
            String qps = session.getQueryParameterString();
            if(qps != null)
                url += "?" + qps;
            try {
                String[] a = Wzs_.fx__(Url_.url__(url, false), zs_);
                if(a != null)
                    switch (a.length) {
                        case 3:
                            return newChunkedResponse(Status.OK, a[0], FileOp_.open__(a[2], true, i_.context__()));
                        case 1:
                            return newFixedLengthResponse(a[0]);
                    }
            } catch (MalformedURLException e) {
                i_.sendMessage__(e);
            }
            return super.handle(session);
        }
    }

    int port_;
    I_ i_;
    Zs_ zs_;
    T t_;

	public Server_(int port, I_ i, Zs_ zs) throws IOException {
        port_ = port;
        i_ = i;
        zs_ = zs;
        //ss_ = new ServerSocket(port);
        try {
            (t_ = new T()).start();
        } catch (Exception e) {
            i.sendMessage__(e.getLocalizedMessage());
        }
    }

    public void stop__() {
        if(t_ != null)
            t_.stop();
    }

    public static String[] ip__(int ip_width, Context c) throws SocketException {
        ArrayList<String> al = new ArrayList<>();
        if(c != null) {
            WifiManager wm = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
            if (!wm.isWifiEnabled()) {
                wm.setWifiEnabled(true);
            }
            WifiInfo wi = wm.getConnectionInfo();
            int ip = wi.getIpAddress();
            if(ip != 0)
                al.add(int2ip__(ip));
        }
        for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
            NetworkInterface ni = e.nextElement();
            for (Enumeration<InetAddress> e2 = ni.getInetAddresses(); e2.hasMoreElements();) {
                InetAddress ia = e2.nextElement();
                if (!ia.isLoopbackAddress() && ia.getAddress().length <= ip_width) {
                    String ip = ia.getHostAddress();
                    if(!al.contains(ip))
                        al.add(ip);
                }
            }
        }
        return al.toArray(new String[0]);
    }

    static String int2ip__(int i) {
        return   (i        & 0xFF) + "." +
                ((i >>  8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                ((i >> 24) & 0xFF);
    }
}
