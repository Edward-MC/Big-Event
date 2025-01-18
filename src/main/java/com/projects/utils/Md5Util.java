package com.projects.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    /**
     * The default password string combination is used to convert bytes into 16-base characters, which is used by Apache to verify the correctness of downloaded files
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName() + "Init Failed，MessageDigest doesn't Support MD5Util.");
            nsaex.printStackTrace();
        }
    }

    /**
     * Generates the md5 checksum of the string
     *
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * Determines whether the MD5 check sum of the string matches a known MD5 code
     *
     * @param password  String needed to Verify
     * @param md5PwdStr Know Md5 Code
     * @return
     */
    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }


    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// Convert Highest 4 bits, >>>
        // Logically shifted to the right, shifting the sign bits to the right together, and no difference between the two symbols was found here
        char c1 = hexDigits[bt & 0xf];// Convert Lowest 4 bits
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}
