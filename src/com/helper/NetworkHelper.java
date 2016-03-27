/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class NetworkHelper {

    public static void main(String args[]) throws Exception {
       getMACAddress();

    }

    public static String getLocalIpAddress() throws SocketException {
        final String LOOPBACK_ADDR = "127.0.0.1";
        String ip = "";
        Set set = new HashSet();
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            //  System.out.println("\nDisplay name : " + netint.getDisplayName());
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                String str = inetAddress.getHostAddress();
                if (str.indexOf(":") == -1) {
//                    System.out.println("InetAddress : " + inetAddress.getCanonicalHostName());
//                    System.out.println("InetAddress getHostAddress : " + inetAddress.getHostAddress());
                    set.add(str);
                }
            }
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Object object = it.next();
            if (!object.toString().equalsIgnoreCase(LOOPBACK_ADDR)) {
                ip = object.toString();
                break;
            }
        }
        if (ip.length() == 0) {
            ip = LOOPBACK_ADDR;
        }
        System.out.println(" ip " + ip);
        return ip;
    }

       public static String getMACAddress(){
	StringBuilder sb = new StringBuilder();
	InetAddress ip;
	try {

		ip = InetAddress.getLocalHost();
		System.out.println("Current IP address : " + ip.getHostAddress());

		NetworkInterface network = NetworkInterface.getByInetAddress(ip);

		byte[] mac = network.getHardwareAddress();

		System.out.print("Current MAC address : ");

	
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		System.out.println(sb.toString());

	} catch (UnknownHostException e) {

		e.printStackTrace();

	} catch (SocketException e){

		e.printStackTrace();

	}
    return sb.toString();

   }

}
