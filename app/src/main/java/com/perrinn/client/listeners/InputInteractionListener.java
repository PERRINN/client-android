package com.perrinn.client.listeners;

/**
 * This listener interface has to be implemented in all fragments that shows the keyboard, except
 * keyboard shown for inputs in a Dialog.
 *
 * @since 13.08.2016
 * @author Alessandro
 */
public interface InputInteractionListener {
    /**
     * This method intends to notify the listener that the keyboard is state has changed.
     *
     * @param keyboardHidden true if the keyboard is hidden and false if shown.
     * */
    void onKeyboardStateChanged(boolean keyboardHidden);
}
