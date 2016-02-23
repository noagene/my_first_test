package com.project.demo.demoprojects.library;

/**
 * Created by Kimsj on 2016. 2. 23..
 */
public class BaseBackPressedListener {

    private OnBackPressedListener listener;

    public interface OnBackPressedListener {
        public boolean doBack();
    }

    public BaseBackPressedListener(OnBackPressedListener listener) {
        this.listener = listener;
    }
}
