package com.perrinn.client.helpers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Extension of a ViewPager that can toggle the swipe between the fragments contained.
 * By default swipe is enabled.
 *
 * @since 04.08.2016
 * @author Alessandro
 */
public class ToggledViewPager extends ViewPager {
    private boolean swipeEnabled = true;

    public ToggledViewPager(Context context) {
        super(context);
    }

    public ToggledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.swipeEnabled ? super.onTouchEvent(ev):false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.swipeEnabled ? super.onInterceptTouchEvent(ev):false;
    }

    /**
     * This method returns the swipeable state of the fragments contained in the viewpager.
     *
     * @return boolean true if swipe is enabled otherwise false.
     * */
    public boolean isSwipeEnabled(){
        return this.swipeEnabled;
    }

    /**
     * This method allows the swipeable state change of the viewpager.
     *
     * @param enable true if the swipe has to be enabled or false if swipe needs to be disabled.
     * */
    public void setSwipeEnabled(boolean enable){
        this.swipeEnabled = enable;
    }
}
