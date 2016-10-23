package com.jiatianmong.myapp.bean;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiatianmong on 2016-10-13 20:21
 */
public class FileService {
    private Context context;
    private InputStream inputStream;
    public FileService() {
    }
    public FileService(Context context) {
        this.context = context;
    }
    public static boolean saveContentToSdcard(String content, String filename) {
        boolean flag = false;
        FileOutputStream fileOutputStream = null;
        // 获得sdcard所在路径
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath(), filename);
        // 判断sdcard是否可用
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
    public static String getFileFromSd(String filename) {
        FileInputStream inputStream =null;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        // 获得sdcard所在路径
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath(), filename);
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            try {
                inputStream = new FileInputStream(file);
                int len = 0;
                byte[] data = new byte[1024];
                while ((len = inputStream.read(data)) != -1) {
                    arrayOutputStream.write(data, 0, len);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return new String(arrayOutputStream.toByteArray());
    }
}
