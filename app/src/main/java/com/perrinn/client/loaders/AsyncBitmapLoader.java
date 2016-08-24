package com.perrinn.client.loaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * This class is intends to load in a background task the Bitmap given
 * and set it in an image container. Parameters given may change in future releases.
 *
 * @since 07.08.2016
 * @author Alessandro
 */
public class AsyncBitmapLoader extends AsyncTask<Integer,Integer,Bitmap>{
    private WeakReference<ImageView> mBitmapHolder;
    private int mBitmapRes;
    private Context mContext;
    private OnAsyncBitmapLoaderCompletion mListener;

    public AsyncBitmapLoader(Context context, ImageView imageHolder){
        this.mContext = context;
        this.mBitmapHolder = new WeakReference<>(imageHolder);
    }

    public AsyncBitmapLoader(Context context, ImageView imageHolder, OnAsyncBitmapLoaderCompletion listener){
        this.mContext = context;
        this.mBitmapHolder = new WeakReference<>(imageHolder);
        this.mListener = listener;
    }



    @Override
    protected Bitmap doInBackground(Integer... params) {
        if(mContext == null) return null;
        if(params.length == 0) return null;
        mBitmapRes = params[0];
        Bitmap image = BitmapFactory.decodeResource(mContext.getResources(),mBitmapRes);
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(isCancelled()){
            return;
        }
        if(bitmap != null && mBitmapHolder != null){
            ImageView imageHolder = mBitmapHolder.get();
            if(imageHolder != null)
                imageHolder.setImageBitmap(bitmap);
            if(mListener != null){
                mListener.onComplete();
            }
        }
    }

    public int getBitmapRes(){
        return this.mBitmapRes;
    }

    public interface OnAsyncBitmapLoaderCompletion{
        void onComplete();
    }
}
