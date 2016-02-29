package com.project.demo.demoprojects.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.demo.demoprojects.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class No5TabWithPagerFragment extends Fragment {

    private TabLayout tabLayout;
    private Fragment f1, f2;

    public No5TabWithPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_with_pager, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.selection_tabs);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        f1 = new No4MultipleViewPagerFragment();
        f2 = new No2PagerWithRecyclerViewFragment();

        tabLayout.addTab(tabLayout.newTab().setText("Tab1"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));

        return v;
    }

    private void setCurrentFragment(int position) {
        switch (position) {
            case 0:
                setFragment(f1);
                break;
            case 1:
                setFragment(f2);
                break;
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

}
