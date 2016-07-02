package com.perrinn.client.beans;

/**
 * This class is only specifying informations about the dock dots.
 *
 * @author Alessandro
 * @since 02.06.2016
 */
public class DockIndicator {
    private boolean active;

    public DockIndicator(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
