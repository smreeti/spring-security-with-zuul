package com.cogent.loginservice.utils;

import com.cogent.loginservice.constants.PatternConstants.NetworkConstants;
import com.cogent.loginservice.responseDTO.NetworkResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*THIS CLASS GIVES NETWORK INFORMATION(IP ADDRESSS AND MAC ADDRESS) OF CLIENT AND SERVER*/
public class NetworkUtils {

    public static Function<String, String> getClientMACAddress = (ip) -> {
        Pattern macpt = null;

        /* Find OS and set command according to OS*/
        String OS = System.getProperty("os.name").toLowerCase();

        /*ARP stands for Address Resolution Protocol. When you try to ping an IP address on your local network,
        say 192.168.1.1, your system has to turn the IP address 192.168.1.1 into a MAC address. Because it is
        a broadcast packet, it is sent to a special MAC address that causes all machines on the network to receive it.*/
        String[] cmd;
        if (OS.contains("win")) {
           /*For Windows*/
            macpt = Pattern.compile(NetworkConstants.PATTERN_REGEX_FOR_WINDOWS);
            String[] a = {NetworkConstants.ARP, NetworkConstants.ARP_A, ip};
            cmd = a;
        } else {
            /*For Mac OS X, Linux*/
            macpt = Pattern.compile(NetworkConstants.PATTERN_REGEX_FOR_MAC_OR_LINUX);
            String[] a = {NetworkConstants.ARP, ip};
            cmd = a;
        }

        BufferedReader reader = null;

        try {
            /*Run command*/
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            /*Read output with BufferedReader*/
            System.out.println(p.getInputStream());
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();

            /*Line is of format:*/
            /*Address                  HWtype  HWaddress           Flags Mask            Iface */
            /*192.168.100.8            ether   d8:5d:e2:a4:f5:85   C                     wlp5s0*/
            /*Loop through lines*/
            while (line != null) {
                Matcher m = macpt.matcher(line);

                /*when Matcher finds a Line then return it as result*/
                if (m.find())
                    return m.group();

                line = reader.readLine();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Return empty string if no MAC is found
        return "";
    };

    public static Function<NetworkResponseDTO, NetworkResponseDTO> getLocalHostAddress = (network) -> {

        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {

                    InetAddress addr = addresses.nextElement();

                    if (Inet4Address.class == addr.getClass())
                        network.setIpAddress(addr.getHostAddress());

                    /*GET MAC ADDRESS*/
                    if (Objects.isNull(network.getMacAddress())) {
                        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(addr);

                        byte[] mac = networkInterface.getHardwareAddress();

                    /*CONVERT mac(IN DECIMAL FORMAT) TO HEXADECIMAL FORMAT */
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                            network.setMacAddress(sb.toString());
                        }
                    }
                }
            }

            return network;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    };

    public static Function<HttpServletRequest, NetworkResponseDTO> getDeviceAddresses = (request) -> {
        NetworkResponseDTO network = new NetworkResponseDTO();

        String remoteAddr = "";

        if (!Objects.isNull(request)) {
            remoteAddr = request.getHeader(NetworkConstants.REQUEST_HEADER);
            if (remoteAddr == null || "".equals(remoteAddr))
                remoteAddr = request.getRemoteAddr();
        }

        if (remoteAddr.equals(NetworkConstants.LOCALHOST_IPV6_ADDRESS)) {
            getLocalHostAddress.apply(network);
        } else {
            network.setIpAddress(remoteAddr);
            network.setMacAddress(getClientMACAddress.apply(remoteAddr));
        }

        return network;
    };


    /*TO UNDERSTAND HOW MATCHER WORKS*/
    public static void main(String[] args) {
        // Get the regex to be checked
        String regex = "Geeks";

        // Create a pattern from regex
        Pattern pattern = Pattern.compile(regex);

        // Get the String to be matched
        String stringToBeMatched = "GeeksForGeeks";

        // Create a matcher for the input String
        Matcher matcher = pattern.matcher(stringToBeMatched);

        // Get the subsequence
        // using find() method
        System.out.println(matcher.find());

    }
}
