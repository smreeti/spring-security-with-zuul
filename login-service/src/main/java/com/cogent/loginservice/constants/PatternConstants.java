package com.cogent.loginservice.constants;

/*THIS CLASS INCLUDES REGEX PATTERNS*/
public class PatternConstants {

    public interface NetworkConstants {
        String ARP = "arp";
        String ARP_A = "a";

        String REQUEST_HEADER = "X-FORWARDED-FOR";

        String LOCALHOST_IPV6_ADDRESS = "0:0:0:0:0:0:0:1";

        String PATTERN_REGEX_FOR_MAC_OR_LINUX = "[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+";
        String PATTERN_REGEX_FOR_WINDOWS = "[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+";
    }

    public interface EmailConstants{
        String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$";
    }
}
