package com.example.rentlink.UtilsService;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;


public class UtilService {

    public void hideKeyboard(View view, Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public String getUrl() {
        //String ipAddress = getLocalIP(); // Retrieve IPv4 address
        String ipAddress = "172.20.123.139";
        //String ipAddress = "192.168.160.22";// hotspot
        //String ipAddress = "192.168.31.78";//NSU wifi
        String port = "3500";
        String baseUrl = "http://" + ipAddress + ":" + port + "/";
        return baseUrl;
    }


    public static String getLocalIP() {
        try {
            // Get the network interfaces and iterate over them
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = netInterfaces.nextElement();
                // Skip the loopback interface
                if (netInterface.isLoopback()) continue;
                // Get the interface addresses and iterate over them
                List<InterfaceAddress> interfaceAddresses = netInterface.getInterfaceAddresses();
                for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                    // Get the address and check if it is ipv4
                    InetAddress address = interfaceAddress.getAddress();
                    if (address instanceof Inet4Address) {
                        // Return the address as a string
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        // Return null if no ipv4 address is found
        return null;
    }



}

/*public class UtilService {

    public void hideKeyboard(View view, Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public String getUrl (){
        String IPaddress = "192.168.1.5"; // add your own IPv4 address
        String Port = "3500";
        //"http://192.168.1.2:3500/auth"
        String baseUrl = "http://"+IPaddress+":"+Port+"/";
        return  baseUrl ;
    }
}*/
