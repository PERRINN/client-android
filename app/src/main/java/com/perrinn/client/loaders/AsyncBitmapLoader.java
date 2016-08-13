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
    private Context mContext;

    public AsyncBitmapLoader(Context context, ImageView imageHolder){
        this.mContext = context;
        this.mBitmapHolder = new WeakReference<>(imageHolder);
    }


    @Override
    protected Bitmap doInBackground(Integer... params) {
        if(mContext == null) return null;
        if(params.length == 0) return null;
        Bitmap image = BitmapFactory.decodeResource(mContext.getResources(),params[0]);
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null && mBitmapHolder != null){
            ImageView imageHolder = mBitmapHolder.get();
            if(imageHolder != null)
                imageHolder.setImageBitmap(bitmap);
        }
    }
}
