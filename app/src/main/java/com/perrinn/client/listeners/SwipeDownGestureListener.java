package com.perrinn.client.listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * This class intends to detect a swipe down. It must be used with the GestureDetector.
 *
 * @since 15.08.2016
 * @author Alessandro
 */
public class SwipeDownGestureListener implements GestureDetector.OnGestureListener {
    private float mThreshold = 0;
    private SwipeDownListener mListener;

    public SwipeDownGestureListener(SwipeDownListener mListener) {
        this.mListener = mListener;
    }

    public SwipeDownGestureListener(float mThreshold, SwipeDownListener mListener) {
        this.mThreshold = mThreshold;
        this.mListener = mListener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(velocityY > mThreshold)
            mListener.onSwipeDown();
        return true;
    }

    public interface SwipeDownListener{
        void onSwipeDown();
    }
}
