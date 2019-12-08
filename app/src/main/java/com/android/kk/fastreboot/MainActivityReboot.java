package com.android.kk.fastreboot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class MainActivityReboot extends AppCompatActivity {
    public static final String TAG = "kklog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cmd("su","reboot");
    }

    public static void cmd(String sh, String command) {
        Log.d(TAG, command);
        try {
            Process process = Runtime.getRuntime().exec(new String[]{sh, "-c", command});
            process.waitFor();
            String errStream = readFullyAsString(process.getErrorStream(), Charset.defaultCharset().name());
            String outStream = readFullyAsString(process.getInputStream(), Charset.defaultCharset().name());
            Log.d(TAG, outStream);
            Log.d(TAG, errStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFullyAsString(InputStream inputStream, String encoding) throws IOException {
        return readFully(inputStream).toString(encoding);
    }

    public static byte[] readFullyAsBytes(InputStream inputStream) throws IOException {
        return readFully(inputStream).toByteArray();
    }

    public static ByteArrayOutputStream readFully(InputStream inputStream)  throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos;
    }
}
