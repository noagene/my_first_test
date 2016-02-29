package com.project.demo.demoprojects.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.demo.demoprojects.DemoModel;
import com.project.demo.demoprojects.DemoProject;
import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.activity.ContentsActivity;
import com.project.demo.demoprojects.library.BaseBackPressedListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class No4MultipleViewPagerFragment extends Fragment {

    private BaseBackPressedListener.OnBackPressedListener listener = new BaseBackPressedListener.OnBackPressedListener() {
        @Override
        public boolean doBack() {
            return true;
        }
    };

    ViewPager viewPager;
    List<DemoModel> items;

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
        PagerContainerAdapter adapter = new PagerContainerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(15);

        return v;
    }

    private class PagerContainerAdapter extends FragmentStatePagerAdapter {

        public PagerContainerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return (items == null ? 0 : items.size());
        }

        @Override
        public Fragment getItem(int position) {
            String text = items.get(position).label;
            return PagerFragment.create(text);
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
}
