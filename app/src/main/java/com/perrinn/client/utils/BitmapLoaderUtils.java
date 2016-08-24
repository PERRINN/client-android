package com.perrinn.client.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.perrinn.client.loaders.AsyncBitmapLoader;

import java.lang.ref.WeakReference;

/**
 * Created by alessandrosilacci on 24/08/16.
 */
public class BitmapLoaderUtils {

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
