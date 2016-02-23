package com.project.demo.demoprojects.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.adapter.No3TabLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class No3TabLayoutFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewpager;
    private No3TabLayoutAdapter adapter;

    public No3TabLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_layout, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.sliding_tabs);
        viewpager = (ViewPager) v.findViewById(R.id.tab_viewpager);

        List<String> items = new ArrayList<>();
        items.add(No1RecyclerViewFragment.class.getName());
        items.add(No2PagerWithRecyclerViewFragment.class.getName());

        adapter = new No3TabLayoutAdapter(getActivity().getSupportFragmentManager(), getContext(), items);

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        return v;
    }

}
