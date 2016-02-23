package com.project.demo.demoprojects.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by Kimsj on 2016. 2. 15..
 */
public class No3TabLayoutAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<String> listItems;

    public No3TabLayoutAdapter(FragmentManager fm, Context context, List<String> listItems) {
        super(fm);
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public Fragment getItem(int position) {
        String fragmentName = listItems.get(position).toString();
        Class<?> cls = null;
        try {
            cls = Class.forName(fragmentName);
            Constructor<?> constructor = cls.getConstructor();
            Object obj = constructor.newInstance(new Object[] {});
            return (Fragment) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return (listItems != null) ? listItems.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "# " + position + " tab";
        return title;
    }
}
