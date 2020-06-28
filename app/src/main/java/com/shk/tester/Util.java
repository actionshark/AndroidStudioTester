package com.shk.tester;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class Util {
    private static final char[] HEX_CHAR = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static char n2char(int n) {
        return HEX_CHAR[n];
    }

    public static int char2n(char ch) {
        if ('0' <= ch && ch <= '9') {
            return ch - '0';
        } else if ('A' <= ch && ch <= 'F') {
            return ch - 'A' + 10;
        } else if ('a' <= ch && ch <= 'f') {
            return ch - 'a' + 10;
        } else {
            throw new RuntimeException("invalid char " + ch);
        }
    }

    public static String bytes2hex(byte[] bs) {
        StringBuilder sb = new StringBuilder();

        try {
            for (byte b : bs) {
                sb.append(n2char(b >> 4 & 0xf))
                        .append(n2char(b & 0xf));
            }
        } catch (Exception e) {
        }

        return sb.toString();
    }

    public static byte[] hex2bytes(String hex) {
        try {
            byte[] bs = new byte[hex.length() / 2];

            int index = 0;

            for (int i = 0; i < bs.length; i++) {
                int a = char2n(hex.charAt(index++));
                int b = char2n(hex.charAt(index++));
                bs[i] = (byte) ((a << 4) | b);
            }

            return bs;
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static String str2hex(String str) {
        return bytes2hex(str.getBytes());
    }

    public static boolean checkPermission(Activity activity, String permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[] {permission}, requestCode);
                return true;
            }
        }

        return false;
    }
}