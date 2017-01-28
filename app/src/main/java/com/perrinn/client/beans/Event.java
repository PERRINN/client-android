package com.perrinn.client.beans;

import android.os.Bundle;

/**
 * Created by alessand.silacci on 17.01.2017.
 */

public class Event {
    public static final int EVENT_TEAMSCREENS_ONCOMPLETE = 0;
    public static final int EVENT_PAGE_CHANGED = 1;
    public static final int EVENT_PAGE_COUNT_CHANGED = 2;

    public static final int EVENT_INTERACTION_PRIVATECHAT = 10;
    public static final int EVENT_INTERACTION_INPUT = 11;

    public static final int EVENT_NAVIGATION_SELFPROFILE = 22;
    public static final int EVENT_NAVIGATION_PROFILE = 23;
    public static final int EVENT_NAVIGATION_CHAT = 24;
    public static final int EVENT_NAVIGATION_MAIL = 25;
    public static final int EVENT_NAVIGATION_CAL = 26;
    public static final int EVENT_NAVIGATION_ACTIVITY = 27;
    public static final int EVENT_NAVIGATION_DOCS = 28;
    public static final int EVENT_NAVIGATION_GUEST = 29;
    public static final int EVENT_NAVIGATION_IMAGES_01 = 30;
    public static final int EVENT_NAVIGATION_IMAGES_02 = 31;
    public static final int EVENT_NAVIGATION_MAPS = 32;
    public static final int EVENT_NAVIGATION_MIC = 33;
    public static final int EVENT_NAVIGATION_SPEAKER = 34;
    public static final int EVENT_NAVIGATION_WALLET = 35;
    public static final int EVENT_NAVIGATION_WALLET_HISTORY = 36;



    private int what;
    private int arg;
    private Bundle data;

    public Event(int what, int arg, Bundle data) {
        this.what = what;
        this.arg = arg;
        this.data = data;
    }

    public int getWhat() {
        return what;
    }

    public Bundle getData() {
        return data;
    }

    public int getArg() {
        return arg;
    }
}
