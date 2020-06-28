package com.shk.tester;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class LocalStorage {
    private static File sFile;
    private static JSONObject sData;

    public static void init(Context context) {
        try {
            sFile = new File(Environment.getExternalStorageDirectory(), "localStorage.json");
            if (!sFile.exists()) {
                sFile.createNewFile();
            }

            Scanner scanner = new Scanner(sFile);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append('\n');
            }
            scanner.close();

            try {
                sData = new JSONObject(sb.toString());
            } catch (Exception e) {
                sData = new JSONObject();
            }
        } catch (Exception e) {
            Log.e("tester", "LocalStorage.init", e);
        }
    }

    public static void put(String key, Object value) {
        try {
            if (value instanceof byte[]) {
                sData.put(key, Util.bytes2hex((byte[]) value));
            } else {
                sData.put(key, String.valueOf(value));
            }

            OutputStream os = new FileOutputStream(sFile);
            os.write(sData.toString().getBytes());
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e("tester", "LocalStorage.put", e);
        }
    }

    public static String getString(String key) {
        return sData.optString(key);
    }

    public static byte[] getBytes(String key) {
        String value = getString(key);
        return Util.hex2bytes(value);
    }
}
