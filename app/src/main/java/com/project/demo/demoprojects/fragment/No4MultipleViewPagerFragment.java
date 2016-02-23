package com.project.demo.demoprojects.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.demo.demoprojects.DemoModel;
import com.project.demo.demoprojects.DemoProject;
import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.activity.ContentsActivity;
import com.project.demo.demoprojects.library.BaseBackPressedListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class No4MultipleViewPagerFragment extends Fragment implements View.OnLayoutChangeListener {

    private BaseBackPressedListener.OnBackPressedListener listener = new BaseBackPressedListener.OnBackPressedListener() {
        @Override
        public boolean doBack() {
            if (layoutBig.getVisibility() == View.VISIBLE) {
                layoutSmall.setVisibility(View.VISIBLE);
                layoutBig.setVisibility(View.GONE);
                return false;
            }
            return true;
        }
    };

    ViewPager viewPager;
    List<DemoModel> items;

    ImageView btnSmall;
    View layoutSmall;
    View layoutBig;

    Animation resize;
    Animation fadeIn;

    public No4MultipleViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiple_view_pager, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.multiple_view_pager);

        ((ContentsActivity) getActivity()).setOnBackPressedListener(listener);

        items = DemoProject.getDemoData();
        PagerContainerAdapter adapter = new PagerContainerAdapter();
        viewPager.setAdapter(adapter);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(15);
        viewPager.addOnLayoutChangeListener(this);

        layoutSmall = v.findViewById(R.id.layout_small);
        layoutBig = v.findViewById(R.id.layout_big);

        btnSmall = (ImageView) v.findViewById(R.id.btn_small);
        btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSmall.setVisibility(View.GONE);
                layoutBig.setVisibility(View.VISIBLE);

                viewPager.startAnimation(resize);
            }
        });

        return v;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (resize != null) {
            resize = null;
        }


        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        resize = new ResizeAnimation(v, (oldRight - oldLeft), (oldBottom - oldTop), (right - left), (bottom - top));
        resize.setInterpolator(new DecelerateInterpolator());
        resize.setDuration(1000);
    }

    private class PagerContainerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return (items == null ? 0 : items.size());
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String text = items.get(position).label;
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.listrow_item, container, false);
            ((TextView) view.findViewById(R.id.txt_label_item)).setText(text);

            container.addView(view);

            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public float getPageWidth(int position) {
            return 0.93f;
        }
    }

    public class ResizeAnimation extends Animation {
        private View mView;
        private float mToHeight;
        private float mFromHeight;

        private float mToWidth;
        private float mFromWidth;

        public ResizeAnimation(View v, float fromWidth, float fromHeight, float toWidth, float toHeight) {
            mToHeight = toHeight;
            mToWidth = toWidth;
            mFromHeight = fromHeight;
            mFromWidth = fromWidth;
            mView = v;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float height =
                    (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
            float width = (mToWidth - mFromWidth) * interpolatedTime + mFromWidth;
            mView.getLayoutParams().height = (int) height;
            mView.getLayoutParams().width = (int) width;
            mView.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

}
