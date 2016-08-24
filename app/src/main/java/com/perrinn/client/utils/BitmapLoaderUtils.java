package com.perrinn.client.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.widget.ImageView;

import com.perrinn.client.loaders.AsyncBitmapLoader;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by alessandrosilacci on 24/08/16.
 */
public class BitmapLoaderUtils {
    public static final Object mDiskLock = new Object();
    public static final int DISK_CACHE_DEFAULT_SIZE = 1024*1024*8;
    public static final String DISK_CACHE_DEFAULT_FOLDER = "cache";
    private static boolean mDiskCacheStart = true;
    private static DiskLruCache mDiskLruCache;

    public static void initDiskCache(Context context){
        initDiskCache(DISK_CACHE_DEFAULT_SIZE,context);
    }

    public static void initDiskCache(int diskSize, Context context){
        initDiskCache(diskSize,null, context);
    }

    public static void initDiskCache(String folder, Context context){
        initDiskCache(DISK_CACHE_DEFAULT_SIZE,folder, context);
    }

    public static void initDiskCache(final int diskSize, String folder, Context context){
        if(folder == null) folder = DISK_CACHE_DEFAULT_FOLDER;
        if(diskSize <= 0) return;
        File cacheDirectory = getDiskCacheDirectory(context,folder);
        AsyncTask<File,Void,Void> task = new AsyncTask<File, Void, Void>() {
            @Override
            protected Void doInBackground(File... params) {
                synchronized (mDiskLock){
                    File cache = params[0];
                    try {
                        mDiskLruCache = DiskLruCache.open(cache, Build.VERSION.SDK_INT,1,diskSize);
                        mDiskCacheStart = false;
                        mDiskLock.notifyAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }.execute(cacheDirectory);

    }

    public static void cacheBitmap(String key, Bitmap bitmap){
        synchronized (mDiskLock){
            try {
                if(mDiskLruCache != null && mDiskLruCache.getSnapshot(key) == null){
                    mDiskLruCache.put(key,bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap getBitmapFromCache(String key){
        synchronized (mDiskLock){
            while(mDiskCacheStart){
                try {
                    mDiskLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if(mDiskLruCache != null){
                return (Bitmap) mDiskLruCache.get(key);
            }
        }
        return null;
    }

    public static void loadBitmap(Context context, int resId, ImageView imageView){
        if(isRenderNeeded(resId,imageView)){
            AsyncBitmapLoader loader = new AsyncBitmapLoader(context,imageView);
            AsyncBitmapDrawable drawable = new AsyncBitmapDrawable(context.getResources(),null,loader);
            imageView.setImageDrawable(drawable);
            loader.execute(resId);
        }
    }

    private static boolean isRenderNeeded(int resId, ImageView imageView){
        AsyncBitmapLoader linkedLoader = getLinkedRunningTask(imageView);
        if(linkedLoader == null) return true; // if no loader running render is then needed
        int bitmapRes = linkedLoader.getBitmapRes();
        if(bitmapRes != 0 && bitmapRes == resId) return false;
        linkedLoader.cancel(true);
        return true;
    }

    private static File getDiskCacheDirectory(Context context, String path){
        final String diskCacheFolderPath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !Environment.isExternalStorageRemovable() ? context.getExternalCacheDir().getPath():
                context.getCacheDir().getPath();
        return new File(diskCacheFolderPath+File.separator+path);
    }

    private static AsyncBitmapLoader getLinkedRunningTask(ImageView imageView){
        if(imageView == null) return null;
        Drawable drawable = imageView.getDrawable();
        if(!(drawable instanceof AsyncBitmapDrawable)) return null;
        return ((AsyncBitmapDrawable) drawable).getAsyncBitmapLoader();
    }

    public static class AsyncBitmapDrawable extends BitmapDrawable{
        private final WeakReference<AsyncBitmapLoader> loaderReference;

        public AsyncBitmapDrawable(Resources res, Bitmap bitmap, AsyncBitmapLoader loader){
            super(res,bitmap);
            loaderReference = new WeakReference<AsyncBitmapLoader>(loader);
        }

        public AsyncBitmapLoader getAsyncBitmapLoader(){
            return loaderReference.get();
        }
    }
}
