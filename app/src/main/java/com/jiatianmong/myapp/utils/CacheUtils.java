package com.jiatianmong.myapp.utils;

import android.content.Context;

/**
 * Created by jiatianmong on 2016-10-13 20:11
 */
public class CacheUtils {

    public static void setCache(String url,String json,Context ctx){
        ShaPreUtils.setString(ctx,url,json);
    }
    public static String getCache(String url, Context ctx){
        //文件缓存: 查找有没有一个文件叫做MD5(url)的, 有的话,说明有缓存
        return ShaPreUtils.getString(ctx, url, null);
    }

}
