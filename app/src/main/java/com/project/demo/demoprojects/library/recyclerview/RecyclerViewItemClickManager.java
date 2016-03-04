package com.project.demo.demoprojects.library.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.SoundEffectConstants;
import android.view.View;

import com.project.demo.demoprojects.R;

/**
 * Created by Kimsj on 2016. 2. 12..
 */
public class RecyclerViewItemClickManager {
    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position, long id);
    }

    public interface  OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position, long id);
    }

    private final RecyclerView recyclerView;
    private final TouchListener touchListener;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private RecyclerViewItemClickManager(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

        touchListener = new TouchListener(recyclerView);
        recyclerView.addOnItemTouchListener(touchListener);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        if (!recyclerView.isLongClickable()) {
            recyclerView.setLongClickable(true);
        }

        this.onItemLongClickListener = listener;
    }

    public static RecyclerViewItemClickManager addTo(RecyclerView recyclerView) {
        RecyclerViewItemClickManager clickManager = from(recyclerView);
        if (clickManager == null) {
            clickManager = new RecyclerViewItemClickManager(recyclerView);
            recyclerView.setTag(R.id.recyclerview_item_click_manager, clickManager);
        }

        return clickManager;
    }

    public static void removeFrom(RecyclerView recyclerView) {
        final RecyclerViewItemClickManager clickManager = from(recyclerView);
        if (clickManager == null) {
            return;
        }

        recyclerView.removeOnItemTouchListener(clickManager.touchListener);
        recyclerView.setTag(R.id.recyclerview_item_click_manager, null);
    }

    public static RecyclerViewItemClickManager from(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return null;
        }

        return (RecyclerViewItemClickManager) recyclerView.getTag(R.id.recyclerview_item_click_manager);
    }

    private class TouchListener extends ClickItemTouchListener {

        TouchListener(RecyclerView hostView) {
            super(hostView);
        }

        @Override
        boolean performItemClick(RecyclerView parent, View view, int position, long id) {
            if (onItemClickListener != null) {
                view.playSoundEffect(SoundEffectConstants.CLICK);
                onItemClickListener.onItemClick(parent, view, position, id);
                return true;
            }
            return false;
        }

        @Override
        boolean performItemLongClick(RecyclerView parent, View view, int position, long id) {
            if (onItemLongClickListener != null) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return onItemLongClickListener.onItemLongClick(parent, view, position, id);
            }
            return false;
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
