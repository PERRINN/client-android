package com.perrinn.client.beans;

/**
 * This class is just used to store the item's information of the lists in the profile settings
 * fragment.
 *
 * @author Alessandro
 * @since 08.07.2016
 */
public class SettingsProfileListItem {
    private String name;
    private String state;
    private boolean important;

    public SettingsProfileListItem(String name, String state, boolean important) {
        this.name = name;
        this.state = state;
        this.important = important;
    }

    public SettingsProfileListItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
